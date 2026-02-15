# LeetCode 63: Unique Paths II

## Problem Statement
A robot is located at the top-left corner of an m x n grid. The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.

Now consider if some obstacles are added to the grids. How many unique paths would there be?

**Constraints:**
- An obstacle and empty space is marked by 1 and 0 respectively in the input grid
- The robot cannot pass through obstacles
- Return the number of unique paths that avoid obstacles

## Examples

### Example 1
```
Input: obstacleGrid = [[0,0,0],
                       [0,1,0],
                       [0,0,0]]
Output: 2
Explanation:
The obstacle at position (1,1) blocks one path.
Paths are:
- Right → Right → Down → Down
- Down → Down → Right → Right
```

### Example 2
```
Input: obstacleGrid = [[0,1],
                       [0,0]]
Output: 1
```

### Example 3
```
Input: obstacleGrid = [[1]]
Output: 0
Explanation: Starting position is blocked
```

## Key Insights

1. **Dynamic Programming Problem:** Build solution bottom-up from subproblems
2. **Obstacle Handling:** Skip cells with obstacles (dp[i][j] = 0)
3. **Boundary Cases:** Top row and left column have special handling
4. **Recurrence Relation:** dp[i][j] = dp[i-1][j] + dp[i][j-1] (if no obstacle)
5. **Space Optimization:** Can use 1D or 2D array (both shown in variations)

## Algorithm Steps

### Dynamic Programming Approach

```
1. Create dp array (m x n) to store number of paths
2. Initialize:
   - If obstacle at (0,0), return 0
   - Fill first row: dp[0][j] = 0 if obstacle, else 1 (can only reach via left)
   - Fill first column: dp[i][0] = 0 if obstacle, else 1 (can only reach via top)
3. Fill remaining cells:
   For each cell (i, j):
   - If obstacle at (i, j): dp[i][j] = 0
   - Else: dp[i][j] = dp[i-1][j] + dp[i][j-1]
4. Return dp[m-1][n-1]
```

## Complexity Analysis

- **Time Complexity:** O(m × n) - Must visit each cell once
- **Space Complexity:** O(m × n) - For dp table (can optimize to O(n) with 1D array)

## ASCII Visualization

```
Grid with obstacles (1 = obstacle, 0 = empty):
  0   1   2
0[0] [0] [0]
1[0] [1] [0]
2[0] [0] [0]

DP Table showing number of paths to reach each cell:
  0   1   2
0[1] [1] [1]
1[1] [0] [1]
2[1] [1] [2]

Explanation:
- (0,0): 1 path (starting point)
- (0,1): 1 path (from left)
- (0,2): 1 path (from left)
- (1,0): 1 path (from top)
- (1,1): 0 paths (obstacle blocks)
- (1,2): 1 path (from top, can't use left due to obstacle at (1,1))
- (2,0): 1 path (from top)
- (2,1): 1 path (from left)
- (2,2): 2 paths (from top: 1 + from left: 1 = 2)
```

## Code Implementation (Expected Solution)

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid == null || obstacleGrid.length == 0)
        return 0;

    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    // If starting or ending position has obstacle
    if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1)
        return 0;

    // Initialize dp table
    int[][] dp = new int[m][n];
    dp[0][0] = 1;  // Starting position

    // Fill first row
    for (int j = 1; j < n; j++) {
        dp[0][j] = (obstacleGrid[0][j] == 0) ? dp[0][j-1] : 0;
    }

    // Fill first column
    for (int i = 1; i < m; i++) {
        dp[i][0] = (obstacleGrid[i][0] == 0) ? dp[i-1][0] : 0;
    }

    // Fill remaining cells
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[i][j] == 0) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
    }

    return dp[m-1][n-1];
}
```

## Edge Cases

1. **Starting Cell Blocked:** If (0,0) has obstacle, return 0
2. **Ending Cell Blocked:** If (m-1,n-1) has obstacle, return 0
3. **All Blocked:** Grid with no path returns 0
4. **Single Cell:** 1x1 grid with no obstacle returns 1
5. **Full Row Blocked:** If entire row has obstacle, all cells below are unreachable
6. **Full Column Blocked:** If entire column has obstacle, all cells to right are unreachable
7. **Empty Grid:** No obstacles, use regular unique paths formula

### Example Edge Cases:
```
Input: [[0]], Output: 1
Input: [[1]], Output: 0
Input: [[0,0],[1,0]], Output: 1
Input: [[0,1],[0,0]], Output: 0
```

## Variations

### Space-Optimized (1D Array)
```java
// Use only 1D array instead of 2D
int[] dp = new int[n];
dp[0] = 1;
for (int i = 0; i < m; i++) {
    if (obstacleGrid[i][0] == 1) dp[0] = 0;
    for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1)
            dp[j] = 0;
        else
            dp[j] += dp[j-1];
    }
}
return dp[n-1];
```

## Related Problems

1. **LeetCode 62 - Unique Paths:** Same problem without obstacles
2. **LeetCode 64 - Minimum Path Sum:** Minimum path cost with obstacles
3. **LeetCode 931 - Minimum Falling Path Sum:** Similar DP concept
4. **LeetCode 70 - Climbing Stairs:** Basic DP problem
5. **LeetCode 97 - Interleaving String:** DP with multiple dimensions
## Tags

`#Dynamic-Programming` `#Grid` `#Matrix` `#DP-Combinatorics` `#Medium`

## Key Takeaways

- Obstacles force dp[i][j] = 0, breaking path count
- First row/column become special cases (linear paths only)
- Recurrence: dp[i][j] = dp[i-1][j] + dp[i][j-1]
- Can optimize space from O(m×n) to O(n) with 1D array
- Similar structure to Unique Paths but with obstacle handling
