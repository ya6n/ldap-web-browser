/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import fr.uparis10.miage.ldap.client.ContentManager;
import fr.uparis10.miage.ldap.client.resources.icons.IconsStore;

/**
 * @author iogorode
 * 
 */
public class ApplicationHeader extends BorderLayoutContainer {

	private Image logo;

	private ToolBar info;

	private ToolBar menu;

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

	private ToolBar getInfo() {
		ToolBar toolBar = new ToolBar();

		TextButton btn = new TextButton("Logout", IconsStore.INSTANCE.logoutImg());
		btn.setScale(ButtonScale.LARGE);
		btn.setIconAlign(IconAlign.TOP);
		btn.setArrowAlign(ButtonArrowAlign.BOTTOM);

		btn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				// logout code
				// replace logout (label & image) by login (label & image)

			}
		});
		toolBar.add(btn);

		return toolBar;
	}
}
