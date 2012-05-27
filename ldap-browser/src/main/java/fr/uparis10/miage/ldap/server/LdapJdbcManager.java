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
package fr.uparis10.miage.ldap.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 */
public final class LdapJdbcManager {
	
	private static LdapJdbcManager _inst = null;
	
	private final String _connUrl;
	private final String _user;
	private final String _password;
	/**
	 * Singleton
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private LdapJdbcManager() throws FileNotFoundException, IOException {
		final Properties locProps = new Properties();
		locProps.load(new FileInputStream(new File("ldap_jdbc.properties")));
		final String locHost = locProps.getProperty("host");
		final String locPort = locProps.getProperty("port");
		final String locBaseDn = locProps.getProperty("basedn");
		final String locAuxProps = locProps.getProperty("properties");
		
		final StringBuilder locBld = new StringBuilder();
		locBld.append("jdbc:ldap://").append(locHost);
		if (null != locPort && ! locPort.isEmpty()) {
			locBld.append(':').append(locPort);
		}
		locBld.append('/').append(locBaseDn);
		if (locAuxProps != null && !locAuxProps.isEmpty()) {
			locBld.append('?').append(locAuxProps);
		}
		
		_connUrl = locBld.toString();
		_user = locProps.getProperty("user");
		_password = locProps.getProperty("password");
	}
	
	public final static LdapJdbcManager getInstance() throws FileNotFoundException, IOException {
		if (null == _inst) {
			_inst = new LdapJdbcManager();
		}
		
		return _inst;
	}
	
	public final Connection getConnection() throws SQLException {
		return DriverManager.getConnection(_connUrl, _user, _password);
	}
}
