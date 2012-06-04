/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

import fr.uparis10.miage.ldap.client.ContentManager;
import fr.uparis10.miage.ldap.shared.obj.Person;

//import com.extjs.gxt.ui.client.widget.ContentPanel;
//import com.extjs.gxt.ui.client.widget.Layout;
//import com.extjs.gxt.ui.client.widget.TabItem;
//import com.extjs.gxt.ui.client.widget.TabPanel;
//import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 * @author iogorode
 * 
 */
/**
 * @author iogorode
 * 
 */
public class ApplicationContainer extends ContentPanel {

	private TabPanel tabPanel;

	/**
	 * 
	 */
	public ApplicationContainer() {
		setHeaderVisible(false);
		// setLayout(new FitLayout());

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
		TabItemConfig item = new TabItemConfig("Bienvenue", false);

		SimpleContainer simpleContainer = new SimpleContainer();
		simpleContainer.add(new Frame("Bienvenue.html"));
		tabPanel.add(simpleContainer, item);
		// new HTMLPanel("<center>Bienvenue dans LDAP Browser</center>"), item);

	}

	public void openScreen(Screen screen) {
		TabItemConfig item = new TabItemConfig(screen.getTitle(), true);
		item.setIcon(screen.getIcon());

		// SimpleContainer simpleContainer = new SimpleContainer();
		// simpleContainer.add(screen);
		tabPanel.add(screen, item);
		tabPanel.setActiveWidget(screen);

	}

}
