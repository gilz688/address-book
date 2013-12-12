package tk.gilz688.AddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="AddressBook")
public class AddressBook {
	
	public enum SortingOrder{
		NONE, NAME_ASCENDING, NAME_DESCENDING, ZIPCODE_ASCENDING, ZIPCODE_DESCENDING
	}
	
	@ElementList(inline=true)
	private List<Person> persons;
	
	/**	
	 	Create a new Address Book
	 */	
	public AddressBook(){
		persons = new ArrayList<Person>();
	}
	
	/**	
 		Add Person to the Address Book
 		@param aPerson Person to be added
 		@return <b>true</b> if the operation is successful, <b>false</b> otherwise
	*/		
	public boolean add(Person aPerson){
		return persons.add(aPerson);
	}

	/**	
		Remove Person from the Address Book
		@param aPerson Person to be removed
		@return boolean indicating whether the operation is successful or not
	*/		
	public boolean remove(Person aPerson){
		return persons.remove(aPerson);
	}

	/**	
		Get all Persons in the Address Book
		@return a list of persons
	*/	
	public List<Person> getPersons(){
		return persons;
	}

	/**	
		Set the Persons in the Address Book
	*/	
	public void setPersons(List<Person> aPersons) {
		persons = aPersons;
	}

	/**	
		Get the size of the Address Book
		@return a number of Persons in Address Book
	 */	
	public int size() {
		return persons.size();
	}

	/**	
		Get the Person at the specified index in the Address Book
		@return Persons at the specified index
	 */	
	public Person get(int index) {
		return persons.get(index);
	}

	/**	
		Remove Person with specified index from the Address Book
		@param index index of the Person to be removed
		@return boolean indicating whether the operation is successful or not
	*/	
	public Person remove(int index) {
		return persons.remove(index);
	}

	/**
	    Sort Persons in the AddressBookModel
	 */	
	public void sort(SortingOrder order){
		switch(order){
		case NAME_ASCENDING:
			Collections.sort(persons);
			break;
		case NAME_DESCENDING:
			Collections.sort(persons, Collections.reverseOrder());
			break;
		case ZIPCODE_ASCENDING:
			Collections.sort(persons, new ZipCodeComparator());
			break;
		case ZIPCODE_DESCENDING:
			Collections.sort(persons, Collections.reverseOrder(new ZipCodeComparator()));
			break;
		default:
			return;
		}
	}

	/**
    	Replace Person at specified index in the AddressBookModel
    	@param person new Person
    	@return Person replaced
	*/	
	public Person set(int index, Person person) {
		return persons.set(index, person);
	}
}
