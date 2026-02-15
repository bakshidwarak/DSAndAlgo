# Power of Two - LeetCode Problem 231

## Problem Statement
Given an integer, write a function to determine if it is a power of two.

Constraints:
- Input is a 32-bit signed integer
- Follow-up: Could you solve it without loops/recursion?

## Examples

**Example 1:**
- Input: `1`
- Output: `true`
- Explanation: 2^0 = 1

**Example 2:**
- Input: `16`
- Output: `true`
- Explanation: 2^4 = 16

**Example 3:**
- Input: `218`
- Output: `false`

## Key Insights
1. **Binary Representation**: Powers of 2 have exactly one bit set (e.g., 1=001, 2=010, 4=100, 8=1000)
2. **Bit Trick**: n & (n-1) == 0 only when n is a power of 2
3. **Why it works**: n-1 flips all bits after the rightmost set bit (and the bit itself)
   - For power of 2: n = 1000...0, n-1 = 0111...1, so n & (n-1) = 0
   - For non-power: multiple bits set, so n & (n-1) != 0
4. **O(1) Solution**: No loops needed

## Algorithm Steps

### Approach 1: Bit Manipulation (Optimal)
1. Check if n > 0 (negative numbers and 0 are not powers of 2)
2. Check if `(n & (n-1)) == 0`
3. Return the boolean result

### Approach 2: Iterative Division (Less Optimal)
1. Check if n > 0
2. While n is even:
   - Divide n by 2
3. Return true if final n equals 1

**Pseudocode:**
```
function isPowerOfTwo(n):
    if n <= 0:
        return false

    // n & (n-1) removes the rightmost set bit
    // If only one bit set, result is 0
    return (n & (n - 1)) == 0
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(1) - constant time |
| Space Complexity | O(1) - only variables |

**Time Analysis:**
- All operations are constant time bitwise operations
- No loops or loops with bounded iterations

## ASCII Visualization

```
Power of Two Check using n & (n-1):

Example 1: n = 8 (power of 2)
  n = 1000
n-1 = 0111
  & = 0000 → true

Example 2: n = 6 (not power of 2)
  n = 0110
n-1 = 0101
  & = 0100 → false

Example 3: n = 16 (power of 2)
   n = 10000
 n-1 = 01111
   & = 00000 → true

Powers of Two (Binary):
1 = 0000001 (2^0)
2 = 0000010 (2^1)
4 = 0000100 (2^2)
8 = 0001000 (2^3)
16 = 0010000 (2^4)
32 = 0100000 (2^5)
64 = 1000000 (2^6)

Each has exactly one bit set!

Why n & (n-1) works:
When we subtract 1 from a power of 2:
- The single set bit becomes 0
- All bits to the right become 1

Example: 8 = 1000
     8-1 = 0111
     8 & 7 = 0000
```

## Code Walkthrough

```java
public boolean isPowerOfTwo(int n) {
    // Handle non-positive numbers
    if (n <= 0)
        return false;

    // Isolate the rightmost set bit using n & ~(n-1)
    int y = n & ~(n - 1);

    // XOR with original number
    y = y ^ n;

    // If result is 0, only one bit was set (power of 2)
    return y == 0;
}

// Simplified version using the trick directly
public boolean isPowerOfTwoSimple(int n) {
    if (n <= 0)
        return false;

    // If n is power of 2, n & (n-1) == 0
    return (n & (n - 1)) == 0;
}

// Alternative: Count number of set bits
public boolean isPowerOfTwoBitCount(int n) {
    if (n <= 0)
        return false;

    // Count number of 1s in binary
    // Should be exactly 1 for power of 2
    return Integer.bitCount(n) == 1;
}

// Alternative: Iterative approach (less optimal)
public boolean isPowerOfTwoIterative(int n) {
    if (n <= 0)
        return false;

    while (n % 2 == 0) {
        n = n / 2;
    }

    return n == 1;
}
```

## Edge Cases

1. **Zero**: `0` -> `false`
2. **One**: `1` -> `true` (2^0 = 1)
3. **Two**: `2` -> `true` (2^1 = 2)
4. **Negative**: `-2` -> `false`
5. **Not Power of 2**: `3, 5, 6, 7, 9, 10, 11, 12` -> `false`
6. **Powers of 2**: 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576
7. **Max power in 32-bit**: `1073741824` (2^30) -> `true`
8. **Max int**: `2147483647` (INT_MAX = 2^31 - 1) -> `false`

## Related Problems

1. **LeetCode 326**: Power of Three - Similar concept for base 3
2. **LeetCode 342**: Power of Four - Similar concept for base 4
3. **LeetCode 50**: Pow(x, n) - Calculate power
4. **LeetCode 191**: Number of 1 Bits - Count set bits
5. **LeetCode 338**: Counting Bits - Count 1s in all numbers
6. **LeetCode 2009**: Minimum Number of Operations to Make Array Continuous - Uses power of 2

## Tags

- Bit Manipulation
- Math
- Number Theory
- No Recursion/Loop
- Difficulty: Easy
- Acceptance: ~45%
