# 186. Reverse Words in String II

## Problem Statement
Given a character array representing a sentence, reverse the order of characters in each word within a sentence while preserving word order and whitespace.

**Important:** This problem assumes the input is a mutable character array.

## Examples

### Example 1
```
Input: s = ['H','e','l','l','o',' ','W','o','r','l','d']
Output: ['o','l','l','e','H',' ','d','l','r','o','W']
Explanation: Each word is reversed in place
```

### Example 2
```
Input: s = ['C', 'a', 'n', 'a', 'd', 'a']
Output: ['a', 'd', 'a', 'n', 'a', 'C']
Explanation: Single word is reversed
```

## Key Insights
1. **In-place operation**: Since we're working with character arrays, we can reverse in-place without extra space
2. **Two-pointer technique**: For each word, use two pointers from start and end to swap characters
3. **Word boundary detection**: Spaces mark word boundaries; reverse between spaces
4. **O(n) space complexity**: Only swap operations, no auxiliary data structure needed

## Algorithm Steps

1. Iterate through the character array
2. When a space is encountered:
   - Reverse characters from startIndex to current position - 1
   - Update startIndex to current position + 1
3. After loop ends, reverse the last word (no trailing space)
4. Return the modified array

## Complexity Analysis

**Time Complexity:** O(n)
- Single pass through array plus reversing operations
- Each character is visited at most twice

**Space Complexity:** O(1)
- Only using pointers and swap variables
- No additional data structures

## ASCII Visualization

```
Initial: H e l l o   W o r l d
         0 1 2 3 4 5 6 7 8 9 10

After reversing "Hello":
         o l l e H   W o r l d

After reversing "World":
         o l l e H   d l r o W
```

## Code Walkthrough

```java
public String reverseWords(String s) {
    // Convert to char array for in-place manipulation
    char[] words = s.toCharArray();
    int startIndex = 0;

    // Iterate through the string
    for (int i = 0; i < words.length; i++) {
        // When space found, reverse the word before it
        if (s.charAt(i) == ' ') {
            reverse(words, startIndex, i - 1);
            startIndex = i + 1;  // Next word starts after space
        }
    }

    // Don't forget to reverse the last word
    reverse(words, startIndex, words.length - 1);

    return new String(words);
}

// Helper: Reverse characters in range [start, end]
public void reverse(char[] words, int start, int end) {
    while (start < end) {
        char tmp = words[start];
        words[start] = words[end];
        words[end] = tmp;
        start++;
        end--;
    }
}
```

## Edge Cases

1. **Single word**: "hello" -> "olleh"
2. **Single character**: "a" -> "a"
3. **Words with single char**: "a b c" -> "a b c"
4. **Multiple spaces**: Not mentioned in problem, but should handle gracefully
5. **Empty string**: "" -> ""

## Related Problems

- 151: Reverse Words in a String (reverse word order, not character order within words)
- 557: Reverse Words in a String III (reverse words in string with space delimiter)
- 344: Reverse String (reverse entire string)
- 186: Reverse Words in String II (this problem)
## Tags

`string` `two-pointer` `in-place` `array` `character-manipulation`
