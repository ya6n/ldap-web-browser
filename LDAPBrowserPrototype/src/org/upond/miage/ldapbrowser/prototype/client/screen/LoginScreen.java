/**
 * 
 */
package org.upond.miage.ldapbrowser.prototype.client.screen;

import org.upond.miage.ldapbrowser.prototype.client.GreetingService;
import org.upond.miage.ldapbrowser.prototype.client.GreetingServiceAsync;
import org.upond.miage.ldapbrowser.prototype.client.LDAPBrowserPrototype;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout.BoxLayoutPack;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author iogorode
 * 
 */
public class LoginScreen extends LayoutContainer {

	FormPanel loginForm;

	TextField<String> user;
	TextField<String> pass;

	Button btLogin;
	Button btReset;

	public LoginScreen() {

		loginForm = new FormPanel();

		loginForm.setHeading("Login Form");
		loginForm.setWidth(400);

		user = new TextField<String>();
		user.setFieldLabel("Username");
		user.setWidth(200);

		pass = new TextField<String>();
		pass.setFieldLabel("Password");
		pass.setWidth(200);
		pass.setPassword(true);

		loginForm.add(user);
		loginForm.add(pass);

		btLogin = new Button("Login");
		btReset = new Button("Reset");

		btReset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				loginForm.reset();
			}

		});

		btLogin.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				GreetingServiceAsync greetingService = GWT
						.create(GreetingService.class);
				greetingService.greetServer(user.getValue(),
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								Window.alert(result);
								LDAPBrowserPrototype.onMainScreenLoad();

							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("BAD");

							}
						});

			}

		});

		loginForm.addButton(btLogin);
		loginForm.addButton(btReset);
		loginForm.setButtonAlign(HorizontalAlignment.CENTER);

		loginForm.setFrame(true);
		// setLayout(new CenterLayout());

		VBoxLayout layout = new VBoxLayout();
		layout.setPadding(new Padding(5));
		layout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
		layout.setPack(BoxLayoutPack.CENTER);

		setLayout(layout);

		add(loginForm);

	}

	/**
	 * @param layout
	 */
	public LoginScreen(Layout layout) {
		this();
		setLayout(layout);
	}

	@Override
	public boolean layout(boolean force) {
		loginForm.layout(force);
		return super.layout(force);
	}

}
