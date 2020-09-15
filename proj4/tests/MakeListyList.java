package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import recursionPractice.ListyList;
import java.util.List;

public class MakeListyList {

  // This class contains just one static utility method, which adds a list
  // of elements of any type to a ListyList object and returns it, for use in
  // creating ListyList objects for testing the methods to be written.

  public static <T> ListyList<T> makeList(List<T> elements) {
    ListyList<T> list= new ListyList<>();

    // we return an empty ListyList if the user is dumb enough to pass in
    // null
    if (elements != null)
      for (T elt : elements)
        // we skip adding any null elements in the parameters, if the user
        // is dumb enough to have included them (we are proactively guarding
        // against the possibility that our method may be called by many
        // dumb users)
        if (elt != null)
          list.add(elt);

    return list;
  }

}
