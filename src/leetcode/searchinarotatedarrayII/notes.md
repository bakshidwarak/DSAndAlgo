# 81. Search in Rotated Sorted Array II

## Problem Statement
Suppose an array sorted in ascending order is rotated at some unknown pivot and contains duplicates.

Given a target value to search, return true if found, otherwise return false.

**Key difference from Problem 33:** This problem allows duplicates in the array.

## Examples

### Example 1
```
Input: nums = [1,0,1,1,1], target = 0
Output: true
Explanation: Element 0 exists at index 1
```

### Example 2
```
Input: nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1], target = 2
Output: true
Explanation: Element 2 exists at index 13
```

### Example 3
```
Input: nums = [1], target = 0
Output: false
```

## Key Insights

1. **Duplicates complicate binary search**: When nums[mid] == nums[end], we can't determine which half is sorted
2. **Shrink boundaries**: When duplicates exist, shrink the search space by moving end pointer left
3. **Tradeoff**: Worst case becomes O(n) due to duplicates, but still O(log n) on average
4. **Identify sorted half**: After handling duplicates, identify which half is sorted

## Algorithm Steps

1. Initialize start = 0, end = length - 1
2. While start <= end:
   - If nums[mid] == target, return true
   - While nums[end] == nums[mid] == nums[start], shrink: end--, start++
   - Determine which half is sorted
   - Check if target is in the sorted half
   - Move pointers accordingly
3. Return false if target not found

## Complexity Analysis

**Time Complexity:** O(log n) average, O(n) worst case
- Average case: Binary search with duplicate handling
- Worst case: When array has many duplicates (e.g., [1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1])

**Space Complexity:** O(1)
- Only using pointers
- No additional data structures

## ASCII Visualization

```
Array with duplicates: [1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1]
                        0 1 2 3 4 5 6 7 8 9101112131415161718

Search for target = 2:

Initial: start=0, end=18, mid=9
nums[mid]=1, nums[start]=1, nums[end]=1
All three are equal! Can't determine which half is sorted
Shrink: end=17, start=1

Next: start=1, end=17, mid=9
nums[mid]=1, target=2
nums[mid]==nums[end]? 1==1? YES
Shrink again: end=16, start=2

Continue shrinking until we can identify sorted half...

Eventually find that 2 is in the array
```

## Code Walkthrough

```java
public boolean search(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2;

        if (nums[mid] == target) {
            return true;
        }

        // Handle duplicates: when nums[start], nums[mid], nums[end] are all equal
        // We can't determine which half is sorted, so shrink the boundaries
        while (start < end && nums[start] == nums[end] && nums[end] == nums[mid]) {
            start++;
            end--;
        }

        // Now determine which half is sorted
        if (nums[mid] >= nums[start]) {
            // Left half is sorted [start, mid]
            if (nums[start] <= target && target < nums[mid]) {
                // Target is in the sorted left half
                end = mid - 1;
            } else {
                // Target is in the right half
                start = mid + 1;
            }
        } else {
            // Right half is sorted [mid, end]
            if (nums[mid] < target && target <= nums[end]) {
                // Target is in the sorted right half
                start = mid + 1;
            } else {
                // Target is in the left half
                end = mid - 1;
            }
        }
    }

    return false;
}
```

## Edge Cases

1. **All duplicates**: [1,1,1,1,1], target=1 -> true
2. **All duplicates except one**: [1,1,1,1,2,1,1], target=2 -> true
3. **Empty array**: [] -> false
4. **Single element**: [1], target=1 -> true
5. **No duplicates**: [3,1] -> behaves like Problem 33
6. **Target not present**: [1,3] -> false

## Worst Case Scenario

```
When array is [1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,...,1,1]
and duplicates extend to both ends, we must shrink the search space
significantly, approaching O(n) time complexity.

Example worst case:
[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2]
```

## Comparison with Problem 33

| Aspect | Problem 33 | Problem 81 |
|--------|-----------|-----------|
| Duplicates | No | Yes |
| Time Complexity | O(log n) | O(log n) avg, O(n) worst |
| Binary Search | Straightforward | Needs duplicate handling |
| Difficulty | Medium | Medium |

## Related Problems

- 33: Search in Rotated Sorted Array (no duplicates)
- 153: Find Minimum in Rotated Sorted Array
- 154: Find Minimum in Rotated Sorted Array II (with duplicates)

## Tags

`medium` `binary-search` `array` `duplicates` `rotation`
