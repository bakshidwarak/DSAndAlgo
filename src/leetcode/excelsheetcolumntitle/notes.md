# LeetCode 168: Excel Sheet Column Title

## Problem Statement

Given an integer `columnNumber`, return its corresponding column title as it appears in an Excel sheet.

For example:
```
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...
```

### Examples

**Example 1:**
```
Input: columnNumber = 1
Output: "A"
```

**Example 2:**
```
Input: columnNumber = 28
Output: "AB"
Explanation: AB is the 28th column
```

**Example 3:**
```
Input: columnNumber = 701
Output: "ZY"
Explanation:
26*26 + 25 = 676 + 25 = 701
```

**Constraints:**
- 1 <= columnNumber <= 2^31 - 1

## Key Insights

1. **Base-26 Number System**: Similar to base conversion, but with a twist
2. **Not Pure Base-26**: Excel columns are 1-indexed, not 0-indexed (A=1, not A=0)
3. **Adjustment Needed**: Must subtract 1 before division to handle 1-indexing
4. **Recursive Solution**: Can build the title recursively from right to left
5. **Character Mapping**: Map remainders 0-25 to 'A'-'Z'

## Algorithm Steps

### Recursive Approach

1. **Base Case**: If n <= 26
   - Return single character at position n-1 in ['A'..'Z']

2. **Recursive Case**: If n > 26
   - Adjust n by subtracting 1 (to handle 1-indexing)
   - Calculate first part: n / 26 (quotient)
   - Calculate second part: n % 26 (remainder)
   - Recursively convert first part
   - Append character for second part

3. **Build Result**: Concatenate characters from left to right

## Complexity Analysis

- **Time Complexity**: O(log26(n))
  - Each recursion divides n by 26
  - Number of digits in base-26 is log26(n)
  - Overall: O(log n)

- **Space Complexity**: O(log26(n))
  - Recursion stack depth
  - StringBuilder storage
  - Overall: O(log n)

## Visual Explanation

### Number to Column Mapping

```
1 -> A      27 -> AA     53 -> BA
2 -> B      28 -> AB     ...
3 -> C      29 -> AC     701 -> ZY
...         ...          702 -> ZZ
26 -> Z     52 -> AZ     703 -> AAA

Pattern:
Single letter: 1-26     (A-Z)
Two letters:   27-702   (AA-ZZ)
Three letters: 703-...  (AAA-...)
```

### Example: n = 28

```
Step 1: n = 28
  Is 28 <= 26? No
  Adjust: n = 28 - 1 = 27

Step 2: Divide
  firstPart = 27 / 26 = 1
  secondPart = 27 % 26 = 1

Step 3: Recurse on firstPart (1)
  1 <= 26, base case
  Return chars[1-1] = chars[0] = 'A'

Step 4: Append secondPart
  chars[1] = 'B'

Result: "A" + "B" = "AB"
```

### Example: n = 701

```
Step 1: n = 701
  Is 701 <= 26? No
  Adjust: n = 701 - 1 = 700

Step 2: Divide
  firstPart = 700 / 26 = 26
  secondPart = 700 % 26 = 24

Step 3: Recurse on firstPart (26)
  26 <= 26, base case
  Return chars[26-1] = chars[25] = 'Z'

Step 4: Append secondPart
  chars[24] = 'Y'

Result: "Z" + "Y" = "ZY"
```

### Why Subtract 1?

```
Without adjustment:
n = 26
26 / 26 = 1, remainder = 0
Would give "A" + char[0] = "AA" (WRONG!)
Correct answer: "Z"

With adjustment:
n = 26
Adjust: 26 - 1 = 25
25 / 26 = 0 (no recursion)
Base case: chars[25] = 'Z' (CORRECT!)

The -1 adjustment handles the 1-indexed nature of Excel columns
```

### Conversion Table

```
n     Calculation             Result
1     base case              A
26    base case              Z
27    (27-1)/26=1, %26=1     AA
52    (52-1)/26=1, %26=25    AZ
53    (53-1)/26=2, %26=0     BA
702   (702-1)/26=26, %26=25  ZZ
703   recursive...           AAA
```

## Code Walkthrough

```java
public String convertToTitle(int n) {
    // Character array for easy indexing
    char[] chars = new char[] {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
        'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    StringBuilder sb = new StringBuilder();
    convert(n, chars, sb);
    return sb.toString();
}

public void convert(int n, char[] chars, StringBuilder sb) {
    // Base case: single character (1-26)
    if (n <= 26) {
        sb.append(chars[n - 1]);  // -1 for 0-based indexing
        return;
    }

    // Adjust for 1-indexed system
    n--;

    // Calculate quotient and remainder
    int firstPart = n / 26;
    int secondPart = n % 26;

    // Recursively convert quotient (higher-order digits)
    convert(firstPart, chars, sb);

    // Append remainder (current digit)
    sb.append(chars[secondPart]);
}
```

## Iterative Solution

```java
public String convertToTitle(int columnNumber) {
    StringBuilder result = new StringBuilder();

    while (columnNumber > 0) {
        // Adjust for 1-indexed system
        columnNumber--;

        // Get current character
        result.append((char)('A' + columnNumber % 26));

        // Move to next digit
        columnNumber /= 26;
    }

    // Reverse because we built from right to left
    return result.reverse().toString();
}
```

## Mathematical Explanation

Excel column system is similar to base-26, but with important differences:

**Standard Base-26:**
- Digits: 0, 1, 2, ..., 25
- 0 = A, 1 = B, ..., 25 = Z
- AA = 0*26 + 0 = 0

**Excel System:**
- Digits: 1, 2, 3, ..., 26
- 1 = A, 2 = B, ..., 26 = Z
- AA = 1*26 + 1 = 27

The key difference: **no zero digit**. We must adjust by subtracting 1 before conversion.

## Edge Cases

1. **Minimum Value**: n = 1
   - Output: "A"

2. **Single Letter Boundary**: n = 26
   - Output: "Z"

3. **Two Letter Start**: n = 27
   - Output: "AA"

4. **Two Letter End**: n = 702
   - Output: "ZZ"

5. **Three Letter Start**: n = 703
   - Output: "AAA"

6. **Large Numbers**: n = 2147483647 (2^31 - 1)
   - Should handle without overflow

7. **Powers of 26**:
   - n = 26: "Z"
   - n = 676 (26^2): "YZ"
   - n = 17576 (26^3): "YYZ"

## Related Problems

1. **LeetCode 171**: Excel Sheet Column Number (reverse problem)
2. **LeetCode 29**: Divide Two Integers
3. **LeetCode 7**: Reverse Integer
4. **LeetCode 12**: Integer to Roman
5. **LeetCode 273**: Integer to English Words

## Tags

- Math
- String
- Base Conversion
- Recursion
- Number System
