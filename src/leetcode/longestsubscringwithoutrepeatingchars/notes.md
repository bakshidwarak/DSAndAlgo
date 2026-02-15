# Longest Substring Without Repeating Characters

## Problem Statement
**LeetCode Problem 3**: Longest Substring Without Repeating Characters (Medium)

Given a string, find the length of the longest substring without repeating characters.

### Examples
**Example 1:**
```
Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```

**Example 2:**
```
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3:**
```
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

## Key Insights
1. **Sliding Window**: Use two pointers to maintain a window of unique characters
2. **HashMap/Array**: Track last seen index of each character
3. **Window Adjustment**: When duplicate found, move start past the duplicate
4. **Substring vs Subsequence**: Must be contiguous characters
5. **Track Maximum**: Keep track of longest window seen

## Algorithm Steps
```
1. Create array to store last seen index of each character
2. Initialize:
   - maxLength = 0
   - last = 0 (start of current window)
3. Iterate through string (i = 0 to n-1):
   a. If character not seen in current window (chars[ch] == -1):
      - Add character to window
      - Update last seen index
   b. Else (character seen in current window):
      - Calculate length of current window
      - Update maxLength if needed
      - Move window start past the duplicate:
        last = max(last, chars[ch] + 1)
      - Update last seen index of character
4. Final check: calculate length of last window
5. Return maxLength
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - Single pass through string
  - Each character processed at most twice
- **Space Complexity**: O(min(m, n))
  - m = charset size (128 for ASCII, 256 for extended ASCII)
  - n = length of string
  - Array of fixed size

## Visual Representation

### Example 1: "abcabcbb"
```
String:  a  b  c  a  b  c  b  b
Index:   0  1  2  3  4  5  6  7

Step-by-step with sliding window:

i=0, ch='a': not seen, chars[a]=0, last=0
  Window: [a]
  maxLength=0

i=1, ch='b': not seen, chars[b]=1, last=0
  Window: [a,b]
  maxLength=0

i=2, ch='c': not seen, chars[c]=2, last=0
  Window: [a,b,c]
  maxLength=0

i=3, ch='a': seen at index 0!
  Current window length = 3-0 = 3
  maxLength = max(0, 3) = 3
  Move window: last = max(0, 0+1) = 1
  Window: [b,c,a]
  chars[a]=3

i=4, ch='b': seen at index 1!
  Current window length = 4-1 = 3
  maxLength = max(3, 3) = 3
  Move window: last = max(1, 1+1) = 2
  Window: [c,a,b]
  chars[b]=4

i=5, ch='c': seen at index 2!
  Current window length = 5-2 = 3
  maxLength = max(3, 3) = 3
  Move window: last = max(2, 2+1) = 3
  Window: [a,b,c]
  chars[c]=5

i=6, ch='b': seen at index 4!
  Current window length = 6-3 = 3
  maxLength = max(3, 3) = 3
  Move window: last = max(3, 4+1) = 5
  Window: [c,b]
  chars[b]=6

i=7, ch='b': seen at index 6!
  Current window length = 7-5 = 2
  maxLength = max(3, 2) = 3
  Move window: last = max(5, 6+1) = 7
  Window: [b]
  chars[b]=7

Final check: length = 8-7 = 1
maxLength = max(3, 1) = 3

Answer: 3 (substring "abc")
```

### Example 2: "pwwkew"
```
String:  p  w  w  k  e  w
Index:   0  1  2  3  4  5

i=0, ch='p': Window: [p], last=0

i=1, ch='w': Window: [p,w], last=0

i=2, ch='w': duplicate! seen at index 1
  Window length = 2-0 = 2
  maxLength = 2
  Move last to max(0, 1+1) = 2
  Window: [w]

i=3, ch='k': Window: [w,k], last=2

i=4, ch='e': Window: [w,k,e], last=2

i=5, ch='w': duplicate! seen at index 2
  Window length = 5-2 = 3
  maxLength = max(2, 3) = 3
  Move last to max(2, 2+1) = 3
  Window: [k,e,w]

Final: 6-3 = 3
Answer: 3 (substring "wke" or "kew")
```

## Code Walkthrough

### Current Implementation
```java
public int lengthOfLongestSubstring(String s) {
    // Array to store last seen index of each character
    // Using 128 for ASCII characters
    int[] chars = new int[128];
    Arrays.fill(chars, -1);  // -1 means not seen

    int maxLength = 0;
    int i = 0;
    int last = 0;  // Start of current window

    for (; i < s.length(); i++) {
        // If character not in current window
        if (chars[s.charAt(i)] == -1) {
            chars[s.charAt(i)] = i;
            last = i;
        } else {
            // Character found in window (duplicate!)
            // Calculate length of current window
            int le = i - last;
            maxLength = Math.max(maxLength, le);

            // Move window start past the duplicate
            // The key insight: only move forward, never backward
            if (last < chars[s.charAt(i)] + 1)
                last = chars[s.charAt(i)] + 1;

            // Update last seen index
            chars[s.charAt(i)] = i;
        }
    }

    // Final window length check
    int len = i - last;
    maxLength = Math.max(maxLength, len);

    return maxLength;
}
```

### Alternative: Cleaner Sliding Window
```java
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int maxLen = 0;
    int start = 0;

    for (int end = 0; end < s.length(); end++) {
        char c = s.charAt(end);

        // If character seen and is in current window
        if (map.containsKey(c) && map.get(c) >= start) {
            start = map.get(c) + 1;  // Move start past duplicate
        }

        map.put(c, end);  // Update last seen index
        maxLen = Math.max(maxLen, end - start + 1);
    }

    return maxLen;
}
```

### Alternative: Using HashSet
```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();
    int maxLen = 0;
    int left = 0;
    int right = 0;

    while (right < s.length()) {
        if (!set.contains(s.charAt(right))) {
            set.add(s.charAt(right));
            right++;
            maxLen = Math.max(maxLen, right - left);
        } else {
            set.remove(s.charAt(left));
            left++;
        }
    }

    return maxLen;
}
```

## Edge Cases
1. **Empty string**: Return 0
2. **Single character**: Return 1
3. **All unique characters**: Return length of string
4. **All same characters**: Return 1
5. **Two characters alternating**: Return 2
6. **Duplicates at different positions**: Handle correctly

### Edge Case Examples
```
Input: ""
Output: 0

Input: "a"
Output: 1

Input: "abcdef"
Output: 6 (entire string)

Input: "aaaaaa"
Output: 1

Input: "abba"
Output: 2 ("ab" or "ba")

Input: "dvdf"
Output: 3 ("vdf")

Input: "tmmzuxt"
Output: 5 ("mzuxt")
```

## Common Mistakes
1. **Not updating `last` correctly**: Must use max to avoid moving backward
2. **Forgetting final length check**: String might end with longest substring
3. **Off-by-one errors**: Window length calculation
4. **Not handling empty string**: Check for null/empty input
5. **Using wrong data structure**: Array faster than HashMap for ASCII

## Why `last < chars[s.charAt(i)] + 1` Check?

```
Example: "abba"
         0123

At i=3, ch='a':
  chars['a'] = 0
  Current last = 2 (after seeing second 'b')

  chars['a'] + 1 = 0 + 1 = 1
  But last = 2 > 1

  We should NOT move last backward to 1!
  Window should remain at "ba", not expand back to include first 'a'

  This is why we use: last = max(last, chars[ch] + 1)
```

## Related Problems
- **Longest Substring with At Most Two Distinct Characters (LeetCode 159)**: Similar window
- **Longest Substring with At Most K Distinct Characters (LeetCode 340)**: Generalized version
- **Minimum Window Substring (LeetCode 76)**: Reverse problem
- **Longest Repeating Character Replacement (LeetCode 424)**: With replacements allowed
- **Fruit Into Baskets (LeetCode 904)**: Disguised version of this problem

## Tags
- String
- Hash Table
- Sliding Window
- Two Pointers
- Medium
- Top Interview Question
- Amazon
- Bloomberg
- Microsoft
