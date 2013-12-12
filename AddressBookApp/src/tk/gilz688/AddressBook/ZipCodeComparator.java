package tk.gilz688.AddressBook;

import java.util.Comparator;

/**
 * Find all Persons in the Address Book Model that contains the specified text
 */
public class ZipCodeComparator implements Comparator<Person> {
	public int compare(Person personA, Person personB) {
		int comparison = personA.getZipCode() - personB.getZipCode();
		if (comparison == 0)
			comparison = personA.compareTo(personB);
		return comparison;
	}
}