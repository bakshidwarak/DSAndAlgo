# LeetCode 64: Minimum Path Sum

## Problem Statement
Given an m x n grid filled with non-negative numbers, find a path from **top-left to bottom-right** that **minimizes the sum** of all numbers along its path. You can only move **right or down**.

## Difficulty
Medium

## Examples

### Example 1
```
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]

Output: 7
Explanation: 1 → 3 → 1 → 1 → 1 = 7 (best path)
```

## Key Insights
1. **Dynamic Programming**: Optimal solution uses optimal subproblems
2. **Only Two Choices**: From any cell, can only come from top or left
3. **Bottom-Up Approach**: Start from bottom-right, work backward
4. **State Definition**: dp[i][j] = minimum cost to reach (i,j) from (0,0)
5. **Space Optimization**: Can use original grid instead of extra 2D array

## Algorithm Steps
1. Initialize result 2D array same size as grid
2. Iterate from bottom-right to top-left:
   - Base case (bottom-right): result[m-1][n-1] = grid[m-1][n-1]
   - For other cells:
     - If only right neighbor available: use that cost
     - If only bottom neighbor available: use that cost
     - If both available: use minimum of both neighbors
     - Add current cell's value
3. Return result[0][0]

## Complexity Analysis
- **Time Complexity:** O(m * n) - Visit each cell once
- **Space Complexity:** O(m * n) - Result array (can optimize to O(1) with modifications)

## ASCII Visualization

```
Grid:           DP Table (minimum cost to reach each cell):
[1,3,1]         [1, 4, 5]
[1,5,1]         [2, 7, 6]
[4,2,1]         [6, 8, 7]

Calculation:
(2,2): 1 (start)
(2,1): 1 + 2 = 3, but 1+2=3
(2,0): 1 + 4 = 5
(1,2): 1 + 1 = 2, wait recount...

Bottom-right: (2,2) = 1
(2,1): 1 + 2 = 3
(2,0): 1 + 4 = 5
(1,2): 1 + 1 = 2
(1,1): 1 + min(2,3) + 5 = 8
(1,0): 5 + 1 = 6
(0,2): 2 + 1 = 3... no wait

Let me recalculate properly (bottom-up):
(2,2) = 1
(2,1) = 2 + 1 = 3
(2,0) = 4 + 3 = 7
(1,2) = 1 + 1 = 2
(1,1) = 5 + min(3,2) = 7
(1,0) = 1 + min(7,7) = 8
(0,2) = 1 + 2 = 3
(0,1) = 3 + min(3,7) = 6
(0,0) = 1 + min(6,8) = 7

Path: (0,0) → (0,1) → (0,2) → (1,2) → (2,2)
Sum: 1 + 3 + 1 + 1 + 1 = 7
```

## Edge Cases
1. **Single cell:** Return that cell's value
2. **Single row:** Return sum of entire row
3. **Single column:** Return sum of entire column
4. **All zeros:** Return 0
5. **Large numbers:** Handle correctly with overflow

## Related Problems
- **LeetCode 62:** Unique Paths
- **LeetCode 63:** Unique Paths II
- **LeetCode 120:** Triangle
- **LeetCode 931:** Minimum Falling Path Sum

## Tags
`Dynamic Programming` `Grid` `Path Sum`
