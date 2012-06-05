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
package fr.uparis10.miage.ldap.server.mng;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.uparis10.miage.ldap.junit.AssertUtils;
import fr.uparis10.miage.ldap.server.service.ServicesPropertiesManager;
import fr.uparis10.miage.ldap.server.service.UserLoginChecker;

/**
 * This Unit Test will test that some Singleton classes are singleton as
 * assumed.
 * 
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
@RunWith(Parameterized.class)
public class SingletonTest {
	private Class<?> _class;

	public SingletonTest(final Class<?> parClass) {
		_class = parClass;
	}

	@Parameters
	public final static List<Object[]> getSingletonClasses() {
		return Arrays.<Object[]> asList(
		    new Object[] { AuthLdapCtxManager.class },
		    new Object[] { DataLdapCtxManager.class },
		    new Object[] { PeopleManager.class },
		    new Object[] { GroupManager.class },
		    new Object[] { OrgUnitManager.class },
		    new Object[] { OrganizationManager.class },
		    new Object[] { UserLoginManager.class },
		    new Object[] { ServicesPropertiesManager.class },
		    new Object[] { UserLoginChecker.class });
	}

	@Test(timeout = 10000L)
	public final void testIsSingleton() {
		AssertUtils.assertIsSingleton(_class);
	}
}
