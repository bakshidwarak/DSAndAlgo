# Longest Palindromic Subsequence

## Problem Statement
**LeetCode Problem 516**: Longest Palindromic Subsequence (Medium)

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

### Examples
**Example 1:**
```
Input: "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
```

**Example 2:**
```
Input: "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
```

## Key Insights
1. **Subsequence vs Substring**: Can skip characters (don't need to be contiguous)
2. **Dynamic Programming**: Use 2D DP where dp[i][j] = LPS from index i to j
3. **Two Approaches**: Recursion with memoization OR bottom-up iteration
4. **Palindrome Property**: If s[i] == s[j], include both in palindrome
5. **Recurrence Relation**:
   - If s[i] == s[j]: dp[i][j] = 2 + dp[i+1][j-1]
   - Else: dp[i][j] = max(dp[i+1][j], dp[i][j-1])

## Algorithm Steps

### Approach 1: Recursion with Memoization
```
1. Create 2D cache array
2. Call helper(s, 0, n-1, cache)
3. Helper function:
   a. Base cases:
      - If l > r: return 0
      - If l == r: return 1 (single char)
   b. If cached, return cached value
   c. If s[l] == s[r]:
      - Result = 2 + helper(l+1, r-1)
   d. Else:
      - Result = max(helper(l, r-1), helper(l+1, r))
   e. Cache and return result
```

### Approach 2: Bottom-Up Iteration (Optimal)
```
1. Create 2D DP table
2. Fill diagonals from bottom-right to top-left:
   a. For each starting position i (from end to start):
      For each ending position j (from i to end):
        - If i > j: dp[i][j] = 0
        - If i == j: dp[i][j] = 1
        - If s[i] == s[j]: dp[i][j] = 2 + dp[i+1][j-1]
        - Else: dp[i][j] = max(dp[i][j-1], dp[i+1][j])
3. Return dp[0][n-1]
```

## Complexity Analysis
- **Time Complexity**: O(n²)
  - Fill n x n DP table
  - Each cell computed once
- **Space Complexity**: O(n²)
  - 2D DP table
  - Can be optimized to O(n) with rolling array

## Visual Representation

### Example: s = "bbbab"
```
String: b  b  b  a  b
Index:  0  1  2  3  4

DP Table Construction (bottom-up):
     j: 0  1  2  3  4
i:
0       1  2  3  3  4
1          1  2  2  3
2             1  1  3
3                1  1
4                   1

Interpretation:
dp[0][4] = 4: LPS from index 0 to 4 = "bbbb"

How dp[0][4] is computed:
s[0]='b', s[4]='b', they match!
dp[0][4] = 2 + dp[1][3] = 2 + 2 = 4

dp[1][3] = 2:
s[1]='b', s[3]='a', don't match
dp[1][3] = max(dp[1][2], dp[2][3]) = max(2, 1) = 2

One valid LPS: "bbbb" (skip 'a' at index 3)
```

### Example: s = "cbbd"
```
String: c  b  b  d
Index:  0  1  2  3

DP Table:
     j: 0  1  2  3
i:
0       1  1  2  2
1          1  2  2
2             1  1
3                1

dp[0][3] = 2: LPS = "bb"

Computation:
s[0]='c', s[3]='d', don't match
dp[0][3] = max(dp[0][2], dp[1][3])
         = max(2, 2) = 2

One valid LPS: "bb"
```

### Recursion Tree for "bbbab"
```
                  lps(0, 4)
                 /         \
         s[0]==s[4]='b'
                |
           2 + lps(1, 3)
              /        \
       s[1]='b'!=s[3]='a'
           /              \
      lps(1, 2)        lps(2, 3)
         /                  /
   s[1]==s[2]='b'    s[2]='b'!=s[3]='a'
         |                /      \
    2 + lps(2, 1)   lps(2, 2)  lps(3, 3)
         |               |         |
       return 0          1         1

Result: 2 + 2 = 4
```

## Code Walkthrough

### Current Implementation (Iterative Bottom-Up)
```java
public int longestPalindromeSubseq(String s) {
    int[][] cache = new int[s.length()][s.length()];
    return longestPalindromeHelperIterative(s, 0, s.length() - 1, cache);
}

public int longestPalindromeHelperIterative(String s, int l, int r, int[][] cache) {
    // Fill table from bottom-right to top-left
    for (int i = s.length() - 1; i >= 0; i--) {
        for (int j = 0; j < s.length(); j++) {

            // Base case: invalid range
            if (i > j) {
                cache[i][j] = 0;
                continue;
            }

            // Base case: single character
            if (i == j) {
                cache[i][j] = 1;
                continue;
            }

            // Recurrence relation
            if (s.charAt(i) == s.charAt(j)) {
                // Characters match: include both
                cache[i][j] = 2 + cache[i + 1][j - 1];
            } else {
                // Characters don't match: try excluding each
                cache[i][j] = Math.max(cache[i][j - 1], cache[i + 1][j]);
            }
        }
    }

    // Return LPS for entire string
    return cache[l][r];
}
```

### Recursive with Memoization
```java
public int longestPalindromeHelperRecursionWithMemoization(
    String s, int l, int r, int[][] cache) {

    // Base case: invalid range
    if (l > r) {
        cache[l][r] = 0;
        return cache[l][r];
    }

    // Base case: single character
    if (l == r) {
        cache[l][r] = 1;
        return cache[l][r];
    }

    // Check if already computed
    if (cache[l][r] == -1) {
        if (s.charAt(l) == s.charAt(r)) {
            // Characters match
            cache[l][r] = 2 + longestPalindromeHelperRecursionWithMemoization(
                s, l + 1, r - 1, cache);
        } else {
            // Characters don't match
            cache[l][r] = Math.max(
                longestPalindromeHelperRecursionWithMemoization(s, l, r - 1, cache),
                longestPalindromeHelperRecursionWithMemoization(s, l + 1, r, cache)
            );
        }
    }

    return cache[l][r];
}
```

### Space-Optimized Version (1D DP)
```java
public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[] dp = new int[n];
    int[] dpPrev = new int[n];

    for (int i = n - 1; i >= 0; i--) {
        dp[i] = 1;  // Single character

        for (int j = i + 1; j < n; j++) {
            if (s.charAt(i) == s.charAt(j)) {
                dp[j] = 2 + dpPrev[j - 1];
            } else {
                dp[j] = Math.max(dp[j - 1], dpPrev[j]);
            }
        }

        // Swap arrays
        int[] temp = dpPrev;
        dpPrev = dp;
        dp = temp;
    }

    return dpPrev[n - 1];
}
```

## Edge Cases
1. **Empty string**: Return 0
2. **Single character**: Return 1
3. **All same characters**: Return length
4. **No repeating characters**: Return 1
5. **Entire string is palindrome**: Return length
6. **Two-character string**: Return 1 or 2

### Edge Case Examples
```
Input: ""
Output: 0

Input: "a"
Output: 1

Input: "aaaa"
Output: 4

Input: "abcd"
Output: 1

Input: "racecar"
Output: 7 (entire string)

Input: "ab"
Output: 1

Input: "aa"
Output: 2
```

## Comparison with Related Problems

| Problem | Type | Contiguous? | Time | Space |
|---------|------|-------------|------|-------|
| LPS (this) | Subsequence | No | O(n²) | O(n²) |
| Longest Palindromic Substring | Substring | Yes | O(n²) | O(1) |
| Valid Palindrome | Verification | Yes | O(n) | O(1) |
| Palindrome Partitioning | All partitions | Yes | O(n·2^n) | O(n²) |

## Related Problems
- [**Longest Palindromic Substring **](../longestpalindromicsubsequence/notes.md): Contiguous version
- **Palindrome Partitioning (LeetCode 131)**: Find all partitions
- **Palindrome Partitioning II (LeetCode 132)**: Minimum cuts
- [**Longest Common Subsequence **](../longestuncommonsubsequence/notes.md): Similar DP pattern
- [**Edit Distance **](../editdistance/notes.md): Similar 2D DP

## Tags
- String
- Dynamic Programming
- Recursion
- Memoization
- Subsequence
- Palindrome
- Medium
- Amazon Interview
- Microsoft Interview
