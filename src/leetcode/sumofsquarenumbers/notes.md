# Sum of Square Numbers (LeetCode 633)

## Problem Statement
Given a non-negative integer c, determine if there exist two integers a and b such that a² + b² = c.

## Examples
```
Example 1:
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5

Example 2:
Input: c = 3
Output: false

Example 3:
Input: c = 0
Output: true
Explanation: 0 * 0 + 0 * 0 = 0

Example 4:
Input: c = 2
Output: true
Explanation: 1 * 1 + 1 * 1 = 2
```

## Key Insights
1. We need to find two numbers a and b such that a² + b² = c
2. The maximum value of a is sqrt(c), similarly for b
3. This is similar to a Two Sum problem but with squares
4. We can use a two-pointer approach: start with 0 and sqrt(c)
5. If sum is too small, increase the first pointer; if too large, decrease the second
6. The recursive solution simulates two-pointer approach

## Algorithm Steps

### Approach: Two-Pointer (Recursive Implementation)
1. Initialize start = 0, end = sqrt(c)
2. Use a recursive helper function with parameters: start, end, target
3. Base case: if start > end, return false
4. Calculate curr = start² + end² - c
5. If curr == 0, return true (found the pair)
6. If curr > 0, decrease end (sum too large), recurse with (start, end-1, c)
7. If curr < 0, increase start (sum too small), recurse with (start+1, end, c)

## Complexity Analysis
- **Time Complexity:** O(sqrt(c)) - At most sqrt(c) iterations
- **Space Complexity:** O(sqrt(c)) - Recursion depth in worst case

## ASCII Visualization

```
Finding two squares that sum to 5:
c = 5, sqrt(5) ≈ 2.236, so end = 2

start = 0, end = 2
Step 1: curr = 0² + 2² - 5 = 4 - 5 = -1 (too small)
        Increase start: start = 1

start = 1, end = 2
Step 2: curr = 1² + 2² - 5 = 1 + 4 - 5 = 0 (found!)
        Return true

Visualization:
  0² + 0² = 0   (too small)
  0² + 1² = 1   (too small)
  0² + 2² = 4   (too small)
  1² + 1² = 2   (too small)
  1² + 2² = 5   ✓ FOUND!

---

Finding two squares that sum to 3:
c = 3, sqrt(3) ≈ 1.732, so end = 1

start = 0, end = 1
Step 1: curr = 0² + 1² - 3 = 0 + 1 - 3 = -2 (too small)
        Increase start: start = 1

start = 1, end = 1
Step 2: curr = 1² + 1² - 3 = 1 + 1 - 3 = -1 (too small)
        Increase start: start = 2

start = 2, end = 1
Step 3: start > end, return false
        No solution found
```

## Code Walkthrough

```java
public boolean judgeSquareSum(int c) {
    // Start with smallest and largest possible values
    return helper(0, (int) Math.sqrt(c), c);
}

public boolean helper(int start, int end, int num) {
    // Base case: all pairs exhausted
    if (start > end)
        return false;

    // Calculate current sum of squares
    int curr = start * start + end * end - num;

    if (curr == 0) {
        // Found a valid pair
        return true;
    }
    if (curr > 0) {
        // Sum is too large, decrease end
        return helper(start, end - 1, num);
    }
    // Sum is too small, increase start
    return helper(start + 1, end, num);
}
```

## Iterative Alternative

```java
public boolean judgeSquareSum(int c) {
    long left = 0;
    long right = (long) Math.sqrt(c);

    while (left <= right) {
        long sum = left * left + right * right;
        if (sum == c) {
            return true;
        } else if (sum < c) {
            left++;
        } else {
            right--;
        }
    }

    return false;
}
```

## Edge Cases
1. c = 0: 0 + 0 = 0 → true
2. c = 1: 0 + 1 = 1 or 1 + 0 = 1 → true
3. c = 2: 1 + 1 = 2 → true
4. Prime numbers: c = 7 → false (not expressible)
5. Perfect square: c = 4 → true (0 + 4 = 4 or 4 + 0 = 4)
6. Large numbers: c = 2147483647 → Requires long to avoid overflow

## Important Note on Overflow
When calculating start * start + end * end, it might overflow for large values. Use long:
```java
long sum = (long) start * start + (long) end * end;
```

## Related Problems
- [Two Sum II](../twosum2/notes.md) - Input Array Is Sorted
- [3Sum](../threesum/notes.md)
- LeetCode 1099: Two Sum Less Than K
- LeetCode 163: Missing Ranges
## Tags
- Math
- Two Pointers
- Binary Search
- Fermat's Theorem on Sums of Two Squares
