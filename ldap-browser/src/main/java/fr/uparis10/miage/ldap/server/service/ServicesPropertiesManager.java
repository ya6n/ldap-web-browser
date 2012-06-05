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

import fr.uparis10.miage.ldap.server.utils.PropertyParseUtil;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;

/**
 * @author OMAR
 * 
 */
public class ServicesPropertiesManager {
	private final static String SERVICES_PROPERTIES_FILE_PATH = "/fr/uparis10/miage/ldap/server/service/services.properties";
	private final static String SESSION_EXP_TIME = "serverSessionExpirationTime";

	private static ServicesPropertiesManager theInst;

	private final int sessionExpirationTime;

	public final static ServicesPropertiesManager getInstance() throws ServicePropertiesIOException {
		if (null == theInst) {
			theInst = new ServicesPropertiesManager();
		}

		return theInst;
	}

	private ServicesPropertiesManager() throws ServicePropertiesIOException {
		final Properties locProps = loadProps();
		// trying to get the session expiration timeout; otherwise - no expiration
		sessionExpirationTime = PropertyParseUtil.getIntOrDefault(locProps, SESSION_EXP_TIME, Integer.MAX_VALUE);
	}

	/**
	 * @throws ServicePropertiesIOException
	 */
	private Properties loadProps() throws ServicePropertiesIOException {
		final Properties configProp = new Properties();
		InputStream in = this.getClass().getResourceAsStream(SERVICES_PROPERTIES_FILE_PATH);
		assert (in != null) : "Resource :" + SERVICES_PROPERTIES_FILE_PATH + " is NOT NOT NOT NOT here!!!";
		try {
			configProp.load(in);
		} catch (IOException e) {
			throw new ServicePropertiesIOException(e);
		}
		return configProp;
	}

	public int getSessionExpirationTime() {
		return sessionExpirationTime;
	}
}
