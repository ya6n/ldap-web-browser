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
 * Creation date: 27 mai 2012
 */
package fr.uparis10.miage.ldap.client.resources.messages;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;

/**
 * @author gorodenco
 * 
 */
@DefaultLocale("fr")
public interface PeopleSearchScreenMessages extends Messages {

	@Key("screen.peopleSearch.title")
	String getTitle();

	@Key("screen.peopleSearch.text.label")
	String getTextFieldLabel();

	@Key("screen.peopleSearch.button.label")
	String getSearchButtonLabel();

	@Key("screen.peopleSearch.advanced.title")
	String getAdvancedBoxTitle();

	@Key("screen.peopleSearch.advanced.group.label")
	String getGroupListLabel();

	@Key("screen.peopleSearch.advanced.orgUnits.label")
	String getOrgUnitListLabel();

	@Key("screen.peopleSearch.advanced.lookUpOptions.title")
	String getLookUpOptionsTitle();

	@Key("screen.peopleSearch.advanced.lookUpOptions.people.label")
	String getLookUpPeopleLabel();

	@Key("screen.peopleSearch.advanced.lookUpOptions.orgUnit.label")
	String getLookUpOrgUnitLabel();

	@Key("screen.peopleSearch.advanced.lookUpOptions.group.label")
	String getLookUpGroupLabel();
}
