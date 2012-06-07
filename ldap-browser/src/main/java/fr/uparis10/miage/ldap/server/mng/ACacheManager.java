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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.server.itf.IWithCache;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;
import fr.uparis10.miage.ldap.shared.utils.MapUtils;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public abstract class ACacheManager<I_TYPE extends IIndexable, K_TYPE, V_TYPE extends Map<I_TYPE, K_TYPE>>
    extends ABasicLdapManager<I_TYPE, K_TYPE, V_TYPE> implements IWithCache {
	private final ReadWriteLock dataLock = new ReentrantReadWriteLock();
	private List<V_TYPE> valList;
	private boolean isDataLoaded = false;
	private Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> indexMap = null;

	/**
	 * Shall be a singleton
	 */
	protected ACacheManager() {
	}

	@Override
	public final void refresh() {
		final List<V_TYPE> locFreshValList = getFreshList();
		final Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> locFreshIndexMap = buildIndexMap(locFreshValList);
		dataLock.writeLock().lock();
		try {
			valList = locFreshValList;
			indexMap = locFreshIndexMap;
			isDataLoaded = true;
		} catch (final Exception locExc) {
		} finally {
			dataLock.writeLock().unlock();
		}
	}

	public final List<V_TYPE> indexedSearch(@NotNull final I_TYPE parIndex, @NotNull final K_TYPE parKeyVal) throws DataNotLoadedException {
		if (!isDataLoaded) {
			throw new DataNotLoadedException();
		}
		dataLock.readLock().lock();
		try {
			final Map<K_TYPE, List<V_TYPE>> locIndex = indexMap.get(parIndex);
			final List<V_TYPE> locValList = locIndex.get(parKeyVal);
			if (null == locValList) {
				return Collections.<V_TYPE> emptyList();
			}
			assert (!locValList.isEmpty());
			return Collections.<V_TYPE> unmodifiableList(locValList);
		} finally {
			dataLock.readLock().unlock();
		}
	}

	/**
	 * Returns a list of objects for a list of possible key values.
	 * @param parIndex
	 * @param parKeyValList
	 * @return
	 * @throws DataNotLoadedException
	 */
	public final List<V_TYPE> indexedSearch(final I_TYPE parIndex, final Collection<K_TYPE> parKeyValList) throws DataNotLoadedException {
		final ArrayList<V_TYPE> locAllValList = new ArrayList<V_TYPE>(parKeyValList.size());
		// on utilise TreeSet pour garantir l'unicité et l'ordonance
		final Collection<K_TYPE> locKeyValSet = parKeyValList instanceof Set ? parKeyValList : new TreeSet<K_TYPE>(parKeyValList);
		for (final K_TYPE locKeyVal : locKeyValSet) {
			final List<V_TYPE> locValList = indexedSearch(parIndex, locKeyVal);
			if (null == locValList) {
				continue;
			}
			locAllValList.addAll(locValList);
		}
		locAllValList.trimToSize();

		return locAllValList;

	}

	public final List<V_TYPE> indexedSearch(final K_TYPE parKeyVal) throws DataNotLoadedException {
		if (!isDataLoaded) {
			throw new DataNotLoadedException();
		}
		dataLock.readLock().lock();
		try {
			final ArrayList<V_TYPE> locResList = new ArrayList<V_TYPE>();
			for (final Map<K_TYPE, List<V_TYPE>> locIndex : indexMap.values()) {
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
			dataLock.readLock().unlock();
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
		if (!isDataLoaded) {
			throw new DataNotLoadedException();
		}
		dataLock.readLock().lock();
		try {
			final ArrayList<V_TYPE> locResList = new ArrayList<V_TYPE>();
			final String locSearchFor = parIsCaseSens ? parKeyVal.toString() : parKeyVal.toString().toLowerCase();
			for (final V_TYPE locObject : valList) {
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
			dataLock.readLock().unlock();
		}
	}

	/**
	 * @return a list containing all the objects in the system
	 */
	public final List<V_TYPE> getAllObjList() {
		// We can't use unmodifiable lists here - GWT limitation, at least for the moment
		return new ArrayList<V_TYPE>(valList);
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

	private final List<V_TYPE> getFreshList() {
		return getFreshList(getGenericFilter());
	}

	protected abstract String getGenericFilter();

	protected abstract Map<I_TYPE, Map<K_TYPE, List<V_TYPE>>> createNewIndex();
}
