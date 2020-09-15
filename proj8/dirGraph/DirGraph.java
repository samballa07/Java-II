/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized  
 * assistance on this assignment
 */

package dirGraph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;


public class DirGraph<V> {

	
	LinkedHashMap<V, HashMap<V, Integer>> graph = 
			new LinkedHashMap<V, HashMap<V, Integer>>();

	
	public boolean createVertex(V vertexData) {
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}
		
		if (graph.containsKey(vertexData)) {
			return false;
		}
	
		graph.put(vertexData, new HashMap<V, Integer>());

		return true;
	}
	
	public boolean isVertex(V vertexData) {
		
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}

		if (graph.containsKey(vertexData)) {
			return true;
		}

		return false;
	}
	
	public Collection<V> getVertices() {

		
		Set<V> vertices = new HashSet<V>();

		vertices = graph.keySet();

		return vertices; 

	}

	public boolean deleteVertexWithEdges(V vertexData) {
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}

		if (!graph.containsKey(vertexData))
			return false;

		
		for (HashMap<V, Integer> map : graph.values()) {
			if (map.containsKey(vertexData)) {
				map.remove(vertexData);
			}
		}

		graph.remove(vertexData);

		return true;

	}

	
	public boolean createEdge(V sourceVertex, V destVertex, int weight) {
		if (sourceVertex == null || destVertex == null) {
			throw new IllegalArgumentException();
		}
		
		
		if (weight < 0) {
			return false;
		}

		
		if (graph.containsKey(sourceVertex) && 
				graph.containsKey(destVertex)) {
			
			if(graph.get(sourceVertex).containsKey(destVertex)) {
				
				return false;
			}		
		}
		createVertex(sourceVertex);
		createVertex(destVertex);
		
		graph.get(sourceVertex).put(destVertex, weight);

		return true;

	}

	
	public int getEdge(V sourceVertex, V destVertex) {
		if (sourceVertex == null || destVertex == null) {
			throw new IllegalArgumentException();
		}

		
		if (!graph.containsKey(sourceVertex) || 
			!graph.containsKey(destVertex)) {
			
			return -1;
		}

		
		if (!graph.get(sourceVertex).containsKey(destVertex)) {
			
			return -1;
		}

		
		return graph.get(sourceVertex).get(destVertex);

	}

	
	public boolean changeEdge(V sourceVertex, V destVertex, 
								int newWeight) {
		
		if (sourceVertex == null || destVertex == null) {
			throw new IllegalArgumentException();
		}
		
		if (!graph.containsKey(sourceVertex) || 
			!graph.containsKey(destVertex)) {
			return false;
		}
	
		if (!graph.get(sourceVertex).containsKey(destVertex)) {
			
			return false;
		}

		if (newWeight >= 0) {
			graph.get(sourceVertex).put(destVertex, newWeight);

		} else{
			
			graph.get(sourceVertex).remove(destVertex);
		}
		

		return true;
	}

	
	public Collection<V> neighborsOfVertex(V vertexData) {
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}

		
		if (!graph.containsKey(vertexData)) {
			return null;
		} else {
			
			Set<V> neighbors = new HashSet<V>();

			
			neighbors = graph.get(vertexData).keySet();

			return neighbors; 
		}
		
	}

	public DirGraph<V> divideGraph(Collection<V> verticesOfNewGraph) {


		DirGraph<V> newGraph = new DirGraph<V>();

		for (V vertex : verticesOfNewGraph) {

			if(graph.containsKey(vertex)) {
				newGraph.createVertex(vertex);
			}

				for (V val : neighborsOfVertex(vertex)) {

					if (verticesOfNewGraph.contains(val)) {
						newGraph.createEdge
						(vertex, val, graph.get(vertex).get(val));
					}
				}
			
			deleteVertexWithEdges(vertex);
		}


		return newGraph; 
	}


}
