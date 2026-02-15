# LeetCode 207: Course Schedule

## Problem Statement

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

Return `true` if you can finish all courses. Otherwise, return `false`.

### Examples

**Example 1:**
```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
```

**Example 2:**
```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0
you should also have finished course 1. So it is impossible.
```

**Example 3:**
```
Input: numCourses = 5, prerequisites = [[1,0],[2,1],[3,2],[4,3]]
Output: true
Explanation: Linear dependency chain: 0 -> 1 -> 2 -> 3 -> 4
```

**Constraints:**
- 1 <= numCourses <= 2000
- 0 <= prerequisites.length <= 5000
- prerequisites[i].length == 2
- 0 <= ai, bi < numCourses
- All the pairs prerequisites[i] are unique

## Key Insights

1. **Graph Problem**: This is a graph cycle detection problem
2. **Directed Graph**: Prerequisites form a directed graph where edge (a, b) means "b is prerequisite of a"
3. **Cycle Detection**: If there's a cycle in the graph, it's impossible to complete all courses
4. **DFS with Back Edges**: Use DFS to detect cycles by tracking back edges
5. **Three States**: Need to track visited nodes, currently processing nodes (backEdge), and unvisited nodes

## Algorithm Steps

### Graph Construction
1. Create a graph where each course is a node
2. For each prerequisite [course, prereq], add edge from course -> prereq
3. Store in adjacency list representation

### Cycle Detection (DFS)
1. **Initialize**:
   - visited: tracks all explored nodes
   - backEdge: tracks nodes in current DFS path (recursion stack)

2. **For each unvisited node**:
   - Start DFS from that node
   - If cycle detected, return false

3. **DFS Process**:
   - If node is in backEdge set, cycle detected (return true for cycle)
   - If node already visited and not in backEdge, skip
   - Mark node as visited and add to backEdge
   - Recursively visit all prerequisites
   - Remove node from backEdge (backtracking)

4. **Return**: True if no cycles found, false otherwise

## Complexity Analysis

- **Time Complexity**: O(V + E)
  - V = numCourses (vertices)
  - E = prerequisites.length (edges)
  - Graph construction: O(E)
  - DFS visits each vertex once: O(V)
  - DFS explores each edge once: O(E)
  - Overall: O(V + E)

- **Space Complexity**: O(V + E)
  - Graph storage (adjacency list): O(V + E)
  - visited set: O(V)
  - backEdge set: O(V)
  - Recursion stack: O(V) in worst case
  - Overall: O(V + E)

## Visual Explanation

### Example 1: Can Finish

```
Input: numCourses = 4, prerequisites = [[1,0],[2,1],[3,2]]

Graph representation:
3 -> 2 -> 1 -> 0

Linear chain, no cycle
Result: true

DFS from 3:
- Visit 3 (backEdge: {3})
  - Visit 2 (backEdge: {3,2})
    - Visit 1 (backEdge: {3,2,1})
      - Visit 0 (backEdge: {3,2,1,0})
        - No more prereqs
      - Remove 0 from backEdge
    - Remove 1 from backEdge
  - Remove 2 from backEdge
- Remove 3 from backEdge
All courses reachable, no cycle: true
```

### Example 2: Cannot Finish (Cycle)

```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]

Graph representation:
1 <-> 0

Cycle detected
Result: false

DFS from 1:
- Visit 1 (backEdge: {1})
  - Visit 0 (backEdge: {1,0})
    - Try to visit 1 again
    - 1 is in backEdge -> CYCLE DETECTED!
Result: false
```

### Example 3: Complex Graph

```
Input: numCourses = 6, prerequisites = [[1,0],[2,0],[3,1],[3,2],[4,3],[5,3]]

Graph representation:
        0
       / \
      1   2
       \ /
        3
       / \
      4   5

DFS explores all paths, no back edge to ancestor -> No cycle
Result: true
```

## Code Walkthrough

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    // Step 1: Build graph
    Map<Integer, Graph> nodes = new HashMap<>();

    // Create nodes for all courses
    for (int i = 0; i < numCourses; i++) {
        nodes.put(i, new Graph(i));
    }

    // Add edges (course -> prerequisite)
    for (int i = 0; i < prerequisites.length; i++) {
        int course = prerequisites[i][0];
        int prereq = prerequisites[i][1];
        nodes.get(course).prereq.add(nodes.get(prereq));
    }

    // Step 2: Check for cycles
    return hasCycle(nodes);
}

public boolean hasCycle(Map<Integer, Graph> nodes) {
    Set<Graph> visited = new HashSet<>();
    Set<Graph> backEdge = new HashSet<>();

    // Check each component
    for (Graph g : nodes.values()) {
        if (!visited.contains(g)) {
            // If cycle found, return false
            if (isCyclic(g, visited, backEdge)) {
                return false;
            }
        }
    }

    // No cycle found
    return true;
}

public boolean isCyclic(Graph node, Set<Graph> visited, Set<Graph> backEdge) {
    // Cycle detected: node in current path
    if (backEdge.contains(node)) {
        return true;
    }

    // Already processed this node
    if (visited.contains(node)) {
        return false;
    }

    // Mark as visited and add to current path
    visited.add(node);
    backEdge.add(node);

    // Check all prerequisites
    for (Graph prereq : node.prereq) {
        if (isCyclic(prereq, visited, backEdge)) {
            return true;  // Cycle found in subtree
        }
    }

    // Backtrack: remove from current path
    backEdge.remove(node);

    return false;  // No cycle found
}
```

## Alternative Approach: Topological Sort (Kahn's Algorithm)

Using BFS and in-degree counting:

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] inDegree = new int[numCourses];
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
        adj.add(new ArrayList<>());
    }

    // Build graph and count in-degrees
    for (int[] prereq : prerequisites) {
        adj.get(prereq[1]).add(prereq[0]);
        inDegree[prereq[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (inDegree[i] == 0) {
            queue.offer(i);
        }
    }

    int count = 0;
    while (!queue.isEmpty()) {
        int course = queue.poll();
        count++;

        for (int next : adj.get(course)) {
            if (--inDegree[next] == 0) {
                queue.offer(next);
            }
        }
    }

    return count == numCourses;
}
```

## Edge Cases

1. **No Prerequisites**: prerequisites = []
   - All courses independent
   - Output: true

2. **Single Course**: numCourses = 1, prerequisites = []
   - Output: true

3. **Self-Loop**: prerequisites = [[0,0]]
   - Impossible to take course that requires itself
   - Output: false

4. **Multiple Disconnected Components**:
   ```
   Graph: 0 -> 1    2 -> 3
   No cycles in any component
   Output: true
   ```

5. **Long Chain**: prerequisites = [[1,0],[2,1],[3,2],...,[99,98]]
   - Linear dependency
   - Output: true

6. **Star Pattern**: All courses depend on course 0
   - No cycles
   - Output: true

## Related Problems

1. **LeetCode 210**: Course Schedule II (return the ordering)
2. **LeetCode 630**: Course Schedule III
3. **LeetCode 1462**: Course Schedule IV
4. **LeetCode 802**: Find Eventual Safe States
5. **LeetCode 310**: Minimum Height Trees
6. **LeetCode 269**: Alien Dictionary

## Tags

- Graph
- Directed Graph
- Cycle Detection
- Depth-First Search (DFS)
- Topological Sort
- Backtracking
- Hash Table
