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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.junit.Test;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class LdapCtxManagerTest {

	@Test(timeout = 10000L)
	public final void testGetContextAndDn() throws FileNotFoundException, IOException, NamingException {
		final DirContext locCtx = LdapCtxManager.getInstance().getContext();
		final String locBaseDn = LdapCtxManager.getInstance().getBaseDN();
		assertNotNull(locCtx);
		assertNotNull(locBaseDn);
		assertTrue(!locBaseDn.isEmpty());
		final Object locBaseObj = locCtx.lookup(locBaseDn);
		assertNotNull(locBaseObj);
	}
}
