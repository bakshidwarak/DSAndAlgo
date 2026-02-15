# LeetCode 367: Valid Perfect Square

## Problem Statement
Given a positive integer num, write a function which returns True if num is a perfect square else False.

**Note**: Do not use any built-in library function such as sqrt.

## Examples

**Example 1:**
```
Input: 16
Output: true
Explanation: 4 * 4 = 16
```

**Example 2:**
```
Input: 14
Output: false
Explanation: 14 is not a perfect square
```

**Example 3:**
```
Input: 1
Output: true
```

## Key Insights

1. **Binary Search**: Can use binary search instead of sqrt function
2. **Search Space**: Search between 1 and num
3. **Perfect Square Check**: Find i where i*i == num
4. **Efficiency**: O(log n) is much faster than O(sqrt(n)) linear search

## Algorithm Steps

1. **Base Case**: If num == 1, return true

2. **Binary Search**:
   - Set low = 1, high = num
   - Calculate mid = (low + high) / 2
   - Check mid * mid:
     - If equals num: perfect square found
     - If greater than num: search left half
     - If less than num: search right half

3. **Return**: true if found, false if search exhausted

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(log n) - Binary search |
| **Space Complexity** | O(1) - Only a few variables |

Logarithmic complexity is very efficient!

## ASCII Visualization

```
Number: 16

Binary search range: [1, 16]

Step 1: low=1, high=16, mid=8
  8*8 = 64 > 16
  Reduce search space
  high = mid - 1 = 7

Step 2: low=1, high=7, mid=4
  4*4 = 16 == 16 ✓
  Found! Return true

Searching for 14:
Step 1: low=1, high=14, mid=7
  7*7 = 49 > 14
  high = 6

Step 2: low=1, high=6, mid=3
  3*3 = 9 < 14
  low = 4

Step 3: low=4, high=6, mid=5
  5*5 = 25 > 14
  high = 4

Step 4: low=4, high=4, mid=4
  4*4 = 16 > 14
  high = 3

Step 5: low=4, high=3 (low > high)
  Loop ends, not found
  Return false

Visual search:
1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16
^                   ✓ (4*4=16)
Found at 4!

1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16
     ^    ^    ^    X (16 > 14)
     |    |    4*4 is 16, too big
     |    3*3 is 9, too small
     Try middle values

No perfect square found → false
```

## Code Walkthrough

```java
public boolean isPerfectSquare(int num) {
    long low = 1;
    long high = num;
    return isPerfectSquareBinarySearch(num, low, high);
}

private static boolean isPerfectSquareBinarySearch(int num, long low, long high) {
    // Base case: search space exhausted
    if (low > high)
        return false;

    // Calculate middle point
    long mid = (low + high) / 2;

    // Check if mid is the square root
    long square = mid * mid;

    if (square == num) {
        return true;  // Perfect square found
    }

    // Square is too large, search left half
    if (square > num) {
        return isPerfectSquareBinarySearch(num, low, mid - 1);
    }

    // Square is too small, search right half
    return isPerfectSquareBinarySearch(num, mid + 1, high);
}
```

**Execution for 16:**
```
isPerfectSquare(16):
  low = 1, high = 16
  isPerfectSquareBinarySearch(16, 1, 16)

    low = 1, high = 16 (low <= high)
    mid = 8
    square = 64
    64 > 16, recurse left
    isPerfectSquareBinarySearch(16, 1, 7)

      low = 1, high = 7 (low <= high)
      mid = 4
      square = 16
      16 == 16 ✓
      return true

  return true
```

## Edge Cases

1. **1**: `1` → true (1*1 = 1)
2. **4**: `4` → true (2*2 = 4)
3. **2**: `2` → false (not between 1*1 and 2*2)
4. **Large number**: `2147483647` → false
5. **Perfect square of large**: `1000000000` → true (31622^2 ≈ 1B)

## Related Problems

1. **LeetCode 69** - Sqrt(x) (Find integer square root)
2. **LeetCode 374** - Guess Number Higher or Lower (Binary search pattern)
3. **LeetCode 35** - Search Insert Position (Binary search)
4. **LeetCode 33** - Search in Rotated Sorted Array (Advanced binary search)
5. **LeetCode 1064** - Fixed Point (Binary search variant)

## Tags

`Binary Search` `Math` `Easy` `Google` `Amazon` `Apple` `Facebook`

## Alternative Approaches

### Approach 2: Linear Search (Slow)
```java
public boolean isPerfectSquareLinear(int num) {
    int i = 1;
    while (i <= num) {
        int val = i * i;
        if (val == num)
            return true;
        i++;
    }
    return false;
}
```
- Time: O(sqrt(n))
- Space: O(1)
- Simple but slower

### Approach 3: Newton's Method
```java
public boolean isPerfectSquareNewton(int num) {
    if (num < 1) return false;

    long x = num;
    while (x * x > num) {
        x = (x + num / x) / 2;
    }

    return x * x == num;
}
```
- Time: O(log n)
- Space: O(1)
- More complex but elegant

### Approach 4: Using Built-in (Not Allowed)
```java
public boolean isPerfectSquareBuiltin(int num) {
    int sqrt = (int) Math.sqrt(num);
    return sqrt * sqrt == num;
}
```
- Trivial but violates constraint

## Implementation Notes

1. **Use Long**: Prevent integer overflow when computing mid*mid
2. **Binary Search**: Standard implementation with midpoint calculation
3. **Base Case**: Return false when low > high (not found)
4. **Recursion**: Can also use iterative approach

## Optimization Notes

1. **Starting High**: Can optimize high = num/2 for num > 1
2. **Overflow Prevention**: Using long prevents mid*mid overflow
3. **Tight Bounds**: Better initial bounds reduce iterations

## Performance Comparison

```
Approach              Time    Space   Notes
Binary Search        O(log n) O(1)   Optimal, recommended
Newton's Method      O(log n) O(1)   Complex but elegant
Linear Search        O(sqrt(n)) O(1) Simple but slow
Built-in sqrt        O(1)     O(1)   Trivial but not allowed
```

## Iterative Version

```java
public boolean isPerfectSquareIterative(int num) {
    long low = 1;
    long high = num;

    while (low <= high) {
        long mid = (low + high) / 2;
        long square = mid * mid;

        if (square == num) {
            return true;
        } else if (square > num) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return false;
}
```
- Avoids recursion overhead
- Easier to understand
- Same time/space complexity

## Notes

- Classic binary search application
- Tests understanding of search without built-ins
- O(log n) is much better than O(sqrt(n)) linear
- Shows importance of choosing right algorithm
- Good foundation for more complex searches
- Related to math and number theory concepts
