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
package fr.uparis10.miage.ldap.client.enums;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;

/**
 * @author auprotois
 * 
 **/

@DefaultLocale("en")
public interface EnumOrgUnitAttrMessages extends Messages {

	@Key("objectClass")
	String getObjectClass();

	@Key("ou")
	String getOu();

	@Key("userPassword")
	String getUserPassword();

	@Key("searchGuide")
	String getSearchGuide();

	@Key("seeAlso")
	String getSeeAlso();

	@Key("businessCategory")
	String getBusinessCategory();

	@Key("x121Address")
	String getX121Address();

	@Key("registeredAddress")
	String getRegisteredAddress();

	@Key("destinationIndicator")
	String getDestinationIndicator();

	@Key("preferredDeliveryMethod")
	String getPreferredDeliveryMethod();

	@Key("telexNumber")
	String getTelexNumber();

	@Key("teletexTerminalIdentifier")
	String getTeletexTerminalIdentifier();

	@Key("telephoneNumber")
	String getTelephoneNumber();

	@Key("internationaliSDNNumber")
	String getInternationaliSDNNumber();

	@Key("facsimileTelephoneNumber")
	String getFacsimileTelephoneNumber();

	@Key("street")
	String getStreet();

	@Key("postOfficeBox")
	String getPostOfficeBox();

	@Key("postalCode")
	String getPostalCode();

	@Key("postalAddress")
	String getPostalAddress();

	@Key("physicalDeliveryOfficeName")
	String getPhysicalDeliveryOfficeName();

	@Key("st")
	String getSt();

	@Key("l")
	String get1();

	@Key("description")
	String getDescription();

	@Key("supannCodeEntite")
	String getSupannCodeEntite();

	@Key("supannTypeEntite")
	String getSupannTypeEntite();

	@Key("supannCodeEntiteParent")
	String getSupannCodeEntiteParent();

	@Key("supannRefId")
	String getSupannRefId();

}