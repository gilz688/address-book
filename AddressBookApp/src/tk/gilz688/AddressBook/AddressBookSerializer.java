package tk.gilz688.AddressBook;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class AddressBookSerializer {

	/**	
		Write Address Book to an XML file
		@param lex Address Book to be saved
		@param aFile File where data are to be stored
		@throws Exception 
	*/
	public static void write(AddressBook addressBook, File aFile) throws Exception {
		Serializer serializer = new Persister();
		serializer.write(addressBook, aFile);
	}

	/**	
		Read an Address Book from an XML file
		@param aFile File containing Address Book
		@throws Exception 
	*/
	public static AddressBook read(File aFile) throws Exception {
		Serializer serializer = new Persister();
		AddressBook addressBook = null;
		addressBook = serializer.read(AddressBook.class,aFile);
		return addressBook;
	}
}
