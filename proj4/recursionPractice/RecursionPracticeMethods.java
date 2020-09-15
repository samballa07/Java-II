/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */

package recursionPractice;

/* 
 * This class consists of four different methods which implement
 * recursion. Each method has a different task which deals with list 
 * iteration and manipulation. The list type uses a generic type T and 2
 * of the methods implement the Comparable interface so must use the
 * compareTo() method 
 */
public class RecursionPracticeMethods {

	/*
	 * checks that one list is a sub-list of the other meaning that
	 * the list has the same elements as the other with the same order.
	 */
	public static <T> boolean foundInOrder(ListyList<T> list1,
			ListyList<T> list2) {

		// first checks that both the lists aren't null
		// throws exception if one of them is
		if(list1 == null || list2 == null) 
			throw new IllegalArgumentException();

		
		// calls the helper with the sizes of both lists as new args
		return foundInOrderHelper(list1, list2, list1.size(), 
									list2.size());
	}

	// helper function
	public static <T> boolean foundInOrderHelper(ListyList<T> list1,
          										ListyList<T> list2,
          										int pos1,
          										int pos2) {
		
	  // returns true if the position of the smaller list hits zero
	  if(pos1 == 0) 
		  return true;
		  
	  // returns false if the position of the bigger list hits zero
	  else if(pos2 == 0) 
		  return false;
		  
	  /* 
	   * checks to see if the element before the current one is equal
	   * for both lists
	   */
	  if(list1.get(pos1-1).equals(list2.get(pos2-1))) 
		  // position for first list decrements
		  pos1 = pos1 - 1;
	  
	  // the bigger list's position decreases always
	  return foundInOrderHelper(list1, list2, pos1, pos2-1);
		  
	} 

	
	/*
	 * finds the first element in the list which is between the
	 *  two elements given as a parameter
	 */
	public static <T extends Comparable<T>>
           T firstBetween(ListyList<T> list, T elt1, T elt2) {
		
		// calls the helper function with a position argument as 0
		return firstBetweenHelper(list, elt1, elt2, 0);
	}
  
	public static <T extends Comparable<T>> 
  		T firstBetweenHelper(ListyList<T> list, T elt1, T elt2, int pos) {
	  
		// returns null if the position goes to the end of the list 
		// and no element is found
		if(pos == list.size()) {
			return null;

			/* 
			 * checks that the current element is between both the elements
			 */
		} else if(list.get(pos).compareTo(elt1) > 0 && 
				list.get(pos).compareTo(elt2) < 0) {

			// returns that element
			return list.get(pos);

		} else {

			// recursive call with the position moving up by one
			return firstBetweenHelper(list, elt1, elt2, pos+1);
		}
	}
  		

	/* 
	 * this method iterates two ListyList objects recursively and 
	 * changes the first list in the first parameter so that the larger
	 * element of the two lists(in that specific position) exists in the
	 * first list. This method implements the Comparable interface to 
	 * accommodate java types that utilize compareTo
	 */
	public static <T extends Comparable<T>>
	void largerOf(ListyList<T> list1, ListyList<T> list2) {

		// calls the helper function which has a position arg
		largerOfHelper(list1, list2, 0);
	}

	// helper function
	public static <T extends Comparable<T>> void 
	largerOfHelper(ListyList<T> list1, ListyList<T> list2, int pos) {

		// leaves method if either position hits the end of list
		if(pos >= list2.size() || pos >= list1.size()) 
			return;

		/* 
		 * if the element in the 2nd list is larger, then the first list
		 * will change so that the the bigger element is now in that 
		 * position
		 */
		else if(list1.get(pos).compareTo(list2.get(pos)) < 0)   
			list1.set(pos, list2.get(pos));


		// recursive case, increments the position
		largerOfHelper(list1, list2, pos+1);

	}

	/*
	 * iterates through the ListyList object passed as a parameter 
	 * recursively and removes duplicates which are right next 
	 * to each other
	 */
	public static <T> ListyList<T> removeDuplicates(ListyList<T> list) {

		// initializes a new ListyList object
		ListyList<T> newList = new ListyList<T>();

		/* 
		 * calls the helper function which has arguments for the 
		 * new list and for keeping track of position
		 */
		return removeDuplicatesHelper(list, newList, 0);
	}
  
	// helper method
	public static <T> ListyList<T> removeDuplicatesHelper
	(ListyList<T> list, ListyList<T> newList, int pos){

		// checks that the position does not hit the end of the list
		if((pos) == list.size()) {
			return newList; //returns the new ListyList object

		// checks that the current element is not the same as the next one
		} else if(!(list.get(pos).equals(list.get(pos+1)))) {
			// will add that element if so
			newList.add(list.get(pos));

		}

		// recursive call with position changing by one
		return removeDuplicatesHelper(list, newList, pos+1);
	}

}
