/* This class for creating Manager. */

import java.util.Calendar;
import java.util.ArrayList;

public class Manager extends Employee {

	private ArrayList<RegularEmployee> regularEmployees = new ArrayList<>(1);
	private double bonusBudget;
	
	public Manager(int id, String firstName, String lastName, String gender, 
			Calendar birthDate, String maritalStatus, String hasDriverLicence, double 
			salary, Calendar hireDate, Department department, double bonusBudget) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus,
				hasDriverLicence, salary, hireDate, department);
		setBonusBudget(bonusBudget);
	}
	
	public Manager(Employee employee, double bonusBudget) {
		this(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(),
				employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(),
				employee.getSalary(), employee.getHireDate(), employee.getDepartment(), bonusBudget);
	}
	
	public void addEmployee(RegularEmployee e) {
		regularEmployees.add(e);
	}
	
	public void removeEmployee(RegularEmployee e) {
		regularEmployees.remove(e);
	}
	
	public void distributeBonusBudget() {
		double unit = calculateUnit();
		for(RegularEmployee re: regularEmployees) {
			double bonus = unit * re.getSalary() * re.getPerformanceScore();
			re.setBonus((int)Math.round((bonus*100))/100.0); // To get exactly the same result with given output file, used Math.round() method
		}
	}
	
	// Calculates unit which needed for calculating bonus.
	public double calculateUnit() {
		double sum = 0;
		for(RegularEmployee re: regularEmployees) 
			sum += re.getPerformanceScore() * re.getSalary();
		return getBonusBudget()/sum;
	}

	public ArrayList<RegularEmployee> getRegularEmployees() {
		return regularEmployees;
	}

	public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) {
		this.regularEmployees = regularEmployees;
	}

	public double getBonusBudget() {
		return bonusBudget;
	}

	public void setBonusBudget(double bonusBudget) {
		this.bonusBudget = bonusBudget;
	}

	@Override
	public String toString() {
		return "Manager [id: " + ((Person)this).getId() + ", " + ((Person)this).getFirstName() + " " + ((Person)this).getLastName() + "\n\t\t# of Employees: " + regularEmployees.size() + "]";
	}
	
}
