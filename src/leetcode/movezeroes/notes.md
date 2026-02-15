# LeetCode 283: Move Zeroes

## Problem Statement
Given an array `nums`, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

You must do this in-place without making a copy of the array. Minimize the total number of operations.

## Examples

**Example 1:**
```
Input: [0, 1, 0, 3, 12]
Output: [1, 3, 12, 0, 0]
```

**Example 2:**
```
Input: [0]
Output: [0]
```

**Example 3:**
```
Input: [1, 2, 3]
Output: [1, 2, 3]
```

## Key Insights

1. **Two-Pointer Technique**: Track the position where the next non-zero element should be placed
2. **Single Pass**: Can be solved in one pass through the array
3. **In-place**: No extra space needed for the main operations
4. **Order Preservation**: The relative order of non-zero elements must remain unchanged

## Algorithm Steps

### Approach 1: Swap Non-Zeros Forward (Optimal)

1. Initialize `zeroIndex = 0` (position where next non-zero should go)
2. Iterate through the array from index 0 to n-1
3. When a non-zero element is found:
   - Swap element at index `i` with element at `zeroIndex`
   - Increment `zeroIndex`
4. All zeros naturally end up at the end

### Approach 2: Two Pointers (Alternative)

1. Find a zero and mark its position
2. Search for the next non-zero element
3. Swap them
4. Continue until end of array

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) - Single pass through array |
| **Space Complexity** | O(1) - Only swap operations, no extra space |
| **Best Case** | O(n) - Array is already sorted |
| **Worst Case** | O(n) - Array is all zeros or all non-zeros |

## ASCII Visualization

```
Initial Array: [0, 1, 0, 3, 12]

Step 1: zeroIndex=0, i=0, nums[0]=0 (skip)
        [0, 1, 0, 3, 12]
         ^

Step 2: zeroIndex=0, i=1, nums[1]=1 (swap with zeroIndex=0)
        [1, 0, 0, 3, 12]
         ^

Step 3: zeroIndex=1, i=2, nums[2]=0 (skip)
        [1, 0, 0, 3, 12]
            ^

Step 4: zeroIndex=1, i=3, nums[3]=3 (swap with zeroIndex=1)
        [1, 3, 0, 0, 12]
            ^

Step 5: zeroIndex=2, i=4, nums[4]=12 (swap with zeroIndex=2)
        [1, 3, 12, 0, 0]
               ^

Final Array: [1, 3, 12, 0, 0]
```

## Code Walkthrough

### Solution 1: Optimal Approach

```java
public void moveZeroes(int[] nums) {
    int zeroIndex = 0;
    // zeroIndex tracks where next non-zero element should go

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            // Found non-zero, swap with zeroIndex position
            swap(nums, i, zeroIndex);
            zeroIndex++;
        }
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

**Execution Flow:**
- When non-zero found at position `i`, swap it to position `zeroIndex`
- `zeroIndex` only increments when non-zero is found
- This ensures all non-zeros move forward and zeros accumulate at the end

## Edge Cases

1. **All zeros**: `[0, 0, 0]` → Output: `[0, 0, 0]` (no swaps needed)
2. **No zeros**: `[1, 2, 3]` → Output: `[1, 2, 3]` (all swaps are no-ops)
3. **Single element**:
   - `[0]` → `[0]`
   - `[1]` → `[1]`
4. **Zeros at end**: `[1, 2, 0, 0]` → Output: `[1, 2, 0, 0]` (already sorted)
5. **Alternating**: `[0, 1, 0, 2]` → `[1, 2, 0, 0]`
6. **Large numbers**: Works with any integer values (positive, negative)

## Related Problems

1. **LeetCode 26** - Remove Duplicates from Sorted Array (Similar two-pointer technique)
2. **LeetCode 27** - Remove Element (Move specific element to end)
3. [Sort Colors](../sortcolors/notes.md)
4. **LeetCode 82** - Remove Duplicates from Sorted List II (Two-pointer on linked list)
5. [Product of Array Except Self](../productofarrayexceptself/notes.md)

## Tags

`Array` `Two Pointers` `In-Place` `Easy` `Facebook` `Amazon` `Bloomberg`

## Notes

- The key insight is using a pointer to track where non-zero elements should go
- Swapping is more efficient than shifting all elements
- This is a variation of the partition problem
- Works well for sparse arrays with many zeros
