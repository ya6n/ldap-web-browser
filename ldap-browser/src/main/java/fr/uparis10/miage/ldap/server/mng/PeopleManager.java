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

import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class PeopleManager extends ACacheManager<EnumPersonAttr, String, Person> {

	private static final String _prefix = "ou=people,";
	private static final String _filter = "objectClass=person";

	private static PeopleManager _inst = null;

	private PeopleManager() {
		super();
	}

	public final static PeopleManager getInstance() {
		if (null == _inst) {
			_inst = new PeopleManager();
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
	protected final Map<EnumPersonAttr, Map<String, List<Person>>> createNewIndex() {
		return new EnumMap<EnumPersonAttr, Map<String, List<Person>>>(EnumPersonAttr.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.ACacheManager#createNewObject()
	 */
	@Override
	protected final Person createNewObject() {
		return new Person();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.ACacheManager#valueOfIndex(java.lang.String)
	 */
	@Override
	protected final EnumPersonAttr valueOfIndex(final String parName) {
		return EnumPersonAttr.valueOf(parName);
	}
}
