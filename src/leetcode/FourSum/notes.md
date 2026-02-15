# 18. 4Sum

## Problem Statement
Given an array `nums` of n integers and an integer `target`, find all unique quadruplets in the array which gives the sum of `target`. The elements a, b, c, and d must satisfy `a + b + c + d = target`.

### Examples
```
Input: nums = [1, 0, -1, 0, -2, 2], target = 0
Output: [[-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2]]

Input: nums = [2, 2, 2, 2, 2], target = 8
Output: [[2, 2, 2, 2]]
```

### Constraints
- The solution set must not contain duplicate quadruplets
- n integers in the array
- All numbers (including target) can be positive, negative, or zero

## Approach & Solution

### Key Insights
1. **Sorting first** enables efficient duplicate detection and two-pointer technique
2. **Reduction to 2Sum**: By fixing two outer elements, the problem reduces to finding two elements that sum to a revised target
3. **Two-pointer technique**: After fixing outer elements, use left and right pointers to find the remaining two elements
4. **Duplicate handling**: The current implementation checks if result list already contains the combination

### Algorithm Steps
1. Sort the input array to enable two-pointer approach and easier duplicate handling
2. Use two nested loops for the first and last elements (i and j)
3. For each pair (i, j), calculate revised target = target - (nums[i] + nums[j])
4. Use two pointers (left = i+1, right = j-1) to find pairs that sum to revised target
5. If sum equals revised target, add quadruplet to result and move both pointers
6. If sum is greater than revised target, move right pointer left
7. If sum is less than revised target, move left pointer right
8. Skip duplicate combinations using contains() check

### Complexity Analysis
- **Time Complexity**: O(n^3)
  - Outer loop runs O(n) times
  - Inner loop runs O(n) times
  - Two-pointer search runs O(n) times
  - The contains() check adds additional overhead: O(n) per check in worst case
  - Overall: O(n^3) for the nested loops, potentially O(n^4) with contains() check
- **Space Complexity**: O(k)
  - Where k is the number of valid quadruplets found
  - Excluding the output array, space is O(1)

### Visualization
```
Array after sorting: [-2, -1, 0, 0, 1, 2], target = 0

Step 1: Fix i=0 (val=-2), j=5 (val=2)
        Revised target = 0 - (-2 + 2) = 0

        [-2, -1, 0, 0, 1, 2]
          i   L        R  j

        -1 + 1 = 0 ✓ Found: [-2, -1, 1, 2]

Step 2: Fix i=0 (val=-2), j=4 (val=1)
        Revised target = 0 - (-2 + 1) = 1

        [-2, -1, 0, 0, 1, 2]
          i   L     R  j

        0 + 0 = 0 ≠ 1, move left

Step 3: Continue similar process...
```

## Code Walkthrough

The implementation uses a nested loop structure with two-pointer technique:

```java
Arrays.sort(nums);  // Essential first step
List<List<Integer>> result = new ArrayList<>();

for (int i = 0; i < nums.length; i++) {
    for (int j = nums.length - 1; j > 0; j--) {
        // Fix outer two elements
        int left = i + 1;
        int right = j - 1;
        int revised = target - (nums[i] + nums[j]);

        // Two-pointer search for inner elements
        while (left < right) {
            if (nums[left] + nums[right] > revised) {
                right--;
            } else if (nums[left] + nums[right] == revised) {
                // Found valid quadruplet
                List<Integer> curr = new ArrayList<>();
                curr.add(nums[i]);
                curr.add(nums[left]);
                curr.add(nums[right]);
                curr.add(nums[j]);

                // Duplicate check (could be optimized)
                if (!result.contains(curr))
                    result.add(curr);

                left++;
                right--;
            } else {
                left++;
            }
        }
    }
}
```

**Key Implementation Details:**
- The outer loops fix elements at positions i (start) and j (end)
- Inner two-pointer loop searches for complementary pair
- The `!result.contains(curr)` check prevents duplicates but is expensive

## Edge Cases
- **Empty or small arrays**: Arrays with less than 4 elements return empty result
- **All same elements**: [2,2,2,2,2], target=8 should return [[2,2,2,2]]
- **No valid quadruplets**: Should return empty list
- **Duplicate elements**: Sorting helps, but contains() check ensures no duplicate quadruplets
- **Negative numbers**: Algorithm handles negative, zero, and positive numbers correctly

## Related Problems
- **15. 3Sum**: Similar approach with one fewer element
- **1. Two Sum**: The base case this problem builds upon
- **454. 4Sum II**: Variation with four different arrays
- **K-Sum Problems**: General category of sum problems

## Tags
`array` `two-pointers` `sorting` `hash-table` `medium`
