/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */
package employeeManager;

/* This class is of type Employee which stores info about an employee of
 * the company class. The number of hours and total sales are kept track
 * of as well as other static variables like name and salary.
 * Methods are all used by the company class
 */
public class Employee {
	
	// field
	private String firstName;
	private String lastName;
	private double salary;
	private int numHours; 
	private double totalSales;
	
	// employee constructor
	public Employee(String firstIn, String lastIn, double salaryIn) {
		firstName = firstIn;
		lastName = lastIn;
		salary = salaryIn;
		numHours = 0;
		totalSales = 0;
	}
	
	// equals method which checks if two Employees have the same name
	public boolean equals(Employee other) {
		// ignores the case of the string
		if (firstName.equalsIgnoreCase(other.firstName) && 
			lastName.equalsIgnoreCase(other.lastName)) {
			return true;
		}
		return false;
	}
	
	// overloaded equals method with different parameters
	public boolean equals(String first, String last) {
		if(firstName.equalsIgnoreCase(first) && 
		   lastName.equalsIgnoreCase(last)) {
			return true;
		}
		return false;
	}
	
	// concatenates the first and last name strings and returns it
	public String getFullName() {
		String name = firstName + " " + lastName;
		return name;
	}

	// increments the number of worked hours by the parameter
	public boolean incrementHours(int hours) {
		numHours += hours;
		return true;
	}
	
	// get method for the number of hours worked
	public int getHours() {
		return numHours;
	}
	
	// returns the salary of the current employee
	public double pay() {
		return salary;
	}
	// increments totalSales by the double passed in the parameter
	public void incrementSales(double sale) {
		totalSales += sale;
	}

	// get method for totalSales
	public double getSales() {
		return totalSales;
	}
	
	// new pay period
	public void reset() {
		numHours = 0;
		totalSales = 0;
	}

}
