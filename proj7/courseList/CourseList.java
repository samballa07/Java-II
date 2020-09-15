/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized  
 * assistance on this assignment
 */

package courseList;

import java.util.Map;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;

public class CourseList {

	private Map<String, Collection<String>> courses;

	public CourseList() {
		courses = new TreeMap<String, Collection<String>>();
	}

	public void takeCourse(String student, String course) 
			throws IllegalArgumentException {
		
		if (student == null || course == null) {
			throw new IllegalArgumentException();
		}

		if (courses.containsKey(student)) {

			Collection<String> courseSet = courses.get(student);

			if (courseSet.isEmpty()) {

				courseSet = new HashSet<String>();
				courseSet.add(course);
				courses.put(student, courseSet);

			} else if (!courseSet.contains(course)) {
				courseSet.add(course);
			}

		} else if (!courses.containsKey(student)) {

			Collection<String> newSet = new HashSet<String>();
			newSet.add(course);
			courses.put(student, newSet);
		}
	}

	public boolean isTakingCourse(String student, String course) 
			throws IllegalArgumentException {

		if (student == null || course == null) {
			throw new IllegalArgumentException();
		}

		if (courses.containsKey(student)) {

			if ((courses.get(student)).contains(course)) {
				return true;
			}

		} else if (!courses.containsKey(student)) {
			return false;

		} else {

			boolean exists = false;
			
			for (Collection<String> set : courses.values()) {
				for (String c : set) {
					
					if (c.equals(course)) {
						exists = true;
					}
				}
			}
			if (exists == false)
				return exists;
		}

		return false;

	}

	public int numCoursesTaking(String student)
			throws IllegalArgumentException {

		if (student == null) {
			throw new IllegalArgumentException();
		}

		if (courses.get(student) == null) {
			return 0;
		}
		return courses.get(student).size();

	}

	public int numEnrolled(String course) 
			throws IllegalArgumentException {

		if (course == null) {
			throw new IllegalArgumentException();
		}

		int num = 0;

		for (Collection<String> set : courses.values()) {
			if (set.contains(course)) {
				num++;
			}
		}
		return num;
	}

	public String mostPopularCourse() {
		
		int max = -1;
		String courseStr = "";
		
		for (Collection<String> set : courses.values()) {
			for (String str : set) {
				
				if (numEnrolled(str) > max) {
					
					max = numEnrolled(str);
					courseStr = str;
				}
			}
		}
		if (max == -1) {
			return null;
		}
		return courseStr;
	}

	public boolean dropCourse(String student, String course) 
			throws IllegalArgumentException {

		if (student == null || course == null) {
			throw new IllegalArgumentException();
		}

		if (courses.containsKey(student)) {
			if (courses.get(student).contains(course)) {
				
				courses.get(student).remove(course);
				return true;
			}
		}
		return false;

	}

}
