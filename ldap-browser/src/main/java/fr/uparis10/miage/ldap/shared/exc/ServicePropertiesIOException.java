/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 OMAR
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
 * Creation date: 4 juin 2012
 */
package fr.uparis10.miage.ldap.shared.exc;

import java.io.IOException;

/**
 * @author OMAR
 *
 */
public class ServicePropertiesIOException extends IOException{

  private static final long serialVersionUID = 6319299897290173763L;

  public ServicePropertiesIOException() {
		super();
	}
	
	public ServicePropertiesIOException(final String parMsg) {
		super(parMsg);
	}
	
	public ServicePropertiesIOException(final Throwable parOtherExc) {
		super(parOtherExc);
	}
	
	public ServicePropertiesIOException(final String parMsg, final Throwable parOtherExc) {
		super(parMsg, parOtherExc);
	}
	
	
}
