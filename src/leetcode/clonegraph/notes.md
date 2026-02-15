# 133. Clone Graph

## Problem Statement
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

### Examples
```
Input: Adjacency list representation
Node 1: neighbors [2, 4]
Node 2: neighbors [1, 3]
Node 3: neighbors [2, 4]
Node 4: neighbors [1, 3]

Visual representation:
       1
      / \
     /   \
    0 --- 2
         / \
         \_/

Output: A deep copy of the graph
```

### Constraints
- Number of nodes will be between 1 and 100
- The graph is undirected
- There are no duplicate edges
- No self-loops in the graph

## Approach & Solution

### Key Insights
1. **Deep copy required**: Need to create new node objects, not just copy references
2. **HashMap for tracking**: Use HashMap to map original nodes to cloned nodes
3. **Avoid infinite loops**: Graph may have cycles, need to track visited nodes
4. **DFS traversal**: Recursively clone each node and its neighbors
5. **Clone before neighbors**: Must create and store clone before processing neighbors (handles cycles)

### Algorithm Steps
1. Handle null input (return null)
2. Create HashMap to store mapping: original node → cloned node
3. Start recursive cloning from input node:
   - If node is null, return null
   - If node already cloned (exists in HashMap), return the clone
   - Create new node with same label as original
   - **Important**: Add to HashMap BEFORE processing neighbors (prevents infinite recursion)
   - For each neighbor of original node:
     - Recursively clone the neighbor
     - Add cloned neighbor to current clone's neighbor list
   - Return the cloned node
4. Return the cloned graph

### Complexity Analysis
- **Time Complexity**: O(V + E)
  - Where V is number of vertices (nodes) and E is number of edges
  - Each node visited once: O(V)
  - Each edge traversed once: O(E)
  - HashMap operations are O(1) average
- **Space Complexity**: O(V)
  - HashMap stores V nodes
  - Recursion stack depth up to V in worst case (deep graph)
  - Each cloned node stores references to neighbors (part of output)

### Visualization
```
Original Graph:
    1 ─── 2
    │     │
    │     │
    0 ─── 3

Cloning Process:

Step 1: Clone node 1
        clones = {1 → 1'}
        Process neighbors: [0, 2]

Step 2: Clone node 0 (neighbor of 1)
        clones = {1 → 1', 0 → 0'}
        0' added to 1'.neighbors

Step 3: Clone node 2 (neighbor of 1)
        clones = {1 → 1', 0 → 0', 2 → 2'}
        2' added to 1'.neighbors

Step 4: Process node 2's neighbors
        When visiting node 1 again, found in HashMap
        Return existing clone 1'
        Prevents infinite recursion!

Final cloned graph:
    1'─── 2'
    │     │
    │     │
    0'─── 3'

Why add to HashMap before neighbors?
Without: clone(1) → clone(2) → clone(1) → clone(2) → ... (infinite!)
With:    clone(1) → add to map → clone(2) → finds 1 in map → return
```

## Code Walkthrough

```java
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null)
        return null;

    HashMap<UndirectedGraphNode, UndirectedGraphNode> clones = new HashMap<>();

    return cloneGraph(node, clones);
}

public UndirectedGraphNode cloneGraph(
        UndirectedGraphNode node,
        HashMap<UndirectedGraphNode, UndirectedGraphNode> clones) {

    // Base case: null node
    if (node == null)
        return null;

    // Already cloned: return existing clone
    if (clones.containsKey(node))
        return clones.get(node);

    // Create new node (clone)
    UndirectedGraphNode graph = new UndirectedGraphNode(node.label);

    /**
     * CRITICAL: Add to HashMap BEFORE processing neighbors
     * This prevents infinite recursion in cyclic graphs
     */
    clones.put(node, graph);

    // Clone all neighbors
    for (UndirectedGraphNode neighbour : node.neighbors) {
        UndirectedGraphNode clonedChild = cloneGraph(neighbour, clones);
        graph.neighbors.add(clonedChild);
    }

    return graph;
}
```

**Node Definition:**
```java
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
```

**BFS Alternative:**
```java
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null;

    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    Queue<UndirectedGraphNode> queue = new LinkedList<>();

    // Clone first node
    map.put(node, new UndirectedGraphNode(node.label));
    queue.add(node);

    while (!queue.isEmpty()) {
        UndirectedGraphNode curr = queue.poll();

        for (UndirectedGraphNode neighbor : curr.neighbors) {
            if (!map.containsKey(neighbor)) {
                map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                queue.add(neighbor);
            }
            map.get(curr).neighbors.add(map.get(neighbor));
        }
    }

    return map.get(node);
}
```

## Edge Cases
- **Null graph**: null → null
- **Single node**: [1] → [1'] (no neighbors)
- **Two connected nodes**: [1→2, 2→1] → Creates proper bidirectional clone
- **Cycle**: [1→2→3→1] → Handles cycle correctly with HashMap
- **Self-loop**: [1→1] → Correctly clones self-reference
- **Disconnected components**: If given only one component, clones only that component
- **Complete graph**: Every node connected to every other node

## Related Problems
- [**138. Copy List with Random Pointer**](../copylistwithrandompointer/notes.md): Similar cloning with extra pointers
- **1485. Clone Binary Tree With Random Pointer**: Tree cloning variant
- **Graph traversal problems**: Use similar DFS/BFS approaches

## Tags
`graph` `dfs` `bfs` `hash-table` `medium`
