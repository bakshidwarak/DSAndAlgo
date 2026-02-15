# 557. Reverse Words in a String III

## Problem Statement
Given a string, reverse the order of characters in each word within a sentence while preserving whitespace and word order. Each word is separated by a single space, with no extra spaces.

## Examples

### Example 1
```
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Explanation: Each word is reversed individually
```

### Example 2
```
Input: "Hello World"
Output: "olleH dlroW"
```

## Key Insights

1. **Character-level manipulation**: Convert string to char array for in-place reversal
2. **Space as delimiter**: Spaces mark boundaries between words
3. **Two-pointer technique**: Use pointers to reverse each word efficiently
4. **In-place operation**: Modify the array without creating new strings for each word

## Algorithm Steps

1. Convert string to character array
2. Maintain a `startIndex` pointer for each word's beginning
3. Iterate through array looking for spaces:
   - When space encountered, reverse from startIndex to current - 1
   - Update startIndex to next position after space
4. After loop, reverse the final word (which has no trailing space)
5. Convert char array back to string

## Complexity Analysis

**Time Complexity:** O(n)
- Single pass through the string
- Each character processed a constant number of times

**Space Complexity:** O(n) or O(1)
- O(n) for char array conversion (if we count output)
- O(1) if we consider output separately (only using pointer variables)

## ASCII Visualization

```
Input String: "Let's take LeetCode contest"

Position:    0  1  2  3  4  5  6  7  8  9 10 11 12...
Chars:       L  e  t  '  s     t  a  k  e     L  e...
                              ^
                         space at index 5

After reversing "Let's" (0-4):
             s  '  t  e  L     t  a  k  e     L  e...

After reversing "take" (6-9):
             s  '  t  e  L     e  k  a  t     L  e...

Continue for remaining words...

Final: "s'teL ekat edoCteeL tsetnoc"
```

## Code Walkthrough

```java
public String reverseWords(String s) {
    // Convert string to char array for in-place modification
    char[] words = s.toCharArray();
    int startIndex = 0;

    // Traverse the entire array
    for (int i = 0; i < words.length; i++) {
        // When we hit a space, reverse the word we just passed
        if (s.charAt(i) == ' ') {
            reverse(words, startIndex, i - 1);
            startIndex = i + 1;  // Next word starts after space
        }
    }

    // Reverse the last word (loop doesn't catch it since no trailing space)
    reverse(words, startIndex, words.length - 1);

    return new String(words);
}

// Helper method to reverse characters between start and end indices
public void reverse(char[] words, int start, int end) {
    while (start < end) {
        // Swap characters at start and end positions
        char tmp = words[start];
        words[start] = words[end];
        words[end] = tmp;
        start++;
        end--;
    }
}
```

## Edge Cases

1. **Single character word**: "a" -> "a"
2. **Single word**: "hello" -> "olleh"
3. **Two character word**: "ab cd" -> "ba dc"
4. **Words with special characters**: "don't" -> "t'nod"
5. **Very long word**: Handles efficiently with O(n) time
6. **All single chars**: "a b c d" -> "a b c d"

## Related Problems

- 151: Reverse Words in a String (reverse word order, not individual words)
- 186: Reverse Words in String II (reverse characters in char array, similar concept)
- 344: Reverse String (reverse entire string)
- 541: Reverse String II (reverse string with specific pattern)

## Tags

`string` `two-pointer` `character-array` `in-place` `easy`
