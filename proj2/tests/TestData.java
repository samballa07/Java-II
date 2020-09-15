package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* Although the project grading policies say not to use this form of import
 * (to use explicit imports instead), we have to use it here, because we
 * don't know what classes you will write in the employeeManager package.
 */
import employeeManager.*;

/* This class contains utility methods that create and return example
 * Company objects that the public (and secret) tests can use, to reduce the
 * amount of code needed in different tests to create objects to test the
 * methods with.  You can use these methods in your student tests as well,
 * but don't modify this class, because our version is going to be used on
 * the submit server.  (You can either write your own helper methods in your
 * StudentTests class, and you can add your own classes to the tests
 * package, if you want to use modified versions of these methods.)
 */

public class TestData {

  // Returns a Company with a free account that only has salaried employees,
  // who haven't (yet) worked any hours or made any sales.
  public static Company exampleCompany1() {
    Company company= Manager.createAccount("Gooble", 5);

    company.hireSalariedEmployee("Dolly", "Dolphin", 51012.0);
    company.hireSalariedEmployee("Freddy", "Frog", 68900.0);
    company.hireSalariedEmployee("Geri", "Giraffe", 49946.0);
    company.hireSalariedEmployee("Kourtney", "Koala", 59410.0);

    return company;
  }

  // Returns a Company with a paid account that only has commissioned
  // employees, who haven't (yet) worked any hours or made any sales.
  public static Company exampleCompany2() {
    Company company= Manager.createAccount("Microsloth");

    company.hireCommissionedEmployee("Paul", "Platypus", 15.0);
    company.hireCommissionedEmployee("Steve", "Starfish", 15.25);
    company.hireCommissionedEmployee("Timmy", "Termite", 16.0);
    company.hireCommissionedEmployee("Jackie", "Jaguar", 15.75);
    company.hireCommissionedEmployee("Sally", "Salamander", 16.5);
    company.hireCommissionedEmployee("Chippy", "Chipmunk", 10.0);

    return company;
  }

  // Returns a Company with a free account that has a mix of salaried and
  // commissioned employees, who haven't (yet) worked any hours or made any
  // sales.
  public static Company exampleCompany3() {
    Company company= Manager.createAccount("Nvidiot", 50);

    company.hireSalariedEmployee("Lizzie", "Lizard", 48900.0);
    company.hireCommissionedEmployee("Paul", "Platypus", 5.0);
    company.hireCommissionedEmployee("Chippy", "Chipmunk", 10.0);
    company.hireSalariedEmployee("Kourtney", "Koala", 59425.0);
    company.hireCommissionedEmployee("Jackie", "Jaguar", 7.5);
    company.hireCommissionedEmployee("Steve", "Starfish", 8.0);
    company.hireSalariedEmployee("Ginny", "Giraffe", 79950.0);
    company.hireCommissionedEmployee("Sally", "Salamander", 6.5);
    company.hireCommissionedEmployee("Timmy", "Termite", 4.0);
    company.hireSalariedEmployee("Wally", "Walrus", 61000.0);

    return company;
  }

  // Returns a Company with a paid account that has a mix of salaried and
  // commissioned employees, , who haven't (yet) worked any hours or made
  // any sales.
  public static Company exampleCompany4() {
    Company company= Manager.createAccount("Auricle");

    company.hireSalariedEmployee("Holly", "Dolphin", 81016.0);
    company.hireCommissionedEmployee("Freddy", "Frog", 5.0);
    company.hireSalariedEmployee("Geri", "Giraffe", 49959.0);
    company.hireCommissionedEmployee("Kourtney", "Koala", 6.0);
    company.hireSalariedEmployee("Ryan", "Lion", 79950.0);
    company.hireCommissionedEmployee("Bruce", "Moose", 7.5);

    return company;
  }

}
