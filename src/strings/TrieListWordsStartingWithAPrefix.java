package strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * given:
 * 
 * prefix: String set of words: String[] Find all the words starting with the
 * prefix.
 * 
 * As the API will be called multiple times , preprocessing is allowed
 * 
 * 
 * 
 * 
 * prefix: al
 * 
 * set of words: [alaska, alabama, california, ...]
 * 
 * -> [alaska, alabama]
 * 
 * 
 *
 * @author dwaraknathbs
 *
 */
public class TrieListWordsStartingWithAPrefix {

	static Trie node = new Trie();

	static class Trie {
		/**
		 * List of word indices if duplicates are allowed . Else just the index
		 * would suffice
		 */
		List<Integer> wordIndex = new ArrayList<>();
		Trie[] children = new Trie[26];// lower case alphabets

		int getCharacterIndex(char ch) {
			return ch - 'a';
		}

		Trie addNode(char ch) {
			if (children[getCharacterIndex(ch)] == null)
				children[getCharacterIndex(ch)] = new Trie();

			return children[getCharacterIndex(ch)];
		}

		/**
		 * Given a trie node, walks the trie for all words from the prefix
		 * 
		 * @param node
		 * @param result
		 */
		void walkTrie(Trie node, List<Integer> result) {
			if (!node.wordIndex.isEmpty())
				result.addAll(node.wordIndex);
			Trie[] children = node.children;
			for (int i = 0; i < children.length; i++) {
				if (children[i] != null) {
					walkTrie(children[i], result);
				}
			}
		}

	}

	public static void main(String[] args) {

		String[] dictionary = new String[] { "alaska", "alabama", "california", "cat", "cat" };
		ingest(dictionary);

		List<String> result = search("cam", dictionary);

		result.forEach(System.out::println);
	}

	static void ingest(String[] dictionary) {

		for (int wordIndex = 0; wordIndex < dictionary.length; wordIndex++) {
			Trie temp = node;

			String word = dictionary[wordIndex];
			for (int i = 0; i < word.length(); i++) {
				Trie child = temp.addNode(word.charAt(i));
				temp = child;
			}
			temp.wordIndex.add(wordIndex);
		}
	}

	public static List<String> search(String prefix, String[] dictionary) {

		List<String> result = new ArrayList<>();
		Trie temp = node;
		int i = 0;
		while (i < prefix.length()) {

			temp = temp.children[temp.getCharacterIndex(prefix.charAt(i++))];
			if (temp == null)
				return result;

		}

		List<Integer> wordIndices = new ArrayList<>();
		temp.walkTrie(temp, wordIndices);

		return wordIndices.stream().map(e -> dictionary[e]).collect(Collectors.toList());

	}
}
