# Reverse Words in a String - LeetCode Problem 151

## Problem Statement
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces. You need to reduce multiple spaces between two words to a single space in the reversed string.

## Examples

**Example 1:**
- Input: s = "the sky is blue"
- Output: "blue is sky the"

**Example 2:**
- Input: s = "  hello world  "
- Output: "world hello"

**Example 3:**
- Input: s = "a good   example"
- Output: "example a good"

## Key Insights
1. **Multiple Spaces**: Handle consecutive spaces by using regex split
2. **Trim First**: Remove leading/trailing spaces before processing
3. **Two Pointer Swap**: Can swap words without creating new array
4. **Word Order**: Reverse order of words, not characters within words
5. **Space Normalization**: Reduce multiple spaces to single space

## Algorithm Steps

### Approach: Split and Reverse

**Step 1: Trim and Split**
- Trim whitespace: s.trim()
- Split by regex \\s+ (one or more spaces)
- Result: array of non-empty words

**Step 2: Reverse Order**
- Use two pointers: first and end
- Swap words at positions, move pointers inward
- Continue until pointers meet

**Step 3: Build Result**
- Join reversed words with single space

**Step 4: Return**
- Return the result string

**Pseudocode:**
```
function reverseWords(s):
    words = s.trim().split("\\s+")

    first = 0
    end = words.length - 1

    while first < end:
        swap(words, first, end)
        first++
        end--

    return join(words, " ")
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = string length |
| Space Complexity | O(n) for split array and result |

**Time Analysis:**
- Trim: O(n)
- Split: O(n)
- Reverse: O(n/2) = O(n)
- Join: O(n)
- Total: O(n)

**Space Analysis:**
- Split array: O(n/2) average (words)
- Result string: O(n)

## ASCII Visualization

```
Example: "the sky is blue"

Step 1: Trim (no leading/trailing spaces)
"the sky is blue"

Step 2: Split by \\s+
["the", "sky", "is", "blue"]
 0      1      2    3

Step 3: Reverse using two pointers
Initial:
["the", "sky", "is", "blue"]
 ↑                  ↑
first=0            end=3

Iteration 1:
Swap "the" and "blue"
["blue", "sky", "is", "the"]
 ↑                    ↑
first=1              end=2

Iteration 2:
Swap "sky" and "is"
["blue", "is", "sky", "the"]
 ↑             ↑
first=2        end=1

first >= end, stop

Step 4: Join with space
"blue is sky the"

Example with multiple spaces: "  hello   world  "

Step 1: Trim
"hello   world"

Step 2: Split by \\s+ (handles multiple spaces)
["hello", "world"]
 0        1

Step 3: Reverse
["world", "hello"]

Step 4: Join
"world hello"

Detailed Split Behavior:
Input: "  hello   world  "
\\s+ matches: "  " → skipped, "   " → skipped, "  " → skipped
Words extracted: "hello", "world"

Without trim():
" hello   world "
Split by \\s+ creates: ["", "hello", "world", ""]
With trim() first: "hello world"
Split: ["hello", "world"]

Word Array Visualization:
Original: [the] [sky] [is] [blue]
          0     1     2    3

Process:
Swap 0,3: [blue][sky] [is] [the]
Swap 1,2: [blue][is] [sky][the]
Complete: [blue] [is] [sky] [the]

Output: "blue is sky the"

Pointer Movement:
Initial: first=0, end=3
         ↑              ↑
         L              R

After swap 1: first=1, end=2
         ↑        ↑
         L        R

After swap 2: first=2, end=1
                    ↑  ↑
                    R  L (cross, stop)
```

## Code Walkthrough

```java
public String reverseWords(String s) {
    // Trim leading/trailing spaces and split by multiple spaces
    String[] input = s.trim().split("\\s+");

    int end = input.length - 1;
    int first = 0;

    // Reverse words using two pointers
    while (first < end) {
        String temp = input[end];
        input[end] = input[first];
        input[first] = temp;
        first++;
        end--;
    }

    // Build result string
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < input.length; i++) {
        sb.append(input[i].trim());
        if (i != input.length - 1) {
            sb.append(" ");
        }
    }

    return sb.toString();
}

// Alternative: Using String.join()
public String reverseWordsJoin(String s) {
    String[] words = s.trim().split("\\s+");
    Collections.reverse(Arrays.asList(words));
    return String.join(" ", words);
}

// Alternative: Manual reversal with StringBuilder
public String reverseWordsManual(String s) {
    s = s.trim();
    String[] words = s.split("\\s+");

    // Reverse the array
    for (int i = 0; i < words.length / 2; i++) {
        String temp = words[i];
        words[i] = words[words.length - 1 - i];
        words[words.length - 1 - i] = temp;
    }

    // Join words
    return String.join(" ", words);
}

// In-place character reversal approach
public String reverseWordsInPlace(String s) {
    char[] chars = s.toCharArray();

    // Step 1: Reverse entire string
    reverse(chars, 0, chars.length - 1);

    // Step 2: Reverse each word
    int start = 0;
    for (int i = 0; i <= chars.length; i++) {
        if (i == chars.length || chars[i] == ' ') {
            reverse(chars, start, i - 1);
            start = i + 1;
        }
    }

    // Step 3: Clean up spaces
    return cleanSpaces(chars);
}

private void reverse(char[] chars, int start, int end) {
    while (start < end) {
        char temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;
        start++;
        end--;
    }
}

private String cleanSpaces(char[] chars) {
    int n = chars.length;
    int j = 0;
    for (int i = 0; i < n; i++) {
        if (chars[i] != ' ') {
            if (j != 0) chars[j++] = ' ';
            while (i < n && chars[i] != ' ') {
                chars[j++] = chars[i++];
            }
        }
    }
    return new String(chars, 0, j);
}
```

## Edge Cases

1. **Empty String**: "" -> ""
2. **Single Word**: "word" -> "word"
3. **Two Words**: "hello world" -> "world hello"
4. **Leading Spaces**: "  hello" -> "hello"
5. **Trailing Spaces**: "hello  " -> "hello"
6. **Multiple Spaces Between**: "a  b  c" -> "c b a"
7. **Only Spaces**: "   " -> ""
8. **Single Character**: "a" -> "a"
9. **All Spaces with Words**: "  a  b  c  " -> "c b a"

## Related Problems

1. **LeetCode 344**: Reverse String - Reverse characters
2. **LeetCode 541**: Reverse String II - Reverse k-char groups
3. **LeetCode 557**: Reverse Words in String III - Reverse each word only
4. **LeetCode 541**: Reverse String - Similar concept
5. **LeetCode 917**: Reverse Only Letters - Reverse letters only
## Tags

- String
- Array
- Two Pointers
- Word Processing
- Medium Difficulty
- Acceptance: ~38%
