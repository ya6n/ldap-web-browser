package fr.uparis10.miage.ldap.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;

import fr.uparis10.miage.ldap.client.screen.LoginScreen;
import fr.uparis10.miage.ldap.client.screen.MainScreen;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LDAPBrowser implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel rootPanel = RootPanel.get();

		rootPanel.clear();

		final CenterLayoutContainer root = new CenterLayoutContainer();

		LoginScreen loginScreen = new LoginScreen();

		root.setWidth(Window.getClientWidth());
		root.setHeight(Window.getClientHeight());
		root.setPosition(0, 0);
		// root.setStyleAttribute("margins", "0px");
		// root.setStyleAttribute("padding", "0px");
		// root.setHeaderVisible(false);
		root.setBorders(false);
		// root.setBodyBorder(false);
		Window.setMargin("0px");
		// rootPanel.setSize("100%", "100%");
		root.add(loginScreen);
		rootPanel.add(root);
		RootPanel.get().add(RootLayoutPanel.get());

		// root.layout(true);

		root.show();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				root.setWidth(Window.getClientWidth());
				root.setHeight(Window.getClientHeight());

			}
		});

	}

	public static void onMainScreenLoad() {

		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();

		final MainScreen mainScreen = new MainScreen();

		// rootPanel.setSize("100%", "100%");
		rootPanel.add(mainScreen);

		mainScreen.show();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				mainScreen.setWidth(Window.getClientWidth());
				mainScreen.setHeight(Window.getClientHeight());

			}
		});

	}
}