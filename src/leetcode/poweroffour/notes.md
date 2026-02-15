# Power of Four - LeetCode Problem 342

## Problem Statement
Given an integer (signed 32-bit), write a function to check whether it is a power of 4.

Constraints:
- Input is a 32-bit signed integer
- Follow-up: Could you solve it without loops/recursion?

## Examples

**Example 1:**
- Input: `16`
- Output: `true`
- Explanation: 4^2 = 16

**Example 2:**
- Input: `5`
- Output: `false`
- Explanation: 5 is not a power of 4

**Example 3:**
- Input: `1`
- Output: `true`
- Explanation: 4^0 = 1

## Key Insights
1. **Power of 2 Check**: 4^n is always a power of 2 (2^(2n))
2. **Bit Manipulation**: A power of 4 must be a power of 2 (has exactly one bit set)
3. **Odd Bit Position**: For 4^n, the set bit must be at odd position (0, 2, 4, 6, ...) from right
4. **No Loops Solution**: Use bitwise operations and mathematical properties

## Algorithm Steps

### Approach 1: Check if Power of 2, then verify odd position
1. Check if num is power of 2: `(num & (num-1)) == 0`
2. Find the position of the single set bit
3. Check if position is even (0-indexed, so 4^n has bits at 0, 2, 4, 6...)
4. Use pattern: 0x55555555 = binary 01010101... matches odd positions

**Pseudocode:**
```
function isPowerOfFour(num):
    if num <= 0:
        return false

    // Check if power of 2
    if (num & (num - 1)) != 0:
        return false

    // Check if power of 4 (odd position bit)
    // 0x55555555 = 1010...10 in binary (has 1s at positions 0,2,4,6,...)
    if (num & 0x55555555) == 0:
        return false

    return true
```

### Approach 2: Mathematical approach
- Use logarithm: `log4(num) = log(num) / log(4)` should be integer
- Check if result is integer by comparing with its integer cast

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(1) - constant time operations |
| Space Complexity | O(1) - only variables |

**Time Analysis:**
- All operations (bitwise, comparisons) are O(1)
- No loops or recursion

## ASCII Visualization

```
Power of 4 Check:

Numbers: 1, 4, 16, 64, 256...
Binary:
1 = 00000001 (4^0, bit at position 0)
4 = 00000100 (4^1, bit at position 2)
16 = 00010000 (4^2, bit at position 4)
64 = 01000000 (4^3, bit at position 6)

Pattern for 0x55555555:
Positions: 31 30 29 28 27 26 25 24 ... 7 6 5 4 3 2 1 0
Pattern:    0  1  0  1  0  1  0  1 ... 0 1 0 1 0 1 0 1
Mask:      01010101010101010101010101010101

For num = 16 (00010000):
- Check power of 2: 16 & 15 = 00010000 & 00001111 = 0 ✓
- Check odd position: 16 & 0x55555555 = 00010000 & ...01010101 = 00010000 ✓

For num = 8 (00001000) - power of 2 but not 4:
- Check power of 2: 8 & 7 = 00001000 & 00000111 = 0 ✓
- Check odd position: 8 & 0x55555555 = 00001000 & ...01010101 = 0 ✗
```

## Code Walkthrough

```java
public boolean isPowerOfFour(int num) {
    // Negative numbers and 2 are not powers of 4
    if (num <= 0 || num == 2)
        return false;

    // Single number 1 is 4^0
    if (num == 1)
        return true;

    // Isolate the rightmost set bit
    int y = num & ~(num - 1);

    // Check if isolating bit gives the original number
    // This verifies num is a power of 2
    int k = y ^ num;  // Should be 0 if num is power of 2
    if (k != 0)
        return false;

    // Count bit position (must be even for power of 4)
    k = 1;
    while (y != 0) {
        y = y >>> 1;  // Right shift by 1
        k++;  // Increment position counter
    }

    // For power of 4, position should be odd (1, 3, 5, 7, ...)
    // Or equivalently, position count should be even
    return k % 2 == 0;
}
```

## Edge Cases

1. **Zero**: `0` -> `false` (not a power of 4)
2. **Negative**: `-4` -> `false` (no negative powers of 4)
3. **One**: `1` -> `true` (4^0 = 1)
4. **Four**: `4` -> `true` (4^1 = 4)
5. **Two**: `2` -> `false` (power of 2 but not 4)
6. **Eight**: `8` -> `false` (power of 2 but not 4)
7. **Max int**: `2147483647` (INT_MAX) -> `false`
8. **Min int**: `-2147483648` (INT_MIN) -> `false`

## Related Problems

1. **LeetCode 231**: Power of Two - Check if power of 2
2. **LeetCode 326**: Power of Three - Check if power of 3
3. **LeetCode 50**: Pow(x, n) - Calculate power
4. **LeetCode 367**: Valid Perfect Square - Similar bit manipulation
## Tags

- Bit Manipulation
- Math
- Number Theory
- No Recursion/Loop
- Difficulty: Easy
- Acceptance: ~35%
