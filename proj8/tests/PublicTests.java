package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import dirGraph.DirGraph;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  /* A few tests use a method compareCollection() definied in the TestData
   * class that checks a Collection to see if it contains expected contents.
   * See the comments before it in the TestData class for more info.  One
   * test uses a method compareGraph(), also in the TestData class.  It is
   * passed a graph and some other parameters representing the expected
   * components of the graph, and it compares to see if the graph's
   * components match the parameter values.  Again, see the comments before
   * it in the TestData class for explanation.
   *
   * You can use both of these methods in your own student tests as needed,
   * if you take a few moments first to understand what they are doing.
   * (What this means is to understand what these methods are doing and how
   * to call them- it's not necessary to understand their code to use these
   * helper methods in your own tests, as long as you understand what
   * they're doing and what to pass into them.)
   */

  // Tests calling isVertex() on the vertices of a simple graph that has
  // vertices but no edges, and calling createVertex() on an
  // already-existing vertex.
  @Test public void testPublic1() {
    DirGraph<String> graph= TestData.exampleDirGraph1();

    for (String vertex : Arrays.asList("aardvark", "beagle", "cat",
                                       "donkey", "elephant", "flamingo",
                                       "giraffe", "hyena", "iguana",
                                       "jaguar", "koala", "lemur",
                                       "manatee", "numbat", "ocelot"))
      assertTrue(graph.isVertex(vertex));

    // test the return value of createVertex() in the normal case
    assertTrue(graph.createVertex("parrot"));
    assertTrue(graph.isVertex("parrot"));

    assertFalse(graph.createVertex("parrot"));
  }

  // Tests calling isVertex() on an empty graph and on a nonexistent vertex,
  // and calling getVertices() on a graph that contains vertices but no
  // edges.
  @Test public void testPublic2() {
    DirGraph<String> graph= new DirGraph<>();

    assertFalse(graph.isVertex("zebra"));
    graph= TestData.exampleDirGraph1();
    assertFalse(graph.isVertex("zebra"));

    assertTrue(TestData.compareCollection(
                 Arrays.asList("aardvark", "beagle", "cat", "donkey",
                               "elephant", "flamingo", "giraffe", "hyena",
                               "iguana", "jaguar", "koala", "lemur",
                               "manatee", "numbat", "ocelot"),
                 graph.getVertices()));
  }

  // Tests calling deleteVertexWithEdges() to remove some vertices from a
  // graph that contains vertices but no edges.
  @Test public void testPublic3() {
    DirGraph<String> graph= TestData.exampleDirGraph1();
    int pos= 1;

    // remove half of the current vertices
    for (String vertex : Arrays.asList("aardvark", "cat", "elephant",
                                       "giraffe", "iguana", "koala",
                                       "manatee", "ocelot"))
      assertTrue(graph.deleteVertexWithEdges(vertex));

    for (String vertex : Arrays.asList("aardvark", "beagle", "cat",
                                       "donkey", "elephant", "flamingo",
                                       "giraffe", "hyena", "iguana",
                                       "jaguar", "koala", "lemur",
                                       "manatee", "numbat", "ocelot")) {
      // every other vertex was removed from this list of original vertex
      // names, so test alternating vertices for presence and absence
      if (pos % 2 == 1)
        assertFalse(graph.isVertex(vertex));
      else assertTrue(graph.isVertex(vertex));

      pos++;
    }
  }

  // Tests calling createEdge() and getEdge() in the normal case.
  @Test public void testPublic4() {
    DirGraph<Character> graph=
                        TestData.exampleDirGraph2();  // calls createEdge()
    char[] vertices= {'e', 'd', 'u', 'c', 'a', 't', 'i', 'o', 'n'};
    int i;

    for (i= 0; i < vertices.length - 1; i++)
      assertEquals(1, graph.getEdge(vertices[i], vertices[i + 1]));
  }

  // Tests calling createEdge() to try to create an edge with negative
  // weight, and on an already-existing edge, both of which should fail.
  @Test public void testPublic5() {
    // this is going to be a very small graph
    DirGraph<Boolean> graph= new DirGraph<>();

    graph.createVertex(true);
    graph.createVertex(false);
    assertTrue(graph.createEdge(true, false, 132));
    assertFalse(graph.createEdge(false, true, -1));
    assertFalse(graph.createEdge(true, false, 132));
    assertEquals(132, graph.getEdge(true, false));
  }

  // Tests calling getEdge() on existing vertices that don't have an edge
  // between them.
  @Test public void testPublic6() {
    DirGraph<String> graph= TestData.exampleDirGraph3();
    String[] vertices= {"aardvark", "beagle", "cat", "donkey", "elephant",
                        "flamingo", "giraffe", "hyena", "iguana",
                        "jaguar", "koala", "lemur", "manatee", "numbat",
                        "ocelot"};
    int i, j;

    for (i= vertices.length - 1; i >= 0; i--)
      for (j= i; j >= 0; j--)
        assertEquals(-1, graph.getEdge(vertices[i], vertices[j]));
  }

  // Tests calling deleteVertexWithEdges() on a vertex that has outgoing
  // edges.
  @Test public void testPublic7() {
    DirGraph<Integer> graph= TestData.exampleDirGraph4();

    assertTrue(graph.deleteVertexWithEdges(8));
    assertFalse(graph.isVertex(8));
  }

  // Tests calling deleteVertexWithEdges() on a vertex that has both
  // outgoing and incoming edges.
  @Test public void testPublic8() {
    DirGraph<Integer> graph= TestData.exampleDirGraph4();

    assertTrue(graph.deleteVertexWithEdges(6));
    assertFalse(graph.isVertex(6));
    assertEquals(-1, graph.getEdge(6, 3));
    assertEquals(-1, graph.getEdge(6, 4));
    assertEquals(-1, graph.getEdge(6, 5));
    assertEquals(-1, graph.getEdge(7, 6));
    assertEquals(-1, graph.getEdge(8, 6));
  }

  // Tests calling deleteVertexWithEdges() to remove all of the vertices
  // from a graph (that contains vertices but no edges) and then adds some
  // new vertices.
  @Test public void testPublic9() {
    DirGraph<String> graph= TestData.exampleDirGraph1();
    String[] vertices= {"aardvark", "beagle", "cat", "donkey", "elephant",
                        "flamingo", "giraffe", "hyena", "iguana",
                        "jaguar", "koala", "lemur", "manatee", "numbat",
                        "ocelot"};
    String[] newVertices= {"penguin", "quokka", "rhinoceros",
                           "salamander", "turtle", "umbrellabird",
                           "vulture", "wallaby", "beagle", "koala"};

    for (String vertex : vertices)
      graph.deleteVertexWithEdges(vertex);

    for (String vertex : vertices)
      assertFalse(graph.isVertex(vertex));

    for (String vertex : newVertices)
      graph.createVertex(vertex);

    for (String vertex : newVertices)
      assertTrue(graph.isVertex(vertex));
  }

  // Tests calling neighbors() in the normal case.
  @Test public void testPublic10() {
    DirGraph<Integer> graph= TestData.exampleDirGraph4();
    // a List of Lists of integers
    List<List<Integer>> expectedNeighbors=
      Arrays.asList(
        Arrays.asList(2, 3, 4, 5),  // neighbors of 1
        Arrays.asList(4),           // neighbors of 2
        Arrays.asList(4),           // neighbors of 3
        Arrays.asList(),            // neighbors of 4
        Arrays.asList(4),           // neighbors of 5
        Arrays.asList(3, 4, 5),     // neighbors of 6
        Arrays.asList(3, 6),        // neighbors of 7
        Arrays.asList(4, 6, 7)      // neighbors of 8
    );
    int i;

    // normal cases
    for (i= 0; i < expectedNeighbors.size(); i++)
      assertTrue(TestData.compareCollection(
                   expectedNeighbors.get(i),
                   graph.neighborsOfVertex(i + 1)));
  }

  // Tests calling neighborsOfVertex() on a nonexistent vertex.
  @Test public void testPublic11() {
    DirGraph<Character> graph= TestData.exampleDirGraph2();

    assertNull(graph.neighborsOfVertex('x'));
  }

  // Tests calling changeEdge() when newWeight is nonnegative in the normal
  // case and when one or both vertices don't exist.
  @Test public void testPublic12() {
    DirGraph<Integer> graph= TestData.exampleDirGraph4();

    // normal cases
    assertTrue(graph.changeEdge(2, 4, 100));
    assertTrue(graph.changeEdge(8, 4, 200));
    assertTrue(graph.changeEdge(8, 6, 300));
    assertEquals(100, graph.getEdge(2, 4));
    assertEquals(200, graph.getEdge(8, 4));
    assertEquals(300, graph.getEdge(8, 6));

    // when the source vertex doesn't exist
    assertFalse(graph.changeEdge(8, 9, 400));
    // when the destination vertex doesn't exist
    assertFalse(graph.changeEdge(9, 8, 500));
    // when neither vertex exists
    assertFalse(graph.changeEdge(9, 10, 600));

    assertEquals(8, graph.getVertices().size());
    assertFalse(graph.isVertex(9));
    assertFalse(graph.isVertex(10));
    assertTrue(TestData.compareCollection(
                 Arrays.asList(4, 6, 7),
                 graph.neighborsOfVertex(8)));
  }

  // Tests calling changeEdge() when newWeight is negative in the normal
  // case and when one or both vertices don't exist.
  @Test public void testPublic13() {
    DirGraph<Integer> graph= TestData.exampleDirGraph4();

    // normal cases
    assertTrue(graph.changeEdge(2, 4, -1));
    assertTrue(graph.changeEdge(8, 4, -1));
    assertTrue(graph.changeEdge(8, 6, -1));
    assertEquals(-1, graph.getEdge(2, 4));
    assertEquals(-1, graph.getEdge(8, 4));
    assertEquals(-1, graph.getEdge(8, 6));
    assertTrue(TestData.compareCollection(
                 Arrays.asList(),
                 graph.neighborsOfVertex(2)));
    assertTrue(TestData.compareCollection(
                 Arrays.asList(7),
                 graph.neighborsOfVertex(8)));

    graph= TestData.exampleDirGraph4();

    // when the source vertex doesn't exist
    assertFalse(graph.changeEdge(8, 9, -1));
    // when the destination vertex doesn't exist
    assertFalse(graph.changeEdge(9, 8, -1));
    // when neither vertex exists
    assertFalse(graph.changeEdge(9, 10, -1));

    assertEquals(8, graph.getVertices().size());
    assertFalse(graph.isVertex(9));
    assertFalse(graph.isVertex(10));
    assertTrue(TestData.compareCollection(
                 Arrays.asList(4, 6, 7),
                 graph.neighborsOfVertex(8)));
  }

  // Tests calling divideGraph() on an empty graph.
  @Test public void testPublic14() {
    DirGraph<Boolean> graph= new DirGraph<>();
    DirGraph<Boolean> graph2;

    graph2= graph.divideGraph(Arrays.asList(true));
    assertEquals(0, graph.getVertices().size());
    assertEquals(0, graph2.getVertices().size());
  }

  // Tests calling divideGraph() when all of the graph's vertices are in
  // verticesOfNewGraph}.
  @Test public void testPublic15() {
    DirGraph<Character> graph= TestData.exampleDirGraph2();
    DirGraph<Character> graph2;

    graph2= graph.divideGraph(Arrays.asList('e', 'd', 'u', 'c', 'a',
                                            't', 'i', 'o', 'n'));

    assertEquals(0, graph.getVertices().size());
    assertEquals(9, graph2.getVertices().size());
  }

  // Tests calling divideGraph() when none of the graph's vertices are in
  // verticesOfNewGraph}.
  @Test public void testPublic16() {
    DirGraph<Character> graph= TestData.exampleDirGraph2();
    DirGraph<Character> graph2;

    graph2= graph.divideGraph(Arrays.asList());

    assertEquals(9, graph.getVertices().size());
    assertEquals(0, graph2.getVertices().size());
  }

  // Tests calling divideGraph() when verticesOfNewGraph contains duplicate
  // occurrences of vertices that are in the graph (which should have no
  // effect).
  @Test public void testPublic17() {
    DirGraph<Character> graph= TestData.exampleDirGraph5();
    DirGraph<Character> graph2;

    graph2= graph.divideGraph(Arrays.asList('f', 'r', 'o', 'g',
                                            'f', 'r', 'o', 'g'));

    assertEquals(5, graph.getVertices().size());
    assertEquals(4, graph2.getVertices().size());
  }

  // Tests calling divideGraph() when verticesOfNewGraph contains some
  // nonexistent vertices.
  @Test public void testPublic18() {
    DirGraph<Character> graph= TestData.exampleDirGraph5();
    DirGraph<Character> graph2;

    graph2= graph.divideGraph(Arrays.asList('f', 'r', 'o', 'g',
                                            'w', 'x', 'y', 'z'));

    assertEquals(5, graph.getVertices().size());
    assertEquals(4, graph2.getVertices().size());
  }

  // Tests calling divideGraph() when some of the graph's vertices are in
  // verticesOfNewGraph and some of the graph's edges remain in the current
  // object graph and the rest are moved to the new graph.
  //
  // To understand what the helper method is doing, look at its comments
  // in the TestData class, draw the graph that's being passed into it
  // in each call, and compare the graph to the method's arguments
  @Test public void testPublic19() {
    DirGraph<Character> graph= TestData.exampleDirGraph5();
    DirGraph<Character> graph2;

    graph2= graph.divideGraph(Arrays.asList('f', 'r', 'o', 'g'));

    assertEquals(5, graph.getVertices().size());
    assertEquals(4, graph2.getVertices().size());
    // uses helper method to check original graph
    assertTrue(TestData.compareGraph(
                 graph,  // the graph to check or compare
                 Arrays.asList('c', 'a', 'm', 'e', 'l'),
                 Arrays.asList(
                   Arrays.asList('a', 'e'),   // neighbors of c
                   Arrays.asList('e'),        // neighbors of a
                   Arrays.asList('l'),        // neighbors of m
                   Arrays.asList('c', 'm'),   // neighbors of e
                   Arrays.asList('c', 'e')),  // neighbors of l
                 Arrays.asList(
                   Arrays.asList(2, 2),       // costs to c's neighbors
                   Arrays.asList(2),          // costs to a's neighbors
                   Arrays.asList(2),          // costs to m's neighbors
                   Arrays.asList(2, 2),       // costs to e's neighbors
                   Arrays.asList(2, 2))));    // costs to l's neighbors
    // uses helper method to check new graph
    assertTrue(TestData.compareGraph(
                 graph2,  // the graph to check or compare
                 Arrays.asList('f', 'r', 'o', 'g'),
                 Arrays.asList(
                   Arrays.asList('o'),       // neighbors of f
                   Arrays.asList('f', 'o'),  // neighbors of r
                   Arrays.asList('g'),       // neighbors of o
                   Arrays.asList('r')),      // neighbors of g
                 Arrays.asList(
                   Arrays.asList(2),         // costs to f's neighbors
                   Arrays.asList(2, 2),      // costs to r's neighbors
                   Arrays.asList(2),         // costs to o's neighbors
                   Arrays.asList(2))));      // costs to g's neighbors
  }

  // Tests calling createVertex() and isVertex() with null arguments.
  @Test public void testPublic20() {
    DirGraph<Character> graph= new DirGraph<>();

    try {
      graph.createVertex(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    try {
      graph.isVertex(null);
      // if we reach here- meaning if the expected exception was not thrown-
      // the test should fail
      fail();
    } catch (IllegalArgumentException e) {
      // if we get here the expected exception was thrown, so continue to
      // the next thing we want to test
    }

    assertEquals(0, graph.getVertices().size());
  }

}
