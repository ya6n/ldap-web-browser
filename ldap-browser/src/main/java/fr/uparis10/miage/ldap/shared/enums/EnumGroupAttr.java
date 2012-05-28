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
public enum EnumGroupAttr implements IIndexable, IDecoder<Object, String> {
	// Generic
	objectClass,

	// Inherited from "groupOfNames" class:
	member,
	cn(true),
	businessCategory(true),
	seeAlso,
	owner(true),
	ou(true),
	o(true),
	description,

	// Inherited from "supannGroupe" class:
	supannGroupeDateFin,
	supannGroupeAdminDN(true),
	supannGroupeLecteurDN(true),
	supannRefId(true);

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
