/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */
package tests;
import employeeManager.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StudentTests {

	@Test
	public void testAddEmployee() {
		Company c = new Company("Company Name");
		c.hireCommissionedEmployee("Seth", "Amballa", 30);
		assertEquals(1, c.numEmployees());
		c.hireCommissionedEmployee("HI", "Amballa", 30);
		assertEquals(2, c.numEmployees());
		
		Company co = Manager.createAccount("Co Name");
		co.hireCommissionedEmployee("Ellen", "Degeneres", 40);
		co.hireCommissionedEmployee("Ellen", "Degeneres", 10);

		assertEquals(1, co.numEmployees());
	}
	
	@Test
	public void testAddEmployee1() {
		Company company= TestData.exampleCompany3();
		
	    assertFalse(company.hireCommissionedEmployee("Sally", "Salamander",
	                                                  16.0));
	}
	
	@Test
	public void testCommissionedWorkHours() {
		Company c = TestData.exampleCompany2();
		c.workHours("Paul", "Platypus", 40);
		
		assertTrue(c.workHours("Paul", "Platypus", 45));

	}
	
	@Test
	public void testSalariedWorkHours() {
		Company c = TestData.exampleCompany1();
		c.workHours("Dolly", "Dolphin", 40);
		assertTrue(c.workHours("Dolly", "Dolphin", 10));
		assertFalse(c.workHours("Dolly", "Dolphin", 45));
		assertEquals(50, c.numHours("Dolly", "Dolphin"));
	}
	
	@Test
	public void testAddEmployee2() {
		Company c = Manager.createAccount("Company", 20);
		assertFalse(c.hireCommissionedEmployee("Seth", "", 4));
		assertFalse(c.hireCommissionedEmployee("", "Amballa", 4));
		assertFalse(c.hireCommissionedEmployee(null, "Amballa", 4));
		assertFalse(c.hireCommissionedEmployee("Seth", null, 4));
		assertFalse(c.hireCommissionedEmployee("Seth", "Amballa", -1));
	}
		
	@Test
	public void testGetEmployeeNames() {
		Company c = TestData.exampleCompany3();
		assertTrue(c.hireCommissionedEmployee("Seth", "Koala", 8.0));
		assertEquals(11, c.numEmployees());
		assertArrayEquals(new String[]{"Chippy Chipmunk", "Ginny Giraffe", 
				"Jackie Jaguar","Kourtney Koala", "Seth Koala", 
				"Lizzie Lizard", "Paul Platypus",
			    "Sally Salamander", "Steve Starfish", 
			    "Timmy Termite", "Wally Walrus"},
		c.getEmployeeNames());
	}

	@Test
	public void testSalariedAmtSalesMade() {
		Company c = TestData.exampleCompany3();
		
		assertTrue(c.makeSale("Kourtney", "Koala", 40));
		assertEquals(0, c.amtSalesMade("Kourtney", "Koala"), 0.001);
	}
	
	@Test
	public void testNonexistentEmployee() {
		Company c = TestData.exampleCompany3();
		assertFalse(c.workHours("Seth", "Amballa", 12));
		assertEquals(-1, c.numHours("Seth", "Amballa"));
		assertFalse(c.makeSale("Seth", "Amballa", 89));
		assertEquals(-1, c.amtSalesMade("Seth", "Amballa"), 0.001);
	}
	
	
}

