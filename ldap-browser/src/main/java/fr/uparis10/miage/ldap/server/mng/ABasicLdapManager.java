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
 * Creation date: May 30, 2012
 */
package fr.uparis10.miage.ldap.server.mng;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.shared.exc.ContainerException;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public abstract class ABasicLdapManager<I_TYPE extends IIndexable, K_TYPE, V_TYPE extends Map<I_TYPE, K_TYPE>> {
	/**
	 * @return a fresh list from the container application (LDAP in this project)
	 */
	protected final List<V_TYPE> getFreshList(
			@NotNull final String parFilter) {
		try {
			final ALdapCtxManager locCtxMng = getCtxManager();
			final DirContext locCtx = locCtxMng.getContext();
			final String locDN = getDNPrefix() + locCtxMng.getBaseDN(); 

			final SearchControls locSearchCtrl = new SearchControls();
			locSearchCtrl.setSearchScope(SearchControls.ONELEVEL_SCOPE);
			final NamingEnumeration<SearchResult> locResultSet = locCtx.search(locDN, parFilter, locSearchCtrl);
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
			throw new ContainerException(locExc);
		} catch (final FileNotFoundException locExc) {
			throw new ContainerException(locExc);
		} catch (final IOException locExc) {
			throw new ContainerException(locExc);
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
			} catch (final ContainerException locExc) {
				Logger.getLogger(ACacheManager.class.getName()).log(Level.SEVERE, "For attribute value [" + locAttrName + "]", locExc);
				throw locExc;
			}
			assert (null != locAttrEnum);

			locResMap.put(locAttrEnum, decodeAttribute(locAttr, locAttrEnum));
		}

		return locResMap;
	}

	protected abstract V_TYPE createNewObject();

	protected abstract I_TYPE valueOfIndex(final String parName);

	protected abstract K_TYPE decodeAttribute(@NotNull final Attribute parInput, @NotNull final I_TYPE parType) throws NamingException;

	protected abstract ALdapCtxManager getCtxManager() throws FileNotFoundException, IOException, NamingException;

	protected abstract String getDNPrefix();
}
