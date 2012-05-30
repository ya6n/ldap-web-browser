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
 * Creation date: May 28, 2012
 */
package fr.uparis10.miage.ldap.server.utils;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.shared.itf.IDecoder;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class ServerStringUtils {
	/**
	 * No Instance!!
	 */
	private ServerStringUtils() {
	}

	public final static <I_TYPE> String decodeAttribute(@NotNull final Attribute parAttr, @NotNull IDecoder<Object, String> parDecoder) throws NamingException {
		assert (parAttr.size() > 0);
		if (parAttr.size() == 1) {
			return parDecoder.decodeValue(parAttr.get(0));
		}
		final StringBuilder locAttrVal = new StringBuilder();
		locAttrVal.append(parDecoder.decodeValue(parAttr.get(0)));
		for (int locI = 1; locI < parAttr.size(); ++locI) {
			locAttrVal.append(';').append(parDecoder.decodeValue(parAttr.get(locI)));
		}
		return locAttrVal.toString();
	}
}
