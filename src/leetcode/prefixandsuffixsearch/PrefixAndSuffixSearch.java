package leetcode.prefixandsuffixsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 745. Prefix and Suffix Search Hard
 * 
 * 173
 * 
 * 137
 * 
 * Favorite
 * 
 * Share Given many words, words[i] has weight i.
 * 
 * Design a class WordFilter that supports one function, WordFilter.f(String
 * prefix, String suffix). It will return the word with given prefix and suffix
 * with maximum weight. If no word exists, return -1.
 * 
 * Examples:
 * 
 * Input: WordFilter(["apple"]) WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 * 
 * 
 * Note:
 * 
 * words has length in range [1, 15000]. For each test case, up to words.length
 * queries WordFilter.f may be made. words[i] has length in range [1, 10].
 * prefix, suffix have lengths in range [0, 10]. words[i] and prefix, suffix
 * queries consist of lowercase letters only.
 * 
 * @author dwaraknathbs
 *
 */
public class PrefixAndSuffixSearch {
	Trie tree = new Trie();

	/**
	 * Basic idea is to create a trie with all the suffixes and prefixes
	 * separated by { for example if the word is test create a trie with {test,
	 * t{test,st{test,est{test.test{test
	 * 
	 * During the search construct suffix+{+prefix and look for all the
	 * instances. Maintain a list of indices in the Trie
	 * 
	 * @author dwaraknathbs
	 *
	 */
	static class Trie {
		List<Integer> set = new ArrayList<>();
		Trie[] children = new Trie[27];

		public void addWord(String word, int index) {
			Trie curr = this;
			char[] wordArray = word.toCharArray();
			for (char ch : wordArray) {
				if (curr.children[ch - 'a'] == null) {
					curr.children[ch - 'a'] = new Trie();
				}
				curr.children[ch - 'a'].set.add(index);
				curr = curr.children[ch - 'a'];
			}
		}

		public List<Integer> matches(String pattern) {
			Trie curr = this;
			for (char ch : pattern.toCharArray()) {
				if (curr.children[ch - 'a'] == null) {
					return new ArrayList<>();
				}
				curr = curr.children[ch - 'a'];
			}
			return curr.set;
		}

	}

	public PrefixAndSuffixSearch(String[] words) {
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			for (int s = word.length(); s >= 0; s--) {
				String curr = word.substring(s);
				tree.addWord(curr + "{" + word, i);
			}

		}

	}

	public int f(String prefix, String suffix) {
		List<Integer> psearch = tree.matches(suffix + "{" + prefix);

		int val = psearch.size() == 0 ? -1 : psearch.get(psearch.size() - 1);

		return val;

	}
}
