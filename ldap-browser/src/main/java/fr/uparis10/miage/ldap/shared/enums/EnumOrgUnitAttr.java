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

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.server.itf.IDecoder;
import fr.uparis10.miage.ldap.server.itf.IIndexable;
import fr.uparis10.miage.ldap.server.utils.StringUtils;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumOrgUnitAttr implements IIndexable<String>, IDecoder<Object, String> {
	// Generic
	objectClass,

	// Inherited from "organizationalUnit" class:
	ou(true),
	userPassword,
	searchGuide(true),
	seeAlso,
	businessCategory(true),
	x121Address(true),
	registeredAddress(true),
	destinationIndicator(true),
	preferredDeliveryMethod(true),
	telexNumber(true),
	teletexTerminalIdentifier(true),
	telephoneNumber(true),
	internationaliSDNNumber(true),
	facsimileTelephoneNumber(true),
	street(true),
	postOfficeBox(true),
	postalCode(true),
	postalAddress(true),
	physicalDeliveryOfficeName(true),
	st,
	l,
	description(true),

	// Inherited from "supannEntite" class:
	supannCodeEntite(true),
	supannTypeEntite(true),
	supannCodeEntiteParent(true),
	supannRefId(true);

	@SuppressWarnings("rawtypes")
	private final Class _type;
	private final boolean _isIndexed;

	private EnumOrgUnitAttr() {
		this(String.class, false);
	}

	private EnumOrgUnitAttr(@SuppressWarnings("rawtypes") final Class parType) {
		this(parType, false);
	}

	private EnumOrgUnitAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumOrgUnitAttr(@SuppressWarnings("rawtypes") final Class parType, final boolean parDoIndex) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.itf.IIndexable#decodeAttribute(javax.naming
	 * .directory.Attribute)
	 */
	@Override
	public final String decodeAttribute(@NotNull final Attribute parInput) throws NamingException {
		return StringUtils.decodeAttribute(parInput, this);
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
