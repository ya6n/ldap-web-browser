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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author OMAR
 * 
 */
public class LoginServiceImplTest extends RemoteServiceServlet {
	/**
   * 
   */
	private static final long serialVersionUID = -6472974790838771710L;

	private LoginServiceImpl _loginServiceImpl;

	@Before
	public final void beforeTest() throws ServicePropertiesIOException {
		TestingServicesPropertiesManager.initTestInstance();
		_loginServiceImpl = new LoginServiceImpl();
	}

	@After
	public final void afterTest() {
		_loginServiceImpl = null;
	}

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.LoginServiceImpl#loginUser(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testLoginUser() throws IllegalArgumentException, ServicePropertiesIOException {
		assertNotNull(_loginServiceImpl.loginUser("admin", "miage"));
		assertNotNull(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson"));
		assertTrue(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson") instanceof Person);
	}

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.LoginServiceImpl#loginUser(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testBadLoginUser() throws IllegalArgumentException, ServicePropertiesIOException {
		assertNull(_loginServiceImpl.loginUser("bad-login", "bad-password"));
		assertNull(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson"));
	}

	/**
	 * Test method for
	 * {@link fr.uparis10.miage.ldap.server.service.LoginServiceImpl#logoutUser()}
	 * .
	 * 
	 * @throws ServicePropertiesIOException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testLogoutUser() throws IllegalArgumentException, ServicePropertiesIOException {
		_loginServiceImpl.loginUser("admin", "miage");
		assertTrue(_loginServiceImpl.logoutUser());
		assertNull(_loginServiceImpl.getSession().getAttribute("CurrentLoggedPerson"));
	}

}
