package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import linkedStr.LinkedStr;

public class TestUtilities {

  public static String toString(LinkedStr myStr) {
    String result= "";
    int i= 0;

    while (i < myStr.length()) {
      result += myStr.getCharAt(i);
      i++;
    }

    return result;
  }

}
