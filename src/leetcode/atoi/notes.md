# 8. String to Integer (atoi)

## Problem Statement
Implement the `atoi` function which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

The function first discards whitespace characters until a non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because the string is empty or contains only whitespace characters, no conversion is performed and zero is returned.

### Examples
```
Input: "42"
Output: 42

Input: "   -42"
Output: -42
Explanation: Leading whitespace is trimmed, then negative sign is processed

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at first non-digit character

Input: "words and 987"
Output: 0
Explanation: First non-whitespace character is not a digit or +/-

Input: "-91283472332"
Output: -2147483648
Explanation: Clamped to Integer.MIN_VALUE due to overflow
```

### Constraints
- Only space character ' ' is considered as whitespace
- Assume the environment can only store integers within 32-bit signed integer range: [−2^31, 2^31 − 1]
- If the numerical value is out of range, return INT_MAX (2147483647) or INT_MIN (−2147483648)

## Approach & Solution

### Key Insights
1. **String trimming**: Remove leading whitespace first
2. **Sign handling**: Check for optional '+' or '-' at position 0
3. **Digit-by-digit parsing**: Process each character until non-digit is encountered
4. **Overflow prevention**: Use `long` to detect overflow before casting to `int`
5. **Early termination**: Stop at first non-digit character

### Algorithm Steps
1. Trim leading whitespace using `trim()`
2. Initialize result as `long` (to handle overflow), set negative flag
3. Iterate through each character:
   - If first character is '-', set negative flag and continue
   - If first character is '+', continue
   - If character is not a digit, break
   - Multiply result by 10 and add current digit
   - Check for overflow after each operation
4. Apply sign if negative
5. Return result cast to int (already clamped if overflow occurred)

### Complexity Analysis
- **Time Complexity**: O(n)
  - Where n is the length of the string
  - trim() operation: O(n)
  - Single pass through string: O(n)
  - Total: O(n)
- **Space Complexity**: O(n)
  - trim() creates a new string: O(n)
  - Other variables use O(1) space
  - Total: O(n)

### Visualization
```
Input: "   -42abc"

Step 1: Trim whitespace
"-42abc"

Step 2: Detect sign at position 0
isNegative = true
i = 0, skip this character

Step 3: Process digits
i=1: '4' → result = 0*10 + 4 = 4
i=2: '2' → result = 4*10 + 2 = 42
i=3: 'a' → not a digit, break

Step 4: Apply sign
result = 42 * -1 = -42

Output: -42

Overflow example: "-91283472332"
Processing digits:
result grows: 9 → 91 → 912 → 9128 → 91283 → 912834 → 9128347 → 91283472 → 912834723 → 9128347233 → 91283472332

Check: isNegative && (-1 * result) < Integer.MIN_VALUE
       -91283472332 < -2147483648 → true
Return: Integer.MIN_VALUE = -2147483648
```

## Code Walkthrough

```java
public int myAtoi(String str) {
    long result = 0;

    // Handle null or empty string
    if (str == null || str.length() == 0)
        return 0;

    boolean isNegative = false;

    // Trim leading/trailing whitespace
    str = str.trim();

    // Process each character
    for (int i = 0; i < str.length(); i++) {
        // Handle sign at first position
        if (i == 0 && str.charAt(i) == '-') {
            isNegative = true;
            continue;
        }

        if (str.charAt(i) == '+' && i == 0) {
            continue;
        }

        // Stop at non-digit character
        if (str.charAt(i) > '9' || str.charAt(i) < '0')
            break;

        // Build result digit by digit
        result = (long) (result * 10 + Long.valueOf(str.charAt(i) - '0'));

        // Check for overflow during processing
        if (isNegative && (-1 * result) < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        if (result > Integer.MAX_VALUE && !isNegative)
            return Integer.MAX_VALUE;
    }

    // Apply sign
    if (isNegative)
        result = result * -1;

    return (int) result;
}
```

**Key Implementation Details:**
- Uses `long` to safely detect overflow before clamping
- `str.charAt(i) - '0'` converts character digit to numeric value
- Checks overflow after each digit addition (early detection)
- Handles both positive and negative overflow

## Edge Cases
- **Empty string**: "" → 0
- **Only whitespace**: "   " → 0
- **No digits**: "words" → 0
- **Plus sign**: "+123" → 123
- **Leading zeros**: "0032" → 32
- **Positive overflow**: "2147483648" → 2147483647 (INT_MAX)
- **Negative overflow**: "-2147483649" → -2147483648 (INT_MIN)
- **Mixed content**: "123abc456" → 123 (stops at 'a')
- **Multiple signs**: "+-12" → 0 ('+' at position 0, then '-' is non-digit)

## Related Problems
- **7. Reverse Integer**: Similar overflow handling
- **65. Valid Number**: More complex string parsing
- **9. Palindrome Number**: String/number manipulation
- **415. Add Strings**: String-based arithmetic

## Tags
`string` `parsing` `math` `medium`
