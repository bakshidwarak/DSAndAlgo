# House Robber

## Problem Statement
**LeetCode Problem 198**: House Robber (Easy)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

### Examples
**Example 1:**
```
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount = 1 + 3 = 4.
```

**Example 2:**
```
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount = 2 + 9 + 1 = 12.
```

## Key Insights
1. **Cannot Rob Adjacent Houses**: This is the core constraint
2. **Optimal Substructure**: Decision at house i depends on decisions at i-1 and i-2
3. **Two Choices at Each House**:
   - Rob current house + best from i+2 onwards
   - Skip current house + best from i+1 onwards
4. **Bottom-Up DP**: Build solution from end to start
5. **Space Optimization Possible**: Only need last two values

## Algorithm Steps
```
1. Create cache array of size n+2 (handle boundary conditions)
2. Initialize cache[n] = 0 and cache[n+1] = 0 (base cases)
3. Iterate from end to start (i = n-1 to 0):
   a. Calculate gain_with_current = nums[i] + cache[i+2]
   b. Calculate gain_without_current = cache[i+1]
   c. cache[i] = max(gain_with_current, gain_without_current)
4. Return cache[0]
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - Single pass through array
- **Space Complexity**: O(n)
  - Cache array of size n+2
  - Can be optimized to O(1) using two variables

## Visual Representation

### Example: nums = [2,7,9,3,1]
```
Houses:
Index:  0  1  2  3  4
Value:  2  7  9  3  1

Build cache from right to left:

Initial: cache = [?, ?, ?, ?, ?, 0, 0]
                  0  1  2  3  4  5  6

i=4:
  - Rob house 4: 1 + cache[6] = 1 + 0 = 1
  - Skip house 4: cache[5] = 0
  - cache[4] = max(1, 0) = 1
  cache = [?, ?, ?, ?, 1, 0, 0]

i=3:
  - Rob house 3: 3 + cache[5] = 3 + 0 = 3
  - Skip house 3: cache[4] = 1
  - cache[3] = max(3, 1) = 3
  cache = [?, ?, ?, 3, 1, 0, 0]

i=2:
  - Rob house 2: 9 + cache[4] = 9 + 1 = 10
  - Skip house 2: cache[3] = 3
  - cache[2] = max(10, 3) = 10
  cache = [?, ?, 10, 3, 1, 0, 0]

i=1:
  - Rob house 1: 7 + cache[3] = 7 + 3 = 10
  - Skip house 1: cache[2] = 10
  - cache[1] = max(10, 10) = 10
  cache = [?, 10, 10, 3, 1, 0, 0]

i=0:
  - Rob house 0: 2 + cache[2] = 2 + 10 = 12
  - Skip house 0: cache[1] = 10
  - cache[0] = max(12, 10) = 12
  cache = [12, 10, 10, 3, 1, 0, 0]

Answer: 12 (Rob houses at indices 0, 2, 4)
```

### Decision Tree Visualization
```
                    [2,7,9,3,1]
                    /          \
                Rob 2          Skip 2
              [9,3,1]          [7,9,3,1]
              /    \           /       \
          Rob 9  Skip 9    Rob 7    Skip 7
         [3,1]   [3,1]    [9,3,1]   [9,3,1]
          ...      ...      ...       ...

Optimal path: Rob 2 -> Skip 7 -> Rob 9 -> Skip 3 -> Rob 1
Total: 2 + 9 + 1 = 12
```

## Code Walkthrough

```java
public int rob(int[] nums) {
    // Create cache array (size n+2 for boundary conditions)
    int cache[] = new int[nums.length + 2];

    // Base cases: beyond array = 0 gain
    cache[nums.length] = 0;
    cache[nums.length + 1] = 0;

    // Build solution from right to left
    for (int i = nums.length - 1; i >= 0; i--) {
        // Option 1: Rob current house
        // Add current value + best from i+2 onwards (skip next)
        int gain_with_current = nums[i] + cache[i + 2];

        // Option 2: Skip current house
        // Take best from i+1 onwards
        int gain_without_current = cache[i + 1];

        // Choose maximum of both options
        cache[i] = Math.max(gain_with_current, gain_without_current);
    }

    // Answer is at index 0 (best from start)
    return cache[0];
}
```

### Space-Optimized Version (O(1) Space)
```java
public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];

    int next = 0;      // cache[i+2]
    int nextNext = 0;  // cache[i+1]

    for (int i = nums.length - 1; i >= 0; i--) {
        int current = Math.max(nums[i] + next, nextNext);
        next = nextNext;
        nextNext = current;
    }

    return nextNext;
}
```

## Edge Cases
1. **Empty array**: Return 0
2. **Single house**: Return that house's value
3. **Two houses**: Return max of the two
4. **All zeros**: Return 0
5. **Increasing sequence**: Rob all even or odd indices
6. **Decreasing sequence**: May need different strategy
7. **Alternating high-low**: Rob all high values

## Related Problems
- **House Robber II (LeetCode 213)**: Houses in a circle
- **House Robber III (LeetCode 337)**: Houses in binary tree
- **Delete and Earn (LeetCode 740)**: Similar DP pattern
- **Maximum Sum of Non-Adjacent Elements**: Direct variant
- **Paint House**: Similar constraint-based DP

## Tags
- Dynamic Programming
- Array
- Bottom-Up DP
- Optimization Problem
- Easy
- Interview Favorite
