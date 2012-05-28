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

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.validation.constraints.NotNull;

import fr.uparis10.miage.ldap.server.utils.ServerStringUtils;
import fr.uparis10.miage.ldap.shared.enums.EnumOrganizationAttr;
import fr.uparis10.miage.ldap.shared.obj.Organization;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class OrganizationManager extends ACacheManager<EnumOrganizationAttr, String, Organization> {

	private static final String _prefix = "ou=structures,";
	private static final String _filter = "objectClass=organization";

	private static OrganizationManager _inst = null;

	private OrganizationManager() {
		super();
	}

	public final static OrganizationManager getInstance() {
		if (null == _inst) {
			_inst = new OrganizationManager();
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
	protected final Map<EnumOrganizationAttr, Map<String, List<Organization>>> createNewIndex() {
		return new EnumMap<EnumOrganizationAttr, Map<String, List<Organization>>>(EnumOrganizationAttr.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.server.ACacheManager#createNewObject()
	 */
	@Override
	protected final Organization createNewObject() {
		return new Organization();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.ACacheManager#valueOfIndex(java.lang.String)
	 */
	@Override
	protected final EnumOrganizationAttr valueOfIndex(final String parName) {
		return EnumOrganizationAttr.valueOf(parName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.uparis10.miage.ldap.server.itf.IIndexable#decodeAttribute(javax.naming
	 * .directory.Attribute)
	 */
	@Override
	public final String decodeAttribute(@NotNull final Attribute parInput, @NotNull final EnumOrganizationAttr parType) throws NamingException {
		return ServerStringUtils.decodeAttribute(parInput, parType);
	}
}
