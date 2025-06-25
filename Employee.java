/* This class for creating Employee. */

import java.util.Calendar;

public class Employee extends Person {

	private double salary;
	private Calendar hireDate;
	private Department department;
	public static int numberOfEmployees;
	
	public Employee(int id, String firstName, String lastName, String gender,
			Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary,
			Calendar hireDate, Department department){
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
		setSalary(salary);
		setHireDate(hireDate);
		setDepartment(department);
		numberOfEmployees++;
	}
	
	public Employee(Person person, double salary, Calendar hireDate, Department department) {
		this(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(),
				person.getBirthDate(), person.getMaritalStatus(), person.getHasDriverLicence(), salary,
				hireDate, department);
	}
	
	// Raises salary if percent value is valid.
	public double raiseSalary(double percent) {
		try {
			if(percent>=0 && percent<=1)
				return (int)(salary*(1+percent)*100)/100.0;
			else throw new IllegalArgumentException("Invalid percent value.");
		} catch (IllegalArgumentException ex) {
			System.out.println("Salary couldn't raised for employee with id " + getId() + ", cause of invalid percent value: " + percent);
			return salary;
		}
	}
	
	public double raiseSalary(int amount) {
		return salary+amount;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Calendar getHireDate() {
		return hireDate;
	}

	public void setHireDate(Calendar hireDate) {
		this.hireDate = hireDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public static void setNumberOfEmployees(int numberOfEmployees) {
		Employee.numberOfEmployees = numberOfEmployees;
	}

	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", hireDate=" + Test.calendarToString(hireDate) + ", department=" + department + "]";
	}
	
}
