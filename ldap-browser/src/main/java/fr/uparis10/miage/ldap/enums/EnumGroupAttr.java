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
package fr.uparis10.miage.ldap.enums;

import fr.uparis10.miage.ldap.itf.IIndexable;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public enum EnumGroupAttr implements IIndexable {
	// Generic
	objectClass,
	
	// Everything comes from "organizationalUnit" class:
	ou(true),
	userPassword,
	searchGuide(true),
	seeAlso(true),
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
	description(true);
	
	@SuppressWarnings("rawtypes")
	private final Class _type;
	private final boolean _isIndexed;

	private EnumGroupAttr() {
		this(String.class, false);
	}

	private EnumGroupAttr(@SuppressWarnings("rawtypes") final Class parType) {
		this(parType, false);
	}

	private EnumGroupAttr(final boolean parDoIndex) {
		this(String.class, parDoIndex);
	}

	private EnumGroupAttr(@SuppressWarnings("rawtypes") final Class parType, final boolean parDoIndex) {
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
