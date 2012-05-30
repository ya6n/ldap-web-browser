/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 gorodenco
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
 * Creation date: 28 mai 2012
 */
package fr.uparis10.miage.ldap.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.client.service.PersonService;
import fr.uparis10.miage.ldap.server.mng.PeopleManager;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.obj.Person;
import fr.uparis10.miage.ldap.shared.obj.SearchRequestModel;

/**
 * @author gorodenco
 * 
 */
@SuppressWarnings("serial")
public class PersonServiceImpl extends RemoteServiceServlet implements PersonService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.client.service.PersonService#getPersonsAll()
	 */
	@Override
	public List<Person> getPersonsAll() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.client.service.PersonService#searchPersons(java.
	 * lang.String)
	 */
	@Override
	public List<Person> searchPersons(String request) throws IllegalArgumentException {
		List<Person> listPerson = null;
		try {
			listPerson = PeopleManager.getInstance().dummySearch(request);
		} catch (DataNotLoadedException e) {
			e.printStackTrace();
			listPerson = new ArrayList<Person>();
		}
		return listPerson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.client.service.PersonService#searchPersons(fr.uparis10
	 * .miage.ldap.shared.obj.SearchRequestModel)
	 */
	@Override
	public List<Person> searchPersons(SearchRequestModel requestModel) throws IllegalArgumentException {
		PeopleManager peopleManager = PeopleManager.getInstance();
		List<Person> listPerson = new ArrayList<Person>();

		if (requestModel.getLookUpGroup()) {
			for (Entry<String, Boolean> currentEntry : requestModel.getGroupOptions().entrySet()) {
				String key = currentEntry.getKey();
				Boolean value = currentEntry.getValue();

				if (value) {
					try {
						listPerson.retainAll(peopleManager.indexedSearch(EnumPersonAttr.supannRole,
						    key));
					} catch (DataNotLoadedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (requestModel.getLookUpOrgUnit()) {
			for (Entry<String, Boolean> currentEntry : requestModel.getOrgUnitOptions().entrySet()) {
				String key = currentEntry.getKey();
				Boolean value = currentEntry.getValue();
				if (value) {
					try {
						listPerson.retainAll(peopleManager.indexedSearch(EnumPersonAttr.ou,
						    key));
					} catch (DataNotLoadedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (requestModel.getLookUpPerson()) {
			try {
				listPerson.retainAll(peopleManager.dummySearch(requestModel.getRequest()));
			} catch (DataNotLoadedException e) {
				e.printStackTrace();
			}
		}

		return listPerson;
	}
}
