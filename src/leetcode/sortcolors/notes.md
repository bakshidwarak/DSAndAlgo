# Sort Colors (LeetCode 75)

## Problem Statement
Given an array with n objects colored red, white, or blue (represented as 0, 1, and 2), sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

**Constraint:** You cannot use the library's sort function for this problem.

**Follow-up:** Can you come up with a one-pass algorithm using only constant space?

## Examples
```
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Input: nums = [2,0,1]
Output: [0,1,2]
```

## Key Insights
1. This is the classic Dutch National Flag problem
2. The key insight is to use three pointers: i (left), j (right), and k (middle)
3. One-pass algorithm using constant space requires careful pointer management
4. The algorithm maintains three regions: [0, k) contains 0s, [k, i) contains 1s, [i, j] is unknown
5. We process the unknown region and move pointers accordingly

## Algorithm Steps

### Approach: Three-Pointer In-Place Sorting
1. Initialize three pointers: i=0, j=n-1, k=0
2. While i <= j:
   - If nums[i] > 1: Swap nums[i] with nums[j], decrement j (move 2 to right)
   - Else if nums[i] < 1: Swap nums[i] with nums[k], increment k (move 0 to left)
   - Else: Increment i (nums[i] == 1, already in place)
3. This ensures all 0s are at the beginning, 1s in the middle, 2s at the end

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through the array
- **Space Complexity:** O(1) - Only using three pointers, no extra space

## ASCII Visualization

```
Initial:  [2, 0, 2, 1, 1, 0]
           i              j
           k

Step 1: nums[0]=2 > 1, swap(0,5)
        [0, 0, 2, 1, 1, 2]
           i           j

Step 2: nums[0]=0 < 1, swap(0,0), k++
        [0, 0, 2, 1, 1, 2]
              i        j
              k

Step 3: nums[1]=0 < 1, swap(1,1), k++
        [0, 0, 2, 1, 1, 2]
                 i     j
                 k

Step 4: nums[2]=2 > 1, swap(2,4)
        [0, 0, 1, 1, 2, 2]
                 i  j

Step 5: nums[2]=1 == 1, i++
        [0, 0, 1, 1, 2, 2]
                    i j

Final: [0, 0, 1, 1, 2, 2] ✓
```

## Code Walkthrough

```java
public void sortColors(int[] nums) {
    int i = 0;      // Current pointer
    int j = nums.length - 1;  // Right pointer
    int k = 0;      // Boundary of 0s

    while (i <= j) {
        if (nums[i] > 1) {
            // nums[i] is a 2, swap with j and move j left
            swap(nums, i, j);
            j--;
        } else if (nums[i] < 1) {
            // nums[i] is a 0, swap with k and move k right
            swap(nums, i, k);
            if (i == k) i++;  // Avoid redundant swap
            k++;
        } else {
            // nums[i] is a 1, already in place
            i++;
        }
    }
}
```

## Edge Cases
1. Array with all same color: [0, 0, 0] → [0, 0, 0]
2. Already sorted array: [0, 1, 2] → [0, 1, 2]
3. Reverse sorted array: [2, 1, 0] → [0, 1, 2]
4. Single element: [1] → [1]
5. Two elements: [1, 0] → [0, 1]

## Related Problems
- LeetCode 88: Merge Sorted Array
- LeetCode 179: Largest Number
- LeetCode 324: Wiggle Sort II
- LeetCode 280: Wiggle Sequence

## Tags
- Array
- Two Pointers
- Sorting
- In-Place Algorithm
- One-Pass Algorithm
