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

import bst.EmptyPolyTreeException;
import bst.PolyTree;

import static org.junit.Assert.*;

public class StudentTests{

	// tests delete on root
	@Test public void testDelete() {
		PolyTree<Integer, Character> tree= TestData.examplePolyTree2();

		tree= tree.deleteKeyValuePair(7);

		assertEquals("1+e 2+l 3+e 5+p 11+a 13+n 17+t ", 
				tree.toString());
		
	}
	
	// tests delete on min
	@Test public void testDelete2() {
		PolyTree<Integer, Character> tree= TestData.examplePolyTree1();

		tree= tree.deleteKeyValuePair(1);

		assertEquals("5+o 6+l 7+p 11+h 13+i 17+n ", tree.toString());
	}

	// tests delete on max
	@Test public void testDelete3() {
		PolyTree<Integer, Character> tree= 
				TestData.examplePolyTree1();

		tree= tree.deleteKeyValuePair(17);

		assertEquals("1+d 5+o 6+l 7+p 11+h 13+i ", tree.toString());
	}

	// tests min when the first min is deleted
	@Test public void testMin() throws EmptyPolyTreeException {
		PolyTree<Integer, Character> tree= 
				TestData.examplePolyTree1();

		tree= tree.deleteKeyValuePair(1);
		assertEquals(Integer.valueOf(5), tree.min());
	}
	
	// tests min when the first min is deleted
	@Test public void testMax() throws EmptyPolyTreeException {
		PolyTree<Integer, Character> tree= 
				TestData.examplePolyTree1();

		tree= tree.deleteKeyValuePair(17);
		assertEquals(Integer.valueOf(13), tree.max());
	}
	
	// tests distance on a key after a parent tree is deleted
	@Test public void testDistance() {
		PolyTree<Integer, Character> tree= 
				TestData.examplePolyTree1();
		
		assertEquals(2, tree.distanceFromRoot(11));
		
		tree= tree.deleteKeyValuePair(13);
		
		assertEquals(1, tree.distanceFromRoot(11));
	}
	
	// tests tree size after an object is added
	@Test public void testTreeSize() {
		PolyTree<Integer, Character> tree= 
				TestData.examplePolyTree1();
		
		assertEquals(7, tree.treeSize());
		
		tree.addKeyValuePair(14, 'k');
		
		assertEquals(8, tree.treeSize());
		
	}
	
	// tests tree size after an object is deleted
	@Test public void testTreeSize2() {
		PolyTree<Integer, Character> tree= 
				TestData.examplePolyTree1();

		assertEquals(7, tree.treeSize());

		tree.deleteKeyValuePair(13);

		assertEquals(6, tree.treeSize());

	}

	// tests lookupByValue after an object is deleted
	@Test public void testLookUp() {
		PolyTree<Integer, Character> tree= 
				TestData.examplePolyTree1();

		assertEquals(Character.valueOf('i'), 
				tree.lookupValueByKey(13));

		tree.deleteKeyValuePair(13);

		assertEquals(null, tree.lookupValueByKey(13));

	}


}



