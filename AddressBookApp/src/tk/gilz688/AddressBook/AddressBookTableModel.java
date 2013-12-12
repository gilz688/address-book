package tk.gilz688.AddressBook;

import java.util.ListIterator;

import javax.swing.table.AbstractTableModel;

import tk.gilz688.AddressBook.AddressBook.SortingOrder;

public class AddressBookTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 4601298687175190604L;
	private AddressBook addressBook;
	private SortingOrder sortingOrder = SortingOrder.NONE;
	public static final int COLUMN_AVATAR = 0;
	public static final int COLUMN_NAME = 1;
	public static final int COLUMN_ADDRESS = 2;
	public static final int COLUMN_ZIPCODE = 3;
	public static final int COLUMN_CONTACTNUMBER = 4;
	public static final int COLUMNS = 5;
	private static final String HEADER_NAME = "Person's Name";
	private static final String HEADER_ADDRESS = "Address";
	private static final String HEADER_ZIPCODE = "Zip Code";
	private static final String HEADER_CONTACTNUMBER = "Contact Number";
	
	/**
	 *  Create an AddressBookModel using data from an existing AddressBook
	 */
	public AddressBookTableModel(AddressBook aAddressBook){
		addressBook = aAddressBook;
	}
	
	/**
	 *  Create an empty AddressBookModel
	 */
	public AddressBookTableModel(){
		addressBook = new AddressBook();
	}
	
	@Override
	public int getColumnCount() {
		return COLUMNS;
	}

	@Override
	public int getRowCount() {
		return addressBook.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = addressBook.get(row);
		switch(col){
		case COLUMN_AVATAR:
			return person.getSex();
		case COLUMN_NAME:
			return person.getFullName();
		case COLUMN_ADDRESS:
			return person.getCityAddress();
		case COLUMN_ZIPCODE:
			int zipCode = person.getZipCode();
			if(zipCode == 0)
				return "";
			return String.valueOf(zipCode);
		case COLUMN_CONTACTNUMBER:
			return person.getContactNumber();
			default:
		}
		return null;
	}

	/**
	 *  Remove Person with specified index from AddressBookModel
	 *  @param index index of Person to be removed
	 *  @return removed Person
	 */
	public Person remove(int index) {
		Person removedPerson =  addressBook.remove(index);
		fireTableDataChanged();
		return removedPerson;
	}

	/**
	 *  Add Person to AddressBookModel
	 *  @param person Person to be added
	 */
	public boolean addElement(Person person) {
		boolean result = addressBook.add(person);
		fireTableDataChanged();
		return result;
	}

	/**
	 *  Set a new Person to the specified index of the AddressBookModel
	 *  @param index index of the Person to be replaced
	 *  @param person new Person
	 */
	public Person set(int index, Person person) {
		Person previousPerson = addressBook.set(index, person);
		fireTableDataChanged();
		return previousPerson;
	}
	
	/**
	 *  Return an AddressBook containing all persons in the AddressBookModel
	 *  @return an AddressBook
	 */
	public AddressBook getAddressBook(){
		return addressBook;
	}
	
	/**
	 *  Get Person at specified index in the AddressBookModel
	 *  @param index index of the Person to be retrieved
	 *  @return Person at specified index
	 */
	public Person getPersonAt(int index){
		return addressBook.get(index);
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case COLUMN_NAME:
			return HEADER_NAME;
		case COLUMN_ADDRESS:
			return HEADER_ADDRESS;
		case COLUMN_ZIPCODE:
			return HEADER_ZIPCODE;
		case COLUMN_CONTACTNUMBER:
			return HEADER_CONTACTNUMBER;
			default:
		}
		return "";
	}
	
	@Override
	public void fireTableDataChanged() {
		addressBook.sort(sortingOrder);
		super.fireTableDataChanged();
	}

	/**
	 *  Set sorting order of the AddressBookModel
	 */	
	public void setSortingOrder(SortingOrder order){
		sortingOrder = order;
		fireTableDataChanged();
	}
    
	/**	
		Find next Person in the Address Book Model that contains the specified text
		@param startRow row where the search begins
		@return row containing the specified text, <b>-1</b> if no match if found
	 */		
    public int findNext(int startRow, String text){
    	text = text.trim().toLowerCase();
    	ListIterator<Person> iterator = addressBook.getPersons().listIterator(startRow);
    	while(iterator.hasNext()){
    		int index = iterator.nextIndex();
    		Person current = iterator.next();
    		if(current.getFullName().toLowerCase().indexOf(text) != -1)
    			return index;
    		if(current.getCityAddress().toLowerCase().indexOf(text) != -1)
    			return index;
    		if(String.valueOf(current.getZipCode()).toLowerCase().indexOf(text) != -1)
    			return index;
    	}
    	return -1;
    }
}
