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
 * This class is a Company type which keeps track of aspects that are
 * within a company. Different aspects include all the employees of the 
 * company, the type of company, and the name. This class has methods
 * which allow the employer to keep track of employee pay.
 */
public class Company {

	//field
	private String name;
	//array of type employees, stores all employees
	private int companyType;
	private Employee[] employees; 
	private int maxEmployees;
	final private static int PAID = 0;
	final private static int FREE = 1;

	//constructor for a paid account
	public Company(String name) {
		
		this.name = name;
		employees = new Employee[0];
		companyType = PAID;
	}

	//constructor for a free account
	public Company(String name, int maxEmployees) {
		this.name = name;
		this.maxEmployees = maxEmployees;
		employees = new Employee[maxEmployees];
		companyType = FREE;
	}

	public String getCompanyName() {
		return name;
	}

	public boolean hireCommissionedEmployee(String firstName, 
			String lastName, double commissionRate) {
		//checks if the parameters are valid
		if (firstName == null || lastName == null || commissionRate < 0.0 
				|| commissionRate >= 100.0
				|| firstName.isEmpty() || lastName.isEmpty()) {
			return false;
		}
		//initializes a new Employee reference of type commissioned
		Employee newEmployee = new Commissioned(firstName, lastName, 
				commissionRate);
		
		//checks if the employee was already in there and returns
		for (int j = 0; j < employees.length; j++) {
			if (employees[j] != null && employees[j].equals(newEmployee)){
				return false;
			}
		}
		//checks what the company type is
		if (companyType == PAID) {
			//if the account is paid then a new array 
			//is created with one extra element
			Employee[] tempArr = new Employee[employees.length + 1];
			// adds all the employees to the new arr
			for (int i = 0; i < employees.length; i++) {
					tempArr[i] = employees[i];
			}
			//adds the newEmployee to the last element of the 
			//new array(resizing)
			tempArr[tempArr.length - 1] = newEmployee;
			
			//makes employees field variable a reference to the new array
			employees = tempArr; 
			return true;
		} else {
			
			// for a free account, employees array is iterated and 
			// newEmployee is placed wherever there is a null reference
			for (int i = 0; i < employees.length; i++) {
				if (employees[i] == null) {
					employees[i] = newEmployee;
					return true;
				}
			}
		}
	
		return false;
	
	}

	public boolean hireSalariedEmployee(String firstName, String lastName, 
			double yearlySalary) {
		// parameter validation
		if (firstName == null || lastName == null || yearlySalary < 0.0 
				|| firstName.isEmpty()
				|| lastName.isEmpty()) {
			return false;
		}

		//new employee of type Salaried is created
		Employee newEmployee = new Salaried(firstName, lastName, 
				yearlySalary);
		//iterates through employee list and checks if the new Employee 
		//is already there
		for (int j = 0; j < employees.length; j++) {
			if (employees[j] != null && employees[j].equals(newEmployee)){
				return false;
			}
		}
		
		if (companyType == PAID) {

			//the rest of this method is similar to that of 
			//hireCommissionedEmployee()
			Employee[] tempArr = new Employee[employees.length + 1];
			for (int i = 0; i < employees.length; i++) {
				tempArr[i] = employees[i];
			}
			tempArr[tempArr.length - 1] = newEmployee;
			employees = tempArr;
			return true;
		} else {
			for (int i = 0; i < employees.length; i++) {
				if (employees[i] == null) {
					employees[i] = newEmployee;
					return true;
				}
			}

		}
		return false;

	}

	public int numEmployees() {
		// for a free company account, returns the amount of objects in 
		//employees that isn't null
		if (companyType == FREE) {
			for (int i = 0; i < employees.length; i++) {
				if (employees[i] == null) {
					return i;
				}
			}
		}
		//returns the array size if a paid account
		return employees.length;

	}

	public int employeeCapacity() {
		//returns the employee capacity depending on company type
		if (companyType == FREE) {
			return maxEmployees;
		} else {
			return Integer.MAX_VALUE;
		}
	}

	//helper method for getEmployeeName()
	private String lastNameSubString(String full) {
		//iterates a String to find the whitespace and 
		//returns the substring after the white space
		for(int i = 0; i < full.length(); i++) {
			if(full.charAt(i) == ' ') {
				return full.substring(i+1);
			}
		}
		return ""; //returns empty string if nothing is found
	}
	public String[] getEmployeeNames() {
		//initializes an array of String references
		String[] names = new String[numEmployees()];

		//for each employee, the full name as a string is added to 
		//the array
		for(int i = 0; i < numEmployees(); i++) {
			names[i] = employees[i].getFullName();
		}
		
		String temp;
		// these to String variable will store last names
		String last1;
		String last2;
		for (int i = 0; i < names.length; i++) {
			for (int j = i + 1; j < names.length; j++) {
				// assigns values to the two vars
				last1 = lastNameSubString(names[i]);
				last2 = lastNameSubString(names[j]);
				//checks if the current index last name is not
				//in order with that preceding it
				if (last1.compareTo(last2)>0) {
					//switches their places if so
					temp = names[i];
					names[i] = names[j];
					names[j] = temp;
				//else, puts the first names in alphabetical order
				} else if(names[i].compareTo(names[j]) == 0) {
					temp = names[i];
					names[i] = names[j];
					names[j] = temp;
				}
			}
		}
		return names; //returns the array of String names
	}

	public boolean workHours(String firstName, String lastName, 
							int numHours) {
		// checks if the if the parameters are valid
		if (firstName == null || lastName == null || firstName.isEmpty() ||
			lastName.isEmpty() || (numHours <= 0)) {
			return false;
		}
		
		Employee temp = null;
		//flag to check if that employee exists in array
		boolean exists = false; 
		// gets the employee with that name and assigns that index to temp
		for(int i = 0; i < employees.length; i++) {
			if(employees[i] != null && employees[i].equals(firstName, 
															lastName)) {
				exists = true;
				temp = employees[i];
			}
		}
		//returns false of the employee was not found
		if (exists == false) {
			return false;
		}
 
		// boolean to check if the numHours was added
		boolean added = temp.incrementHours(numHours);
		temp.getHours();
		if(added == true) {
			return true;
		} else {
			return false;
		}
		
	}

	
	public int numHours(String firstName, String lastName) {
		//checks if the parameters are valid
		if(firstName == null || lastName == null ||
		   firstName.isEmpty() || lastName.isEmpty()) {
			return -1;
		}

		// temporary Employee object
		Employee temp = null;
		// iterates the array and returns the hours of the employee
		for(int i = 0; i < numEmployees(); i++) {
			if(employees[i] != null && employees[i].equals(firstName, 
															lastName)) {
				temp = employees[i];
				return temp.getHours();
				
			}
		}
		return -1; //returns -1 if employee was not find
	}

	public boolean makeSale(String firstName, String lastName, 
							double saleAmt) {
		//checks if the parameters are valid
		if(firstName == null || lastName == null ||
	       firstName.isEmpty() || lastName.isEmpty() || saleAmt <= 0) {
					return false;
		}
		//increases the sales based on what type of Employee
		for(int i = 0; i < employees.length; i++) {
			if(employees[i] != null && employees[i].equals(firstName, 
															lastName)) {
				employees[i].incrementSales(saleAmt);
				return true;				
			}
		}
		return false;
	}

	public double amtSalesMade(String firstName, String lastName) {
		//checks if the parameters are valid
		if(firstName == null || lastName == null ||
		   firstName.isEmpty() || lastName.isEmpty()) {
			return -1.0;
		}	
		Employee temp = null;
		for(int i = 0; i < employees.length; i++) {
			if(employees[i] != null && employees[i].equals(firstName, 
															lastName)) {
				temp = employees[i];
				//returns the total sales of that employee
				return temp.getSales(); 
			}
		}
		
		return -1; //returns -1 if name was not find
		
	}


	public double getPaycheckAmount(String firstName, String lastName) {
		if(firstName == null || lastName == null ||
				firstName.isEmpty() || lastName.isEmpty()) {
			return -1.0;
		}	
		//finds the employee and returns the pay which they should receive
		//depending on their salary and employee type
		for(int i = 0; i < employees.length; i++) {
			if(employees[i] != null && employees[i].equals(firstName, 
															lastName)) {
				return employees[i].pay();

			}
		}
		return -1; //returns -1 if name is not find
	}

	public double getPayroll() {
		double payroll = 0;
		//adds up the pay of all employees so the company knows
		//how much money they need on their pay roll
		for(int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				payroll += employees[i].pay();
			}
		}
		return payroll;
	}

	
	public void newPayPeriod() {
		//iterates array of employees and sets 
		//the number of hours and sales to 0
		for(int i = 0; i < employees.length; i++) {
			if(employees[i] != null) {
				employees[i].reset();
			}
		}
	}

	
	public double billAmount() {
		//depending on company account, calculates the amount which the 
		//company owes
		if (companyType == FREE) {
			return 0;
		} else {
			return 10*employees.length;
		}
	}

}
