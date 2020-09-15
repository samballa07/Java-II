/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */

package quuly;

import java.util.ArrayList;

/*
 * This class is a Quuly type which emulates a TA hours technology. Stores the total amount
 * of students with an ArrayList as well as another ArrayList that stores students who join the queue
 * The amount of students who are helped is tracked as well as the a maximum number of visits
 */
public class Quuly {

	// field
	private int maxVisits; // stores the max number of times that a student can visit TA hours
	private int numHelped; // keeps track of the total number of students helped in TA hours
	private ArrayList<Student> students = new ArrayList<>();
	private ArrayList<Student> queue = new ArrayList<>();

	// constructor
	public Quuly(int maxVisits) {
		if (maxVisits <= 0) {
			this.maxVisits = 1;
		} else {
			this.maxVisits = maxVisits;
		}
		numHelped = 0; // initializes numHelped
	}

	public boolean addStudent(String newStudentName, Integer newStudentID) {
		// checks if there is already a student w/ that id and returns false if true
		for (Student curr : students) {

			if (curr.isIdEqual(newStudentID)) {
				return false;
			}
		}

		// returns false for the following cases
		if (newStudentName == "" || newStudentID < 0 || newStudentID == null || newStudentName == null) {
			return false;
		} else {
			// adds a new student object to the "students" ArrayList
			Student newStudent = new Student(newStudentName, newStudentID);
			students.add(newStudent);
			return true;
		}
	}

	public int numStudents() {
		// size of the list corresponds to the number of students in the Quuly object
		return students.size();
	}

	public boolean addStudentToQueue(Integer id) {
		if (id == null) {
			return false;
		}

		// checks to see that the id in parameter was not already added
		for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i).isIdEqual(id)) {
				return false;
			}
		}

		for (int i = 0; i < students.size(); i++) {
			// first checks to see if the parameter id exists in the Quuly
			if (students.get(i).isIdEqual(id)) {
				// makes sure that student hasn't exceeded maxVisits and returns false if so
				if (students.get(i).getNumTimesHelped() >= maxVisits) {
					return false;
				} else {
					queue.add(students.get(i));
					return true;
				}
			}
		}

		return false;
	}

	public int queueSize() {
		return queue.size();
	}

	public boolean isInQueue(Integer id) {
		// returns false is parameter Integer is null or if the queue is empty
		if (id == null || queue.size() == 0) {
			return false;
		}
		// returns true if the id from parameter was found in queue
		for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i).isIdEqual(id)) {
				return true;
			}
		}
		return false;
	}

	public int isInQueue(String name) {
		if (name == null || queue.size() == 0) {
			return 0;
		}
		int count = 0; // keeps track of how many instances the parameter name is found
		for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i).isNameEqual(name)) {
				count++; // increments count if the name was found in the queue
			}
		}
		if (count > 0) {
			return count;
		} else {
			return 0; // returns 0 if no instances were found
		}
	}

	public boolean helpNextStudent() {
		// returns false if queue is empty
		if (queue.size() == 0) {
			return false;
		} else {
			// increments the student object's numTimesHelped
			queue.get(0).incrementTimesHelped();
			queue.remove(0); // removed from queue
			numHelped++; // incremented to keep track of the total people helped in Quuly object
			return true;
		}
	}

	public int numStudentsHelped() {
		return numHelped;
	}

	public int numTimesHelped(Integer id) {
		// returns the number of times the student with that id was helped so far
		for (Student curr : students) {
			if (curr.isIdEqual(id)) {
				return curr.getNumTimesHelped();
			}
		}

		boolean studentExists = false; // used to indicate whether student is in Quuly object
		for (Student currStudent : students) {
			if (currStudent.isIdEqual(id)) {
				studentExists = true;
				break; // leaves loop when student is found
			}
		}
		if (studentExists == true) {
			return 0; // returns 0 when student is in Quuly object but hasn't joined the queue yet.
		} else {
			return -1;
		}
	}

	public boolean dropStudent(Integer id) {
		if (id == null) {
			return false;
		}

		// indicates whether the student was found and removed from Quuly object
		boolean studentRemoved = false;

		// removes the student object from students ArrayList if id is found
		for (Student curr : students) {
			if (curr.isIdEqual(id)) {
				students.remove(curr);
				studentRemoved = true;
				break;
			}
		}

		// depending on if the id was found in the students ArrayList, this if-statement
		// will run
		if (studentRemoved == true) {
			for (Student currStud : queue) {
				if (currStud.isIdEqual(id)) {
					queue.remove(currStud);
					break;
				}
			}
		}
		if (studentRemoved == true) {
			return true;
		} else {
			return false;
		}
	}

}
