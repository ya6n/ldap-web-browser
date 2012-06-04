/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
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

import fr.uparis10.miage.ldap.client.enums.EnumPersonAttrMessages;
import fr.uparis10.miage.ldap.client.resources.icons.IconsStore;
import fr.uparis10.miage.ldap.shared.enums.EnumPersonAttr;
import fr.uparis10.miage.ldap.shared.obj.Person;

//import com.extjs.gxt.ui.client.data.BaseTreeModel;
//import com.extjs.gxt.ui.client.data.ModelData;
//import com.extjs.gxt.ui.client.event.ButtonEvent;
//import com.extjs.gxt.ui.client.event.SelectionListener;
//import com.extjs.gxt.ui.client.store.TreeStore;
//import com.extjs.gxt.ui.client.widget.ContentPanel;
//import com.extjs.gxt.ui.client.widget.Layout;
//import com.extjs.gxt.ui.client.widget.button.Button;
//import com.extjs.gxt.ui.client.widget.button.ButtonBar;
//import com.extjs.gxt.ui.client.widget.layout.FlowData;
//import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

/**
 * @author iogorode
 * 
 */
public class LdapTreeScreen extends ContentPanel {

	/**
	 * 
	 */
	final Tree<TreeNodeImpl, String> tree;

	public LdapTreeScreen() {
		tree = getTreeModel();

		// final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setWidth(300);
		// tree.setDisplayProperty("name");
		// tree.getStyle().setLeafIcon(Resources.ICONS.BaseTreeModel());

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

		TreeNodeImpl model = new TreeNodeImpl(0, "root");
		// store.set("name", "root");
		store.add(model);

		TreeNodeImpl beth = new TreeNodeImpl(1, "Beethoven");
		model.addChild(beth);
		store.add(model, beth);

		TreeNodeImpl quart = new TreeNodeImpl(2, "Quartets");
		beth.addChild(quart);
		store.add(beth, quart);

		TreeNodeImpl fugue = new TreeNodeImpl(3,
		    "Grosse Fugue for String Quartet");
		quart.addChild(fugue);
		store.add(quart, fugue);

		TreeNodeImpl moz = new TreeNodeImpl(4, "Mozard");
		model.addChild(moz);
		store.add(model, moz);

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

		root.setExpanded(model, true);

		return root;
	}

	public void loadPerson(Person person) {

		TreeStore<TreeNodeImpl> store = tree.getStore();

		store.clear();

		int id = 0;
		TreeNodeImpl model = new TreeNodeImpl(id++, person.get(EnumPersonAttr.uid));

		store.add(model);

		TreeStyle treeStyle = new TreeStyle();
		// treeStyle.setJointOpenIcon(IconsStore.INSTANCE.searchImg());
		treeStyle.setNodeOpenIcon(IconsStore.INSTANCE.personIcon());
		treeStyle.setNodeCloseIcon(IconsStore.INSTANCE.personIcon());
		treeStyle.setLeafIcon(IconsStore.INSTANCE.propertyIconSmall());
		tree.setStyle(treeStyle);

		final EnumPersonAttrMessages messages = (EnumPersonAttrMessages) GWT.create(EnumPersonAttrMessages.class);
		TreeNodeImpl propertyNode;
		for (Entry<EnumPersonAttr, String> entry : person.entrySet()) {
			propertyNode = new TreeNodeImpl(id++, entry.getKey().getTitleMessage(messages) + " : " + entry.getValue());
			model.addChild(propertyNode);
			store.add(model, propertyNode);
		}

		tree.setExpanded(model, true);

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

}
