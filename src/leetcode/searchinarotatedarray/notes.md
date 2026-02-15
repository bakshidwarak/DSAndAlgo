# 33. Search in Rotated Sorted Array

## Problem Statement
Suppose an array sorted in ascending order is rotated at some unknown pivot. Given a target value to search, return the index if found, otherwise return -1.

Assumptions:
- No duplicates exist in the array
- Time complexity should be O(log n)

## Examples

### Example 1
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Explanation: Element 0 is at index 4
```

### Example 2
```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Explanation: Element 3 is not present
```

### Example 3
```
Input: nums = [1], target = 1
Output: 0
```

## Key Insights

1. **Binary search still works**: Despite rotation, we can identify which half is sorted
2. **Identify sorted half**: Check if mid <= end to know if right half is sorted
3. **Target in sorted half**: Check if target is in the sorted half's range
4. **Eliminate half**: Based on target location, eliminate left or right half
5. **O(log n) complexity**: Binary search guarantees logarithmic time

## Algorithm Steps

1. Initialize start = 0, end = length - 1
2. While start <= end:
   - Calculate mid = start + (end - start) / 2
   - If nums[mid] == target, return mid
   - Check if right half is sorted (nums[mid] <= nums[end]):
     - If yes, check if target is in range [nums[mid], nums[end]]
     - If yes, search right (start = mid + 1)
     - Otherwise, search left (end = mid - 1)
   - Otherwise, left half is sorted:
     - Check if target is in range [nums[start], nums[mid]]
     - If yes, search left (end = mid - 1)
     - Otherwise, search right (start = mid + 1)

## Complexity Analysis

**Time Complexity:** O(log n)
- Binary search eliminates half the array each iteration
- At most log n iterations

**Space Complexity:** O(1)
- Only using pointers
- No additional data structures

## ASCII Visualization

```
Original sorted array: [0, 1, 2, 4, 5, 6, 7]

After rotation at pivot 3: [4, 5, 6, 7, 0, 1, 2]
                            0  1  2  3  4  5  6

Search for target = 0:

Iteration 1:
start=0, end=6, mid=3
nums[mid]=7, target=0
Right half [7, 0, 1, 2] is sorted? nums[3]=7 <= nums[6]=2? NO
Left half [4, 5, 6, 7] is sorted? nums[0]=4 <= nums[3]=7? YES
Target in left half [4, 7]? 0 in [4, 7]? NO
So search right: start=4

Iteration 2:
start=4, end=6, mid=5
nums[mid]=1, target=0
Right half [1, 2] is sorted? nums[5]=1 <= nums[6]=2? YES
Target in right half [1, 2]? 0 in [1, 2]? NO
So search left: end=4

Iteration 3:
start=4, end=4, mid=4
nums[mid]=0, target=0
Found! Return 4
```

## Code Walkthrough

```java
public static int search(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2;

        // Check if we found the target
        if (nums[mid] == target)
            return mid;

        // Determine which half is sorted
        if (nums[mid] <= nums[end]) {
            // Right half is sorted [mid, end]
            if (nums[mid] < target && target <= nums[end]) {
                // Target is in the sorted right half
                start = mid + 1;
            } else {
                // Target is in the left half
                end = mid - 1;
            }
        } else {
            // Left half is sorted [start, mid]
            if (nums[mid] > target && target >= nums[start]) {
                // Target is in the sorted left half
                end = mid - 1;
            } else {
                // Target is in the right half
                start = mid + 1;
            }
        }
    }

    return -1;  // Target not found
}
```

## Edge Cases

1. **Single element**: [1], target=1 -> 0
2. **Target at rotation point**: [4,5,6,7,0,1,2], target=0 -> 4
3. **Target at end**: [4,5,6,7,0,1,2], target=2 -> 6
4. **No rotation**: [1,2,3,4,5], target=3 -> 2
5. **Empty array**: [] -> -1
6. **Duplicates at boundaries**: Not in this problem (no duplicates)

## Visualization of Rotation

```
Original:  [0, 1, 2, 4, 5, 6, 7]  (sorted)

Rotation 1: [7, 0, 1, 2, 4, 5, 6] (pivot=0)
Rotation 2: [6, 7, 0, 1, 2, 4, 5] (pivot=1)
Rotation 3: [5, 6, 7, 0, 1, 2, 4] (pivot=2)
...

Key insight: One half will always be sorted in a rotated array
```

## Related Problems

- 81: Search in Rotated Sorted Array II (with duplicates)
- 153: Find Minimum in Rotated Sorted Array
- 154: Find Minimum in Rotated Sorted Array II
- 34: Find First and Last Position of Element in Sorted Array

## Tags

`medium` `binary-search` `array` `rotation` `divide-and-conquer`
