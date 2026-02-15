# House Robber II

## Problem Statement
**LeetCode Problem 213**: House Robber II (Medium)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a **circle**. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

### Examples
**Example 1:**
```
Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
because they are adjacent houses.
```

**Example 2:**
```
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
```

## Key Insights
1. **Circular Constraint**: Since houses are in a circle, we cannot rob both first and last house
2. **Problem Decomposition**: Break into two subproblems:
   - Rob houses from index 0 to n-2 (exclude last)
   - Rob houses from index 1 to n-1 (exclude first)
3. **Return Maximum**: The answer is the maximum of these two scenarios
4. **Base Cases Matter**: Handle arrays with 0, 1, or 2 elements separately
5. **Similar to House Robber I**: Each subproblem is solved like the linear version

## Algorithm Steps
```
1. Handle edge cases (length 0, 1, 2)
2. Create memoization array for dynamic programming
3. Solve two subproblems:
   a) Rob houses [1 to n-1] (exclude first house)
   b) Rob houses [0 to n-2] (exclude last house)
4. Return max of both scenarios
5. For each subproblem, use DP recurrence:
   - At each house: max(rob current + skip next, skip current)
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - Two passes through the array
  - Each house visited once per pass
- **Space Complexity**: O(n)
  - Memoization array to cache results
  - Can be optimized to O(1) with sliding window

## Visual Representation

### Example: nums = [2,3,2]
```
Houses arranged in circle:
        [2]
       /   \
     [3]   [2]
       \   /
        ---

Cannot rob both nums[0] and nums[2] (they are adjacent in circle)

Scenario 1: Rob from houses [1 to n-1] = [3, 2]
   Index:  1  2
   Value:  3  2
   Choose: 3 (don't rob adjacent)
   Result: 3

Scenario 2: Rob from houses [0 to n-2] = [2, 3]
   Index:  0  1
   Value:  2  3
   Choose: 3 (don't rob adjacent)
   Result: 3

Answer: max(3, 3) = 3
```

### Example: nums = [1,2,3,1]
```
Houses in circle:
     [1]---[2]
      |     |
     [1]---[3]

Scenario 1: Rob [1,2,3] (indices 1-3, exclude first)
   Indices: 1  2  3
   Values:  2  3  1
   DP:      2  3  3
   Skip 2 or 3? Choose 3
   Then can rob 1: 3 + 1 = 4
   Result: 4

Scenario 2: Rob [1,2,3] (indices 0-2, exclude last)
   Indices: 0  1  2
   Values:  1  2  3
   DP:      1  2  4
   Rob 1+3 = 4
   Result: 4

Answer: max(4, 4) = 4
```

## Code Walkthrough

```java
public int rob(int[] nums) {
    // Handle edge cases
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    if (nums.length == 2) return Math.max(nums[0], nums[1]);

    // Create memoization table
    // 3 rows to handle different scenarios
    int[][] result = new int[3][nums.length + 1];
    for (int i = 0; i < 3; i++)
        Arrays.fill(result[i], -1);

    // Key idea: solve two subproblems
    // 1. Start from index 1, end at n-1 (exclude first house)
    // 2. Start from index 0, end at n-2 (exclude last house)
    return Math.max(
        helper(nums, 1, result, nums.length - 1),
        helper(nums, 0, result, nums.length - 2)
    );
}

public int helper(int[] nums, int index, int[][] result, int maxLength) {
    // Use modulo to differentiate between scenarios in cache
    int xindex = nums.length % maxLength;

    if (result[xindex][index] == -1) {
        if (index > maxLength) {
            // Base case: beyond array bounds
            result[xindex][index] = 0;
        } else if (index == maxLength) {
            // Base case: last house in this scenario
            result[xindex][index] = nums[index];
        } else {
            // Recurrence: max of (rob current + skip next, skip current)
            int gain = nums[index] + helper(nums, index + 2, result, maxLength);
            int gainWithoutCurrent = helper(nums, index + 1, result, maxLength);
            result[xindex][index] = Math.max(gain, gainWithoutCurrent);
        }
    }

    return result[xindex][index];
}
```

### DP Recurrence Explanation
```
At each house i, we have two choices:
1. Rob house i: gain = nums[i] + helper(i+2)
   (skip next house due to alarm)
2. Skip house i: gain = helper(i+1)
   (continue to next house)

Choose maximum of both options
```

## Edge Cases
1. **Empty array**: Return 0
2. **Single house**: Return that house's value
3. **Two houses**: Return max of the two
4. **All zeros**: Return 0
5. **All same values**: Return sum of alternate houses
6. **Decreasing values**: May need to skip houses strategically
7. **Large first/last house**: One scenario will dominate

## Related Problems
- [**House Robber (LeetCode 198)**](../houserobbery/notes.md): Linear version without circular constraint
- **House Robber III**: Houses arranged as binary tree
- **Delete and Earn**: Similar DP pattern
- **Paint House**: Another constraint-based DP problem
- **Decode Ways**: Similar memoization technique

## Tags
- Dynamic Programming
- Array
- Recursion
- Memoization
- Medium
- Circular Array
