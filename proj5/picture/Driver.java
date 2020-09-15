package picture;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Iterator;

public class Driver {

  public static void main(String args[]) {
    Picture picture= new Picture();
    Iterator<Shape> iter;

    picture.addShape(new Shape("square", 4));
    picture.addShape(new Shape("circle", 2));
    picture.addShape(new Shape("dodecahedron", 6));
    picture.addShape(new Shape("triangle", 5));
    picture.addShape(new Shape("pentagon", 1));

    iter= picture.iterator();

    while (iter.hasNext())
      System.out.println(iter.next());
  }

}
