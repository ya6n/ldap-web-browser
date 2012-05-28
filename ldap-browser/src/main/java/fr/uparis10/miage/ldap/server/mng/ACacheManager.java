/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License v3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Creation date: May 26, 2012
 */
package fr.uparis10.miage.ldap.server.mng;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.server.utils.MapUtils;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public abstract class ACacheManager<I_TYPE extends IIndexable<K_TYPE>, K_TYPE, V_TYPE extends Map<I_TYPE, K_TYPE>> {
	private final ReadWriteLock _dataLock = new ReentrantReadWriteLock();
	private List<V_TYPE> _valList;
	private boolean _isDataLoaded = false;
	private Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> _indexMap = null;

	/**
	 * Shall be a singleton
	 */
	protected ACacheManager() {
	}

	public final void refresh() {
		final List<V_TYPE> locFreshValList = getFreshList();
		final Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> locFreshIndexMap = buildIndexMap(locFreshValList);
		_dataLock.writeLock().lock();
		try {
			_valList = locFreshValList;
			_indexMap = locFreshIndexMap;
			_isDataLoaded = true;
		} catch (final Exception locExc) {
		} finally {
			_dataLock.writeLock().unlock();
		}
	}

	public final List<V_TYPE> indexedSearch(@NotNull final I_TYPE parIndex, @NotNull final K_TYPE parKeyVal) throws DataNotLoadedException {
		if (!_isDataLoaded) {
			throw new DataNotLoadedException();
		}
		_dataLock.readLock().lock();
		try {
			final Map<K_TYPE, List<V_TYPE>> locIndex = _indexMap.get(parIndex);
			final List<V_TYPE> locValList = locIndex.get(parKeyVal);
			if (null == locValList) {
				return Collections.<V_TYPE> emptyList();
			}
			assert (!locValList.isEmpty());
			return Collections.<V_TYPE> unmodifiableList(locValList);
		} finally {
			_dataLock.readLock().unlock();
		}
	}

	public final List<V_TYPE> indexedSearch(final K_TYPE parKeyVal) throws DataNotLoadedException {
		if (!_isDataLoaded) {
			throw new DataNotLoadedException();
		}
		_dataLock.readLock().lock();
		try {
			final ArrayList<V_TYPE> locResList = new ArrayList<V_TYPE>();
			for (final Map<K_TYPE, List<V_TYPE>> locIndex : _indexMap.values()) {
				final List<V_TYPE> locValList = locIndex.get(parKeyVal);
				if (null == locValList) {
					continue;
				}
				assert (!locValList.isEmpty());
				locResList.addAll(locValList);
			}
			if (locResList.isEmpty()) {
				return Collections.<V_TYPE> emptyList();
			}
			return locResList;
		} finally {
			_dataLock.readLock().unlock();
		}
	}

	/**
	 * ACHTUNG: Non-indexed search - very heavy
	 */
	public final List<V_TYPE> dummySearch(@NotNull final K_TYPE parKeyVal) throws DataNotLoadedException {
		return dummySearch(parKeyVal, false, false);
	}

	/**
	 * ACHTUNG: Non-indexed search - very heavy
	 */
	public final List<V_TYPE> dummySearch(@NotNull final K_TYPE parKeyVal, final boolean parExactMatch, final boolean parIsCaseSens)
	    throws DataNotLoadedException {
		if (!_isDataLoaded) {
			throw new DataNotLoadedException();
		}
		_dataLock.readLock().lock();
		try {
			final ArrayList<V_TYPE> locResList = new ArrayList<V_TYPE>();
			final String locSearchFor = parIsCaseSens ? parKeyVal.toString() : parKeyVal.toString().toLowerCase();
			for (final V_TYPE locObject : _valList) {
				for (final K_TYPE locKeyVal : locObject.values()) {
					final String locSearchInto = parIsCaseSens ? locKeyVal.toString() : locKeyVal.toString().toLowerCase();
					if (parExactMatch ? locSearchInto.equals(locSearchFor) : locSearchInto.contains(locSearchFor)) {
						locResList.add(locObject);
						break;
					}
				}
			}
			if (locResList.isEmpty()) {
				return Collections.<V_TYPE> emptyList();
			}
			return locResList;
		} finally {
			_dataLock.readLock().unlock();
		}
	}

	/**
	 * @return an unmodifiable list containing all the objects in the system
	 */
	public final List<V_TYPE> getAllObjList() {
		return Collections.<V_TYPE> unmodifiableList(_valList);
	}

	/**
	 * @return a fresh list from the container application (LDAP in this project)
	 */
	private final List<V_TYPE> getFreshList() {
		try {
			// Get the context used to communicate with the LDAP server throughout
			final DirContext locCtx = LdapCtxManager.getInstance().getContext();
			final String locDN = getDNPrefix() + LdapCtxManager.getInstance().getBaseDN();

			final SearchControls locSearchCtrl = new SearchControls();
			locSearchCtrl.setSearchScope(SearchControls.ONELEVEL_SCOPE);
			final NamingEnumeration<SearchResult> locResultSet = locCtx.search(locDN, getGenericFilter(), locSearchCtrl);
			final ArrayList<V_TYPE> locResMapList = new ArrayList<V_TYPE>();
			while (locResultSet.hasMore()) {
				// Get the next individual result
				final SearchResult locResult = locResultSet.next();
				final NamingEnumeration<? extends Attribute> locAttrSet = locResult.getAttributes().getAll();
				final V_TYPE locAttrMap = createObjectForResult(locAttrSet);
				locResMapList.add(locAttrMap);
			}

			locResMapList.trimToSize();
			return locResMapList;
		} catch (final NamingException locExc) {
			throw new RuntimeException(locExc);
		} catch (final FileNotFoundException locExc) {
			throw new RuntimeException(locExc);
		} catch (final IOException locExc) {
			throw new RuntimeException(locExc);
		}
	}

	private final V_TYPE createObjectForResult(final NamingEnumeration<? extends Attribute> parAttrSet) throws NamingException {
		// Display all resulting attributes
		final V_TYPE locResMap = createNewObject();
		while (parAttrSet.hasMore()) {
			final Attribute locAttr = parAttrSet.next();
			final String locAttrName = locAttr.getID();
			assert (null != locAttrName);
			final I_TYPE locAttrEnum;
			try {
				locAttrEnum = valueOfIndex(locAttrName);
			} catch (final RuntimeException locExc) {
				Logger.getLogger(ACacheManager.class.getName()).log(Level.SEVERE, "For attribute value [" + locAttrName + "]", locExc);
				throw locExc;
			}
			assert (null != locAttrEnum);

			locResMap.put(locAttrEnum, locAttrEnum.decodeAttribute(locAttr));
		}

		return locResMap;
	}

	/**
	 * Builds a index list based on a list of values.
	 * 
	 * @param parValList
	 *          the list of values to Index
	 * @return a list of indexes (maps)
	 */
	private final Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> buildIndexMap(final List<V_TYPE> parValList) {
		final Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> locResIxdMap = createNewIndex();
		for (final V_TYPE locObject : parValList) {
			for (final Entry<I_TYPE, K_TYPE> locEntry : locObject.entrySet()) {
				final I_TYPE locAttrName = locEntry.getKey();
				assert (null != locAttrName);
				if (!locAttrName.isIndexed()) {
					continue;
				}
				final K_TYPE locAttrVal = locEntry.getValue();
				assert (null != locAttrVal);
				MapUtils.<I_TYPE, K_TYPE, V_TYPE> putInIndexMap(locResIxdMap, locAttrName, locAttrVal, locObject);
			}
		}
		return locResIxdMap;
	}

	protected abstract String getDNPrefix();

	protected abstract String getGenericFilter();

	protected abstract Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> createNewIndex();

	protected abstract V_TYPE createNewObject();

	protected abstract I_TYPE valueOfIndex(final String parName);
}
