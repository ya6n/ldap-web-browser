/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 iogorode
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License v3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Creation date: 31 mai 2012
 */
package fr.uparis10.miage.ldap.client.screen;

import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;

import fr.uparis10.miage.ldap.client.enums.EnumGroupAttrMessages;
import fr.uparis10.miage.ldap.client.resources.icons.IconsStore;
import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.obj.Group;

/**
 * @author iogorode
 * 
 */
public class GroupSynthesisScreen extends VerticalLayoutContainer implements Screen {

	private static final String screenId = "GroupSynthesisScreen";

	private String title;

	private Group group;

	public static final EnumGroupAttrMessages messages;

	static {
		messages = (EnumGroupAttrMessages) GWT.create(EnumGroupAttrMessages.class);
	}

	public GroupSynthesisScreen(String title, Group group) {
		this.title = title;
		this.group = group;

		setScrollMode(ScrollMode.AUTOY);

		setAdjustForScroll(true);

		generatePanel();
	}

	private void generatePanel() {

		int countAdded = 0;

		VerticalLayoutContainer form = new VerticalLayoutContainer();

		// code example:
		for (Entry<EnumGroupAttr, String> entry : group.entrySet()) {

			final TextField textField = new TextField();
			textField.setValue(entry.getValue());
			textField.setReadOnly(true);
			final FieldLabel labTextField = new FieldLabel(textField, entry.getKey().getTitleMessage(messages));
			form.add(labTextField, new VerticalLayoutData(1, -1, new Margins(5, 0, 5, 0)));
			countAdded++;

		}
		if (countAdded != 0) {
			FieldSet general = new FieldSet();
			general.setHeadingText("Properties");
			general.add(form);
			this.add(general, new VerticalLayoutData(1, -1, new Margins(10)));
		}
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getScreenId() {
		return screenId;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	@Override
	public ImageResource getIcon() {
		return IconsStore.INSTANCE.groupIcon16();
	}

}
