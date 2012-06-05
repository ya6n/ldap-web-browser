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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.uparis10.miage.ldap.server.mng.PeopleManager;
import fr.uparis10.miage.ldap.shared.enums.EnumOrganizationAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Organization;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author OMAR
 * 
 */
public final class OrganizationServiceImplTest {
	private OrganizationServiceImpl theTestInst;

	
	@Before
	public final void setUp() throws ServicePropertiesIOException {
		TestingServicesPropertiesManager.initTestInstance();
		theTestInst = new OrganizationServiceImpl();
	}

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.OrganizationServiceImpl#getOrganizationsAll()}
	 * .
	 * 
	 * @throws UserNotLoggedException
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
	@Test(timeout = 10000L)
	public final void testGetOrganizationsAll() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		final List<Organization> locOrgList = theTestInst.getOrganizationsAll();
		assertNotNull(locOrgList);
		assertFalse(locOrgList.isEmpty());
	}

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.OrganizationServiceImpl#getOrganizationsAll()}
	 * .
	 * 
	 * @throws UserNotLoggedException
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 * @throws DataNotLoadedException
	 */
  @Test(timeout = 10000L)
	public final void testGetPersonOrganizations() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException, DataNotLoadedException {
		final List<Person> locAdminList = PeopleManager.getInstance().indexedSearch(EnumPersonAttr.uid, "admin");
		assertNotNull(locAdminList);
		assertFalse(locAdminList.isEmpty());
		assertEquals(1, locAdminList.size());
		final Person locAdmin = locAdminList.get(0);
		// until here we may have test related bugs, now starting the real tests
		final List<Organization> locOrgList = theTestInst.getPersonOrganizations(locAdmin);
		assertNotNull(locOrgList);
		assertFalse(locOrgList.isEmpty());
		for (final Organization locOrg : locOrgList) {
			final String locAdminOrgName = locAdmin.get(EnumPersonAttr.supannOrganisme);
			assertNotNull(locAdminOrgName);
			final String locOrgName = locOrg.get(EnumOrganizationAttr.supannCodeEntite);
			assertNotNull(locOrgName);
			assertEquals(locAdminOrgName, locOrgName);
		}
	}
}
