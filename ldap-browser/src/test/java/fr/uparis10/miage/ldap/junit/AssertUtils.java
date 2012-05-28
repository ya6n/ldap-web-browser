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
package fr.uparis10.miage.ldap.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class AssertUtils {
	/**
	 * This method tests if the given class is a singleton.<br>
	 * It uses Java Reflection to get the "getInstance()" method of the class.<br>
	 * It tests that this method is public, static and that the resulting object
	 * is of the parClass type.<br>
	 * It will also assert that the resulting reference is always the same (it's a
	 * singleton, and not a factory).
	 * 
	 * @param parClass
	 *          The class to be tested for singletoness
	 */
	public final static void assertIsSingleton(@NotNull final Class<?> parClass) {
		try {
			final Method locMeth = parClass.getDeclaredMethod("getInstance");
			assertNotNull(locMeth);
			final Object locInst1 = locMeth.invoke(null);
			assertNotNull(locInst1);
			assertTrue(parClass.isInstance(locInst1));
			final Object locInst2 = locMeth.invoke(null);
			assertNotNull(locInst2);
			assertTrue(parClass.isInstance(locInst2));
			assertTrue(locInst1 == locInst2);
		} catch (final SecurityException locExc) {
			throw new RuntimeException(locExc);
		} catch (final NoSuchMethodException locExc) {
			throw new RuntimeException(locExc);
		} catch (final IllegalArgumentException locExc) {
			throw new RuntimeException(locExc);
		} catch (final IllegalAccessException locExc) {
			throw new RuntimeException(locExc);
		} catch (final InvocationTargetException locExc) {
			throw new RuntimeException(locExc);
		}
	}

	/**
	 * Tests that the content of 2 lists is the virtually the same (the 2 lists a
	 * a deep copy of each other).
	 * 
	 * @param parList1
	 *          the first list
	 * @param parList2
	 *          the second list
	 */
	public final static <V_TYPE> void assertListsAreClones(@NotNull final List<V_TYPE> parList1, @NotNull final List<V_TYPE> parList2) {
		assertEquals(parList1.size(), parList2.size());
		final Iterator<V_TYPE> locIter1 = parList1.iterator();
		final Iterator<V_TYPE> locIter2 = parList2.iterator();
		while (locIter1.hasNext()) {
			assertTrue(locIter2.hasNext());
			final V_TYPE locObj1 = locIter1.next();
			final V_TYPE locObj2 = locIter2.next();
			assertEquals(locObj1, locObj2);
			assertTrue(locObj1 != locObj2);
		}
	}

	/**
	 * Tests that the content of 2 lists is the virtually the same (the 2 lists a
	 * a deep copy of each other).
	 * 
	 * @param parList1
	 *          the first list
	 * @param parList2
	 *          the second list
	 */
	public final static <V_TYPE extends Comparable<V_TYPE>> void assertListsAreSame(@NotNull final List<V_TYPE> parList1, @NotNull final List<V_TYPE> parList2) {
		assertEquals(parList1.size(), parList2.size());

		final List<V_TYPE> locCloneList1 = new ArrayList<V_TYPE>(parList1);
		final List<V_TYPE> locCloneList2 = new ArrayList<V_TYPE>(parList2);
		Collections.<V_TYPE> sort(locCloneList1);
		Collections.<V_TYPE> sort(locCloneList2);
		
		final Iterator<V_TYPE> locIter1 = locCloneList1.iterator();
		final Iterator<V_TYPE> locIter2 = locCloneList2.iterator();
		while (locIter1.hasNext()) {
			assertTrue(locIter2.hasNext());
			final V_TYPE locObj1 = locIter1.next();
			final V_TYPE locObj2 = locIter2.next();
			assertEquals(locObj1, locObj2);
		}
	}
}
