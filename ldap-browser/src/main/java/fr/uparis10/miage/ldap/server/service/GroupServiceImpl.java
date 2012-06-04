/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 gorodenco
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
 * Creation date: 28 mai 2012
 */
package fr.uparis10.miage.ldap.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.uparis10.miage.ldap.client.service.GroupService;
import fr.uparis10.miage.ldap.server.mng.GroupManager;
import fr.uparis10.miage.ldap.shared.exc.ServicePropertiesIOException;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Group;

/**
 * @author gorodenco
 * 
 */
@SuppressWarnings("serial")
public class GroupServiceImpl extends RemoteServiceServlet implements GroupService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.client.service.GroupService#getGroupsAll()
	 */
	@Override
	public List<Group> getGroupsAll() throws IllegalArgumentException, UserNotLoggedException, ServicePropertiesIOException {
		UserLoginChecker.getInstance().check();
		List<Group> listGroup = GroupManager.getInstance().getAllObjList();
		ArrayList<Group> result = new ArrayList<Group>();
		if (listGroup != null) {
			result.addAll(listGroup);
		}
		return result;
	}
}
