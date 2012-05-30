/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 iogorode
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
 * Creation date: 30 mai 2012
 */
package fr.uparis10.miage.ldap.shared.obj;

import java.io.Serializable;
import java.util.Map;

/**
 * @author iogorode
 * 
 */
public class SearchRequestModel implements Serializable {

	/**
   * 
   */
	private static final long serialVersionUID = -4227615474633970308L;

	private String request;

	private Map<String, Boolean> groupOptions;

	private Map<String, Boolean> orgUnitOptions;

	private Boolean lookUpPerson;

	private Boolean lookUpOrgUnit;

	private Boolean lookUpGroup;

	/**
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request
	 *          the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * @return the groupOptions
	 */
	public Map<String, Boolean> getGroupOptions() {
		return groupOptions;
	}

	/**
	 * @param groupOptions
	 *          the groupOptions to set
	 */
	public void setGroupOptions(Map<String, Boolean> groupOptions) {
		this.groupOptions = groupOptions;
	}

	/**
	 * @return the orgUnitOptions
	 */
	public Map<String, Boolean> getOrgUnitOptions() {
		return orgUnitOptions;
	}

	/**
	 * @param orgUnitOptions
	 *          the orgUnitOptions to set
	 */
	public void setOrgUnitOptions(Map<String, Boolean> orgUnitOptions) {
		this.orgUnitOptions = orgUnitOptions;
	}

	/**
	 * @return the lookUpPerson
	 */
	public Boolean getLookUpPerson() {
		return lookUpPerson;
	}

	/**
	 * @param lookUpPerson
	 *          the lookUpPerson to set
	 */
	public void setLookUpPerson(Boolean lookUpPerson) {
		this.lookUpPerson = lookUpPerson;
	}

	/**
	 * @return the lookUpOrgUnit
	 */
	public Boolean getLookUpOrgUnit() {
		return lookUpOrgUnit;
	}

	/**
	 * @param lookUpOrgUnit
	 *          the lookUpOrgUnit to set
	 */
	public void setLookUpOrgUnit(Boolean lookUpOrgUnit) {
		this.lookUpOrgUnit = lookUpOrgUnit;
	}

	/**
	 * @return the lookUpGroup
	 */
	public Boolean getLookUpGroup() {
		return lookUpGroup;
	}

	/**
	 * @param lookUpGroup
	 *          the lookUpGroup to set
	 */
	public void setLookUpGroup(Boolean lookUpGroup) {
		this.lookUpGroup = lookUpGroup;
	}

}
