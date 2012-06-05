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

import javax.servlet.http.HttpSession;

import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;

/**
 * @author OMAR
 * 
 */
public class UserLoginChecker {
	private static UserLoginChecker theInst;

	public final static UserLoginChecker getInstance() {
		if (null == theInst) {
			theInst = new UserLoginChecker();
		}

		return theInst;
	}

	public final void check(HttpSession session) throws UserNotLoggedException, ServicePropertiesIOException {
		// HttpSession session = this.getThreadLocalRequest().getSession();
		if (session.getAttribute("CurrentLoggedPerson") == null) {
			throw new UserNotLoggedException();
		}
		int sessionExpirationTime = ServicesPropertiesManager.getInstance().getSessionExpirationTime();
		session.setMaxInactiveInterval(sessionExpirationTime);
	}
}
