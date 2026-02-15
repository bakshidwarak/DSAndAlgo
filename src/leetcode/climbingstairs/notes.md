# 70. Climbing Stairs

## Problem Statement
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

### Examples
```
Example 1:
Input: n = 2
Output: 2
Explanation: Two ways:
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: Three ways:
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Example 3:
Input: n = 5
Output: 8
Explanation: 8 different combinations
```

### Constraints
- 1 <= n <= 45
- n is a positive integer

## Approach & Solution

### Key Insights
1. **Fibonacci pattern**: The number of ways to reach step n equals ways(n-1) + ways(n-2)
2. **Dynamic programming**: Build solution bottom-up using previously computed values
3. **Base cases**: 1 way to reach step 1, 2 ways to reach step 2
4. **Space optimization**: Only need last two values, not entire array

### Algorithm Steps
1. Handle base cases: n=0 → 0, n=1 → 1, n=2 → 2
2. Create cache array of size n+1
3. Initialize: cache[0]=0, cache[1]=1, cache[2]=2
4. For i from 3 to n:
   - cache[i] = cache[i-1] + cache[i-2]
5. Return cache[n]

**Optimized version (Space O(1)):**
1. Use two variables (first, second) instead of array
2. Iterate from 3 to n, updating variables
3. Return final value

### Complexity Analysis
- **Time Complexity**: O(n)
  - Single loop from 3 to n
  - Each iteration performs constant work
- **Space Complexity**:
  - Array version: O(n) for cache array
  - Optimized version: O(1) using only two variables

### Visualization
```
Example: n = 5

Step-by-step build up:
Step 0: 0 ways (no step)
Step 1: 1 way  [1]
Step 2: 2 ways [1+1, 2]
Step 3: 3 ways [1+1+1, 1+2, 2+1]
Step 4: 5 ways [1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2]
Step 5: 8 ways

Recurrence relation:
ways(5) = ways(4) + ways(3)
        = 5 + 3
        = 8

Visual tree (partial):
                    5
                   / \
                  4   3
                 / \ / \
                3 2 2 1
               ...

Why? To reach step 5:
- Take 1 step from step 4 (ways(4) options)
- Take 2 steps from step 3 (ways(3) options)

Fibonacci sequence:
F(0)=0, F(1)=1, F(2)=2, F(3)=3, F(4)=5, F(5)=8, F(6)=13...
```

## Code Walkthrough

**Version 1: Array-based DP**
```java
public static int climbStairs(int n) {
    if (n == 1)
        return 1;
    if (n == 2)
        return 2;
    if (n == 0)
        return 0;

    int[] cache = new int[n + 1];
    cache[0] = 0;
    cache[1] = 1;
    cache[2] = 2;

    // Build up from step 3 to n
    for (int i = 3; i <= n; i++) {
        cache[i] = cache[i - 1] + cache[i - 2];
    }

    return cache[n];
}
```

**Version 2: Space-optimized (O(1) space)**
```java
public static int climbStairsWithJustStoringTheLastVals(int n) {
    if (n == 1)
        return 1;
    if (n == 2)
        return 2;
    if (n == 0)
        return 0;

    int first = 1;   // ways to reach step 1
    int second = 2;  // ways to reach step 2
    int nthstep = 0;

    for (int i = 3; i <= n; i++) {
        nthstep = first + second;
        first = second;      // Shift window
        second = nthstep;    // Shift window
    }

    return nthstep;
}
```

**Key Differences:**
- Version 1 uses O(n) space but clearer logic
- Version 2 uses O(1) space by maintaining sliding window
- Both have O(n) time complexity

## Edge Cases
- **n = 1**: 1 way (single step)
- **n = 2**: 2 ways (1+1 or 2)
- **n = 3**: 3 ways (verified manually)
- **Large n**: Works efficiently up to n=45
- **n = 0**: 0 ways (edge case, no steps needed)

## Related Problems
- **746. Min Cost Climbing Stairs**: Similar DP with cost optimization
- **509. Fibonacci Number**: Identical recurrence relation
- **1137. N-th Tribonacci Number**: Extension to 3-step jumps
- **91. Decode Ways**: Similar DP pattern
- **House Robber Series**: Similar DP optimization problems

## Tags
`dynamic-programming` `math` `fibonacci` `easy`
