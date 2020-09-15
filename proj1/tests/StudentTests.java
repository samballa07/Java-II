/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */
package tests;

import quuly.Quuly;
import org.junit.*;
import static org.junit.Assert.*;

public class StudentTests {

	// This test makes sure that the student was added correctly to Quuly object
	@Test
	public void testAddQueue() {
		Quuly q = new Quuly(1);
		q.addStudent("Adam", 1234);
		assertTrue(q.addStudentToQueue(1234));
		assertFalse(q.addStudentToQueue(2222));
		assertEquals(1, q.numStudents());
	}

	// this test makes sure the helpNextStudent() method works correctly.
	@Test
	public void testHelpStudent() {
		Quuly q = new Quuly(1);
		q.addStudent("Seth", 1234);
		assertFalse(q.helpNextStudent());

		q.addStudentToQueue(1234);
		assertTrue(q.helpNextStudent());
		assertFalse(q.helpNextStudent()); // returns false when called again
	}

	// tests that the student can only join the amount of times under maxVisits
	@Test
	public void testMaxVisits() {
		Quuly q = new Quuly(2); // maxVisits is 2
		q.addStudent("Seth", 1234);
		q.addStudentToQueue(1234);
		q.helpNextStudent();
		assertTrue(q.addStudentToQueue(1234));
		q.addStudentToQueue(1234);
		q.helpNextStudent();
		assertFalse(q.addStudentToQueue(1234)); // should fail this time since student already was helped twice

	}

	// tests the maxVisits functionality again with a different parameter
	@Test
	public void testMaxVisits2() {
		Quuly q = new Quuly(3);
		q.addStudent("Seth", 1234);
		q.addStudentToQueue(1234);
		q.helpNextStudent();
		assertTrue(q.addStudentToQueue(1234));
		q.addStudentToQueue(1234);
		q.helpNextStudent();
		assertTrue(q.addStudentToQueue(1234));
		q.addStudentToQueue(1234);
		q.helpNextStudent();
		assertFalse(q.addStudentToQueue(1234));

	}

	// tests the maxVisits functionality again with a different parameter
	@Test
	public void testMaxVisits3() {
		Quuly q = new Quuly(1);
		q.addStudent("Seth", 1234);
		q.addStudentToQueue(1234);
		q.helpNextStudent();
		assertFalse(q.addStudentToQueue(1234));
	}

	// tests the functionality of the dropStudent method
	@Test
	public void testDropStudent() {
		Quuly q = new Quuly(1);
		q.addStudent("Seth", 1234);
		q.addStudentToQueue(1234);
		// checks that the student was added to queue and students list
		assertEquals(1, q.numStudents());
		assertEquals(1, q.queueSize());
		q.helpNextStudent();
		q.dropStudent(1234);
		// checks that the student isnt in queue and students list
		assertEquals(0, q.numStudents());
		assertEquals(0, q.queueSize());
	}

	// tests that addStudent fails when id is negative
	@Test
	public void testID() {
		Quuly q = new Quuly(1);
		assertFalse(q.addStudent("Seth", -1111));
	}

}
