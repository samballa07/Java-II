package tests;

// (c) Dale Dullnig and Larry Herman, 2020.  You are allowed to use this
// code yourself, but not to provide it to anyone else.

import linkedStr.LinkedStr;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests checking the length of an empty LinkedStr.
  @Test public void testPublic1() {
    LinkedStr myStr= new LinkedStr();

    assertEquals(0, myStr.length());
  }

  // Tests appending some characters to a LinkedStr
  // and checking its length.
  @Test public void testPublic2() {
    LinkedStr myStr= new LinkedStr();

    myStr= myStr.append('w').append('a').append('t').append('e').append('r');

    assertEquals("water", TestUtilities.toString(myStr));
    assertEquals(5, myStr.length());
  }

  // Tests the basic operation of insert().
  @Test public void testPublic3() {
    LinkedStr myStr= new LinkedStr();

    myStr.insert(0, 'w');
    myStr.insert(1, 'a');
    myStr.insert(2, 't');
    myStr.insert(3, 'e');
    myStr.insert(4, 'r');

    assertEquals("water", TestUtilities.toString(myStr));

    myStr.insert(2, 'l');

    assertEquals("walter", TestUtilities.toString(myStr));
  }

  // Tests insert() with a too-large value for position, which should throw
  // the expected exception.
  @Test(expected= IndexOutOfBoundsException.class)
  public void testPublic4() {
    LinkedStr myStr= new LinkedStr();

    myStr= myStr.append('e').append('a').append('r').append('t').append('h');

    assertEquals("earth", TestUtilities.toString(myStr));

    myStr.insert(6, 'y');
  }

  // Tests the basic operation of concat().
  @Test public void testPublic5() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();

    myStr= myStr.append('f').append('i').append('r').append('e');

    myStr2.insert(0, 'a');
    myStr2.insert(1, 't');
    myStr2.insert(2, 'i');
    myStr2.insert(3, 'o');
    myStr2.insert(4, 'n');
    myStr2.insert(0, 'n');

    assertEquals("fire", TestUtilities.toString(myStr));
    assertEquals("nation", TestUtilities.toString(myStr2));

    myStr.concat(myStr2);
    assertEquals("firenation", TestUtilities.toString(myStr));
  }

  // Tests some special cases for concat().
  @Test public void testPublic6() {
    LinkedStr initStr= new LinkedStr();
    LinkedStr myStr= new LinkedStr();
    LinkedStr blankStr= new LinkedStr();

    myStr= myStr.append('f').append('i').append('r').append('e');

    initStr.concat(myStr);
    assertEquals("fire", TestUtilities.toString(initStr));

    initStr.concat(blankStr);
    assertEquals("fire", TestUtilities.toString(initStr));
  }

  // Tests that the current object and otherStr are independent
  // after calling concat().
  @Test public void testPublic7() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();

    myStr= myStr.append('f').append('i').append('r').append('e');
    myStr2= myStr2.append('l').append('o').append('r').append('d');

    assertEquals("fire", TestUtilities.toString(myStr));
    assertEquals("lord", TestUtilities.toString(myStr2));

    myStr.concat(myStr2);

    assertEquals("firelord", TestUtilities.toString(myStr));
    assertEquals("lord", TestUtilities.toString(myStr2));

    myStr.append('s');
    myStr2.insert(3, 'e');
    assertEquals("firelords", TestUtilities.toString(myStr));
    assertEquals("lored", TestUtilities.toString(myStr2));
  }

  // Tests the basic operation of findFirst().
  @Test public void testPublic8() {
    LinkedStr myStr= new LinkedStr();

    myStr= myStr.append('a').append('i').append('r');
    assertEquals(2, myStr.findFirst(0, 'r'));
  }

  // Tests calling findFirst() on a character that is not present at all
  // in a LinkedStr.
  @Test public void testPublic9() {
    LinkedStr myStr= new LinkedStr();

    myStr.insert(0, 'e');
    myStr.insert(1, 'l');
    myStr.insert(2, 'e');
    myStr.insert(3, 'm');
    myStr.insert(4, 'e');
    myStr.insert(5, 'n');
    myStr.insert(6, 't');
    myStr.insert(7, 's');

    assertEquals(-1, myStr.findFirst(0, 'v'));
  }

  // Tests calling findFirst on a character that is present in a LinkedStr,
  // but not at or after the indicated position.
  @Test public void testPublic10() {
    LinkedStr myStr= new LinkedStr();

    myStr.insert(0, 'e');
    myStr.insert(1, 'l');
    myStr.insert(2, 'e');
    myStr.insert(3, 'm');
    myStr.insert(4, 'e');
    myStr.insert(5, 'n');
    myStr.insert(6, 't');
    myStr.insert(7, 's');

    assertEquals(-1, myStr.findFirst(5, 'e'));
  }

  // Tests calling compareTo() on two LinkedStr objects that all have the
  // same values in the same order.
  @Test public void testPublic11() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();

    myStr.insert(0, 'h');
    myStr.insert(1, 'a');
    myStr.insert(2, 'r');
    myStr.insert(3, 'm');
    myStr.insert(4, 'o');
    myStr.insert(5, 'n');
    myStr.insert(6, 'y');

    myStr2.insert(0, 'h');
    myStr2.insert(1, 'a');
    myStr2.insert(2, 'r');
    myStr2.insert(3, 'm');
    myStr2.insert(4, 'o');
    myStr2.insert(5, 'n');
    myStr2.insert(6, 'y');

    assertEquals(0, myStr.compareTo(myStr2));
  }

  // Tests calling compareTo() on two LinkedStr objects that have
  // different values.
  @Test public void testPublic12() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();

    myStr.insert(0, 'e');
    myStr.insert(1, 'v');
    myStr.insert(2, 'e');
    myStr.insert(3, 'r');
    myStr.insert(4, 'y');

    myStr2.insert(0, 't');
    myStr2.insert(1, 'h');
    myStr2.insert(2, 'i');
    myStr2.insert(3, 'n');
    myStr2.insert(4, 'g');

    assertTrue(myStr.compareTo(myStr2) < 0);
  }

  // Tests the basic functionality of the copy constructor.
  @Test public void testPublic13() {
    LinkedStr myStr= new LinkedStr();

    myStr.insert(0, 'e');
    myStr.insert(1, 'v');
    myStr.insert(2, 'e');
    myStr.insert(3, 'r');
    myStr.insert(4, 'y');

    LinkedStr myStr2= new LinkedStr(myStr);

    assertEquals("every", TestUtilities.toString(myStr2));
  }

  // Tests that the current object and otherStr are independent after
  // calling the copy constructor.
  @Test public void testPublic14() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2;

    myStr.insert(0, 't');
    myStr.insert(1, 'h');
    myStr.insert(2, 'i');
    myStr.insert(3, 'n');
    myStr.insert(4, 'g');

    myStr2= new LinkedStr(myStr);

    myStr2.insert(2, 'r');
    assertEquals("thing", TestUtilities.toString(myStr));
    assertEquals("thring", TestUtilities.toString(myStr2));
  }

  // Tests the basic operation of deleteCharsAt().
  @Test public void testPublic15() {
    LinkedStr myStr= new LinkedStr();

    myStr.insert(0, 'c');
    myStr.insert(1, 'h');
    myStr.insert(2, 'a');
    myStr.insert(3, 'n');
    myStr.insert(4, 'g');
    myStr.insert(5, 'e');
    myStr.insert(6, 'd');

    myStr.deleteCharsAt(1, 1);
    myStr.deleteCharsAt(2, 1);

    assertEquals("caged", TestUtilities.toString(myStr));
  }

  // Tests calling a few of the method on empty list.
  @Test public void testPublic16() {
    LinkedStr myStr= new LinkedStr();
    LinkedStr myStr2= new LinkedStr();

    assertNull(myStr.getCharAt(5));
    assertEquals(-1, myStr.findFirst(0, 'w'));
    assertEquals(0, myStr.compareTo(myStr2));
  }

  // Tests calling some of the methods with null parameters.
  @Test(expected= NullPointerException.class)
  public void testPublic17() {
    LinkedStr myStr= new LinkedStr(null);

    assertEquals(myStr.length(), 0);

    myStr.concat(null);  // this should not throw the exception
    assertEquals(myStr.length(), 0);

    myStr.compareTo(null);  // but this should
  }

}
