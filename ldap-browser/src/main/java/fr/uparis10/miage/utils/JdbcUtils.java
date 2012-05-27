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
package fr.uparis10.miage.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 */
public final class JdbcUtils {
	public final static void closeIFP(final Connection parConn) {
		if (parConn == null) {
			return;
		}
		try {
	    if (parConn.isClosed()) {
	    	return;
	    }
	    parConn.close();
    } catch (final SQLException locExc) {
    	Logger.getLogger(JdbcUtils.class.getName()).log(Level.WARNING, "Can't close the LDAP JDBC connection!", locExc);
    }
	}

}
