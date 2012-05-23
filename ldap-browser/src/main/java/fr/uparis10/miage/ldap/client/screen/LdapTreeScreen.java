/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

/**
 * @author iogorode
 * 
 */
public class LdapTreeScreen extends ContentPanel {

	/**
	 * 
	 */
	public LdapTreeScreen() {
		BaseTreeModel model = getTreeModel();

		TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add(model.getChildren(), true);

		final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setWidth(300);
		tree.setDisplayProperty("name");
		// tree.getStyle().setLeafIcon(Resources.ICONS.BaseTreeModel());

		ButtonBar buttonBar = new ButtonBar();

		buttonBar.add(new Button("Expand All",
				new SelectionListener<ButtonEvent>() {
					public void componentSelected(ButtonEvent ce) {
						tree.expandAll();
					}
				}));
		buttonBar.add(new Button("Collapse All",
				new SelectionListener<ButtonEvent>() {
					public void componentSelected(ButtonEvent ce) {
						tree.collapseAll();
					}
				}));

		add(buttonBar, new FlowData(10));
		add(tree, new FlowData(10));
	}

	/**
	 * @param layout
	 */
	public LdapTreeScreen(Layout layout) {
		this();
		setLayout(layout);
	}

	private BaseTreeModel getTreeModel() {

		BaseTreeModel root = new BaseTreeModel();
		root.set("name", "root");
		BaseTreeModel model;

		BaseTreeModel beth = new BaseTreeModel();
		beth.set("name", "Beethoven");
		root.add(beth);
		BaseTreeModel quart = new BaseTreeModel();
		quart.set("name", "Quartets");
		beth.add(quart);

		model = new BaseTreeModel();
		model.set("name", "Grosse Fugue for String Quartet");
		quart.add(model);

		model = new BaseTreeModel();
		model.set("name", "Mozard");
		root.add(model);

		return root;
	}
}
