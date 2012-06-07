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
package fr.uparis10.miage.ldap.client.messages;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;

/**
 * @author gorodenco
 * 
 */
@DefaultLocale("fr")
public interface ApplicationMessages extends Messages {

	@Key("header.menu.peopleSearch")
	String getMenuPeopleSearch();

	@Key("header.logout")
	String getHeaderLogout();

	@Key("properties.person")
	String getPropertiesPerson();

	@Key("properties.orgpers")
	String getPropertiesOrgPers();

	@Key("properties.edupers")
	String getPropertiesEduPers();

	@Key("properties.inetorg")
	String getPropertiesInetOrg();

	@Key("properties.supann")
	String getPropertiesSupann();

	@Key("properties.all")
	String getPropertiesAll();

	@Key("screen.welcome")
	String getWelcomeScreen();

}
