package fr.uparis10.miage.ldap.client;

//import com.extjs.gxt.ui.client.widget.ContentPanel;
//import com.extjs.gxt.ui.client.widget.LayoutContainer;

import com.sencha.gxt.widget.core.client.ContentPanel;

import fr.uparis10.miage.ldap.client.screen.ApplicationContainer;
import fr.uparis10.miage.ldap.client.screen.ApplicationHeader;
import fr.uparis10.miage.ldap.client.screen.LdapTreeScreen;

/**
 * @author gorodenco
 * 
 */
public class ContentManager {

	private static ContentManager instance = null;

	private final ContentPanel header;
	private final ContentPanel container;
	private final LdapTreeScreen ldapTreeScreen;
	private final ContentPanel footer;

	private ContentManager() {
		header = new ApplicationHeader();
		container = new ApplicationContainer();
		ldapTreeScreen = new LdapTreeScreen();
		footer = new ContentPanel();
	}

	public static ContentManager getInstance() {
		if (instance == null) {
			instance = new ContentManager();
		}

		return instance;
	}

	/**
	 * @return the header
	 */
	public ContentPanel getHeader() {
		return header;
	}

	/**
	 * @return the container
	 */
	public ContentPanel getContainer() {
		return container;
	}

	/**
	 * @return the ldapTreeScreen
	 */
	public LdapTreeScreen getLdapTreeScreen() {
		return ldapTreeScreen;
	}

	/**
	 * @return the footer
	 */
	public ContentPanel getFooter() {
		return footer;
	}

}
