package tk.gilz688.AddressBook;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBoxMenuItem;

/**
 *  Group that manages JCheckBoxMenuItems
 */
public class JCheckBoxMenuItemGroup implements ItemListener {
	private Set<JCheckBoxMenuItem> items = new HashSet<JCheckBoxMenuItem>();

	/**
	 *  Add JCheckBoxMenuItem to the JCheckBoxMenuItemGroup
	 */
	public void add(JCheckBoxMenuItem cbmi) {
		cbmi.addItemListener(this);
		cbmi.setState(false);
		items.add(cbmi);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {

			JCheckBoxMenuItem itemAffected = (JCheckBoxMenuItem) e.getItem();
			for (JCheckBoxMenuItem item : items) {
				if (item != itemAffected)
					item.setState(false);
			}
		}
	}

	public void selectItem(JCheckBoxMenuItem itemToSelect) {
		for (JCheckBoxMenuItem item : items) {
			item.setState(item == itemToSelect);
		}
	}

	public JCheckBoxMenuItem getSelectedItem() {
		for (JCheckBoxMenuItem item : items) {
			if (item.getState())
				return item;
		}
		return null;
	}
}