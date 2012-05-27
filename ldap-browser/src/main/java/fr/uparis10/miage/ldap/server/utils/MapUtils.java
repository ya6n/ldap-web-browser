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
package fr.uparis10.miage.ldap.server.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 */
public final class MapUtils {
	
	public final static <K_TYPE, V_TYPE> void putInListMap(@NotNull final Map<K_TYPE, List<V_TYPE>> parMap, @NotNull final K_TYPE parKey, @NotNull final V_TYPE parVal) {
		List<V_TYPE> locValList = parMap.get(parKey);
		if (null == locValList) {
			locValList = new ArrayList<V_TYPE>(1);
			parMap.put(parKey, locValList);
		}
		locValList.add(parVal);
	}	

	public final static <K_TYPE, V_TYPE> void putInListMap(@NotNull final Map<K_TYPE, List<V_TYPE>> parMap, @NotNull final K_TYPE parKey, @NotNull final Collection<V_TYPE> parValList) {
		List<V_TYPE> locValList = parMap.get(parKey);
		if (null == locValList) {
			locValList = new ArrayList<V_TYPE>(parValList.size());
			parMap.put(parKey, locValList);
		}
		locValList.addAll(parValList);
	}
	
	public final static <I_TYPE,K_TYPE,V_TYPE> void putInIndexMap(@NotNull final Map<I_TYPE,Map<K_TYPE,List<V_TYPE>>> parIndexMap, @NotNull final I_TYPE parIndexType, @NotNull final K_TYPE parKey, @NotNull final V_TYPE parVal) {
		Map<K_TYPE, List<V_TYPE>> locIndex = parIndexMap.get(parIndexType);
		if (null == locIndex) {
			locIndex = new HashMap<K_TYPE, List<V_TYPE>>();
			parIndexMap.put(parIndexType, locIndex);
			assert (null != locIndex);
		}
		putInListMap(locIndex, parKey, parVal);
	}
}
