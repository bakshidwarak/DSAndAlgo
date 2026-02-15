# LeetCode 273: Integer to English Words

## Problem Statement
Convert a non-negative integer to its English words representation.

Given input is guaranteed to be less than 2^31 - 1.

## Examples

**Example 1:**
```
Input: 123
Output: "One Hundred Twenty Three"
```

**Example 2:**
```
Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
```

**Example 3:**
```
Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
```

**Example 4:**
```
Input: 0
Output: "Zero"
```

## Key Insights

1. **Grouping by Thousands**: Process number in groups of 3 digits (ones, thousands, millions, billions)
2. **Lookup Tables**: Pre-defined strings for ones, tens, and big numbers
3. **Modulo Operations**: Use mod/divide to extract digit groups
4. **Reverse Processing**: Process from least significant to most significant group

## Algorithm Steps

1. Handle special case: zero
2. Create lookup tables for:
   - ones (0-9)
   - tens (20, 30, 40, ... 90)
   - big numbers (thousand, million, billion)
3. Extract 3-digit groups using mod 1000 and divide 1000
4. Convert each group to words and append big number suffix
5. Reverse the list and join with spaces

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(1) - Fixed number of groups (max 4) |
| **Space Complexity** | O(1) - Output size is bounded |

- Maximum input < 2^31, so at most billions (4 groups of 3)
- 4 × 3 = 12 digits maximum
- Output length is bounded

## ASCII Visualization

```
Input: 1234567

Break into groups of 3 from right:
1,234,567
  ↓  ↓  ↓
  1  234 567

Group values:
567: "Five Hundred Sixty Seven" (ones)
234: "Two Hundred Thirty Four" + "Thousand"
1: "One" + "Million"

Build result list:
Iteration 1: k=0, curr=567
  hundreds(567) = "Five Hundred Sixty Seven"
  words[0] = "Five Hundred Sixty Seven"

Iteration 2: k=1, curr=234
  hundreds(234) = "Two Hundred Thirty Four"
  words[1] = "Two Hundred Thirty Four Thousand"

Iteration 3: k=2, curr=1
  hundreds(1) = "One"
  words[2] = "One Million"

Reverse words: ["One Million", "Two Hundred Thirty Four Thousand", "Five Hundred Sixty Seven"]

Join: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Input breakdown: 123
123 % 1000 = 123 (ones group)
123 / 1000 = 0 (stop)

Process 123:
Ones place: 3 → "Three"
Tens place: 20 → "Twenty"
Hundreds place: 100 → "One Hundred"
Result: "One Hundred Twenty Three"
```

## Code Walkthrough

```java
public String numberToWords(int num) {
    if (num == 0) {
        return "Zero";  // Special case
    }

    ArrayList<String> strList = new ArrayList<>();
    int k = 0;

    while (num > 0) {
        // Extract 3-digit group
        int curr = num % 1000;

        // Convert group to words
        StringBuilder internalSb = new StringBuilder();
        internalSb.append(hundred(curr));  // Get word for 0-999

        if (hundred(curr).trim() != "") {
            internalSb.append(" ");
        }

        num = num / 1000;  // Move to next group

        // Add scale word (thousand, million, billion)
        if (curr == 0) {
            if (num == 0) {
                internalSb.append(bigs[k]);  // Empty string for zero groups
            }
        } else {
            internalSb.append(bigs[k]);
        }

        k++;
        strList.add(internalSb.toString().trim());
    }

    // Reverse and join
    Collections.reverse(strList);
    return strList.stream()
        .filter(str -> !str.trim().equals(""))
        .collect(Collectors.joining(" "));
}

// Convert 0-999 to words
private String hundred(int num) {
    if (num == 0) {
        return "";
    }

    ArrayList<String> strList = new ArrayList<>();
    int curr = num % 100;

    // Ones and tens
    if (curr < 20) {
        strList.add(ones[curr]);
    } else {
        strList.add(ones[curr % 10]);  // Ones digit
        strList.add(tens[curr / 10]);  // Tens digit
    }

    num = num / 100;

    // Hundreds
    if (num != 0) {
        strList.add(ones[num] + " Hundred");
    }

    Collections.reverse(strList);
    return strList.stream()
        .collect(Collectors.joining(" "));
}
```

**Supporting Lookup Tables:**
```java
String[] ones = {
    "", "One", "Two", "Three", "Four", "Five",
    "Six", "Seven", "Eight", "Nine", "Ten",
    "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
    "Sixteen", "Seventeen", "Eighteen", "Nineteen"
};

String[] tens = {
    "", "", "Twenty", "Thirty", "Forty", "Fifty",
    "Sixty", "Seventy", "Eighty", "Ninety"
};

String[] bigs = {
    "", "Thousand", "Million", "Billion", "Trillion"
};
```

## Edge Cases

1. **Zero**: 0 → "Zero" (special case)
2. **Single digit**: 5 → "Five"
3. **Teens**: 10-19 have special names
4. **Tens**: 20, 30, 40... (zero ones)
5. **Hundreds**: 100, 200, 300... (zero tens/ones)
6. **Large gaps**: 1000000 → "One Million" (skip zeros)
7. **Maximum**: 2147483647 → "Two Billion One Hundred Forty Seven Million..."

## Related Problems

1. **LeetCode 12** - Integer to Roman (Similar conversion)
2. **LeetCode 13** - Roman to Integer (Reverse conversion)
3. **LeetCode 165** - Compare Version Numbers (String parsing)
4. **LeetCode 71** - Simplify Path (String manipulation)
5. **LeetCode 224** - Basic Calculator (Parsing expressions)

## Tags

`Math` `String` `Hard` `Google` `Facebook` `LinkedIn` `Microsoft`

## Alternative Approaches

### Approach 2: Direct String Building
```
- Process each group and build result string directly
- Less list reversal, more straightforward
- Similar time/space complexity
```

### Approach 3: Recursive
```
- Recursively convert each group
- Then concatenate results
- More elegant but similar complexity
```

## Implementation Notes

1. **Lookup Tables**: Pre-computed for efficiency
2. **Group Processing**: Process groups of 1000 at a time
3. **Empty Group Skip**: Don't add big number words for zero groups
4. **Trimming**: Remove extra spaces from result
5. **Reversing**: Build list backwards, then reverse at end

## Common Pitfalls

1. **Zero handling**: Special case needed
2. **Empty groups**: Don't add thousand/million for 0 values
3. **Extra spaces**: Need trimming between words
4. **Teens**: 11-19 have special names, not "One Ten One"
5. **Boundary conditions**: Group extraction requires careful modulo/divide

## Notes

- This is a classic interview problem testing string manipulation
- Requires understanding of number grouping by powers of 1000
- Key insight: Work with groups of 3 digits at a time
- Lookup tables make code clean and efficient
- Good practice for handling edge cases
- Demonstrates importance of proper test coverage
