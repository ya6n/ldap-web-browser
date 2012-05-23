/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

//import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
//import com.extjs.gxt.ui.client.event.ButtonEvent;
//import com.extjs.gxt.ui.client.event.SelectionListener;
//import com.extjs.gxt.ui.client.util.Padding;
//import com.extjs.gxt.ui.client.widget.Layout;
//import com.extjs.gxt.ui.client.widget.LayoutContainer;
//import com.extjs.gxt.ui.client.widget.button.Button;
//import com.extjs.gxt.ui.client.widget.form.FormPanel;
//import com.extjs.gxt.ui.client.widget.form.TextField;
//import com.extjs.gxt.ui.client.widget.layout.BoxLayout.BoxLayoutPack;
//import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
//import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.google.gwt.core.client.GWT;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.Style.HorizontalAlignment;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

import fr.uparis10.miage.ldap.client.GreetingService;
import fr.uparis10.miage.ldap.client.GreetingServiceAsync;
import fr.uparis10.miage.ldap.client.LDAPBrowser;

/**
 * @author iogorode
 * 
 */
public class LoginScreen extends LayoutContainer {

	FormPanel loginForm;

	TextField user;
	TextField pass;

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
								LDAPBrowser.onMainScreenLoad();

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
