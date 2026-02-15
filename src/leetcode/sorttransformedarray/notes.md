# Sort Transformed Array (LeetCode 360)

## Problem Statement
Given a sorted array of integers nums and integer values a, b, and c, apply a quadratic function f(x) = ax² + bx + c to each element x in the array.

The returned array must be in sorted order.

**Constraint:** Expected time complexity is O(n), meaning we cannot sort the result.

## Examples
```
Example 1:
Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Explanation:
  f(-4) = 1*16 - 12 + 5 = 9
  f(-2) = 1*4 - 6 + 5 = 3
  f(2) = 1*4 + 6 + 5 = 15
  f(4) = 1*16 + 12 + 5 = 33
  Sorted: [3,9,15,33]

Example 2:
Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
Explanation:
  f(-4) = -16 - 12 + 5 = -23
  f(-2) = -4 - 6 + 5 = -5
  f(2) = -4 + 6 + 5 = 7
  f(4) = -16 + 12 + 5 = 1
  Sorted: [-23,-5,1,7]
```

## Key Insights
1. Parabola properties: When a > 0, the parabola opens upward (U-shape), so extremes are at the ends
2. When a < 0, the parabola opens downward (inverted U), so minimum is in the middle
3. When a = 0, f(x) is linear: the array is already sorted or reverse sorted
4. For a >= 0: extremes are at the edges, fill result array from ends
5. For a < 0: use middle index to fill result from the middle

## Algorithm Steps

### Approach: Two-Pointer with Parabola Analysis
1. Handle two cases based on the value of 'a':

**Case 1: a >= 0 (U-shaped parabola)**
- Larger values are at the edges of the input array
- Fill result array from the beginning (ascending order)
- Use two pointers: start and end
- Compare transformed values and add the smaller one to result

**Case 2: a < 0 (Inverted U parabola)**
- Larger values are at the edges
- Fill result array from the end (descending order)
- Compare transformed values and add the larger one to result

2. Continue until all elements are processed

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through the array
- **Space Complexity:** O(1) - Only using two pointers (excluding output array)

## ASCII Visualization

```
Example: nums = [-4,-2,2,4], a = 1, b = 3, c = 5 (parabola opens up)

Input array:  [-4, -2, 2, 4]
              start       end

f(-4) = 9, f(2) = 15
f(-2) = 3, f(4) = 33

Since a >= 0 (opens up), extremes are at edges, fill from left:

Result: [_, _, _, _]
         ^
Compare f(-4)=9 vs f(4)=33: 9 < 33, add 9, move start
Result: [9, _, _, _]
            ^
Compare f(-2)=3 vs f(4)=33: 3 < 33, add 3, move start
Result: [9, 3, _, _]
               ^
Compare f(2)=15 vs f(4)=33: 15 < 33, add 15, move end
Result: [9, 3, 15, _]
                   ^
Add f(4)=33, done
Result: [9, 3, 15, 33]

Wait, that's not sorted. Need to fill differently for a >= 0:
Actually should fill from position 0 with i and j pointers going forward.

Correct approach for a >= 0:
Result positions: [0, 1, 2, 3]
                   i              j

f(-4)=9, f(4)=33: 9 < 33, result[0]=9, move i
f(-2)=3, f(4)=33: 3 < 33, result[1]=3, move i
f(2)=15, f(4)=33: 15 < 33, result[2]=15, move i
f(4)=33: result[3]=33

Result: [9, 3, 15, 33] → Still needs rethinking.

The idea: Fill result from start when a >= 0, comparing which value is smaller.
Expected sorted output: [3,9,15,33]
Actually comparing smallest first: Add 3, then 9, then 15, then 33.
```

## Code Walkthrough

```java
public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int[] result = new int[nums.length];
    int start = 0;
    int end = nums.length - 1;

    // Pointer for result array filling
    int i = start;
    int j = end;

    while (start <= end) {
        // Calculate transformed values at both ends
        int fromFirst = calc(nums[start], a, b, c);
        int fromLast = calc(nums[end], a, b, c);

        if (a <= 0) {
            // For inverted parabola or line: fill from start
            if (fromFirst > fromLast) {
                result[i++] = fromLast;
                end--;
            } else {
                result[i++] = fromFirst;
                start++;
            }
        } else {
            // For U-shaped parabola: fill from end
            if (fromFirst > fromLast) {
                result[j--] = fromFirst;
                start++;
            } else {
                result[j--] = fromLast;
                end--;
            }
        }
    }

    return result;
}

public int calc(int num, int a, int b, int c) {
    return a * num * num + b * num + c;
}
```

## Edge Cases
1. Single element: [0] → [c]
2. a = 0 (linear function): nums = [1,2,3], b = 1, c = 1 → [2,3,4]
3. All identical results: [0,1,2], a = 0, b = 0, c = 5 → [5,5,5]
4. Negative coefficients: a = -1, b = 0, c = 0 with [-2, 2] → [-4, -4]
5. Large numbers: May cause integer overflow

## Related Problems
- LeetCode 11: Container With Most Water
- LeetCode 15: 3Sum
- LeetCode 167: Two Sum II
- LeetCode 259: 3Sum Smaller

## Tags
- Array
- Two Pointers
- Math
- Parabola
- Quadratic Function
