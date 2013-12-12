package tk.gilz688.AddressBook;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Person")
public class Person implements Comparable<Person>{
	public enum Sex {
		UNSPECIFIED, MALE, FEMALE
	}

	@Element(required=false)
	private String title;
	@Element(required=false)
	private String firstName;
	@Element(required=false)
	private String middleName;
	@Element(required=false)
	private String lastName;
	@Element(required=false)
	private Sex sex;
	@Element(required=false)
	private String zipCode;
	@Element(required=false)
	private String contactNumber;
	@Element(required=false)
	private String address;
	@Element(required=false)
	private String city;
	@Element(required=false)
	private String state;
	@Element(required=false)
	private String country;

	/**	
		Get the title of the Person
	*/
	public String getTitle() {
		return title;
	}

	/**	
		Set the title of the Person
	*/
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**	
	Set the first name of the Person
	*/		
	public String getFirstName() {
		return firstName;
	}
	
	/**	
		Set the first name of the Person
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**	
		Get the middle name of the Person
		@return middle name
	*/
	public String getMiddleName() {
		return middleName;
	}
	
	/**	
		Set the middle name of the Person
	*/
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**	
		Get the last name of the Person
		@return last name
	*/
	public String getLastName() {
		return lastName;
	}
	
	/**	
		Set the last name of the Person
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**	
		Get the sex orientation of the Person
		@return sexual orientation
	*/
	public Sex getSex() {
		return sex;
	}

	/**	
		Set the gender of the Person
	*/
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	/**	
		Set the zip code or postal code of the Person
	*/	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/**	
		Get the contact number of the Person
		@return contact number
	 */	
	public String getContactNumber() {
		return contactNumber;
	}
	
	/**	
		Set the contact number of the Person
	*/	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	/**	
		Get the address of the Person
		@return address
	*/
	public String getAddress() {
		return address;
	}

	/**	
		Set the address of the Person
	*/
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**	
		Get the city of the Person
		@return city
	*/
	public String getCity() {
		return city;
	}
	
	/**	
		Set the city of the Person
	*/
	public void setCity(String city) {
		this.city = city;
	}
	
	/**	
		Get the state or province of the Person
		@return state or province
	*/
	public String getState() {
		return state;
	}
	
	/**	
		Set the state or province of the Person
	*/
	public void setState(String state) {
		this.state = state;
	}
	
	/**	
		Get the country of the Person
		@return country
	*/
	public String getCountry() {
		return country;
	}
	
	/**	
		Set the country of the Person
	*/
	public void setCountry(String country) {
		this.country = country;
	}

	/**	
		Get the full name of the Person
		@return full name
	*/
	public String getFullName(){
		StringBuilder builder = new StringBuilder();
		if(lastName != null)
			builder.append(lastName);
		if(builder.length() > 0)
			builder.append(", ");
		if(firstName != null)
			builder.append(firstName);
		return builder.toString();
	}
	
	/**	
		Get the city address of the Person
		@return city address
	*/
	public String getCityAddress(){
		StringBuilder builder = new StringBuilder();
		if(address != null)
			builder.append(address);
		if(builder.length() >0)
			builder.append(", ");
		if(city != null)
			builder.append(city);
		return builder.toString();
	}
	
	@Override
	public int compareTo(Person aPerson) {
		return getFullName().compareTo(aPerson.getFullName());
	}	
}