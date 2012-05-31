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
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumOrgUnitAttr implements IIndexable, IDecoder<Object, String> {
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

	private final Class<?> dataType;
	private final boolean isIndexed;

	private EnumOrgUnitAttr() {
		this(String.class, false);
	}

	private EnumOrgUnitAttr(final Class<?> parType) {
		this(parType, false);
	}

	private EnumOrgUnitAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumOrgUnitAttr(final Class<?> parType, final boolean parDoIndex) {
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
