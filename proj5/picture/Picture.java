/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */

package picture;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Picture implements Iterable<Shape>{

  private static class Node {
    private Shape data;
    private Node next;
    private Node prev;
  }

  private Node head;
  private Node tail;
  private int numShapes;

  public Picture() {
    head= tail= null;
    numShapes= 0;
  }
  
  public Iterator<Shape> iterator(){
	  return new MyIterator();
  }
  
  private class MyIterator implements Iterator<Shape>{
	  
	  private Node curr;
	  private Node prevCurr;
	  
	  public MyIterator() {
		  curr = head;
		  prevCurr = null;
	  }
	  public boolean hasNext() {
		  return curr != null;
	  }
	  
	  public Shape next() throws NoSuchElementException {
		  if(hasNext() == false) {
			  throw new NoSuchElementException();
		  }
		  Shape currData = curr.data;
		  prevCurr = curr;
		  curr = curr.next;
		  return currData;
	  }
	  
	  public void remove() throws IllegalStateException {
		  if(prevCurr == null) {
			  throw new IllegalStateException();
		  }
		  if (prevCurr == head) {
			  head = head.next;
			  
		  } else if (prevCurr == tail) {
			  Node tempPrev = tail.prev;
			  tempPrev.next = null;
			  tail = tempPrev;
		  } else {
			  Node tempNext = prevCurr.next;
			  Node tempPrev = prevCurr.prev;
			  
			  tempNext.prev = tempPrev;
			  tempPrev.next = tempNext;
		  }
		  
		  prevCurr = null;
		  numShapes--;
	  }
  }

  // prepend newShape to the doubly-linked list of shapes being stored, but
  // just do nothing if newSape is null
  public void addShape(Shape newShape) {
    Node newNode;

    if (newShape != null) {
      newNode= new Node();
      newNode.data= newShape;

      newNode.next= head;
      if (head != null)
        head.prev= newNode;
      else tail= newNode;  // if the list is currently empty

      head= newNode;

      numShapes++;
    }
  }

  // for use in debugging
  @Override
  public String toString() {
    String result= "";
    Node current= head;

    while (current != null) {
      result += (current != head ? " " : "") + current.data;
      current= current.next;
    }

    return result;
  }

}
