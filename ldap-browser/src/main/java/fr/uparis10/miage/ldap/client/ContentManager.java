package fr.uparis10.miage.ldap.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

import fr.uparis10.miage.ldap.client.screen.ApplicationContainer;
import fr.uparis10.miage.ldap.client.screen.ApplicationHeader;
import fr.uparis10.miage.ldap.client.screen.LdapTreeScreen;

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