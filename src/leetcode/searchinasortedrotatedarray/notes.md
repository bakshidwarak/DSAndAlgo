# Search in a Sorted Rotated Array (Recursive Implementation)

## Problem Statement
Given an array that was originally sorted in ascending order and then rotated at some unknown pivot, search for a target value. Return the index if found, otherwise return -1.

This note covers the recursive implementation approach.

## Examples

### Example 1
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

### Example 2
```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

## Key Insights

1. **Recursive binary search**: Use helper function for cleaner code
2. **Three conditions for rotation detection**:
   - No rotation: normal binary search
   - Pivot on right: right half is rotated
   - Pivot on left: left half is rotated
3. **Base case**: When low > high, target not found
4. **Check rotation**: Compare mid with boundaries

## Algorithm Steps

1. Call searchHelper with initial bounds [0, n-1]
2. Base case: if low > high, return -1
3. Calculate mid
4. If nums[mid] == target, return mid
5. Check for rotation patterns:
   - If nums[mid] >= nums[low] <= nums[high]: No rotation
   - If nums[mid] >= nums[low] >= nums[high]: Pivot on right
   - If nums[mid] <= nums[low] <= nums[high]: Pivot on left
6. Recursively search appropriate half
7. Return result

## Complexity Analysis

**Time Complexity:** O(log n)
- Recursive binary search
- Each call eliminates half the search space

**Space Complexity:** O(log n)
- Recursion call stack
- Maximum depth is log n

## ASCII Visualization

```
Array: [4, 5, 6, 7, 0, 1, 2]
Index:  0  1  2  3  4  5  6

Target = 0

Call 1: searchHelper(nums, 0, 0, 6)
mid = 3, nums[3] = 7
Is target? 7 == 0? NO

Check rotation status:
nums[mid]=7, nums[low]=4, nums[high]=2
nums[mid] >= nums[low] && nums[mid] >= nums[high]?
7 >= 4 && 7 >= 2? YES - Pivot on right

Is target in [4, 7]? Is 0 in [4, 7]? NO
Search right: Call searchHelper(nums, 0, 4, 6)

Call 2: searchHelper(nums, 0, 4, 6)
mid = 5, nums[5] = 1
Is target? 1 == 0? NO

Check rotation:
nums[mid]=1, nums[low]=4, nums[high]=2
nums[mid] <= nums[low] && nums[mid] <= nums[high]?
1 <= 4 && 1 <= 2? YES - Pivot on left

Is target in [1, 2]? Is 0 in [1, 2]? NO
Search left: Call searchHelper(nums, 0, 4, 4)

Call 3: searchHelper(nums, 0, 4, 4)
mid = 4, nums[4] = 0
Is target? 0 == 0? YES
Return 4
```

## Code Walkthrough

```java
public int search(int[] nums, int target) {
    return searchHelper(nums, target, 0, nums.length - 1);
}

public int searchHelper(int[] nums, int target, int low, int high) {
    // Base case: target not found
    if (low > high)
        return -1;

    int mid = (low + high) / 2;

    // Check if we found the target
    if (nums[mid] == target)
        return mid;

    // Case 1: No rotation (normal sorted range)
    if (nums[mid] >= nums[low] && nums[mid] <= nums[high]) {
        if (target < nums[mid]) {
            return searchHelper(nums, target, low, mid - 1);
        } else {
            return searchHelper(nums, target, mid + 1, high);
        }
    }

    // Case 2: Pivot on right (nums[mid] >= nums[low] >= nums[high])
    if (nums[mid] >= nums[low] && nums[mid] >= nums[high]) {
        // Left half is sorted
        if (target >= nums[low] && target <= nums[mid]) {
            return searchHelper(nums, target, low, mid - 1);
        } else {
            return searchHelper(nums, target, mid + 1, high);
        }
    }

    // Case 3: Pivot on left (nums[mid] <= nums[low] <= nums[high])
    if (nums[mid] <= nums[low] && nums[mid] <= nums[high]) {
        // Right half is sorted
        if (target <= nums[high] && target > nums[mid]) {
            return searchHelper(nums, target, mid + 1, high);
        } else {
            return searchHelper(nums, target, low, mid - 1);
        }
    }

    return -1;
}
```

## Rotation Detection Logic

```
Three possible conditions:

1. NO ROTATION: nums[mid] >= nums[low] && nums[mid] <= nums[high]
   [1, 2, 3, 4, 5]
   All three maintain order

2. PIVOT ON RIGHT: nums[mid] >= nums[low] && nums[mid] >= nums[high]
   [3, 4, 5, 1, 2]
   mid(5) >= low(3) and mid(5) >= high(2)
   Left half is sorted

3. PIVOT ON LEFT: nums[mid] <= nums[low] && nums[mid] <= nums[high]
   [4, 5, 6, 1, 2, 3]
   mid(6) <= low(4)? NO, this doesn't work

   Actually for [4, 5, 1, 2, 3]:
   mid(1) <= low(4) and mid(1) <= high(3)
   Right half is sorted
```

## Edge Cases

1. **Single element**: [1], target=1 -> 0
2. **No rotation**: [1,2,3,4,5], target=3 -> 2
3. **Full rotation**: [5,1,2,3,4], target=1 -> 1
4. **Not found**: [4,5,6,7,0,1,2], target=3 -> -1

## Iterative Alternative

```java
public int searchIterative(int[] nums, int target) {
    int start = 0, end = nums.length - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) return mid;

        if (nums[mid] <= nums[end]) {
            if (target > nums[mid] && target <= nums[end]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        } else {
            if (target < nums[mid] && target >= nums[start]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
    return -1;
}
```

## Related Problems

- 33: Search in Rotated Sorted Array (primary solution)
- 81: Search in Rotated Sorted Array II (with duplicates)
- 153: Find Minimum in Rotated Sorted Array
- 154: Find Minimum in Rotated Sorted Array II
## Tags

`medium` `binary-search` `recursion` `rotation` `divide-and-conquer`
