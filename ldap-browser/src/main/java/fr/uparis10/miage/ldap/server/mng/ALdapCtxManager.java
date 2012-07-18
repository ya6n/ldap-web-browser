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
 * Creation date: May 30, 2012
 */
package fr.uparis10.miage.ldap.server.mng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public abstract class ALdapCtxManager {
	private final Properties props;
	private final String baseDN;
	private final DirContext ctx;

	/**
	 * Singleton
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws NamingException
	 */
	protected ALdapCtxManager() {
		try {
			props = new Properties();
			props.load(new FileInputStream(new File(getConfigFile())));
			baseDN = props.getProperty("basedn");

			final String locHost = props.getProperty("host");
			final String locPort = props.getProperty("port");
			final String locUser = props.getProperty("user");
			final String locPassword = props.getProperty("password");
			// --------------------------------------------------
			// Set up the environment for creating the initial context
			// --------------------------------------------------
			Properties locEnvProps = new Properties();
			locEnvProps.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

			final String locUrl = "ldap://" + locHost + ((locPort == null || locPort.isEmpty()) ? "" : (":" + locPort));
			locEnvProps.setProperty(Context.PROVIDER_URL, locUrl);
			locEnvProps.setProperty(Context.URL_PKG_PREFIXES, "com.sun.jndi.url");

			locEnvProps.setProperty(Context.REFERRAL, "ignore");
			locEnvProps.setProperty(Context.SECURITY_AUTHENTICATION, "simple");

			locEnvProps.setProperty(Context.SECURITY_PRINCIPAL, locUser);
			locEnvProps.setProperty(Context.SECURITY_CREDENTIALS, locPassword);

			ctx = new InitialDirContext(locEnvProps);
		} catch (final FileNotFoundException locExc) {
			Logger.getLogger(getClass().getName()).severe("Looking in : " + System.getProperty("user.dir"));
			throw new RuntimeException(locExc);
		} catch (final IOException locExc) {
			throw new RuntimeException(locExc);
		} catch (final NamingException locExc) {
			throw new RuntimeException(locExc);
		}
	}

	public final DirContext getContext() {
		return ctx;
	}

	public final String getBaseDN() {
		return baseDN;
	}

	public abstract String getConfigFile();
}
