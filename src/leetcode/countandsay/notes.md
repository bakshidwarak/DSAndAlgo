# LeetCode 38: Count and Say

## Problem Statement

The **count-and-say** sequence is a sequence of digit strings defined by the recursive formula:
- `countAndSay(1) = "1"`
- `countAndSay(n)` is the way you would "say" the digit string from `countAndSay(n-1)`, which is then converted into a different digit string.

To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.

Given a positive integer `n`, return the `nth` term of the count-and-say sequence.

### Examples

**Example 1:**
```
Input: n = 1
Output: "1"
Explanation: This is the base case.
```

**Example 2:**
```
Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
```

**Example 3:**
```
Input: n = 5
Output: "111221"
Explanation:
countAndSay(4) = "1211"
countAndSay(5) = say "1211" = one 1 + one 2 + two 1's = "11" + "12" + "21" = "111221"
```

**Constraints:**
- 1 <= n <= 30

## Key Insights

1. **Recursive Pattern**: Each term is derived from the previous term by "reading" it
2. **Run-Length Encoding**: The sequence is essentially run-length encoding of the previous term
3. **Base Cases**: n=1 returns "1", n=2 returns "11" (optional optimization)
4. **Character Grouping**: Need to count consecutive identical digits
5. **String Building**: Use StringBuilder for efficient string concatenation

## Algorithm Steps

1. **Base Cases**:
   - If n = 1, return "1"
   - If n = 2, return "11" (optimization)

2. **Recursive Call**: Get the result for (n-1)

3. **Process Previous String**:
   - Iterate through each character
   - Count consecutive occurrences of same digit
   - For each group, append count + digit to result

4. **Count Logic**:
   - Initialize count = 1
   - While next character is same as current, increment count and move forward
   - Append count and character to StringBuilder

5. **Return** the constructed string

## Complexity Analysis

- **Time Complexity**: O(2^n)
  - Each recursion level processes the string from previous level
  - String length roughly doubles at each level (worst case)
  - Number of levels: n
  - Approximate: O(2^n) in worst case
  - More precisely: O(n * L) where L is the length of the result string

- **Space Complexity**: O(2^n)
  - Recursion call stack: O(n)
  - String storage at each level: exponentially growing
  - Result string can be very long for large n
  - Overall: O(2^n) for the result storage

## Visual Explanation

### Sequence Evolution

```
n = 1:  "1"
        Read: one 1

n = 2:  "11"
        Read: two 1's

n = 3:  "21"
        Read: one 2, one 1

n = 4:  "1211"
        Read: one 1, one 2, two 1's

n = 5:  "111221"
        Read: three 1's, two 2's, one 1

n = 6:  "312211"
        Read: one 3, one 1, one 2, two 2's, two 1's

n = 7:  "13112221"
        Read: one 1, one 3, two 1's, three 2's, one 1

n = 8:  "1113213211"
```

### Processing Example: n = 4

```
Input: "21"

Step-by-step:
Index 0: char = '2'
         count = 1
         Next char '1' != '2', stop counting
         Append: "1" + "2" = "12"

Index 1: char = '1'
         count = 1
         Next char doesn't exist, stop counting
         Append: "1" + "1" = "11"

Result: "12" + "11" = "1211"
```

### Processing Example: n = 5

```
Input: "1211"

Index 0: char = '1'
         count = 1
         Next char '2' != '1', stop counting
         Append: "11"

Index 1: char = '2'
         count = 1
         Next char '1' != '2', stop counting
         Append: "12"

Index 2: char = '1'
         count = 1
         Index 3: '1' == '1', count++, count = 2
         Next char doesn't exist, stop counting
         Append: "21"

Result: "11" + "12" + "21" = "111221"
```

## Code Walkthrough

```java
public String countAndSay(int n) {
    // Entry point to recursive solution
    return countAndSayHelper(n);
}

public String countAndSayHelper(int n) {
    // Base case 1: n = 1
    if (n == 1) {
        return "1";
    }

    // Base case 2: n = 2 (optimization)
    if (n == 2) {
        return "11";
    }

    // Recursive call to get previous term
    String num = countAndSayHelper(n - 1);

    // StringBuilder for efficient string building
    StringBuilder sb = new StringBuilder();

    // Process each character group
    for (int i = 0; i < num.length(); i++) {
        int count = 1;

        // Count consecutive same characters
        while (i < num.length() - 1 && num.charAt(i) == num.charAt(i + 1)) {
            count++;
            i++;  // Move to next character
        }

        // Append count followed by the digit
        sb.append(count);
        sb.append(num.charAt(i));
    }

    return sb.toString();
}
```

## Iterative Solution

An iterative version is also possible and often preferred:

```java
public String countAndSay(int n) {
    String result = "1";

    for (int i = 2; i <= n; i++) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int j = 0; j < result.length(); j++) {
            if (j < result.length() - 1 && result.charAt(j) == result.charAt(j + 1)) {
                count++;
            } else {
                sb.append(count);
                sb.append(result.charAt(j));
                count = 1;
            }
        }

        result = sb.toString();
    }

    return result;
}
```

## Edge Cases

1. **n = 1**: Base case
   - Output: "1"

2. **n = 2**: Simple case
   - Output: "11"

3. **Maximum n = 30**: Very long string
   - Should handle efficiently with StringBuilder

4. **Pattern Recognition**: The sequence never contains digits > 3 (for reasonable n)
   - Because maximum consecutive count is typically small

## Interesting Properties

1. **No Digit > 3**: For n <= 30, you'll never see a digit greater than 3 in the count
2. **Conway's Constant**: The sequence length grows at rate approximately 1.303577... (Conway's constant)
3. **Self-Describing**: Each term describes the previous term
4. **No Fixed Point**: No term equals itself when "said"

## Related Problems

1. **LeetCode 271**: Encode and Decode Strings
2. **LeetCode 443**: String Compression (run-length encoding)
3. **LeetCode 604**: Design Compressed String Iterator
4. **LeetCode 900**: RLE Iterator
5. **LeetCode 1313**: Decompress Run-Length Encoded List

## Tags

- String
- Recursion
- Run-Length Encoding
- Simulation
- Math
- Sequence Generation
