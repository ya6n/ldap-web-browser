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
 * Creation date: Jun 1, 2012
 */
package fr.uparis10.miage.ldap.shared.exc;

import javax.validation.constraints.NotNull;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public class ContainerException extends RuntimeException {
	/**
   * 
   */
	private static final long serialVersionUID = 1L;

	public ContainerException(@NotNull final Throwable parExc) {
		super(parExc);
	}
}
