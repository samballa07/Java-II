package picture;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

public class Shape {

  private String type;
  private int area;

  public Shape(String type, int area) {
    this.type= type;
    this.area= area;
  }

  public String toString() {
    return '(' + type + ' ' + area + ')';
  }

  public boolean equals(Object obj) {
    Shape otherShape;
    boolean result= false;

    if (obj == this)
      result= true;
    else
      if (obj instanceof Shape) {
        otherShape= (Shape) obj;
        result= type.equals(otherShape.type) && area == otherShape.area;
      }

    return result;
  }

  // if we implement equals() we must implement hashCode() (this will be
  // covered later)
  public int hashCode() {
    return 0;
  }

}
