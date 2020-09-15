package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import picture.Shape;
import picture.Picture;

/* This class contains utility methods that create and return example
 * Picture objects that the public (and secret) tests can use, to reduce the
 * amount of code needed in different tests to create objects to test the
 * methods with.  You can use these methods in your student tests as well,
 * but don't modify this file, because our version is going to be used on
 * the submit server.  (You can write your own helper methods in your
 * StudentTests class, and you can add your own classes to the tests
 * package, if you want to use modified versions of these methods.)
 */

public class TestData {

  // This is public so the public tests can use it, which some of them do.
  // They make copies of it though (using Arrays.copyOf()), because some
  // tests remove elements, and we need the array to be unmodified in later
  // tests.
  public final static Shape[] shapeArr= {
    new Shape("dodecahedron", 6),  // listed one per line for clarity
    new Shape("pentagon", 1), 
    new Shape("square", 4),
    new Shape("circle", 2),
    new Shape("octagon", 8),
    new Shape("line", 3),
    new Shape("triangle", 5),
    new Shape("hexadecagon", 11),
    new Shape("icosagon", 7),
    new Shape("rhombus", 9),
    new Shape("nonagon", 10)
  };

  // Returns a sample Picture that is storing eleven Shapes (the ones in the
  // array above).  Since the Picture addShape() method adds each shape to
  // the beginning of a linked list of shapes, the shapes will be stored in
  // the picture in reverse order of how they appear in the array above.
  public static Picture examplePicture() {
    Picture picture= new Picture();

    for (Shape shape : shapeArr)
      picture.addShape(shape);

    return picture;
  }

  // Searches for a shape in an unsorted array of shapes, returning true if
  // and only if it is found; also removes the shape from the array (so we
  // can test that an iterator is returning each value only once).
  public static boolean findShape(Shape[] shapeArray, Shape aShape) {
    int i= 0;
    boolean found= false;

    while (i < shapeArray.length && !found) {
      if (shapeArray[i] != null)
        found= shapeArray[i].equals(aShape);
      i++;
    }

    if (found) {
      while (i <= shapeArray.length - 1) {
        // note: i was already incremented one past the shape, if found
        shapeArray[i - 1]= shapeArray[i];
        i++;
      }

      shapeArray[i - 1]= null;  // really remove the last element
    }

    return found;
  }

}
