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
 * Creation date: Jun 5, 2012
 */
package fr.uparis10.miage.ldap.server.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class PropertyParseUtil {

	private PropertyParseUtil() {
	}
	
	public final static int getIntOrDefault(@NotNull final Properties parProps, @NotNull final String parKey, final int parDefaultValue) {
		final String locExpTimeStr = parProps.getProperty(parKey);
		if (null == locExpTimeStr) {
			Logger.getLogger(PropertyParseUtil.class.getName()).severe("Property " + parKey + " NOT present in file ");
			return parDefaultValue;
		}
		try {
			final int locResult = Integer.parseInt(locExpTimeStr);
			return locResult;
		} catch (final NumberFormatException locExc) {
			Logger.getLogger(PropertyParseUtil.class.getName()).log(Level.SEVERE, "Invalid property value " + parKey + " in file ", locExc);
			return parDefaultValue;
		}
	}
}
