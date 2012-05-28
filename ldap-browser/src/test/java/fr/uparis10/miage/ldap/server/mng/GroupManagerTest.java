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

import org.junit.Before;
import org.junit.Test;

import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class GroupManagerTest {

	@Before
	public final void beforeTest() {
		GroupManager.getInstance().refresh();
	}

	@Test(timeout = 10000L)
	public final void testDummySearchById() throws DataNotLoadedException {
		SearchTestUtils.testDummySearchById(GroupManager.getInstance(), EnumGroupAttr.cn);
	}

	@Test(timeout = 10000L)
	public final void testIndexedSearchWhiteBox() throws DataNotLoadedException {
		SearchTestUtils.testIndexedSearchWhiteBox(GroupManager.getInstance(), EnumGroupAttr.cn);
	}

	@Test(timeout = 10000L)
	public final void testIndexedSearchBlackBox() throws DataNotLoadedException {
		SearchTestUtils.testIndexedSearchBlackBox(GroupManager.getInstance(), EnumGroupAttr.cn);
	}

}
