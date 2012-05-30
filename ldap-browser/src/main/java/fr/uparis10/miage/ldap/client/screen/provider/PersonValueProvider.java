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

import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author gorodenco
 * 
 */
public class PersonValueProvider implements ValueProvider<Person, String> {

	private EnumPersonAttr key;

	public PersonValueProvider() {
		this(EnumPersonAttr.uid);
	}

	public PersonValueProvider(EnumPersonAttr key) {
		this.key = key;
	}

	public String getValue(Person person) {
		return person.get(key);
	}

	public void setValue(Person person, String value) {
		person.put(key, value);
	}

	public String getPath() {
		return key.name();
	}
}
