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

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.uparis10.miage.ldap.shared.exc.InvalidPasswordException;
import fr.uparis10.miage.ldap.shared.exc.NoSuchUserException;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public class UserLoginManagerTest {

	private UserLoginManager _mng;

	@Before
	public final void beforeTest() {
		_mng = UserLoginManager.getInstance();
	}

	@After
	public final void afterTest() {
		_mng = null;
	}

	@Test(timeout = 10000L)
	public final void testLoginOK() {
		try {
			@SuppressWarnings("unused")
			final Person locPerson = _mng.login("admin", "miage");
		} catch (final NoSuchUserException locExc) {
			fail(locExc.getLocalizedMessage());
		} catch (final InvalidPasswordException locExc) {
			fail(locExc.getLocalizedMessage());
		}
	}

	@Test(timeout = 10000L)
	public final void testNoSuchLogin() {
		try {
			@SuppressWarnings("unused")
			final Person locPerson = _mng.login("adminTRTRTR", "trtrtr");
			fail("We had to get an NoSuchUserException here!!!");
		} catch (final NoSuchUserException locExc) {
			// ignore
		} catch (final InvalidPasswordException locExc) {
			fail(locExc.getLocalizedMessage());
		}
	}

	@Test(timeout = 10000L)
	public final void testInvalidPassword() {
		try {
			@SuppressWarnings("unused")
			final Person locPerson = _mng.login("admin", "trtrtr-bla-bla-bla");
			fail("We had to get an InvalidPasswordException here!!!");
		} catch (final NoSuchUserException locExc) {
			fail(locExc.getLocalizedMessage());
		} catch (final InvalidPasswordException locExc) {
			// ignore
		}
	}
}
