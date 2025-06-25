/* This class for creating Sales Employee. */

import java.util.Calendar;
import java.util.ArrayList;

public class SalesEmployee extends RegularEmployee {

	private ArrayList<Product> sales;
	public static int numberOfSalesEmployees;
	
	public SalesEmployee(int id, String firstName, String lastName, String gender, 
			Calendar birthDate, String maritalStatus, String hasDriverLicence, double 
			salary, Calendar hireDate, Department department, double pScore,  
			ArrayList<Product> s) {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence,
				salary, hireDate, department, pScore);
		setSales(s);
		numberOfSalesEmployees++;
	}

	public SalesEmployee(RegularEmployee re, ArrayList<Product> s) {
		this(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(),
				re.getMaritalStatus(), re.getHasDriverLicence(), re.getSalary(), re.getHireDate(),
				re.getDepartment(), re.getPerformanceScore(), s);
	}
	
	// if the sale already exist, it doesn't add it again.
	public boolean addSale(Product s) {
		if(!isSaleExist(s)) {
			sales.add(s);
			return true;
		} else return false;
	}
	
	// if the sale isn't exist, it doesn't try to remove it.
	public boolean removeSale(Product s) {
		if(isSaleExist(s)) {
			sales.remove(s);
			return true;
		} else return false;
	}
	
	// Checks is the sale exist or not.
	public boolean isSaleExist(Product s) {
		for(Product sale: sales) {
			if(sale == s)
				return true;
		}
		return false;
	}
	
	// Calculates total price of given products.
	public double calculateTotalSales(ArrayList<Product> sales) {
		double totalSales = 0;
		for(int i=0 ; i<sales.size() ; i++) 
			totalSales += sales.get(i).getPrice();
		return totalSales;
	}
	
	public ArrayList<Product> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Product> sales) {
		this.sales = sales;
	}

	public static int getNumberOfSalesEmployees() {
		return numberOfSalesEmployees;
	}

	public static void setNumberOfSalesEmployees(int numberOfSalesEmployees) {
		SalesEmployee.numberOfSalesEmployees = numberOfSalesEmployees;
	}

	@Override
	public String toString() {
		return "Person Info [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", gender=" + getGender() + "]" +
			   "\n\t\t\t\tEmployee Info [salary=" + getSalary() + ", hireDate=" + Test.calendarToString(getHireDate()) + "]" +
			   "\n\t\t\t\tRegularEmployee Info [performanceScore=" + getPerformanceScore() + ", bonus=" + getBonus() + "]"
			   + "\n\t\t\t\t" + sales.toString();
	}
	
}
