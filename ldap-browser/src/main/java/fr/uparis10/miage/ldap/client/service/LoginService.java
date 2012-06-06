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
package fr.uparis10.miage.ldap.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author iogorode
 * 
 */

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

	Person loginUser(String login, String pass) throws IllegalArgumentException, ServicePropertiesIOException;

	Boolean logoutUser() throws IllegalArgumentException;

	Person isUserLoggedIn() throws IllegalArgumentException;

}
