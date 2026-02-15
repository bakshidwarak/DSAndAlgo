# 243. Shortest Word Distance

## Problem Statement
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Assumptions:
- word1 does not equal word2
- Both word1 and word2 are in the list
- Words are case-sensitive

## Examples

### Example 1
```
Input: words = ["practice", "makes", "perfect", "coding", "makes"],
       word1 = "coding", word2 = "practice"
Output: 3
Explanation: Distance between index 3 (coding) and 0 (practice) is 3
```

### Example 2
```
Input: words = ["practice", "makes", "perfect", "coding", "makes"],
       word1 = "makes", word2 = "coding"
Output: 1
Explanation: Distance between index 4 (makes) and 3 (coding) is 1
```

## Key Insights

1. **Single pass solution**: Track indices of both words
2. **Update minimum**: Every time we find either word, update minimum distance
3. **No need for storage**: Don't need to store all indices
4. **O(n) time and O(1) space**: Simple and efficient

## Algorithm Steps

1. Initialize index1 = -1, index2 = -1, min = Integer.MAX_VALUE
2. Iterate through the words array:
   - If word equals word1, update index1 to current index
   - If word equals word2, update index2 to current index
   - If both indices are valid (!= -1), update min with absolute difference
3. Return min

## Complexity Analysis

**Time Complexity:** O(n)
- Single pass through the array
- n = length of words array

**Space Complexity:** O(1)
- Only using a few pointer variables
- No additional data structures

## ASCII Visualization

```
Array: ["practice", "makes", "perfect", "coding", "makes"]
Index:   0          1       2          3        4

Search for word1="coding", word2="practice"

i=0: words[0]="practice"
  If word equals word2? YES -> index2=0
  If word equals word1? NO
  Both indices valid? NO (index1=-1)

i=1: words[1]="makes"
  If word equals word2? NO
  If word equals word1? NO
  Both indices valid? NO

i=2: words[2]="perfect"
  If word equals word2? NO
  If word equals word1? NO
  Both indices valid? NO

i=3: words[3]="coding"
  If word equals word2? NO
  If word equals word1? YES -> index1=3
  Both indices valid? YES (index1=3, index2=0)
  min = min(MAX, |3-0|) = 3

i=4: words[4]="makes"
  If word equals word2? NO
  If word equals word1? NO
  Both indices valid? YES
  min = min(3, |3-0|) = 3

Return: 3
```

## Code Walkthrough

```java
public static int shortestDistance(String[] words, String word1, String word2) {
    // Initialize indices to -1 (not found)
    int index1 = -1;
    int index2 = -1;
    int min = Integer.MAX_VALUE;

    // Single pass through the array
    for (int i = 0; i < words.length; i++) {
        // Update index if word matches word1
        if (words[i].equals(word1)) {
            index1 = i;
        }

        // Update index if word matches word2
        if (words[i].equals(word2)) {
            index2 = i;
        }

        // If we've found both words, update minimum distance
        if (index1 != -1 && index2 != -1) {
            min = Math.min(min, Math.abs(index2 - index1));
        }
    }

    return min;
}
```

## Detailed Walkthrough

```
words = ["practice", "makes", "perfect", "coding", "makes"]
word1 = "makes", word2 = "coding"

Iteration 1 (i=0):
  words[0] = "practice"
  "practice".equals("makes")? NO
  "practice".equals("coding")? NO
  index1=-1, index2=-1

Iteration 2 (i=1):
  words[1] = "makes"
  "makes".equals("makes")? YES -> index1 = 1
  "makes".equals("coding")? NO
  index1 != -1 && index2 != -1? NO

Iteration 3 (i=2):
  words[2] = "perfect"
  "perfect".equals("makes")? NO
  "perfect".equals("coding")? NO
  index1 != -1 && index2 != -1? NO

Iteration 4 (i=3):
  words[3] = "coding"
  "coding".equals("makes")? NO
  "coding".equals("coding")? YES -> index2 = 3
  index1 != -1 && index2 != -1? YES (1 and 3)
  min = min(MAX, |1-3|) = 2

Iteration 5 (i=4):
  words[4] = "makes"
  "makes".equals("makes")? YES -> index1 = 4
  "makes".equals("coding")? NO
  index1 != -1 && index2 != -1? YES (4 and 3)
  min = min(2, |4-3|) = 1

Return: 1
```

## Edge Cases

1. **Two words at consecutive positions**: ["a", "b"] -> 1
2. **Words at opposite ends**: ["a", "b", "c", "d"] (find a and d) -> 3
3. **Multiple occurrences**: ["a", "b", "a", "b"] -> 1
4. **Word1 before word2**: ["a", "x", "b"] -> 2
5. **Word2 before word1**: ["b", "x", "a"] -> 2

## Alternative Approaches

### Approach 2: Store All Indices (Space-heavy)
```java
public int shortestDistanceAlt(String[] words, String word1, String word2) {
    List<Integer> indices1 = new ArrayList<>();
    List<Integer> indices2 = new ArrayList<>();

    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1)) indices1.add(i);
        if (words[i].equals(word2)) indices2.add(i);
    }

    int min = Integer.MAX_VALUE;
    for (int i : indices1) {
        for (int j : indices2) {
            min = Math.min(min, Math.abs(i - j));
        }
    }
    return min;
}
// Time: O(n + m*k) where m and k are counts of word1 and word2
// Space: O(m + k)
```

## Why One Pass is Better

- **Time**: O(n) vs O(n + m*k)
- **Space**: O(1) vs O(m + k)
- **Elegance**: Single pass captures the idea perfectly

## Related Problems

- 244: Shortest Word Distance II (multiple queries, preprocessing)
- 245: Shortest Word Distance III (word1 and word2 can be the same)
- 1166: Design File System

## Tags

`easy` `array` `string` `one-pass` `optimization`
