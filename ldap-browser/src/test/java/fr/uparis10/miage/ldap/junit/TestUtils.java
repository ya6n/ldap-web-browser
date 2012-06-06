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
 * Creation date: Jun 6, 2012
 */
package fr.uparis10.miage.ldap.junit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.easymock.EasyMock;

import com.google.gwt.user.server.rpc.AbstractRemoteServiceServlet;

import static org.junit.Assert.*;

/**
 * @author Gicu GORODENCO <cyclopsihus@gmail.com>
 * 
 */
public final class TestUtils {

	private TestUtils() {
	}

	public final static void initTestServlet(@NotNull final AbstractRemoteServiceServlet parServlet) throws ServletException, IOException {
		final HttpServletRequest locMockReq = EasyMock.createNiceMock(HttpServletRequest.class);
		assertNotNull(locMockReq);
		final HttpServletResponse locMockResp = EasyMock.createControl().createMock(HttpServletResponse.class);
		assertNotNull(locMockResp);
		final RequestDispatcher locReqDisp = locMockReq.getRequestDispatcher("");
		assertNotNull(locReqDisp);
		locReqDisp.forward(locMockReq, locMockResp);
		parServlet.doPost(locMockReq, locMockResp);
	}

}
