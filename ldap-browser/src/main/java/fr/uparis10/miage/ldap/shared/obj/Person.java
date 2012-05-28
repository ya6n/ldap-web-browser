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

import java.util.EnumMap;

import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.itf.IHasPrimaryKey;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 */
public class Person extends EnumMap<EnumPersonAttr, String> implements IHasPrimaryKey<EnumPersonAttr,String>, Comparable<Person>{

	/**
   * @param keyType
   */
  public Person() {
	  super(EnumPersonAttr.class);
  }

	/**
   * Default Serial Version UID
   */
  private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public final int compareTo(final Person parOther) {
  	final String locUID = getPrimaryKeyValue();
  	assert (null != locUID);
  	final String locOtherUID = parOther.getPrimaryKeyValue();
  	assert (null != locOtherUID);

	  return locUID.compareTo(locOtherUID);
  }

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.uparis10.miage.ldap.shared.itf.IHasPrimaryKey#getPrimaryKey()
	 */
	@Override
	public final EnumPersonAttr getPrimaryKey() {
		return EnumPersonAttr.uid;
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
