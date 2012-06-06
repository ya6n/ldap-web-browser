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
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.client.service.OrgUnitService;
import fr.uparis10.miage.ldap.server.mng.OrgUnitManager;
import fr.uparis10.miage.ldap.shared.enums.EnumOrgUnitAttr;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;

/**
 * @author OMAR
 * 
 */
@SuppressWarnings("serial")
public class OrgUnitServiceImpl extends RemoteServiceServlet implements OrgUnitService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.client.service.OrgUnitService#getOrgUnitsAll()
	 */
	@Override
	public List<OrgUnit> getOrgUnitsAll() throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException {
		UserLoginChecker.getInstance().check(this.getThreadLocalRequest());
		List<OrgUnit> listOrgUnit = OrgUnitManager.getInstance().getAllObjList();
		ArrayList<OrgUnit> result = new ArrayList<OrgUnit>();
		if (listOrgUnit != null) {
			result.addAll(listOrgUnit);
		}
		return result;
	}

	@Override
	public List<OrgUnit> getPersonOrgUnits(String supannEntiteAffectation, String supannEntiteAffectationPrincipale) throws IllegalArgumentException,
	    UserNotLoggedException, ServicePropertiesIOException, DataNotLoadedException {
		List<OrgUnit> result = new ArrayList<OrgUnit>();
		List<String> personOrgUnitList = new ArrayList<String>();

		if (supannEntiteAffectation == null)
			supannEntiteAffectation = "";
		String[] personAffectations = supannEntiteAffectation.split(";");

		if (personAffectations != null &&
		    personAffectations.length > 0) {
			personOrgUnitList.addAll(Arrays.asList(personAffectations));
		}

		if (supannEntiteAffectationPrincipale != null &&
		    !supannEntiteAffectationPrincipale.equals("")) {
			personOrgUnitList.add(supannEntiteAffectationPrincipale);
		}

		if (personOrgUnitList.size() > 0) {
			result = fillPersonsOrgUnitsList(personOrgUnitList);
		}

		return result;
	}

	/**
	 * @param listOrgUnit
	 * @param result
	 * @param personOrgUnitList
	 * @return
	 * @throws UserNotLoggedException
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 * @throws DataNotLoadedException
	 */
	private List<OrgUnit> fillPersonsOrgUnitsList(List<String> personOrgUnitList) throws IllegalArgumentException, ServicePropertiesIOException,
	    UserNotLoggedException, DataNotLoadedException {
		// List<OrgUnit> listOrgUnit = getOrgUnitsAll();
		final List<OrgUnit> locAllOrgUnitList = new ArrayList<OrgUnit>();
		for (final String locOrgName : personOrgUnitList) {
			final List<OrgUnit> locOrgUnitList = OrgUnitManager.getInstance().indexedSearch(EnumOrgUnitAttr.supannCodeEntite, locOrgName);
			if (null == locOrgUnitList) {
				continue;
			}
			locAllOrgUnitList.addAll(locOrgUnitList);
		}
		return locAllOrgUnitList;
	}
}
