package tests;

// (c) Dale Dullnig and Larry Herman, 2020.  You are allowed to use this
// code yourself, but not to provide it to anyone else.

import linkedStr.LinkedStr;
import org.junit.*;
import static org.junit.Assert.*;

public class Project3SecretTests {
  
  // Tests calling getCharAt() to get the first and last characters of 
  // a LinkedStr.
  @Test public void testSecret1() {
    LinkedStr myStr= new LinkedStr();

    myStr= myStr.append('L').append('a').append('r').append('r').append('y');

    assertTrue('L' == myStr.getCharAt(0));
    assertTrue('y' == myStr.getCharAt(4));
  }
  
  // Tests calling getCharAt() with a value for position that is too large and
  // with a value for position that is negative.
  @Test public void testSecret2() {
    LinkedStr myStr= new LinkedStr();
    
    myStr= myStr.append('w').append('a').append('t').append('e').append('r');
    
    assertNull(myStr.getCharAt(-3));
    assertNull(myStr.getCharAt(7));
  }
  
  // Tests passing an empty LinkedStr into the copy constructor.
  @Test public void testSecret3() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr(myStr);
    
    assertEquals("", TestUtilities.toString(myStr2));
  }
  
  // Tests passing null into the copy constructor.
  @Test public void testSecret4() {
    LinkedStr myStr= new LinkedStr(null);
    
    assertEquals("", TestUtilities.toString(myStr));
  }
  
  // Tests calling various methods after reset() is called.
  @Test public void testSecret5() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'e');
    myStr.insert(2, 's');
    myStr.insert(3, 'e');
    myStr.insert(4, 't');
    myStr.insert(5, 't');
    myStr.insert(6, 'i');
    
    myStr2.insert(0, 'm');
    myStr2.insert(1, 'o');
    myStr2.insert(2, 'l');
    myStr2.insert(3, 'e');
    
    myStr.reset();
    
    assertEquals("", TestUtilities.toString(myStr));
    
    myStr.append('z');
    assertEquals("z", TestUtilities.toString(myStr));
    
    myStr.reset();
    assertEquals("", TestUtilities.toString(myStr));
    
    assertEquals(0, myStr.length());
    assertNull(myStr.getCharAt(1));
    
    myStr.insert(0, 'x');
    assertEquals("x", TestUtilities.toString(myStr));
    
    myStr.reset();
    myStr.concat(myStr2);
    assertEquals("mole", TestUtilities.toString(myStr));
  }
  
  // Tests calling insert() with a value for position that is too large.
  @Test public void testSecret6() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'e');
    myStr.insert(2, 's');
    myStr.insert(3, 'e');
    myStr.insert(4, 't');
    myStr.insert(5, 't');
    myStr.insert(6, 'i');
    
    try {
      myStr.insert(8, 'z');
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IndexOutOfBoundsException e) {
      assertEquals("resetti", TestUtilities.toString(myStr));
    }
  }
  
  // Tests calling insert() with a value for position that is negative.
  @Test public void testSecret7() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'e');
    myStr.insert(2, 's');
    myStr.insert(3, 'e');
    myStr.insert(4, 't');
    myStr.insert(5, 't');
    myStr.insert(6, 'i');
    
    try {
      myStr.insert(-1, 'y');
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IndexOutOfBoundsException e) {
      assertEquals("resetti", TestUtilities.toString(myStr));
    }
  }
  
  // Tests calling findFirst() on a character that happens to be the last one
  // in a LinkedStr (one that does not also appear earlier), and on one that
  // happens to the first one.
  @Test public void testSecret8() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    assertEquals(6, myStr.findFirst(0, 'v'));
    assertEquals(0, myStr.findFirst(0, 'r'));
  }
  
  // Tests calling findFirst() with with a value for position that is 
  // too large.
  @Test public void testSecret9() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    assertEquals(-1, myStr.findFirst(7, 'v'));
  }
  
  // Tests calling findFirst() with with a value for position that is
  // negative.
  @Test public void testSecret10() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    assertEquals(-1, myStr.findFirst(-6, 'r'));
  }
  
  
  // Tests calling deleteCharsAt() with with a value for position that is
  // too large.
  @Test public void testSecret11() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    try {
      myStr.deleteCharsAt(8, 1);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IndexOutOfBoundsException e) {
      assertEquals("roygbiv", TestUtilities.toString(myStr));
    }
  }
  
  // Tests calling deleteCharsAt() with with a value for position that is
  // negative.
  @Test public void testSecret12() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    try {
      myStr.deleteCharsAt(-1, 1);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IndexOutOfBoundsException e) {
      assertEquals("roygbiv", TestUtilities.toString(myStr));
    }
  }
  
  // Tests deleting the first character of a string with deleteCharsAt(),
  // then adds new characters to the LinkedStr.
  @Test public void testSecret13() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();
    
    myStr.insert(0, 'm');
    myStr.insert(1, 'a');
    myStr.insert(2, 'r');
    myStr.insert(3, 't');
    myStr.insert(4, 'h');
    
    myStr2.append('z');
    
    myStr.deleteCharsAt(0, 1);
    myStr2.deleteCharsAt(0, 1);
    
    myStr.append('a').append('s');
    myStr2.append('y');
    
    assertEquals("arthas", TestUtilities.toString(myStr));
    assertEquals("y", TestUtilities.toString(myStr2));
    
    myStr.deleteCharsAt(0, 1);
    myStr2.deleteCharsAt(0, 1);
    
    myStr.insert(0, 'o');
    myStr.insert(0, 'b');
    myStr2.insert(0, 'x');
    
    assertEquals("borthas", TestUtilities.toString(myStr));
    assertEquals("x", TestUtilities.toString(myStr2));
  }
  
  // Tests calling deleteCharsAt() with a value for numChars that is negative.
  @Test public void testSecret14() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    myStr.deleteCharsAt(3, -3);
    
    assertEquals("roygbiv", TestUtilities.toString(myStr));
  }
  
  // Tests calling deleteCharsAt() with a value for numChars that is too
  // large, which should just remove everything up until the end of the string.
  @Test public void testSecret15() {
    LinkedStr myStr= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    myStr.deleteCharsAt(3, 20);
    
    assertEquals("roy", TestUtilities.toString(myStr));
  }
  
  // Tests calling deleteCharsAt() to remove all characters from a LinkedStr,
  // then adds some back.
  @Test public void testSecret16() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();
    
    myStr.insert(0, 'r');
    myStr.insert(1, 'o');
    myStr.insert(2, 'y');
    myStr.insert(3, 'g');
    myStr.insert(4, 'b');
    myStr.insert(5, 'i');
    myStr.insert(6, 'v');
    
    myStr2.insert(0, 'o');
    myStr2.insert(1, 'r');
    
    myStr.deleteCharsAt(0, 7);
    assertEquals("", TestUtilities.toString(myStr));
    
    myStr.append('m').append('o');
    assertEquals("mo", TestUtilities.toString(myStr));
    
    myStr.reset();
    myStr.insert(0, 'd');
    myStr.insert(0, 'r');
    assertEquals("rd", TestUtilities.toString(myStr));
    
    myStr.reset();
    myStr.concat(myStr2);
    assertEquals("or", TestUtilities.toString(myStr));
  }
  
  // Tests calling deleteCharsAt() repeatedly to remove all characters from
  // the end of a LinkedStr, then adds some back.
  @Test public void testSecret17() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();
    
    myStr.insert(0, 'w');
    myStr.insert(1, 'a');
    myStr.insert(2, 'l');
    myStr.insert(3, 'k');
    
    myStr2.insert(0, 'o');
    myStr2.insert(1, 'r');
    
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    assertEquals("", TestUtilities.toString(myStr));
    
    myStr.append('m').append('o');
    assertEquals("mo", TestUtilities.toString(myStr));
    
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    myStr.insert(0, 'd');
    myStr.insert(0, 'r');
    assertEquals("rd", TestUtilities.toString(myStr));
    
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    myStr.deleteCharsAt(myStr.length() - 1, 1);
    myStr.concat(myStr2);
    assertEquals("or", TestUtilities.toString(myStr));
  }
  
  // Tests calling compareTo() on two LinkedStrs that have different
  // elements.
  @Test public void testSecret18() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();
    
    myStr.insert(0, 'g');
    myStr.insert(1, 'o');
    myStr.insert(2, 'n');
    myStr.insert(3, 'd');
    myStr.insert(4, 'o');
    myStr.insert(5, 'r');
    
    myStr2.insert(0, 'g');
    myStr2.insert(1, 'o');
    myStr2.insert(2, 'n');
    myStr2.insert(3, 'd');
    myStr2.insert(4, 'o');
    myStr2.insert(5, 'l');
    myStr2.insert(6, 'a');
    
    assertTrue(myStr.compareTo(myStr2) > 0);
  }
  
  // Tests calling compareTo() on two LinkedStrs that have the same
  // elements, except one is longer (has additional trailing elements).
  @Test public void testSecret19() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();
    
    myStr.insert(0, 'n');
    myStr.insert(1, 'a');
    myStr.insert(2, 'z');
    myStr.insert(3, 'g');
    myStr.insert(4, 'u');
    myStr.insert(5, 'l');
    
    myStr2.insert(0, 'n');
    myStr2.insert(1, 'a');
    myStr2.insert(2, 'z');
    myStr2.insert(3, 'g');
    myStr2.insert(4, 'u');
    myStr2.insert(5, 'l');
    myStr2.insert(6, 'i');
    
    assertTrue(myStr.compareTo(myStr2) < 0);
    assertTrue(myStr2.compareTo(myStr) > 0);
  }
  
  // Tests calling compareTo() on two LinkedStrs that differ just in their
  // last elements.
  @Test public void testSecret20() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();
    
    myStr.insert(0, 'b');
    myStr.insert(1, 'a');
    myStr.insert(2, 's');
    myStr.insert(3, 'k');
    myStr.insert(4, 'e');
    myStr.insert(5, 't');
    
    myStr2.insert(0, 'b');
    myStr2.insert(1, 'a');
    myStr2.insert(2, 's');
    myStr2.insert(3, 'k');
    myStr2.insert(4, 'e');
    myStr2.insert(5, 'r');
    
    assertTrue(myStr.compareTo(myStr2) > 0);
    assertTrue(myStr2.compareTo(myStr) < 0);
  }

}
