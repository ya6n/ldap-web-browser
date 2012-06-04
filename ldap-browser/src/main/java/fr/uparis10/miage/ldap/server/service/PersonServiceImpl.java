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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.client.service.PersonService;
import fr.uparis10.miage.ldap.server.mng.PeopleManager;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Person;
import fr.uparis10.miage.ldap.shared.obj.SearchRequestModel;

/**
 * @author gorodenco
 * 
 */
@SuppressWarnings("serial")
public class PersonServiceImpl extends RemoteServiceServlet implements PersonService {

	List<Person> result;
	SearchRequestModel requestModel;
	PeopleManager peopleManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.client.service.PersonService#getPersonsAll()
	 */
	@Override
	public List<Person> getPersonsAll() throws IllegalArgumentException, UserNotLoggedException {
		UserLoginChecker.getInstance().check();
		result = new ArrayList<Person>();
		List<Person> listPerson = PeopleManager.getInstance().getAllObjList();
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
	public List<Person> searchPersons(String request) throws IllegalArgumentException, UserNotLoggedException {
		UserLoginChecker.getInstance().check();
		result = new ArrayList<Person>();
		try {
			result.addAll(PeopleManager.getInstance().dummySearch(request));
		} catch (DataNotLoadedException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "The LDAP tree wasn't charged. Not just yet.", e);
			result = new ArrayList<Person>();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.client.service.PersonService#searchPersons(fr.uparis10
	 * .miage.ldap.shared.obj.SearchRequestModel)
	 */
	@Override
	public List<Person> searchPersons(SearchRequestModel requestModel) throws IllegalArgumentException, UserNotLoggedException {
		UserLoginChecker.getInstance().check();
		peopleManager = PeopleManager.getInstance();

		List<Person> listPerson = new ArrayList<Person>();
		listPerson.addAll(peopleManager.getAllObjList());

		result = new ArrayList<Person>();
		this.requestModel = requestModel;

		boolean indicateur;

		for (Person person : listPerson) {
			indicateur = true;

			indicateur = searByPerson(person);

			if (indicateur) {
				indicateur = searchByGroup(person);
			}
			if (indicateur) {
				indicateur = searchByOrgUnit(person);
			}

			if (indicateur) {
				result.add(person);
			}
		}

		return result;
	}

	/**
   * @param person
   * @return
   */
  private boolean searchByOrgUnit(Person person) {
  	boolean indicateur = true;
	  if (requestModel.getLookUpOrgUnit() &&
	      requestModel.getOrgUnitOptions().size() > 0) {
	  	indicateur = false;
	  	for (Entry<String, Boolean> currentEntry : requestModel.getOrgUnitOptions().entrySet()) {
	  		if (currentEntry.getValue()) {
	  			String key = currentEntry.getKey();
	  			if (person.get(EnumPersonAttr.ou).equals(key)) {
	  				indicateur = true;
	  			}
	  		}
	  	}
	  }
	  return indicateur;
  }

	/**
	 * @param person
	 * @return
	 */
	private boolean searchByGroup(Person person) {
		boolean indicateur = true;
		if (requestModel.getLookUpGroup() &&
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
		return indicateur;
	}

	/**
	 * @param person
	 * @return
	 */
	private boolean searByPerson(Person person) {
		boolean indicateur = true;
		if (requestModel.getLookUpPerson() &&
		    !requestModel.getRequest().equals("")) {
			try {
				indicateur = peopleManager.dummySearch(requestModel.getRequest()).contains(person);
			} catch (DataNotLoadedException e) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, "The LDAP tree wasn't charged. Not just yet.", e);
			}
		}
		return indicateur;
	}
}
