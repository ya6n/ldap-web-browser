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
 * Creation date: May 26, 2012
 */
package fr.uparis10.miage.ldap.server.mng;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.obj.Group;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class GroupManager extends ACacheManager<EnumGroupAttr, String, Group> {

	private static final String _prefix = "ou=groups,";
	private static final String _filter = "objectClass=organisationalUnit";

	private static GroupManager _inst = null;

	private GroupManager() {
		super();
	}

	public final static GroupManager getInstance() {
		if (null == _inst) {
			_inst = new GroupManager();
		}

		return _inst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.AEnumCacheManager#getDNPrefix()
	 */
	@Override
	protected final String getDNPrefix() {
		return _prefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.AEnumCacheManager#getGenericFilter()
	 */
	@Override
	protected final String getGenericFilter() {
		return _filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.ACacheManager#createNewIndex()
	 */
	@Override
	protected final Map<EnumGroupAttr, Map<String, List<Group>>> createNewIndex() {
		return new EnumMap<EnumGroupAttr, Map<String, List<Group>>>(EnumGroupAttr.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.ACacheManager#createNewObject()
	 */
	@Override
	protected final Group createNewObject() {
		return new Group();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.ACacheManager#valueOfIndex(java.lang.String)
	 */
	@Override
	protected final EnumGroupAttr valueOfIndex(final String parName) {
		return EnumGroupAttr.valueOf(parName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.ACacheManager#valueOfKey(java.lang.String)
	 */
	@Override
	protected final String valueOfKey(final String parName) {
		return parName;
	}
}
