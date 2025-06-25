/* This class for creating Regular Employee. */

import java.util.Calendar;

public class RegularEmployee extends Employee {

	private double performanceScore;
	private double bonus;
	
	public RegularEmployee(int id, String firstName, String lastName, String gender, 
			Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, 
			Calendar hireDate, Department department, double performanceScore) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus,
				hasDriverLicence, salary, hireDate, department);
		setPerformanceScore(performanceScore);
	}
	
	public RegularEmployee(Employee employee, double perfScore) {
		this(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(),
				employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(),
				employee.getSalary(), employee.getHireDate(), employee.getDepartment(), perfScore);
	}

	public double getPerformanceScore() {
		return performanceScore;
	}

	public void setPerformanceScore(double performanceScore) {
		this.performanceScore = performanceScore;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "Person Info [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", gender=" + getGender() + "]" +
			   "\n\t\t\t\tEmployee Info [salary=" + getSalary() + ", hireDate=" + Test.calendarToString(getHireDate()) + "]" +
			   "\n\t\t\t\tRegularEmployee Info [performanceScore=" + getPerformanceScore() + ", bonus=" + getBonus() + "]";
	}
	
}
