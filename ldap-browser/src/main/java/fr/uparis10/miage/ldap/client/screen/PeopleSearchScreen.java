/**
 * This file is part of LDAP Web Browser project
 * Copyright (C) 2012 gorodenco
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
 * Creation date: 27 mai 2012
 */
package fr.uparis10.miage.ldap.client.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import fr.uparis10.miage.ldap.client.resources.messages.PeopleSearchScreenMessages;
import fr.uparis10.miage.ldap.client.screen.provider.GroupModelKeyProvider;
import fr.uparis10.miage.ldap.client.screen.provider.GroupValueProvider;
import fr.uparis10.miage.ldap.client.screen.provider.OrgUnitModelKeyProvider;
import fr.uparis10.miage.ldap.client.screen.provider.OrgUnitValueProvider;
import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.obj.Group;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;

/**
 * @author gorodenco
 * 
 */
public class PeopleSearchScreen extends VerticalLayoutContainer implements Screen {

	private static final String screenId = "PeopleSearch";

	public static final PeopleSearchScreenMessages messages;

	static {
		messages = (PeopleSearchScreenMessages) GWT.create(PeopleSearchScreenMessages.class);
	}

	TextField searchBox;

	TextButton searchButton;

	Grid<Group> groupGrid;

	Grid<OrgUnit> orgUnitGrid;

	String title;

	public PeopleSearchScreen() {

		title = messages.getTitle();

		setScrollMode(ScrollMode.AUTOY);

		getElement().setPadding(new Padding(10));

		setAdjustForScroll(true);

		searchBox = new TextField();

		HorizontalLayoutContainer hp = new HorizontalLayoutContainer();
		hp.add(new FieldLabel(searchBox, messages.getTextFieldLabel()), new HorizontalLayoutData(-110, -1));

		searchButton = new TextButton(messages.getSearchButtonLabel());

		searchButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				// TODO Auto-generated method stub

			}
		});

		hp.add(searchButton, new HorizontalLayoutData(100, -1, new Margins(0, 0, 0, 10)));

		add(hp, new VerticalLayoutData(1, 50, new Margins(20, 0, 0, 0)));

		FieldSet advancedBox = new FieldSet();

		advancedBox.setHeadingText(messages.getAdvancedBoxTitle());
		advancedBox.setTitle(messages.getAdvancedBoxTitle());
		advancedBox.setCollapsible(true);
		advancedBox.setExpanded(false);
		advancedBox.setHeight(190);

		hp = new HorizontalLayoutContainer();

		// ajout de la liste de groupes
		generateGroupGrid();
		FieldLabel fl = new FieldLabel(groupGrid, messages.getGroupListLabel());
		fl.setLabelAlign(LabelAlign.TOP);
		hp.add(fl, new HorizontalLayoutData(0.33, 140, new Margins(0, 0, 0, 0)));

		// ajout de la liste d'unites d'organisation
		generateOrgUnitGrid();
		fl = new FieldLabel(orgUnitGrid, messages.getOrgUnitListLabel());
		fl.setLabelAlign(LabelAlign.TOP);
		hp.add(fl, new HorizontalLayoutData(0.33, 140, new Margins(0, 0, 0, 0)));

		advancedBox.add(hp, new VerticalLayoutData(1, 1));

		add(advancedBox, new VerticalLayoutData(1, 200, new Margins(10, 0, 0, 0)));

		// ajout du Grid de resultats
		List<ColumnConfig<Map<String, String>, ?>> list = new ArrayList<ColumnConfig<Map<String, String>, ?>>();

		ColumnConfig<Map<String, String>, ?> colConfig = new ColumnConfig<Map<String, String>, String>(new ValueProvider<Map<String, String>, String>() {

			@Override
			public String getValue(Map<String, String> object) {
				return object.get("value");
			}

			@Override
			public void setValue(Map<String, String> object, String value) {
				object.put("value", value);
			}

			@Override
			public String getPath() {
				return "value";
			}
		}, 100, "Test");

		ContentPanel gridModel = new ContentPanel();
		ColumnModel<Map<String, String>> cm = new ColumnModel<Map<String, String>>(list);
		list.add(colConfig);
		Grid<Map<String, String>> grid = new Grid<Map<String, String>>(new ListStore<Map<String, String>>(new ModelKeyProvider<Map<String, String>>() {

			@Override
			public String getKey(Map<String, String> item) {
				return item.get("value");
			}
		}), cm);
		grid.getView().setForceFit(true);
		grid.getView().setAutoFill(true);
		grid.setWidth(400);
		grid.setHeight(200);
		gridModel.add(grid);
		gridModel.setHeadingText("Test Grid");
		add(gridModel, new VerticalLayoutData(1, 300, new Margins(10, 0, 0, 0)));
	}

	private void generateOrgUnitGrid() {
		List<ColumnConfig<OrgUnit, ?>> list = new ArrayList<ColumnConfig<OrgUnit, ?>>();
		ColumnConfig<OrgUnit, ?> colConfig = new ColumnConfig<OrgUnit, String>(new OrgUnitValueProvider(), 100, "OU");
		list.add(colConfig);
		ColumnModel<OrgUnit> colModel = new ColumnModel<OrgUnit>(list);
		orgUnitGrid = new Grid<OrgUnit>(new ListStore<OrgUnit>(new OrgUnitModelKeyProvider()), colModel);
		CheckBoxSelectionModel<OrgUnit> checkBoxSM = new CheckBoxSelectionModel<OrgUnit>(new IdentityValueProvider<OrgUnit>());
		checkBoxSM.setSelectionMode(SelectionMode.MULTI);
		orgUnitGrid.setSelectionModel(checkBoxSM);
	}

	private void generateGroupGrid() {
		List<ColumnConfig<Group, ?>> list = new ArrayList<ColumnConfig<Group, ?>>();
		IdentityValueProvider<Group> identity = new IdentityValueProvider<Group>();
		final CheckBoxSelectionModel<Group> checkBoxSM = new CheckBoxSelectionModel<Group>(identity);
		ColumnConfig<Group, ?> colConfig = new ColumnConfig<Group, String>(new GroupValueProvider(), 100, "CN");
		ColumnConfig<Group, ?> colConfig2 = new ColumnConfig<Group, String>(new ValueProvider<Group, String>() {

			@Override
			public String getValue(Group object) {
				return object.get(EnumGroupAttr.businessCategory);
			}

			@Override
			public void setValue(Group object, String value) {
				object.put(EnumGroupAttr.businessCategory, value);
			}

			@Override
			public String getPath() {
				return EnumGroupAttr.businessCategory.name();
			}
		}, 100, "Business Category");

		list.add(checkBoxSM.getColumn());
		list.add(colConfig);
		list.add(colConfig2);
		ColumnModel<Group> colModel = new ColumnModel<Group>(list);
		groupGrid = new Grid<Group>(new ListStore<Group>(new GroupModelKeyProvider()), colModel);
		checkBoxSM.setSelectionMode(SelectionMode.SIMPLE);

		groupGrid.setSelectionModel(checkBoxSM);
		checkBoxSM.setLocked(false);
		groupGrid.getView().setAutoExpandColumn(colConfig);
		groupGrid.setBorders(false);
		groupGrid.getView().setForceFit(true);
		groupGrid.getView().setStripeRows(true);
		groupGrid.getView().setColumnLines(true);

		Group group = new Group();
		group.put(EnumGroupAttr.cn, "étudiant");
		group.put(EnumGroupAttr.businessCategory, "1111");
		groupGrid.getStore().add(group);
		group = new Group();
		group.put(EnumGroupAttr.cn, "enseignant");
		group.put(EnumGroupAttr.businessCategory, "2222");
		groupGrid.getStore().add(group);
		group = new Group();
		group.put(EnumGroupAttr.cn, "employé");
		group.put(EnumGroupAttr.businessCategory, "3333");
		groupGrid.getStore().add(group);

		groupGrid.getView().refresh(true);
	}

	@Override
	public String getScreenId() {
		return screenId;
	}

	@Override
	public String getTitle() {
		return title;
	}

}
