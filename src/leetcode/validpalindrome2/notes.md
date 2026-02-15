# LeetCode 680: Valid Palindrome II

## Problem Statement
Given a non-empty string `s`, you may delete at most one character. Determine whether you can make it a palindrome.

**Constraints:**
- String contains only lowercase characters a-z
- Maximum length: 50,000
- Can delete at most one character
- Return true if can form palindrome with at most one deletion

## Examples

### Example 1
- **Input:** `s = "aba"`
- **Output:** `true`
- **Explanation:** String is already palindrome (no deletion needed)

### Example 2
- **Input:** `s = "abca"`
- **Output:** `true`
- **Explanation:** Can delete 'c' to get "aba" which is palindrome

### Example 3
- **Input:** `s = "abc"`
- **Output:** `false`
- **Explanation:** Cannot form palindrome even with one deletion

## Key Insights

1. **Two-Pointer from Ends:** Check palindrome while moving inward
2. **First Mismatch:** When characters don't match, try skipping either one
3. **Two Attempts:** Try deleting left character or right character
4. **Recursive Checking:** Helper function validates remaining substring
5. **Greedy Approach:** Try both options when mismatch found

## Algorithm Steps

### Two-Pointer with One Deletion Attempt

```
1. Initialize pointers:
   - i = 0 (left)
   - j = length - 1 (right)
2. While i <= j:
   - If s[i] == s[j]:
     - Move both pointers inward
   - Else (mismatch found):
     - Try skipping left: check if s[i+1..j] is palindrome
     - Try skipping right: check if s[i..j-1] is palindrome
     - Return true if either is palindrome
3. If no mismatch found, return true (already palindrome)
```

## Complexity Analysis

- **Time Complexity:** O(n) - At most two passes: O(n) for main check + O(n) for helper
- **Space Complexity:** O(1) - Only pointers, no extra data structures

## ASCII Visualization

```
Example 1: s = "abca"

Initial Two-Pointer:
  i=0      j=3
  "abca"
   ^        ^
  'a' == 'a' ✓ move inward

  i=1      j=2
  "abca"
    ^      ^
  'b' != 'c' ✗ MISMATCH!

  Try skipping left (s[i+1..j] = "ca"):
    i=2      j=2
    "abca"
      ^
    Single character, palindrome ✓
    Return TRUE

---

Example 2: s = "abc"

Initial Two-Pointer:
  i=0    j=2
  "abc"
   ^      ^
  'a' != 'c' ✗ MISMATCH!

  Try skipping left (s[i+1..j] = "bc"):
    i=1    j=2
    "bc"
     ^     ^
    'b' != 'c' ✗ NOT palindrome

  Try skipping right (s[i..j-1] = "ab"):
    i=0    j=1
    "ab"
     ^     ^
    'a' != 'b' ✗ NOT palindrome

  Neither option works
  Return FALSE

---

Example 3: s = "abc" (already palindrome scenario)

Input: s = "aba"

  i=0      j=2
  "aba"
   ^        ^
  'a' == 'a' ✓ move inward

  i=1      j=1
  "aba"
    ^
  i > j, exit loop
  Return TRUE (no mismatch, already palindrome)
```

## Code Walkthrough

```java
public boolean validPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;

    // Find first mismatch
    while (i <= j && s.charAt(i) == s.charAt(j)) {
        i++;
        j--;
    }

    // If no mismatch found, already palindrome
    if (i > j)
        return true;

    // At mismatch, try deleting either character
    // Option 1: Delete left character (skip it)
    // Option 2: Delete right character (skip it)
    if (isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1))
        return true;

    return false;
}

// Helper: Check if substring s[i..j] is palindrome
public boolean isPalindrome(String s, int i, int j) {
    while (i <= j) {
        if (s.charAt(i) != s.charAt(j))
            return false;
        i++;
        j--;
    }
    return true;
}
```

**Execution Flow:**
1. Use two-pointer approach from ends
2. Find first position where characters don't match
3. If no mismatch, string is already palindrome
4. If mismatch found, try two recovery strategies:
   - Check if remaining string (excluding left char) is palindrome
   - Check if remaining string (excluding right char) is palindrome
5. Return true if either option works

## Edge Cases

1. **Already Palindrome:** No deletion needed
   - `"aba"` → true
   - `"abba"` → true

2. **Single Character:** Always palindrome
   - `"a"` → true

3. **Two Characters:** Palindrome or deleting one makes it palindrome
   - `"ab"` → true (delete either 'a' or 'b')
   - `"aa"` → true (already palindrome)

4. **Last Character Different:** Deletion at end works
   - `"abab"` → true (delete last 'b')

5. **First Character Different:** Deletion at start works
   - `"abaa"` → true (delete first 'a')

6. **Multiple Mismatches:** If first char and last char match but middle fails, still needs one deletion
   - `"aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupclmgmqfvnbgtapekouga"` → true

7. **Impossible Cases:** Cannot form palindrome even with deletion
   - `"abc"` → false
   - `"abcd"` → false

### Example Edge Cases:
```
Input: "aba", Output: true
Input: "abca", Output: true
Input: "abc", Output: false
Input: "a", Output: true
Input: "ab", Output: true
Input: "aab", Output: true (delete 'a' at position 0)
```

## Related Problems

1. **LeetCode 125 - Valid Palindrome:** Check palindrome without deletion
2. **LeetCode 131 - Palindrome Partitioning:** Partition into palindromes
3. **LeetCode 516 - Longest Palindromic Subsequence:** DP with palindromes
4. **LeetCode 9 - Palindrome Number:** Number palindrome check
5. **LeetCode 234 - Palindrome Linked List:** Palindrome in linked list

## Alternative Approach: Dynamic Programming

```java
// Can also solve with DP but two-pointer is more efficient
public boolean validPalindrome(String s) {
    // Length check
    if (s.length() <= 2) return true;

    // Check with at most one deletion using recursion + memoization
    // or analyze directly with two pointers (current approach is best)

    // Current greedy approach with two attempts is optimal
}
```

## Tags

`#String` `#Two-Pointers` `#Greedy` `#Easy`

## Key Takeaways

- Use two-pointer approach to find mismatch efficiently
- When mismatch found, try deleting either character
- Helper function validates if substring is pure palindrome
- Time complexity O(n) despite two passes (worst case)
- Greedy approach works: only need to consider first mismatch
- Can make at most one deletion
