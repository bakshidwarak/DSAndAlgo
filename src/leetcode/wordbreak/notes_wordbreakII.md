# LeetCode 140: Word Break II

## Problem Statement
Given a non-empty string `s` and a dictionary `wordDict` containing a list of non-empty words, return all possible sentences where each word is a valid dictionary word. You may reuse dictionary words.

**Constraints:**
- String contains only lowercase alphabets
- Dictionary does not contain duplicate words
- All words are non-empty
- Return list of all possible sentences

## Examples

### Example 1
- **Input:** `s = "catsanddog"`, `wordDict = ["cat", "cats", "and", "sand", "dog"]`
- **Output:** `["cats and dog", "cat sand dog"]`
- **Explanation:** Two valid segmentations found

### Example 2
- **Input:** `s = "pineapplepenapple"`, `wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]`
- **Output:** `["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]`

### Example 3
- **Input:** `s = "catsandog"`, `wordDict = ["cat", "cats", "and", "sand", "dog"]`
- **Output:** `[]` (empty list, no valid segmentation)

## Key Insights

1. **Memoization for Feasibility:** First check if segmentation is possible
2. **Backtracking/DFS:** Generate all valid segmentations
3. **Memoized Feasibility Check:** Cache results of possible[index]
4. **String Building:** Append valid words while backtracking
5. **Pruning:** Only explore paths that can lead to valid segmentations

## Algorithm Steps

### Two-Phase Approach

```
Phase 1: Feasibility Check (Memoization)
1. Create cache array of size string.length + 1
2. Initialize cache[length] = 1 (empty string is feasible)
3. For each index from length-1 down to 0:
   a. If substring s[index..i] in dictionary AND cache[i+1] == 1:
      - Set cache[index] = 1

Phase 2: Generate All Valid Segmentations (DFS/Backtracking)
1. Start DFS from index 0
2. If index == length, add current segmentation to result
3. For each valid substring from index:
   a. If cache[nextIndex] == 1 (feasible):
      - Add substring to current path
      - Recursively DFS from nextIndex
      - Backtrack: remove substring from path
```

## Complexity Analysis

- **Time Complexity:** O(N × 2^N) - Up to 2^N valid segmentations, each takes O(N) to build
- **Space Complexity:** O(N) - Cache array + recursion stack + result storage

## ASCII Visualization

```
Input: s = "catsanddog", wordDict = ["cat", "cats", "and", "sand", "dog"]

Phase 1: Feasibility Check
Position:  0   1   2   3   4   5   6   7   8   9  10
String:    c   a   t   s   a   n   d   d   o   g   |
Cache:     F   F   F   T   F   F   F   F   F   F   T

Working backwards:
cache[10] = 1 (empty, base case)

Position 9: substring "g"
  "g" in dict? NO
  cache[9] = 0

Position 8: substring "og", "o"
  "og" in dict? NO
  "o" in dict? NO
  cache[8] = 0

Position 7: substring "dog", "do", "d"
  "dog" in dict? YES, cache[10]=1 → cache[7] = 1

Position 6: substring "ddog", "ddo", "dd", "d"
  All NO, cache[6] = 0

Position 5: substring "ndddog"..., "and", ...
  "and" in dict? YES, check cache[8]=0... NO
  So position 5 depends on finding valid path
  (continuing this process...)

Final cache after phase 1: [?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 1]

Result: cache[0] determines if solution exists

Phase 2: Generate Solutions (via DFS)
Current = [], index = 0

DFS(0, cache):
  Try substring "c" (0->1)
    "c" in dict? NO

  Try substring "ca" (0->2)
    "ca" in dict? NO

  Try substring "cat" (0->3)
    "cat" in dict? YES
    cache[3] = ? (assume 1 for continuation)
    current = ["cat"]
    DFS(3, cache):
      Try substring "s" (3->4)
        "s" in dict? NO
      Try substring "sa" (3->4)
        ...
      Try substring "sand" (3->7)
        "sand" in dict? YES
        cache[7] = 1 ✓
        current = ["cat", "sand"]
        DFS(7, cache):
          Try substring "dog" (7->10)
            "dog" in dict? YES
            cache[10] = 1 ✓
            current = ["cat", "sand", "dog"]
            DFS(10, cache):
              index == length? YES
              Add "cat sand dog" to results
          index == length? NO, continue
        Backtrack: current = ["cat", "sand"]
    current = ["cat"]

  Try substring "cats" (0->4)
    "cats" in dict? YES
    ... (similar exploration)
    Eventually finds "cats and dog"
```

## Code Walkthrough

```java
public List<String> wordBreak(String s, List<String> wordDict) {
    HashSet<String> dictionary = new HashSet<>(wordDict);
    int[] cache = new int[s.length() + 1];
    Arrays.fill(cache, -1);
    cache[s.length()] = 1;  // Empty string is feasible

    // Phase 1: Check feasibility
    wordBreakHelper(s, 0, dictionary, cache);

    List<String> current = new ArrayList<>();
    List<String> result = new ArrayList<>();

    // Phase 2: Generate solutions only if feasible
    if (cache[0] == 1) {
        dfs(s, cache, current, result, 0, dictionary);
    }

    return result;
}

// Phase 1: Memoized feasibility check
public boolean wordBreakHelper(String s, int start, HashSet<String> dictionary, int[] cache) {
    if (cache[start] == -1) {
        int output = 0;
        for (int i = start; i < s.length(); i++) {
            if (dictionary.contains(s.substring(start, i + 1))
                    && wordBreakHelper(s, i + 1, dictionary, cache)) {
                output = 1;
            }
        }
        cache[start] = output;
    }
    return cache[start] == 1;
}

// Phase 2: DFS to generate all valid segmentations
public void dfs(String s, int[] cache, List<String> current, List<String> result,
                int index, HashSet<String> dictionary) {
    if (index >= s.length()) {
        result.add(current.stream().collect(Collectors.joining(" ")));
        return;
    }

    // Try all possible substrings from current index
    for (int i = index + 1; i <= s.length(); i++) {
        if (cache[i] == 1 && dictionary.contains(s.substring(index, i))) {
            current.add(s.substring(index, i));
            dfs(s, cache, current, result, i, dictionary);
            current.remove(current.size() - 1);  // Backtrack
        }
    }
}
```

**Execution Flow:**
1. Convert word list to HashSet for O(1) lookup
2. Initialize cache array with -1 (uncomputed)
3. Set cache[length] = 1 (base case: empty string is valid)
4. Call wordBreakHelper to compute feasibility
5. If cache[0] == 1, call DFS to generate solutions
6. DFS explores all valid paths, building segmentations
7. When reaching end of string, add to results with spaces

## Edge Cases

1. **No Valid Segmentation:** Return empty list
   - `s = "catsandog"`, `dict = ["cat", "cats", "and", "sand", "dog"]` → `[]`

2. **Single Word:** Return list with one sentence
   - `s = "hello"`, `dict = ["hello"]` → `["hello"]`

3. **Multiple Valid Segmentations:** Return all
   - `s = "catsanddog"`, `dict = ["cat", "cats", "and", "sand", "dog"]` → `["cats and dog", "cat sand dog"]`

4. **Overlapping Subproblems:** Memoization prevents recomputation
   - String with repeated patterns benefits from caching

5. **Full String is Dictionary Word:** Single word solution
   - `s = "abc"`, `dict = ["abc", "ab", "c"]` → `["abc", "ab c"]`

### Example Edge Cases:
```
Input: s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"]
Output: ["cats and dog", "cat sand dog"]

Input: s = "catsandog", dict = ["cat", "cats", "and", "sand", "dog"]
Output: []

Input: s = "pineapplepenapple", dict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output: ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
```

## Related Problems

1. **LeetCode 139 - Word Break:** Check if segmentation exists (boolean)
2. **LeetCode 472 - Concatenated Words:** Find concatenated words in list
3. **LeetCode 301 - Remove Invalid Parentheses:** Generate valid expressions
4. **LeetCode 37 - Sudoku Solver:** Backtracking to find solutions
5. **LeetCode 212 - Word Search II:** Backtracking word search

## Optimization Tips

1. **Skip Infeasible Paths:** Cache prevents exploring impossible paths
2. **Convert to HashSet:** Dictionary lookup becomes O(1)
3. **Early Termination:** Check feasibility before DFS
4. **Prune Search Space:** Only explore cache[i] == 1 positions

## Tags

`#Dynamic-Programming` `#Backtracking` `#DFS` `#String` `#Hash-Table` `#Hard`

## Key Takeaways

- Two-phase approach: feasibility check + solution generation
- Memoization caches prevent exponential recomputation
- Backtracking builds solutions incrementally
- HashSet for O(1) dictionary lookup
- Early pruning based on feasibility cache is critical
- Time complexity depends on number of valid solutions
