# Power of Three - LeetCode Problem 326

## Problem Statement
Given an integer, write a function to determine if it is a power of 3.

Constraints:
- Input is a 32-bit signed integer

## Examples

**Example 1:**
- Input: `27`
- Output: `true`
- Explanation: 3^3 = 27

**Example 2:**
- Input: `0`
- Output: `false`

**Example 3:**
- Input: `9`
- Output: `true`
- Explanation: 3^2 = 9

## Key Insights
1. **Iterative Division**: Repeatedly divide by 3 until we get 1 (power of 3) or a non-integer
2. **Mathematical Approach**: Use logarithm to check if log base 3 is an integer
3. **Maximum Power of 3**: In 32-bit integer, max power of 3 is 3^19 = 1,162,261,467
4. **Direct Division**: Integer repetitive division until num becomes 1

## Algorithm Steps

### Approach 1: Iterative Division (Most Common)
1. Handle edge cases (n <= 0)
2. While n is divisible by 3:
   - Divide n by 3
3. Return true if final n equals 1

### Approach 2: Mathematical (Logarithm)
1. Calculate log3(n) = log(n) / log(3)
2. Check if result is an integer (within floating point tolerance)

### Approach 3: Modulo with Maximum Power
1. Calculate maximum power of 3 in 32-bit: 3^19
2. Check if maxPower % n == 0

**Pseudocode:**
```
function isPowerOfThree(n):
    if n <= 0:
        return false

    while n % 3 == 0:
        n = n / 3

    return n == 1
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(log3(n)) - divide by 3 in each iteration |
| Space Complexity | O(1) - only variables |

**Time Analysis:**
- Each iteration: O(1) division and modulo
- Maximum iterations: log3(n) ≈ 20 (since 3^20 > 2^31)

## ASCII Visualization

```
Power of 3 Check:

Powers of 3:
3^0 = 1
3^1 = 3
3^2 = 9
3^3 = 27
3^4 = 81
3^5 = 243
...
3^19 = 1,162,261,467 (max in 32-bit)

Iterative Division Example (n = 27):
27 ÷ 3 = 9 (27 % 3 == 0, continue)
 9 ÷ 3 = 3 (9 % 3 == 0, continue)
 3 ÷ 3 = 1 (3 % 3 == 0, continue)
 1 (n == 1, return true)

Iterative Division Example (n = 10):
10 % 3 = 1 (not divisible by 3, stop)
10 != 1, return false

Division Tree:
       27
      / | \
     9  9  9
    / | \
   3  3  3
  / | \
 1  1  1
(After all divisions by 3, we get 1)
```

## Code Walkthrough

```java
public boolean isPowerOfThree(int n) {
    // Handle non-positive numbers
    if (n <= 0)
        return false;

    // Repeatedly divide by 3
    while (n % 3 == 0) {
        n = n / 3;
    }

    // If final value is 1, it was power of 3
    return n == 1;
}

// Alternative approach using logarithm
public boolean isPowerOfThreeLog(int n) {
    if (n <= 0)
        return false;

    // Calculate log base 3
    double logResult = Math.log(n) / Math.log(3);
    double rounded = Math.round(logResult);

    // Check if it's close to an integer (within floating point tolerance)
    return Math.abs(logResult - rounded) < 1e-10;
}

// Alternative approach using max power
public boolean isPowerOfThreeMaxPower(int n) {
    if (n <= 0)
        return false;

    // Maximum power of 3 in 32-bit integer
    int maxPowerOf3 = 1162261467; // 3^19

    return maxPowerOf3 % n == 0;
}
```

## Edge Cases

1. **Zero**: `0` -> `false`
2. **One**: `1` -> `true` (3^0 = 1)
3. **Three**: `3` -> `true` (3^1 = 3)
4. **Negative**: `-27` -> `false`
5. **Not Power of 3**: `10` -> `false`
6. **Powers of 3**: 1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467
7. **Large Number**: `2147483647` (INT_MAX) -> Check if divisible by 3
8. **Min int**: `-2147483648` (INT_MIN) -> `false`

## Related Problems

1. **LeetCode 231**: Power of Two - Similar for base 2
2. **LeetCode 342**: Power of Four - Similar for base 4
3. **LeetCode 50**: Pow(x, n) - Calculate power
4. **LeetCode 367**: Valid Perfect Square - Similar divisibility check

## Tags

- Math
- Number Theory
- Division
- Logarithm
- Difficulty: Easy
- Acceptance: ~40%
