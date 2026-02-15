# Reverse Vowels of a String - LeetCode Problem 345

## Problem Statement
Write a function that takes a string as input and reverses only the vowels of a string.

The vowels does not include the letter "y".

Vowels are: a, e, i, o, u (both lowercase and uppercase).

## Examples

**Example 1:**
- Input: s = "hello"
- Output: "holle"
- Explanation: 'e' and 'o' are vowels. Reversed: o and e

**Example 2:**
- Input: s = "leetcode"
- Output: "leotcede"

**Example 3:**
- Input: s = "aA"
- Output: "Aa"

## Key Insights
1. **Two Pointers**: One from left, one from right
2. **Character Identification**: Check if vowel quickly
3. **Swap In-Place**: Modify character array for efficiency
4. **Case Sensitive**: Both uppercase and lowercase vowels
5. **Non-Vowels**: Skip non-vowel characters

## Algorithm Steps

### Approach: Two Pointer Swap

**Step 1: Convert to Array**
- Convert string to char array for in-place modification

**Step 2: Initialize Pointers**
- left = 0
- right = length - 1

**Step 3: Swap Vowels**
- While left < right:
  - If both are vowels: swap them, move both pointers
  - If left is not vowel: move left
  - If right is not vowel: move right

**Step 4: Return**
- Convert char array back to string

**Pseudocode:**
```
function reverseVowels(s):
    chars = s.toCharArray()
    left = 0
    right = length - 1

    while left < right:
        if isVowel(chars[left]) and isVowel(chars[right]):
            swap(chars, left, right)
            left++
            right--
        else if isVowel(chars[left]):
            right--
        else:
            left++

    return new String(chars)

function isVowel(ch):
    return ch in {'a','e','i','o','u','A','E','I','O','U'}
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = string length |
| Space Complexity | O(1) or O(n) depending on string storage |

**Time Analysis:**
- Single pass through string: O(n)
- Each character visited at most once
- Each vowel swapped once: O(n/2) swaps

**Space Analysis:**
- In-place swap: O(1) extra space
- String to array conversion: O(n) but part of output

## ASCII Visualization

```
Example: "hello"

Initial: h e l l o
Vowels:    e     o
Positions: 1     4

Set up pointers:
left=0, right=4
h e l l o
↑       ↑

Iteration 1:
h is not vowel, left++
h e l l o
  ↑     ↑

Iteration 2:
e is vowel, o is vowel
swap(1, 4): e ↔ o
h o l l e
left++, right--
h o l l e
    ↑ ↑

Iteration 3:
left=2, right=3 (left < right)
l is not vowel, left++
h o l l e
      ↑ ↑

Iteration 4:
left=3, right=3 (not left < right, stop)

Result: "holle"

Example: "leetcode" (chars: l,e,e,t,c,o,d,e)

Vowel positions: 1(e), 2(e), 5(o), 7(e)

Initial:
l e e t c o d e
  ↑           ↑
left=0  right=7

Process:
l not vowel, left++ → left=1
  e e t c o d e
    ↑         ↑

e is vowel, e is vowel
swap (doesn't change): left=1, right=7
  e e t c o d e
    ↑         ↑

left++, right--
l e e t c o d e
      ↑     ↑

e is vowel, o is vowel
swap: l e o t c e d e
      left=3, right=5
      ↑         ↑

left++, right--
l e o t c e d e
        ↑ ↑

t is not vowel, left++
l e o t c e d e
          ↑ ↑

left=4, right=5 (left < right)
c is not vowel, left++
l e o t c e d e
            ↑ ↑

left=5, right=5 (not left < right, stop)

Result: "leotcede"

Vowel Movement Chart:
Original positions: [e(1), e(2), o(5), e(7)]
Final positions:    [e(7), o(5), e(2), e(1)]

Mapping:
Position 1: e → o
Position 2: e → e
Position 5: o → e
Position 7: e → o

Actually:
Original: l e e t c o d e
          0 1 2 3 4 5 6 7

After all swaps:
l e o t c e d e
0 1 2 3 4 5 6 7

Vowels: e@1→o@1, e@2→e@2, o@5→e@5, e@7→o@7
Wait, let me trace more carefully:

Vowel order: e, e, o, e (positions 1,2,5,7)
Reversed: e, o, e, e (should place at 1,2,5,7)

Actually swapping:
Swap(1,7): e ↔ e → no change
Swap(2,5): e ↔ o → changes
Result: l e o t c e d e
```

## Code Walkthrough

```java
public String reverseVowels(String s) {
    // Convert string to character array for in-place modification
    char[] input = s.toCharArray();
    int left = 0;
    int right = s.length() - 1;

    // Two pointer approach
    while (left < right) {
        // Check if both pointers point to vowels
        if (isVowel(input[left]) && isVowel(input[right])) {
            // Swap vowels
            swap(input, left, right);
            left++;
            right--;
        } else if (isVowel(input[left])) {
            // Left is vowel, right is not, move right
            right--;
        } else {
            // Left is not vowel, move left
            left++;
        }
    }

    return new String(input);
}

// Helper: Check if character is a vowel
public boolean isVowel(char ch) {
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
           ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
}

// Helper: Swap two characters in array
public void swap(char[] input, int left, int right) {
    char temp = input[left];
    input[left] = input[right];
    input[right] = temp;
}

// Alternative: Using HashSet for vowel checking
public String reverseVowelsHashSet(String s) {
    Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
    char[] chars = s.toCharArray();
    int left = 0, right = chars.length - 1;

    while (left < right) {
        if (vowels.contains(chars[left]) && vowels.contains(chars[right])) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        } else if (vowels.contains(chars[left])) {
            right--;
        } else {
            left++;
        }
    }

    return new String(chars);
}

// Alternative: Using regex
public String reverseVowelsRegex(String s) {
    char[] chars = s.toCharArray();
    int left = 0, right = chars.length - 1;

    while (left < right) {
        while (left < right && !String.valueOf(chars[left]).matches("[aeiouAEIOU]"))
            left++;
        while (left < right && !String.valueOf(chars[right]).matches("[aeiouAEIOU]"))
            right--;

        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
        left++;
        right--;
    }

    return new String(chars);
}
```

## Edge Cases

1. **Empty String**: "" -> ""
2. **No Vowels**: "xyz" -> "xyz"
3. **All Vowels**: "aeiou" -> "uoiea"
4. **Single Vowel**: "a" -> "a"
5. **Single Non-Vowel**: "x" -> "x"
6. **Uppercase**: "AEI" -> "IEA"
7. **Mixed Case**: "Aa" -> "aA"
8. **Long String**: Handle 10000+ characters
9. **Special Characters**: "a,e.i" -> "i,e.a"

## Related Problems

1. **LeetCode 344**: Reverse String - Reverse entire string
2. **LeetCode 541**: Reverse String II - Reverse in k-character chunks
3. **LeetCode 151**: Reverse Words in a String - Reverse word order
4. **LeetCode 557**: Reverse Words in a String III - Reverse each word
5. **LeetCode 917**: Reverse Only Letters - Reverse only alphabetic chars
## Tags

- Two Pointers
- String
- Character Manipulation
- Easy Difficulty
- Acceptance: ~65%
