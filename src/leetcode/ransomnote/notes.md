# Ransom Note - LeetCode Problem 383

## Problem Statement
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Constraints:
- Both strings contain only lowercase letters
- Case sensitive comparison

## Examples

**Example 1:**
- Input: ransomNote = "a", magazine = "b"
- Output: false

**Example 2:**
- Input: ransomNote = "aa", magazine = "ab"
- Output: false

**Example 3:**
- Input: ransomNote = "aa", magazine = "aab"
- Output: true

**Example 4:**
- Input: ransomNote = "aa", magazine = "baa"
- Output: true

## Key Insights
1. **Character Frequency**: Count occurrences of each character
2. **Sufficient Letters**: For each character in ransom note, magazine must have at least that count
3. **Efficient Check**: Array of size 26 for lowercase letters is optimal
4. **Early Termination**: Can stop early if any character count becomes negative
5. **Hash Map Alternative**: Works for uppercase/mixed case scenarios

## Algorithm Steps

### Approach 1: Character Frequency Array

**Step 1: Count Magazine Letters**
- Create array of size 26 (for 'a' to 'z')
- Increment count for each character in magazine

**Step 2: Subtract Ransom Note Characters**
- For each character in ransom note, decrement count
- If count becomes negative, return false

**Step 3: Success**
- All characters decremented without going negative

**Pseudocode:**
```
function canConstruct(ransomNote, magazine):
    chars = new int[26]

    // Count magazine characters
    for char in magazine:
        chars[char - 'a']++

    // Check ransom note
    for char in ransomNote:
        chars[char - 'a']--
        if chars[char - 'a'] < 0:
            return false

    return true
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(m + n) where m = magazine.length, n = ransomNote.length |
| Space Complexity | O(1) - constant size array of 26 |

**Time Analysis:**
- First loop: O(m) for magazine
- Second loop: O(n) for ransom note
- Total: O(m + n)

**Space Analysis:**
- Fixed array of 26 integers: O(1)
- Independent of input size

## ASCII Visualization

```
Example: ransomNote = "aa", magazine = "aab"

Step 1: Count Magazine Characters
magazine = "aab"
┌───┬───┬───┬───┬───┬───┬───┐
│a=2│b=1│c=0│d=0│e=0│f=0│...│
└───┴───┴───┴───┴───┴───┴───┘

Step 2: Subtract Ransom Note Characters
ransomNote = "aa"

Process 'a':
Before: a=2
After:  a=1

Process 'a':
Before: a=1
After:  a=0

Final state: a=0 (no negative), return true

Example with negative: ransomNote = "aaa", magazine = "aab"

Process 'a': count[a] = 2 → 1
Process 'a': count[a] = 1 → 0
Process 'a': count[a] = 0 → -1 (NEGATIVE!) return false

Character Array Visualization:
┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
│ a  │ b  │ c  │ d  │ e  │ f  │ g  │ h  │ i  │ j  │ k  │ l  │ m  │ n  │ o  │ p  │ q  │ r  │ s  │ t  │ u  │ v  │ w  │ x  │ y  │ z  │
├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
│ 2  │ 1  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │ 0  │
└────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
```

## Code Walkthrough

```java
public boolean canConstruct(String ransomNote, String magazine) {
    // Array to store character frequencies
    int[] chars = new int[26];

    // Count characters in magazine
    for (int i = 0; i < magazine.length(); i++) {
        chars[magazine.charAt(i) - 'a']++;
    }

    // Check if ransom note can be constructed
    for (int i = 0; i < ransomNote.length(); i++) {
        chars[ransomNote.charAt(i) - 'a']--;

        // If any character count becomes negative, impossible
        if (chars[ransomNote.charAt(i) - 'a'] < 0)
            return false;
    }

    // All characters found in sufficient quantity
    return true;
}

// Alternative: Using HashMap for any character set
public boolean canConstructHashMap(String ransomNote, String magazine) {
    HashMap<Character, Integer> freqMap = new HashMap<>();

    // Count magazine characters
    for (char ch : magazine.toCharArray()) {
        freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
    }

    // Check ransom note
    for (char ch : ransomNote.toCharArray()) {
        if (!freqMap.containsKey(ch) || freqMap.get(ch) == 0) {
            return false;
        }
        freqMap.put(ch, freqMap.get(ch) - 1);
    }

    return true;
}

// Alternative: Using Java 8 stream (less efficient)
public boolean canConstructStream(String ransomNote, String magazine) {
    return ransomNote.chars()
            .allMatch(ch -> magazine.length() - magazine.replace(String.valueOf((char)ch), "")
                    >= ransomNote.length() - ransomNote.replace(String.valueOf((char)ch), ""));
}
```

## Edge Cases

1. **Empty Ransom Note**: `"", "abc"` -> true
2. **Empty Magazine**: `"a", ""` -> false
3. **Both Empty**: `"", ""` -> true
4. **Ransom Longer**: `"aab", "ab"` -> false
5. **Magazine Longer**: `"a", "aaaaaaaaa"` -> true
6. **Same Strings**: `"abc", "abc"` -> true
7. **Single Character**: `"a", "a"` -> true
8. **No Match**: `"abc", "def"` -> false
9. **Partial Match**: `"ab", "ba"` -> true
10. **Case Sensitive**: `"A", "a"` -> false

## Related Problems

1. **LeetCode 49**: Group Anagrams - Character frequency grouping
2. **LeetCode 242**: Valid Anagram - Check if anagram using frequency
3. **LeetCode 438**: Find All Anagrams in a String - Frequency window
4. **LeetCode 205**: Isomorphic Strings - Character mapping
5. **LeetCode 290**: Word Pattern - Character pattern matching

## Tags

- Hash Map
- String
- Array
- Character Frequency
- Easy Difficulty
- Acceptance: ~60%
