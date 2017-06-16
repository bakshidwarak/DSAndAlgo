package leetcode.worddictionary;

/**
 * 211. Add and Search Word - Data structure design Design a data structure that
 * supports the following two operations:
 * 
 * void addWord(word) bool search(word) search(word) can search a literal word
 * or a regular expression string containing only letters a-z or .. A . means it
 * can represent any one letter.
 * 
 * For example:
 * 
 * addWord("bad") addWord("dad") addWord("mad") search("pad") -> false
 * search("bad") -> true search(".ad") -> true search("b..") -> true Note: You
 * may assume that all words are consist of lowercase letters a-z.
 * 
 * click to show hint.
 * 
 * You should be familiar with how a Trie works. If not, please work on this
 * problem: Implement Trie (Prefix Tree) first.
 * 
 * @author dwaraknathbs
 *
 */
public class WordDictionary {

	class Trie {
		boolean isWord;
		Trie[] children = new Trie[26];

	}

	Trie words = new Trie();

	/** Initialize your data structure here. */
	public WordDictionary() {

	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {

		char[] wordChars = word.toCharArray();
		Trie temp = words;
		for (int i = 0; i < wordChars.length; i++) {
			Trie node = temp.children[wordChars[i] - 'a'];
			if (node == null) {
				temp.children[wordChars[i] - 'a'] = new Trie();
			}
			temp = temp.children[wordChars[i] - 'a'];
		}
		temp.isWord = true;

	}

	/**
	 * Returns if the word is in the data structure. A word could contain the
	 * dot character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		return searchHelper(word, 0, words);
	}

	public boolean searchHelper(String word, int index, Trie root) {
		if (index == word.length()) {
			return root.isWord;
		}

		if (word.charAt(index) == '.') {
			for (int i = 0; i < root.children.length; i++) {
				if (root.children[i] != null && searchHelper(word, index + 1, root.children[i]))
					return true;

			}
		} else {
			if (root != null && root.children[word.charAt(index) - 'a'] != null) {
				return searchHelper(word, index + 1, root.children[word.charAt(index) - 'a']);
			}
		}
		return false;
	}
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary(); obj.addWord(word); boolean param_2
 * = obj.search(word);
 */