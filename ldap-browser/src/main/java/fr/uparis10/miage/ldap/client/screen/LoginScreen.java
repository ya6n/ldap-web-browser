/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import fr.uparis10.miage.ldap.client.ContentManager;
import fr.uparis10.miage.ldap.client.LDAPBrowser;
import fr.uparis10.miage.ldap.client.messages.LoginScreenMessages;
import fr.uparis10.miage.ldap.client.service.LoginService;
import fr.uparis10.miage.ldap.client.service.LoginServiceAsync;
import fr.uparis10.miage.ldap.shared.obj.Person;

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

	public static final LoginScreenMessages messages;

	static {
		messages = (LoginScreenMessages) GWT.create(LoginScreenMessages.class);
	}

	public LoginScreen() {

		setHeaderVisible(false);
		setBodyBorder(false);
		setBorders(false);
		setWidth(400);
		setHeight(250);

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		loginForm = new FramedPanel();
		loginForm.setHeadingText(messages.getTitle());

		loginForm.setWidth(400);
		loginForm.setHeight(200);

		user = new TextField();
		user.setWidth(200);

		pass = new PasswordField();
		pass.setWidth(200);

		p.add(new FieldLabel(user, messages.getUserLabel()), new VerticalLayoutData(1, -1));
		p.add(new FieldLabel(pass, messages.getPassLabel()), new VerticalLayoutData(1, -1));

		btLogin = new TextButton(messages.getLoginButtonLabel());
		btReset = new TextButton(messages.getResetButtonLabel());

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
		// add(loginForm);

		loginForm.addButton(btLogin);
		loginForm.addButton(btReset);

		IconButton fr = new IconButton("fr-button");
		fr.setTitle("FR");
		fr.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Window.open(Window.Location.getPath() + "?locale=fr", "_self", "");
			}
		});
		fr.setWidth(32);
		fr.setHeight(32);

		IconButton en = new IconButton("en-button");
		en.setTitle("EN");
		en.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Window.open(Window.Location.getPath() + "?locale=en", "_self", "");
			}
		});
		en.setWidth(32);
		en.setHeight(32);

		HorizontalLayoutContainer langContainer = new HorizontalLayoutContainer();

		langContainer.add(fr, new HorizontalLayoutData(-1, -1, new Margins(1)));
		langContainer.add(en, new HorizontalLayoutData(-1, -1, new Margins(1)));

		// p.add(langContainer, new VerticalLayoutData(1, -1, new Margins(10)));

		VerticalLayoutContainer lastContainer = new VerticalLayoutContainer();

		lastContainer.add(loginForm);
		lastContainer.add(langContainer);

		add(lastContainer);

	}

	private void tryLogin() {
		LoginServiceAsync loginService = GWT
		    .create(LoginService.class);
		loginService.loginUser(user.getCurrentValue(), pass.getCurrentValue(),
		    new AsyncCallback<Person>() {

			    @Override
			    public void onSuccess(Person result) {
				    if (result != null) {
					    Info.display(messages.getSuccessTitle(), messages.getSuccessMessage());
					    ContentManager.getInstance().setUserPerson(result);
					    ContentManager.getInstance().getHeader().updateUserData();
					    LDAPBrowser.onMainScreenLoad();
				    }
				    else {
					    AlertMessageBox d = new AlertMessageBox(messages.getAlertTitle(), messages.getAlertMessage());
					    d.show();
				    }

			    }

			    @Override
			    public void onFailure(Throwable caught) {
				    ContentManager.getInstance().chekFailure(caught);
			    }
		    });
	}
}
