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

import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.shared.itf.IDecoder;
import fr.uparis10.miage.ldap.shared.itf.IIndexable;

/**
 * Note : No need to index the attributes of this class - there will be only 1
 * object in the whole LDAP tree.
 * 
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumOrganizationAttr implements IIndexable, IDecoder<Object, String> {
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
	teletexTerminalIdentifier,
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
	cn(true),
	eduOrgHomePageURI,
	eduOrgIdentityAuthNPolicyURI,
	eduOrgLegalName,
	eduOrgSuperiorURI,
	eduOrgWhitePagesURI;

	private final Class<?> dataType;
	private final boolean isIndexed;

	private EnumOrganizationAttr() {
		this(String.class, false);
	}

	private EnumOrganizationAttr(final Class<?> parType) {
		this(parType, false);
	}

	private EnumOrganizationAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumOrganizationAttr(final Class<?> parType, final boolean parDoIndex) {
		dataType = parType;
		isIndexed = parDoIndex;
	}

	public final Class<?> getTypeClass() {
		return dataType;
	}

	@Override
	public final boolean isIndexed() {
		return isIndexed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.itf.IDecoder#decodeValue(java.lang.Object)
	 */
	@Override
	public final String decodeValue(@NotNull final Object parInput) {
		return parInput.toString();
	}
}
