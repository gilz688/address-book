package tk.gilz688.AddressBook;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import tk.gilz688.AddressBook.Person.Sex;

/**
	Dialog for creating or editing an existing <b>Person</b>
*/
public class PersonDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = -7418526799025380346L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFirstName;
	private JTextField tfMiddleName;
	private JTextField tfLastName;
	private JTextField tfTitle;
	private JTextField tfAddress;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;
	private JTextField tfContactNumber;
	private Person person;
	private OKButtonClickListener listener;
	private JComboBox<String> cbCountry;
	private JComboBox<Sex> cbSex;
	private Vector<String> countries;

	/**
 		Set the listener for the OK Button
	*/
	public void setOKButtonClickListener(OKButtonClickListener listener) {
		this.listener = listener;
	}

	/**
	 	Create and show the "Create/Edit Person" dialog.
	*/
	public static void createDialog(Component parent, Person person, OKButtonClickListener listener){
		try {
			PersonDialog dialog = new PersonDialog(person);
			dialog.setOKButtonClickListener(listener);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setPreferredSize(new Dimension(400, 220));
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setLocationRelativeTo(parent);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 	Create the dialog.
	*/
	public PersonDialog(Person aPerson) {

		setBounds(100, 100, 450, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblFirstName = new JLabel("First Name:");
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("Middle Name:");
		
		tfMiddleName = new JTextField();
		tfMiddleName.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblTitle = new JLabel("Title:");
		contentPanel.setLayout(new MigLayout("", "[128.00px][311px,grow]", "[20px][20px][19px][20px][20px][20px][20px][][20px][21.00px][20px]"));
		
		tfTitle = new JTextField();
		contentPanel.add(tfTitle, "cell 1 0,alignx left");
		tfTitle.setColumns(10);
		contentPanel.add(lblMiddleName, "cell 0 2,alignx left,aligny center");
		contentPanel.add(lblFirstName, "cell 0 1,alignx left,aligny center");
		contentPanel.add(tfMiddleName, "cell 1 2,growx,aligny top");
		
		JLabel lblLastName = new JLabel("Last Name:");
		contentPanel.add(lblLastName, "cell 0 3,alignx left");
		contentPanel.add(tfLastName, "cell 1 3,growx,aligny top");
		contentPanel.add(tfFirstName, "cell 1 1,growx,aligny top");
		
		JLabel lblSex = new JLabel("Sex:");
		contentPanel.add(lblSex, "cell 0 4,alignx left");
		
		cbSex = new JComboBox<Sex>();
		contentPanel.add(cbSex, "cell 1 4,alignx left");
		cbSex.setModel(new DefaultComboBoxModel<Sex>(Sex.values()));
		
		JLabel lblAddress = new JLabel("Address:");
		contentPanel.add(lblAddress, "cell 0 5,alignx left");
		
		tfAddress = new JTextField();
		contentPanel.add(tfAddress, "cell 1 5,growx");
		tfAddress.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		contentPanel.add(lblCity, "cell 0 6,alignx left");
		
		tfCity = new JTextField();
		contentPanel.add(tfCity, "cell 1 6,growx");
		tfCity.setColumns(10);
		
		JLabel lblProvince = new JLabel("State/Province:");
		contentPanel.add(lblProvince, "cell 0 7,alignx left");
		
		tfState = new JTextField();
		contentPanel.add(tfState, "cell 1 7,growx");
		tfState.setColumns(10);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		contentPanel.add(lblZipCode, "cell 0 8,alignx left");
		
		tfZipCode = new JTextField();
		contentPanel.add(tfZipCode, "cell 1 8,alignx left");
		tfZipCode.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		contentPanel.add(lblCountry, "cell 0 9");
		
		cbCountry = new JComboBox<String>();
		try {
			countries = readCountries();
			cbCountry.setModel(new DefaultComboBoxModel<String>(countries));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		contentPanel.add(cbCountry, "flowx,cell 1 9");
		contentPanel.add(separator, "cell 1 9,alignx center,aligny top");
		contentPanel.add(lblTitle, "cell 0 0,alignx left,aligny top");
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		contentPanel.add(lblContactNumber, "cell 0 10,alignx left");
		
		tfContactNumber = new JTextField();
		contentPanel.add(tfContactNumber, "cell 1 10,growx,aligny top");
		tfContactNumber.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if(aPerson == null){
			person = new Person();
			setTitle("Enter new Person");
		}
		else{
			person = aPerson;
			updateDialog();
			setTitle("Edit Person");
		}
	}
	
	/**
		Read the list of countries from a text file resource
		@return list of countries
	*/
	public Vector<String> readCountries() throws IOException{
		Vector<String> countries = new Vector<String>();
		countries.add("Select a country");
		URL countriesUrl = AddressBookApp.class.getResource("/res/countries.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(countriesUrl.openStream()));
		String line;
		while ((line = br.readLine()) != null) {
		   countries.add(line);
		}
		br.close();
		return countries;
	}
	
	/**
 		Listener interface that handles clicks on the OK Button
	*/
	public interface OKButtonClickListener{
		public void onOKButtonClick(Person contact);
	}

	/**
 	 	Handles the ActionEvents of OK Button
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		updateContact();
		if(listener != null)
			listener.onOKButtonClick(person);
		dispose();
	}
	
	/**
	 	Synchronizes the fields of the Person with that of the PersonDialog
	 */
	public void updateContact(){
		Sex sex = (Sex) cbSex.getSelectedItem();
		person.setSex(sex);
		person.setTitle(tfTitle.getText().trim());
		person.setFirstName(tfFirstName.getText().trim());
		person.setMiddleName(tfMiddleName.getText().trim());
		person.setLastName(tfLastName.getText().trim());
		person.setAddress(tfAddress.getText().trim());
		person.setCity(tfCity.getText().trim());
		person.setState(tfState.getText().trim());
		person.setContactNumber(tfContactNumber.getText().trim());
		person.setZipCode(tfZipCode.getText().trim());
		String country = null;
		if(cbCountry.getSelectedIndex() != 0)
			country = (String) cbCountry.getSelectedItem();
		person.setCountry(country);
	}
	
	/**
 		Synchronizes the fields of the PersonDialog with that of the Person
	*/
	public void updateDialog(){
		cbSex.setSelectedItem(person.getSex());
		tfTitle.setText(person.getTitle());
		tfFirstName.setText(person.getFirstName());
		tfMiddleName.setText(person.getMiddleName());
		tfLastName.setText(person.getLastName());
		tfAddress.setText(person.getAddress());
		tfCity.setText(person.getCity());
		tfState.setText(person.getState());
		tfContactNumber.setText(person.getContactNumber());
		tfZipCode.setText(person.getZipCode());
		cbCountry.setSelectedItem(person.getCountry());
	}
}
