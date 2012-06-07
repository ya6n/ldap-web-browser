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
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.core.shared.FastMap;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import fr.uparis10.miage.ldap.client.ContentManager;
import fr.uparis10.miage.ldap.client.messages.PeopleSearchScreenMessages;
import fr.uparis10.miage.ldap.client.resources.icons.IconsStore;
import fr.uparis10.miage.ldap.client.screen.provider.GroupModelKeyProvider;
import fr.uparis10.miage.ldap.client.screen.provider.GroupValueProvider;
import fr.uparis10.miage.ldap.client.screen.provider.OrgUnitModelKeyProvider;
import fr.uparis10.miage.ldap.client.screen.provider.OrgUnitValueProvider;
import fr.uparis10.miage.ldap.client.screen.provider.PersonModelKeyProvider;
import fr.uparis10.miage.ldap.client.screen.provider.PersonValueProvider;
import fr.uparis10.miage.ldap.client.service.GroupService;
import fr.uparis10.miage.ldap.client.service.GroupServiceAsync;
import fr.uparis10.miage.ldap.client.service.OrgUnitService;
import fr.uparis10.miage.ldap.client.service.OrgUnitServiceAsync;
import fr.uparis10.miage.ldap.client.service.PersonService;
import fr.uparis10.miage.ldap.client.service.PersonServiceAsync;
import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumOrgUnitAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.obj.Group;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;
import fr.uparis10.miage.ldap.shared.obj.Person;
import fr.uparis10.miage.ldap.shared.obj.SearchRequestModel;

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

	private final TextField searchBox;
	private final TextButton searchButton;
	private final Grid<Person> personGrid;
	private final FieldSet advancedBox;
	private final String title;

	private Grid<Group> groupGrid;
	private Grid<OrgUnit> orgUnitGrid;

	private CheckBox opPerson;
	private CheckBox opOrgUnit;
	private CheckBox opGroup;

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

				PersonServiceAsync personService = GWT.create(PersonService.class);

				String request = searchBox.getValue();
				if (request == null) {
					request = "";
				}

				final Grid<Person> resultGrid = personGrid;
				if (!advancedBox.isExpanded()
				    || (groupGrid.getSelectionModel().getSelectedItems().size() == groupGrid.getStore().size()
				        && orgUnitGrid.getSelectionModel().getSelectedItems().size() == orgUnitGrid.getStore().size() && opPerson.getValue() && opOrgUnit.getValue() && opGroup
				          .getValue())) {
					personService.searchPersons(request, new AsyncCallback<List<Person>>() {

						@Override
						public void onFailure(Throwable caught) {
							ContentManager.getInstance().chekFailure(caught);
						}

						@Override
						public void onSuccess(List<Person> result) {
							resultGrid.getStore().clear();
							resultGrid.getStore().addAll(result);
							resultGrid.getView().refresh(true);
						}
					});
				}
				else
				{
					SearchRequestModel requestModel = new SearchRequestModel();

					requestModel.setRequest(request);
					requestModel.setLookUpPerson(opPerson.getValue());
					requestModel.setLookUpOrgUnit(opOrgUnit.getValue());
					requestModel.setLookUpGroup(opGroup.getValue());

					Map<String, Boolean> groupOptions = new FastMap<Boolean>();
					for (Group group : groupGrid.getStore().getAll()) {
						groupOptions.put(group.get(EnumGroupAttr.cn), groupGrid.getSelectionModel().isSelected(group));
					}
					requestModel.setGroupOptions(groupOptions);

					Map<String, Boolean> orgUniOptions = new FastMap<Boolean>();
					for (OrgUnit orgUnit : orgUnitGrid.getStore().getAll()) {
						orgUniOptions.put(orgUnit.get(EnumOrgUnitAttr.ou), orgUnitGrid.getSelectionModel().isSelected(orgUnit));
					}
					requestModel.setOrgUnitOptions(orgUniOptions);

					personService.searchPersons(requestModel, new AsyncCallback<List<Person>>() {

						@Override
						public void onFailure(Throwable caught) {
							ContentManager.getInstance().chekFailure(caught);
						}

						@Override
						public void onSuccess(List<Person> result) {
							personGrid.getStore().clear();
							personGrid.getStore().addAll(result);
						}
					});
				}

			}
		});

		hp.add(searchButton, new HorizontalLayoutData(100, -1, new Margins(0, 0, 0, 10)));

		add(hp, new VerticalLayoutData(1, 50, new Margins(20, 0, 0, 0)));

		advancedBox = new FieldSet();

		advancedBox.setHeadingText(messages.getAdvancedBoxTitle());
		advancedBox.setTitle(messages.getAdvancedBoxTitle());
		advancedBox.setCollapsible(true);
		advancedBox.setExpanded(false);
		advancedBox.setHeight(190);

		hp = new HorizontalLayoutContainer();

		// ajout de la liste de groupes
		generateGroupGrid();
		FieldSet sc = new FieldSet();
		sc.setHeadingText(messages.getGroupListTitle());
		sc.add(groupGrid);
		groupGrid.setHeight(110);
		hp.add(sc, new HorizontalLayoutData(0.38, 150, new Margins(0, 0, 0, 0)));

		// ajout des options de recherche
		hp.add(generateOptions(), new HorizontalLayoutData(0.24, 150, new Margins(0, 10, 0, 10)));

		// ajout de la liste d'unites d'organisation
		generateOrgUnitGrid();
		sc = new FieldSet();
		sc.setHeadingText(messages.getOrgUnitListTitle());
		orgUnitGrid.setHeight(110);
		sc.add(orgUnitGrid);
		hp.add(sc, new HorizontalLayoutData(0.38, 150, new Margins(0, 0, 0, 0)));

		advancedBox.add(hp, new VerticalLayoutData(1, 1));

		add(advancedBox, new VerticalLayoutData(1, 200, new Margins(10, 0, 0, 0)));

		// ajout du Grid de resultats
		List<ColumnConfig<Person, ?>> list = new ArrayList<ColumnConfig<Person, ?>>();

		ColumnConfig<Person, ?> colConfig = new ColumnConfig<Person, String>(new PersonValueProvider(), 100, messages.getResultGridIdLabel());
		ColumnConfig<Person, ?> colConfig2 = new ColumnConfig<Person, String>(new PersonValueProvider(EnumPersonAttr.displayName), 100,
		    messages.getResultGridNameLabel());
		ColumnConfig<Person, ?> colConfig3 = new ColumnConfig<Person, String>(new PersonValueProvider(EnumPersonAttr.mail), 100, messages.getResultGridMailLabel());
		ColumnConfig<Person, ?> colConfig4 = new ColumnConfig<Person, String>(new PersonValueProvider(EnumPersonAttr.telephoneNumber), 100,
		    messages.getResultGridTelephoneLabel());
		ColumnConfig<Person, ?> colConfig5 = new ColumnConfig<Person, String>(new PersonValueProvider(EnumPersonAttr.postalAddress), 100,
		    messages.getResultGridAddressLabel());

		ContentPanel gridModel = new ContentPanel();
		ColumnModel<Person> cm = new ColumnModel<Person>(list);
		list.add(colConfig);
		list.add(colConfig2);
		list.add(colConfig3);
		list.add(colConfig4);
		list.add(colConfig5);
		personGrid = new Grid<Person>(new ListStore<Person>(new PersonModelKeyProvider()), cm);
		personGrid.getView().setForceFit(true);
		personGrid.getView().setAutoFill(true);
		personGrid.getView().setStripeRows(true);
		personGrid.getView().setColumnLines(true);
		personGrid.setWidth(400);
		personGrid.setHeight(200);

		personGrid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				Person person = personGrid.getStore().get(event.getRowIndex());
				ContentManager.getInstance().getContainer().openScreen(new PeopleSynthesisScreen(person.get(EnumPersonAttr.uid), person));
			}
		});

		personGrid.addRowClickHandler(new RowClickHandler() {
			@Override
			public void onRowClick(RowClickEvent event) {
				Person person = personGrid.getStore().get(event.getRowIndex());
				ContentManager.getInstance().getLdapTreeScreen().loadPerson(person);
			}
		});

		gridModel.add(personGrid);
		gridModel.setHeadingText(messages.getResultGridTitle());
		add(gridModel, new VerticalLayoutData(1, 300, new Margins(10, 0, 0, 0)));
	}

	private void generateOrgUnitGrid() {
		List<ColumnConfig<OrgUnit, ?>> list = new ArrayList<ColumnConfig<OrgUnit, ?>>();
		IdentityValueProvider<OrgUnit> identity = new IdentityValueProvider<OrgUnit>();
		final CheckBoxSelectionModel<OrgUnit> checkBoxSM = new CheckBoxSelectionModel<OrgUnit>(identity);
		ColumnConfig<OrgUnit, ?> colConfig = new ColumnConfig<OrgUnit, String>(new OrgUnitValueProvider(), 100, "OU");
		ColumnConfig<OrgUnit, ?> colConfig2 = new ColumnConfig<OrgUnit, String>(new OrgUnitValueProvider(EnumOrgUnitAttr.description), 100, "Description");

		list.add(checkBoxSM.getColumn());
		list.add(colConfig);
		list.add(colConfig2);
		ColumnModel<OrgUnit> colModel = new ColumnModel<OrgUnit>(list);
		orgUnitGrid = new Grid<OrgUnit>(new ListStore<OrgUnit>(new OrgUnitModelKeyProvider()), colModel);
		checkBoxSM.setSelectionMode(SelectionMode.SIMPLE);

		orgUnitGrid.setSelectionModel(checkBoxSM);
		checkBoxSM.setLocked(false);
		orgUnitGrid.getView().setAutoExpandColumn(colConfig);
		orgUnitGrid.setBorders(false);
		orgUnitGrid.getView().setForceFit(true);
		orgUnitGrid.getView().setStripeRows(true);
		orgUnitGrid.getView().setColumnLines(true);

		orgUnitGrid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				OrgUnit orgUnit = orgUnitGrid.getStore().get(event.getRowIndex());
				ContentManager.getInstance().getContainer().openScreen(new OrgUnitSynthesisScreen(orgUnit.get(EnumOrgUnitAttr.ou), orgUnit));
			}
		});

		checkBoxSM.selectAll();

	}

	private void generateGroupGrid() {
		List<ColumnConfig<Group, ?>> list = new ArrayList<ColumnConfig<Group, ?>>();
		IdentityValueProvider<Group> identity = new IdentityValueProvider<Group>();
		final CheckBoxSelectionModel<Group> checkBoxSM = new CheckBoxSelectionModel<Group>(identity);
		ColumnConfig<Group, ?> colConfig = new ColumnConfig<Group, String>(new GroupValueProvider(), 100, "CN");
		ColumnConfig<Group, ?> colConfig2 = new ColumnConfig<Group, String>(new GroupValueProvider(EnumGroupAttr.businessCategory), 100, "Business Category");

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

		groupGrid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				Group group = groupGrid.getStore().get(event.getRowIndex());
				ContentManager.getInstance().getContainer().openScreen(new GroupSynthesisScreen(group.get(EnumGroupAttr.cn), group));
			}
		});

	}

	private Widget generateOptions() {
		opPerson = new CheckBox();
		opOrgUnit = new CheckBox();
		opGroup = new CheckBox();

		opPerson.setValue(true);
		opOrgUnit.setValue(true);
		opGroup.setValue(true);

		opPerson.setBoxLabel(messages.getLookUpPeopleLabel());
		opOrgUnit.setBoxLabel(messages.getLookUpOrgUnitLabel());
		opGroup.setBoxLabel(messages.getLookUpGroupLabel());

		FieldSet result = new FieldSet();
		VerticalLayoutContainer vc = new VerticalLayoutContainer();
		vc.add(opPerson);
		vc.add(opOrgUnit);
		vc.add(opGroup);
		result.add(vc);

		result.setHeadingText(messages.getLookUpOptionsTitle());

		return result;
	}

	public void selectAll() {
		groupGrid.getSelectionModel().selectAll();
		orgUnitGrid.getSelectionModel().selectAll();

		GroupServiceAsync groupService = GWT.create(GroupService.class);
		groupService.getGroupsAll(new AsyncCallback<List<Group>>() {

			@Override
			public void onFailure(Throwable caught) {
				ContentManager.getInstance().chekFailure(caught);

			}

			@Override
			public void onSuccess(List<Group> result) {
				groupGrid.getStore().clear();
				groupGrid.getStore().addAll(result);
				groupGrid.getSelectionModel().selectAll();
			}

		});

		OrgUnitServiceAsync orgUnitService = GWT.create(OrgUnitService.class);
		orgUnitService.getOrgUnitsAll(new AsyncCallback<List<OrgUnit>>() {

			@Override
			public void onFailure(Throwable caught) {
				ContentManager.getInstance().chekFailure(caught);

			}

			@Override
			public void onSuccess(List<OrgUnit> result) {
				orgUnitGrid.getStore().clear();
				orgUnitGrid.getStore().addAll(result);
				orgUnitGrid.getSelectionModel().selectAll();
			}

		});

	}

	@Override
	public String getScreenId() {
		return screenId;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public ImageResource getIcon() {
		return IconsStore.INSTANCE.searchIcon();
	}

	public Person getSelectedPerson() {
		return personGrid.getSelectionModel().getSelectedItem();
	}

}
