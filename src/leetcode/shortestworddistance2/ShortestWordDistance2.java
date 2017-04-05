package leetcode.shortestworddistance2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 244. Shortest Word Distance II
 * 
 * This is a follow up of Shortest Word Distance. The only difference is now you
 * are given the list of words and your method will be called repeatedly many
 * times with different parameters. How would you optimize it?
 * 
 * Design a class which receives a list of words in the constructor, and
 * implements a method that takes two words word1 and word2 and return the
 * shortest distance between these two words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding",
 * "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes",
 * word2 = "coding", return 1.
 * 
 * Note: You may assume that word1 does not equal to word2, and word1 and word2
 * are both in the list.
 * 
 * @author dwaraknathbs
 *
 */
public class ShortestWordDistance2 {

	public static void main(String[] args) {
		String[] words = { "practice", "makes", "perfect", "coding", "makes" };
		ShortestWordDistance2 distance = new ShortestWordDistance2(words);
		System.out.println(distance.shortest("coding", "practice"));
		System.out.println(distance.shortest("makes", "coding"));

	}

	HashMap<String, List<Integer>> wordMap = new HashMap<>();

	public ShortestWordDistance2(String[] words) {
		for (int i = 0; i < words.length; i++) {
			if (wordMap.containsKey(words[i])) {
				wordMap.get(words[i]).add(i);
			} else {
				ArrayList<Integer> numList = new ArrayList();
				numList.add(i);
				wordMap.put(words[i], numList);
			}
		}
	}

	public int shortest(String word1, String word2) {
		List<Integer> firstIndex = wordMap.get(word1);
		List<Integer> secondIndex = wordMap.get(word2);
		int index1 = -1;
		int index2 = -1;

		int min = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		/**
		 * When we iterate the two lists, iterating both the lists can be O(n2).
		 * Given that inherently both the lists are sorted. We could increase
		 * one at a time based on which is greater. If index 1 is greater
		 * increase j so that index2 comes closer to index1 and vice versa
		 */
		for (; i < firstIndex.size() && j < secondIndex.size();) {
			index1 = firstIndex.get(i);
			index2 = secondIndex.get(j);

			if (index1 > index2) {
				min = Math.min(min, index1 - index2);
				j++;
			} else {
				min = Math.min(min, index2 - index1);
				i++;
			}
		}

		while (i < firstIndex.size()) {
			index1 = firstIndex.get(i);

			min = Math.min(min, Math.abs(index2 - index1));
			i++;
		}

		while (j < secondIndex.size()) {

			index2 = secondIndex.get(j);
			min = Math.min(min, Math.abs(index2 - index1));
			j++;
		}

		return min;
	}

}
