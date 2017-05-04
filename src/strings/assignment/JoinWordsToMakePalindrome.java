
package strings.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Join words to make a palindrome [This uses technique similar to the longest
 * palindrome problem we did in the class]
 * 
 * Given a list of words, is there any pair of words, that can be joined (in any
 * order) to form a palindrome?
 * 
 * Example 1:- Consider a list {bat, tab, cat}. Then bat and tab can be joined
 * together to form a palindrome.
 * 
 * Example 2 :- {ab, deedba} can be joined to form a palindrome.
 * 
 * Example 3 :- {'ant', 'cat', 'dog'}: No two words here can be joined to form a
 * palindrome. There can be multiple pairs, just return the first one if found.
 * About expected solutions: Given n = number of words and k is length of the
 * longest word,
 * 
 * O(N.K^2) solution for this problem is relatively straightforward to come by,
 * using Maps or Tries. That may suffice for most interviews. e.g.
 * https://discuss.leetcode.com/topic/40657/150-ms-45-lines-java-solution or
 * https://discuss.leetcode.com/topic/39585/o-n-k-2-java-solution-with-trie-
 * structure-n-total-number-of-words-k-average-length-of-each-word In order to
 * 
 * 
 * increase the average case efficiency, see if you can apply the Mancher
 * technique we learnt in the class. That can bring it down to O(N.K):
 * https://www.quora.com/Given-a-list-of-words-can-two-words-be-joined-together-
 * to-form-a-palindrome
 * 
 * @author dwaraknathbs
 */
public class JoinWordsToMakePalindrome {

	public static void main(String[] args) {
		String[] wordsWithNoPalindrome = { "ant", "cat", "dog" };

		String[] wordWithPalindrome = { "bat", "tab", "cat", "mad", "am" };

		// List<List<String>> palindromePairs =
		// getPalindromePairsSlow(wordWithPalindrome);
		List<List<String>> palindromePairs = getPalindromePairsFast(wordWithPalindrome);

		palindromePairs.forEach(list -> list.forEach(System.out::println));

	}

	/**
	 * Brute force solution, concatenate each pair of words and check if it is a
	 * palindrome
	 * 
	 * @param words
	 * @return
	 */
	private static List<List<String>> getPalindromePairsSlow(String[] words) {

		List<List<String>> result = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i == j)
					continue;

				String word = words[i].concat(words[j]);
				if (isPalindrome(word)) {
					List<String> pair = getPair(words[i], words[j]);
					result.add(pair);
				}
			}
		}
		return result;
	}

	private static List<String> getPair(String word1, String word2) {
		List<String> pair = new ArrayList<>();
		pair.add(word1);
		pair.add(word2);
		return pair;
	}

	/**
	 * O(nk^2) solution. Add each word and its index to a map. For each word,
	 * keep splitting the word at different indices. If first part is a
	 * palindrome , check if reverse of the second part is in the map. If so
	 * there exists a pair. Do the same for the other way around. Check if
	 * second part is a palindrome, if so check if its reverse is in the map
	 * 
	 * @param words
	 * @return
	 */
	private static List<List<String>> getPalindromePairsFast(String[] words) {
		List<List<String>> result = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < words.length; i++) {
			map.put(words[i], i);
		}

		for (int i = 0; i < words.length; i++) {

			String word = words[i];
			for (int j = 0; j < word.length(); j++) {
				String firstPart = word.substring(0, j);
				String secondPart = word.substring(j);

				if (isPalindrome(firstPart)) {
					String reverse = (new StringBuilder(secondPart).reverse()).toString();
					if (map.containsKey(reverse)) {
						result.add(getPair(word, words[map.get(reverse.toString())]));
						break;
					}
				}
				if (isPalindrome(secondPart)) {
					String reverse = (new StringBuilder(firstPart).reverse()).toString();
					if (map.containsKey(reverse)) {
						result.add(getPair(word, words[map.get(reverse)]));
						break;
					}
				}
			}

		}
		return result;
	}

	private static boolean isPalindrome(String word) {
		int i = 0;
		int j = word.length() - 1;
		while (i <= j) {
			if (word.charAt(i) != word.charAt(j))
				return false;
			i++;
			j--;
		}
		return true;
	}

}
