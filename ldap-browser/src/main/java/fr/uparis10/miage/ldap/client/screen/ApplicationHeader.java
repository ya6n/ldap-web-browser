/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import fr.uparis10.miage.ldap.client.ContentManager;
import fr.uparis10.miage.ldap.client.resources.icons.IconsStore;
import fr.uparis10.miage.ldap.client.service.LoginService;
import fr.uparis10.miage.ldap.client.service.LoginServiceAsync;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;

/**
 * @author iogorode
 * 
 */
public class ApplicationHeader extends BorderLayoutContainer {

	private Image logo;

	private ContentPanel info;

	private ToolBar menu;

	private TextField userId;
	private TextField userName;

	/**
	 * 
	 */
	public ApplicationHeader() {

		logo = new Image("images/logo-upx.png");
		SimpleContainer logoContainer = new SimpleContainer();
		logoContainer.add(logo);

		/*
		 * info = new ContentPanel(); info.setHeaderVisible(false);
		 * info.setBodyBorder(false); info.setBorders(false);
		 */

		menu = getMenu();
		info = getInfo();

		setWestWidget(logoContainer, new BorderLayoutData(250));
		setCenterWidget(menu, new BorderLayoutData());
		setEastWidget(info, new BorderLayoutData(250));

	}

	private ToolBar getMenu() {
		ToolBar toolBar = new ToolBar();

		TextButton btn = new TextButton("Simple Search", IconsStore.INSTANCE.searchImg());
		btn.setScale(ButtonScale.LARGE);
		btn.setIconAlign(IconAlign.TOP);
		btn.setArrowAlign(ButtonArrowAlign.BOTTOM);

		btn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				PeopleSearchScreen screen = new PeopleSearchScreen();
				ContentManager.getInstance().getContainer().openScreen(screen);
				screen.selectAll();

			}
		});
		toolBar.add(btn);

		return toolBar;
	}

	private ContentPanel getInfo() {
		ContentPanel toolBar = new ContentPanel();

		HorizontalLayoutContainer tbContainer = new HorizontalLayoutContainer();
		// TextButton btn = new TextButton("Logout",
		// IconsStore.INSTANCE.logoutImg32());

		IconButton btn = new IconButton("logout-button");

		// btn.setScale(ButtonScale.MEDIUM);
		// btn.setIconAlign(IconAlign.LEFT);
		// btn.setArrowAlign(ButtonArrowAlign.BOTTOM);
		// Label userId = new
		// Label(ContentManager.getInstance().getUserPerson().get(EnumPersonAttr.uid));
		// Label userName = new
		// Label(ContentManager.getInstance().getUserPerson().get(EnumPersonAttr.displayName));
		userId = new TextField();
		userName = new TextField();

		userId.setReadOnly(true);
		userName.setReadOnly(true);

		userId.setStyleName("userinfo-text");
		userName.setStyleName("userinfo-text");

		final LoginServiceAsync loginService = GWT.create(LoginService.class);
		btn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				// logout code
				// replace logout (label & image) by login (label & image)
				loginService.logoutUser(new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							userId.reset();
							userName.reset();
							Window.Location.reload();
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						ContentManager.getInstance().chekFailure(caught);

					}
				});

			}
		});
		btn.setWidth(64);
		btn.setHeight(36);
		// btn.setStyleName("logout-button");
		// btn.setStylePrimaryName("logout-button");

		// tbContainer.add(btn, new HorizontalLayoutData(-1, -1, new Margins(0)));
		VerticalLayoutContainer container = new VerticalLayoutContainer();

		container.add(userId, new VerticalLayoutData(1, -1, new Margins(0, 2, 2, 0)));
		container.add(userName, new VerticalLayoutData(1, -1, new Margins(2, 2, 0, 0)));
		SimpleContainer simContainer = new SimpleContainer();
		simContainer.addStyleName("logout-button");
		Label lab = new Label("Logout");
		lab.setHeight("32px");
		simContainer.add(lab);
		lab.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				loginService.logoutUser(new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							userId.reset();
							userName.reset();
							Window.Location.reload();
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						ContentManager.getInstance().chekFailure(caught);

					}
				});
			}
		});

		container.add(simContainer, new VerticalLayoutData(1, -1, new Margins(2, 2, 0, 0)));

		container.setWidth(166);

		// simContainer.add(child)
		tbContainer.add(container, new HorizontalLayoutData(-1, 1, new Margins(10)));

		toolBar.add(tbContainer);

		toolBar.setHeaderVisible(false);
		toolBar.setBodyBorder(false);
		toolBar.setBorders(false);
		toolBar.setBodyStyleName("usercontent-header");
		toolBar.setWidth(250);
		toolBar.setHeight(100);

		return toolBar;
	}

	public void updateUserData() {
		userId.setValue(ContentManager.getInstance().getUserPerson().get(EnumPersonAttr.uid));
		userName.setValue(ContentManager.getInstance().getUserPerson().get(EnumPersonAttr.displayName));
	}
}
