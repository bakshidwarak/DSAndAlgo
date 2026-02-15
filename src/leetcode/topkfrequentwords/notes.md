# Top K Frequent Words (LeetCode 692)

## Problem Statement
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

## Examples
```
Example 1:
Input: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
Note: "i" comes before "love" due to lower alphabetical order.

Example 2:
Input: words = ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation:
  "the" - 4 times
  "is" - 3 times
  "sunny" - 2 times
  "day" - 1 time

Example 3:
Input: words = ["a", "aa", "aaa"], k = 1
Output: ["aaa"]
```

## Key Insights
1. We need to count word frequencies using a HashMap
2. Use a min heap to maintain top k elements efficiently
3. Min heap size = k, so we keep only k most frequent words
4. When heap size exceeds k, remove the element with minimum frequency
5. Custom comparator: Compare by frequency (ascending), then by lexicographic order (descending)
6. For same frequency, alphabetically smaller word should have higher priority in heap

## Algorithm Steps

### Approach: HashMap + Min Heap
1. Create a HashMap to count word frequencies: word -> count
2. Create a min heap with custom comparator:
   - If frequencies equal, compare strings lexicographically (reverse order for heap)
   - Otherwise, compare by frequency (ascending for min heap)
3. Iterate through frequency map:
   - Add each entry to heap
   - If heap size exceeds k, remove the smallest element
4. Extract elements from heap and add to result in reverse order
5. This maintains the k most frequent words with proper ordering

## Complexity Analysis
- **Time Complexity:** O(n log k) - n word additions, each log k heap operation
- **Space Complexity:** O(n) - For HashMap storing all words and their counts

## ASCII Visualization

```
words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2

Step 1: Count frequencies
  "i": 2
  "love": 2
  "leetcode": 1
  "coding": 1

Step 2: Add to min heap
  Add ("i", 2): heap = [("i", 2)]
  Add ("love", 2): heap = [("i", 2), ("love", 2)]
    Comparator: same frequency, compare alphabetically
    "i" < "love" lexicographically, but we want "love" at root
    Heap comparison is reversed: heap = [("love", 2), ("i", 2)]
  Add ("leetcode", 1): heap = [("love", 2), ("i", 2), ("leetcode", 1)]
    size = 3 > k = 2, remove min
    Min is ("leetcode", 1) - freq=1, remove
    heap = [("love", 2), ("i", 2)]
  Add ("coding", 1): heap = [("love", 2), ("i", 2), ("coding", 1)]
    size = 3 > k = 2, remove min
    Min is ("coding", 1) - freq=1, remove
    heap = [("love", 2), ("i", 2)]

Step 3: Extract from heap
  poll(): get ("i", 2)
  poll(): get ("love", 2)

  Add to front: result = ["love", "i"]
  Wait, we want ["i", "love"]

  Actually, we add to index 0 each time (add(0, ...)):
  After first poll ("i", 2): result = ["i"]
  After second poll ("love", 2): result = ["love", "i"]

  But output should be ["i", "love"]
  So we add to front each time, which reverses order

Final: ["i", "love"]

---

Frequency counting example:
["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"]

Counts:
  "the": 4
  "is": 3
  "sunny": 2
  "day": 1

Heap process (k=4):
  All fit since we have 4 unique words
  Min heap with comparator = freq ASC, then word DESC

  Heap = [
    ("day", 1),
    ("sunny", 2),
    ("is", 3),
    ("the", 4)
  ]

  Extract and add to front:
  result = ["the", "is", "sunny", "day"]
```

## Code Walkthrough

```java
public List<String> topKFrequent(String[] words, int k) {
    List<String> result = new ArrayList<>();

    // Count frequencies
    HashMap<String, Integer> map = new HashMap<>();
    for (String s : words) {
        map.putIfAbsent(s, 0);
        int c = map.get(s);
        map.put(s, c + 1);
    }

    // Create min heap with custom comparator
    // For same frequency: alphabetically smaller should come first in result
    // So in min heap, larger string should have higher priority
    PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
        (e1, e2) -> e1.getValue() == e2.getValue()
            ? e2.getKey().compareTo(e1.getKey())  // Same freq: reverse alphabetical
            : e1.getValue().compareTo(e2.getValue())  // Different freq: ascending
    );

    // Maintain heap of size k
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        heap.offer(entry);
        if (heap.size() == k + 1) {
            heap.poll();  // Remove smallest
        }
    }

    // Extract from heap and add to front
    while (!heap.isEmpty()) {
        result.add(0, heap.poll().getKey());
    }

    return result;
}
```

## Edge Cases
1. k = 1: Return the single most frequent word
2. All words same frequency: Return first k words alphabetically
3. All different words: Return k words with highest frequency
4. Single word: Return that word
5. k = number of unique words: Return all words sorted by frequency then alphabetically

## Comparator Explanation

The custom comparator determines order in min heap:
- `e1.getValue() == e2.getValue()`: If frequencies equal
  - `e2.getKey().compareTo(e1.getKey())`: Return reverse comparison
  - This ensures alphabetically smaller word is at heap root

- `e1.getValue().compareTo(e2.getValue())`: Different frequencies
  - Compare by count ascending (min heap keeps smallest frequency at root)

## Related Problems
- LeetCode 347: Top K Frequent Elements
- LeetCode 215: Kth Largest Element in an Array
- LeetCode 973: K Closest Points to Origin
- LeetCode 703: Kth Largest Element in a Stream
## Tags
- Hash Table
- Heap
- Priority Queue
- Sorting
- Top K Elements
