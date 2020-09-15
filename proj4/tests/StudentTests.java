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

import recursionPractice.ListyList;
import recursionPractice.RecursionPracticeMethods;

import static org.junit.Assert.*;

import java.util.Arrays;

public class StudentTests {

	// tests the basic functionality of foundInOrder
	@Test public void testFoundInOrder() {
		ListyList<Character> list1=
				MakeListyList.makeList(Arrays.asList(
						't', 's', 'i', 'n', 'g'));
		
		ListyList<Character> list2=
				MakeListyList.makeList(Arrays.asList(
						't', 'e', 's', 't', 'i', 'n', 'g'));
		
		assertTrue(RecursionPracticeMethods.foundInOrder(list1, list2));
	}
	
	// tests that the method returns false when list1 is bigger
	@Test public void testFoundInOrder2() {
		ListyList<Character> list1=
				MakeListyList.makeList(Arrays.asList(
						't', 'e', 's', 't', 'i', 'n', 'g'));
		
		ListyList<Character> list2=
				MakeListyList.makeList(Arrays.asList(
						't', 's', 'i', 'n', 'g'));
		
		assertFalse(RecursionPracticeMethods.foundInOrder(list1, list2));
	}
	
	// tests the basic functionality of foundInOrder
	@Test public void testFoundInOrder3() {
		ListyList<Character> list1=
				MakeListyList.makeList(Arrays.asList(
						't', 's', 'i', 'n', 'e'));
		
		ListyList<Character> list2=
				MakeListyList.makeList(Arrays.asList(
						't', 'e', 's', 't', 'i', 'n', 'g'));
		
		assertFalse(RecursionPracticeMethods.foundInOrder(list1, list2));
	}
	
	// tests basic functionality of firstBetween with integers
	@Test public void testFirstBetween1() {
		ListyList<Integer> list=
				MakeListyList.makeList(Arrays.asList(9, 4, 4, 5, 7, 9, 10));

		assertEquals(Integer.valueOf(9),
				RecursionPracticeMethods.firstBetween(list, 7, 10));
	}
	
	// tests basic functionality of firstBetween with integers
	@Test public void testFirstBetween2() {
		ListyList<Integer> list=
				MakeListyList.makeList(Arrays.asList(7, 1, 4, 3, 6, 2, 8));

		assertEquals(Integer.valueOf(8),
				RecursionPracticeMethods.firstBetween(list, 7, 10));
	}
	
	// tests basic functionality of firstBetween with chars
	@Test public void testFirstBetween3() {
		ListyList<Integer> list=
				MakeListyList.makeList(Arrays.asList(4, 5, 7, 8, 9, 2, 8));

		assertEquals(Integer.valueOf(7),
				RecursionPracticeMethods.firstBetween(list, 5, 10));
	}
	
	// tests that null is returned for the right case
	@Test public void testFirstBetween4() {
		ListyList<Integer> list=
				MakeListyList.makeList(Arrays.asList(4, 5, 7, 8, 9, 2, 8));

		assertEquals(null,
				RecursionPracticeMethods.firstBetween(list, 5, 6));
	}
	
	// tests basic functionality of firstBetween with chars
	@Test public void testFirstBetween5() {
		ListyList<Character> list=
				MakeListyList.makeList(Arrays.asList
						('t', 'p', 'a', 'd', 'b', 'c'));

		assertEquals(Character.valueOf('b'),
				RecursionPracticeMethods.firstBetween(list, 'a', 'c'));
	}
	
	// tests basic functionality of largerOf with integers
	@Test public void testLargerOf1() {
		ListyList<Integer> list1=
				MakeListyList.makeList(Arrays.asList(4, 3, 10));
		ListyList<Integer> list2=
				MakeListyList.makeList(Arrays.asList(0, 10));

		RecursionPracticeMethods.largerOf(list1, list2);
		assertEquals("4 10 10", list1.toString());
	}
	
	// tests basic functionality of largerOf with integers
	@Test public void testLargerOf2() {
		ListyList<Integer> list1=
				MakeListyList.makeList(Arrays.asList(1, 6, 4));
		ListyList<Integer> list2=
				MakeListyList.makeList(Arrays.asList(5, 2, 7, 8, 3, 5));

		RecursionPracticeMethods.largerOf(list1, list2);
		assertEquals("5 6 7", list1.toString());
	}
	
	// tests basic functionality of largerOf with chars
		@Test public void testLargerOf3() {
			ListyList<Character> list1=
					MakeListyList.makeList(Arrays.asList('i', 'd', 'a'));
			ListyList<Character> list2=
					MakeListyList.makeList(Arrays.asList('k', 'c', 'x', 
							'f'));

			RecursionPracticeMethods.largerOf(list1, list2);
			assertEquals("k d x", list1.toString());
		}
	
	@Test public void testRemoveDuplicates1() {
		ListyList<Integer> list1=
				MakeListyList.makeList(Arrays.asList(1, 6, 4));
		ListyList<Integer> list2=
				RecursionPracticeMethods.removeDuplicates(list1);

		assertEquals("1 6 4", list2.toString());
	}
	
	// tests basic functionality of removeDuplicates with Strings
	@Test public void testRemoveDuplicates2() {
		ListyList<Integer> list1=
				MakeListyList.makeList(Arrays.asList
						(1, 1, 1, 6, 6, 6, 6, 4, 4, 4, 4));
		ListyList<Integer> list2=
				RecursionPracticeMethods.removeDuplicates(list1);

		assertEquals("1 6 4", list2.toString());
	}
	
	// checks that removeDuplicates() works for an empty list
	@Test public void testRemoveDuplicates3() {
		ListyList<Integer> list1=
				MakeListyList.makeList(Arrays.asList
						());
		ListyList<Integer> list2=
				RecursionPracticeMethods.removeDuplicates(list1);

		assertEquals("", list2.toString());
	}
	
	// checks that removeDuplicates() works for chars
		@Test public void testRemoveDuplicates4() {
			ListyList<Character> list1=
					MakeListyList.makeList(Arrays.asList
							('s', 's', 's', 'e', 't', 't', 't', 't', 'h'));
			ListyList<Character> list2=
					RecursionPracticeMethods.removeDuplicates(list1);

			assertEquals("s e t h", list2.toString());
		}
	
}
