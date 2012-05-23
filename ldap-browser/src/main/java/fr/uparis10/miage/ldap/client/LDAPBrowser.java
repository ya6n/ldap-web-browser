package fr.uparis10.miage.ldap.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

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

		final ContentPanel root = new ContentPanel(new FitLayout());

		LoginScreen loginScreen = new LoginScreen();

		root.setSize(Window.getClientWidth(), Window.getClientHeight());
		root.setPosition(0, 0);
		root.setStyleAttribute("margins", "0px");
		root.setStyleAttribute("padding", "0px");
		root.setHeaderVisible(false);
		root.setBorders(false);
		root.setBodyBorder(false);
		Window.setMargin("0px");
		// rootPanel.setSize("100%", "100%");
		root.add(loginScreen);
		rootPanel.add(root);

		root.layout(true);

		root.show();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				root.setSize(Window.getClientWidth(), Window.getClientHeight());
				root.layout(true);

			}
		});

	}

	public static void onMainScreenLoad() {

		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();

		final MainScreen mainScreen = new MainScreen();

		// rootPanel.setSize("100%", "100%");
		rootPanel.add(mainScreen);

		mainScreen.layout(true);
		mainScreen.show();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				mainScreen.setSize(Window.getClientWidth(),
						Window.getClientHeight());
				mainScreen.layout(true);

			}
		});

	}
}