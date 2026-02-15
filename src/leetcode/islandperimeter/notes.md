# Island Perimeter

## Problem Statement
**LeetCode Problem 463**: Island Perimeter (Easy)

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

### Examples
**Example 1:**
```
Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Explanation: The perimeter is the 16 yellow stripes in the visualization
```

## Key Insights
1. **Perimeter Contribution**: Each land cell contributes 4 edges initially
2. **Subtract Neighbors**: For each neighbor that is also land, subtract 1 edge
3. **DFS/BFS Approach**: Visit all connected land cells
4. **Simple Counting**: Can also iterate through grid and count exposed edges
5. **Formula**: Perimeter = 4 * (number of land cells) - 2 * (number of adjacent pairs)

## Algorithm Steps

### Approach 1: DFS with Visited Array
```
1. Create visited array to track processed cells
2. Find first land cell (grid[i][j] == 1)
3. Start DFS from that cell:
   a. Mark current cell as visited
   b. Count neighbors that are land (0-4 neighbors)
   c. Add (4 - neighbors) to perimeter count
   d. Recursively process unvisited land neighbors
4. Return total perimeter
```

### Approach 2: Simple Iteration (More Efficient)
```
1. For each cell in grid:
   a. If cell is land (value = 1):
      - Add 4 to perimeter
      - Check all 4 neighbors
      - For each land neighbor, subtract 1
2. Return total perimeter
```

## Complexity Analysis

### DFS Approach:
- **Time Complexity**: O(m * n)
  - Visit each cell at most once
  - m = rows, n = columns
- **Space Complexity**: O(m * n)
  - Visited array
  - Recursion stack in worst case

### Simple Iteration:
- **Time Complexity**: O(m * n)
  - Single pass through grid
- **Space Complexity**: O(1)
  - No extra data structures needed

## Visual Representation

### Example Grid Analysis
```
Grid:
  0 1 2 3
0 [0,1,0,0]
1 [1,1,1,0]
2 [0,1,0,0]
3 [1,1,0,0]

Land cells marked with coordinates:
        (0,1)
         |
(1,0)-(1,1)-(1,2)
         |
      (2,1)
         |
      (3,0)-(3,1)

Cell-by-Cell Perimeter Calculation:
-------------------------------------
(0,1): 4 edges - 1 neighbor below = 3
       +++
       +-+   (3 exposed edges)
        +

(1,0): 4 edges - 1 neighbor right = 3
       +++
       + +   (3 exposed edges)
       +++

(1,1): 4 edges - 4 neighbors = 0
       + +
       +-+   (0 exposed edges, all connected)
       + +

(1,2): 4 edges - 1 neighbor left = 3
       +++
       + +   (3 exposed edges)
       +++

(2,1): 4 edges - 2 neighbors (above, below) = 2
        +
       + +   (2 exposed edges)
        +

(3,0): 4 edges - 1 neighbor right = 3
       +++
       + +   (3 exposed edges)
       +++

(3,1): 4 edges - 2 neighbors (left, above) = 2
        +
       +-+   (2 exposed edges)
       +++

Total: 3 + 3 + 0 + 3 + 2 + 3 + 2 = 16
```

### Perimeter Visualization
```
    +--+
    |  |
+--+--+--+
|  |  |  |
+--+--+--+
    |  |
    +--+
    |  |
+--+--+
|  |  |
+--+--+

Count exposed edges: 16
```

## Code Walkthrough

### DFS Solution
```java
public int islandPerimeter(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0)
        return 0;

    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int[] count = new int[1];  // Use array for pass-by-reference

    // Find first land cell and start DFS
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (!visited[i][j] && grid[i][j] == 1) {
                exhaust(grid, i, j, count, visited);
            }
        }
    }

    return count[0];
}

public void exhaust(int[][] grid, int i, int j, int[] count, boolean[][] visited) {
    if (visited[i][j])
        return;

    visited[i][j] = true;

    // Get land neighbors
    List<int[]> neighbours = getNeighbours(grid, i, j);

    // Perimeter contribution: 4 edges minus number of land neighbors
    count[0] += (4 - neighbours.size());

    // Recursively process neighbors
    for (int[] neighbour : neighbours) {
        exhaust(grid, neighbour[0], neighbour[1], count, visited);
    }
}

public List<int[]> getNeighbours(int[][] grid, int i, int j) {
    List<int[]> neighbours = new ArrayList<>();

    // Check all 4 directions
    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
        neighbours.add(new int[] { i + 1, j });
    }
    if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
        neighbours.add(new int[] { i, j + 1 });
    }
    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
        neighbours.add(new int[] { i - 1, j });
    }
    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
        neighbours.add(new int[] { i, j - 1 });
    }

    return neighbours;
}
```

### Simple Iteration Solution (More Efficient)
```java
public int islandPerimeter(int[][] grid) {
    int perimeter = 0;

    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) {
                // Start with 4 edges
                perimeter += 4;

                // Subtract 1 for each land neighbor
                // Check above
                if (i > 0 && grid[i-1][j] == 1) {
                    perimeter--;
                }
                // Check left
                if (j > 0 && grid[i][j-1] == 1) {
                    perimeter--;
                }
                // Check below
                if (i < grid.length-1 && grid[i+1][j] == 1) {
                    perimeter--;
                }
                // Check right
                if (j < grid[0].length-1 && grid[i][j+1] == 1) {
                    perimeter--;
                }
            }
        }
    }

    return perimeter;
}
```

### Alternative: Count Land and Adjacent Pairs
```java
public int islandPerimeter(int[][] grid) {
    int lands = 0;
    int adjacentPairs = 0;

    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) {
                lands++;

                // Count adjacent pairs (only check right and down to avoid double counting)
                if (i < grid.length - 1 && grid[i+1][j] == 1) {
                    adjacentPairs++;
                }
                if (j < grid[0].length - 1 && grid[i][j+1] == 1) {
                    adjacentPairs++;
                }
            }
        }
    }

    // Formula: 4 * lands - 2 * adjacentPairs
    return 4 * lands - 2 * adjacentPairs;
}
```

## Edge Cases
1. **Single cell island**: Perimeter = 4
2. **Straight line island**: Perimeter = 2 * (length + 1)
3. **Square island**: Check all interior cells contribute 0
4. **Entire grid is island**: Only outer boundary counts
5. **Empty grid**: Perimeter = 0

### Edge Case Examples
```
Single cell:
[1]
Perimeter: 4

2x1 island:
[1,1]
Perimeter: 6

2x2 island:
[1,1]
[1,1]
Perimeter: 8

3x3 island:
[1,1,1]
[1,1,1]
[1,1,1]
Perimeter: 12
```

## Related Problems
- **Number of Islands (LeetCode 200)**: Count separate islands using DFS/BFS
- **Max Area of Island (LeetCode 695)**: Find largest island
- **Island Perimeter II**: Multiple islands version
- **Flood Fill (LeetCode 733)**: Similar grid traversal
- **Number of Enclaves (LeetCode 1020)**: Count interior land cells

## Tags
- Array
- Matrix
- Depth-First Search
- Breadth-First Search
- Graph
- Grid
- Easy
- Geometry
