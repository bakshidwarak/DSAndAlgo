# LeetCode 125: Valid Palindrome

## Problem Statement
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring case.

**Constraints:**
- Only compare alphanumeric characters (a-z, A-Z, 0-9)
- Ignore case (treat uppercase as lowercase)
- Spaces and special characters are ignored
- Empty string is considered valid palindrome

## Examples

### Example 1
- **Input:** `"A man, a plan, a canal: Panama"`
- **Output:** `true`
- **Explanation:** When considering only alphanumeric chars (case-insensitive): "amanaplanacanalpanama" is palindrome

### Example 2
- **Input:** `"race a car"`
- **Output:** `false`
- **Explanation:** Filtered: "raceacar" is not a palindrome (race != racar)

### Example 3
- **Input:** `"0P"`
- **Output:** `false`

## Key Insights

1. **Filtering Approach:** Strip non-alphanumeric characters first
2. **Two-Pointer Technique:** Compare from both ends simultaneously
3. **Case Insensitivity:** Convert to lowercase or uppercase for comparison
4. **Skip Non-Alphanumeric:** Move pointer past invalid characters
5. **Two Approaches:** Iterative (efficient) vs Recursive (elegant)

## Algorithm Steps

### Iterative Two-Pointer Approach (Used in Code)

```
1. Convert string to lowercase
2. Initialize pointers:
   - start = 0 (left)
   - end = length - 1 (right)
3. While start <= end:
   a. Skip non-alphanumeric at start
   b. Skip non-alphanumeric at end
   c. If both start and end point to non-alphanumeric:
      - Move both pointers
   d. If only start is non-alphanumeric:
      - Move start pointer
   e. If only end is non-alphanumeric:
      - Move end pointer
   f. If characters don't match:
      - Return false
   g. If characters match:
      - Move both pointers
4. Return true (all characters matched)
```

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(1) - Only using pointers (or O(n) for lowercase string creation)

## ASCII Visualization

```
Input: "A man, a plan, a canal: Panama"

Lowercase: "a man, a plan, a canal: panama"

Two-Pointer Comparison (only alphanumeric):
  start=0  end=30
  "a man, a plan, a canal: panama"
   ^                              ^
  'a' == 'a' ✓ (skip non-alphanumeric)

  start=1  end=29
  "a man, a plan, a canal: panama"
     ^                           ^
  'm' == 'm' ✓

  start=2  end=28
  "a man, a plan, a canal: panama"
      ^                          ^
  'a' == 'a' ✓

  ... continuing for all alphanumeric pairs...

  Eventually start > end, all matched ✓
  Result: TRUE

---

Input: "race a car"

Lowercase: "race a car"

Two-Pointer:
  start=0  end=9
  "race a car"
   ^         ^
  'r' == 'r' ✓

  start=1  end=8
  "race a car"
    ^        ^
  'a' == 'a' ✓

  start=2  end=7
  "race a car"
     ^       ^
  'c' != 'r' ✗

  Result: FALSE
```

## Code Walkthrough

```java
public boolean isPalindrome(String s) {
    if (s == null || s.isEmpty())
        return true;

    String input = s.toLowerCase();

    // Two-pointer approach
    for (int start = 0, end = input.length() - 1; start <= end;) {
        // Skip non-alphanumeric at both ends
        if (isNotAChar(input.charAt(start)) && isNotAChar(input.charAt(end))) {
            start++;
            end--;
        } else if (isNotAChar(input.charAt(start))) {
            start++;
        } else if (isNotAChar(input.charAt(end))) {
            end--;
        } else if (input.charAt(start) == input.charAt(end)) {
            start++;
            end--;
        } else {
            return false;  // Characters don't match
        }
    }
    return true;
}

// Helper: Check if character is not alphanumeric
public boolean isNotAChar(char ch) {
    return (ch < 'a' || ch > 'z') && (ch < '0' || ch > '9');
}
```

**Execution Flow:**
1. Handle null/empty cases
2. Convert to lowercase
3. Use two pointers from opposite ends
4. Skip non-alphanumeric characters
5. Compare alphanumeric characters
6. Return false if mismatch, true if all match

## Recursive Approach (Alternative)

```java
public boolean isPali(String input, int start, int end) {
    if (start >= end) {
        return true;  // Base case: all checked
    }

    // Skip non-alphanumeric from start
    if (isNotAChar(input.charAt(start))) {
        return isPali(input, start + 1, end);
    }

    // Skip non-alphanumeric from end
    if (isNotAChar(input.charAt(end))) {
        return isPali(input, start, end - 1);
    }

    // Compare characters
    if (input.charAt(start) == input.charAt(end)) {
        return isPali(input, start + 1, end - 1);
    }

    return false;
}
```

## Edge Cases

1. **Empty String:** Should return true
   - `""` → true

2. **Single Character:** Always palindrome
   - `"a"` → true
   - `"0"` → true

3. **Only Spaces/Special:** Should return true (no alphanumeric to compare)
   - `" "` → true
   - `".,'"` → true

4. **Mixed Case:** Should be case-insensitive
   - `"Aa"` → true
   - `"AaBb"` → true

5. **With Numbers:** Should include digits in comparison
   - `"a1b1a"` → true
   - `"a1b2a"` → false

6. **Long Palindrome:** Should handle large strings
   - Multiple spaces/punctuation mixed in

### Example Edge Cases:
```
Input: "", Output: true
Input: "a", Output: true
Input: " ", Output: true
Input: "a.", Output: true
Input: "A man, a plan, a canal: Panama", Output: true
Input: "race a car", Output: false
```

## Related Problems

1. **LeetCode 9 - Palindrome Number:** Check if number is palindrome
2. **LeetCode 131 - Palindrome Partitioning:** Partition string into palindromes
3. **LeetCode 167 - Two Sum II:** Similar two-pointer technique
4. **LeetCode 680 - Valid Palindrome II:** Allow one character deletion
5. **LeetCode 234 - Palindrome Linked List:** Palindrome in linked list

## Tags

`#String` `#Two-Pointers` `#Easy`

## Key Takeaways

- Two-pointer technique is efficient for palindrome checking
- Must handle non-alphanumeric characters properly
- Case-insensitive means convert before comparison
- Empty string is valid palindrome by definition
- Recursive approach is elegant but iterative is more efficient
- Helper function to check alphanumeric makes code cleaner
