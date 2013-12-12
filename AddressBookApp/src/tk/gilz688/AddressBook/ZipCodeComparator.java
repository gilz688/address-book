package tk.gilz688.AddressBook;

import java.util.Comparator;

/**
    Sort persons by Zip Code, and by Name if Zip Code is the same
 */
public class ZipCodeComparator implements Comparator<Person> {
	public int compare(Person personA, Person personB) {
		int comparison = personA.getZipCode().compareTo(personB.getZipCode());
		if (comparison == 0)
			comparison = personA.compareTo(personB);
		return comparison;
	}
}