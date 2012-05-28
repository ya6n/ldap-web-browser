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
 * Creation date: May 28, 2012
 */
package fr.uparis10.miage.ldap.server.mng;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class SearchTestUtils {
	public final static <I_TYPE extends IIndexable<String>, V_TYPE extends Map<I_TYPE, String> & Comparable<V_TYPE>>
	    void testDummySearchById(
	        @NotNull final ACacheManager<I_TYPE, String, V_TYPE> parMng,
	        @NotNull final I_TYPE parIndex) throws DataNotLoadedException {
		final List<V_TYPE> locAllObjList = parMng.getAllObjList();
		assertNotNull(locAllObjList);
		assertTrue(!locAllObjList.isEmpty());

		for (final V_TYPE locObject : locAllObjList) {
			final String locKeyVal = locObject.get(parIndex);
			final List<V_TYPE> locSearchList = parMng.dummySearch(locKeyVal, true, true);
			assertNotNull(locSearchList);
			assertTrue(!locSearchList.isEmpty());
			assertTrue(locSearchList.contains(locObject));
		}
	}

	public final static <I_TYPE extends IIndexable<String>, V_TYPE extends Map<I_TYPE, String> & Comparable<V_TYPE>>
	    void testIndexedSearchWhiteBox(
	        @NotNull final ACacheManager<I_TYPE, String, V_TYPE> parMng,
	        @NotNull final I_TYPE parIndex) throws DataNotLoadedException {
		final List<V_TYPE> locAllObjList = parMng.getAllObjList();
		assertNotNull(locAllObjList);
		assertTrue(!locAllObjList.isEmpty());

		for (final V_TYPE locObject : locAllObjList) {
			final String locKeyValue = locObject.get(parIndex);
			final List<V_TYPE> locSearchList = parMng.indexedSearch(parIndex, locKeyValue);
			assertNotNull(locSearchList);
			assertTrue(!locSearchList.isEmpty());
			assertTrue(locSearchList.contains(locObject));
		}
	}

	public final static <I_TYPE extends IIndexable<String>, V_TYPE extends Map<I_TYPE, String> & Comparable<V_TYPE>>
	    void testIndexedSearchBlackBox(
	        @NotNull final ACacheManager<I_TYPE, String, V_TYPE> parMng,
	        @NotNull final I_TYPE parIndex) throws DataNotLoadedException {
		final List<V_TYPE> locAllObjList = parMng.getAllObjList();
		assertNotNull(locAllObjList);
		assertTrue(!locAllObjList.isEmpty());

		for (final V_TYPE locObject : locAllObjList) {
			final String locKeyValue = locObject.get(parIndex);
			final List<V_TYPE> locSearchList = parMng.indexedSearch(locKeyValue);
			assertNotNull(locSearchList);
			assertTrue(!locSearchList.isEmpty());
			assertTrue(locSearchList.contains(locObject));
		}
	}

}
