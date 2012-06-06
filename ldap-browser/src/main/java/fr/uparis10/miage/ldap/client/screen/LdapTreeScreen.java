/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;

import fr.uparis10.miage.ldap.client.ContentManager;
import fr.uparis10.miage.ldap.client.enums.EnumPersonAttrMessages;
import fr.uparis10.miage.ldap.client.resources.icons.IconsStore;
import fr.uparis10.miage.ldap.client.service.GroupService;
import fr.uparis10.miage.ldap.client.service.GroupServiceAsync;
import fr.uparis10.miage.ldap.client.service.OrgUnitService;
import fr.uparis10.miage.ldap.client.service.OrgUnitServiceAsync;
import fr.uparis10.miage.ldap.shared.enums.EnumGroupAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumOrgUnitAttr;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.obj.Group;
import fr.uparis10.miage.ldap.shared.obj.OrgUnit;
import fr.uparis10.miage.ldap.shared.obj.Person;

/**
 * @author iogorode
 * 
 */
public class LdapTreeScreen extends ContentPanel {

	/**
	 * 
	 */
	final Tree<TreeNodeImpl, String> tree;

	int lastIdOffset;

	String currentPersonId;

	public LdapTreeScreen() {
		tree = getTreeModel();

		tree.setWidth(300);

		ButtonBar buttonBar = new ButtonBar();

		buttonBar.add(new TextButton("Expand All", new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				tree.expandAll();

			}
		}));
		buttonBar.add(new TextButton("Collapse All", new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				tree.collapseAll();
			}
		}));

		add(buttonBar, new MarginData(10));
		add(tree, new MarginData(10));

		tree.expandAll();
	}

	private Tree<TreeNodeImpl, String> getTreeModel() {

		TreeStore<TreeNodeImpl> store = new TreeStore<TreeNodeImpl>(
		    new ModelKeyProvider<TreeNodeImpl>() {
			    @Override
			    public String getKey(TreeNodeImpl item) {
				    return item.getId() + "";
			    }
		    });

		Tree<TreeNodeImpl, String> root = new Tree<TreeNodeImpl, String>(store,
		    new ValueProvider<TreeNodeImpl, String>() {

			    @Override
			    public String getValue(TreeNodeImpl object) {
				    return object.getName();
			    }

			    @Override
			    public void setValue(TreeNodeImpl object, String value) {
			    }

			    @Override
			    public String getPath() {
				    return "name";
			    }
		    });

		return root;
	}

	public void loadPerson(final Person person) {

		if (person.get(EnumPersonAttr.uid).equals(currentPersonId)) {
			return;
		}
		final TreeStore<TreeNodeImpl> store = tree.getStore();

		final StoreSortInfo<TreeNodeImpl> sortInfo = new StoreSortInfo<TreeNodeImpl>(new Comparator<TreeNodeImpl>() {

			@Override
			public int compare(TreeNodeImpl o1, TreeNodeImpl o2) {
				int size1 = o1.getChildren().size();
				int size2 = o2.getChildren().size();
				if (size1 > 0 && size2 == 0) {
					return -1;
				} else if (size1 == 0 && size2 > 0) {
					return 1;
				}
				else
					return o1.getName().compareTo(o2.getName());
			}
		}, SortDir.ASC);

		store.clear();

		int id = 0;
		final TreeNodeImpl model = new TreeNodeImpl(id++, person.get(EnumPersonAttr.uid));

		store.add(model);

		TreeStyle treeStyle = new TreeStyle();
		// treeStyle.setJointOpenIcon(IconsStore.INSTANCE.searchImg());
		treeStyle.setNodeOpenIcon(IconsStore.INSTANCE.personIcon());
		treeStyle.setNodeCloseIcon(IconsStore.INSTANCE.personIcon());
		treeStyle.setLeafIcon(IconsStore.INSTANCE.propertyIconSmall());
		tree.setStyle(treeStyle);

		final EnumPersonAttrMessages messages = (EnumPersonAttrMessages) GWT.create(EnumPersonAttrMessages.class);

		currentPersonId = person.get(EnumPersonAttr.uid);
		tree.setExpanded(model, true);
		this.lastIdOffset = id;

		// Ne pas oublier qu'il y a qu'un seul Thread en JavaScript
		final LdapTreeScreen thisScreen = this;
		GroupServiceAsync groupService = GWT.create(GroupService.class);
		final OrgUnitServiceAsync orgUnitService = GWT.create(OrgUnitService.class);
		groupService.getPersonGroups(person.get(EnumPersonAttr.uid), new AsyncCallback<List<Group>>() {

			@Override
			public void onSuccess(List<Group> result) {
				if (currentPersonId != null && currentPersonId.equals(person.get(EnumPersonAttr.uid))) {
					int id = thisScreen.getLastIdOffset();
					thisScreen.setLastIdOffset(id + 1 + result.size());

					TreeNodeImpl groupsNode = new TreeNodeImpl(id++, "Groups");
					store.add(model, groupsNode);
					TreeNodeImpl groupNode;
					for (Group group : result) {
						groupNode = new TreeNodeImpl(id++, group.get(EnumGroupAttr.cn));
						groupsNode.addChild(groupNode);
						store.add(groupsNode, groupNode);
					}

					orgUnitService.getPersonOrgUnits(person.get(EnumPersonAttr.supannEntiteAffectation), person.get(EnumPersonAttr.supannEntiteAffectationPrincipale),
					    new AsyncCallback<List<OrgUnit>>() {

						    @Override
						    public void onSuccess(List<OrgUnit> result) {
							    if (currentPersonId != null && currentPersonId.equals(person.get(EnumPersonAttr.uid))) {
								    int id = thisScreen.getLastIdOffset();
								    thisScreen.setLastIdOffset(id + 1 + result.size());

								    TreeNodeImpl orgUnitsNode = new TreeNodeImpl(id++, "OrgUnits");
								    store.add(model, orgUnitsNode);
								    TreeNodeImpl orgUnitNode;
								    for (OrgUnit orgUnit : result) {
									    orgUnitNode = new TreeNodeImpl(id++, "(" + orgUnit.get(EnumOrgUnitAttr.ou) + ") " + orgUnit.get(EnumOrgUnitAttr.description));
									    orgUnitsNode.addChild(orgUnitNode);
									    store.add(orgUnitsNode, orgUnitNode);
								    }

								    TreeNodeImpl propertyNode;
								    for (Entry<EnumPersonAttr, String> entry : person.entrySet()) {
									    propertyNode = new TreeNodeImpl(id++, entry.getKey().getTitleMessage(messages) + " : " + entry.getValue());
									    model.addChild(propertyNode);
									    store.add(model, propertyNode);
								    }
								    tree.setExpanded(model, true);

								    // store.addSortInfo(sortInfo);
							    }
						    }

						    @Override
						    public void onFailure(Throwable caught) {
							    ContentManager.getInstance().chekFailure(caught);

						    }
					    });

				}
			}

			@Override
			public void onFailure(Throwable caught) {
				ContentManager.getInstance().chekFailure(caught);

			}
		});

	}

	public class TreeNodeImpl implements Serializable,
	    TreeStore.TreeNode<TreeNodeImpl> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Integer id;
		private String name;
		private List<TreeNodeImpl> children;

		protected TreeNodeImpl() {
			children = new ArrayList<TreeNodeImpl>();
		}

		public TreeNodeImpl(Integer id, String name) {
			this();
			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public TreeNodeImpl getData() {
			return this;
		}

		@Override
		public List<? extends TreeNode<TreeNodeImpl>> getChildren() {
			return children;
		}

		public void addChild(TreeNodeImpl child) {
			children.add(child);
		}

		@Override
		public String toString() {
			return name != null ? name : super.toString();
		}

	}

	/**
	 * @return the lastId
	 */
	public int getLastIdOffset() {
		return lastIdOffset;
	}

	/**
	 * @param lastId
	 *          the lastId to set
	 */
	public void setLastIdOffset(int lastIdOffset) {
		this.lastIdOffset = lastIdOffset;
	}

}
