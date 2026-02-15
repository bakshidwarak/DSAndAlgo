# LeetCode 9: Palindrome Number

## Problem Statement
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

**Follow-up**: Could you solve it without converting the integer to a string?

## Examples

**Example 1:**
```
Input: 121
Output: true
```

**Example 2:**
```
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, 121-.
Therefore it is not a palindrome.
```

**Example 3:**
```
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

## Key Insights

1. **Negative Numbers**: Always false (negative sign not symmetric)
2. **Trailing Zeros**: Numbers ending in 0 can't be palindromes (except 0 itself)
3. **Digit Reversal**: Reverse the number and compare with original
4. **No String Conversion**: Use modulo and division to extract digits

## Algorithm Steps

1. **Special Cases**:
   - If x < 0: return false (negative)
   - If x % 10 == 0 and x != 0: return false (trailing zero)

2. **Reverse Number**:
   - Extract last digit using x % 10
   - Build reversed number: reversed = reversed * 10 + digit
   - Remove last digit: x = x / 10

3. **Compare**:
   - If reversed == original: palindrome
   - If reversed != original: not palindrome

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(log x) - Process each digit |
| **Space Complexity** | O(1) - Only a few variables |

- Number of digits = log₁₀(x)
- Process one digit per iteration

## ASCII Visualization

```
Input: 121

Extract digits and reverse:
original: 121

Iteration 1: x=121, digit=1 (121%10)
  reversed = 0*10 + 1 = 1
  x = 121/10 = 12

Iteration 2: x=12, digit=2 (12%10)
  reversed = 1*10 + 2 = 12
  x = 12/10 = 1

Iteration 3: x=1, digit=1 (1%10)
  reversed = 12*10 + 1 = 121
  x = 1/10 = 0

x = 0, loop ends
reversed = 121
121 == 121 → Palindrome!

Input: -121

-121 < 0 → return false immediately

Input: 10

10 % 10 = 0 and 10 != 0 → return false immediately
(Can't be palindrome with trailing zero)

Input: 1001

Iteration 1: x=1001, digit=1
  reversed = 1
  x = 100

Iteration 2: x=100, digit=0
  reversed = 10
  x = 10

Iteration 3: x=10, digit=0
  reversed = 100
  x = 1

Iteration 4: x=1, digit=1
  reversed = 1001
  x = 0

1001 == 1001 → Palindrome!
```

## Code Walkthrough

```java
public boolean isPalindrome(int x) {
    // Negative numbers are not palindromes
    if (x < 0)
        return false;

    int current = x;
    int reverseNum = 0;

    // Extract digits and reverse
    while (current != 0) {
        int last = current % 10;  // Get last digit
        reverseNum = reverseNum * 10 + last;  // Add to reversed
        current = current / 10;  // Remove last digit
    }

    // Compare original with reversed
    return x == reverseNum;
}
```

**Digit Extraction Mechanism:**
```
For number 123:
  123 % 10 = 3  (last digit)
  123 / 10 = 12 (remove last digit)
  12 % 10 = 2
  12 / 10 = 1
  1 % 10 = 1
  1 / 10 = 0   (stop)

Reversed:
  0*10 + 3 = 3
  3*10 + 2 = 32
  32*10 + 1 = 321

Result: 321 (reversed)
```

## Edge Cases

1. **Zero**: `0` → true (reads same both ways)
2. **Single digit**: `5` → true
3. **Negative**: `-121` → false (has negative sign)
4. **Trailing zeros**: `100` → false
5. **Large numbers**: Works for any 32-bit integer
6. **Powers of 10**: `1000` → false
7. **Repdigits**: `1111` → true

## Related Problems

1. [Valid Palindrome](../validpalindrome/notes.md)
2. [Palindrome Linked List](../palindromelinkedlist/notes.md)
3. [Longest Palindromic Subsequence](../longestpalindromicsubsequence/notes.md)
4. **LeetCode 5** - Longest Palindromic Substring (String expansion)
5. **LeetCode 131** - Palindrome Partitioning (DP/Backtracking)

## Tags

`Math` `Two Pointers` `Easy` `Google` `Amazon` `Microsoft` `Apple` `Facebook`

## Alternative Approaches

### Approach 2: String Conversion (Simpler, Not Optimal)
```java
public boolean isPalindromeString(int x) {
    String s = String.valueOf(x);
    String reversed = new StringBuilder(s).reverse().toString();
    return s.equals(reversed);
}
```
- Time: O(log x)
- Space: O(log x) for strings
- Simpler but uses extra space

### Approach 3: Two-Pointer String Method
```java
public boolean isPalindromeTwo(int x) {
    String s = String.valueOf(x);
    int left = 0, right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left) != s.charAt(right))
            return false;
        left++;
        right--;
    }
    return true;
}
```

## Implementation Notes

1. **Early Exit**: Check for negative and trailing zeros immediately
2. **Modulo 10**: Extracts last digit reliably
3. **Integer Division**: x / 10 removes last digit
4. **Order Independence**: Process digits from right to left

## Optimization Notes

1. **Short Circuit**: Exit immediately for negative numbers
2. **Overflow Prevention**: Use long for large reverses if needed (but input is 32-bit int)
3. **Early Termination**: Stop reversing at halfway point (possible optimization)

## Common Mistakes

1. **Negative Check**: Must handle negative numbers first
2. **Trailing Zero**: Numbers like 10, 100, 1000 are never palindromes
3. **Overflow**: When reversing large numbers (less critical for 32-bit ints)
4. **Integer Division**: Must use `/` not `//` (Java) for digit removal

## Performance Comparison

```
String Method:     O(log x) time, O(log x) space
Digit Reversal:    O(log x) time, O(1) space ✓
Two-Pointer:       O(log x) time, O(log x) space
```

## Notes

- Classic easy interview question
- Tests understanding of number manipulation
- Demonstrates importance of avoiding string conversion when not needed
- Good foundation for number-based algorithms
- Simple but requires attention to edge cases
