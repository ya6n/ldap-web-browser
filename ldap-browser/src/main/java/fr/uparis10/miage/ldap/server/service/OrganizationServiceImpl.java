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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.client.service.OrganizationService;
import fr.uparis10.miage.ldap.server.mng.OrganizationManager;
import fr.uparis10.miage.ldap.shared.enums.EnumOrganizationAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Organization;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author OMAR
 * 
 */
@SuppressWarnings("serial")
public class OrganizationServiceImpl extends RemoteServiceServlet implements OrganizationService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.client.service.OrganizationService#getOrganizationsAll
	 * ()
	 */
	@Override
	public List<Organization> getOrganizationsAll() throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException {
		UserLoginChecker.getInstance().check(this.getThreadLocalRequest().getSession());
		List<Organization> listOrganization = OrganizationManager.getInstance().getAllObjList();
		ArrayList<Organization> result = new ArrayList<Organization>();
		if (listOrganization != null) {
			result.addAll(listOrganization);
		}
		return result;
	}

	@Override
	public List<Organization> getPersonOrganizations(Person person) throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException {
		List<Organization> result = new ArrayList<Organization>();
		List<String> personOrganizationList = new ArrayList<String>();

		String[] personOrganismes = person.get(EnumPersonAttr.supannOrganisme).split(";");

		if (personOrganismes != null &&
		    personOrganismes.length > 0) {
			personOrganizationList.addAll(Arrays.asList(personOrganismes));
		}

		String personOrganization = person.get(EnumPersonAttr.o);
		if (personOrganization != null &&
		    !personOrganization.equals("")) {
			personOrganizationList.add(personOrganization);
		}

		if (personOrganizationList.size() > 0) {
			result = fillPersonsOrganizationList(personOrganizationList);
		}

		return result;
	}

	private List<Organization> fillPersonsOrganizationList(List<String> personOrganisationList) throws IllegalArgumentException, ServicePropertiesIOException,
	    UserNotLoggedException {
		List<Organization> listOrganization = getOrganizationsAll();
		List<Organization> result = new ArrayList<Organization>();
		for (Organization organization : listOrganization) {
			if (Collections.binarySearch(personOrganisationList,
			    organization.get(EnumOrganizationAttr.o)) != -1) {
				result.add(organization);
			}
		}
		return result;
	}
}
