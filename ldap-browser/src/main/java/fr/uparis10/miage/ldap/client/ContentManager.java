package fr.uparis10.miage.ldap.client;

//import com.extjs.gxt.ui.client.widget.ContentPanel;
//import com.extjs.gxt.ui.client.widget.LayoutContainer;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;

import fr.uparis10.miage.ldap.client.messages.LoginScreenMessages;
import fr.uparis10.miage.ldap.client.screen.ApplicationContainer;
import fr.uparis10.miage.ldap.client.screen.ApplicationHeader;
import fr.uparis10.miage.ldap.client.screen.LdapTreeScreen;
import fr.uparis10.miage.ldap.client.screen.PopupLoginScreen;
import fr.uparis10.miage.ldap.shared.exc.UserNotLoggedException;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author gorodenco
 * 
 */
public final class ContentManager {

	private static ContentManager instance = null;

	private final ApplicationHeader header;
	private final ApplicationContainer container;
	private final LdapTreeScreen ldapTreeScreen;
	private final ContentPanel footer;

	public static final LoginScreenMessages messagesLogin;

	static {
		messagesLogin = (LoginScreenMessages) GWT.create(LoginScreenMessages.class);
	}

	private Person userPerson;

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
	public ApplicationHeader getHeader() {
		return header;
	}

	/**
	 * @return the container
	 */
	public ApplicationContainer getContainer() {
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

	/**
	 * @return the userPerson
	 */
	public Person getUserPerson() {
		return userPerson;
	}

	/**
	 * @param userPerson
	 *          the userPerson to set
	 */
	public void setUserPerson(Person userPerson) {
		this.userPerson = userPerson;
	}

	public void chekFailure(Throwable caught) {

		if (caught instanceof UserNotLoggedException) {
			final AlertMessageBox d = new AlertMessageBox(messagesLogin.getExpiredTitle(), messagesLogin.getExpiredMessage());
			d.setHideOnButtonClick(true);
			d.addHideHandler(new HideHandler() {

				@Override
				public void onHide(HideEvent event) {
					PopupLoginScreen login = new PopupLoginScreen();
					login.addHideHandler(new HideHandler() {

						@Override
						public void onHide(HideEvent event) {
							d.hide();

						}
					});

					login.show();
				}
			});
			d.show();
		}

	}
}
