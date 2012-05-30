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
package fr.uparis10.miage.ldap.shared.utils;

import javax.validation.constraints.NotNull;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public class StringUtils {
	
	/**
	 * No Instance!!
	 */
	private StringUtils() {
	}

	public final static String decodeByteArray(@NotNull final byte[] parArray) {
		final StringBuilder locStrBld = new StringBuilder(parArray.length);
		for (final byte locByte : parArray) {
			locStrBld.append((char) locByte);
		}

		return locStrBld.toString();
	}

}
