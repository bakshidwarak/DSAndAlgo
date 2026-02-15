# 244. Shortest Word Distance II

## Problem Statement
This is a follow-up to Shortest Word Distance. The difference is that now you are given a list of words and your method will be called repeatedly many times with different parameters.

Design a class that:
1. Takes a list of words in the constructor
2. Implements shortest(word1, word2) method
3. Returns the shortest distance between word1 and word2

Optimization: Preprocess words once in constructor for multiple queries.

## Examples

### Example 1
```
words = ["practice", "makes", "perfect", "coding", "makes"]

solution.shortest("coding", "practice") -> 3
solution.shortest("makes", "coding") -> 1
```

## Key Insights

1. **Preprocessing**: Store indices of each word in a HashMap during construction
2. **Two-pointer on sorted lists**: Use two pointers to traverse the index lists
3. **Optimal solution for multiple queries**: O(1) space at query time
4. **Avoids redundant searches**: No need to search entire array each query

## Algorithm Steps

### Constructor
1. Create HashMap<String, List<Integer>>
2. Iterate through words array
3. For each word, add its index to the corresponding list
4. Each list of indices is automatically sorted

### shortest(word1, word2)
1. Get index lists for word1 and word2
2. Initialize two pointers (i, j) at start of each list
3. Use two-pointer technique:
   - Calculate distance |list1[i] - list2[j]|
   - Update minimum
   - Move the pointer pointing to smaller index forward
4. Continue until one pointer reaches end
5. Return minimum distance

## Complexity Analysis

**Constructor:** O(n)
- Single pass through words array
- HashMap operations are O(1) average

**shortest() method:** O(m + k)
- m = length of index list for word1
- k = length of index list for word2
- At most m + k comparisons

**Overall:** Trades O(n) preprocessing for faster queries

## ASCII Visualization

```
words = ["practice", "makes", "perfect", "coding", "makes"]
Index:   0          1       2          3        4

After preprocessing:
map = {
  "practice": [0],
  "makes": [1, 4],
  "perfect": [2],
  "coding": [3]
}

Query: shortest("makes", "coding")

word1_indices = [1, 4]  (for "makes")
word2_indices = [3]     (for "coding")

Initialize: i=0, j=0, min=MAX
word1_indices[0]=1, word2_indices[0]=3

Iteration 1:
  i=0, j=0
  indices[i]=1, indices[j]=3
  distance = |1-3| = 2
  min = 2
  1 < 3? Move i: i=1

Iteration 2:
  i=1, j=0
  indices[i]=4, indices[j]=3
  distance = |4-3| = 1
  min = 1
  4 < 3? NO, Move j: j=1
  j >= list2.size()? YES, stop

Return: 1
```

## Code Walkthrough

```java
public class ShortestWordDistance2 {
    HashMap<String, List<Integer>> wordMap = new HashMap<>();

    // Constructor: Preprocess words and store indices
    public ShortestWordDistance2(String[] words) {
        for (int i = 0; i < words.length; i++) {
            // If word already exists, add to its list
            if (wordMap.containsKey(words[i])) {
                wordMap.get(words[i]).add(i);
            } else {
                // Create new list for this word
                ArrayList<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                wordMap.put(words[i], indexList);
            }
        }
    }

    // Query: Find shortest distance between two words
    public int shortest(String word1, String word2) {
        // Get sorted index lists for both words
        List<Integer> indices1 = wordMap.get(word1);
        List<Integer> indices2 = wordMap.get(word2);

        int min = Integer.MAX_VALUE;
        int i = 0;  // Pointer for word1 indices
        int j = 0;  // Pointer for word2 indices

        // Two-pointer approach
        // Since both lists are sorted, we can efficiently find minimum distance
        while (i < indices1.size() && j < indices2.size()) {
            int idx1 = indices1.get(i);
            int idx2 = indices2.get(j);

            // Calculate current distance
            min = Math.min(min, Math.abs(idx1 - idx2));

            // Move the pointer pointing to smaller index
            // This ensures we explore closer positions
            if (idx1 < idx2) {
                i++;
            } else {
                j++;
            }
        }

        // Process remaining elements of word1 if any
        while (i < indices1.size()) {
            int idx1 = indices1.get(i);
            int idx2 = indices2.get(indices2.size() - 1);
            min = Math.min(min, Math.abs(idx1 - idx2));
            i++;
        }

        // Process remaining elements of word2 if any
        while (j < indices2.size()) {
            int idx2 = indices2.get(j);
            int idx1 = indices1.get(indices1.size() - 1);
            min = Math.min(min, Math.abs(idx2 - idx1));
            j++;
        }

        return min;
    }
}
```

## Two-Pointer Logic Explanation

```
Why move the pointer at smaller index?

If idx1 < idx2:
  distance = idx2 - idx1
  Moving i forward: new_idx1 will be > idx1, closer to idx2
  Potentially decreasing distance

If idx1 >= idx2:
  distance = idx1 - idx2
  Moving j forward: new_idx2 will be > idx2, closer to idx1
  Potentially decreasing distance

This greedy approach finds minimum distance without nested loops
```

## Complexity Comparison

| Operation | Problem 243 | Problem 244 |
|-----------|------------|------------|
| Constructor | O(1) | O(n) |
| Single shortest() | O(n) | O(m + k) |
| k queries | O(k*n) | O(n + k*(m+k)) |

For many queries (k >> 1), Problem 244 is much faster!

## Edge Cases

1. **Single occurrence each**: ["a", "b"] -> 1
2. **Multiple same words**: ["a", "a", "b"] -> 1
3. **Words at boundaries**: ["a", "x", "x", "b"] -> 3
4. **Long distance**: ["a", "x", "x", "x", "b"] -> 4

## Using PutIfAbsent (Java 8+)

```java
// Cleaner constructor with putIfAbsent
public ShortestWordDistance2(String[] words) {
    for (int i = 0; i < words.length; i++) {
        wordMap.putIfAbsent(words[i], new ArrayList<>());
        wordMap.get(words[i]).add(i);
    }
}

// Or using computeIfAbsent
public ShortestWordDistance2(String[] words) {
    for (int i = 0; i < words.length; i++) {
        wordMap.computeIfAbsent(words[i], k -> new ArrayList<>())
               .add(i);
    }
}
```

## Why Two-Pointer Works

Both lists are naturally sorted because we traverse words from left to right.
This allows efficient finding of minimum distance without nested loops.

## Related Problems

- 243: Shortest Word Distance (single query version)
- 245: Shortest Word Distance III (word1 and word2 can be same)
- 346: Moving Average from Data Stream
- 232: Implement Queue using Stacks
## Tags

`medium` `hash-map` `design` `two-pointer` `optimization` `preprocessing`
