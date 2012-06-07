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
 * Creation date: Jun 7, 2012
 */
package fr.uparis10.miage.ldap.shared.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class ListUtils {

	private ListUtils() {
	}

	/**
	 * Merges a List of Lists of values info one (Array)List, trying to optimize
	 * the memory allocation
	 * 
	 * @param parListOfList
	 *          the list of list of values to be merges
	 * @return a merged list
	 */
	public final static <V_TYPE> List<V_TYPE> mergeListOfList(@NotNull final List<List<V_TYPE>> parListOfList) {
		if (parListOfList.isEmpty()) {
			return Collections.<V_TYPE> emptyList();
		}
		// estimating size:
		int locEstSize = 0;
		final List<List<V_TYPE>> locOptListOfList = new ArrayList<List<V_TYPE>>(parListOfList.size());
		for (final List<V_TYPE> locList : parListOfList) {
			if (null == locList || locList.isEmpty()) {
				continue;
			}
			locEstSize += locList.size();
			locOptListOfList.add(locList);
		}
		switch (locOptListOfList.size()) {
		case 0:
			return Collections.<V_TYPE> emptyList();
			// otherwise
		case 1:
			final List<V_TYPE> locResList = locOptListOfList.get(0);
			assert (null != locResList);
			assert (!locResList.isEmpty());
			return locResList;
			// otherwise
		default:
			final List<V_TYPE> locMergedList = new ArrayList<V_TYPE>(locEstSize);
			for (final List<V_TYPE> locList : locOptListOfList) {
				assert (null != locList);
				assert (!locList.isEmpty());
				locMergedList.addAll(locList);
			}
			return locMergedList;
		}
	}

}
