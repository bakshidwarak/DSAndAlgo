# LeetCode 6: ZigZag Conversion

## Problem Statement
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:

```
P   A   H   N
A P L S I I G
Y   I   R
```

And then read line by line: "PAHNAPLSIIGYI"

Given a string `s` and the number of rows `numRows`, convert the string to a zigzag pattern and return the converted string.

**Constraints:**
- `1 <= s.length <= 1000`
- `1 <= numRows <= 1000`
- String contains printable characters

## Examples

### Example 1
- **Input:** `s = "PAYPALISHIRING"`, `numRows = 3`
- **Output:** `"PAHNAPLSIIGYI"`
- **Explanation:**
  ```
  P   A   H   N
  A P L S I I G
  Y   I   R
  ```

### Example 2
- **Input:** `s = "PAYPALISHIRING"`, `numRows = 4`
- **Output:** `"PINALSIGYAHRPI"`
- **Explanation:**
  ```
  P     I    N
  A   L S  I G
  Y A   H R
  P     I
  ```

### Example 3
- **Input:** `s = "A"`, `numRows = 1`
- **Output:** `"A"`

## Key Insights

1. **Cyclic Pattern:** Characters follow a zigzag up-down pattern
2. **Row-Based Organization:** Collect characters row by row
3. **Mathematical Pattern:** Calculate which row each character belongs to
4. **Two Directions:** Down going (left column) and up-going (diagonal)
5. **Cycle Length:** Pattern repeats every `2 * numRows - 2` characters

## Algorithm Steps

### Approach: Row Collection

```
1. If numRows == 1, return original string
2. Create numRows number of StringBuilders (or character lists)
3. Initialize:
   - currentRow = 0 (start from first row)
   - goingDown = true (initially moving down)
4. For each character in string:
   a. Append character to currentRow's StringBuilder
   b. If currentRow == 0:
      - Set goingDown = true
   c. If currentRow == numRows - 1:
      - Set goingDown = false
   d. Update currentRow:
      - If goingDown: currentRow++
      - Else: currentRow--
5. Concatenate all rows and return
```

### Approach: Mathematical Pattern

```
1. If numRows == 1, return string
2. Calculate cycle length = 2 * (numRows - 1)
3. Create numRows number of StringBuilders
4. For each character at index i:
   - Calculate position within cycle: pos = i % cycle_length
   - Determine row:
     - If pos < numRows: row = pos
     - Else: row = cycle_length - pos
   - Append character to rows[row]
5. Concatenate all rows and return
```

## Complexity Analysis

- **Time Complexity:** O(n) - Process each character once
- **Space Complexity:** O(n) - Store all characters in result

## ASCII Visualization

```
Input: s = "PAYPALISHIRING", numRows = 3

Zigzag Pattern:
P   A   H   N
A P L S I I G
Y   I   R

Character by character placement:
Index: 0 1 2 3 4 5 6 7 8 9 10 11 12
Char:  P A Y P A L I S H I R  I  N  G
Row:   0 1 2 1 0 1 2 1 0 1 2  1  0  1

Movement:
P (0) → Down
A (1) → Down
Y (2) → Down (reach bottom)
P (3) → Up
A (4) → Up (reach top)
L (5) → Down
I (6) → Down
S (7) → Down (reach bottom)
H (8) → Up
I (9) → Up (reach top)
R (10) → Down
I (11) → Down (reach bottom)
N (12) → Up
G (13) → Up (would reach top)

Result by row:
Row 0: P (0), A (4), H (8), N (12) → "PAHN"
Row 1: A (1), P (3), L (5), S (7), I (9), I (11) → "APLSII"
Row 2: Y (2), I (6), R (10) → "YIR"

Concatenated: "PAHN" + "APLSII" + "YIR" = "PAHNAPLSIIYIR"

Wait, expected "PAHNAPLSIIGYI", let me recalculate...

Actually, let me recount:
Index: 0  1  2  3  4  5  6  7  8  9  10 11 12
Char:  P  A  Y  P  A  L  I  S  H  I  R  I  N  G (14 chars, 0-13)

Zigzag:
Row 0: 0(P), 4(A), 8(H), 12(N) → "PAHN"
Row 1: 1(A), 3(P), 5(L), 7(S), 9(I), 11(I) → "APLSII"
Row 2: 2(Y), 6(I), 10(R) → "YIR"

Hmm, that gives "PAHNAPLSIIYIR" but expected is "PAHNAPLSIIGYI"

Let me recount the original string:
"PAYPALISHIRING" = P A Y P A L I S H I R I N G (14 characters)

Zigzag placement:
Position 0: P → Row 0
Position 1: A → Row 1
Position 2: Y → Row 2
Position 3: P → Row 1
Position 4: A → Row 0
Position 5: L → Row 1
Position 6: I → Row 2
Position 7: S → Row 1
Position 8: H → Row 0
Position 9: I → Row 1
Position 10: R → Row 2
Position 11: I → Row 1
Position 12: N → Row 0
Position 13: G → Row 1

By row:
Row 0: P(0), A(4), H(8), N(12) → "PAHN"
Row 1: A(1), P(3), L(5), S(7), I(9), I(11), G(13) → "APLSIIG"
Row 2: Y(2), I(6), R(10) → "YIR"

Result: "PAHN" + "APLSIIG" + "YIR" = "PAHNAPLSIIGYI" ✓

Cycle Pattern Analysis:
For numRows = 3:
  Cycle length = 2 * (3 - 1) = 4

Index mod 4:
0 mod 4 = 0 → Row 0
1 mod 4 = 1 → Row 1
2 mod 4 = 2 → Row 2
3 mod 4 = 3 → Row 1 (going back up: 4 - 3 - 1 = 0? No, 4 - 3 = 1)
4 mod 4 = 0 → Row 0
5 mod 4 = 1 → Row 1
6 mod 4 = 2 → Row 2
7 mod 4 = 3 → Row 1
8 mod 4 = 0 → Row 0
9 mod 4 = 1 → Row 1
10 mod 4 = 2 → Row 2
11 mod 4 = 3 → Row 1
12 mod 4 = 0 → Row 0
13 mod 4 = 1 → Row 1

Formula for row from position:
pos = index % cycle_length
if pos < numRows:
  row = pos
else:
  row = cycle_length - pos

For numRows=3, cycle=4:
pos=0: row = 0 ✓
pos=1: row = 1 ✓
pos=2: row = 2 ✓
pos=3: row = 4 - 3 = 1 ✓
```

## Code Implementation (Approach 1: Row Collection)

```java
public String convert(String s, int numRows) {
    if (numRows == 1)
        return s;

    // Create StringBuilders for each row
    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
        rows.add(new StringBuilder());
    }

    int currentRow = 0;
    boolean goingDown = true;

    // Place each character in appropriate row
    for (char c : s.toCharArray()) {
        rows.get(currentRow).append(c);

        // Change direction at top and bottom
        if (currentRow == 0)
            goingDown = true;
        else if (currentRow == numRows - 1)
            goingDown = false;

        // Move to next row
        if (goingDown)
            currentRow++;
        else
            currentRow--;
    }

    // Concatenate all rows
    StringBuilder result = new StringBuilder();
    for (StringBuilder row : rows) {
        result.append(row);
    }

    return result.toString();
}
```

## Code Implementation (Approach 2: Mathematical Pattern)

```java
public String convert(String s, int numRows) {
    if (numRows == 1)
        return s;

    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
        rows.add(new StringBuilder());
    }

    int cycle = 2 * (numRows - 1);

    for (int i = 0; i < s.length(); i++) {
        int pos = i % cycle;
        int row = pos < numRows ? pos : cycle - pos;
        rows.get(row).append(s.charAt(i));
    }

    StringBuilder result = new StringBuilder();
    for (StringBuilder row : rows) {
        result.append(row);
    }

    return result.toString();
}
```

## Edge Cases

1. **Single Row:** numRows == 1
   - `s = "PAYPALISHIRING"`, `numRows = 1` → `"PAYPALISHIRING"` (no zigzag)

2. **Single Character:** String with one character
   - `s = "A"`, `numRows = 3` → `"A"`

3. **String Length <= numRows:** Characters fit in fewer rows than available
   - `s = "AB"`, `numRows = 3` → Each character in different row

4. **Large numRows:** numRows >= string length
   - `s = "ABCD"`, `numRows = 10` → Each character in separate row (like numRows = 1)

5. **numRows == 2:** Special case of zigzag
   - `s = "PAYPALISHIRING"`, `numRows = 2`
   - Pattern: 0 1 0 1 0 1 ...
   - `Row 0: P, Y, A, I, H, I, G`
   - `Row 1: A, P, L, S, R, N`

### Example Edge Cases:
```
Input: s = "A", numRows = 1, Output: "A"
Input: s = "AB", numRows = 1, Output: "AB"
Input: s = "ABCD", numRows = 2, Output: "ACBD"
Input: s = "ABCD", numRows = 3, Output: "ABCD" (only 4 chars for 3 rows)
Input: s = "ABC", numRows = 4, Output: "ABC"
```

## Related Problems

1. **LeetCode 68 - Text Justification:** Text formatting
2. **LeetCode 443 - String Compression:** String manipulation
3. **LeetCode 12 - Integer to Roman:** Character patterns
4. **LeetCode 273 - Integer to English Words:** Complex string formatting
5. **LeetCode 336 - Palindrome Pairs:** String analysis
## Tags

`#String` `#Simulation` `#Easy`

## Key Takeaways

- Zigzag follows cyclic up-down pattern
- Character placement depends on position in cycle
- Cycle length = 2 * (numRows - 1)
- Can use direction flag (down/up) or mathematical formula
- Handle special case numRows == 1
- Time and space complexity O(n)
- Two implementation approaches: iterative direction or mathematical formula
