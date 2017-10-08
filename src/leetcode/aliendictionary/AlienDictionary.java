package leetcode.aliendictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * There is a new alien language which uses the latin alphabet. However, the
 * order among letters are unknown to you. You receive a list of non-empty words
 * from the dictionary, where words are sorted lexicographically by the rules of
 * this new language. Derive the order of letters in this language.
 * 
 * Example 1: Given the following words in dictionary,
 * 
 * <pre>
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".
 * </pre>
 * 
 * Example 2: Given the following words in dictionary,
 * 
 * <pre>
[
  "z",
  "x"
]
The correct order is: "zx".
 * </pre>
 * 
 * Example 3: Given the following words in dictionary,
 * 
 * <pre>
[
  "z",
  "x",
  "z"
]
 * </pre>
 * 
 * The order is invalid, so return "".
 * 
 * Note: You may assume all letters are in lowercase. You may assume that if a
 * is a prefix of b, then a must appear before b in the given dictionary. If the
 * order is invalid, return an empty string. There may be multiple valid order
 * of letters, return any one of them is fine.
 * 
 * @author dwaraknathbs
 *
 */
public class AlienDictionary {

	static class Vertex {
		char ch;
		List<Vertex> neighbours = new ArrayList<>();

		public Vertex(char ch) {
			this.ch = ch;
		}

	}

	public String alienOrder(String[] words) {
		HashMap<Character, Vertex> graph = buildGraph(words);
		HashSet<Character> visited = new HashSet<>();
		Stack<Character> order = new Stack<>();
		HashSet<Character> set = new HashSet<>();
		try {
			for (java.util.Map.Entry<Character, Vertex> entry : graph.entrySet()) {
				if (!visited.contains(entry.getKey())) {
					traverse(entry.getValue(), visited, order, set);
				}
			}
		} catch (Exception e) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		while (!order.isEmpty()) {
			sb.append(order.pop());
		}
		return sb.toString();
	}

	public HashMap<Character, Vertex> buildGraph(String[] words) {
		HashMap<Character, Vertex> vertexMap = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				if (!vertexMap.containsKey(words[i].charAt(j))) {
					Vertex v = new Vertex(words[i].charAt(j));
					vertexMap.put(words[i].charAt(j), v);
				}
			}

		}

		for (int i = 1; i < words.length; i++) {
			String first = words[i - 1];
			String second = words[i];
			int n = Math.min(first.length(), second.length());
			for (int j = 0; j < n; j++) {
				if (first.charAt(j) != second.charAt(j)) {

					vertexMap.get(first.charAt(j)).neighbours.add(vertexMap.get(second.charAt(j)));
					break;

				}
			}
		}
		return vertexMap;
	}

	public void traverse(Vertex v, HashSet<Character> visited, Stack<Character> order, HashSet<Character> set)
			throws Exception {

		if (set.contains(v.ch)) {
			throw new Exception("Graph is cyclic");
		}
		if (visited.contains(v.ch)) {
			return;
		}
		visited.add(v.ch);
		set.add(v.ch);

		for (Vertex neighbour : v.neighbours) {
			traverse(neighbour, visited, order, set);

		}
		set.remove(v.ch);
		order.push(v.ch);
	}

}
