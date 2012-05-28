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

import java.util.Map;

import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author gorodenco
 * 
 */

public class MapValueProvider implements ValueProvider<Map<String, String>, String> {
	private final String key;

	public MapValueProvider(String key) {
		this.key = key;
	}

	public String getValue(Map<String, String> object) {
		return object.get(key);
	}

	public void setValue(Map<String, String> object, String value) {
		object.put(key, value);
	}

	public String getPath() {
		return key;
	}
}
