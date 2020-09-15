package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

// This class contains a helper method used in the public tests.

import wordFrequencyCounter.WordFrequencyCounter;
import java.util.Collection;

public class TestUtilities {

  // utility method /////////////////////////////////////////////////////

  // In various tests we have to check the contents of a Collection returned
  // by a method, but we can't create a Collection that has the expected
  // values and use the equals() method to compare against the Collection,
  // because we don't even know what kind of Collection the methods will
  // return.  This method takes a Collection to check and another Collection
  // with the expected values (this Collection will actually be some type of
  // List).  Then it uses the Collection containsAll() method to compare the
  // two parameter Collections.  If we have two collections A and B, and A
  // contains all of the elements of B, and B contains all of the elements
  // of A, then it must be the case that they must have all the same
  // elements, and only the same elements.  Of course this is not
  // particularly efficient, but our goal is just to make it easy to check
  // the results of tests.
  public static <T> boolean compareCollection(Collection<T> collection,
                                              Collection<T> expected) {
    return collection.containsAll(expected) &&
           expected.containsAll(collection);
  }

}
