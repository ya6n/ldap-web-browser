/**
 * 
 */
package fr.uparis10.miage.ldap.client.screen;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Layout;

/**
 * @author iogorode
 * 
 */
public class ApplicationHeader extends ContentPanel {

	/**
	 * 
	 */
	public ApplicationHeader() {
		setHeaderVisible(false);
	}

	/**
	 * @param layout
	 */
	public ApplicationHeader(Layout layout) {
		this();
		setLayout(layout);
	}

}
