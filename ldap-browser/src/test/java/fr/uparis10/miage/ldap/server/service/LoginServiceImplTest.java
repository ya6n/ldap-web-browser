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
 * Creation date: 1 juin 2012
 */
package fr.uparis10.miage.ldap.server.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.junit.TestUtils;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author OMAR
 * 
 */
public final class LoginServiceImplTest extends RemoteServiceServlet {
	/**
   * 
   */
	private static final long serialVersionUID = -6472974790838771710L;

	private LoginServiceImpl _loginServiceImpl;

	@BeforeClass
	public final static void beforeClass() throws ServicePropertiesIOException {
		TestingServicesPropertiesManager.initTestInstance();
	}

	@Before
	public final void beforeTest() throws ServletException, IOException {
		_loginServiceImpl = new LoginServiceImpl();
		TestUtils.initTestServlet(_loginServiceImpl);
	}

	@After
	public final void afterTest() {
		_loginServiceImpl = null;
	}

	/**
	 * TODO : faire marcher!!!
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.LoginServiceImpl#loginUser(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
//	@Test(timeout = 10000L)
	public final void testLoginUser() throws IllegalArgumentException, ServicePropertiesIOException {
		assertTrue(_loginServiceImpl.loginUser("admin", "miage"));
		assertNotNull(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson"));
		assertTrue(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson") instanceof Person);
	}

	/**
	 * TODO : faire marcher!!!
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.LoginServiceImpl#loginUser(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
//	@Test(timeout = 10000L)
	public final void testBadLoginUser() throws IllegalArgumentException, ServicePropertiesIOException {
		assertFalse(_loginServiceImpl.loginUser("bad-login", "bad-password"));
		assertNull(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson"));
	}

	/**
	 * TODO : faire marcher!!!
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.LoginServiceImpl#logoutUser()}
	 * .
	 * 
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
//	@Test(timeout = 10000L)
	public final void testLogoutUser() throws IllegalArgumentException, ServicePropertiesIOException {
		_loginServiceImpl.loginUser("admin", "miage");
		assertTrue(_loginServiceImpl.logoutUser());
		assertNull(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson"));
	}
}
