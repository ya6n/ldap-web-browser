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
package fr.uparis10.miage.ldap.client.screen.provider;

import com.sencha.gxt.core.client.ValueProvider;

import fr.uparis10.miage.ldap.shared.enums.EnumOrgUnitAttr;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;

/**
 * @author gorodenco
 * 
 */
public class OrgUnitValueProvider implements ValueProvider<OrgUnit, String> {

	public String getValue(OrgUnit orgUnit) {
		return orgUnit.getPrimaryKeyValue();
	}

	public void setValue(OrgUnit orgUnit, String value) {
		orgUnit.put(orgUnit.getPrimaryKey(), value);
	}

	public String getPath() {
		return EnumOrgUnitAttr.ou.toString();
	}
}
