/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 OMAR
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
 * Creation date: 1 juin 2012
 */
package fr.uparis10.miage.ldap.server.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.uparis10.miage.ldap.server.mng.GroupManager;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Group;

/**
 * @author OMAR
 * 
 */
public class GroupServiceImplTest {

	private GroupServiceImpl theTestInst;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public final void setUp() throws Exception {
		TestingServicesPropertiesManager.initTestInstance();
		theTestInst = new GroupServiceImpl();
	}

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.GroupServiceImpl#getGroupsAll()}
	 * .
	 * 
	 * @throws UserNotLoggedException
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
	@Test(timeout = 10000L)
	public final void testGetGroupsAll() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		final List<Group> locGroupList = theTestInst.getGroupsAll();
		assertNotNull(locGroupList);
		assertFalse(locGroupList.isEmpty());
	}

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.GroupServiceImpl#getPersonGroups()}
	 * .
	 * 
	 * @throws IllegalArgumentException
	 * @throws ServicePropertiesIOException
	 * @throws UserNotLoggedException
	 * @throws DataNotLoadedException
	 */
	public final void testGetPersonGroups() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException, DataNotLoadedException {
		final List<Group> locGroupList = theTestInst.getPersonGroups("admin");
		final List<Group> locExpGrList = GroupManager.getInstance().dummySearch("admin");
		for (final Group locGroup : locGroupList) {
			assertTrue(locExpGrList.contains(locGroup));
		}
	}
}
