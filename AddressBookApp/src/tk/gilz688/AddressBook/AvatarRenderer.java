package tk.gilz688.AddressBook;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import tk.gilz688.AddressBook.Person.Sex;

public class AvatarRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 7574697667493068376L;
	private Icon maleIcon;
	private Icon femaleIcon;
	
	public AvatarRenderer(){
		loadResources();
	}
	
	/**
	 *  Load image resources
	 */
    private void loadResources() {
    	URL maleIconUrl = AddressBookApp.class.getResource("/res/male_icon.png");
    	URL femaleIconUrl = AddressBookApp.class.getResource("/res/female_icon.png");
		maleIcon = new ImageIcon(maleIconUrl);
		femaleIcon = new ImageIcon(femaleIconUrl);
	}

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        if(value instanceof Sex){
        	Sex sex = (Sex) value;
        	if(sex.equals(Sex.MALE))
        		label.setIcon(maleIcon);
        	else if(sex.equals(Sex.FEMALE))
        		label.setIcon(femaleIcon);
        	label.setText("");
        }
        return label;
    }
}
