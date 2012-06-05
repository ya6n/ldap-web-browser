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

import static org.junit.Assert.*;

import org.junit.Test;

import fr.uparis10.miage.ldap.server.service.ServicesPropertiesManager;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class ServicesPropertiesManagerTest {

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.ServicesPropertiesManager#getSessionExpirationTime()}
	 * .
	 * 
	 * @throws ServicePropertiesIOException
	 */
	@Test(timeout = 10000L)
	public final void testGetSessionExpirationTime() throws ServicePropertiesIOException {
		// on sait que c'est 1800 sec par defaut
		assertEquals(1800, ServicesPropertiesManager.getInstance().getSessionExpirationTime());
	}
}
