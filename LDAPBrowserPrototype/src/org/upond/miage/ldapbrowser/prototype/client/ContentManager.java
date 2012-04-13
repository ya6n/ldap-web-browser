package org.upond.miage.ldapbrowser.prototype.client;

import org.upond.miage.ldapbrowser.prototype.client.screen.ApplicationContainer;
import org.upond.miage.ldapbrowser.prototype.client.screen.ApplicationHeader;
import org.upond.miage.ldapbrowser.prototype.client.screen.LdapTreeScreen;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

/**
 * @author gorodenco
 * 
 */
public class ContentManager {

	public static final ContentPanel header = new ApplicationHeader();
	public static final ContentPanel container = new ApplicationContainer();
	public static final LdapTreeScreen ldapTreeScreen = new LdapTreeScreen();
	public static final LayoutContainer footer = new LayoutContainer();

}