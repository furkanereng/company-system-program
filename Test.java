/*
 * This program reads the input file and edits and prints them into an output file.
 * if any error occurs, prints them to console.
 * This class implemented according to given test case. 
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) throws Exception {
		
		// if there is no input file, program gives error and ends. Otherwise, creates the input file object and continues.
		File inputFile = new File("input.txt");
		if(!inputFile.exists()) {
			System.out.println("There is no input file!");
			System.exit(1);
		}
		Scanner input = new Scanner(inputFile);
		
		// Reads content of input file line by line and keep each line as string in an ArrayList.
		ArrayList<String> arrayOfInput = new ArrayList<>();
		while(input.hasNext()) 
			arrayOfInput.add(input.nextLine());
		input.close();
		
		// Each line's content is splitting and holding in an array. All of those arrays are keeping in another ArrayList.
		ArrayList<String[]> arrayOfLines = new ArrayList<>();
		for(String line: arrayOfInput)
			arrayOfLines.add(line.split(" "));
		
		// Checks first word of each line and creates objects depend of it and puts them into an ArrayList. Handles some errors.
		// I set maritalStatus and hasDriverLicence as not-case-sensitive.
		ArrayList<Person> people = new ArrayList<>();
		ArrayList<Department> departments = new ArrayList<>();
		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Project> projects = new ArrayList<>();
		
		for(String[] wordsOfLine: arrayOfLines) { 
			switch(wordsOfLine[0]) {
			case "Product":
				products.add(new Product(wordsOfLine[1], stringToCalendar(wordsOfLine[2]), Integer.parseInt(wordsOfLine[3])));
				break;
				
			case "Project":
				projects.add(new Project(wordsOfLine[1], stringToCalendar(wordsOfLine[2]), wordsOfLine[3]));
				break;
				
			case "Department":
				departments.add(new Department(Integer.parseInt(wordsOfLine[1]), wordsOfLine[2]));
				break;
				
			case "Person":
				String firstName = wordsOfLine[1];
				String lastName = wordsOfLine[2];
				int id = Integer.parseInt(wordsOfLine[3]);
				String gender = wordsOfLine[4];
				Calendar birthDate = stringToCalendar(wordsOfLine[5]);
				String maritalStatus = wordsOfLine[6];
				String hasDriverLicence = wordsOfLine[7];
				people.add(new Person(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence));
				break;
			}
		}
		
		for(Department department: departments) {
			if(department.isNotValidDepartment()) {
				departments.remove(department);
			}
		}
		for(Product product: products) {
			if(product.isNotValidProduct()) {
				products.remove(product);
			}
		}
		for(Project project: projects) {
			if(project.isNotValidProject()) {
				projects.remove(project);
			}
		}
		for(Person person: people) {
			if(person.isNotValidPerson()) {
				people.remove(person);
			}
		}
		
		for(String[] wordsOfLine: arrayOfLines) { 
			switch(wordsOfLine[0]) {
					
				case "Customer":
					ArrayList<Product> productsOfCustomer = new ArrayList<>();
					Person customer = new Customer(Person.findPerson(people, wordsOfLine[1]), productsOfCustomer);
					people.remove(Person.findPerson(people, wordsOfLine[1]));
					people.add(customer);
					for(int j=2 ; j<wordsOfLine.length ; j++) {
						try {
							if(Product.isProductExist(products, wordsOfLine[j])) {
								for (Product product: products) {
									if(product.getProductName().equals(wordsOfLine[j])) {
										try {
											if(!Product.isProductExist(((Customer)customer).getProducts(), wordsOfLine[j]))
												((Customer)customer).getProducts().add(product);
											else throw new Exception("Already added.");
										} catch (Exception ex) {
											System.out.println(product.getProductName() + "'s already added to list of " + customer.getFirstName() + ".");
										}
									}
								}
							} else throw new Exception("Invalid product.");
						} catch (Exception ex) {
							System.out.println("Invalid product: " + wordsOfLine[j]);
						}
					}
					break;
					
				case "Employee":
					for(Department department: departments) {
						if(department.getDepartmentName().equals(wordsOfLine[4])) {
							Person employee = new Employee(Person.findPerson(people, wordsOfLine[1]), Double.parseDouble(wordsOfLine[2]), stringToCalendar(wordsOfLine[3]), department);
							people.remove(Person.findPerson(people, wordsOfLine[1]));
							people.add(employee);
						}
					}
					break;
					
				case "RegularEmployee":
					Person re = new RegularEmployee((Employee)Person.findPerson(people, wordsOfLine[1]), Double.parseDouble(wordsOfLine[2]));
					people.remove(Person.findPerson(people, wordsOfLine[1]));
					people.add(re);
					break;
					
				case "Manager":
					Person manager = new Manager((Employee)Person.findPerson(people, wordsOfLine[1]), Double.parseDouble(wordsOfLine[2]));
					people.remove(Person.findPerson(people, wordsOfLine[1]));
					people.add(manager);
					break;
					
				case "SalesEmployee":
					ArrayList<Product> productsOfSE = new ArrayList<>();
					Person salesEmployee = new SalesEmployee((RegularEmployee)Person.findPerson(people, wordsOfLine[1]), productsOfSE);
					for(int j=2 ; j<wordsOfLine.length ; j++) {
						try {
							for (Product product: products) {
								if(Product.isProductExist(products, wordsOfLine[j])) {
									if(product.getProductName().equals(wordsOfLine[j])) {
										try {
											if(((SalesEmployee)salesEmployee).addSale(product)) {
											} else throw new Exception("Product's already added.");
										} catch (Exception ex) {
											System.out.println("Product's already added to sales of sales employee with id " + salesEmployee.getId() + ": " + wordsOfLine[j]);
										}
									}
								} else throw new Exception("Invalid product.");
							} 
						} catch (Exception ex) {
							System.out.println("Product doesn't exist: " + wordsOfLine[j]);
						}
					}
					people.remove(Person.findPerson(people, wordsOfLine[1]));
					people.add(salesEmployee);
					break;
					
				case "Developer":
					ArrayList<Project> projectsOfDev = new ArrayList<>();
					Person dev = new Developer((RegularEmployee)Person.findPerson(people, wordsOfLine[1]), projectsOfDev);
					for(int j=2 ; j<wordsOfLine.length ; j++) {
						try {
							for (Project project: projects) {
								if(Project.isProjectExist(projects, wordsOfLine[j])) {
									if(project.getProjectName().equals(wordsOfLine[j])) {
										try {
											if(((Developer)dev).addProject(project)) {
											} else throw new Exception("Project's already added.");
										} catch (Exception ex) {
											System.out.println("Project's already added to projects of developer with id " + dev.getId() + ": " + wordsOfLine[j]);
										}
									} 
								} else throw new Exception("Invalid project.");
							} 
						} catch (Exception ex) {
							System.out.println("Project doesn't exist: " + wordsOfLine[j]);
						}
					}
					people.remove(Person.findPerson(people, wordsOfLine[1]));
					people.add(dev);
					break;
					
				case "Department":
				case "Project":
				case "Product":
				case "Person":
					break;
					
				default:
					System.out.println("File contains invalid input: " + wordsOfLine[0]);
			}
		}
		
		for(Person person: people) {
			if(person.isNotValidPerson()) {
				if(person instanceof Employee)
					Employee.setNumberOfEmployees((Employee.getNumberOfEmployees()-1));
				if(person instanceof SalesEmployee)
					SalesEmployee.setNumberOfSalesEmployees((SalesEmployee.getNumberOfSalesEmployees()-1));
				if(person instanceof Developer)
					Developer.setNumberOfDevelopers((Developer.getNumberOfDevelopers()-1));
				people.remove(person);
			}
		}
		
		// Adding employees of each managers to their lists.
		for(Person person: people) {
			if(person instanceof RegularEmployee && !(person instanceof Developer) && !(person instanceof SalesEmployee)) {
				for(Person manager: people) 
					if(manager instanceof Manager) 
						if(((Manager)manager).getDepartment().equals(((RegularEmployee)person).getDepartment())) 
							((Manager)manager).addEmployee((RegularEmployee)person);
			} else if(person instanceof Developer) { 
				for(Person manager: people) 
					if(manager instanceof Manager) 
						if(((Manager)manager).getDepartment().equals(((Developer)person).getDepartment())) 
							((Manager)manager).addEmployee((Developer)person);
			} else if(person instanceof SalesEmployee) {
				for(Person manager: people) 
					if(manager instanceof Manager) 
						if(((Manager)manager).getDepartment().equals(((SalesEmployee)person).getDepartment())) 
							((Manager)manager).addEmployee((SalesEmployee)person);
			}
		}
		
		// Calling some methods according to given test case, salaries are raising with a percent value.
		// if percent value is invalid, it won't be raised.
		for(Person person: people) {
			if(person instanceof Manager) {
				((Manager)person).distributeBonusBudget();
				((Manager)person).setSalary(((Manager)person).raiseSalary(0.2));
			}
		}
		for(Person person: people) {
			if(person instanceof RegularEmployee && !(person instanceof Developer) && !(person instanceof SalesEmployee)) {
				((RegularEmployee)person).setSalary(((RegularEmployee)person).raiseSalary(0.3));	
			} else if(person instanceof Developer) {
				((Developer)person).setSalary(((Developer)person).raiseSalary(0.23));
			} else if(person instanceof SalesEmployee) {
				((SalesEmployee)person).setSalary(((SalesEmployee)person).raiseSalary(0.18));
			}
		}
		
		// Finding SalesEmployee who has maximum amount of sales and calling raiseSalary method to that employee.
		SalesEmployee maxSales = null;
		for(Person person: people) {
			if(person instanceof SalesEmployee) {
				maxSales = (SalesEmployee)person;
				break;
			}
		}
		for(Person person: people) {
			if(person instanceof SalesEmployee) {
				if(maxSales.calculateTotalSales(maxSales.getSales()) < ((SalesEmployee)person).calculateTotalSales(((SalesEmployee)person).getSales())) {
					maxSales = (SalesEmployee)person;
				}
			}
		}
		((SalesEmployee)maxSales).setSalary(((SalesEmployee)maxSales).raiseSalary(10000));
		
		File outputFile = new File("output.txt");
		if(outputFile.exists()) {
			System.out.println("Overriding the output file!");
		}
		PrintWriter output = new PrintWriter(outputFile);
		
		// Printing departments.
		for(Department department: departments) {
			output.println("************************************************");
			output.println(department.toString());
			
			int j=0;
			for(; j<people.size() ; j++) {
				if(people.get(j) instanceof Manager) {
					if(((Manager)people.get(j)).getDepartment().getDepartmentName().equals(department.getDepartmentName())) {
						break;
					}
				}
			}
			output.println("\t" + ((Manager)people.get(j)).toString());
			
			// Printing employees of department in the order.
			int index=1;
			for(int k=0 ; k<((Manager)people.get(j)).getRegularEmployees().size() ; k++) {
				if(((Manager)people.get(j)).getRegularEmployees().get(k) instanceof Developer) {
					output.println("\t\t\t" + index + ". Developer ");
					output.println("\t\t\t\t" + ((Developer)((Manager)people.get(j)).getRegularEmployees().get(k)).toString());
					index++;
				}
			}
			for(int k=0 ; k<((Manager)people.get(j)).getRegularEmployees().size() ; k++) {
				if (((Manager)people.get(j)).getRegularEmployees().get(k) instanceof SalesEmployee) {
					output.println("\t\t\t" + index + ". SalesEmployee ");
					output.println("\t\t\t\t" + ((SalesEmployee)((Manager)people.get(j)).getRegularEmployees().get(k)).toString());
					index++;
				}
			}
			for(int k=0 ; k<((Manager)people.get(j)).getRegularEmployees().size() ; k++) {
				if (!(((Manager)people.get(j)).getRegularEmployees().get(k) instanceof Developer) && !(((Manager)people.get(j)).getRegularEmployees().get(k) instanceof SalesEmployee)) {
					output.println("\t\t\t" + index + ". RegularEmployee ");
					output.println("\t\t\t\t" + ((RegularEmployee)((Manager)people.get(j)).getRegularEmployees().get(k)).toString());
					index++;
				}
			}
			output.println();
		}
		
		output.println();
		output.println();
		output.println("**********************CUSTOMERS************************");
		for(Person person: people) {
			if(person instanceof Customer) {
				output.println(((Customer)person).toString());
			}
		}
		
		output.println();
		output.println("\n**********************PEOPLE************************");
		for(Person person: people) {
			if(!(person instanceof Customer) && !(person instanceof Employee)) {
				output.println(person.toString());
			}
		}
		
		output.close();
		
	}
	
	public static Calendar stringToCalendar(String str) {
		String[] date = str.split("/");
		int day = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar;
	}
	
	public static String calendarToString(Calendar c) {
		// if the month is December, it's shown as "0" and year's increased by 1. To fix it, used if statement.
		if (c.get(Calendar.MONTH) == 0)
			return c.get(Calendar.DAY_OF_MONTH) + "/12/" + (c.get(Calendar.YEAR)-1);
		else	
			return c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
	}
	
}
