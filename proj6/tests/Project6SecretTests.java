package tests;

// (c) Dale Dullnig and Larry Herman, 2020.  You are allowed to use this
// code yourself, but not to provide it to anyone else.

import bst.PolyTree;
import bst.EmptyPolyTree;
import bst.EmptyPolyTreeException;
import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

@SuppressWarnings({"unchecked"})
public class Project6SecretTests {

  // Tests calling addKeyValuePair() on an empty tree when aKey is null,
  // on an empty tree when aValue is null, on a nonempty tree when aKey
  // is null, and on a nonempty tree when aValue is null.
  @Test public void testSecret1() {
    try {
      EmptyPolyTree.getInstance().addKeyValuePair(null, 5);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    try {
      EmptyPolyTree.getInstance().addKeyValuePair(7, null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    try {
      TestData.examplePolyTree1().addKeyValuePair(null, 'p');
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    try {
      TestData.examplePolyTree2().addKeyValuePair(7, null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so the test
      // passes
    }
  }

  // Tests calling addKeyValuePair() when aKey is already present in the tree;
  // this should replace the value associated with it.
  @Test public void testSecret2() {
    assertEquals(Character.valueOf('d'), examplePolyTree6()
        .addKeyValuePair('k', 'd')
        .lookupValueByKey('k'));
  }

  // Tests calling lookupValueByKey() on the key of an leaf element of a
  // nonempty tree.
  @Test public void testSecret3() {
    assertEquals(Character.valueOf('g'),
                 examplePolyTree5().lookupValueByKey(3));
  }

  // Tests calling lookupValueByKey() on a key that is not in a tree.
  @Test public void testSecret4() {
    assertNull(examplePolyTree6().lookupValueByKey('z'));
  }

  // Tests calling lookupValueByKey() on an empty tree.
  @Test public void testSecret5() {
    assertNull(EmptyPolyTree.getInstance().lookupValueByKey('z'));
  }

  // Tests calling lookupValueByKey() when aKey is null, on both an empty tree
  // and a nonempty tree.
  @Test public void testSecret6() {
    try {
      EmptyPolyTree.getInstance().lookupValueByKey(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    try {
      TestData.examplePolyTree1().lookupValueByKey(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so the test
      // passes
    }
  }

  // Tests calling max() on an empty tree.
  @Test public void testSecret7() {
    try {
      EmptyPolyTree.getInstance().max();
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (EmptyPolyTreeException e) {
      // if we get here the expected exception was thrown, so the test
      // passes
    }
  }

  // Tests calling max() on a degenerate tree where every element,
  // including the root, only has a left child.
  @Test public void testSecret8() {
    try {
      assertEquals(Integer.valueOf(30), examplePolyTree7().max());
    } catch (EmptyPolyTreeException e) {
      fail();
    }
  }

  //Tests calling min() on an empty tree.
  @Test public void testSecret9() {
    try {
      EmptyPolyTree.getInstance().min();
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (EmptyPolyTreeException e) {
      // if we get here the expected exception was thrown, so the test
      // passes
    }
  }

  // Tests calling min() on a degenerate tree where every element,
  // including the root, only has a right child.
  @Test public void testSecret10() {
    try {
      assertEquals(Integer.valueOf(2), examplePolyTree8().min());
    } catch (EmptyPolyTreeException e) {
      fail();
    }
  }

  // Tests calling deleteKeyValuePair() on an empty tree.
  @Test public void testSecret11() {
    assertEquals(EmptyPolyTree.getInstance(), 
        EmptyPolyTree.getInstance().deleteKeyValuePair(3));
  }

  // Tests calling deleteKeyValuePair() on the key of the root element of a
  // nonempty tree.
  @Test public void testSecret12() {
    PolyTree<Integer, Character> test= 
        examplePolyTree4().deleteKeyValuePair(8);

    assertNull(test.lookupValueByKey(8));
    assertEquals("2+g 4+a 6+n 10+a 12+l 14+f ", test.toString());
  }

  // Tests calling deleteKeyValuePair() on the key of a leaf element
  // of a nonempty tree.
  @Test public void testSecret13() {
    PolyTree<Integer, Character> test= 
        examplePolyTree4().deleteKeyValuePair(2);

    assertEquals(2, test.distanceFromRoot(6));
    assertEquals("4+a 6+n 8+d 10+a 12+l 14+f ", test.toString());
  }

  // Tests calling deleteKeyValuePair() on the key of the root element
  // of a degenerate tree where every element, including the root, only
  // has a left child.
  @Test public void testSecret14() {
    PolyTree<Integer, Character> test= 
        examplePolyTree7().deleteKeyValuePair(30);

    assertEquals(3, test.distanceFromRoot(22));
    assertEquals("2+e 4+c 6+n 8+e 10+c 12+s 14+e 16+n 18+i 20+m "
        + "22+u 24+l 26+o 28+i ", test.toString());
  }

  // Tests calling deleteKeyValuePair() on the key of the root element
  // of a degenerate tree where every element, including the root, only
  // has a right child.
  @Test public void testSecret15() {
    PolyTree<Integer, Character> test= 
        examplePolyTree8().deleteKeyValuePair(2);

    assertEquals(4, test.distanceFromRoot(12));
    assertEquals("4+i 6+o 8+l 10+u 12+m 14+i 16+n 18+e 20+s 22+c "
        + "24+e 26+n 28+c 30+e ", test.toString());
  }

  // Tests calling deleteKeyValuePair() to remove all
  // of the elements of a nonempty tree.
  @Test public void testSecret16() {
    PolyTree<Integer, Character> test= examplePolyTree5()
        .deleteKeyValuePair(3)
        .deleteKeyValuePair(13)
        .deleteKeyValuePair(5)
        .deleteKeyValuePair(15)
        .deleteKeyValuePair(8)
        .deleteKeyValuePair(10)
        .deleteKeyValuePair(20)
        .deleteKeyValuePair(18);

    assertEquals(0, test.treeSize());
    assertEquals("", test.toString());
  }

  // Tests calling distanceFromRoot() and deleteKeyValuePair() 
  // on a nonempty tree when aKey is null.
  @Test public void testSecret17() {
    try {
      TestData.examplePolyTree3().distanceFromRoot(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    try {
      TestData.examplePolyTree3().deleteKeyValuePair(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so the test
      // passes
    }
  }

  // Tests making a number of calls to addKeyValuePair()
  // and deleteKeyValuePair().
  @Test public void testSecret18() {
    PolyTree<Integer, Integer> test= EmptyPolyTree.getInstance()
        .addKeyValuePair(7, 107)
        .addKeyValuePair(1, 101)
        .addKeyValuePair(2, 102)
        .addKeyValuePair(9, 109)
        .addKeyValuePair(3, 103)
        .addKeyValuePair(8, 108)
        .deleteKeyValuePair(1)
        .addKeyValuePair(5, 105)
        .addKeyValuePair(10, 110)
        .deleteKeyValuePair(3)
        .addKeyValuePair(21, 101)
        .deleteKeyValuePair(5)
        .addKeyValuePair(4, 104)
        .addKeyValuePair(6, 106)
        .deleteKeyValuePair(7)
        .addKeyValuePair(12, 112)
        .addKeyValuePair(22, 102)
        .addKeyValuePair(13, 113)
        .deleteKeyValuePair(9)
        .addKeyValuePair(17, 117)
        .addKeyValuePair(23, 103)
        .addKeyValuePair(18, 118)
        .addKeyValuePair(25, 105)
        .addKeyValuePair(16, 116)
        .addKeyValuePair(11, 111)
        .addKeyValuePair(19, 119)
        .deleteKeyValuePair(13)
        .addKeyValuePair(26, 106)
        .addKeyValuePair(20, 120)
        .deleteKeyValuePair(17)
        .deleteKeyValuePair(19)
        .addKeyValuePair(14, 114)
        .deleteKeyValuePair(11)
        .addKeyValuePair(15, 115)
        .deleteKeyValuePair(15)
        .addKeyValuePair(28, 108)
        .addKeyValuePair(9, 19)
        .addKeyValuePair(11, 21)
        .addKeyValuePair(29, 109)
        .addKeyValuePair(13, 23)
        .addKeyValuePair(15, 25)
        .addKeyValuePair(17, 27)
        .addKeyValuePair(30, 110)
        .addKeyValuePair(24, 104)
        .addKeyValuePair(5, 15)
        .addKeyValuePair(7, 17)
        .addKeyValuePair(27, 107)
        .addKeyValuePair(1, 11)
        .addKeyValuePair(3, 13)
        .addKeyValuePair(19, 29);

    assertEquals(30, test.treeSize());
    assertEquals("1+11 2+102 3+13 4+104 5+15 6+106 7+17 8+108 9+19 10+110 "
        + "11+21 12+112 13+23 14+114 15+25 16+116 17+27 18+118 19+29 20+120 "
        + "21+101 22+102 23+103 24+104 25+105 26+106 27+107 28+108 29+109 "
        + "30+110 ", test.toString());
  }

  // Tests calling distanceFromRoot() and deleteKeyValuePair() on an empty
  // tree when aKey is null.
  @Test public void testSecret19() {
    try {
      EmptyPolyTree.getInstance().distanceFromRoot(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    try {
      EmptyPolyTree.getInstance().deleteKeyValuePair(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so the test
      // passes
    }
  }

  // inner (helper) classes /////////////////////////////////////////////

  // Returns a small tree with several elements, with Integer keys and
  // Character values.
  private static PolyTree<Integer, Character> examplePolyTree4() {
    return TestData.createPolyTree(Arrays.asList(8,  4,  6,  2, 10, 12, 14),
        Arrays.asList('d', 'a', 'n', 'g', 'a', 'l', 'f'));
  }

  // Returns a small tree with several elements, with Integer keys and
  // Character values.
  private static PolyTree<Integer, Character> examplePolyTree5() {
    return TestData.createPolyTree(Arrays.asList(5, 15, 10,  8,  3, 18, 20,
        13),
        Arrays.asList('o', 'o', 'd', 'l', 'g', 'r', 'e',
            'c'));
  }

  // Returns a slightly larger tree with Character keys and Character values.
  private static PolyTree<Character, Character> examplePolyTree6() {
    return TestData.createPolyTree(Arrays.asList('q', 'u', 'i', 'c', 'k',
        'b' , 'r', 'o', 'w', 'n', 'f', 'o', 'x', 'l', 'd'),
        Arrays.asList('b', 'i', 'o', 'l', 'u', 'm', 'i',
            'n', 'e', 's', 'c', 'e', 'n', 'c', 'e'));
  }

  // Returns a tree with only left branches.
  private static PolyTree<Integer, Character> examplePolyTree7() {
    return TestData.createPolyTree(Arrays.asList( 30, 28, 26, 24, 22, 20, 18,
        16, 14, 12, 10, 8, 6, 4, 2),
        Arrays.asList('b', 'i', 'o', 'l', 'u', 'm', 'i',
            'n', 'e', 's', 'c', 'e', 'n', 'c', 'e'));
  }

  // Returns a tree with only right branches.
  private static PolyTree<Integer, Character> examplePolyTree8() {
    return TestData.createPolyTree(Arrays.asList(2, 4, 6, 8, 10, 12, 14,
        16, 18, 20, 22, 24, 26, 28, 30),
        Arrays.asList('b', 'i', 'o', 'l', 'u', 'm', 'i',
            'n', 'e', 's', 'c', 'e', 'n', 'c', 'e'));
  }

}
