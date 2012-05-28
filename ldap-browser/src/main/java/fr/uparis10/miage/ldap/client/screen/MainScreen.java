/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

//import com.extjs.gxt.ui.client.Style.LayoutRegion;
//import com.extjs.gxt.ui.client.widget.ContentPanel;
//import com.extjs.gxt.ui.client.widget.Layout;
//import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
//import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

import fr.uparis10.miage.ldap.client.ContentManager;

/**
 * @author iogorode
 * 
 */
public class MainScreen extends BorderLayoutContainer {

	public MainScreen() {

		// setLayout(new BorderLayout());
		this.setWidth(Window.getClientWidth());
		this.setHeight(Window.getClientHeight());
		setPosition(0, 0);
		this.getElement().setMargins(new Margins(0));
		this.getElement().setPadding(new Padding(0));
		setBorders(false);
		Window.setMargin("0px");

		ContentManager contMan = ContentManager.getInstance();

		BorderLayoutData layoutData = new BorderLayoutData(100);
		layoutData.setCollapsible(true);
		setNorthWidget(contMan.getHeader(), layoutData);
		setCenterWidget(contMan.getContainer(), new BorderLayoutData());
		layoutData = new BorderLayoutData(250);
		layoutData.setCollapsible(true);
		setWestWidget(contMan.getLdapTreeScreen(), layoutData);
		// layoutData = new BorderLayoutData(100);
		// layoutData.setCollapsible(true);
		// setSouthWidget(contMan.getFooter(), layoutData);

	}
}
