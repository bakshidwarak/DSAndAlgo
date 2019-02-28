package leetcode.topkfrequentwords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 692. Top K Frequent Words Medium
 * 
 * 630
 * 
 * 59
 * 
 * Favorite
 * 
 * Share Given a non-empty list of words, return the k most frequent elements.
 * 
 * Your answer should be sorted by frequency from highest to lowest. If two
 * words have the same frequency, then the word with the lower alphabetical
 * order comes first.
 * 
 * Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"] Explanation: "i" and "love" are the two most frequent
 * words. Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2: Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny",
 * "is", "is"], k = 4 Output: ["the", "is", "sunny", "day"] Explanation: "the",
 * "is", "sunny" and "day" are the four most frequent words, with the number of
 * occurrence being 4, 3, 2 and 1 respectively. Note: You may assume k is always
 * valid, 1 ≤ k ≤ number of unique elements. Input words contain only lowercase
 * letters. Follow up: Try to solve it in O(n log k) time and O(n) extra space.
 * 
 * @author dwaraknathbs
 *
 */
public class TopKFrequentWords {

	public List<String> topKFrequent(String[] words, int k) {
		List<String> result = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (String s : words) {
			map.putIfAbsent(s, 0);
			int c = map.get(s);
			map.put(s, c + 1);
		}

		PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((e1, e2) -> e1.getValue() == e2.getValue()
				? e2.getKey().compareTo(e1.getKey()) : e1.getValue().compareTo(e2.getValue()))

		;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {

			heap.offer(entry);
			if (heap.size() == k + 1) {
				heap.poll();
			}

		}

		while (!heap.isEmpty()) {
			result.add(0, heap.poll().getKey());
		}

		return result;
	}

}
