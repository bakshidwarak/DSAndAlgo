# 35. Search Insert Position

## Problem Statement
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

Assumptions:
- Array is sorted in ascending order
- No duplicates in the array
- Time complexity should be O(log n)

## Examples

### Example 1
```
Input: nums = [1,3,5,6], target = 5
Output: 2
Explanation: 5 is found at index 2
```

### Example 2
```
Input: nums = [1,3,5,6], target = 2
Output: 1
Explanation: 2 should be inserted at index 1 to maintain order
```

### Example 3
```
Input: nums = [1,3,5,6], target = 7
Output: 4
Explanation: 7 should be inserted at the end (index 4)
```

### Example 4
```
Input: nums = [1,3,5,6], target = 0
Output: 0
Explanation: 0 should be inserted at the beginning (index 0)
```

## Key Insights

1. **Binary search applies**: Array is sorted, so O(log n) is achievable
2. **Find position, not just existence**: Need to identify insertion point
3. **Handle edge cases**: Target before first, after last, or in middle
4. **Boundary conditions**: When target is not found, determine insert position

## Algorithm Steps

1. Handle edge cases:
   - If target < first element, return 0
   - If target > last element, return length
2. Use binary search with modified logic
3. When target found, return mid
4. When target not found:
   - Check if position between mid-1 and mid
   - Return the position where target should be inserted
5. Continue until target is found or insert position is determined

## Complexity Analysis

**Time Complexity:** O(log n)
- Binary search algorithm
- Each iteration eliminates half the remaining elements

**Space Complexity:** O(1)
- Only using a few pointer variables
- No additional data structures

## ASCII Visualization

```
Array: [1, 3, 5, 6]
Index:  0  1  2  3

Target = 2 (to be inserted)

Iteration 1:
start=0, end=3, mid=1
nums[mid]=3, target=2
3 > 2? YES
Is there element between mid-1 and mid where target fits?
nums[0]=1 < 2 < nums[1]=3? YES
Return 1

Visual:
[1, 2, 3, 5, 6]
 0  1  2  3  4

Position 1 is correct for inserting 2
```

## Code Walkthrough

```java
public int searchInsert(int[] nums, int target) {
    // Edge case: target is before the first element
    if (nums[0] > target)
        return 0;

    // Edge case: target is after the last element
    if (nums[nums.length - 1] < target)
        return nums.length;

    // Binary search for target or insert position
    return helper(nums, 0, nums.length - 1, target);
}

public int helper(int[] nums, int start, int end, int target) {
    int mid = (start + end) / 2;

    // Found the target
    if (nums[mid] == target) {
        return mid;
    }

    // Check if target should be inserted between mid-1 and mid
    if (mid > 0 && nums[mid - 1] < target && nums[mid] > target) {
        return mid;
    }

    // Recursive case: search left or right
    if (nums[mid] < target) {
        // Target is in the right half
        return helper(nums, mid + 1, end, target);
    } else {
        // Target is in the left half
        return helper(nums, start, mid - 1, target);
    }
}
```

## Detailed Walkthrough for Example 2

```
Input: nums = [1,3,5,6], target = 2

Call helper(nums, 0, 3, 2):
  mid = (0 + 3) / 2 = 1
  nums[1] = 3
  3 == 2? NO

  mid > 0? YES (mid = 1)
  nums[0] < 2? 1 < 2? YES
  nums[1] > 2? 3 > 2? YES
  All conditions met - return 1

Answer: 1 (insert at index 1)
Result array: [1, 2, 3, 5, 6]
```

## Edge Cases

1. **Target before first element**: [1,3,5,6], target=0 -> 0
2. **Target after last element**: [1,3,5,6], target=7 -> 4
3. **Target at beginning**: [1,3,5,6], target=1 -> 0
4. **Target at end**: [1,3,5,6], target=6 -> 3
5. **Single element (target less)**: [1], target=0 -> 0
6. **Single element (target greater)**: [1], target=2 -> 1
7. **Single element (target equal)**: [1], target=1 -> 0

## Iterative Alternative

```java
public int searchInsertIterative(int[] nums, int target) {
    int left = 0, right = nums.length;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;  // This gives the insert position directly
}
```

This iterative approach is cleaner:
- `left` will be the insertion position
- No need for edge case handling
- More standard binary search template

## Related Problems

- 34: Find First and Last Position of Element in Sorted Array
- 278: First Bad Version
- 702: Search in a Sorted Array of Unknown Size
- 704: Binary Search

## Tags

`easy` `binary-search` `array` `sorted-array` `insertion`
