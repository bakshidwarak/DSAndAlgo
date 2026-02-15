# LeetCode 200: Number of Islands

## Problem Statement
Given a 2D grid map of '1's (land) and '0's (water), count the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are surrounded by water.

## Examples

**Example 1:**
```
Input:
11110
11010
11000
00000
Output: 1
```

**Example 2:**
```
Input:
11000
11000
00100
00011
Output: 3
```

## Key Insights

1. **Graph Traversal**: Treat grid as undirected graph
2. **DFS/BFS**: Use DFS or BFS to explore connected components
3. **Visited Tracking**: Mark visited cells to avoid revisits
4. **Component Counting**: Each new unvisited land starts a new island

## Algorithm Steps

1. Create visited 2D boolean array
2. Iterate through each cell in grid
3. When unvisited land ('1') found:
   - Increment island count
   - Use DFS/BFS to mark all connected land as visited
4. Return island count

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(m × n) - Visit each cell once |
| **Space Complexity** | O(m × n) - Visited array + recursion stack |
| **Best Case** | O(m × n) - All cells visited |
| **Worst Case** | O(m × n) - All cells are land |

- m, n = dimensions of grid
- Recursion depth: O(m × n) in worst case (spiral island)

## ASCII Visualization

```
Grid:
11000
11000
00100
00011

Visited tracking:
Step 1: Find '1' at (0,0), DFS explores connected land
T T 0 0 0
T T 0 0 0
0 0 0 0 0
0 0 0 0 0
Islands = 1

Step 2: Find '1' at (2,2), DFS explores it
T T 0 0 0
T T 0 0 0
0 0 T 0 0
0 0 0 0 0
Islands = 2

Step 3: Find '1' at (3,3), DFS explores connected land
T T 0 0 0
T T 0 0 0
0 0 T 0 0
0 0 0 T T
Islands = 3

Final answer: 3 islands

DFS Exploration for first island:
  (0,0)
  / | \ \
(1,0)(0,1)
      |
    (1,1)

Neighbors checked:
(0,0) → (1,0), (0,1) → (1,0), (1,1) → (1,1)
```

## Code Walkthrough

```java
public int numIslands(char[][] grid) {
    if (grid.length == 0)
        return 0;

    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int count = 0;

    // Iterate through each cell
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            // Found unvisited land
            if (!visited[i][j] && grid[i][j] == '1') {
                exhaust(grid, i, j, visited);  // DFS
                count++;  // Found new island
            }
        }
    }

    return count;
}

// DFS to mark all connected land as visited
public void exhaust(char[][] grid, int x, int y, boolean[][] visited) {
    // Boundary check
    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
        return;
    }

    // Already visited
    if (visited[x][y]) {
        return;
    }

    // Mark as visited
    visited[x][y] = true;

    // Explore all neighbors
    List<Pair> neighbors = getNeighbours(grid, x, y);
    for (Pair p : neighbors) {
        exhaust(grid, p.x, p.y, visited);
    }
}

// Get valid '1' neighbors
public List<Pair> getNeighbours(char[][] grid, int x, int y) {
    List<Pair> result = new ArrayList<>();

    // Down
    if (x + 1 < grid.length && grid[x + 1][y] == '1') {
        result.add(new Pair(x + 1, y));
    }
    // Right
    if (y + 1 < grid[0].length && grid[x][y + 1] == '1') {
        result.add(new Pair(x, y + 1));
    }
    // Up
    if (x - 1 >= 0 && grid[x - 1][y] == '1') {
        result.add(new Pair(x - 1, y));
    }
    // Left
    if (y - 1 >= 0 && grid[x][y - 1] == '1') {
        result.add(new Pair(x, y - 1));
    }

    return result;
}
```

**Execution Flow for Example 1:**
```
Grid:
11110
11010
11000
00000

i=0, j=0: grid[0][0]='1', not visited
  exhaust(grid, 0, 0, visited)
    DFS explores: (0,0)→(0,1)→(0,2)→(0,3)→(1,0)→(1,1)→(1,3)→(2,0)→(2,1)
    All marked as visited
  count = 1

i=0, j=1: Already visited, skip
i=0, j=2: Already visited, skip
i=0, j=3: Already visited, skip
i=0, j=4: grid[0][4]='0', skip

Continue scanning...
All remaining '1's are visited, no more islands found

Return count = 1
```

## Edge Cases

1. **Empty grid**: `[]` → 0
2. **All water**: `[0,0,0]` → 0
3. **All land**: `[1,1,1]` → 1
4. **Single cell**: `[1]` → 1 or `[0]` → 0
5. **Diagonal not connected**: `[[1,0],[0,1]]` → 2
6. **Checkerboard pattern**: Many islands
7. **L-shaped island**: Connected through turns

## Related Problems

1. **LeetCode 130** - Surrounded Regions (Similar grid DFS)
2. [Clone Graph](../clonegraph/notes.md)
3. [Course Schedule](../courseschedule/notes.md)
4. [Alien Dictionary](../aliendictionary/notes.md)
5. **LeetCode 417** - Pacific Atlantic Water Flow (Grid DFS)
6. **LeetCode 827** - Making A Large Island (DFS variant)

## Tags

`DFS` `BFS` `Union-Find` `Medium` `Amazon` `Google` `Microsoft` `Facebook`

## Alternative Approaches

### Approach 2: BFS
```java
public int numIslandsUsingBFS(char[][] grid) {
    if (grid.length == 0)
        return 0;

    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int count = 0;
    Queue<Pair> queue = new LinkedList<>();

    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (!visited[i][j] && grid[i][j] == '1') {
                queue.offer(new Pair(i, j));
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    Pair p = queue.poll();
                    // Process neighbors and add unvisited to queue
                }

                count++;
            }
        }
    }

    return count;
}
```

### Approach 3: Union-Find
```
- Treat each land cell as separate component initially
- Union connected land cells
- Count number of components with land
- Time: O(m×n × α(m×n)) ≈ O(m×n)
- Space: O(m×n) for parent array
```

## Optimization Notes

1. **Early Termination**: Stop exploration at boundaries
2. **Neighbor Generation**: Only check valid neighbors (avoid redundant checks)
3. **Visited Array**: Prevents revisiting cells (critical for correctness)
4. **Stack vs Recursion**: Iterative DFS using stack avoids stack overflow

## Notes

- This is a classic connected components problem
- Can be solved with DFS, BFS, or Union-Find
- DFS is most intuitive and efficient for this problem
- Key insight: Each new unvisited land cell = new island
- Common interview problem for graph traversal
- Foundation for understanding grid-based graph problems
