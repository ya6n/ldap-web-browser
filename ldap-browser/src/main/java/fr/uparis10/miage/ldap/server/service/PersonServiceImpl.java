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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.client.service.PersonService;
import fr.uparis10.miage.ldap.server.mng.GroupManager;
import fr.uparis10.miage.ldap.server.mng.OrgUnitManager;
import fr.uparis10.miage.ldap.server.mng.PeopleManager;
import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumOrgUnitAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.exc.DataNotLoadedException;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Group;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;
import fr.uparis10.miage.ldap.shared.obj.Person;
import fr.uparis10.miage.ldap.shared.obj.SearchRequestModel;
import fr.uparis10.miage.ldap.shared.utils.ListUtils;

/**
 * @author OMAR
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
	public List<Person> getPersonsAll() throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException {
		UserLoginChecker.getInstance().check(this.getThreadLocalRequest());
		final List<Person> locResult = PeopleManager.getInstance().getAllObjList();
		assert (locResult instanceof ArrayList);
		return locResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.client.service.PersonService#searchPersons(java.
	 * lang.String)
	 */
	@Override
	public final List<Person> searchPersons(final String parRequest) throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException {
		UserLoginChecker.getInstance().check(this.getThreadLocalRequest());
		List<Person> locResult = null;
		try {
			@SuppressWarnings("unchecked")
			final List<List<Person>> locListOfList = Arrays.<List<Person>> asList(
			    // Searching for Persons
			    PeopleManager.getInstance().dummySearch(parRequest),
			    // Searching in the OrgUnit fields:
			    searchInOrgUnits(parRequest),
			    // Searching in the Group fields:
			    searchInGroups(parRequest));
			final List<Person> locMergedList = ListUtils.<Person> mergeListOfList(locListOfList);
			return locMergedList;
		} catch (final DataNotLoadedException locExc) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "The LDAP tree wasn't charged. Not just yet.", locExc);
			locResult = Collections.<Person> emptyList();
		}
		return locResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.client.service.PersonService#searchPersons(fr.uparis10
	 * .miage.ldap.shared.obj.SearchRequestModel)
	 */
	@Override
	public final List<Person> searchPersons(final SearchRequestModel parRequestModel) throws IllegalArgumentException, UserNotLoggedException,
	    ServicePropertiesIOException {
		UserLoginChecker.getInstance().check(this.getThreadLocalRequest());
		final String locRequest = parRequestModel.getRequest();
		assert (null != locRequest);
		List<Person> locResult = null;
		try {
			final List<List<Person>> locListOfList = new ArrayList<List<Person>>();
			if (parRequestModel.getLookUpPerson()) {
				final List<Person> locList = PeopleManager.getInstance().dummySearch(locRequest);
				assert (null != locList);
				locListOfList.add(locList);
			}
			if (parRequestModel.getLookUpOrgUnit()) {
				final List<Person> locList = searchInOrgUnits(locRequest);
				assert (null != locList);
				locListOfList.add(locList);
			}
			if (parRequestModel.getLookUpGroup()) {
				final List<Person> locList = searchInGroups(locRequest);
				assert (null != locList);
				locListOfList.add(locList);
			}

			// FIXME: optimize this
			final List<Person> locMergedList = ListUtils.<Person> mergeListOfList(locListOfList);
			final Map<String, Boolean> locOrgFilter = parRequestModel.getOrgUnitOptions();
			final List<Person> locOrgFiltList;
			if (locOrgFilter != null) {
				locOrgFiltList = filterByOrgUnitOptions(locMergedList, locOrgFilter);
			} else {
				locOrgFiltList = locMergedList;
			}
			assert (null != locOrgFiltList);
			final Map<String, Boolean> locGroupFilter = parRequestModel.getGroupOptions();
			final List<Person> locGroupFiltList;
			if (locGroupFilter != null) {
				locGroupFiltList = filterByGroupOptions(locOrgFiltList, locGroupFilter);
			} else {
				locGroupFiltList = locOrgFiltList;
			}
			assert (null != locGroupFiltList);

			return new ArrayList<Person>(new HashSet<Person>(locGroupFiltList));
		} catch (final DataNotLoadedException locExc) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "The LDAP tree wasn't charged. Not just yet.", locExc);
			locResult = Collections.<Person> emptyList();
		}
		return locResult;
	}

	/**
	 * This method does a dummy search over all the OrgUnit, and then finds the
	 * Persons associated to these OrgUnits
	 * 
	 * @param parRequest
	 *          a string to search for
	 * @return a List of persons, or an empty list
	 * @throws DataNotLoadedException
	 */
	private final List<Person> searchInOrgUnits(@NotNull final String parRequest) throws DataNotLoadedException {
		final List<OrgUnit> locOrgUnitList = OrgUnitManager.getInstance().dummySearch(parRequest);
		assert (null != locOrgUnitList);
		if (locOrgUnitList.isEmpty()) {
			return Collections.<Person> emptyList();
		}
		// found some OrgUnits
		final Collection<String> locOrgUnitCodes = new TreeSet<String>();
		for (final OrgUnit locUnit : locOrgUnitList) {
			final String locUnitCode = locUnit.get(EnumOrgUnitAttr.supannCodeEntite);
			if (null != locUnitCode) {
				locOrgUnitCodes.add(locUnitCode);
			}
		}
		if (locOrgUnitCodes.isEmpty()) {
			return Collections.<Person> emptyList();
		}
		// there are some non-empty unit codes
		@SuppressWarnings("unchecked")
		final List<List<Person>> locListOfList = Arrays.<List<Person>> asList(
		    // FIXME: will it work for the cases "orgUnit1;orgUnit2;orgUnit3"?
		    PeopleManager.getInstance().indexedSearch(EnumPersonAttr.supannEntiteAffectation, locOrgUnitCodes),
		    PeopleManager.getInstance().indexedSearch(EnumPersonAttr.supannEntiteAffectationPrincipale, locOrgUnitCodes));
		final List<Person> locMergedList = ListUtils.<Person> mergeListOfList(locListOfList);
		return locMergedList;
	}

	/**
	 * This method does a dummy search over all the OrgUnit, and then finds the
	 * Persons associated to these OrgUnits
	 * 
	 * @param parRequest
	 *          a string to search for
	 * @return a List of persons, or an empty list
	 * @throws DataNotLoadedException
	 */
	private final List<Person> searchInGroups(@NotNull final String parRequest) throws DataNotLoadedException {
		final List<Group> locGroupList = GroupManager.getInstance().dummySearch(parRequest);
		assert (null != locGroupList);
		if (locGroupList.isEmpty()) {
			return Collections.<Person> emptyList();
		}
		// found some Groups
		final Collection<String> locUIDList = new TreeSet<String>();
		for (final Group locGroup : locGroupList) {
			// nasty... very...
			final String locUIDStr = locGroup.get(EnumGroupAttr.member);
			if (null == locUIDStr || locUIDStr.isEmpty()) {
				continue;
			}
			final String[] locUIDVect = locUIDStr.split(";");
			assert (locUIDVect.length > 0);
			locUIDList.addAll(Arrays.<String> asList(locUIDVect));
		}
		if (locUIDList.isEmpty()) {
			return Collections.<Person> emptyList();
		}
		// there are some non-empty unit codes
		final List<Person> locFinalList = PeopleManager.getInstance().indexedSearch(EnumPersonAttr.uid, locUIDList);
		return locFinalList;
	}

	private final List<Person> filterByOrgUnitOptions(@NotNull final List<Person> parInList, @NotNull final Map<String, Boolean> parOrgUnitOUFilter)
	    throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException, DataNotLoadedException {
		if (parInList.isEmpty()) {
			return parInList;
		}
		final ArrayList<Person> locResultList = new ArrayList<Person>(parInList.size());
		for (final Person locPerson : parInList) {
			final List<OrgUnit> locOrgUnitList = OrgUnitServiceImpl.getOrgUnitsForPerson(locPerson);
			for (final OrgUnit locOrgUnit : locOrgUnitList) {
				final String locOU = locOrgUnit.get(EnumOrgUnitAttr.ou);
				assert (null != locOU);
				final Boolean locIsCheck = parOrgUnitOUFilter.get(locOU);
				if (locIsCheck) {
					locResultList.add(locPerson);
					break;
				}
			}
		}
		return locResultList;
	}

	private final List<Person> filterByGroupOptions(@NotNull final List<Person> parInList, @NotNull final Map<String, Boolean> parGroupCNFilter)
	    throws IllegalArgumentException, ServicePropertiesIOException, UserNotLoggedException {
		if (parInList.isEmpty()) {
			return parInList;
		}
		final ArrayList<Person> locResultList = new ArrayList<Person>(parInList.size());
		for (final Person locPerson : parInList) {
			final String locUID = locPerson.get(EnumPersonAttr.uid);
			final List<Group> locGroupList = GroupServiceImpl.getGroupsForPersUID(locUID);
			for (final Group locGroup : locGroupList) {
				final String locCN = locGroup.get(EnumGroupAttr.cn);
				assert (null != locCN);
				final Boolean locIsCheck = parGroupCNFilter.get(locCN);
				if (locIsCheck) {
					locResultList.add(locPerson);
					break;
				}
			}
		}
		return locResultList;
	}

}
