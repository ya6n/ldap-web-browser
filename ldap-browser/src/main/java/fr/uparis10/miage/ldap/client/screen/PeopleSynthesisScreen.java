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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;

import fr.uparis10.miage.ldap.client.enums.EnumPersonAttrMessages;
import fr.uparis10.miage.ldap.client.resources.icons.IconsStore;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author iogorode
 * 
 */
public class PeopleSynthesisScreen extends VerticalLayoutContainer implements Screen {

	private static final String screenId = "PeopleSynthesisScreen";

	private String title;

	private Person person;

	public static final EnumPersonAttrMessages messages;

	static {
		messages = (EnumPersonAttrMessages) GWT.create(EnumPersonAttrMessages.class);
	}

	public PeopleSynthesisScreen(String title, Person person) {
		this.title = title;
		this.person = person;

		setScrollMode(ScrollMode.AUTOY);

		setAdjustForScroll(true);

		generatePersonPanel();
		generateOrgpersPanel();
		generateEdupersPanel();
		generateInetorgPanel();
		generateSupannPanel();
	}

	private void generatePersonPanel() {

		int countAdded = 0;

		VerticalLayoutContainer[] form = new VerticalLayoutContainer[2];
		form[0] = new VerticalLayoutContainer();
		form[1] = new VerticalLayoutContainer();

		// l'index qui basqule entre 0 et 1
		int p = 0;

		// code example:
		for (EnumPersonAttr property : EnumPersonAttr.getEnumsForFieldSetId(1)) {

			final String locValue = person.get(property);
			if (null != locValue) {
				final TextField textField = new TextField();
				textField.setValue(locValue);
				textField.setReadOnly(true);
				final FieldLabel labTextField = new FieldLabel(textField, property.getTitleMessage(messages));
				form[p].add(labTextField, new VerticalLayoutData(1, -1, new Margins(5, 0, 5, 0)));
				p = 1 - p;
				countAdded++;
			}
		}
		if (countAdded != 0) {
			FieldSet general = new FieldSet();
			general.setHeadingText("Person Peroperties");
			ContentPanel vcontainer = new ContentPanel();
			HorizontalLayoutContainer container = new HorizontalLayoutContainer();
			container.add(form[0], new HorizontalLayoutData(0.5, -1, new Margins(0)));
			container.add(form[1], new HorizontalLayoutData(0.5, -1, new Margins(0)));
			vcontainer.add(container);
			general.add(vcontainer);
			this.add(general, new VerticalLayoutData(1, -1, new Margins(10)));
		}
	}

	private void generateOrgpersPanel() {

		int countAdded = 0;

		VerticalLayoutContainer form = new VerticalLayoutContainer();

		// code example:
		for (EnumPersonAttr property : EnumPersonAttr.getEnumsForFieldSetId(2)) {

			final String locValue = person.get(property);
			if (null != locValue) {
				final TextField textField = new TextField();
				textField.setValue(locValue);
				textField.setReadOnly(true);
				final FieldLabel labTextField = new FieldLabel(textField, property.getTitleMessage(messages));
				form.add(labTextField, new VerticalLayoutData(1, -1, new Margins(5, 0, 5, 0)));
				countAdded++;
			}
		}

		if (countAdded != 0) {
			FieldSet general = new FieldSet();
			general.setHeadingText("Orgpers Peroperties");
			general.add(form);
			this.add(general, new VerticalLayoutData(1, -1, new Margins(10)));
		}
	}

	private void generateEdupersPanel() {

		int countAdded = 0;

		VerticalLayoutContainer form = new VerticalLayoutContainer();

		// code example:
		for (EnumPersonAttr property : EnumPersonAttr.getEnumsForFieldSetId(3)) {

			final String locValue = person.get(property);
			if (null != locValue) {
				final TextField textField = new TextField();
				textField.setValue(locValue);
				textField.setReadOnly(true);
				final FieldLabel labTextField = new FieldLabel(textField, property.getTitleMessage(messages));
				form.add(labTextField, new VerticalLayoutData(1, -1, new Margins(5, 0, 5, 0)));
				countAdded++;
			}
		}

		if (countAdded != 0) {
			FieldSet general = new FieldSet();
			general.setHeadingText("Edupers Peroperties");
			general.add(form);
			this.add(general, new VerticalLayoutData(1, -1, new Margins(10)));
		}
	}

	private void generateInetorgPanel() {

		int countAdded = 0;

		VerticalLayoutContainer form = new VerticalLayoutContainer();

		// code example:
		for (EnumPersonAttr property : EnumPersonAttr.getEnumsForFieldSetId(4)) {

			final String locValue = person.get(property);
			if (null != locValue) {
				final TextField textField = new TextField();
				textField.setValue(locValue);
				textField.setReadOnly(true);
				final FieldLabel labTextField = new FieldLabel(textField, property.getTitleMessage(messages));
				form.add(labTextField, new VerticalLayoutData(1, -1, new Margins(5, 0, 5, 0)));
				countAdded++;
			}
		}

		if (countAdded != 0) {
			FieldSet general = new FieldSet();
			general.setHeadingText("Inetorg Peroperties");
			general.add(form);
			this.add(general, new VerticalLayoutData(1, -1, new Margins(10)));
		}
	}

	private void generateSupannPanel() {

		int countAdded = 0;

		VerticalLayoutContainer form = new VerticalLayoutContainer();

		// code example:
		for (EnumPersonAttr property : EnumPersonAttr.getEnumsForFieldSetId(5)) {

			final String locValue = person.get(property);
			if (null != locValue) {
				final TextField textField = new TextField();
				textField.setValue(locValue);
				textField.setReadOnly(true);
				final FieldLabel labTextField = new FieldLabel(textField, property.getTitleMessage(messages));
				form.add(labTextField, new VerticalLayoutData(1, -1, new Margins(5, 0, 5, 0)));
				countAdded++;
			}
		}

		if (countAdded != 0) {
			FieldSet general = new FieldSet();
			general.setHeadingText("Supann Peroperties");
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
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	@Override
	public ImageResource getIcon() {
		return IconsStore.INSTANCE.personIcon();
	}

}
