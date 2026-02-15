# LeetCode 159: Longest Substring with At Most Two Distinct Characters

## Problem Statement
Given a string `s`, find the length of the **longest substring** `t` that contains **at most 2 distinct characters**.

## Difficulty
Hard

## Examples

### Example 1
- **Input:** `"eceba"`
- **Output:** `3`
- **Explanation:** `t` is `"ece"` which has length 3 (contains 'e' and 'c')

### Example 2
- **Input:** `"ccaabbb"`
- **Output:** `5`
- **Explanation:** `t` is `"aabbb"` which has length 5 (contains 'a' and 'b')

## Key Insights
1. **Sliding Window Pattern**: Use a two-pointer approach to maintain a window of valid characters
2. **HashSet Tracking**: Track distinct characters within the current window
3. **Character Removal Strategy**: When adding a new character would exceed the limit of 2 distinct characters, remove the previous character completely from the left side
4. **Maximum Tracking**: Keep track of the maximum length substring found

## Algorithm Steps
1. Handle edge cases (null, empty, or single character strings)
2. Initialize left and right pointers, and a HashSet
3. Expand the right pointer through the string
4. If the current character is already in the set, continue expanding
5. If the set has less than 2 characters, add the new character
6. If the set already has 2 distinct characters, remove the entire previous character from the left
7. Update maximum length at each step
8. Return the maximum length found

## Complexity Analysis
- **Time Complexity:** O(n) - Each character is visited at most twice
- **Space Complexity:** O(1) - At most 2 distinct characters in HashSet

## Code Walkthrough
The algorithm uses a sliding window with two pointers (left and right). When we encounter a third distinct character, we shrink the window from the left by removing all occurrences of the previous character.

## Edge Cases
1. Empty string → Return 0
2. Null string → Return 0
3. Single character → Return 1
4. All same character → Return string length
5. Only 1 distinct character → Return entire string length
6. String with exactly 2 different characters → Return full length

## Related Problems
- **LeetCode 3:** Longest Substring Without Repeating Characters
- **LeetCode 76:** Minimum Window Substring
- **LeetCode 438:** Find All Anagrams in a String
- **LeetCode 567:** Permutation in String
- **LeetCode 1004:** Max Consecutive Ones III

## Tags
`Sliding Window` `String` `Hash Table` `Two Pointers`
