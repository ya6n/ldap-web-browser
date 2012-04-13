/**
 * 
 */
package org.upond.miage.ldapbrowser.prototype.client.screen;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

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
		setLayout(new FitLayout());

		tabPanel = new TabPanel();

		tabPanel.setResizeTabs(true);
		tabPanel.setAnimScroll(true);
		tabPanel.setTabScroll(true);
		tabPanel.setCloseContextMenu(true);

		addBienvenueTab();

		this.add(tabPanel);

	}

	/**
	 * @param layout
	 */
	public ApplicationContainer(Layout layout) {
		this();
		setLayout(layout);
	}

	/**
	 * @return the tabPanel
	 */
	public TabPanel getTabPanel() {
		return tabPanel;
	}

	private void addBienvenueTab() {
		TabItem item = new TabItem();
		item.setText("Bienvenue");
		item.setClosable(false);
		item.addText("Bienvenue dans LDAP Browser");
		item.addStyleName("pad-text");
		tabPanel.add(item);
	}

}
