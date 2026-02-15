# LeetCode 242: Valid Anagram

## Problem Statement
Given two strings `s` and `t`, determine if `t` is an anagram of `s`.

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Constraints:**
- Strings contain only lowercase alphabets (in base problem)
- Can contain spaces and special characters (in follow-up)
- Return true if `t` is an anagram of `s`, false otherwise

## Examples

### Example 1
- **Input:** `s = "anagram"`, `t = "nagaram"`
- **Output:** `true`
- **Explanation:** Both strings have same characters with same frequencies

### Example 2
- **Input:** `s = "rat"`, `t = "car"`
- **Output:** `false`
- **Explanation:** Different characters ('t' vs 'c')

### Example 3
- **Input:** `s = "listen"`, `t = "silent"`
- **Output:** `true`

## Key Insights

1. **Character Frequency Match:** Two strings are anagrams if they have identical character frequency counts
2. **Multiple Approaches:** Sorting vs Character Count Array vs HashMap
3. **Constant Space Option:** For lowercase ASCII (26 chars), use fixed-size array
4. **Character Frequency Must Match:** Different length strings cannot be anagrams
5. **Order Doesn't Matter:** Only character counts matter

## Algorithm Steps

### Approach 1: Character Count Array (Used in Code)

```
1. Create frequency array of size 256 (ASCII characters)
   or size 26 (lowercase only)
2. Iterate through string s:
   - Increment count for each character
3. Iterate through string t:
   - Decrement count for each character
4. Check all counts are zero:
   - If any count != 0, strings are not anagrams
   - If all counts == 0, strings are anagrams
```

### Approach 2: Sort and Compare
```
1. Sort both strings
2. Compare sorted strings
3. If equal, they are anagrams
```

### Approach 3: HashMap
```
1. Create frequency map from string s
2. For each character in string t:
   - Decrement count in map
3. Check all counts are zero
```

## Complexity Analysis

### Approach 1: Character Count Array
- **Time Complexity:** O(n) - Single pass through both strings
- **Space Complexity:** O(1) - Fixed array of 256 (constant space for ASCII)

### Approach 2: Sorting
- **Time Complexity:** O(n log n) - Sorting dominates
- **Space Complexity:** O(1) - With extra space, or O(n) - If sorting counts as extra

### Approach 3: HashMap
- **Time Complexity:** O(n) - Single pass through both strings
- **Space Complexity:** O(1) - At most 26 lowercase characters

## ASCII Visualization

```
String s = "anagram"
String t = "nagaram"

Step 1: Count characters in s
chars array (only showing non-zero):
  a: 3
  n: 1
  g: 1
  r: 1
  m: 1

Step 2: Decrement for characters in t
After processing "nagaram":
  a: 3 - 2 = 1, 1 - 1 = 0
  n: 1 - 1 = 0
  g: 1 - 1 = 0
  a: (already processed)
  r: 1 - 1 = 0
  a: (already processed)
  m: 1 - 1 = 0

Final check: All counts are 0 ✓
Result: TRUE (anagrams)

---

String s = "rat"
String t = "car"

Step 1: Count characters in s
  r: 1
  a: 1
  t: 1

Step 2: Decrement for characters in t
  c: 0 - 1 = -1 (character 'c' not in s!)
  Result: Found non-zero count

Result: FALSE (not anagrams)
```

## Code Walkthrough

```java
public boolean isAnagram(String s, String t) {
    int[] chars = new int[256];  // ASCII character frequencies

    // Count characters in string s
    for (int i = 0; i < s.length(); i++) {
        chars[s.charAt(i) - 'a']++;  // Increment frequency
    }

    // Decrement for characters in string t
    for (int i = 0; i < t.length(); i++) {
        chars[t.charAt(i) - 'a']--;  // Decrement frequency
    }

    // Check if all frequencies are zero
    for (int i = 0; i < chars.length; i++) {
        if (chars[i] != 0)
            return false;  // Found character with non-zero count
    }

    return true;  // All characters match exactly
}
```

**Execution Flow:**
1. Create array to track character frequencies
2. Build up counts from string s
3. Subtract counts from string t
4. If any character count is non-zero, not an anagram
5. If all counts are zero, strings are anagrams

## Edge Cases

1. **Different Lengths:** Strings of different lengths cannot be anagrams
   - `s = "abc"`, `t = "abcd"` → `false`

2. **Empty Strings:** Both empty strings are anagrams
   - `s = ""`, `t = ""` → `true`

3. **One Empty:** Empty and non-empty are not anagrams
   - `s = "a"`, `t = ""` → `false`

4. **Identical Strings:** String with itself is an anagram
   - `s = "hello"`, `t = "hello"` → `true`

5. **Single Character:** Single char appears multiple times
   - `s = "aa"`, `t = "aa"` → `true`
   - `s = "aa"`, `t = "a"` → `false`

6. **Repeated Characters:** Must match frequencies exactly
   - `s = "aab"`, `t = "aba"` → `true`
   - `s = "aab"`, `t = "bba"` → `false`

## Follow-up: Unicode Characters

For Unicode input:
```java
// Use HashMap instead of fixed array
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length())
        return false;

    Map<Character, Integer> charCount = new HashMap<>();

    for (char c : s.toCharArray()) {
        charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }

    for (char c : t.toCharArray()) {
        if (!charCount.containsKey(c))
            return false;
        charCount.put(c, charCount.get(c) - 1);
        if (charCount.get(c) < 0)
            return false;
    }

    return true;
}
```

## Related Problems

1. **LeetCode 438 - Find All Anagrams in String:** Find anagrams as substrings
2. **LeetCode 49 - Group Anagrams:** Group strings by anagrams
3. **LeetCode 383 - Ransom Note:** Check if characters available for substring
4. **LeetCode 409 - Longest Palindrome:** Count character frequencies
5. **LeetCode 451 - Sort Characters by Frequency:** Count and sort by frequency

## Tags

`#String` `#Hash-Table` `#Sorting` `#Counting` `#Easy`

## Key Takeaways

- Anagrams have identical character frequency distributions
- Character count array is O(1) space for fixed character sets
- Sorting approach is simpler but O(n log n) time
- Different lengths immediately mean not anagrams
- For Unicode, use HashMap instead of fixed array
