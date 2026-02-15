# LeetCode 88: Merge Sorted Array

## Problem Statement
Given two sorted integer arrays `nums1` and `nums2`, merge `nums2` into `nums1` as one sorted array **in-place**. Assume `nums1` has enough space (size >= m + n) to hold elements from both arrays.

## Difficulty
Easy

## Examples

### Example 1
- **Input:** `nums1 = [1,2,3,0,0,0]` m=3, `nums2 = [2,5,6]` n=3
- **Output:** `[1,2,3,2,5,6]` - Actually `[1,2,2,3,5,6]`
- **Explanation:** Merge in place without extra space

## Key Insights
1. **Three Pointer Approach**: Use pointers for nums1, nums2, and result position
2. **Reverse Iteration**: Start from end of arrays to avoid overwriting
3. **Shift Strategy**: First shift nums1 elements right by n positions, then merge
4. **No Extra Array Needed**: Can merge in place using the existing space

## Algorithm Steps
1. Shift elements of nums1 right by n positions (shift n places)
2. Initialize three pointers: k=0 (result), i=0 (nums2), j=n (shifted nums1)
3. Merge:
   - Compare nums1[j] and nums2[i]
   - Add smaller element to position k
   - Move corresponding pointer
4. Copy remaining elements from nums2 if any

## Complexity Analysis
- **Time Complexity:** O(m + n) - Single pass through both arrays
- **Space Complexity:** O(1) - In-place merge, no extra space

## ASCII Visualization

```
Initial:
nums1 = [1, 2, 3, _, _, _]  m=3
nums2 = [2, 5, 6]            n=3

Step 1: Shift nums1 right by n=3
nums1 = [_, _, _, 1, 2, 3]
        Position: 0  1  2  3  4  5

Step 2: Merge using three pointers
        k=0, i=0, j=3

Compare nums1[3]=1 vs nums2[0]=2 → pick 1
nums1 = [1, _, _, _, 2, 3]
        k=1, i=0, j=4

Compare nums1[4]=2 vs nums2[0]=2 → pick 2
nums1 = [1, 2, _, _, _, 3]
        k=2, i=1, j=4

...continue merging...

Final: [1, 2, 2, 3, 5, 6]
```

## Edge Cases
1. **Empty nums2:** No change needed
2. **Empty nums1:** Copy all of nums2
3. **All nums2 smaller:** nums2 elements come first
4. **All nums1 smaller:** Order unchanged
5. **Duplicate elements:** Handle correctly

## Related Problems
- **LeetCode 21:** Merge Two Sorted Lists
- **LeetCode 977:** Squares of a Sorted Array
- **LeetCode 1305:** All Elements in Two Binary Search Trees

## Tags
`Array` `Two Pointers` `Sorting` `Merge`
