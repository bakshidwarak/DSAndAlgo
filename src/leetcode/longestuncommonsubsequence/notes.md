# LeetCode 521: Longest Uncommon Subsequence I

## Problem Statement
Given two strings `a` and `b`, find the **longest uncommon subsequence** - a subsequence that appears in one string but NOT as a subsequence of the other string.

## Difficulty
Easy

## Examples

### Example 1
- **Input:** `a = "aba"`, `b = "cdc"`
- **Output:** `3`
- **Explanation:** The longest uncommon subsequence is "aba" (or "cdc"), as "aba" is a subsequence of "aba" but not "cdc"

### Example 2
- **Input:** `a = "abc"`, `b = "abc"`
- **Output:** `-1`
- **Explanation:** Both strings are identical, so no uncommon subsequence exists

## Key Insights
1. **String Comparison Key**: If strings have different lengths, the longer string is always an uncommon subsequence (cannot be a subsequence of the shorter one)
2. **Same Length Case**: When strings have the same length, if they're different, each is uncommon. If identical, return -1
3. **Character Analysis**: For same-length strings, compare character frequencies. If any frequency differs, strings differ and are uncommon

## Algorithm Steps
1. Compare string lengths
2. If lengths differ, return the longer length (it's always uncommon)
3. If lengths are equal:
   - Create frequency array for both strings
   - Compare frequencies
   - If any frequency differs, return the common length
   - If all frequencies match, return -1 (strings are identical)

## Complexity Analysis
- **Time Complexity:** O(n) where n = length of strings
- **Space Complexity:** O(1) - Fixed size frequency array (26 letters)

## ASCII Visualization

```
Case 1: Different lengths
a = "aba" (length 3)
b = "cdc" (length 3)
Same length, check characters:
a: a=2, b=1
b: c=2, d=1
Frequencies differ → return 3

Case 2: Different lengths
a = "abc" (length 3)
b = "abcde" (length 5)
Longer string "abcde" is always uncommon → return 5
```

## Edge Cases
1. **Identical strings:** Return -1
2. **Empty string vs non-empty:** Return non-empty length
3. **Single character strings:** If same character, return -1; otherwise return 1
4. **Very different lengths:** Return max length
5. **One character different:** Return length (strings are different)

## Related Problems
- **LeetCode 522:** Longest Uncommon Subsequence II
- **LeetCode 524:** Longest Word in Dictionary through Deleting Letters
- **LeetCode 300:** Longest Increasing Subsequence
- **LeetCode 1143:** Longest Common Subsequence

## Tags
`String` `Logic` `Subsequence`
