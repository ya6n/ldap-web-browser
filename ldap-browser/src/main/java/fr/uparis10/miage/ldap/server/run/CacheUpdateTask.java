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
 * Creation date: May 30, 2012
 */
package fr.uparis10.miage.ldap.server.run;

import java.util.TimerTask;

import fr.uparis10.miage.ldap.server.itf.IWithCache;
import fr.uparis10.miage.ldap.server.mng.GroupManager;
import fr.uparis10.miage.ldap.server.mng.OrgUnitManager;
import fr.uparis10.miage.ldap.server.mng.OrganizationManager;
import fr.uparis10.miage.ldap.server.mng.PeopleManager;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 */
public class CacheUpdateTask extends TimerTask {
	
	/* (non-Javadoc)
   * @see java.util.TimerTask#run()
   */
  @Override
  public final void run() {
  	for (final IWithCache locCacheMng : getCacheMngVect()) {
  		locCacheMng.refresh();
  	}
  }
  
  private final IWithCache[] getCacheMngVect() {
  	return new IWithCache[] {
  			PeopleManager.getInstance(),
  			GroupManager.getInstance(),
  			OrgUnitManager.getInstance(),
  			OrganizationManager.getInstance()
  	}; 
  }
}
