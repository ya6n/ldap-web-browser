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

import java.util.Properties;

import org.junit.Test;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 */
public final class PropertyParseUtilTest {

	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.utils.PropertyParseUtil#getIntOrDefault(java.util.Properties, java.lang.String, int)}.
	 */
	@Test (timeout=5000L)
	public final void testGetIntOrDefaultOK() {
		final Properties locProps = new Properties();
		final String locKey = "theKey";
		final String locValue = "1234";
		locProps.put(locKey, locValue);
		final int locIntValue = PropertyParseUtil.getIntOrDefault(locProps, locKey, Integer.MIN_VALUE);
		assertEquals(1234, locIntValue);
	}

	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.utils.PropertyParseUtil#getIntOrDefault(java.util.Properties, java.lang.String, int)}.
	 */
	@Test (timeout=5000L)
	public final void testGetIntOrDefaultKO1() {
		final Properties locProps = new Properties();
		final String locKey = "theKey";
		final int locIntValue = PropertyParseUtil.getIntOrDefault(locProps, locKey, Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, locIntValue);
	}

	/**
	 * Test method for {@link fr.uparis10.miage.ldap.server.utils.PropertyParseUtil#getIntOrDefault(java.util.Properties, java.lang.String, int)}.
	 */
	@Test (timeout=5000L)
	public final void testGetIntOrDefaultKO2() {
		final Properties locProps = new Properties();
		final String locKey = "theKey";
		final String locValue = "INVALID_INT_VALUE";
		locProps.put(locKey, locValue);
		final int locIntValue = PropertyParseUtil.getIntOrDefault(locProps, locKey, Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, locIntValue);
	}
}
