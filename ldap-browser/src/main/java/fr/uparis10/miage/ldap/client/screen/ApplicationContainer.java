/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

import fr.uparis10.miage.ldap.client.ContentManager;
import fr.uparis10.miage.ldap.client.messages.ApplicationMessages;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author iogorode
 * 
 */
public class ApplicationContainer extends ContentPanel {

	private TabPanel tabPanel;

	public static final ApplicationMessages messages;

	static {
		messages = (ApplicationMessages) GWT.create(ApplicationMessages.class);
	}

	/**
	 * 
	 */
	public ApplicationContainer() {
		setHeaderVisible(false);

		tabPanel = new TabPanel();

		tabPanel.setResizeTabs(false);
		tabPanel.setAnimScroll(true);
		tabPanel.setTabScroll(true);
		tabPanel.setCloseContextMenu(true);

		addBienvenueTab();

		this.add(tabPanel);

		tabPanel.addSelectionHandler(new SelectionHandler<Widget>() {

			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				Widget activeWidget = tabPanel.getActiveWidget();

				if (activeWidget instanceof PeopleSynthesisScreen) {
					Person person = ((PeopleSynthesisScreen) activeWidget).getPerson();
					ContentManager.getInstance().getLdapTreeScreen().loadPerson(person);
				} else
				if (activeWidget instanceof PeopleSearchScreen) {
					Person person = ((PeopleSearchScreen) activeWidget).getSelectedPerson();
					if (person != null)
						ContentManager.getInstance().getLdapTreeScreen().loadPerson(person);
				}

			}
		});

	}

	/**
	 * @return the tabPanel
	 */
	public TabPanel getTabPanel() {
		return tabPanel;
	}

	private void addBienvenueTab() {
		TabItemConfig item = new TabItemConfig(messages.getWelcomeScreen(), false);

		SimpleContainer simpleContainer = new SimpleContainer();
		simpleContainer.add(new Frame("Bienvenue.html"));
		tabPanel.add(simpleContainer, item);
	}

	public void openScreen(Screen screen) {
		TabItemConfig item = new TabItemConfig(screen.getTitle(), true);
		item.setIcon(screen.getIcon());

		tabPanel.add(screen, item);
		tabPanel.setActiveWidget(screen);

	}

}
