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
 * Creation date: May 27, 2012
 */
package fr.uparis10.miage.ldap.shared.obj;

import java.util.HashMap;

import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.itf.IHasPrimaryKey;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public class Group extends HashMap<EnumGroupAttr, String> implements IHasPrimaryKey<EnumGroupAttr, String>, Comparable<Group> {

	/**
	 * @param keyType
	 */
	public Group() {
	}

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public final int compareTo(final Group parOther) {
		final String locCN = getPrimaryKeyValue();
		assert (null != locCN);
		final String locOtherCN = parOther.getPrimaryKeyValue();
		assert (null != locOtherCN);

		return locCN.compareTo(locOtherCN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.shared.itf.IHasPrimaryKey#getPrimaryKey()
	 */
	@Override
	public final EnumGroupAttr getPrimaryKey() {
		return EnumGroupAttr.cn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.shared.itf.IHasPrimaryKey#getPrimaryKeyValue()
	 */
	@Override
	public final String getPrimaryKeyValue() {
		return get(getPrimaryKey());
	}
}
