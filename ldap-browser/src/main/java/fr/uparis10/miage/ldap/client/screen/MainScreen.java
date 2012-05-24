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

		setNorthWidget(contMan.getHeader(), new BorderLayoutData(100));
		setCenterWidget(contMan.getContainer(), new BorderLayoutData());
		setWestWidget(contMan.getLdapTreeScreen(), new BorderLayoutData(250));
		setSouthWidget(contMan.getFooter(), new BorderLayoutData(100));

	}

}
