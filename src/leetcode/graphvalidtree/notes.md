# LeetCode 261: Graph Valid Tree

## Problem Statement

Given `n` nodes labeled from `0` to `n-1` and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

**Note:** You can assume that no duplicate edges will appear in edges. Since all edges are undirected, `[0, 1]` is the same as `[1, 0]` and thus will not appear together in edges.

### Examples

**Example 1:**
```
Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true

Graph:
    0
   /|\
  1 2 3
 /
4
```

**Example 2:**
```
Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false

Graph:
    0
    |
    1---4
   / \
  2---3
(cycle between 1-2-3)
```

**Constraints:**
- 1 <= n <= 2000
- 0 <= edges.length <= 5000
- edges[i].length == 2
- 0 <= ai, bi < n
- ai != bi
- There are no duplicate edges

## Key Insights

1. **Tree Properties**:
   - Must be connected (all nodes reachable from any starting node)
   - Must be acyclic (no cycles)
   - Must have exactly n-1 edges

2. **Quick Check**: If edges.length != n-1, cannot be a tree

3. **Cycle Detection**: Use DFS with parent tracking for undirected graph

4. **Connectivity Check**: All nodes must be visited after DFS from any starting node

5. **Graph Representation**: Use adjacency list for efficient traversal

## Algorithm Steps

### DFS-Based Solution

1. **Quick Edge Count Check**:
   - If edges.length != n-1, return false

2. **Build Graph**:
   - Create adjacency list representation
   - For each edge [a,b], add b to a's neighbors and vice versa

3. **Cycle Detection**:
   - Use DFS from node 0
   - Track visited nodes
   - Track current path (back edge set)
   - If encounter node in current path (not parent), cycle detected

4. **Connectivity Check**:
   - After DFS, check if all nodes were visited
   - If any unvisited, graph is disconnected

5. **Return** true only if no cycle and all connected

## Complexity Analysis

- **Time Complexity**: O(V + E)
  - V = n (number of vertices/nodes)
  - E = edges.length
  - Build graph: O(E)
  - DFS: O(V + E)
  - Overall: O(V + E)

- **Space Complexity**: O(V + E)
  - Adjacency list: O(V + E)
  - Visited array: O(V)
  - Back edge set: O(V)
  - Recursion stack: O(V)
  - Overall: O(V + E)

## Visual Explanation

### Example 1: Valid Tree

```
n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]

Graph:
    0
   /|\
  1 2 3
 /
4

Properties:
- Edges: 4 = n-1 ✓
- Connected: All nodes reachable from 0 ✓
- No cycles: Tree structure ✓

DFS from 0:
  Visit 0 (backEdge: {0})
    Visit 1 (backEdge: {0,1})
      Visit 4 (backEdge: {0,1,4})
        No more neighbors
      Remove 4 from backEdge
    Remove 1 from backEdge
    Visit 2 (backEdge: {0,2})
      No more neighbors
    Remove 2 from backEdge
    Visit 3 (backEdge: {0,3})
      No more neighbors
    Remove 3 from backEdge
  Remove 0 from backEdge

All visited: {0,1,2,3,4} = 5 nodes ✓
Result: true
```

### Example 2: Invalid (Has Cycle)

```
n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]

Graph:
    0
    |
    1---4
   / \
  2---3
  \___/

Cycle: 1 -> 2 -> 3 -> 1

DFS from 0:
  Visit 0 (backEdge: {0})
    Visit 1 (backEdge: {0,1})
      Visit 2 (backEdge: {0,1,2})
        Visit 3 (backEdge: {0,1,2,3})
          Try to visit 1 (neighbor of 3)
          1 is in backEdge and not parent!
          CYCLE DETECTED! ✗

Result: false
```

### Example 3: Invalid (Disconnected)

```
n = 4, edges = [[0,1],[2,3]]

Graph:
  0---1    2---3
  (island 1) (island 2)

Properties:
- Edges: 2 != n-1 (3) ✗
- Disconnected: Can't reach 2,3 from 0 ✗

Result: false
```

## Code Walkthrough

```java
public boolean validTree(int n, int[][] edges) {
    // Quick check: tree must have n-1 edges
    if (edges.length != n - 1) {
        return false;
    }

    // Build adjacency list graph
    Map<Integer, Graph> nodes = constructGraph(n, edges);

    // Check for cycles and connectivity
    boolean[] visited = new boolean[n];
    Set<Integer> backEdge = new HashSet<>();

    // Start DFS from node 0
    // If cycle detected, return false
    if (!validTreeNode(nodes.get(0), visited, backEdge, -1)) {
        return false;
    }

    // Check if all nodes were visited (connected)
    for (int i = 0; i < n; i++) {
        if (!visited[i])
            return false;  // Disconnected graph
    }

    return true;
}

public boolean validTreeNode(Graph node, boolean[] visited,
                             Set<Integer> backEdge, int parent) {
    // Mark as visited
    visited[node.val] = true;

    // Check if already in current path (cycle)
    if (backEdge.contains(node.val))
        return false;

    // Add to current path
    backEdge.add(node.val);

    // Explore all neighbors
    for (Graph neighbour : node.neigbours) {
        // Don't revisit parent (undirected edge)
        if (neighbour.val != parent) {
            // Recursively check neighbor
            if (!validTreeNode(neighbour, visited, backEdge, node.val)) {
                return false;  // Cycle found
            }
        }
    }

    // Remove from current path (backtrack)
    backEdge.remove(node.val);

    return true;  // No cycle found
}

public Map<Integer, Graph> constructGraph(int n, int[][] edges) {
    Map<Integer, Graph> graphMap = new HashMap<>();

    // Create nodes
    for (int i = 0; i < n; i++) {
        graphMap.put(i, new Graph(i));
    }

    // Add undirected edges
    for (int i = 0; i < edges.length; i++) {
        int u = edges[i][0];
        int v = edges[i][1];
        graphMap.get(u).neigbours.add(graphMap.get(v));
        graphMap.get(v).neigbours.add(graphMap.get(u));
    }

    return graphMap;
}

class Graph {
    int val;
    List<Graph> neigbours = new ArrayList<>();

    public Graph(int val) {
        this.val = val;
    }
}
```

## Alternative Approach: Union-Find

```java
public boolean validTree(int n, int[][] edges) {
    // Tree must have n-1 edges
    if (edges.length != n - 1) return false;

    // Union-Find structure
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
        parent[i] = i;
    }

    // Try to union all edges
    for (int[] edge : edges) {
        int root1 = find(parent, edge[0]);
        int root2 = find(parent, edge[1]);

        // If already in same set, cycle exists
        if (root1 == root2) return false;

        // Union the sets
        parent[root1] = root2;
    }

    return true;  // No cycles and correct edge count
}

private int find(int[] parent, int i) {
    if (parent[i] != i) {
        parent[i] = find(parent, parent[i]);  // Path compression
    }
    return parent[i];
}
```

## Why Parent Tracking Matters

```
In undirected graph:
  1---2

When at node 1 exploring neighbors:
  - See node 2 (not yet visited)
  - Visit 2, mark parent=1

When at node 2 exploring neighbors:
  - See node 1 (already visited)
  - But 1 is the parent!
  - Must skip to avoid false cycle detection

Without parent tracking:
  Would incorrectly detect cycle 1-2-1
```

## Edge Cases

1. **Single Node**: n = 1, edges = []
   - Valid tree (no edges needed)

2. **Two Nodes Connected**: n = 2, edges = [[0,1]]
   - Valid tree

3. **Two Nodes Disconnected**: n = 2, edges = []
   - Invalid (not connected)

4. **Self Loop**: edges = [[0,0]]
   - Invalid (cycle)

5. **Multiple Components**:
   ```
   n = 4, edges = [[0,1],[2,3]]
   Two separate trees
   Invalid
   ```

6. **Complete Graph**: All nodes connected
   - If n nodes, n(n-1)/2 edges
   - Has cycles, invalid

7. **Linear Chain**: 0-1-2-3-4
   - Valid tree if n-1 edges

## Tree Properties Summary

A valid tree with n nodes must satisfy:

1. **Exactly n-1 edges**
2. **Connected**: All nodes reachable
3. **Acyclic**: No cycles
4. **Undirected**: Edges work both ways

Note: Properties 1 + 2 imply property 3!
- If connected with n-1 edges, must be acyclic

## Related Problems

1. **LeetCode 323**: Number of Connected Components in an Undirected Graph
2. **LeetCode 684**: Redundant Connection
3. **LeetCode 685**: Redundant Connection II
4. **LeetCode 207**: Course Schedule (directed graph cycle detection)
5. **LeetCode 210**: Course Schedule II
6. **LeetCode 547**: Number of Provinces

## Tags

- Graph
- Tree
- Depth-First Search (DFS)
- Union-Find
- Cycle Detection
- Connectivity
- Undirected Graph
