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

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.naming.NamingException;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class DataLdapCtxManager extends ALdapCtxManager {
	/**
   * @throws FileNotFoundException
   * @throws IOException
   * @throws NamingException
   */
  private DataLdapCtxManager() throws FileNotFoundException, IOException, NamingException {
	  super();
  }

	private static DataLdapCtxManager _inst = null;

	public final static DataLdapCtxManager getInstance() throws FileNotFoundException, IOException, NamingException {
		if (null == _inst) {
			_inst = new DataLdapCtxManager();
		}

		return _inst;
	}

	/* (non-Javadoc)
   * @see fr.uparis10.miage.ldap.server.mng.ALdapCtxManager#getConfigFile()
   */
  @Override
  public final String getConfigFile() {
  	return "ldap_server.properties";
  }

}
