package employeeManager;

public class Manager {	
	
  public static Company createAccount(String name) {
	  if (name == null || name.equals("")) {
		  return null;
	  } 
	  Company account = new Company(name);
	  return account;
	  
  }

  public static Company createAccount(String name, int maxEmployees) {
	  if (name == null || name.equals("") || maxEmployees < 0) {
		  return null;
	  }
	  Company account = new Company(name, maxEmployees);
	  return account;
  }

}
