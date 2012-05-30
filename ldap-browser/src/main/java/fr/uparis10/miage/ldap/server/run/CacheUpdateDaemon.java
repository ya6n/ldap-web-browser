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

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public class CacheUpdateDaemon extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Timer _timer = new Timer();
	private static final int _UPDATE_TIMEOUT = 3600000;

	@Override
	public final void init() {
		final TimerTask locTask = new CacheUpdateTask();
		// The first load is right now ans we shall wait for the task to finish
		locTask.run();
		// one fois par heure (3600 000 ms) on va recharger tout l'arbre LDAP
		_timer.scheduleAtFixedRate(locTask, _UPDATE_TIMEOUT, _UPDATE_TIMEOUT);
	}

}
