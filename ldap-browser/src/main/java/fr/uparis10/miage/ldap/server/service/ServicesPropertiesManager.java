/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 OMAR
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
 * Creation date: 4 juin 2012
 */
package fr.uparis10.miage.ldap.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;

/**
 * @author OMAR
 * 
 */
public class ServicesPropertiesManager {
	private static ServicesPropertiesManager theInst;

	private Properties configProp;
	private final String SERVICES_PROPERTIES_FILE_PATH = "/ldap-browser/src/main/resources/fr/uparis10/miage/ldap/server/service/services.properties";

	public final static ServicesPropertiesManager getInstance() {
		if (null == theInst) {
			theInst = new ServicesPropertiesManager();
		}

		return theInst;
	}

	/**
	 * @throws ServicePropertiesIOException
	 */
	private void loadProps() throws ServicePropertiesIOException {
		configProp = new Properties();
		InputStream in = this.getClass().getResourceAsStream(SERVICES_PROPERTIES_FILE_PATH);
		try {
			configProp.load(in);
		} catch (IOException e) {
			throw new ServicePropertiesIOException(e);
		}
	}
	
	public int getSessionExpirationTime() throws ServicePropertiesIOException{
		loadProps();
		int sessionExpirationTime = Integer.parseInt(configProp.getProperty("sessionExpirationTime"));
		return sessionExpirationTime;
	}

}
