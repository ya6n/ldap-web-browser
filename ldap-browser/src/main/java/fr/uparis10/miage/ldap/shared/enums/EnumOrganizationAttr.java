/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 Gicu GORODENCO <cyclopsihus@gmail.com>
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
 * Creation date: May 26, 2012
 */
package fr.uparis10.miage.ldap.shared.enums;

import fr.uparis10.miage.ldap.server.itf.IIndexable;

/**
 * Note : No need to index the attributes of this class - there will be only 1 object in the whole LDAP tree.
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumOrganizationAttr implements IIndexable {
	// Generic
	objectClass,

	// Inherited from "organization" class:
	o,
	userPassword,
	searchGuide,
	seeAlso,
	businessCategory,
	x121Address,
	registeredAddress,
	destinationIndicator,
	preferredDeliveryMethod,
	telexNumber,
	teletexTerminalIdentifier ,
	telephoneNumber,
	internationaliSDNNumber,
	facsimileTelephoneNumber,
	street,
	postOfficeBox,
	postalCode,
	postalAddress,
	physicalDeliveryOfficeName,
	st,
	l,
	description,
	
	// Inherited from "supannOrg" class:
	supannEtablissement,
	
	// Inherited from "supannEntite" class:
	supannCodeEntite(true),
	supannTypeEntite(true),
	supannCodeEntiteParent(true),
	supannRefId(true),
	
	// Inherited from "eduOrg" class:
	cn,
	eduOrgHomePageURI,
	eduOrgIdentityAuthNPolicyURI,
	eduOrgLegalName,
	eduOrgSuperiorURI,
	eduOrgWhitePagesURI;
	
	@SuppressWarnings("rawtypes")
	private final Class _type;
	private final boolean _isIndexed;

	private EnumOrganizationAttr() {
		this(String.class, false);
	}

	private EnumOrganizationAttr(@SuppressWarnings("rawtypes") final Class parType) {
		this(parType, false);
	}

	private EnumOrganizationAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumOrganizationAttr(@SuppressWarnings("rawtypes") final Class parType, final boolean parDoIndex) {
		_type = parType;
		_isIndexed = parDoIndex;
	}

	@SuppressWarnings("rawtypes")
	public final Class getTypeClass() {
		return _type;
	}

	@Override
	public final boolean isIndexed() {
		return _isIndexed;
	}

}
