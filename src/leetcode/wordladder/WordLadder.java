package leetcode.wordladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * 127. Word Ladder
 * 
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * Only one letter can be changed at a time. Each transformed word must exist in
 * the word list. Note that beginWord is not a transformed word. For example,
 * 
 * Given: beginWord = "hit" endWord = "cog" wordList =
 * ["hot","dot","dog","lot","log","cog"] As one shortest transformation is "hit"
 * -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * 
 * Note: Return 0 if there is no such transformation sequence. All words have
 * the same length. All words contain only lowercase alphabetic characters. You
 * may assume no duplicates in the word list. You may assume beginWord and
 * endWord are non-empty and are not the same.
 * 
 * @author dwaraknathbs
 *
 */
public class WordLadder {

	public static void main(String[] args) {
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("hog");
		wordList.add("fog");

		System.out.println(ladderLength("hot", "fog", wordList));

	}

	static class Pair {
		String word;
		int level;

		public Pair(String s, int l) {
			this.word = s;
			this.level = l;
		}
	}

	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

		Set<String> words = new HashSet<>();
		for (String s : wordList)
			words.add(s);
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(beginWord, 1));

		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			String curr = p.word;

			int level = p.level;

			if (curr.equals(endWord))
				return level;

			char[] current = curr.toCharArray();
			for (int i = 0; i < current.length; i++) {
				char tmp = current[i];
				for (char c = 'a'; c <= 'z'; c++) {
					current[i] = c;
					String next = new String(current);
					if (words.contains(next)) {
						queue.add(new Pair(next, level + 1));
						words.remove(next);
					}
				}
				current[i] = tmp;
			}

		}

		return 0;

	}

}
