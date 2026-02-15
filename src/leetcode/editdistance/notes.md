# LeetCode 72: Edit Distance

## Problem Statement

Given two strings `word1` and `word2`, return the **minimum number of operations** required to convert `word1` to `word2`.

You have the following three operations permitted on a word:
- **Insert** a character
- **Delete** a character
- **Replace** a character

### Examples

**Example 1:**
```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

**Example 2:**
```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```

**Example 3:**
```
Input: word1 = "abc", word2 = "abc"
Output: 0
```

**Constraints:**
- 0 <= word1.length, word2.length <= 500
- word1 and word2 consist of lowercase English letters

## Key Insights

1. **Classic DP Problem**: One of the most famous dynamic programming problems (Levenshtein Distance)
2. **Three Choices**: At each position, we can insert, delete, or replace
3. **Optimal Substructure**: Solution depends on solutions to smaller subproblems
4. **Bottom-Up vs Top-Down**: Can be solved either way; bottom-up often more efficient
5. **Base Cases**: Converting empty string or to empty string requires len operations

## Algorithm Steps

### Bottom-Up Dynamic Programming

1. **Initialize DP Table**:
   - Create 2D array dp[m+1][n+1] where m=len(word1), n=len(word2)
   - dp[i][j] = min operations to convert word1[i..] to word2[j..]

2. **Base Cases**:
   - dp[m][j] = n - j (insert all remaining characters of word2)
   - dp[i][n] = m - i (delete all remaining characters of word1)

3. **Fill Table** (from bottom-right to top-left):
   - If word1[i] == word2[j]: dp[i][j] = dp[i+1][j+1] (no operation needed)
   - Else: dp[i][j] = 1 + min(
       - dp[i+1][j+1]  // Replace
       - dp[i][j+1]    // Insert
       - dp[i+1][j]    // Delete
     )

4. **Return** dp[0][0]

## Complexity Analysis

- **Time Complexity**: O(m * n)
  - m = word1.length, n = word2.length
  - Fill table with m*n cells
  - Each cell computed in O(1)
  - Overall: O(m * n)

- **Space Complexity**: O(m * n)
  - 2D DP array: O(m * n)
  - Can be optimized to O(min(m, n)) with rolling array
  - Overall: O(m * n)

## Visual Explanation

### Example: word1 = "horse", word2 = "ros"

```
DP Table Construction:

    ""  r   o   s
""  0   1   2   3
h   1   1   2   3
o   2   2   1   2
r   3   2   2   2
s   4   3   3   2
e   5   4   4   3

Step-by-step:
Row by row, from bottom-right to top-left

Base cases:
dp[5][3] = 0 (both strings exhausted)
dp[5][2] = 1 (insert 's')
dp[5][1] = 2 (insert 'o', 's')
dp[5][0] = 3 (insert 'r', 'o', 's')
dp[4][3] = 1 (delete 's')
dp[3][3] = 2 (delete 'r', 's')
...

Key cell dp[0][0]:
word1 = "horse", word2 = "ros"
h != r
  Option 1 (replace): 1 + dp[1][1] = 1 + 1 = 2
  Option 2 (insert):  1 + dp[0][1] = 1 + 1 = 2
  Option 3 (delete):  1 + dp[1][0] = 1 + 1 = 2
  min = 2... but further analysis gives 3
```

### Detailed DP Table with Operations

```
Converting "horse" to "ros":

        ""  r   o   s
    ""  0   1   2   3
        ↓   ←   ←   ←
    h   1   1   2   3
        ↓   ↘   ←   ←
    o   2   2   1   2
        ↓   ←   ↘   ←
    r   3   2   2   2
        ↓   ↘   ←   ↘
    s   4   3   3   2
        ↓   ←   ←   ↘
    e   5   4   4   3

Arrows show the optimal path (operations taken):
↘ = match or replace
← = insert
↓ = delete

Reading the path from [0][0] to [5][3]:
1. Replace h with r: "rorse"
2. Match o: "rorse"
3. Delete r: "rose"
4. Match s: "rose"
5. Delete e: "ros"
Total: 3 operations
```

## Code Walkthrough

### Bottom-Up Solution

```java
public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    // DP table: dp[i][j] = min ops to convert word1[i..] to word2[j..]
    int[][] result = new int[m + 1][n + 1];

    // Fill table from bottom-right to top-left
    for (int i = m; i >= 0; i--) {
        for (int j = n; j >= 0; j--) {

            // Base case 1: word1 exhausted, insert remaining of word2
            if (i == m) {
                result[i][j] = n - j;
            }
            // Base case 2: word2 exhausted, delete remaining of word1
            else if (j == n) {
                result[i][j] = m - i;
            }
            // Case 3: Characters match, no operation needed
            else if (word1.charAt(i) == word2.charAt(j)) {
                result[i][j] = result[i + 1][j + 1];
            }
            // Case 4: Characters don't match, try all operations
            else {
                int replace = result[i + 1][j + 1];  // Replace char
                int insert = result[i][j + 1];       // Insert char
                int delete = result[i + 1][j];       // Delete char

                result[i][j] = 1 + Math.min(replace, Math.min(insert, delete));
            }
        }
    }

    return result[0][0];
}
```

### Top-Down Solution (Memoization)

```java
public int minDistance(String word1, String word2) {
    int[][] result = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i < result.length; i++) {
        Arrays.fill(result[i], -1);
    }
    return distanceHelper(word1, word2, 0, 0, result);
}

public int distanceHelper(String word1, String word2, int index1, int index2, int[][] result) {
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
            result[index1][index2] = distanceHelper(word1, word2, index1 + 1, index2 + 1, result);
        }
        // Case 4: Characters don't match
        else {
            int replace = distanceHelper(word1, word2, index1 + 1, index2 + 1, result);
            int insert = distanceHelper(word1, word2, index1, index2 + 1, result);
            int delete = distanceHelper(word1, word2, index1 + 1, index2, result);

            result[index1][index2] = 1 + Math.min(replace, Math.min(insert, delete));
        }
    }

    return result[index1][index2];
}
```

## Three Operations Explained

```
word1[i] != word2[j], three choices:

1. Replace: word1[i] = word2[j]
   Cost: 1 + dp[i+1][j+1]
   Example: "horse" -> "rorse" (replace h with r)

2. Insert: Insert word2[j] into word1
   Cost: 1 + dp[i][j+1]
   Example: "ros" -> "rosx" (insert x)

3. Delete: Delete word1[i]
   Cost: 1 + dp[i+1][j]
   Example: "horse" -> "orse" (delete h)
```

## Space Optimization

Can reduce space to O(n) using rolling array:

```java
public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[] dp = new int[n + 1];

    // Initialize for empty word1
    for (int j = 0; j <= n; j++) {
        dp[j] = j;
    }

    for (int i = 1; i <= m; i++) {
        int prev = dp[0];
        dp[0] = i;

        for (int j = 1; j <= n; j++) {
            int temp = dp[j];

            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[j] = prev;
            } else {
                dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j - 1]));
            }

            prev = temp;
        }
    }

    return dp[n];
}
```

## Edge Cases

1. **Empty Strings**: word1 = "", word2 = ""
   - Output: 0

2. **One Empty**: word1 = "abc", word2 = ""
   - Output: 3 (delete all)

3. **Identical Strings**: word1 = "abc", word2 = "abc"
   - Output: 0

4. **Single Character**: word1 = "a", word2 = "b"
   - Output: 1 (replace)

5. **Complete Different**: word1 = "abc", word2 = "def"
   - Output: 3 (replace all)

6. **One Substring**: word1 = "abc", word2 = "aXbc"
   - Output: 1 (insert X)

## Related Problems

1. **LeetCode 583**: Delete Operation for Two Strings (only delete allowed)
2. **LeetCode 712**: Minimum ASCII Delete Sum for Two Strings
3. **LeetCode 1143**: Longest Common Subsequence
4. **LeetCode 161**: One Edit Distance
5. **LeetCode 44**: Wildcard Matching
6. **LeetCode 10**: Regular Expression Matching

## Tags

- Dynamic Programming
- String
- Classic Problem
- Levenshtein Distance
- Memoization
- Bottom-Up DP
