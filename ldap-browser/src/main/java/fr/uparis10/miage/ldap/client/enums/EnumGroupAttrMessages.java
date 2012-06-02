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

@DefaultLocale("fr")
public interface EnumGroupAttrMessages extends Messages {

	@Key("member")
	String getMember();

	@Key("cn")
	String getCn();

	@Key("businessCategory")
	String getBusinessCategory();

	@Key("seeAlso")
	String getSeeAlso();

	@Key("owner")
	String getOwner();

	@Key("ou")
	String getOu();

	@Key("o")
	String getO();

	@Key("description")
	String getDescription();

	@Key("supannGroupeDateFin")
	String getSupannGroupeDateFin();

	@Key("supannGroupeAdminDN")
	String getSupannGroupeAdminDN();

	@Key("supannGroupeLecteurDN")
	String getSupannGroupeLecteurDN();

	@Key("supannRefId")
	String getSupannRefId();

}