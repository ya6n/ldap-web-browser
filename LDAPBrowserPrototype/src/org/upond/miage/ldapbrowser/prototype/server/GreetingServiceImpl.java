package org.upond.miage.ldapbrowser.prototype.server;

import org.upond.miage.ldapbrowser.prototype.client.GreetingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {

		return "Hello, " + input;
	}

}
