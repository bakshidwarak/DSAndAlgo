# Strobogrammatic Number (LeetCode 246)

## Problem Statement
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

**Valid mappings when rotated 180 degrees:**
- 0 → 0
- 1 → 1
- 8 → 8
- 6 → 9
- 9 → 6

## Examples
```
Input: "69"
Output: true
Explanation: 69 rotated 180 degrees is 69 (6→9, 9→6)

Input: "88"
Output: true
Explanation: 88 rotated 180 degrees is 88

Input: "962"
Output: false
Explanation: 962 rotated 180 degrees would be 296 (9→6, 6→9, 2→?)
              But 2 has no valid rotation

Input: "0"
Output: true

Input: "1"
Output: true
```

## Key Insights
1. A strobogrammatic number is a palindrome with a constraint: rotated digits must match
2. The key insight is to check that for each digit at position i and position n-1-i, they form a valid strobogrammatic pair
3. Invalid digits (2, 3, 4, 5, 7) cannot be in strobogrammatic numbers
4. Only digits 0, 1, 6, 8, 9 can be part of a strobogrammatic number
5. The mapping table can be used: subs[digit] = what it rotates to

## Algorithm Steps

### Approach: Two-Pointer Matching
1. Create a mapping array: subs[0]=0, subs[1]=1, subs[6]=9, subs[8]=8, subs[9]=6, others=-1
2. Use two pointers: start (left) and end (right)
3. While start <= end:
   - Get digit at start position
   - Get digit at end position
   - Check if digit at end position, when rotated, equals digit at start position
   - If not, return false
   - Move pointers towards center
4. If loop completes, return true

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through the string with two pointers
- **Space Complexity:** O(1) - Only using two pointers and a fixed mapping array

## ASCII Visualization

```
String: "69"
Index:   0 1

Step 1: start=0, end=1
        curr = '6' (digit 6)
        endNum = '9' (digit 9)
        subs[9] = 6 ✓ Match!
        start++, end--

Step 2: start > end, loop ends
Result: true

---

String: "962"
Index:   0 1 2

Step 1: start=0, end=2
        curr = '9'
        endNum = '2'
        subs[2] = -1 (invalid)
        subs[2] != 9, return false

Result: false

---

String: "88"
Index:   0 1

Step 1: start=0, end=1
        curr = '8'
        endNum = '8'
        subs[8] = 8 ✓ Match!
        start++, end--

Step 2: start > end, loop ends
Result: true

---

Rotation visualization:
Original:  69    88    962
Rotate:    69    88    ???
           (6→9) (8→8) (9→6, 6→9, 2→?)
           Matches! Matches! Invalid!
```

## Code Walkthrough

```java
public boolean isStrobogrammatic(String num) {
    // Mapping of digits to their 180-degree rotations
    // Index is the digit, value is what it rotates to
    // -1 means invalid (no rotation exists)
    int[] subs = new int[] { 0, 1, -1, -1, -1, -1, 9, -1, 8, 6 };
    //              0  1   2   3   4   5   6   7  8  9

    int start = 0;
    int end = num.length() - 1;

    while (start <= end) {
        // Get the digit at start position
        int curr = Integer.parseInt(num.substring(start, start + 1));
        // Get the digit at end position
        int endNum = Integer.parseInt(num.substring(end, end + 1));

        // Check if digit at end, when rotated, matches digit at start
        if (curr != subs[endNum])
            return false;

        start++;
        end--;
    }

    return true;
}
```

## Edge Cases
1. Single digit valid: "0" → true, "1" → true, "8" → true
2. Single digit invalid: "2" → false
3. Even length: "69" → true, "96" → true
4. Odd length: "818" → true, "888" → true
5. Invalid middle digit (odd length): "1819" → false (9→6, not 1)
6. Empty string: "" → true (all pairs matched vacuously)
7. Only invalid digits: "234" → false
8. Leading zeros: "0" → true, "00" → true

## Related Problems
- [Strobogrammatic Number II (Generate all strobogrammatic numbers of n digits)](../sstrobogrammaticnumberII/notes.md)
- [Strobogrammatic Number III (Count strobogrammatic numbers in a range)](../sstrobogrammaticnumberII/notes.md)
- LeetCode 9: Palindrome Number
- [Valid Palindrome](../validpalindrome/notes.md)
## Tags
- String
- Two Pointers
- Math
- Palindrome
- Hash Table
