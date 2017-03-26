package graphs.assignment.detectcycle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Detecting Cycle in a graph Given a directed graph, check whether there is a
 * cycle in it.
 * 
 * There can be multiple cycles We don't need to print all the cycles. Just
 * return a boolean true/false if there is/is-not at least one cycle
 * respectively
 * 
 * 
 * Solution: http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 * 
 * Suggested time: 45 minutes max.
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class DetectCycleInGraph {

	static class Graph {
		List<Vertex> vertices = new ArrayList<>();

		boolean isCyclic(Graph g) {
			HashSet<Vertex> visited = new HashSet<>();
			for (Vertex v : g.vertices) {
				if (!visited.contains(v)) {
					HashSet<Vertex> backRef = new HashSet<>();
					if (isCyclic(v, backRef, visited)) {
						return true;
					}
				}
			}
			return false;

		}

		/**
		 * Basic idea is to enhance the DFS to store the path in a hashset. If
		 * the node is present in the hashset( which means a backref exists and
		 * hence the graph cyclic. False otherwise. It is important to remove
		 * the node from the hashset after processing all its neighbours ( i.e
		 * the difference between the hashset and the visisted hashset)
		 * 
		 * @param v
		 * @param backRef
		 * @param visited
		 * @return
		 */
		private boolean isCyclic(Vertex v, HashSet<Vertex> backRef, HashSet<Vertex> visited) {
			if (visited.contains(v) && backRef.contains(v))
				return true;
			if (!visited.contains(v)) {

				visited.add(v);
				if (backRef.contains(v)) {
					return true;
				}
				backRef.add(v);
				for (Vertex neighbour : v.neighbours) {
					if (isCyclic(neighbour, backRef, visited)) {
						return true;
					}
				}
				backRef.remove(v);
			}
			return false;
		}
	}

	static class Vertex {
		int val;
		List<Vertex> neighbours = new ArrayList<>();

		public Vertex(int val) {
			super();
			this.val = val;
		}

	}

	public static void main(String[] args) {
		Graph g = new Graph();
		Vertex v0 = new Vertex(0);
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);

		v0.neighbours.add(v1);
		v0.neighbours.add(v2);

		v1.neighbours.add(v2);

		v2.neighbours.add(v1);
		v2.neighbours.add(v3);

		v3.neighbours.add(v3);

		g.vertices.add(v0);
		System.out.println(g.isCyclic(g));

		// Graph without cycle

		Graph g1 = new Graph();

		Vertex A = new Vertex(1);
		Vertex B = new Vertex(2);
		Vertex C = new Vertex(3);

		A.neighbours.add(B);
		A.neighbours.add(C);
		B.neighbours.add(C);

		System.out.println(g1.isCyclic(g1));

	}

}
