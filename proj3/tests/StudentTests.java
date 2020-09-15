/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized
 * assistance on this assignment
 */

package tests;

import org.junit.*;
import static org.junit.Assert.*;
import linkedStr.LinkedStr;

public class StudentTests {

	/* 
	 * checks that the character is inserted even when the position is
	 * equal to the length of the string
	 */
	@Test public void testInsert() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'a');
	    str.insert(1, 'm');
	    str.insert(2, 'b');
	    str.insert(3, 'a');
	    str.insert(4, 'l');
	    str.insert(5, 'l');
	    str.insert(6, 'a');
	    str.insert(str.length(), 's');
	    
	    assertEquals("amballas", TestUtilities.toString(str));
	}
	
	// tests that delete works when numChars is longer than the length
	@Test public void testDelete() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'a');
	    str.insert(1, 'm');
	    str.insert(2, 'b');
	    str.insert(3, 'a');
	    str.insert(4, 'l');
	    str.insert(5, 'l');
	    str.insert(6, 'a');
	    
	    str.deleteCharsAt(3, 15);
	    assertEquals("amb", TestUtilities.toString(str));

	}

	// tests that delete works when the position is zero
	@Test public void testDelete1() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'a');
	    str.insert(1, 'm');
	    str.insert(2, 'b');
	    str.insert(3, 'a');
	    str.insert(4, 'l');
	    str.insert(5, 'l');
	    str.insert(6, 'a');
	    
	    str.deleteCharsAt(0, 1);
	    assertEquals("mballa", TestUtilities.toString(str));

	}
	
	// tests that delete works when numCHars is 0 and nothing is changed
	@Test public void testDelete2() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'a');
	    str.insert(1, 'm');
	    str.insert(2, 'b');
	    str.insert(3, 'a');
	    str.insert(4, 'l');
	    str.insert(5, 'l');
	    str.insert(6, 'a');
	    
	    str.deleteCharsAt(5, 0);
	    assertEquals("amballa", TestUtilities.toString(str));

	}
	
	// tests that 0 is returned if it is the same string
	@Test public void testCompare() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'a');
	    str.insert(1, 'm');
	    str.insert(2, 'b');
	    str.insert(3, 'a');
	    str.insert(4, 'l');
	    str.insert(5, 'l');
	    str.insert(6, 'a');
	    
	    LinkedStr ostr = new LinkedStr();
		ostr.insert(0, 'a');
	    ostr.insert(1, 'm');
	    ostr.insert(2, 'b');
	    ostr.insert(3, 'a');
	    ostr.insert(4, 'l');
	    ostr.insert(5, 'l');
	    ostr.insert(6, 'a');
	    
	    assertEquals(0, str.compareTo(ostr));
	}
	
	// tests the basic functionality of compareTo()
	@Test public void testCompare1() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'a');
	    str.insert(1, 'm');
	    str.insert(2, 'b');
	    str.insert(3, 'a');
	    str.insert(4, 'l');
	    str.insert(5, 'l');
	    str.insert(6, 'a');
	    
	    LinkedStr ostr = new LinkedStr();
		ostr.insert(0, 'a');
	    ostr.insert(1, 'm');
	    ostr.insert(2, 'b');
	    
	    assertTrue(ostr.compareTo(str) < 0);
	}
	
	// tests the basic functionality of compareTo()
	@Test public void testCompare2() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'b');
	    str.insert(1, 'a');
	    str.insert(2, 'b');
	    str.insert(3, 'o');
	    str.insert(4, 'o');
	    str.insert(5, 'n');
	    
	    LinkedStr ostr = new LinkedStr();
		ostr.insert(0, 'c');
	    ostr.insert(1, 'a');
	    ostr.insert(2, 't');
	    
	    assertTrue(str.compareTo(ostr) < 0);
	}
	
	// tests the basic functionality of compareTo()
	@Test public void testCompare3() {
		LinkedStr str = new LinkedStr();
		str.insert(0, 'c');
	    str.insert(1, 'a');
	    str.insert(2, 'u');
	    str.insert(3, 't');
	    str.insert(4, 'e');
	    str.insert(5, 'r');
	    str.insert(6, 'i');
	    str.insert(7, 'z');
	    str.insert(8, 'e');
	    
	    LinkedStr ostr = new LinkedStr();
		ostr.insert(0, 'c');
	    ostr.insert(1, 'a');
	    ostr.insert(2, 't');
	    
	    assertTrue(str.compareTo(ostr) > 0);
	}
	
	
}
