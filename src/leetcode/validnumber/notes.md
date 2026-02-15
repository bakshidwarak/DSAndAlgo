# LeetCode 65: Valid Number

## Problem Statement
Validate if a given string is a valid numeric value.

**Valid Number Format:**
- Integer: "0", "123", "-456"
- Decimal: "0.1", ".5", "1."
- Scientific notation: "2e10", "1.5e-3", "6E+2"
- With whitespace: " 0.1 ", " 123 "

**Invalid Examples:**
- "abc", "1 a", "..1", "1.2.3", "1e1.2", "e10"

**Constraints:**
- Only digits 0-9, '+', '-', '.', 'e'/'E', and whitespace allowed
- Must have at least one digit
- '+'/'-' only at start or after 'e'/'E'
- '.' cannot appear after 'e'/'E'
- 'e'/'E' cannot appear twice

## Examples

### Valid Examples
- `"0"` → true
- `"0.1"` → true
- `".1"` → true (leading decimal point)
- `"2e10"` → true
- `"-90E3"` → true
- `" 0.1 "` → true (with spaces)

### Invalid Examples
- `"abc"` → false (letters)
- `"1 a"` → false (embedded non-numeric)
- `"1.2.3"` → false (multiple decimals)
- `"e"` → false (no digits)
- `"."` → false (no digits, only point)
- `"1e"` → false (e without exponent digits)

## Key Insights

1. **State Machine Logic:** Track what characters are allowed after each type
2. **Digit Requirement:** At least one digit must be present before decimal or after e
3. **Sign Position:** '+' and '-' only valid at start or immediately after 'e'/'E'
4. **Decimal Constraint:** Only one '.' allowed, and not after 'e'/'E'
5. **Exponent Constraint:** Only one 'e'/'E' allowed, cannot be last character

## Algorithm Steps

### Character Validation Approach

```
1. Trim leading/trailing whitespace
2. Initialize flags for seen elements:
   - isNumberSeen (at least one digit)
   - isESeen (e/E seen)
   - isDecimalSeen (decimal point seen)
   - isPlusSeen, isMinusSeen (sign seen)
3. For each character:
   a. If digit (0-9):
      - Set isNumberSeen = true
   b. If '+':
      - Cannot repeat
      - Only at position 0 or after 'e'
      - Cannot be last character
   c. If '-':
      - Cannot repeat
      - Only at position 0 or after 'e'
      - Cannot be last character
   d. If '.':
      - Cannot repeat
      - Cannot appear after 'e'
   e. If 'e'/'E':
      - Cannot repeat
      - Must have number before it
      - Cannot be last character
   f. Else:
      - Invalid character
4. Final check: Must have seen at least one digit
```

## Complexity Analysis

- **Time Complexity:** O(n) - Single pass through string
- **Space Complexity:** O(1) - Only tracking state with flags

## ASCII Visualization

```
Valid: "2e10"
  Position: 0   1   2
  Character:'2' 'e' '1' '0'

  Step 1: '2' is digit
    isNumberSeen = true ✓

  Step 2: 'e' is exponent
    isNumberSeen = true (check passed ✓)
    isESeen = true ✓
    Not last char ✓

  Step 3: '1' is digit
    isNumberSeen = true ✓

  Step 4: '0' is digit
    isNumberSeen = true ✓

  Final: All digits found, valid format ✓
  Result: TRUE

---

Invalid: "3.25e"
  Position: 0   1   2   3   4
  Character:'3' '.' '2' '5' 'e'

  Step 1-3: '3', '.', '2', '5' OK

  Step 4: 'e' is exponent
    Not last char? NO! ('e' is last character)
    Result: FALSE (exponent without following digits)

---

Invalid: "1a"
  Position: 0   1
  Character:'1' 'a'

  Step 1: '1' is digit
    isNumberSeen = true ✓

  Step 2: 'a' is invalid character
    Not in [0-9, +, -, ., e]
    Result: FALSE
```

## Code Walkthrough

```java
public static boolean isNumber(String s) {
    if (s == null || s.isEmpty() || s.trim().isEmpty())
        return false;

    char[] chars = s.trim().toCharArray();

    boolean isESeen = false;
    boolean isDecimalSeen = false;
    boolean isNumberSeen = false;
    boolean isPlusSeen = false;
    boolean isMinusSeen = false;

    for (int i = 0; i < chars.length; i++) {
        char ch = chars[i];

        if (ch < '0' || ch > '9') {  // Not a digit
            boolean isLastCharacter = i == chars.length - 1;
            boolean isNotFirstCharacter = i != 0;

            if (ch == '+') {
                // '+' only valid at start or after 'e'
                if (isPlusSeen || !isESeen && isNotFirstCharacter || isLastCharacter)
                    return false;
                if (isNotFirstCharacter)
                    isPlusSeen = true;
            } else if (ch == '-') {
                // '-' only valid at start or after 'e'
                if (isMinusSeen || !isESeen && isNotFirstCharacter || isLastCharacter)
                    return false;
                if (isNotFirstCharacter)
                    isMinusSeen = true;
            } else if (ch == '.') {
                // Only one '.', not after 'e'
                if (isDecimalSeen || isESeen)
                    return false;
                isDecimalSeen = true;
            } else if (ch == 'e') {
                // Only one 'e', needs digit before, can't be last
                if (isESeen || !isNumberSeen || isLastCharacter)
                    return false;
                isESeen = true;
            } else {
                return false;  // Invalid character
            }
        } else {
            isNumberSeen = true;  // Found a digit
        }
    }

    // Must have at least one number
    if ((isESeen || isDecimalSeen) && (!isNumberSeen))
        return false;

    return true;
}
```

**Execution Flow:**
1. Trim whitespace from input
2. Convert to char array
3. Track state with boolean flags
4. Validate each character based on context
5. Check final state has at least one digit

## Edge Cases

1. **Whitespace:** Leading/trailing spaces are OK
   - `" 123 "` → true

2. **Leading Decimal:** `.5` is valid
   - `".5"` → true
   - `"."` → false (needs digit)

3. **Trailing Decimal:** `1.` is valid
   - `"1."` → true
   - `"."` → false (needs digit somewhere)

4. **Sign Position:** Only at start or after e
   - `"+1.5"` → true
   - `"1+5"` → false
   - `"1e+5"` → true
   - `"1e5"` → true

5. **Exponent Cases:**
   - `"1e10"` → true
   - `"1E10"` → true
   - `"-1e-5"` → true
   - `"1e"` → false (incomplete)
   - `"e10"` → false (no base digit)

6. **Empty/Null:**
   - `""` → false
   - `" "` → false
   - `null` → false

## Related Problems

1. **LeetCode 8 - String to Integer:** Parse integer from string
2. **LeetCode 127 - Valid Palindrome:** String validation
3. **LeetCode 157 - Read N Characters Given Read4:** String processing
4. **LeetCode 468 - Validate IP Address:** IP format validation
5. **LeetCode 567 - Permutation in String:** String pattern matching
## Tags

`#String` `#State-Machine` `#Validation` `#Hard`

## Key Takeaways

- Requires understanding all valid numeric formats
- State machine with flags tracks what's allowed at each position
- Signs only valid at start or after exponent
- Exponent cannot be last character
- Must have at least one digit somewhere in string
- Be careful with edge cases like ".5", "1.", "1e+5"
