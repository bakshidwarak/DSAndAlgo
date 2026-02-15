# LeetCode 399: Evaluate Division

## Problem Statement

You are given an array of variable pairs `equations` and an array of real numbers `values`, where `equations[i] = [Ai, Bi]` and `values[i]` represent the equation `Ai / Bi = values[i]`. Each `Ai` or `Bi` is a string that represents a single variable.

You are also given some `queries`, where `queries[j] = [Cj, Dj]` represents the `jth` query where you must find the answer for `Cj / Dj = ?`.

Return the answers to all queries. If a single answer cannot be determined, return `-1.0`.

**Note:** The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

### Examples

**Example 1:**
```
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0],
       queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
a / c = (a / b) * (b / c) = 2.0 * 3.0 = 6.0
b / a = 1 / (a / b) = 1 / 2.0 = 0.5
a / e = -1.0 (e not in equations)
a / a = 1.0
x / x = -1.0 (x not in equations)
```

**Example 2:**
```
Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
       queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
```

**Constraints:**
- 1 <= equations.length <= 20
- equations[i].length == 2
- 1 <= Ai.length, Bi.length <= 5
- values.length == equations.length
- 0.0 < values[i] <= 20.0
- 1 <= queries.length <= 20
- queries[i].length == 2
- 1 <= Cj.length, Dj.length <= 5
- Ai, Bi, Cj, Dj consist of lowercase English letters and digits

## Key Insights

1. **Graph Representation**: Model as a weighted directed graph
   - Nodes: variables (a, b, c, etc.)
   - Edges: equations with values as weights
   - a/b = 2.0 means edge a->b with weight 2.0

2. **Bidirectional Edges**: If a/b = 2.0, then b/a = 0.5 (reciprocal)

3. **Path Finding**: To find a/c, find path from a to c and multiply all edge weights

4. **DFS Solution**: Use DFS to traverse the graph and accumulate products

5. **Visited Set**: Need to track visited nodes to avoid cycles

## Algorithm Steps

### Graph Construction
1. Create adjacency list representation
2. For each equation [a, b] with value v:
   - Add edge a -> b with weight v
   - Add edge b -> a with weight 1/v

### Query Processing
1. For each query [start, end]:
   - If start or end not in graph, return -1.0
   - If start == end, return 1.0
   - Otherwise, DFS from start to find end

### DFS Process
1. Mark current node as visited
2. If current == target, return accumulated result
3. For each neighbor of current:
   - If not visited, recursively DFS with result * edge_weight
   - If path found (result != -1.0), return result
4. Unmark current node (backtrack)
5. Return -1.0 if no path found

## Complexity Analysis

- **Time Complexity**: O(E + Q * (V + E))
  - E = number of equations
  - V = number of unique variables
  - Q = number of queries
  - Graph construction: O(E)
  - Each query: O(V + E) for DFS
  - Overall: O(Q * (V + E))

- **Space Complexity**: O(V + E)
  - Graph storage: O(V + E)
  - Visited set: O(V)
  - DFS recursion stack: O(V)
  - Overall: O(V + E)

## Visual Explanation

### Example: equations = [["a","b"],["b","c"]], values = [2.0,3.0]

```
Graph Construction:

Step 1: a/b = 2.0
  a --2.0--> b
  b --0.5--> a

Step 2: b/c = 3.0
  b --3.0--> c
  c --0.33--> b

Complete Graph:
     0.5
  a <--- b ---> c
  |  2.0  |  3.0
  |       v 0.33
  +----> (reciprocal edges)

Query: a/c = ?
Path: a -> b -> c
Calculation: 2.0 * 3.0 = 6.0

Query: b/a = ?
Direct edge: b -> a with weight 0.5
Result: 0.5

Query: a/e = ?
No node 'e' in graph
Result: -1.0

Query: a/a = ?
Start == End
Result: 1.0
```

### DFS Trace for Query a/c

```
DFS(a, c, result=1.0, visited={})

Step 1: Start at 'a'
  visited = {a}
  a != c, continue

Step 2: Explore neighbor 'b' (edge weight 2.0)
  DFS(b, c, result=1.0*2.0=2.0, visited={a})
    visited = {a, b}
    b != c, continue

    Step 3: Explore neighbor 'c' (edge weight 3.0)
      DFS(c, c, result=2.0*3.0=6.0, visited={a,b})
        visited = {a, b, c}
        c == c, FOUND!
        return 6.0

      return 6.0
  return 6.0

Final result: 6.0
```

### Complex Graph Example

```
equations = [["a","b"],["b","c"],["c","d"],["d","e"]]
values = [2.0, 3.0, 4.0, 5.0]

Graph:
a --2.0--> b --3.0--> c --4.0--> d --5.0--> e

Query: a/e = ?
Path: a -> b -> c -> d -> e
Result: 2.0 * 3.0 * 4.0 * 5.0 = 120.0

Query: e/a = ?
Path: e -> d -> c -> b -> a
Result: (1/5.0) * (1/4.0) * (1/3.0) * (1/2.0) = 1/120.0
```

## Code Walkthrough

```java
// Neighbor class to store node and edge weight
static class Neighbour {
    String node;
    double value;

    public Neighbour(String node, double val) {
        this.node = node;
        this.value = val;
    }
}

public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    // Step 1: Build graph
    Map<String, List<Neighbour>> graph = new HashMap<>();

    for (int i = 0; i < equations.length; i++) {
        String[] eq = equations[i];

        // Ensure both nodes exist in graph
        graph.putIfAbsent(eq[0], new ArrayList<>());
        graph.putIfAbsent(eq[1], new ArrayList<>());

        // Add bidirectional edges
        // eq[0] / eq[1] = values[i]
        graph.get(eq[0]).add(new Neighbour(eq[1], values[i]));
        // eq[1] / eq[0] = 1 / values[i]
        graph.get(eq[1]).add(new Neighbour(eq[0], 1 / values[i]));
    }

    // Step 2: Process queries
    double[] result = new double[queries.length];

    for (int i = 0; i < queries.length; i++) {
        String[] query = queries[i];

        // Check if both variables exist in graph
        if (!graph.containsKey(query[0]) || !graph.containsKey(query[1])) {
            result[i] = -1.0;
        } else {
            // DFS to find path and compute result
            result[i] = dfs(query[0], query[1], 1.0, new HashSet<>(), graph);
        }
    }

    return result;
}

public double dfs(String start, String end, double result,
                  Set<String> visited, Map<String, List<Neighbour>> graph) {
    // Avoid cycles
    if (visited.contains(start))
        return -1.0;

    // Found the target
    if (start.equals(end))
        return result;

    // Mark as visited
    visited.add(start);

    // Explore all neighbors
    List<Neighbour> neighbours = graph.get(start);
    double val = -1.0;

    for (Neighbour n : neighbours) {
        // Recursively search, multiplying edge weights
        val = dfs(n.node, end, n.value * result, visited, graph);

        // If path found, break early
        if (val != -1.0)
            break;
    }

    // Backtrack: remove from visited
    visited.remove(start);

    return val;
}
```

## Alternative Approach: Union-Find

Can also solve using weighted Union-Find where each node stores its ratio to its parent.

## Edge Cases

1. **Self Query**: query = ["a", "a"]
   - Output: 1.0 (anything divided by itself is 1)

2. **Variable Not in Graph**: query = ["a", "x"]
   - Output: -1.0

3. **Direct Edge**: query matches an equation
   - Return the value directly

4. **No Path**: Disconnected components
   ```
   equations = [["a","b"],["c","d"]]
   query = ["a","d"]
   Output: -1.0
   ```

5. **Single Equation**: equations = [["a","b"]], values = [2.0]
   - Can answer ["a","b"], ["b","a"], ["a","a"], ["b","b"]

6. **Cycle in Graph**:
   ```
   a -> b -> c -> a
   DFS must handle with visited set
   ```

## Related Problems

1. **LeetCode 990**: Satisfiability of Equality Equations
2. **LeetCode 721**: Accounts Merge
3. **LeetCode 684**: Redundant Connection
4. **LeetCode 765**: Couples Holding Hands
5. **LeetCode 737**: Sentence Similarity II

## Tags

- Graph
- Depth-First Search (DFS)
- Union-Find
- Hash Table
- Weighted Graph
- Path Finding
- Division Chain
