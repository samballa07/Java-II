package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import bst.PolyTree;
import bst.EmptyPolyTree;
import bst.EmptyPolyTreeException;
import org.junit.*;
import static org.junit.Assert.*;

@SuppressWarnings({"unchecked"})
public class PublicTests {

  // Tests calling treeSize() on an empty tree.
  @Test public void testPublic1() {
    assertEquals(0, EmptyPolyTree.getInstance().treeSize());
  }

  // Tests calling treeSize() on nonempty trees; this also tests that
  // addKeyValuePair() is working.
  @Test public void testPublic2() {
    assertEquals(7, TestData.examplePolyTree1().treeSize());
    assertEquals(8, TestData.examplePolyTree2().treeSize());
  }

  // Tests calling lookupValueByKey() on the key of the root element of a
  // nonempty tree.
  @Test public void testPublic3() {
    assertEquals(Character.valueOf('p'),
                 TestData.examplePolyTree1().lookupValueByKey(7));
  }

  // Tests calling lookupValueByKey() on the key of an interior element of a
  // nonempty tree.
  @Test public void testPublic4() {
    assertEquals(Character.valueOf('i'),
                 TestData.examplePolyTree1().lookupValueByKey(13));
  }

  // Tests calling toString() on an empty tree.
  @Test public void testPublic5() {
    assertEquals("", EmptyPolyTree.getInstance().toString());
  }

  // Tests calling toString() on a nonempty tree.
  @Test public void testPublic6() {
    assertEquals("1+d 5+o 6+l 7+p 11+h 13+i 17+n ",
                 TestData.examplePolyTree1().toString());
  }

  // Tests the basic functionality of max().
  @Test public void testPublic7() throws EmptyPolyTreeException {
    assertEquals(Integer.valueOf(17), TestData.examplePolyTree1().max());
  }

  // Tests the basic functionality of min().
  @Test public void testPublic8() throws EmptyPolyTreeException {
    assertEquals(Integer.valueOf(1), TestData.examplePolyTree1().min());
  }

  // Tests calling distanceFromRoot() on the key of the root element of a
  // nonempty tree.
  @Test public void testPublic9() {
    assertEquals(0, TestData.examplePolyTree2().distanceFromRoot(7));
  }

  // Tests calling distanceFromRoot() on a key that is an interior element
  // of a nonempty tree.
  @Test public void testPublic10() {
    assertEquals(2, TestData.examplePolyTree2().distanceFromRoot(1));
  }

  // Tests calling distanceFromRoot() on keys that are leaf elements of
  // nonempty trees.
  @Test public void testPublic11() {
    assertEquals(2, TestData.examplePolyTree1().distanceFromRoot(17));
    assertEquals(4, TestData.examplePolyTree2().distanceFromRoot(2));
  }

  // Tests calling distanceFromRoot() on an key that is not present in a
  // nonempty tree.
  @Test public void testPublic12() {
    assertEquals(-1, TestData.examplePolyTree2().distanceFromRoot(100000));
  }

  // Tests calling distanceFromRoot() on an empty tree.
  @Test public void testPublic13() {
    assertEquals(-1, EmptyPolyTree.getInstance().distanceFromRoot(1));
  }

  // Tests calling deleteKeyValuePair() on an interior element of a nonempty
  // tree.
  @Test public void testPublic14() {
    PolyTree<Integer, Character> tree= TestData.examplePolyTree2();

    tree= tree.deleteKeyValuePair(13);

    assertEquals(7, tree.treeSize());
    assertEquals("1+e 2+l 3+e 5+p 7+h 11+a 17+t ", tree.toString());
  }

  // Tests calling deleteKeyValuePair() on a key that is not present in a
  // nonempty tree.
  @Test public void testPublic15() {
    PolyTree<Integer, Character> tree= TestData.examplePolyTree2();

    tree= tree.deleteKeyValuePair(100000);

    assertEquals(8, tree.treeSize());
    assertEquals("1+e 2+l 3+e 5+p 7+h 11+a 13+n 17+t ", tree.toString());
  }

}
