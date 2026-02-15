# LeetCode 583: Delete Operation for Two Strings

## Problem Statement

Given two strings `word1` and `word2`, return the **minimum number of steps** required to make `word1` and `word2` the same.

In one step, you can delete exactly one character in either string.

### Examples

**Example 1:**
```
Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation:
Step 1: "sea" -> "ea" (delete 's')
Step 2: "eat" -> "ea" (delete 't')
```

**Example 2:**
```
Input: word1 = "leetcode", word2 = "etco"
Output: 4
Explanation:
Delete 'l', 'e', 'e', 'd' from word1 to get "to"
Delete 'e', 'c' from word2 to get "to"
Or find LCS "eto" and delete accordingly
```

**Example 3:**
```
Input: word1 = "a", word2 = "b"
Output: 2
Explanation: Delete both characters
```

**Constraints:**
- 1 <= word1.length, word2.length <= 500
- word1 and word2 consist of only lowercase English letters

## Key Insights

1. **LCS Connection**: The problem is equivalent to finding the Longest Common Subsequence (LCS)
   - Result = len(word1) + len(word2) - 2 * LCS_length

2. **Dynamic Programming**: Use 2D DP to compute minimum deletions at each position

3. **Recursive Structure**:
   - If characters match, no deletion needed, move both pointers
   - If they don't match, try deleting from either string and take minimum

4. **Memoization**: Cache results to avoid recomputation

5. **State Definition**: dp[i][j] = minimum deletions to make word1[i..] equal to word2[j..]

## Algorithm Steps

### Top-Down DP with Memoization

1. **Initialize**:
   - Create 2D array result[word1.length + 1][word2.length + 1]
   - Fill with -1 to indicate uncomputed states

2. **Recursive Helper**:
   - If result already computed, return cached value
   - If at end of word1, return remaining characters in word2
   - If at end of word2, return remaining characters in word1
   - If characters match, recurse with both indices incremented
   - If characters don't match:
     - Option 1: Delete from word1 (increment index1)
     - Option 2: Delete from word2 (increment index2)
     - Take minimum + 1

3. **Cache and Return**: Store result in dp array and return

## Complexity Analysis

- **Time Complexity**: O(m * n)
  - m = word1.length, n = word2.length
  - Each of m*n states computed once
  - Each computation is O(1)
  - Overall: O(m * n)

- **Space Complexity**: O(m * n)
  - 2D DP array: O(m * n)
  - Recursion stack: O(m + n)
  - Overall: O(m * n)

## Visual Explanation

### Example: word1 = "sea", word2 = "eat"

```
DP Table (dp[i][j] = min deletions to make word1[i..] == word2[j..]):

        ""  e   a   t
    ""  0   1   2   3
    s   1   2   2   3
    e   2   1   2   3
    a   3   2   1   2

Trace:
- Start at [0][0]
- s != e: min(delete s: [1][0], delete e: [0][1]) + 1 = min(1,1) + 1 = 2
- Continue filling table based on matches and mismatches

Result: dp[0][0] = 2
```

### Decision Tree

```
"sea" vs "eat"

                    (0,0): s vs e
                   /              \
         delete s /                \ delete e
                /                    \
          (1,0): e vs e          (0,1): s vs a
          match!                      /        \
                |                    /          \
          (2,1): a vs a        (1,1) OR    (0,2)
          match!                   |
                |                  |
          (3,2): "" vs t      ... continues
          return 1
```

### Step-by-Step Example

```
word1 = "sea", word2 = "eat"

Index positions:
word1: s(0) e(1) a(2)
word2: e(0) a(1) t(2)

helper("sea", 0, "eat", 0):
  s != e
  Option 1: delete s -> helper("ea", 1, "eat", 0) = ?
  Option 2: delete e -> helper("sea", 0, "at", 1) = ?

helper("ea", 1, "eat", 0):
  e == e
  -> helper("a", 2, "at", 1)

helper("a", 2, "at", 1):
  a == a
  -> helper("", 3, "t", 2)

helper("", 3, "t", 2):
  word1 exhausted
  return len(word2) - index2 = 3 - 2 = 1

Backtrack: helper("ea", 1, "eat", 0) = 1
Backtrack: Option 1 cost = 1 + 1 = 2

Similarly, Option 2 also gives 2
Result: min(2, 2) = 2
```

## Code Walkthrough

```java
public int minDistance(String word1, String word2) {
    // Handle null cases
    if (word1 == null && word2 != null)
        return word2.length();
    if (word2 == null && word1 != null)
        return word1.length();
    if (word1 == null && word2 == null)
        return 0;

    // Initialize DP table with -1 (uncomputed)
    int[][] result = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i < result.length; i++) {
        Arrays.fill(result[i], -1);
    }

    return helper(word1, 0, word2, 0, result);
}

public int helper(String word1, int index1, String word2, int index2, int[][] result) {
    // If result already computed, return cached value
    if (result[index1][index2] == -1) {

        // Base case 1: word1 exhausted
        if (index1 == word1.length()) {
            result[index1][index2] = word2.length() - index2;
        }
        // Base case 2: word2 exhausted
        else if (index2 == word2.length()) {
            result[index1][index2] = word1.length() - index1;
        }
        // Case 3: Characters match
        else if (word1.charAt(index1) == word2.charAt(index2)) {
            // No deletion needed, move both pointers
            result[index1][index2] = helper(word1, index1 + 1, word2, index2 + 1, result);
        }
        // Case 4: Characters don't match
        else {
            // Option 1: Delete from word1
            int deleteWord1 = helper(word1, index1 + 1, word2, index2, result);
            // Option 2: Delete from word2
            int deleteWord2 = helper(word1, index1, word2, index2 + 1, result);

            // Take minimum and add 1 for current deletion
            result[index1][index2] = 1 + Math.min(deleteWord1, deleteWord2);
        }
    }

    return result[index1][index2];
}
```

## Bottom-Up DP Solution

```java
public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    int[][] dp = new int[m + 1][n + 1];

    // Initialize base cases
    for (int i = 0; i <= m; i++) dp[i][n] = m - i;
    for (int j = 0; j <= n; j++) dp[m][j] = n - j;

    // Fill DP table
    for (int i = m - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 0; j--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                dp[i][j] = dp[i + 1][j + 1];
            } else {
                dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
    }

    return dp[0][0];
}
```

## LCS-Based Solution

```java
public int minDistance(String word1, String word2) {
    int lcs = longestCommonSubsequence(word1, word2);
    return word1.length() + word2.length() - 2 * lcs;
}

private int longestCommonSubsequence(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[m][n];
}
```

## Edge Cases

1. **Identical Strings**: word1 = "abc", word2 = "abc"
   - Output: 0 (no deletions needed)

2. **Completely Different**: word1 = "abc", word2 = "def"
   - Output: 6 (delete all characters from both)

3. **One Empty**: word1 = "", word2 = "abc"
   - Output: 3

4. **Single Character Match**: word1 = "a", word2 = "a"
   - Output: 0

5. **Single Character Different**: word1 = "a", word2 = "b"
   - Output: 2

6. **One Substring of Other**: word1 = "abc", word2 = "aec"
   - Output: 2 (delete 'b' from word1 and 'e' from word2)

## Related Problems

1. **LeetCode 72**: Edit Distance (allows insert, delete, replace)
2. **LeetCode 1143**: Longest Common Subsequence
3. **LeetCode 712**: Minimum ASCII Delete Sum for Two Strings
4. **LeetCode 115**: Distinct Subsequences
5. **LeetCode 392**: Is Subsequence

## Tags

- Dynamic Programming
- String
- Memoization
- Longest Common Subsequence (LCS)
- Recursion
- Two Pointers
