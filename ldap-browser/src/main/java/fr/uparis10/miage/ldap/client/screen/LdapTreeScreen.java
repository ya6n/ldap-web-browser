/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	public LdapTreeScreen() {
		final Tree<TreeNodeImpl, String> tree = getTreeModel();

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
		TreeNodeImpl quart = new TreeNodeImpl(2, "Quartets");
		beth.addChild(quart);

		TreeNodeImpl fugue = new TreeNodeImpl(3,
				"Grosse Fugue for String Quartet");
		quart.addChild(fugue);

		TreeNodeImpl moz = new TreeNodeImpl(4, "Mozard");
		model.addChild(moz);

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
