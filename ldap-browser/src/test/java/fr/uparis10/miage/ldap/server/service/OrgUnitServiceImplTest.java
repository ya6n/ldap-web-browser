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

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;

/**
 * @author OMAR
 *
 */
public class OrgUnitServiceImplTest {
	private OrgUnitServiceImpl _orgUnitServiceImpl;
	
	@Before
	public final void beforeTest() throws ServicePropertiesIOException {
		TestingServicesPropertiesManager.initTestInstance();
		_orgUnitServiceImpl = new OrgUnitServiceImpl();
	}

	@After
	public final void afterTest() {
		_orgUnitServiceImpl = null;
	}

	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.service.OrgUnitServiceImpl#getOrgUnitsAll()}.
	 * @throws UserNotLoggedException 
	 * @throws ServicePropertiesIOException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testGetOrgUnitsAll() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		List<OrgUnit> listOrgUnit =  _orgUnitServiceImpl.getOrgUnitsAll();
		assertNotNull(listOrgUnit);
	}
	
	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.service.OrgUnitServiceImpl#getPersonOrgUnits()}.
	 * @throws UserNotLoggedException 
	 * @throws ServicePropertiesIOException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testGetPersonOrgUnits() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		List<OrgUnit> personOrgUnitList =  _orgUnitServiceImpl.getPersonOrgUnits("", "");
		assertNotNull(personOrgUnitList);
	}
}
