/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */
package quuly;

/*
 * This class is a Student type which stores info about the student such as their ID, name, 
 * and number of times which they were helped in TA hours. This type is used in the Quuly class 
 * as an object stored in an ArrayList
 */
public class Student {

	private String name;
	private Integer studentId;
	private Integer numTimesHelped; // tracks the number of times which the student joined the queue

	// constructor
	public Student(String nameIn, Integer newStudentId) {
		name = nameIn;
		studentId = newStudentId;
		numTimesHelped = 0;

	}

	// checks of the current student id number is equal to one passed through
	// parameter
	public boolean isIdEqual(Integer otherStudentID) {
		if (otherStudentID.equals(studentId)) {
			return true;
		} else {
			return false;
		}
	}

	// checks that the current student name is equal to the one passed through
	// parameter
	public boolean isNameEqual(String otherName) {
		if (otherName.equals(name)) {
			return true;
		} else {
			return false;
		}
	}

	// get method for the numTimesHelped var
	public int getNumTimesHelped() {
		return numTimesHelped;
	}

	// increments the number of times the student has been helped by 1
	public void incrementTimesHelped() {
		numTimesHelped++;
	}
}
