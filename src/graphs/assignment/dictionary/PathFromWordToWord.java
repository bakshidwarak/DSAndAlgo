package graphs.assignment.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Convert string from a to b, using a dictionary of words
 * 
 * This is a problem, that can be modeled as a Graph problem. It's a simplified
 * version of the famous Edit Distance problem (which is DP).
 * 
 * You have a dictionary of words and two strings a and b.
 * 
 * How can one convert a to b by changing only one character at a time and
 * making sure that all the intermediate words are in the dictionary?
 * 
 * Example:
 * 
 * dictionary: {"cat", "bat", "hat", "bad", "had"} a = "bat" b = "had" 
 * 
 * 
 * solution:
 * 
 * "bat" -> "bad" -> "had"
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class PathFromWordToWord {
	static List<String> wordsDictionary = new ArrayList<>();
	static Map<String, WordGraph> wordMap;

	public static void main(String[] args) {
		String[] words = { "cat", "bat", "hat", "had", "bad", "cot", "dot" };
		wordsDictionary.addAll(Arrays.asList(words));
		wordMap = createGraph(wordsDictionary);
		System.out.println(wordMap.size());

		printPath("bat", "cat");
	}

	private static void printPath(String str1, String str2) {
		WordGraph firstWord = wordMap.get(str1);
		WordGraph secondWord = wordMap.get(str2);

		printPath(firstWord, secondWord);

	}

	private static void printPath(WordGraph firstWord, WordGraph secondWord) {
		HashSet<WordGraph> visited = new HashSet<>();
		HashMap<WordGraph, WordGraph> backRef = new HashMap<>();
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(firstWord, null));
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			if (visited.contains(p.vertex))
				continue;
			backRef.put(p.vertex, p.backRef);
			visited.add(p.vertex);
			if (p.vertex.equals(secondWord))
				break;
			for (WordGraph neighbour : p.vertex.neighbors) {
				queue.add(new Pair(neighbour, p.vertex));
			}
		}

		WordGraph curr = secondWord;
		Stack<WordGraph> stack = new Stack<>();
		while (curr != null) {
			stack.push(curr);
			curr = backRef.get(curr);
		}

		while (!stack.isEmpty()) {

			System.out.print(stack.pop().word + (stack.size() != 0 ? "->" : ""));
		}

	}

	private static Map<String, WordGraph> createGraph(List<String> wordsDictionary2) {
		Map<String, WordGraph> wordMap = new HashMap<>();
		for (String str : wordsDictionary2) {
			WordGraph g = wordMap.containsKey(str) ? wordMap.get(str) : new WordGraph(str);

			g = new WordGraph(str);
			for (String another : wordsDictionary2) {
				if (offByOne(str, another)) {
					WordGraph g1 = wordMap.containsKey(another) ? wordMap.get(another) : new WordGraph(another);

					g.neighbors.add(g1);
					g1.neighbors.add(g);
					wordMap.put(another, g1);
				}
			}
		}
		return wordMap;
	}

	private static boolean offByOne(String one, String two) {
		char[] first = one.toCharArray();
		char[] second = two.toCharArray();
		int numberOfDifferences = 0;
		for (int i = 0; i < first.length; i++) {
			if (first[i] == second[i])
				continue;
			numberOfDifferences++;
		}

		if (numberOfDifferences == 1)
			return true;
		return false;
	}

}

class WordGraph {
	String word;
	List<WordGraph> neighbors = new ArrayList<>();

	public WordGraph(String word) {
		super();
		this.word = word;
	}

}

class Pair {
	WordGraph vertex;
	WordGraph backRef;

	public Pair(WordGraph vertex, WordGraph backRef) {
		super();
		this.vertex = vertex;
		this.backRef = backRef;

	}

}
