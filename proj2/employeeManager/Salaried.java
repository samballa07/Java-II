/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */
package employeeManager;

/*
 * Child class of the Employee class. Has certain aspects which are 
 * different than that of the employee class. TotalSales is different
 * for Salaried since it never changes for this type
 */
public class Salaried extends Employee{
	
	private double salary;
	private int numHours;
	private double totalSales;
	
	// salary constructor
	public Salaried(String firstIn, String lastIn, double salaryIn) {
		// calls the super class constructor
		super(firstIn, lastIn, salaryIn);
		salary = salaryIn;
		numHours = 0;
		totalSales = 0;
	}

	@Override
	public boolean incrementHours(int hours) {
		//checks if the hours will be greater than 80 if added
		int temp = numHours;
		temp += hours;
		if (temp >= 80) {
			return false;
		} else {
			numHours += hours; //adds only if its less than 80
			return true;
		}
	}
	
	@Override
	public double pay() {
		//returns the bi-weekly pay of the Salaried employee
		return salary/26;
	}
	@Override
	public int getHours() {
		// overridden method for numHours
		return numHours;
	}
	
	@Override
	public double getSales() {
		//overridden method for totalSales
		return totalSales;
	}
	@Override
	public void reset() {
		//resets numHours
		numHours = 0;
	}
	
}
