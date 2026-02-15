# Regular Expression Matching - LeetCode Problem 10

## Problem Statement
Given an input string `s` and a pattern `p`, implement regular expression matching with support for '.' and '*'.

- '.' matches any single character
- '*' matches zero or more of the preceding element

The matching should cover the entire input string (not partial).

Constraints:
- s could be empty and contains only lowercase letters a-z
- p could be empty and contains only lowercase letters a-z, and characters like . or *

## Examples

**Example 1:**
- Input: s = "aa" p = "a"
- Output: false
- Explanation: "a" does not match the entire string "aa"

**Example 2:**
- Input: s = "aa" p = "a*"
- Output: true
- Explanation: '*' means zero or more of the preceding element, 'a'. So "a*" matches "aa"

**Example 3:**
- Input: s = "ab" p = ".*"
- Output: true
- Explanation: ".*" means "zero or more of any character"

**Example 4:**
- Input: s = "aab" p = "c*a*b"
- Output: true
- Explanation: c can be repeated 0 times, a can be repeated 1 time. So "c*a*b" matches "aab"

**Example 5:**
- Input: s = "mississippi" p = "mis*is*p*."
- Output: false

## Key Insights
1. **Dynamic Programming**: Subproblems have overlapping solutions
2. **Memoization**: Cache results to avoid recalculation
3. **Pattern Rules**: '.' matches one char, '*' matches 0+ preceding
4. **Two Cases for '*'**: Zero occurrence or one+ occurrences
5. **Recursion with Memoization**: More intuitive than bottom-up DP

## Algorithm Steps

### Approach: Top-Down DP with Memoization

**Base Cases:**
1. If both s and p are empty: match (true)
2. If only s is empty: p must be zero-occurrence pattern (like a*, b*, etc.)
3. If only p is empty: no match (s has remaining chars)

**Recursive Cases:**
1. Check current characters match: s[i] == p[j] or p[j] == '.'
2. Handle '*' pattern:
   - Try zero occurrence: skip pattern (j+2)
   - Try one+ occurrence: if current matches, advance in s (i+1)
3. Use memoization table to store results

**Pseudocode:**
```
function isMatch(s, p):
    memo = new HashMap()
    return isMatch(s, 0, p, 0, memo)

function isMatch(s, i, p, j, memo):
    if (i, j) in memo:
        return memo[(i, j)]

    // Base case: both empty
    if j == p.length:
        return i == s.length

    // First character matches
    firstMatch = i < s.length and (s[i] == p[j] or p[j] == '.')

    // Handle '*'
    if j + 1 < p.length and p[j+1] == '*':
        // Two cases: zero occurrence or one+ occurrence
        zeroOccurrence = isMatch(s, i, p, j+2, memo)
        oneOrMore = firstMatch and isMatch(s, i+1, p, j, memo)
        result = zeroOccurrence or oneOrMore
    else:
        // Normal character or '.' without '*'
        result = firstMatch and isMatch(s, i+1, p, j+1, memo)

    memo[(i, j)] = result
    return result
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(m*n) where m = s.length, n = p.length |
| Space Complexity | O(m*n) for memoization table + O(m*n) recursion stack |

**Time Analysis:**
- Memoization ensures each state (i,j) computed once
- Total states: m*n
- Each state: O(1) operations

## ASCII Visualization

```
Example: s = "aab", p = "c*a*b"

DP Table (i=row for s, j=col for p):
       ""  c  *  a  *  b
    "" T  T  T  F  T  F
    a  F  F  F  T  T  F
    a  F  F  F  F  T  F
    b  F  F  F  F  F  T

Trace through isMatch("aab", "c*a*b"):
- Check if strings match from end backwards
- '*' means flexible matching of preceding char

Recursion Tree (simplified):
                    isMatch(0,0)
                   /            \
            zero c*              (can't match)
            |
        isMatch(0,2)
       /           \
    zero a*         (can't match)
      |
  isMatch(0,4)
  /         \
zero b*    (match b)
(no)        |
            isMatch(1,4)
            /         \
        match b      zero b*
         (no)         (no)
        Failed    Need to continue...

Character Matching Example:
s = "aab"
p = "c*a*b"

Position mapping:
s: |a|a|b|
p: |c|*|a|*|b|

Matching Process:
1. c* : can match 0 c's (skip)
2. a* : can match a's → matches position 0,1
3. b : must match b → matches position 2
Result: True

Pattern Analysis:
- "c*" can represent: "" or "c" or "cc" or ...
- "a*" can represent: "" or "a" or "aa" or ...
- "b" must match exactly one 'b'
```

## Code Walkthrough

```java
public boolean isMatch(String s, String p) {
    // Memoization table: -1 = unknown, 0 = false, 1 = true
    int[][] result = new int[s.length() + 1][p.length() + 1];

    // Initialize with -1 (unknown)
    for (int[] res : result) {
        Arrays.fill(res, -1);
    }

    return isMatch(s, s.length(), p, p.length(), result) == 1;
}

private int isMatch(String s, int word, String p, int pat, int[][] result) {
    if (result[word][pat] != -1) {
        return result[word][pat];
    }

    // Base case: both strings exhausted
    if (word <= 0) {
        int i = pat;
        // Check if remaining pattern is valid (must be x*, y*, etc.)
        while (i > 0) {
            if (p.charAt(i - 1) == '*') {
                i -= 2;  // Skip pair like "a*"
            } else {
                result[word][pat] = 0;
                return 0;
            }
        }
        result[word][pat] = (i <= 0) ? 1 : 0;
        return result[word][pat];
    }

    // If pattern exhausted but string not
    if (pat <= 0) {
        result[word][pat] = (word <= 0) ? 1 : 0;
        return result[word][pat];
    }

    // Check if current characters match (or pattern has '.')
    if (pat > 0 && (s.charAt(word - 1) == p.charAt(pat - 1) || p.charAt(pat - 1) == '.')) {
        // Characters match or pattern has wildcard
        result[word][pat] = isMatch(s, word - 1, p, pat - 1, result);
    } else if (pat > 0 && p.charAt(pat - 1) == '*') {
        // Pattern has '*' - try zero occurrence and one+ occurrence
        boolean zeroOccurrence = isMatch(s, word, p, pat - 2, result) == 1;
        boolean eatingUp = ((s.charAt(word - 1) == p.charAt(pat - 2) ||
                p.charAt(pat - 2) == '.') &&
                isMatch(s, word - 1, p, pat, result) == 1);

        result[word][pat] = (zeroOccurrence || eatingUp) ? 1 : 0;
    } else {
        // Characters don't match
        result[word][pat] = 0;
    }

    return result[word][pat];
}
```

## Edge Cases

1. **Empty Both**: s = "", p = "" -> true
2. **Empty Pattern**: s = "a", p = "" -> false
3. **Empty String**: s = "", p = "a*" -> true
4. **Dot Pattern**: s = "a", p = "." -> true
5. **Star Without Preceding**: p = "*a" (invalid per constraints)
6. **Multiple Stars**: p = "a*b*c*" -> depends on s
7. **Alternating Pattern**: p = "a*b*a*b*" -> complex matching
8. **Single Character**: s = "a", p = "a" -> true

## Related Problems

1. **LeetCode 44**: Wildcard Matching - Similar with '*' and '?'
2. **LeetCode 212**: Word Search II - Pattern matching in grid
3. **LeetCode 943**: Find the Shortest Superstring - Pattern combination
4. **LeetCode 97**: Interleaving String - DP similar structure
## Tags

- Dynamic Programming
- Memoization
- Regular Expression
- Recursion
- String Matching
- Hard Difficulty
- Acceptance: ~28%
