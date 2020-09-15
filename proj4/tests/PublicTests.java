package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import recursionPractice.ListyList;
import recursionPractice.RecursionPracticeMethods;
import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests the basic operation of foundInOrder().
  @Test public void testPublic1() {
    ListyList<String> list1=
      MakeListyList.makeList(Arrays.asList("cat", "goat", "seal"));
    ListyList<String> list2=
      MakeListyList.makeList(Arrays.asList("rat", "cat", "lamb", "bat",
                                           "dog", "goat", "deer", "seal",
                                           "eel", "lynx"));

    assertTrue(RecursionPracticeMethods.foundInOrder(list1, list2));
   
  }

  // Tests the basic operation of firstBetween().
  @Test public void testPublic2() {
    ListyList<Integer> list=
      MakeListyList.makeList(Arrays.asList(9, 1, 4, 3, 6, 2, 8));

    assertEquals(Integer.valueOf(6),
                 RecursionPracticeMethods.firstBetween(list, 5, 7));
  }

  // Tests the basic operation of largerOf().
  @Test public void testPublic3() {
    ListyList<Character> list1=
      MakeListyList.makeList(Arrays.asList('b', 'o', 'w', 'l'));
    ListyList<Character> list2=
      MakeListyList.makeList(Arrays.asList('c', 'a', 't', 's'));

    RecursionPracticeMethods.largerOf(list1, list2);
    assertEquals("c o w s", list1.toString());
  }

  // Tests the basic operation of removeDuplicates().
  @Test public void testPublic4() {
    ListyList<Integer> list1=
      MakeListyList.makeList(Arrays.asList(1, 3, 3, 3, 2));
    ListyList<Integer> list2=
      RecursionPracticeMethods.removeDuplicates(list1);

    assertEquals("1 3 2", list2.toString());
  }

}
