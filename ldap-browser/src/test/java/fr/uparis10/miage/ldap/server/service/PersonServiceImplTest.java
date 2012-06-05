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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Person;
import fr.uparis10.miage.ldap.shared.obj.SearchRequestModel;

/**
 * @author OMAR
 *
 */
public class PersonServiceImplTest {

	private PersonServiceImpl _personServiceImpl;

	@Before
	public final void beforeTest() throws ServicePropertiesIOException {
		TestingServicesPropertiesManager.initTestInstance();
		_personServiceImpl = new PersonServiceImpl();
	}

	@After
	public final void afterTest() {
		_personServiceImpl = null;
	}

	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.service.PersonServiceImpl#getPersonsAll()}.
	 * @throws UserNotLoggedException 
	 * @throws ServicePropertiesIOException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testGetPersonsAll() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		List<Person> personsList = _personServiceImpl.getPersonsAll();
		assertNotNull(personsList);
	}

	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.service.PersonServiceImpl#searchPersons(java.lang.String)}.
	 * @throws UserNotLoggedException 
	 * @throws ServicePropertiesIOException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSearchPersonsString() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		List<Person> personsList = _personServiceImpl.searchPersons("");
		assertNotNull(personsList);
	}

	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.service.PersonServiceImpl#searchPersons(fr.uparis10.miage.ldap.shared.obj.SearchRequestModel)}.
	 * @throws UserNotLoggedException 
	 * @throws ServicePropertiesIOException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSearchPersonsSearchRequestModel() throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		SearchRequestModel searchRequestModel = new SearchRequestModel();
		searchRequestModel.setLookUpGroup(true);
		searchRequestModel.setLookUpOrgUnit(true);
		searchRequestModel.setLookUpPerson(true);
		
		Map<String, Boolean> groupOptions = new HashMap<String, Boolean>();
		groupOptions.put("etudiants", true);
		
		Map<String, Boolean> orgUnitOptions = new HashMap<String, Boolean>();
		groupOptions.put("segmi", true);
		
		searchRequestModel.setGroupOptions(groupOptions);
		searchRequestModel.setRequest("boomar");
		searchRequestModel.setOrgUnitOptions(orgUnitOptions);
		
		List<Person> personsList = _personServiceImpl.searchPersons(searchRequestModel);
		assertNotNull(personsList);
		assertTrue(personsList.size() > 0);
	}
}
