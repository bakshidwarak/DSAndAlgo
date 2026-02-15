# 581. Shortest Unsorted Continuous Subarray

## Problem Statement
Given an integer array nums, find the shortest continuous subarray that if you only sort this subarray in ascending order, the entire array will be sorted in ascending order.

Return the length of that subarray. If the array is already sorted, return 0.

## Examples

### Example 1
```
Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6,4,8,10,9] to make the array sorted
Result after sorting that part: [2,4,6,8,9,10,15]
```

### Example 2
```
Input: nums = [1,2,3,4]
Output: 0
Explanation: The array is already sorted
```

### Example 3
```
Input: nums = [1]
Output: 0
Explanation: Single element is already sorted
```

## Key Insights

1. **Find boundaries**: Find the leftmost and rightmost positions that are out of order
2. **Compare with sorted**: Sort the array and compare with original to find differences
3. **Two-pointer approach**: Can be solved without explicit sorting by finding boundaries
4. **Min/Max tracking**: The subarray to sort lies between positions where min > left and max < right

## Algorithm Steps

### Approach 1: Sorting
1. Create a sorted copy of the array
2. Compare original with sorted version
3. Find leftmost position where they differ
4. Find rightmost position where they differ
5. Return length = right - left + 1

### Approach 2: Two-Pointer (O(1) space)
1. Scan from left to find rightmost position that's smaller than an element to its right
2. Scan from right to find leftmost position that's larger than an element to its left
3. Calculate length between these positions

## Complexity Analysis

**Time Complexity:**
- Approach 1: O(n log n) due to sorting
- Approach 2: O(n) with two passes

**Space Complexity:**
- Approach 1: O(n) for sorted copy
- Approach 2: O(1) only using pointers

## ASCII Visualization

```
Original: [2,6,4,8,10,9,15]
Index:     0 1 2 3 4  5 6
Sorted:   [2,4,6,8,9,10,15]
Index:     0 1 2 3 4  5 6

Compare positions:
Index 0: 2 == 2? YES
Index 1: 6 != 4? NO - First mismatch at 1
Index 2: 4 != 6? NO
Index 3: 8 == 8? YES
Index 4: 10 != 9? NO
Index 5: 9 != 10? NO
Index 6: 15 == 15? YES

Last mismatch at index 5

Length = 5 - 1 + 1 = 5

Subarray to sort: [6,4,8,10,9] (indices 1-5)
```

## Code Walkthrough

```java
// Approach 1: Using sorting (simpler, more intuitive)
public int findUnsortedSubarray(int[] nums) {
    // Create a sorted copy
    int[] sorted = nums.clone();
    Arrays.sort(sorted);

    // Find leftmost position where they differ
    int left = 0;
    while (left < nums.length && nums[left] == sorted[left]) {
        left++;
    }

    // If entire array is sorted
    if (left == nums.length)
        return 0;

    // Find rightmost position where they differ
    int right = nums.length - 1;
    while (right >= 0 && nums[right] == sorted[right]) {
        right--;
    }

    // Length of unsorted subarray
    return right - left + 1;
}
```

## Two-Pointer Alternative (O(n) Time, O(1) Space)

```java
public int findUnsortedSubarrayOptimal(int[] nums) {
    int n = nums.length;

    // Find rightmost position that breaks ascending order from left
    int end = -1;
    int maxSoFar = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
        if (nums[i] < maxSoFar) {
            end = i;
        } else {
            maxSoFar = nums[i];
        }
    }

    // If array is already sorted
    if (end == -1)
        return 0;

    // Find leftmost position that breaks ascending order from right
    int start = 0;
    int minSoFar = Integer.MAX_VALUE;
    for (int i = n - 1; i >= 0; i--) {
        if (nums[i] > minSoFar) {
            start = i;
        } else {
            minSoFar = nums[i];
        }
    }

    return end - start + 1;
}
```

### How Two-Pointer Works

```
Array: [2,6,4,8,10,9,15]

Left scan (finding end):
maxSoFar = -INF, end = -1
i=0: nums[0]=2 < -INF? NO, maxSoFar=2
i=1: nums[1]=6 < 2? NO, maxSoFar=6
i=2: nums[2]=4 < 6? YES, end=2
i=3: nums[3]=8 < 6? NO, maxSoFar=8
i=4: nums[4]=10 < 8? NO, maxSoFar=10
i=5: nums[5]=9 < 10? YES, end=5
i=6: nums[6]=15 < 10? NO, maxSoFar=15

end = 5 (rightmost position out of order)

Right scan (finding start):
minSoFar = INF, start = 0
i=6: nums[6]=15 > INF? NO, minSoFar=15
i=5: nums[5]=9 > 15? NO, minSoFar=9
i=4: nums[4]=10 > 9? YES, start=4
i=3: nums[3]=8 > 9? NO, minSoFar=8
i=2: nums[2]=4 > 8? NO, minSoFar=4
i=1: nums[1]=6 > 4? YES, start=1
i=0: nums[0]=2 > 4? NO, minSoFar=2

start = 1 (leftmost position out of order)

Length = 5 - 1 + 1 = 5
```

## Edge Cases

1. **Already sorted**: [1,2,3,4,5] -> 0
2. **Reverse sorted**: [5,4,3,2,1] -> 5
3. **Single element**: [1] -> 0
4. **Two elements sorted**: [1,2] -> 0
5. **Two elements reverse**: [2,1] -> 2
6. **Duplicates**: [1,2,2,2,3] -> 0

## Comparison of Approaches

| Aspect | Sorting | Two-Pointer |
|--------|---------|-------------|
| Time | O(n log n) | O(n) |
| Space | O(n) | O(1) |
| Code clarity | Very clear | Requires understanding |
| Practical | Good for interviews | Better for production |

## Related Problems

- 88: Merge Sorted Array
- 215: Kth Largest Element in an Array
- 280: Wiggle Sort
## Tags

`medium` `array` `sorting` `two-pointer` `optimization`
