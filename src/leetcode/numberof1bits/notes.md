# LeetCode 191: Number of 1 Bits

## Problem Statement
Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

## Examples

**Example 1:**
```
Input: 00000000000000000000000000001011 (11 in decimal)
Output: 3
Explanation: Has three '1' bits
```

**Example 2:**
```
Input: 00000000000000000000000010000000 (128 in decimal)
Output: 1
Explanation: Has one '1' bit
```

**Example 3:**
```
Input: 11111111111111111111111111111101 (-3 in 2's complement)
Output: 31
Explanation: Has thirty-one '1' bits
```

## Key Insights

1. **Bit Manipulation**: Core bit operation problem
2. **2's Complement**: Negative numbers represented using 2's complement
3. **Isolated Bit**: Use `n & (n-1)` to isolate rightmost 1-bit
4. **Count Until Zero**: Continue until all bits are processed

## Algorithm Steps

### Approach 1: Brian Kernighan's Algorithm (Most Efficient)

1. Initialize count = 0
2. While n != 0:
   - Isolate rightmost 1-bit using `n & (n-1)`
   - Remove it from n using XOR
   - Increment count
3. Return count

This removes exactly one 1-bit per iteration, so only runs as many times as there are 1-bits.

### Approach 2: Check Each Bit

1. For each of 32 bits:
   - Check if bit is set using `(n >> i) & 1`
   - If set, increment count

## Complexity Analysis

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| Kernighan's | O(k) | O(1) | k = number of 1-bits |
| Check Each Bit | O(32) | O(1) | Fixed 32 bits to check |
| **Kernighan's is optimal** | O(k) where k ≤ 32 | O(1) | Best for sparse 1-bits |

## ASCII Visualization

```
Example: n = 11 (binary: 1011)

Iteration 1:
  n = 1011 (11)
  n-1 = 1010 (10)
  n & (n-1) = 1011 & 1010 = 1010
  n = 1010, count = 1

Iteration 2:
  n = 1010 (10)
  n-1 = 1001 (9)
  n & (n-1) = 1010 & 1001 = 1000
  n = 1000, count = 2

Iteration 3:
  n = 1000 (8)
  n-1 = 0111 (7)
  n & (n-1) = 1000 & 0111 = 0000
  n = 0000, count = 3

n = 0, loop ends
Return 3

Visual bit removal:
1011 → remove rightmost 1 → 1010
1010 → remove rightmost 1 → 1000
1000 → remove rightmost 1 → 0000
Total 1-bits removed: 3
```

## Code Walkthrough

### Solution 1: Kernighan's Algorithm (Optimal)

```java
public int hammingWeight(int n) {
    int count = 0;

    while (n != 0) {
        // Isolate rightmost 1-bit: n & ~(n-1)
        int y = n & ~(n - 1);

        // Remove the isolated bit using XOR
        n = y ^ n;

        // Increment count
        count++;
    }

    return count;
}
```

**Key Operations:**
- `n & ~(n-1)`: Isolates the rightmost 1-bit
  - If n = 1011, n-1 = 1010
  - ~(n-1) = ...11110101
  - n & ~(n-1) = 0001 (isolated bit)
- `y ^ n`: Removes the isolated bit (XOR)
  - 0001 ^ 1011 = 1010

### Alternative: Simpler Kernighan's

```java
public int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        // Direct: n & (n-1) removes rightmost 1-bit
        n = n & (n - 1);
        count++;
    }
    return count;
}
```

### Solution 2: Check Each Bit

```java
public int hammingWeight(int n) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
        // Check if i-th bit is set
        if (((n >> i) & 1) == 1) {
            count++;
        }
    }
    return count;
}
```

### Solution 3: Built-in Function

```java
public int hammingWeight(int n) {
    return Integer.bitCount(n);  // Java built-in
    // return Integer.popcount(n);  // Python equivalent
}
```

## Edge Cases

1. **Zero**: 0 → 0 (no 1-bits)
2. **One**: 1 → 1 (one 1-bit)
3. **All 1's**: 0xFFFFFFFF (32-bit all 1's) → 32
4. **Powers of 2**: 1, 2, 4, 8, 16... → 1 (single 1-bit)
5. **Negative Numbers**: Use 2's complement representation
6. **Alternating bits**: 0xAAAAAAAA → 16 (every other bit is 1)

## Related Problems

1. **LeetCode 338** - Counting Bits (Count for all numbers 0 to n)
2. **LeetCode 1052** - Grumpy Bookstore Owner (Bit manipulation pattern)
3. **LeetCode 401** - Binary Watch (Generate valid times from bits)
4. **LeetCode 411** - Minimum Unique Word Abbreviation
5. **LeetCode 1107** - New Users Daily Count (Bit operations)

## Tags

`Bit Manipulation` `Easy` `Google` `Amazon` `Microsoft` `Adobe` `Facebook`

## Performance Comparison

```
Number: 11 (binary 1011, three 1-bits)

Kernighan's: 3 iterations
Check Each Bit: 32 iterations

Number: 1 (binary 0001, one 1-bit)

Kernighan's: 1 iteration
Check Each Bit: 32 iterations

Number: 2147483647 (binary 01111...1111, 31 1-bits)

Kernighan's: 31 iterations
Check Each Bit: 32 iterations
```

## Bit Manipulation Techniques

1. **Check if i-th bit is set**: `(n >> i) & 1`
2. **Set i-th bit**: `n | (1 << i)`
3. **Clear i-th bit**: `n & ~(1 << i)`
4. **Toggle i-th bit**: `n ^ (1 << i)`
5. **Remove rightmost 1-bit**: `n & (n-1)`
6. **Isolate rightmost 1-bit**: `n & -n` or `n & ~(n-1)`

## Follow-up Question

**"If this function is called many times, how would you optimize it?"**

**Answer**: Use lookup table (memoization)
```java
private int[] cache = new int[256];

public int hammingWeight(int n) {
    if (cache[n & 0xFF] == 0) {
        cache[n & 0xFF] = popcount(n & 0xFF);
    }
    // For 32-bit number, break into 4 bytes and sum counts
    return cache[n & 0xFF] + cache[(n >> 8) & 0xFF] +
           cache[(n >> 16) & 0xFF] + cache[(n >> 24) & 0xFF];
}
```

## Notes

- Kernighan's algorithm is most efficient for sparse 1-bits
- Direct check is more intuitive but less efficient
- Java's `Integer.bitCount()` is optimized at hardware level
- This is a classic bit manipulation problem
- Understanding 2's complement is crucial for negative numbers
- Great foundation for understanding bit operations
