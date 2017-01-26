package graphs.clonegraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import graphs.BasicGraphOperations;

/**
 * Given a graph, write a program to clone the graph
 * @author dwaraknathbs
 *
 */
public class CloneAGraph {

	void dfs(Vertex v) {
		HashSet<Vertex> visited = new HashSet<>();
		dfTraversal(v, visited);
	}

	/**
	 * Depth first traversal
	 * 
	 * @param v
	 * @param visited
	 */
	private void dfTraversal(Vertex v, HashSet<Vertex> visited) {

		if (visited.contains(v))// Check if the node is already visited
			return;

		System.out.print(v.val + " ");
		visited.add(v);
		for (Vertex neighbour : v.neighbours) {
			dfTraversal(neighbour, visited);// Recursivley process each of the
											// neighbors
		}

	}

	/**
	 * Breadth first traversal
	 * 
	 * @param v
	 */
	void bfTraversal(Vertex v) {
		Queue<Vertex> queue = new LinkedList<>();
		HashSet<Vertex> visited = new HashSet<>();
		queue.add(v);
		while (!queue.isEmpty()) {
			Vertex current = queue.remove();
			if (visited.contains(current))
				continue;
			System.out.print(current.val + " ");
			visited.add(current);
			for (Vertex neighbor : current.neighbours) {
				queue.add(neighbor);
			}

		}

	}

	public static void main(String[] args) {
		Vertex v1 = new Vertex(1);

		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);

		v1.neighbours.add(v2);
		v1.neighbours.add(v5);
		v1.neighbours.add(v3);

		v2.neighbours.add(v4);
		v4.neighbours.add(v5);
		v3.neighbours.add(v4);
		CloneAGraph graph= new CloneAGraph();
		
		graph.dfs(v1);
		System.out.println(v1.toString());
		System.out.println(" New Node");
		
		
		Vertex newV1=graph.clone(v1);
		System.out.println(newV1.toString());
		graph.dfs(newV1);

	}

	/**
	 * Determine and print all the paths between start and end Vertices
	 * 
	 * @param start
	 * @param end
	 */
	void determineThePath(Vertex start, Vertex end) {
		ArrayList<Vertex> path = new ArrayList<>();
		HashSet<Vertex> visited = new HashSet<>();
		determinePaths(start, end, path, visited);
	}

	private void determinePaths(Vertex start, Vertex end, ArrayList<Vertex> path, HashSet<Vertex> visited) {

		if (path.contains(start))
			return;
		if (start == end) {

			print(path);

		}
		path.add(start);
		for (Vertex neigh : start.neighbours) {
			determinePaths(neigh, end, path, visited);
		}
		path.remove(path.size() - 1);

	}

	private void print(ArrayList<Vertex> path) {
		System.out.println("Paths");
		path.stream().map(v -> v.val).forEach(System.out::print);

	}

	/**
	 * Breadth First Traversal always gives the shortes path
	 * 
	 * @param start
	 * @param end
	 */
	void shortestPath(Vertex start, Vertex end) {
		System.out.println("Shortest Path");
		Queue<Pair> queue = new LinkedList<>();
		HashSet<Vertex> visited = new HashSet<>();
		HashMap<Vertex, Vertex> backRef = new HashMap<>();
		queue.add(new Pair(start, null));

		while (!queue.isEmpty()) {
			Pair p = queue.remove();

			Vertex current = p.v1;
			if (visited.contains(current))
				continue;
			backRef.put(current, p.backRef);// BackRef maintains the vertex that
											// referenced the current vertex
			visited.add(current);
			if (current == end)
				break;
			for (Vertex neighBour : current.neighbours) {
				queue.add(new Pair(neighBour, current));
			}
		}

		Vertex current = end;
		StringBuilder pathBuilder = new StringBuilder();
		while (current != start) {
			pathBuilder.append(current.val + " ");
			Vertex v = backRef.get(current);
			current = v;
		}

		pathBuilder.append(start.val);
		System.out.println(pathBuilder.reverse().toString());

	}
	
	
	Vertex clone(Vertex original){
		HashMap<Vertex,Vertex> originalToCloneMap= new HashMap<>();
		
		Vertex newNode=dfsclone(original,originalToCloneMap);
		return newNode;
	}

	private Vertex dfsclone(Vertex original, HashMap<Vertex, Vertex> originalToCloneMap) {
		if(originalToCloneMap.containsKey(original))
			return originalToCloneMap.get(original);
		
		Vertex newNode= new Vertex(original.val);
		originalToCloneMap.put(original, newNode);
		for(Vertex neighbor:original.neighbours){
			newNode.neighbours.add(dfsclone(neighbor, originalToCloneMap));
		}
		return newNode;
	}


	
	

}

class Vertex {
	public int val;
	List<Vertex> neighbours = new ArrayList<>();

	public Vertex(int val) {
		super();
		this.val = val;
	}

}

class Pair {
	Vertex v1;
	Vertex backRef;

	public Pair(Vertex v1, Vertex backRef) {
		super();
		this.v1 = v1;
		this.backRef = backRef;
	}

}
