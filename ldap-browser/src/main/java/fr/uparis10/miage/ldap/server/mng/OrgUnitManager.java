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

import fr.uparis10.miage.ldap.shared.enums.EnumOrgUnitAttr;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class OrgUnitManager extends ACacheManager<EnumOrgUnitAttr, String, OrgUnit> {

	private static final String _prefix = "ou=structures,";
	private static final String _filter = "objectClass=organizationalUnit";

	private static OrgUnitManager _inst = null;

	private OrgUnitManager() {
		super();
	}

	public final static OrgUnitManager getInstance() {
		if (null == _inst) {
			_inst = new OrgUnitManager();
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
	protected final Map<EnumOrgUnitAttr, Map<String, List<OrgUnit>>> createNewIndex() {
		return new EnumMap<EnumOrgUnitAttr, Map<String, List<OrgUnit>>>(EnumOrgUnitAttr.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.ACacheManager#createNewObject()
	 */
	@Override
	protected final OrgUnit createNewObject() {
		return new OrgUnit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.ACacheManager#valueOfIndex(java.lang.String)
	 */
	@Override
	protected final EnumOrgUnitAttr valueOfIndex(final String parName) {
		return EnumOrgUnitAttr.valueOf(parName);
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
