package graphs.assignment.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Topological Sort Given a sorted dictionary of an alien language, find order
 * of characters.
 * 
 * Example-1: Input: words[] = {"baa", "abcd", "abca", "cab", "cad"} Output:
 * Order of characters is 'b', 'd', 'a', 'c'
 * 
 * Note that words are sorted and in the given language. "baa" comes before
 * "abcd", therefore 'b' is before 'a' in output.
 * 
 * Example-2: Input: words[] = {"caa", "aaa", "aab"} Output: Order of characters
 * is 'c', 'a', 'b'
 * 
 * @author dwaraknathbs
 *
 */
public class TopologicalSort {
	
	static class Vertex {
		char c;
		List<Vertex> neighbours = new ArrayList<>();

		public Vertex(char c) {
			super();
			this.c = c;
		}

	}


	public static void main(String args[]) {

		String[] words = { "dcb","dca","dbc","bbc" };

		HashMap<Character, Vertex> charToVertexMap = new HashMap<>();

		buildGraph(words, charToVertexMap);
		
		System.out.println("Hello");
		
		topologicalSort(charToVertexMap);
	}

	private static void topologicalSort(HashMap<Character, Vertex> charToVertexMap) {
		Stack<Vertex> stack = new Stack<>();
		HashSet<Vertex> visited = new HashSet<>();
		for (Vertex v : charToVertexMap.values()) {
			if (visited.contains(v))
				continue;
			exhaust(v, visited, stack);
		}

		while (!stack.isEmpty()) {
			System.out.print(stack.pop().c + (stack.isEmpty() ? "" : "->"));
		}

	}

	private static void exhaust(Vertex v, HashSet<Vertex> visited, Stack<Vertex> stack) {
		if (visited.contains(v)) {
			System.out.println("Got a cycle");
			return;
		}
		visited.add(v);
		for (Vertex n : v.neighbours) {
			System.out.println(n.c);
			exhaust(n, visited, stack);
		}
		stack.push(v);

	}

	private static void buildGraph(String[] words, HashMap<Character, Vertex> charToVertexMap) {
		int maxLen = 0;
		for (String word : words) {
			char[] charArray = word.toCharArray();
			maxLen = Math.max(maxLen, word.length());
			for (char c : charArray) {
				if (!charToVertexMap.containsKey(c)) {
					Vertex v = new Vertex(c);
					charToVertexMap.put(c, v);
				}
			}
		}

		for (int k = 0; k < words.length - 1; k++) {
			String word1 = words[k];
			String word2 = words[k + 1];
			char[] word1charArray = word1.toCharArray();
			char[] word2charArray = word2.toCharArray();
			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
				if (word1charArray[j] != word2charArray[j]) {
					Vertex v1 = charToVertexMap.get(word1charArray[j]);
					Vertex nextCharVertex = charToVertexMap.get(word2charArray[j]);
					v1.neighbours.add(nextCharVertex);
					break;
				}
			}
		}

	}
}

