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

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.uparis10.miage.ldap.junit.AssertUtils;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;

/**
 * This class will test:<br>
 * - PeopleManager<br>
 * - GroupManager<br>
 * - OrgUnitManager<br>
 * - OrganizationManager<br>
 * 
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
@RunWith(Parameterized.class)
public final class ACacheManagerTest<I_TYPE extends IIndexable<String>, V_TYPE extends Map<I_TYPE, String> & Comparable<V_TYPE>> {
	final ACacheManager<I_TYPE, String, V_TYPE> _mng;

	public ACacheManagerTest(final ACacheManager<I_TYPE, String, V_TYPE> parMng) {
		_mng = parMng;
	}

	@Parameters
	public final static List<Object[]> getMngInstList() {
		return Arrays.<Object[]> asList(
		    new Object[] { PeopleManager.getInstance() },
		    new Object[] { GroupManager.getInstance() },
		    new Object[] { OrgUnitManager.getInstance() },
		    new Object[] { OrganizationManager.getInstance() });
	}

	@Before
	public final void beforeTest() {
		_mng.refresh();
	}

	/**
	 * Tests that after 2 refreshes we get the same list in terms of content, but
	 * not in terms of references.
	 */
	@Test(timeout = 20000L)
	public final void testRefresh() {
		final List<V_TYPE> locList1 = _mng.getAllObjList();
		assertNotNull(locList1);
		_mng.refresh();
		final List<V_TYPE> locList2 = _mng.getAllObjList();
		assertNotNull(locList2);
		AssertUtils.<V_TYPE> assertListsAreClones(locList1, locList2);
	}

	@Test(timeout = 10000L)
	public final void testCreateNewIndex() {
		final Map<I_TYPE, Map<String, List<V_TYPE>>> locIndex1 = _mng.createNewIndex();
		assertNotNull(locIndex1);
		assertTrue(locIndex1.isEmpty());
		final Map<I_TYPE, Map<String, List<V_TYPE>>> locIndex2 = _mng.createNewIndex();
		assertNotNull(locIndex2);
		assertTrue(locIndex2.isEmpty());
		
		assertTrue(locIndex1 != locIndex2);
	}

	@Test(timeout = 10000L)
	public final void testCreateNewObject() {
		final V_TYPE locObj1 = _mng.createNewObject();
		assertNotNull(locObj1);
		final V_TYPE locObj2 = _mng.createNewObject();
		assertNotNull(locObj2);
		
		assertTrue(locObj1 != locObj2);
	}

	@Test(timeout = 10000L)
	public final void testDummySearchBlackBox() throws DataNotLoadedException {
		final List<V_TYPE> locAllObjList = _mng.getAllObjList();
		assertNotNull(locAllObjList);
		assertTrue(!locAllObjList.isEmpty());
		
		final List<V_TYPE> locSearchList = _mng.dummySearch("");
		assertNotNull(locSearchList);
		assertTrue(!locSearchList.isEmpty());
		
		AssertUtils.assertListsAreSame(locAllObjList, locSearchList);
	}
	
	@Test(timeout = 10000L)
	public final void testDummySearcWhiteBox() throws DataNotLoadedException {
		final List<V_TYPE> locAllObjList = _mng.getAllObjList();
		assertNotNull(locAllObjList);
		assertTrue(!locAllObjList.isEmpty());
		
		final List<V_TYPE> locSearchList = _mng.dummySearch("", false, true);
		assertNotNull(locSearchList);
		assertTrue(!locSearchList.isEmpty());
		
		AssertUtils.assertListsAreSame(locAllObjList, locSearchList);
	}
}
