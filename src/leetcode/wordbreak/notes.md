# LeetCode 139: Word Break

## Problem Statement
Given a non-empty string `s` and a dictionary `wordDict` containing a list of non-empty words, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words.

**Constraints:**
- String contains only lowercase alphabets
- Dictionary does not contain duplicate words
- Cannot use word more than once per segmentation
- Return true if string can be segmented, false otherwise

## Examples

### Example 1
- **Input:** `s = "leetcode"`, `wordDict = ["leet", "code"]`
- **Output:** `true`
- **Explanation:** "leetcode" can be segmented as "leet code"

### Example 2
- **Input:** `s = "applepenapple"`, `wordDict = ["apple", "pen"]`
- **Output:** `true`
- **Explanation:** "applepenapple" can be segmented as "apple pen apple"

### Example 3
- **Input:** `s = "catsandog"`, `wordDict = ["cat", "cats", "and", "sand", "dog"]`
- **Output:** `false`
- **Explanation:** Cannot find valid segmentation

## Key Insights

1. **Dynamic Programming Problem:** Build solution from smaller subproblems
2. **Memoization/Caching:** Avoid recalculating same subproblems
3. **Two Directions:** Top-down (recursive with memoization) or bottom-up (iterative)
4. **Index-Based DP:** Track which positions can start valid segmentations
5. **Dictionary Lookup:** Use HashSet for O(1) membership checking

## Algorithm Steps

### Approach 1: Top-Down Recursive with Memoization

```
1. Create cache array of size string.length + 1
2. Initialize cache with -1 (not computed)
3. wordBreakHelper(s, index):
   a. If cache[index] != -1, return cached result
   b. Base case: if index == length, return true
   c. Try all possible substrings starting at index:
      - If substring in dictionary AND rest can be segmented:
        - Return true
   d. Cache and return false if no valid segmentation
```

### Approach 2: Bottom-Up Iterative (Most Efficient)

```
1. Create cache array of size string.length + 1
2. Initialize cache[length] = true (empty string is valid)
3. For each position j from length-1 down to 0:
   a. Try all substrings starting at j
   b. If substring s[j..i] is in dictionary AND cache[i+1] == true:
      - Set cache[j] = true and break
4. Return cache[0]
```

## Complexity Analysis

### Approach 1: Recursive with Memoization
- **Time Complexity:** O(n²) - At most n positions × checking all substrings
- **Space Complexity:** O(n) - Cache array + recursion stack

### Approach 2: Iterative DP
- **Time Complexity:** O(n²) - Nested loops for all substrings
- **Space Complexity:** O(n) - Cache array only

## ASCII Visualization

```
Input: s = "leetcode", wordDict = ["leet", "code"]

Bottom-Up DP Approach:
Cache index: 0   1   2   3   4   5   6   7   8 (length)
String:      l   e   e   t   c   o   d   e   |
Cache:       ?   ?   ?   ?   ?   ?   ?   ?   T

Start from right (index 7 down to 0):

j=7: Substrings from position 7
  "e" in dict? NO
  cache[7] = false

j=6: Substrings from position 6
  "de" in dict? NO
  "d" in dict? NO
  cache[6] = false

j=5: Substrings from position 5
  "ode" in dict? NO
  "od" in dict? NO
  "o" in dict? NO
  cache[5] = false

j=4: Substrings from position 4
  "code" in dict? YES, cache[8]=T → cache[4] = true
  (found valid segmentation)

j=3: Substrings from position 3
  "tcode" in dict? NO
  "tcod" in dict? NO
  "tco" in dict? NO
  "tc" in dict? NO
  "t" in dict? NO
  cache[3] = false

j=2: Substrings from position 2
  "etcode" in dict? NO
  "etco" in dict? NO
  "etc" in dict? NO
  "et" in dict? NO
  "e" in dict? NO
  cache[2] = false

j=1: Substrings from position 1
  "eetcode" in dict? NO
  "eetcod" in dict? NO
  ... (many checks)
  cache[1] = false

j=0: Substrings from position 0
  "leetcode" in dict? NO
  "leetcod" in dict? NO
  "leetco" in dict? NO
  "leetc" in dict? NO
  "leet" in dict? YES, cache[4]=T → cache[0] = true

Final: cache[0] = true ✓
Result: "leet code" ✓
```

## Code Walkthrough (Iterative Approach)

```java
public boolean wordBreakHelperIterative(String s, List<String> wordDict) {
    int[] cache = new int[s.length() + 1];

    // Bottom-up from right to left
    for (int j = s.length() - 1; j >= 0; j--) {
        boolean result = false;

        // Try all substrings starting at j
        for (int i = j; i < s.length(); i++) {
            if (i + 1 < s.length()) {
                // Check if substring and rest can be segmented
                result = result || (wordDict.contains(s.substring(j, i + 1))
                        && (cache[i + 1] == 1));
            } else {
                // Last substring, just check dictionary
                result = result || (wordDict.contains(s.substring(j, i + 1)));
            }
        }
        cache[j] = result ? 1 : 0;
    }

    return cache[0] == 1;
}
```

**Execution Flow:**
1. Initialize cache with zeros
2. Process from end of string backward
3. For each position, try all possible dictionary words starting there
4. Only mark position as valid if word found AND rest of string is valid
5. Return whether position 0 is valid

## Code Walkthrough (Recursive with Memoization)

```java
public boolean wordBreakHelper(String s, int index, List<String> wordDict) {
    if (index >= s.length())
        return true;  // Empty substring is valid

    boolean result = false;
    for (int i = index; i < s.length(); i++) {
        String substring = s.substring(index, i + 1);
        if (wordDict.contains(substring)) {
            // Recursively check if rest can be segmented
            result = result || wordBreakHelper(s, i + 1, wordDict);
            if (result)  // Early termination
                break;
        }
    }
    return result;
}
```

## Edge Cases

1. **Empty String:** Should return true (vacuously true)
   - `s = ""`, `dict = ["a"]` → true (but problem says non-empty)

2. **Word Not in Dictionary:** Cannot segment
   - `s = "abc"`, `dict = ["ab", "c"]` → false (missing combination)

3. **Single Character:** Must be in dictionary
   - `s = "a"`, `dict = ["a"]` → true
   - `s = "a"`, `dict = ["b"]` → false

4. **Full String as Single Word:** Should work
   - `s = "hello"`, `dict = ["hello"]` → true

5. **Multiple Valid Segmentations:** Only need one
   - `s = "applepenapple"`, `dict = ["apple", "pen"]` → true

6. **Overlapping Words:** Must choose correct segmentation
   - `s = "catsandcatsdog"`, `dict = ["cat", "cats", "and", "sand", "dog", "catsand"]`

### Example Edge Cases:
```
Input: s = "leetcode", dict = ["leet", "code"], Output: true
Input: s = "applepenapple", dict = ["apple", "pen"], Output: true
Input: s = "catsandog", dict = ["cat", "cats", "and", "sand", "dog"], Output: false
Input: s = "a", dict = ["a"], Output: true
Input: s = "ab", dict = ["a"], Output: false
```

## Variations

### Word Break with Reuse
```
// Some versions allow using same word multiple times
// The approach remains same, just no tracking of used words
```

## Related Problems

1. **LeetCode 140 - Word Break II:** Return all possible segmentations
2. **LeetCode 472 - Concatenated Words:** Find concatenated words in list
3. **LeetCode 301 - Remove Invalid Parentheses:** Similar DP segmentation
4. **LeetCode 97 - Interleaving String:** DP matching problem
5. **LeetCode 212 - Word Search II:** Word search with dictionary
## Tags

`#Dynamic-Programming` `#String` `#Hash-Table` `#BFS` `#Medium`

## Key Takeaways

- Classic DP problem: solve using smaller subproblems
- Iterative bottom-up approach is typically most efficient
- Memoization caches prevent recomputation
- HashSet for dictionary provides O(1) lookup
- Early termination in recursion improves performance
- Both top-down and bottom-up solve in O(n²) time
