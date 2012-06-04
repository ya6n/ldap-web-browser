/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 iogorode
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
 * Creation date: 30 mai 2012
 */
package fr.uparis10.miage.ldap.server.service;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.client.service.LoginService;
import fr.uparis10.miage.ldap.server.mng.UserLoginManager;
import fr.uparis10.miage.ldap.shared.exc.InvalidPasswordException;
import fr.uparis10.miage.ldap.shared.exc.NoSuchUserException;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author OMAR
 * 
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.client.service.LoginService#loginUser(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Boolean loginUser(String login, String pass) throws IllegalArgumentException, ServicePropertiesIOException {
		int sessionExpirationTime = ServicesPropertiesManager.getInstance().getSessionExpirationTime();
		UserLoginManager userLoginManager = UserLoginManager.getInstance();
		try {
			Person person = userLoginManager.login(login, pass);
			HttpSession session = this.getThreadLocalRequest().getSession();
			session.setAttribute("CurrentLoggedPerson", person);
			session.setMaxInactiveInterval(sessionExpirationTime);
		} catch (NoSuchUserException e) {
			return false;
		} catch (InvalidPasswordException e) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.client.service.LoginService#logoutUser()
	 */
	@Override
	public Boolean logoutUser() throws IllegalArgumentException {
		HttpSession session = this.getThreadLocalRequest().getSession();
		if (session.getAttribute("CurrentLoggedPerson") != null) {
			session.setAttribute("CurrentLoggedPerson", null);
		}
		return true;
	}
}
