# Longest Increasing Path in a Matrix

## Problem Statement
**LeetCode Problem 329**: Longest Increasing Path in a Matrix (Hard)

Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

### Examples
**Example 1:**
```
Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Matrix:
[9, 9, 4]
[6, 6, 8]
[2, 1, 1]

Path visualization:
[9, 9, 4]
[6→6, 8]
[2→1, 1]
   ↑
Path: 1 -> 2 -> 6 -> 9 (length 4)
```

**Example 2:**
```
Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6].

Matrix:
[3→4→5]
[3, 2, 6]
      ↑
[2, 2, 1]

Path: 3 -> 4 -> 5 -> 6 (length 4)
```

**Example 3:**
```
Input: matrix = [[1]]
Output: 1
```

**Constraints**:
- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 200
- 0 <= matrix[i][j] <= 2^31 - 1

## Key Insights
1. **DFS + Memoization**: Use DFS to explore paths, cache results to avoid recomputation
2. **Topological Sort Alternative**: Can also use topological sort with outdegree
3. **Directed Acyclic Graph (DAG)**: Increasing constraint ensures no cycles
4. **Memoization Critical**: Without caching, time complexity becomes exponential
5. **Start from Every Cell**: Try each cell as starting point

## Algorithm Steps

### Approach: DFS with Memoization
```
1. Create memoization matrix (same size as input)
2. For each cell in matrix:
   a. Call DFS to find longest path starting from that cell
   b. Track maximum path length found
3. DFS function:
   a. If already computed (memo[i][j] > 0), return cached value
   b. Initialize path length = 1 (current cell)
   c. Try all 4 directions:
      - If neighbor is in bounds AND value is greater:
        - Recursively get path length from neighbor
        - Update current path length
   d. Cache and return result
4. Return global maximum
```

## Complexity Analysis
- **Time Complexity**: O(m * n)
  - Each cell computed once due to memoization
  - DFS visits each cell at most once
- **Space Complexity**: O(m * n)
  - Memoization matrix
  - Recursion stack: O(m * n) worst case

## Visual Representation

### Example 1: Detailed Walkthrough
```
Matrix:
[9, 9, 4]
[6, 6, 8]
[2, 1, 1]

Finding longest path starting from (2,1) = 1:

Step 1: Start at (2,1) value=1
Neighbors:
  - (2,0)=2 > 1 ✓ can go there
  - (2,2)=1 = 1 ✗ not increasing
  - (1,1)=6 > 1 ✓ can go there

Step 2: Try (2,0) value=2
Neighbors from 2:
  - (2,1)=1 < 2 ✗
  - (1,0)=6 > 2 ✓ can go there

Step 3: Try (1,0) value=6
Neighbors from 6:
  - (0,0)=9 > 6 ✓ can go there
  - (1,1)=6 = 6 ✗
  - (2,0)=2 < 6 ✗

Step 4: Try (0,0) value=9
Neighbors from 9:
  - All neighbors are <= 9
  - Dead end, return 1

Backtrack:
Path length from (1,0): 1 + 1 = 2
Path length from (2,0): 1 + 2 = 3
Path length from (2,1): 1 + 3 = 4

Complete path: 1 -> 2 -> 6 -> 9 (length 4)
```

### Memoization Example
```
Matrix:
[9, 9, 4]
[6, 6, 8]
[2, 1, 1]

Memoization table after computing:
[1, 1, 1]
[2, 1, 1]
[3, 4, 1]

Each cell stores the longest increasing path starting from that cell.
```

## Code Walkthrough

### Template Implementation (DFS + Memoization)
```java
public class LongestIncreasingPathInMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        // Memoization matrix
        int[][] memo = new int[m][n];
        int maxPath = 0;

        // Try starting from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, memo));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        // Return cached result if available
        if (memo[i][j] != 0)
            return memo[i][j];

        int m = matrix.length;
        int n = matrix[0].length;

        // Directions: up, down, left, right
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        int maxLen = 1;  // At least current cell

        // Try all 4 directions
        for (int[] dir : dirs) {
            int newI = i + dir[0];
            int newJ = j + dir[1];

            // Check bounds and increasing condition
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n
                && matrix[newI][newJ] > matrix[i][j]) {

                int len = 1 + dfs(matrix, newI, newJ, memo);
                maxLen = Math.max(maxLen, len);
            }
        }

        // Cache result
        memo[i][j] = maxLen;
        return maxLen;
    }
}
```

### Alternative: Topological Sort (BFS-based)
```java
public int longestIncreasingPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0)
        return 0;

    int m = matrix.length;
    int n = matrix[0].length;
    int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    // Calculate outdegree for each cell
    int[][] outdegree = new int[m][n];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            for (int[] dir : dirs) {
                int ni = i + dir[0];
                int nj = j + dir[1];

                if (ni >= 0 && ni < m && nj >= 0 && nj < n
                    && matrix[ni][nj] > matrix[i][j]) {
                    outdegree[i][j]++;
                }
            }
        }
    }

    // BFS from cells with outdegree 0 (local maxima)
    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (outdegree[i][j] == 0) {
                queue.offer(new int[]{i, j});
            }
        }
    }

    int length = 0;
    while (!queue.isEmpty()) {
        length++;
        int size = queue.size();

        for (int k = 0; k < size; k++) {
            int[] cell = queue.poll();
            int i = cell[0];
            int j = cell[1];

            // Check all neighbors
            for (int[] dir : dirs) {
                int ni = i + dir[0];
                int nj = j + dir[1];

                if (ni >= 0 && ni < m && nj >= 0 && nj < n
                    && matrix[ni][nj] < matrix[i][j]) {

                    outdegree[ni][nj]--;
                    if (outdegree[ni][nj] == 0) {
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
        }
    }

    return length;
}
```

## Edge Cases
1. **Empty matrix**: Return 0
2. **Single cell**: Return 1
3. **All same values**: Return 1 (no increasing path possible)
4. **Strictly increasing row/column**: Return length of row/column
5. **Multiple equal-length paths**: Return any maximum length

## Related Problems
- **Longest Increasing Subsequence (LeetCode 300)**: 1D version
- **Number of Increasing Paths in Grid (LeetCode 2328)**: Count paths
- **Minimum Path Sum (LeetCode 64)**: Different optimization
- **Word Search II (LeetCode 212)**: DFS with trie
- **Pacific Atlantic Water Flow (LeetCode 417)**: Similar grid DFS

## Tags
- Dynamic Programming
- Depth-First Search
- Breadth-First Search
- Graph
- Topological Sort
- Memoization
- Matrix
- Hard
- Google Interview
- Amazon Interview

**Note**: The provided Java file is empty, so this solution represents a complete implementation based on the problem requirements.
