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
		List<Person> listPerson = PeopleManager.getInstance().getAllObjList();
		ArrayList<Person> result = new ArrayList<Person>();
		if (listPerson != null) {
			result.addAll(listPerson);
		}
		return result;
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
		List<Person> listPerson = new ArrayList<Person>();
		try {
			listPerson.addAll(PeopleManager.getInstance().dummySearch(request));
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
		listPerson.addAll(peopleManager.getAllObjList());

		List<Person> result = new ArrayList<Person>();

		boolean indicateur;

		for (Person person : listPerson) {
			indicateur = true;

			if (requestModel.getLookUpPerson() &&
			    !requestModel.getRequest().equals("")) {
				try {
					indicateur = peopleManager.dummySearch(requestModel.getRequest()).contains(person);
				} catch (DataNotLoadedException e) {
					e.printStackTrace();
				}
			}

			if (indicateur &&
			    requestModel.getLookUpGroup() &&
			    requestModel.getGroupOptions().size() > 0) {
				indicateur = false;
				for (Entry<String, Boolean> currentEntry : requestModel.getGroupOptions().entrySet()) {
					if (currentEntry.getValue()) {
						String key = currentEntry.getKey();
						if (person.get(EnumPersonAttr.supannRole).equals(key)) {
							indicateur = true;
						}
					}
				}
			}

			if (indicateur &&
			    requestModel.getLookUpOrgUnit() &&
			    requestModel.getOrgUnitOptions().size() > 0) {
				indicateur = false;
				for (Entry<String, Boolean> currentEntry : requestModel.getOrgUnitOptions().entrySet()) {
					if (currentEntry.getValue()) {
						String key = currentEntry.getKey();
						if (person.get(EnumPersonAttr.ou).equals(key)) {

						}
					}
				}
			}

			if (indicateur) {
				result.add(person);
			}

		}

		return result;
	}
}
