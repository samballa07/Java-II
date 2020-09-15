/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized  
 * assistance on this assignment
 */

package tests;

import org.junit.*;
import dirGraph.DirGraph;
import static org.junit.Assert.*;
import java.util.HashSet;

public class StudentTests {

	// tests the basic functionality of delete
	@Test
	public void testDelete() {
		DirGraph<Integer> graph = TestData.exampleDirGraph4();
		assertTrue(graph.deleteVertexWithEdges(7));
		assertFalse(graph.deleteVertexWithEdges(9));
		assertFalse(graph.isVertex(7));

	}

	// tests getVertices when graph is empty
	@Test
	public void testGetVertices() {
		DirGraph<Integer> graph = new DirGraph<Integer>();

		assertTrue(graph.getVertices().size() == 0);

	}

	// Tests createEdge when vertices are not already in graph
	@Test
	public void testCreateEdge() {
		DirGraph<Integer> graph = TestData.exampleDirGraph4();

		assertTrue(graph.createEdge(54, 36, 69));

		assertTrue(graph.isVertex(54));
		assertTrue(graph.isVertex(36));
		assertEquals(69, graph.getEdge(54, 36));
	}

	// Tests createEdge on vertices that already have an edge
	@Test
	public void testCreateEdge1() {
		DirGraph<Integer> graph = TestData.exampleDirGraph4();

		assertFalse(graph.createEdge(1, 2, 32));
		assertEquals(15, graph.getEdge(1, 2));
	}

	//Tests createEdge on a vertex that has an edge to itself
	@Test public void testCreateEdge2() {
		DirGraph<Integer> graph = TestData.exampleDirGraph4();

		assertTrue(graph.createEdge(8, 8, 32));
		assertEquals(32, graph.getEdge(8, 8));
	}

	// tests getEdge when only one of the vectors exist in graph
	@Test public void testGetEdge() {
		DirGraph<Integer> graph = TestData.exampleDirGraph4();

		graph.createVertex(40);

		assertEquals(-1, graph.getEdge(40, 70));
		assertEquals(-1, graph.getEdge(60, 40));
	}

	// tests changeEdge when newWeight is nonnegative
	@Test public void testChangeEdge() {
		DirGraph<Character> graph = TestData.exampleDirGraph5();

		assertTrue(graph.changeEdge('f', 'o', 0));
		assertEquals(0, graph.getEdge('f', 'o'));

		graph.createVertex('s');
		graph.createVertex('z');
		assertFalse(graph.changeEdge('s', 'z', 1));
		assertFalse(graph.changeEdge('f', 's', 9));

	}

	// tests changeEdge when newWeight is negative
	@Test public void testChangeEdge1() {
		DirGraph<Character> graph = TestData.exampleDirGraph5();

		assertTrue(graph.changeEdge('f', 'o', -2));
		assertEquals(-1, graph.getEdge('f', 'o'));

		graph.createVertex('s');
		graph.createVertex('f');
		assertFalse(graph.changeEdge('s', 'z', -8));
		assertFalse(graph.changeEdge('f', 's', -5));

	}

	// tests basic functionality of neighborsOfVertex()
	@Test public void testNeighbors() {
		DirGraph<Character> graph = TestData.exampleDirGraph5();

		HashSet<Character> testSet = new HashSet<Character>();
		testSet.add('f');
		testSet.add('o');

		for (Character val : graph.neighborsOfVertex('r')) {
			assertTrue(testSet.contains(val));
		}

	}

}
