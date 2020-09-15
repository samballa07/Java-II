package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import picture.Shape;
import picture.Picture;
import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Checks the value of calling hasNext() on a new iterator over a nonempty
  // Picture, which should be true.
  @Test public void testPublic1() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();

    assertTrue(iter.hasNext());
  }

  // Checks that when hasNext() is called on an iterator for a Picture with
  // several elements, it still returns true after the first call to next().
  @Test public void testPublic2() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();

    iter.next();  // we just ignore the Shape returned

    assertTrue(iter.hasNext());
  }

  // Checks the values returned by multiple iterations (meaning calling
  // next() multiple times) on an iterator.  Note that there is no
  // requirement that an iterator return the elements of an object in any
  // particular order, so we are checking that the iterator next() method
  // returns the shapes in the picture, but we don't know what order they
  // will be returned in.
  @Test public void testPublic3() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);
    int i;

    for (i= 0; i < expectedShapes.length; i++)
      // findShape() is calling the Shape equals() method
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
  }

  // Checks that hasNext() can be called multiple times and returns the
  // right value each time.
  @Test public void testPublic4() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();

    assertTrue(iter.hasNext());
    assertTrue(iter.hasNext());
    assertTrue(iter.hasNext());

    while (iter.hasNext())
      iter.next();

    assertFalse(iter.hasNext());
    assertFalse(iter.hasNext());
    assertFalse(iter.hasNext());
  }

  // Checks the value of hasNext() on a new iterator over an empty Picture,
  // which should be false.
  @Test public void testPublic5() {
    Picture picture= new Picture();
    Iterator<Shape> iter= picture.iterator();

    assertFalse(iter.hasNext());
  }

  // Checks the values returned by multiple calls to next() on a Picture
  // iterator, until hasNext() returns false, which is also testing that
  // hasNext() properly returns false when it should, and the iterator
  // doesn't have any errors in this case.
  @Test public void testPublic6() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);

    // findShape() is calling the Shape equals() method
    while (iter.hasNext())
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
  }

  // Verifies that a Picture iterator's next() method properly throws a
  // NoSuchElementException when next() is called more times than the number
  // of Shapes in a Picture.
  @Test(expected= NoSuchElementException.class) public void testPublic7() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();

    while (true)
      iter.next();
  }

  // Tests that a new iterator can iterate over a Picture object after one
  // iterator has already iterated over it.
  @Test public void testPublic8() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);
    int count= 0;

    // findShape() is calling the Shape equals() method
    while (iter.hasNext()) {
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
      count++;
    }
    assertEquals(TestData.shapeArr.length, count);

    // now do the same thing with a new iterator
    count= 0;
    iter= picture.iterator();

    // reinitialize the array
    expectedShapes= Arrays.copyOf(TestData.shapeArr, TestData.shapeArr.length);
    // findShape() is calling the Shape equals() method
    while (iter.hasNext()) {
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
      count++;
    }
    assertEquals(TestData.shapeArr.length, count);
  }

  // Tests that two iterators can simultaneously iterate over a Picture
  // object.
  @Test public void testPublic9() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter1= picture.iterator();
    Iterator<Shape> iter2= null;
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);
    // make a second copy of the array
    Shape[] expectedShapes2= Arrays.copyOf(TestData.shapeArr,
                                           TestData.shapeArr.length);
    // will count every element returned by both iterators
    int count= 0;

    // continue until the second iterator has returned all of the shapes, as
    // well as before it has started
    while (iter2 == null || iter2.hasNext()) {
      if (iter1.hasNext()) {
        assertTrue(TestData.findShape(expectedShapes, iter1.next()));
        count++;
      }

      // start the second iterator when the first iterator has processed a
      // few elements
      if (count == 4)
        iter2= picture.iterator();
      else
        if (count > 4) {
          assertTrue(TestData.findShape(expectedShapes2, iter2.next()));
          count++;
        }
    }

    assertEquals(2 * TestData.shapeArr.length, count);
  }

  // Tests that an iterator for a Picture works correctly after the Picture
  // has been modified by adding new elements.  The picture is iterated
  // over, elements are added, and the picture is iterated over again, to
  // ensure that the iterator is iterating over the picture's correct
  // current contents.
  @Test public void testPublic10() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);
    int i;

    for (i= 0; i < expectedShapes.length; i++)
      // findShape() is calling the Shape equals() method
      assertTrue(TestData.findShape(expectedShapes, iter.next()));

    // add new shapes to the picture
    picture.addShape(new Shape("ellipse", 100));
    picture.addShape(new Shape("cone", 110));
    picture.addShape(new Shape("hexagram", 120));

    // reinitialize the array used for checking the iterator, and add the
    // new shapes to it also
    expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                  TestData.shapeArr.length + 3);
    expectedShapes[expectedShapes.length - 3]= new Shape("ellipse", 100);
    expectedShapes[expectedShapes.length - 2]= new Shape("cone", 110);
    expectedShapes[expectedShapes.length - 1]= new Shape("hexagram", 120);

    // now create a new iterator and check the picture's contents again
    iter= picture.iterator();
    for (i= 0; i < expectedShapes.length; i++)
      // findShape() is calling the Shape equals() method
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
  }

  // Tests the basic operation of the Picture iterator remove() method to
  // remove just one Shape from a Picture.
  @Test public void testPublic11() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);
    int i;

    iter.next();
    iter.remove();

    // check the remaining four elements (findShape() is calling the Shape
    // equals() method)
    for (i= 1; i <= 4; i++)
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
  }

  // Tests that the right element is removed by calling the iterator
  // remove() method, meaning the one returned by the most recent call to
  // next().
  @Test public void testPublic12() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);
    Shape shapeToRemove;

    iter.next();
    // save the shape that's going to be removed from the picture
    shapeToRemove= iter.next();
    iter.remove();

    // now remove the shape that was removed from the picture from
    // expectedShapes, by calling findShape(), then iterate over the picture
    // again, making sure that all of the remaining shapes are present in it
    // (meaning that the right one was removed)
    TestData.findShape(expectedShapes, shapeToRemove);

    // check the elements remaining, using the iterator
    iter= picture.iterator();
    while (iter.hasNext())
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
  }

  // Test calling the Picture iterator remove() method to remove only some
  // Shapes from a Picture object (several in the middle).
  @Test public void testPublic13() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    int i, count= 0, firstToRemove= 4, numToRemove= 6;

    for (i= 1; i <= TestData.shapeArr.length; i++) {
      iter.next();
      // remove six elements starting with the fourth one
      if (i >= firstToRemove && i - firstToRemove + 1 <= numToRemove)
        iter.remove();
    }

    // now count the elements remaining and ensure that there are the right
    // number; the list should still be valid for a new iterator to work
    iter= picture.iterator();
    while (iter.hasNext()) {
      iter.next();
      count++;
    }
    assertEquals(TestData.shapeArr.length - numToRemove, count);
  }

  // Tests that the Picture iterator remove() method throws the expected
  // IllegalStateException if remove() is called before next().
  @Test(expected= IllegalStateException.class) public void testPublic14() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();

    iter.remove();
  }

  // Tests that the Picture iterator remove() method throws the expected
  // IllegalStateException if remove() has already been called after the
  // last call to next(), so the element has already been removed.
  @Test(expected= IllegalStateException.class) public void testPublic15() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();

    iter.next();
    iter.next();

    iter.remove();
    iter.remove();
  }

  // Test calling the Picture iterator remove() method to remove only some
  // Shapes from a Picture object (the first several).
  @Test public void testPublic16() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    int i, count= 0, numToRemove= 5;

    for (i= 1; i <= TestData.shapeArr.length; i++) {
      iter.next();
      if (i <= numToRemove)
        iter.remove();
    }

    // now count the elements remaining and ensure that there are the right
    // number; the list should still be valid for a new iterator to work
    iter= picture.iterator();
    while (iter.hasNext()) {
      iter.next();
      count++;
    }
    assertEquals(TestData.shapeArr.length - numToRemove, count);
  }

  // Test calling the Picture iterator remove() method to remove only some
  // Shapes from a Picture object (the last several).
  @Test public void testPublic17() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    int i, count= 0, numToRemove= 5;

    for (i= 1; i <= TestData.shapeArr.length; i++) {
      iter.next();
      if (i > TestData.shapeArr.length - numToRemove)
        iter.remove();
    }

    // now count the elements remaining and ensure that there are the right
    // number; the list should still be valid for a new iterator to work
    iter= picture.iterator();
    while (iter.hasNext()) {
      iter.next();
      count++;
    }
    assertEquals(TestData.shapeArr.length - numToRemove, count);
  }

  // Tests using the Picture iterator remove() method to remove all of the
  // Shapes from a Picture.
  @Test public void testPublic18() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);
    int i;

    for (i= 1; i <= TestData.shapeArr.length; i++) {
      // findShape() is calling the Shape equals() method
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
      iter.remove();
    }

    // ensure everything has been removed
    iter= picture.iterator();
    assertFalse(iter.hasNext());
  }

  // Tests that its hasNext() method returns false after all of the Shapes
  // have been removed from a Picture by calling the remove() iterator
  // method.
  @Test public void testPublic19() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= Arrays.copyOf(TestData.shapeArr,
                                          TestData.shapeArr.length);

    while (iter.hasNext()) {
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
      iter.remove();
    }

    // ensure everything has been removed
    iter= picture.iterator();
    assertFalse(iter.hasNext());
  }

  // Tests that the Picture iterator next() method throws the expected
  // NoSuchElementException when called after all of the elements have been
  // removed by the iterator remove() method.
  @Test(expected= NoSuchElementException.class) public void testPublic20() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();

    while (iter.hasNext()) {
      iter.next();
      iter.remove();
    }

    assertFalse(picture.iterator().hasNext());
    // the Picture should be empty now, so this should throw the exception
    iter.next();
  }

  // Tests using the Picture iterator remove() method to remove every other
  // Shape from a Picture.  First the odd-position shapes are removed
  // (meaning the first in the list, the third, etc.); then the
  // even-position ones are removed from the remaining ones.  Then the
  // Picture's contents are checked, and the remaining ones are removed, to
  // ensure that the list of shapes remains valid.
  @Test public void testPublic21() {
    Picture picture= TestData.examplePicture();
    Iterator<Shape> iter= picture.iterator();
    Shape[] expectedShapes= {
    new Shape("pentagon", 1),   // listed one per line for clarity
    new Shape("line", 3),
    new Shape("rhombus", 9),
    };
    int i;

    i= 1;
    while (iter.hasNext()) {
      iter.next();

      if (i % 2 != 0)
        iter.remove();

      i++;
    }

    iter= picture.iterator();  // a new iterator that starts at the beginning
    i= 1;
    while (iter.hasNext()) {
      iter.next();

      if (i % 2 == 0)
        iter.remove();

      i++;
    }

    // check current contents and remove them in the process
    iter= picture.iterator();
    while (iter.hasNext()) {
      // findShape() is calling the Shape equals() method
      assertTrue(TestData.findShape(expectedShapes, iter.next()));
      iter.remove();
    }

    // ensure everything has been removed
    iter= picture.iterator();
    assertFalse(iter.hasNext());
  }

}
