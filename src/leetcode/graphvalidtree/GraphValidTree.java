package leetcode.graphvalidtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 * 
 * For example:
 * 
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return
 * false.
 * 
 * Note: you can assume that no duplicate edges will appear in edges. Since all
 * edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 * 
 * @author dwaraknathbs
 *
 */
public class GraphValidTree {

	class Graph {
		int val;
		List<Graph> neigbours = new ArrayList<>();

		public Graph(int val) {
			this.val = val;
		}
	}

	public boolean validTree(int n, int[][] edges) {

		Map<Integer, Graph> g = constructGraph(n, edges);
		boolean[] visited = new boolean[n];
		Set<Integer> backEdge = new HashSet<>();

		/**
		 * Check if the graph has a cycle starting from first node
		 */
		if (!validTreeNode(g.get(0), visited, backEdge, -1)) {
			return false;
		}

		/**
		 * Ensure all the components are connected i.e all nodes are visited
		 * after traversing
		 */
		for (int i = 0; i < n; i++) {
			if (!visited[i])
				return false;
		}
		return true;

	}

	public boolean validTreeNode(Graph g, boolean[] visited, Set<Integer> backEdge, int parent) {

		visited[g.val] = true;

		if (backEdge.contains(g.val))
			return false;

		backEdge.add(g.val);
		for (Graph neighbour : g.neigbours) {
			/**
			 * Make sure you dont visit your parent again
			 */
			if (neighbour.val != parent) {
				if (!validTreeNode(neighbour, visited, backEdge, g.val)) {
					return false;
				}
			}
		}
		backEdge.remove(g.val);

		return true;
	}

	public Map<Integer, Graph> constructGraph(int n, int[][] edges) {

		Map<Integer, Graph> graphMap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			Graph g = new Graph(i);
			graphMap.put(i, g);
		}

		for (int i = 0; i < edges.length; i++) {
			graphMap.get(edges[i][0]).neigbours.add(graphMap.get(edges[i][1]));
			graphMap.get(edges[i][1]).neigbours.add(graphMap.get(edges[i][0]));
		}
		return graphMap;
	}
}