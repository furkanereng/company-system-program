/* This class for creating Developer. */

import java.util.Calendar;
import java.util.ArrayList;

public class Developer extends RegularEmployee {

	private ArrayList<Project> projects;
	public static int numberOfDevelopers;
	
	public Developer(int id, String firstName, String lastName, String gender, 
			Calendar birthDate, String maritalStatus, String hasDriverLicence, double 
			salary, Calendar hireDate, Department department, double pScore,  
			ArrayList<Project> p) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence,
				salary, hireDate, department, pScore);
		setProjects(p);
		numberOfDevelopers++;
	}

	public Developer(RegularEmployee re, ArrayList<Project> p) {
		this(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(),
				re.getMaritalStatus(), re.getHasDriverLicence(), re.getSalary(), re.getHireDate(),
				re.getDepartment(), re.getPerformanceScore(), p);
	}
	
	public boolean addProject(Project s) {
		if(!isProjectExist(s)) {
			projects.add(s);
			return true;
		} else return false;
	}
	
	public boolean removeProject(Project s) {
		if(isProjectExist(s)) {
			projects.remove(s);
			return true;
		} else return false;
	}
	
	// Created for make things easier.
	public boolean isProjectExist(Project s) {
		for(Project p: projects) {
			if(p == s)
				return true;
		}
		return false;
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public static int getNumberOfDevelopers() {
		return numberOfDevelopers;
	}

	public static void setNumberOfDevelopers(int numberOfDevelopers) {
		Developer.numberOfDevelopers = numberOfDevelopers;
	}

	@Override
	public String toString() {
		return "Person Info [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", gender=" + getGender() + "]" +
			   "\n\t\t\t\tEmployee Info [salary=" + getSalary() + ", hireDate=" + Test.calendarToString(getHireDate()) + "]" +
			   "\n\t\t\t\tRegularEmployee Info [performanceScore=" + getPerformanceScore() + ", bonus=" + getBonus() + "]"
			   + "\n\t\t\t\t" + projects.toString();
	}
	
}
