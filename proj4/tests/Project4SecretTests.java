package tests;

// (c) Ariel Liu and Larry Herman, 2020.  You are allowed to use this code
// yourself, but not to provide it to anyone else.

import recursionPractice.ListyList;
import recursionPractice.RecursionPracticeMethods;
import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class Project4SecretTests {

  // Tests calling foundInOrder() when the first list's first element occurs
  // as the very first element of the second list.
  @Test public void testSecret1() {
    ListyList<String> list1=
      MakeListyList.makeList(Arrays.asList("deer", "seal"));
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));

    assertTrue(RecursionPracticeMethods.foundInOrder(list1, list2));
  }

  // Tests calling foundInOrder() when the first list's last element occurs
  // as the very last element of the second list.
  @Test public void testSecret2() {
    ListyList<String> list1=
      MakeListyList.makeList(Arrays.asList("seal", "crow"));
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));

    assertTrue(RecursionPracticeMethods.foundInOrder(list1, list2));
  }

  // Tests calling foundInOrder() when the two lists have all the same
  // contents.
  @Test public void testSecret3() {
    ListyList<String> list1=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));

    assertTrue(RecursionPracticeMethods.foundInOrder(list1, list2));
  }

  // Tests calling foundInOrder() when the first list has some elements that
  // are not in the second list, so false should be returned.
  @Test public void testSecret4() {
    ListyList<String> list1=
      MakeListyList.makeList(Arrays.asList("deer", "candy", "crow"));
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));

    assertFalse(RecursionPracticeMethods.foundInOrder(list1, list2));
  }

  // Tests calling foundInOrder() when all of the first list elements are in
  // the second list except for some extra ones at the end that are not, so
  // false should also be returned.
  @Test public void testSecret5() {
    ListyList<String> list1=
      MakeListyList.makeList(Arrays.asList("deer", "crow", "candy"));
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));

    assertFalse(RecursionPracticeMethods.foundInOrder(list1, list2));
  }

  // Tests calling foundInOrder() with one or both lists being empty.
  @Test public void testSecret6() {
    ListyList<String> list1= new ListyList<>();
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));
    ListyList<String> list3= new ListyList<>();

    assertTrue(RecursionPracticeMethods.foundInOrder(list1, list2));
    assertFalse(RecursionPracticeMethods.foundInOrder(list2, list1));
    assertTrue(RecursionPracticeMethods.foundInOrder(list1, list3));
  }

  // Tests calling foundInOrder() with one or both lists being null.
  @Test public void testSecret7() {
    ListyList<String> list1= null;
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("deer", "seal", "dog", "goat",
                                           "rat", "cat", "lamb", "bat",
                                           "mole", "frog", "eel", "lynx",
                                           "crow"));
    ListyList<String> list3= null;

    try {
      RecursionPracticeMethods.foundInOrder(list1, list2);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }

    try {
      RecursionPracticeMethods.foundInOrder(list2, list1);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }

    try {
      RecursionPracticeMethods.foundInOrder(list1, list3);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }
  }

  // Tests that foundInOrder() is comparing elements of lists using
  // equals(), and not using reference comparisons or string comparisons.
  @Test public void testSecret8() {
    Fraction frac1= new Fraction(1, 2);
    Fraction frac2= new Fraction(1, 3);
    Fraction frac3= new Fraction(2, 3);
    Fraction frac4= new Fraction(2, 4);
    Fraction frac5= new Fraction(3, 9);
    Fraction frac6= new Fraction(2, 3);
    ListyList<Fraction> list1=
      MakeListyList.makeList(Arrays.asList(frac1, frac2, frac3));
    ListyList<Fraction> list2=
      MakeListyList.makeList(Arrays.asList(frac4, frac5, frac6));

    assertTrue(RecursionPracticeMethods.foundInOrder(list1, list2));

  }

  // Tests calling firstBetween() when the first element that is between
  // elt1 and elt2 is the first element of the list.
  @Test public void testSecret9() {
    ListyList<Integer> list=
      MakeListyList.makeList(Arrays.asList(11, 1, 4, 3, 6, 2, 8));

    assertEquals(Integer.valueOf(11),
                 RecursionPracticeMethods.firstBetween(list, 10, 12));
  }

  // Tests calling firstBetween() when the first element that is between
  // elt1 and elt2 is the last element of the list.
  @Test public void testSecret10() {
    ListyList<Integer> list=
      MakeListyList.makeList(Arrays.asList(1, 4, 3, 6, 2, 8, 11));

    assertEquals(Integer.valueOf(11),
                 RecursionPracticeMethods.firstBetween(list, 10, 12));
  }

  // Tests calling firstBetween() when none of the elements of the list are
  // between elt1 and elt2.
  @Test public void testSecret11() {
    ListyList<Integer> list=
      MakeListyList.makeList(Arrays.asList(1, 4, 3, 6, 2, 8));

    assertNull(RecursionPracticeMethods.firstBetween(list, 10, 12));
  }

  // Tests calling firstBetween() when some of the elements of the list are
  // equal to elt1 or elt2.  (These elements should not be returned, because
  // they are not strictly between the values of elt1 and elt2.)
  @Test public void testSecret12() {
    ListyList<Integer> list=
      MakeListyList.makeList(Arrays.asList(1, 4, 7, 6, 11, 8));

    assertEquals(Integer.valueOf(4),
                 RecursionPracticeMethods.firstBetween(list, 1, 5));
  }

  // Tests calling firstBetween() with an empty list.
  @Test public void testSecret13() {
    ListyList<Integer> list= new ListyList<>();

    assertNull(RecursionPracticeMethods.firstBetween(list, 1, 5));
  }

  // Tests calling firstBetween() with the list, elt1, and elt2 being null.
  @Test public void testSecret14() {
    ListyList<Integer> list=
      MakeListyList.makeList(Arrays.asList(1, 4, 7, 6, 11, 8));

    try {
      RecursionPracticeMethods.firstBetween(null, 1, 5);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }

    try {
      RecursionPracticeMethods.firstBetween(list, null, 5);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }

    try {
      RecursionPracticeMethods.firstBetween(list, 1, null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }
  }

  // Tests that firstBetween() is comparing elements of lists using
  // compareTo(), and not using reference comparisons, string comparisons or
  // the equals() method.  Uses the Rectangle inner class below.
  @Test public void testSecret15() {
    Rectangle rect1= new Rectangle(2, 100); // area= 200
    Rectangle rect2= new Rectangle(3, 60); // area= 180
    Rectangle rect3= new Rectangle(6, 40); // area= 240
    Rectangle rect4= new Rectangle(3, 60); // area= 180
    ListyList<Rectangle> list1=
      MakeListyList.makeList(Arrays.asList(rect4, rect1, rect2, rect3));

    assertNotNull(RecursionPracticeMethods.firstBetween(list1,
                    new Rectangle(3, 60), new Rectangle(6, 40)));
    assertEquals(rect1.toString(),
                 RecursionPracticeMethods.firstBetween(list1,
                   new Rectangle(3, 60), new Rectangle(6, 40)).toString());
  }

  // Tests calling largerOf() when all elements of the first list are larger
  // than those of the second list.
  @Test public void testSecret16() {
    ListyList<Character> list1=
      MakeListyList.makeList(Arrays.asList('x', 'y', 'z'));
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('a', 'b', 'c'));

    RecursionPracticeMethods.largerOf(list1, list2);

    assertEquals("x y z", list1.toString());
  }

  // Tests calling largerOf() when all elements of the second list are
  // larger than those of the first list.
  @Test public void testSecret17() {
    ListyList<Character> list1=
      MakeListyList.makeList(Arrays.asList('a', 'b', 'c'));
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('x', 'y', 'z'));

    RecursionPracticeMethods.largerOf(list1, list2);

    assertEquals("x y z", list1.toString());
  }

  // Tests calling largerOf() when the first list has more elements than the
  // second one does.
  @Test public void testSecret18() {
    ListyList<Character> list1=
      MakeListyList.makeList(Arrays.asList('x', 'y', 'z', 'h'));
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('a', 'b', 'c'));

    RecursionPracticeMethods.largerOf(list1, list2);

    assertEquals("x y z h", list1.toString());
  }

  // Tests calling largerOf() when the second list has more elements than
  // the first one does.
  @Test public void testSecret19() {
    ListyList<Character> list1=
      MakeListyList.makeList(Arrays.asList('a', 'b', 'c'));
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('x', 'y', 'z', 'h'));

    RecursionPracticeMethods.largerOf(list1, list2);

    assertEquals("x y z h", list1.toString());
  }

  // Tests calling largerOf() when one list is empty but the other one is
  // not, and when both lists are empty.
  @Test public void testSecret20() {
    ListyList<Character> list1= new ListyList<>();
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('a', 'b', 'c'));

    RecursionPracticeMethods.largerOf(list1, list2);
    assertEquals("a b c", list1.toString());

    list1= new ListyList<>();
    list2= MakeListyList.makeList(Arrays.asList('a', 'b', 'c'));
    RecursionPracticeMethods.largerOf(list2, list1);
    assertEquals("a b c", list2.toString());

    list1= new ListyList<>();
    list2= new ListyList<>();
    RecursionPracticeMethods.largerOf(list1, list2);
    assertEquals("", list1.toString());
  }

  // Tests that largerOf() does not modify its second list argument.
  @Test public void testSecret21() {
    ListyList<Character> list1=
      MakeListyList.makeList(Arrays.asList('d', 'h', 'k'));
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('e', 'a', 'b'));

    RecursionPracticeMethods.largerOf(list1, list2);
    assertEquals("e a b", list2.toString());
  }

  // Tests calling largerOf() with one or both lists being null.
  @Test public void testSecret22() {
    ListyList<Character> list1=
      MakeListyList.makeList(Arrays.asList('d', 'h', 'k'));
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('e', 'a', 'b'));

    try {
      RecursionPracticeMethods.largerOf(null, list2);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }

    try {
      RecursionPracticeMethods.largerOf(list1, null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }

    try {
      RecursionPracticeMethods.largerOf(null, null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException iae) {
      // if we get here the expected exception was thrown
    }
  }

  // Tests that largerOf() is comparing elements of lists using equals(),
  // and not using reference comparisons or string comparisons.  Uses the
  // Rectangle inner class below.
  @Test public void testSecret23() {
    Rectangle rect1= new Rectangle(2, 100);  // area = 200
    Rectangle rect2= new Rectangle(3, 60);   // area = 180
    Rectangle rect3= new Rectangle(6, 40);   // area = 240
    Rectangle rect4= new Rectangle(3, 20);   // area = 60
    Rectangle rect5= new Rectangle(3, 80);   // area = 240
    ListyList<Rectangle> list1=
      MakeListyList.makeList(Arrays.asList(rect1, rect2, rect3));
    ListyList<Rectangle> list2=
      MakeListyList.makeList(Arrays.asList(rect4, rect5, rect3));

    RecursionPracticeMethods.largerOf(list1, list2);
    assertEquals("2x100 3x80 6x40", list1.toString());
  }

  // Tests calling removeDuplicates() when there are multiple duplicate
  // elements at the beginning of the list.
  @Test public void testSecret24() {
    ListyList<Integer> list1=
      MakeListyList.makeList(Arrays.asList(3, 3, 3, 2));
    ListyList<Integer> list2=
      RecursionPracticeMethods.removeDuplicates(list1);

    assertEquals("3 2", list2.toString());
  }

  // Tests calling removeDuplicates() when there are multiple duplicate
  // elements at the end of the list.
  @Test public void testSecret25() {
    ListyList<Integer> list1=
      MakeListyList.makeList(Arrays.asList(5, 7, 3, 3, 3));
    ListyList<Integer> list2=
      RecursionPracticeMethods.removeDuplicates(list1);

    assertEquals("5 7 3", list2.toString());
  }

  // Tests calling removeDuplicates() when all of the list elements are the
  // same.
  @Test public void testSecret26() {
    ListyList<Integer> list1=
      MakeListyList.makeList(Arrays.asList(3, 3, 3, 3, 3));
    ListyList<Integer> list2=
      RecursionPracticeMethods.removeDuplicates(list1);

    assertEquals("3", list2.toString());
  }

  // Tests calling removeDuplicates() when none of the list elements are the
  // same.
  @Test public void testSecret27() {
    ListyList<Integer> list1=
      MakeListyList.makeList(Arrays.asList(1, 7, 6, 8, 5));
    ListyList<Integer> list2=
      RecursionPracticeMethods.removeDuplicates(list1);

    assertEquals("1 7 6 8 5", list2.toString());
  }

  // Tests calling removeDuplicates() when the list has duplicate elements
  // but they are not adjacent.
  @Test public void testSecret28() {
    ListyList<Integer> list1=
      MakeListyList.makeList(Arrays.asList(1, 3, 6, 3, 2));
    ListyList<Integer> list2=
      RecursionPracticeMethods.removeDuplicates(list1);

    assertEquals("1 3 6 3 2", list2.toString());
  }

  // Tests calling removeDuplicates() when the list is empty.
  @Test public void testSecret29() {
    ListyList<Integer> list1= new ListyList<>();
    ListyList<Integer> list2=
      RecursionPracticeMethods.removeDuplicates(list1);

    assertEquals("", list2.toString());
  }

  // Tests calling removeDuplicates() when the list is null.
  @Test(expected= IllegalArgumentException.class) public void testSecret30() {
    RecursionPracticeMethods.removeDuplicates(null);
  }

  // Tests that removeDuplicates() does not modify its argument list, and
  // that the list it returns is independent of the argument list.
  @Test public void testSecret31() {
    ListyList<Integer> list1=
      MakeListyList.makeList(Arrays.asList(1, 3, 3, 3, 3, 2));

    RecursionPracticeMethods.removeDuplicates(list1);
    assertEquals("1 3 3 3 3 2", list1.toString());
  }

  // Tests that removeDuplicates() is comparing elements of lists using
  // equals(), and not using reference comparisons or string comparisons.
  // Uses the Fraction inner class below.
  @Test public void testSecret32() {
    Fraction frac1= new Fraction(1, 2);
    Fraction frac2= new Fraction(1, 3);
    Fraction frac3= new Fraction(3, 9);
    Fraction frac4= new Fraction(1, 3);
    Fraction frac5= new Fraction(2, 3);
    ListyList<Fraction> list1=
      MakeListyList.makeList(Arrays.asList(frac1, frac2, frac3, frac4, frac5));

    ListyList<Fraction> list2=
      RecursionPracticeMethods.removeDuplicates(list1);
    assertEquals("1/2 1/3 2/3", list2.toString());
  }

  // inner (helper) classes /////////////////////////////////////////////

  /* Some secret tests use objects of the Fraction class.  A Fraction has a
   * numerator and denominator, and two Fractions are called equal if they
   * have the same numeric value, even if their numerators and denominators
   * are different.  For example, 2/4 is considered to be equal to 5/10.
   * This class can be used to test that the equals() method is being used
   * to compare objects, and not string comparisons.  (As mentioned in
   * lecture, objects cannot generally be compared just using their string
   * representations- this may sometimes work for simple objects that only
   * contain one field, like a wrapper class object, but will not work for
   * all objects.)
   */
  private static class Fraction {

    private int numerator;
    private int denominator;

    Fraction(int numerator, int denominator) {
      this.numerator= numerator;
      this.denominator= denominator;
    }

    @Override
    public String toString() {
      return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
      Fraction f;
      boolean result= false;

      if (obj == this)
        result= true;
      else
        if (obj instanceof Fraction) {
          f= (Fraction) obj;
          // Now we compare the specific fields of the current object and
          // the parameter object obj here, returning true if they have the
          // same values and false if not.
          result= (numerator * f.denominator) ==
                  (f.numerator * denominator);
        }

      return result;
    }

    // if we implement equals() we must implement hashCode() (this will be
    // covered later)
    @Override
    public int hashCode() {
      return 0;
    }

  }

  /* An inner class that implements the Comparable interface.  A Rectangle
   * has a width and height, and is compared (ordered) according to its
   * area, which does not always give the same result as comparing their
   * string representations would give.  This class can be used to test that
   * the compareTo() method is being used to compare objects, and not string
   * comparisons.  (As mentioned in lecture, objects cannot generally be
   * compared just using their string representations- this may sometimes
   * work for simple objects that only contain one field, like a wrapper
   * class object, but will not work for all objects.)  This class can also
   * be used to test that objects that are supposed to be compared using the
   * compareTo() method are not incorrectly being compared using equals(),
   * because although this class has a compareTo() method it does not
   * override equals(), so the default equals() in the Object class, that
   * just does reference comparison, will being used when Rectangles are
   * incorrectly compared using equals().
   */
  private static class Rectangle implements Comparable<Rectangle> {

    private Integer width;
    private Integer height;

    Rectangle(Integer widthIn, Integer heightIn) {
      width= widthIn;
      height= heightIn;
    }

    // rectangles are compared based on their areas
    @Override
    public int compareTo(Rectangle otherRectangle) {
      return (width * height) -
             (otherRectangle.width * otherRectangle.height);
    }

    @Override
    public String toString() {
      return width.toString() + "x" + height.toString();
    }

  }

}
