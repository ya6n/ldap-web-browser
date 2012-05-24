/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.google.gwt.user.client.ui.Frame;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

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

		tabPanel.setResizeTabs(true);
		tabPanel.setAnimScroll(true);
		tabPanel.setTabScroll(true);
		tabPanel.setCloseContextMenu(true);

		addBienvenueTab();

		this.add(tabPanel);

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
}
