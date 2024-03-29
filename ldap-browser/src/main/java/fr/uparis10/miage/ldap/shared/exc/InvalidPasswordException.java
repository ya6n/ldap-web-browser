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
package fr.uparis10.miage.ldap.shared.exc;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 *
 */
public class InvalidPasswordException extends Exception {
	/**
   * Default Serial Version UID 
   */
  private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super();
	}
	
	public InvalidPasswordException(final String parMsg) {
		super(parMsg);
	}
	
	public InvalidPasswordException(final Throwable parOtherExc) {
		super(parOtherExc);
	}
	
	public InvalidPasswordException(final String parMsg, final Throwable parOtherExc) {
		super(parMsg, parOtherExc);
	}
}
