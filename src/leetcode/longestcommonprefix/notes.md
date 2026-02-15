# Longest Common Prefix

## Problem Statement
**LeetCode Problem 14**: Longest Common Prefix (Easy)

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

### Examples
**Example 1:**
```
Input: ["flower","flow","flight"]
Output: "fl"
```

**Example 2:**
```
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

**Note**: All given inputs are in lowercase letters a-z.

## Key Insights
1. **Character-by-Character Comparison**: Check each position across all strings
2. **Early Termination**: Stop at first mismatch or shortest string end
3. **Recursive Approach**: Build prefix character by character
4. **Alternative: Horizontal Scanning**: Compare strings pairwise
5. **Shortest String Constraint**: Prefix cannot be longer than shortest string

## Algorithm Steps

### Approach 1: Vertical Scanning (Recursive)
```
1. Base case: If first string is empty or null, return 0
2. Get character at current index from first string
3. Check if all other strings have same character at this index:
   a. If any string is too short, return 0
   b. If any character differs, return 0
4. If all match, return 1 + recurse(index + 1)
5. Extract substring from first string with computed length
```

### Approach 2: Horizontal Scanning (Iterative)
```
1. Take first string as initial prefix
2. For each subsequent string:
   a. While string doesn't start with prefix:
      - Remove last character from prefix
      - If prefix becomes empty, return ""
3. Return remaining prefix
```

## Complexity Analysis

### Vertical Scanning (Current Implementation):
- **Time Complexity**: O(S)
  - S = sum of all characters in all strings
  - Best case: O(n * minLen) where n = number of strings
- **Space Complexity**: O(m)
  - m = length of prefix (recursion stack)

### Horizontal Scanning:
- **Time Complexity**: O(S)
  - In worst case, scan all characters
- **Space Complexity**: O(1)
  - No extra space except prefix

## Visual Representation

### Example 1: ["flower", "flow", "flight"]
```
Vertical Scanning (character by character):

Index: 0  1  2  3  4  5
-----------------------------
str[0]: f  l  o  w  e  r
str[1]: f  l  o  w
str[2]: f  l  i  g  h  t

Position 0: f = f = f ✓
Position 1: l = l = l ✓
Position 2: o = o ≠ i ✗ STOP!

Common prefix: "fl"
```

### Example 2: ["dog", "racecar", "car"]
```
Index: 0  1  2  3  4  5  6
-----------------------------
str[0]: d  o  g
str[1]: r  a  c  e  c  a  r
str[2]: c  a  r

Position 0: d ≠ r ✗ STOP!

Common prefix: ""
```

### Recursive Trace for ["flower", "flow", "flight"]
```
lcs(strs, 0)
  strs[0].charAt(0) = 'f'
  Check all strings at index 0:
    "flower"[0] = 'f' ✓
    "flow"[0] = 'f' ✓
    "flight"[0] = 'f' ✓
  return 1 + lcs(strs, 1)

  lcs(strs, 1)
    strs[0].charAt(1) = 'l'
    Check all strings at index 1:
      "flower"[1] = 'l' ✓
      "flow"[1] = 'l' ✓
      "flight"[1] = 'l' ✓
    return 1 + lcs(strs, 2)

    lcs(strs, 2)
      strs[0].charAt(2) = 'o'
      Check all strings at index 2:
        "flower"[2] = 'o' ✓
        "flow"[2] = 'o' ✓
        "flight"[2] = 'i' ✗
      return 0

    return 1 + 0 = 1
  return 1 + 1 = 2

Final: strs[0].substring(0, 2) = "fl"
```

## Code Walkthrough

### Current Implementation (Recursive)
```java
public String longestCommonPrefix(String[] strs) {
    // Handle edge cases
    if (strs == null || strs.length == 0)
        return "";

    // Find length of common prefix
    int count = lcs(strs, 0);

    // No common prefix found
    if (count == 0)
        return "";

    // Extract prefix from first string
    return strs[0].substring(0, count);
}

int lcs(String[] strs, int start) {
    // Base case: first string exhausted or null
    if (strs[0] == null || strs[0].isEmpty() || strs[0].length() <= start)
        return 0;

    // Get character at current position from first string
    char ch = strs[0].charAt(start);

    // Check if all strings have same character at this position
    for (int i = 1; i < strs.length; i++) {
        // String too short or character mismatch
        if (strs[i].length() <= start || ch != strs[i].charAt(start))
            return 0;
    }

    // All matched, continue to next position
    return 1 + lcs(strs, start + 1);
}
```

### Alternative: Horizontal Scanning
```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0)
        return "";

    // Start with first string as prefix
    String prefix = strs[0];

    // Compare with each subsequent string
    for (int i = 1; i < strs.length; i++) {
        // Shrink prefix until it matches start of current string
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);

            // No common prefix found
            if (prefix.isEmpty())
                return "";
        }
    }

    return prefix;
}
```

### Alternative: Divide and Conquer
```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0)
        return "";

    return divideAndConquer(strs, 0, strs.length - 1);
}

private String divideAndConquer(String[] strs, int left, int right) {
    if (left == right) {
        return strs[left];
    }

    int mid = (left + right) / 2;
    String leftLCP = divideAndConquer(strs, left, mid);
    String rightLCP = divideAndConquer(strs, mid + 1, right);

    return commonPrefix(leftLCP, rightLCP);
}

private String commonPrefix(String left, String right) {
    int minLen = Math.min(left.length(), right.length());
    for (int i = 0; i < minLen; i++) {
        if (left.charAt(i) != right.charAt(i)) {
            return left.substring(0, i);
        }
    }
    return left.substring(0, minLen);
}
```

## Edge Cases
1. **Empty array**: Return ""
2. **Single string**: Return entire string
3. **One empty string**: Return ""
4. **All strings identical**: Return entire string
5. **No common prefix**: Return ""
6. **Strings of different lengths**: Limited by shortest

### Edge Case Examples
```
Input: []
Output: ""

Input: ["abc"]
Output: "abc"

Input: ["", "abc"]
Output: ""

Input: ["abc", "abc", "abc"]
Output: "abc"

Input: ["a", "b", "c"]
Output: ""

Input: ["ab", "abc", "abcd"]
Output: "ab"

Input: ["abcd", "ab", "abc"]
Output: "ab"
```

## Optimization Comparison

| Approach | Time | Space | Best For |
|----------|------|-------|----------|
| Vertical Scanning | O(S) | O(m) | Clear logic |
| Horizontal Scanning | O(S) | O(1) | Space efficiency |
| Divide & Conquer | O(S) | O(m log n) | Large datasets |
| Binary Search | O(S log m) | O(1) | Many short strings |

Where:
- S = sum of all characters
- m = length of shortest string
- n = number of strings

## Related Problems
- [**Longest Common Subsequence **](../longestuncommonsubsequence/notes.md): More complex version
- [**Shortest Common Supersequence **](../longestuncommonsubsequence/notes.md): Related concept
- **Find the Index of First Occurrence (LeetCode 28)**: String matching
- **Implement strStr() (LeetCode 28)**: Substring search
- [**Valid Palindrome **](../validpalindrome/notes.md): Character comparison

## Tags
- String
- String Matching
- Recursion
- Easy
- Interview Warm-up
- Amazon
- Microsoft
