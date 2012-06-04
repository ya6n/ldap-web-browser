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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

import fr.uparis10.miage.ldap.client.LDAPBrowser;
import fr.uparis10.miage.ldap.client.service.LoginService;
import fr.uparis10.miage.ldap.client.service.LoginServiceAsync;

/**
 * @author iogorode
 * 
 */
public class LoginScreen extends ContentPanel {

	private final FramedPanel loginForm;

	private final TextField user;
	private final PasswordField pass;

	private final TextButton btLogin;
	private final TextButton btReset;

	public LoginScreen() {

		setHeaderVisible(false);
		setBodyBorder(false);
		setBorders(false);
		setWidth(400);
		setHeight(250);

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		loginForm = new FramedPanel();
		loginForm.setHeadingText("Login Form");

		loginForm.setWidth(400);
		loginForm.setHeight(200);

		user = new TextField();
		user.setWidth(200);

		pass = new PasswordField();
		pass.setWidth(200);

		p.add(new FieldLabel(user, "Username"), new VerticalLayoutData(1, -1));
		p.add(new FieldLabel(pass, "Password"), new VerticalLayoutData(1, -1));

		btLogin = new TextButton("Login");
		btReset = new TextButton("Reset");

		btReset.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				user.reset();
				pass.reset();
			}
		});

		KeyDownHandler keyDownHandler = new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					tryLogin();
				}
			}
		};
		user.addKeyDownHandler(keyDownHandler);
		pass.addKeyDownHandler(keyDownHandler);

		btLogin.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				tryLogin();
			}

		});

		loginForm.add(p);
		add(loginForm);

		loginForm.addButton(btLogin);
		loginForm.addButton(btReset);

	}

	private void tryLogin() {
		LoginServiceAsync loginService = GWT
		    .create(LoginService.class);
		loginService.loginUser(user.getCurrentValue(), pass.getCurrentValue(),
		    new AsyncCallback<Boolean>() {

			    @Override
			    public void onSuccess(Boolean result) {
				    if (result) {
					    LDAPBrowser.onMainScreenLoad();
				    }
				    else {
					    Window.alert(result.toString());
				    }

			    }

			    @Override
			    public void onFailure(Throwable caught) {
				    Window.alert("BAD");

			    }
		    });
	}
}
