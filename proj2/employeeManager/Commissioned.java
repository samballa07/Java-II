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
 * Child class of the Employee parent class which keeps track of different
 * aspects of an employee. A commissioned employee does not need to keep
 * track of their hours and their totalSales is important for their pay.
 */
public class Commissioned extends Employee{

	//field
	private double commission;
	private double totalSales;
	
	//commissioned constructor
	public Commissioned(String firstIn, String lastIn, double salaryIn) {
		// calls the super class constuctor
		super(firstIn, lastIn, salaryIn);
		
		commission = salaryIn;
		totalSales = 0;
	}
	
	@Override
	public double pay() {
		//calulates pay using commission rate and sales
		return commission*(0.01*totalSales);
	}
	
	@Override
	public void incrementSales(double sale) {
		//overridden method for incrementing totalSales
		totalSales += sale;
	}
	@Override
	public double getSales() {
		return totalSales;
	}
	@Override
	public void reset() {
		//resets only totalSales since hours does not matter
		totalSales = 0; 
	}
	
}
