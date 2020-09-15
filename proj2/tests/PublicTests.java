package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* Some tests use a form of assertEquals() that is used to compare two
 * floating-point (real) numbers, which has three arguments:
 * assertEquals(double1, double2, delta).  It will say that the two doubles
 * are equal if their values are within delta of each other.  For instance,
 * a call like assertEquals(2.5, 2.501, 0.01) will be true, while
 * assertEquals(2.5, 2.55, 0.01) will fail.  Comparing real numbers this way
 * is needed due to the imprecision involved with doing arithmetic with
 * them.
 */

/* Although the project grading policies say not to use the form of import
 * using '*' (you should use explicit imports instead), we have to use it in
 * the next line here, because we don't know what classes different students
 * will write in the employeeManager package, so we can't explicitly import
 * them.
 */
import employeeManager.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Just tests creating some Company objects, of different types, and
  // calling getCompanyName() on them.
  @Test public void testPublic1() {
    Company company1= Manager.createAccount("Gooble");
    Company company2= Manager.createAccount("InstaSnap", 10);

    assertEquals("Gooble", company1.getCompanyName());
    assertEquals("InstaSnap", company2.getCompanyName());
  }

  // Tests hiring some employees for a company and calling numEmployees().
  @Test public void testPublic2() {
	
    assertEquals(4, TestData.exampleCompany1().numEmployees());
  }

  // Tests calling employeeCapacity() for companies with free accounts.
  @Test public void testPublic3() {
    Company company1= TestData.exampleCompany1();
    Company company2= TestData.exampleCompany3();

    assertEquals(5, company1.employeeCapacity());
    assertEquals(50, company2.employeeCapacity());
  }

  // Tests calling getEmployeeNames() for a company that has employees who
  // are all of the same type, and verifies that the names are in proper
  // alphabetical order in the returned array.
  @Test public void testPublic4() {
    Company company= TestData.exampleCompany2();

    assertArrayEquals(new String[]{"Chippy Chipmunk", "Jackie Jaguar",
                                   "Paul Platypus", "Sally Salamander",
                                   "Steve Starfish", "Timmy Termite"},
                      company.getEmployeeNames());
  }

  // Tests calling getEmployeeNames() for a company that has employees who
  // are of different types.
  @Test public void testPublic5() {
    Company company= TestData.exampleCompany3();

    assertArrayEquals(new String[]{"Chippy Chipmunk", "Ginny Giraffe",
                                   "Jackie Jaguar", "Kourtney Koala",
                                   "Lizzie Lizard", "Paul Platypus",
                                   "Sally Salamander", "Steve Starfish",
                                   "Timmy Termite", "Wally Walrus"},
                      company.getEmployeeNames());
  }

  // Tests trying to try to hire employees who have the same names as
  // existing employees, which should fail.
  @Test public void testPublic6() {
    Company company= TestData.exampleCompany3();
    assertFalse(company.hireSalariedEmployee("Kourtney", "Koala", 89530.0));
    assertFalse(company.hireCommissionedEmployee("Sally", "Salamander",
                                                  16.0));
    assertEquals(10, company.numEmployees());
  }

  // Tests trying to hire more employees than a free account company's
  // capacity.
  @Test public void testPublic7() {
    Company company= TestData.exampleCompany1();

    assertTrue(company.hireSalariedEmployee("Leanne", "Lemur", 14.65));
    assertFalse(company.hireSalariedEmployee("Sheila", "Sheep", 34500.0));
    assertEquals(5, company.employeeCapacity());
    assertEquals(5, company.numEmployees());
  }

  // Tests the basic operation of workHours() and numHours().
  @Test public void testPublic8() {
    Company company= TestData.exampleCompany4();

    assertTrue(company.workHours("Holly", "Dolphin", 8));
    assertTrue(company.workHours("Holly", "Dolphin", 9));
    assertTrue(company.workHours("Holly", "Dolphin", 7));
    assertTrue(company.workHours("Holly", "Dolphin", 9));
    assertTrue(company.workHours("Geri", "Giraffe", 11));
    assertTrue(company.workHours("Geri", "Giraffe", 10));
    assertTrue(company.workHours("Geri", "Giraffe", 9));
    assertTrue(company.workHours("Ryan", "Lion", 8));

    assertEquals(33, company.numHours("Holly", "Dolphin"));
    assertEquals(0, company.numHours("Freddy", "Frog"));
    assertEquals(30, company.numHours("Geri", "Giraffe"));
    assertEquals(0, company.numHours("Kourtney", "Koala"));
    assertEquals(8, company.numHours("Ryan", "Lion"));
    assertEquals(0, company.numHours("Bruce", "Moose"));
  }

  // Tests the basic operation of getPaycheckAmount() for a salaried
  // employee.
  @Test public void testPublic9() {
    Company company= TestData.exampleCompany4();
    
    assertEquals(3116.0, company.getPaycheckAmount("Holly", "Dolphin"),
                 0.001);
  }

  // Tests the basic operation of getPaycheckAmount() for commissioned
  // employees.
  @Test public void testPublic10() {
    Company company= TestData.exampleCompany4();

    company.makeSale("Freddy", "Frog", 15000.0);
    company.makeSale("Freddy", "Frog", 30000.0);
    company.makeSale("Kourtney", "Koala", 27000.0);
    company.makeSale("Kourtney", "Koala", 32000.0);

    assertEquals(2250.0, company.getPaycheckAmount("Freddy", "Frog"),
                 0.001);
    assertEquals(3540.0, company.getPaycheckAmount("Kourtney", "Koala"),
                 0.001);
    // Bruce Moose did not make any sales
    assertEquals(0.0, company.getPaycheckAmount("Bruce", "Moose"), 0.001);
  }

  // Tests nonexistent employees trying to work hours and make sales.
  @Test public void testPublic11() {
    Company company= TestData.exampleCompany4();

    assertFalse(company.workHours("Antonio", "Antelope", 1));
    assertFalse(company.makeSale("Quinn", "Quokka", 1000.0));
  }

  // Tests trying to call workHours() with a negative number of hours.
  @Test public void testPublic12() {
    Company company= TestData.exampleCompany3();

    assertTrue(company.workHours("Lizzie", "Lizard", 10));
    assertTrue(company.workHours("Timmy", "Termite", 11));
    assertFalse(company.workHours("Lizzie", "Lizard", -2));
    assertFalse(company.workHours("Timmy", "Termite", -2));
    assertEquals(10, company.numHours("Lizzie", "Lizard"));
    assertEquals(11, company.numHours("Timmy", "Termite"));
    
  }

  // Tests that employees of both types are able to work more than 40 hours
  // in a pay period.
  @Test public void testPublic13() {
    Company company= TestData.exampleCompany3();
    int i;

    for (i= 1; i <= 12; i++) {
      assertTrue(company.workHours("Kourtney", "Koala", 4));
      assertTrue(company.workHours("Jackie", "Jaguar", 4));
    }

    assertEquals(48, company.numHours("Kourtney", "Koala"));
    assertEquals(48, company.numHours("Jackie", "Jaguar"));

  }

  // Tests trying to call makeSale() with a negative sale amount.
  @Test public void testPublic14() {
    Company company= TestData.exampleCompany2();

    assertTrue(company.makeSale("Chippy", "Chipmunk", 1000.0));
    assertFalse(company.makeSale("Chippy", "Chipmunk", -200.0));
    assertTrue(company.makeSale("Chippy", "Chipmunk", 2000.0));
    assertEquals(3000.0, company.amtSalesMade("Chippy", "Chipmunk"), 0.001);
  }

  // Tests calling getPayroll().
  @Test public void testPublic15() {
    Company company= TestData.exampleCompany1();

    assertEquals(8818.0, company.getPayroll(), 0.001);

  }

  // Tests calling billAmount() on companies that have free accounts.
  @Test public void testPublic16() {
    Company company1= Manager.createAccount("Applesauce", 10);
    Company company2= TestData.exampleCompany1();

    assertEquals(0.0, company1.billAmount(), 0.001);
    assertEquals(0.0, company2.billAmount(), 0.001);
  }

  // Tests calling billAmount() on companies that have paid accounts.
  @Test public void testPublic17() {
    Company company1= Manager.createAccount("Instaounce");
    Company company2= Manager.createAccount("Sungsam");

    company2.hireSalariedEmployee("Dolly", "Dolphin", 51012.0);
    company2.hireSalariedEmployee("Freddy", "Frog", 68900.0);
    company2.hireSalariedEmployee("Geri", "Giraffe", 49946.0);
    company2.hireSalariedEmployee("Kourtney", "Koala", 59410.0);

    assertEquals(0.0, company1.billAmount(), 0.001);
    assertEquals(40.0, company2.billAmount(), 0.001);
  }

}
