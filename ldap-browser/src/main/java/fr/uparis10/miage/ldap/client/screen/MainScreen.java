/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Window;

import fr.uparis10.miage.ldap.client.ContentManager;

/**
 * @author iogorode
 * 
 */
public class MainScreen extends ContentPanel {

	public MainScreen() {

		setLayout(new BorderLayout());
		setSize(Window.getClientWidth(), Window.getClientHeight());
		setPosition(0, 0);
		setStyleAttribute("margins", "0px");
		setStyleAttribute("padding", "0px");
		setHeaderVisible(false);
		setBorders(false);
		setBodyBorder(false);
		Window.setMargin("0px");

		add(ContentManager.header,
				new BorderLayoutData(LayoutRegion.NORTH, 100));
		add(ContentManager.container, new BorderLayoutData(LayoutRegion.CENTER));
		add(ContentManager.ldapTreeScreen, new BorderLayoutData(
				LayoutRegion.WEST, 250));
		add(ContentManager.footer,
				new BorderLayoutData(LayoutRegion.SOUTH, 100));

	}

	/**
	 * @param layout
	 */
	public MainScreen(Layout layout) {
		this();
		setLayout(layout);
	}

	@Override
	public boolean layout(boolean force) {
		return super.layout(force);
	}

}
