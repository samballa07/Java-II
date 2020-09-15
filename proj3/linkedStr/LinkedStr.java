/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */

package linkedStr;

import java.lang.IndexOutOfBoundsException;

/*
 * This class structures a linked list object which simulates a string
 * type. The entire list itself is one string and each element or node
 * is a character of that string. The LinkedStr class implements
 * Comparable so a compareTo method is needed.
 */
public class LinkedStr implements Comparable<LinkedStr> {

	//inner class which lets the outer class access everything
	private static class Node {

		private Character data;
		private Node next;

		private Node(char charIn) {
			data = charIn; // holds the data of the list
			next = null; // is a pointer to the next Node in the list
		}
	}

	private Node head; //first node in the list
	private int size; // tracks the size of the list
	
	// constructor
	public LinkedStr() {
		// initializes value for the head null and size
		head = null;
		size = 0;
}

	// copy constructor, deep copy
	public LinkedStr(LinkedStr otherStr) {
		
		//checks if the otherStr is empty
		if(otherStr == null || otherStr.head.data == ' ') {
			head = null;
		} else {
			Node curr = otherStr.head;
			/*
			 *  traverses the otherStr list and makes the current object
			 *  a deep object of the parameter
			 */
			while (curr != null) {
				append(curr.data);
				curr = curr.next;
			}
		}
	}

	/*
	 * creates a node with the parameter as the data and adds it to
	 * the end of the list
	 */
	public LinkedStr append(char ch) {
		// initializes a new Node with "ch" as the data
		Node newNode = new Node(ch);
		
		// checks if the list is empty and adds it to the head Node
		if(head == null) {
			head = newNode;
			return this; //returns the current LinkedStr object
		}
		
		// makes the new Node point to null since it is now the new tail
		newNode.next = null; 
		
		Node curr = head;
		// traverses the loop to find the last node and stores it in curr
		while (curr.next != null) {
			curr = curr.next; // moves to the next node
		}
		
		// points the original last node's next tp the new node
		curr.next = newNode; 
		size++;
		return this;
	}

	public int length() {
		
		int count = 0; // counter to keep track of how many nodes there are
		
		// traverses the list until the end
		Node curr = head;
		while(curr != null) {
			count++; //increments count for each iteration
			curr = curr.next;
		}
		return count; // returns the length of the list
	}

	// finds the character in the list at the position from param
	public Character getCharAt(int position) {
		
		// checks that the parameter is valid
		if(position < 0 || position >= length()) {
			return null;
		}
		
		int index = 0; // index 
		Node curr = head;
		
		// stops traversing when index is equal to position
		while(index != position) {
			curr = curr.next;
			index++;
		}

		return curr.data; //returns the character at that node
	}

	public void reset() {
		this.head.data = null;
		this.head = null; //resets the head to null
		size = 0; //changes my size instance var to 0
	}

	public void insert(int position, char ch) 
			throws IndexOutOfBoundsException {
		
		// checks that the parameter is valid
		if(position > size || position < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		size++; // increments the size of the list by 1
		
		// initializes a new Node with the parameter char as the data
		Node newNode = new Node(ch);
		
		// declares nodes to keep track of curr and prev nodes
		Node curr = head, prev = null;
		
		int index = 0; 
		
		// traverses the list until the last node or until index=position
		while(curr != null && index < position) {
			prev = curr; //sets the prev equal to curr
			curr = curr.next; // moves curr to the next node
			index++; // increments index by 1

		}
		// the newNode's next, points to the curr node
		newNode.next = curr;
		
		if(index == 0)
			
			// new Node is head when the index stays 0
			head = newNode;
		
		else
			// sets the previous' next to the new node
			prev.next = newNode;	

	}

	public void concat(LinkedStr otherStr) {
		
		// checks if the otherStr object is null
		if(otherStr != null) {
			
			Node curr = otherStr.head;
			
			// traverses the otherStr list until the end
			while (curr != null) {
				
				//appends each node of the otherStr to the current object
				append(curr.data);
				
				//increments size
				size++;
				
				// moves to the next node
				curr = curr.next;
			}
		}
	}

	public int findFirst(int position, char ch) {
		
		// checks that the parameter position is valid
		if(position > size || position < 0) {
			return -1;
		}
		
		
		Node curr = head;
		int index = 0;

		// traverses the list until the end
		while(curr != null) {
			
			// checks if the index is greater than the position given
			if(index >= position) {

				// checks if the char in that node is the same as param
				if (curr.data == ch) 
					return index; //returns that node's index
			}
			index++;
			curr = curr.next;
		}

		return -1; //returns 1 if nothing is found
	}

	public void deleteCharsAt(int position, int numChars) 
			throws IndexOutOfBoundsException {
		
		// Exception is thrown is position is not valid
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		
		// checks that numChars is valid and returns if otherwise
		if(numChars < 1) {
			return;
		}
		
		/* 
		 * temp nodes that keep track of the current node, the first
		 * node of the sequence, and the last
		 */
		Node curr = head;
		Node first = null;
		Node last = null;
		
		int index = 0; // tracks the number of iterations
		
		// boolean flag wgich indicates whether the position is 0
		boolean changed = false;
		
		// the last index of the nodes which are deleted
		int endIndex = (position + numChars);
		
		if (position == 0) {
			// if position is 0, then first node will be the new head
			first = head;
			// boolean is flagged and changed to true
			changed = true;
		}
		
		//traverses list until the end
		while(curr != null && index < size) {
			
			/* 
			 * checks that the position is not zero and that curr is
			 * in the index previous to position
			 */
			if((index+1) == position && changed != true) {
				first = curr; // sets first to the current node
			}
			
			//checks that the index is equal to the endIndex
			if(index >= position && endIndex == index) {
				last = curr;
			}
			index++;
			curr = curr.next;
		}
		
		//checks that the position is 0
		if(changed == true) {
			head = last;
		}
		
		/* 
		 * makes the first node now point to the second node found
		 * so that all the nodes in the middle will be deleted
		 */
		first.next = last;
		size -= numChars; // decrements size by the num of chars deleted
	}

	public int compareTo(LinkedStr otherStr) {
		// throws an exception of otherStr is null
		if(otherStr == null) {
			throw new NullPointerException();
		}
		
		// two nodes are initialized and set equals to the heads
		Node curr = head, otherCurr = otherStr.head;
	
		// traverses until either of the lists ends
		while(curr != null || otherCurr != null) {
			
			/* 
			 * returns a negative or positive number depending on
			 * whether the current char is either less than or
			 * more than the otherStr char
			 */
			if(curr.data.compareTo(otherCurr.data) < 0) {
				return -1;
				
			} else if (curr.data.compareTo(otherCurr.data) > 0) {
				return 1;
			}
			
			/*
			 * Checks if the strings are identical but with one being
			 * longer than the other.
			 * Returns a positive number if the current objects string 
			 * is the same as the other objects string but longer. And 
			 * returns a negative number if it is the other way around
			 */
			if(curr.data == otherCurr.data && 
					curr.next == null && otherCurr.next != null) {
				return -1;
				
			} else if(curr.data == otherCurr.data && 
					curr.next != null && otherCurr.next == null) {
				return 1;
			}
			
			curr = curr.next;
			otherCurr = otherCurr.next;
		}
		
		// 0 is returned since none of the other cases met; they are equal
		return 0;
	}
}
