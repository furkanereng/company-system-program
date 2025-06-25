/* This class for creating Person. */

import java.util.ArrayList;
import java.util.Calendar;

public class Person {

	private int id;
	private String firstName = "";
	private String lastName = "";
	private byte gender = 0;
	private Calendar birthDate;
	private byte maritalStatus = 0;
	private boolean hasDriverLicence;
	
	public Person(int id, String firstName, String lastName,
	String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setBirthDate(birthDate);
		setGender(gender);
		setMaritalStatus(maritalStatus);
		setHasDriverLicence(hasDriverLicence);
	}
	
	// Created for make things easier.
	public static Person findPerson(ArrayList<Person> people, String id) {
		int i=0;
		for(; i<people.size() ; i++) 
			if(Integer.parseInt(id) == people.get(i).getId()) 
				break;
		return people.get(i);
	}
	
	public boolean isNotValidPerson() {
		if ( id != 0 && !firstName.equals("") && !lastName.equals("") && !(gender == 0) && !(maritalStatus == 0) &&
				(getHasDriverLicence().equals("Yes") || getHasDriverLicence().equals("No")) )
			return false;
		else return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		try {
			if (id > 0)
				this.id = id;
			else throw new Exception("Invalid id.");
		} catch (Exception ex) {
			System.out.println("Invalid id: " + id);
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		try {
			if (firstName.length() >= 3)
				this.firstName = firstName;
			else throw new Exception("Invalid firstName.");
		} catch (Exception ex) {
			System.out.println("Invalid first name: " + firstName);
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		try {
			if (lastName.length() >= 3)
				this.lastName = lastName;
			else throw new Exception("Invalid lastName.");
		} catch (Exception ex) {
			System.out.println("Invalid last name: " + lastName);
		}
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	// We are sure about gender is coded as 1 or 2 for sure. So, there is no exception handling.
	public String getGender() {
		if (gender == 1)
			return "Woman";
		else 
			return "Man";
	}

	public void setGender(String gender) {
		try {
			if (gender.equals("Woman"))
				this.gender = 1;
			else if (gender.equals("Man"))
				this.gender = 2;
			else throw new Exception("Invalid gender.");
		} catch (Exception ex) {
			System.out.println("Invalid gender: " + gender);
		}
	}

	// We are sure about maritalStatus is coded as 1 or 2 for sure. So, there is no exception handling.
	public String getMaritalStatus() {
		if (maritalStatus == 1)
			return "Single";
		else
			return "Married";
	}

	public void setMaritalStatus(String maritalStatus) {
		try {
			if (maritalStatus.equals("Single"))
				this.maritalStatus = 1;
			else if (maritalStatus.equals("Married"))
				this.maritalStatus = 2;
			else throw new Exception("Invalid marital status");
		} catch (Exception ex) {
			System.out.println("Invalid marital status: " + maritalStatus);
		}
	}

	// We are sure about it is true or false for sure. So, there is no exception handling.
	public String getHasDriverLicence() {
		if (hasDriverLicence)
			return "Yes";
		else
			return "No";
	}

	public void setHasDriverLicence(String info) {
		try {
			if (info.equals("Yes"))
				this.hasDriverLicence = true;
			else if (info.equals("No"))
				this.hasDriverLicence = false;
			else throw new Exception("Invalid hasDriverLicence.");
		} catch (Exception ex) {
			System.out.println("Invalid hasDriverLicence: " + info);
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + getGender()
				+ ", birthDate=" + Test.calendarToString(birthDate) + ", maritalStatus=" + getMaritalStatus() +
				", hasDriverLicence=" + getHasDriverLicence() + "]";
	}
	
}
