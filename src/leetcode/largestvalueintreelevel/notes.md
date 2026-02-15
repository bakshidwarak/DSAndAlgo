# Find Largest Value in Each Tree Row

## Problem Statement
**LeetCode Problem 515**: Find Largest Value in Each Tree Row (Medium)

You need to find the largest value in each row of a binary tree.

### Examples
**Example 1:**
```
Input:
          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]
```

**Example 2:**
```
Input:
          1
         / \
        2   3

Output: [1, 3]
```

## Key Insights
1. **Level-Order Traversal**: Process tree level by level
2. **Track Maximum**: Maintain max value for each level
3. **BFS with Queue**: Use queue to process nodes level by level
4. **Pair Structure**: Store node with its level/order
5. **Update Strategy**: Update max when we see larger value at same level

## Algorithm Steps
```
1. Create result list to store max values
2. Create queue and add root with level 0
3. While queue is not empty:
   a. Dequeue (node, level) pair
   b. If level exists in result:
      - Update max for that level if current value is larger
   c. If level doesn't exist:
      - Add current value as max for new level
   d. Enqueue left child with level+1 (if exists)
   e. Enqueue right child with level+1 (if exists)
4. Return result list
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - Visit each node exactly once
  - n = number of nodes
- **Space Complexity**: O(w)
  - Queue stores at most one level at a time
  - w = maximum width of tree
  - Result list: O(h) where h = height

## Visual Representation

### Example: Detailed Process
```
Tree:
          1
         / \
        3   2
       / \   \
      5   3   9

Level-by-Level Processing:
--------------------------

Level 0: [1]
  Max: 1
  Children to process: 3 (level 1), 2 (level 1)

Level 1: [3, 2]
  Initial max: 3
  Process 2: 3 > 2, max stays 3
  Children to process: 5 (level 2), 3 (level 2), 9 (level 2)

Level 2: [5, 3, 9]
  Initial max: 5
  Process 3: 5 > 3, max stays 5
  Process 9: 9 > 5, max updates to 9
  No children

Result: [1, 3, 9]
```

### Queue State Visualization
```
Initial:
Queue: [(1, order=0)]
MaxList: []

Step 1: Process (1, order=0)
Queue: [(3, order=1), (2, order=1)]
MaxList: [1]

Step 2: Process (3, order=1)
Queue: [(2, order=1), (5, order=2), (3, order=2)]
MaxList: [1, 3]

Step 3: Process (2, order=1)
Queue: [(5, order=2), (3, order=2), (9, order=2)]
MaxList: [1, 3]  (3 > 2, no update)

Step 4: Process (5, order=2)
Queue: [(3, order=2), (9, order=2)]
MaxList: [1, 3, 5]

Step 5: Process (3, order=2)
Queue: [(9, order=2)]
MaxList: [1, 3, 5]  (5 > 3, no update)

Step 6: Process (9, order=2)
Queue: []
MaxList: [1, 3, 9]  (9 > 5, update!)

Final: [1, 3, 9]
```

## Code Walkthrough

### Current Implementation (BFS with Pair)
```java
static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

static class Pair {
    TreeNode node;
    int order;  // Level/depth of node

    public Pair(TreeNode node, int order) {
        this.node = node;
        this.order = order;
    }
}

public static List<Integer> largestValues(TreeNode root) {
    List<Integer> maxList = new ArrayList<>();

    // Use queue for BFS
    Queue<Pair> queue = new LinkedList<Pair>();
    queue.add(new Pair(root, 0));

    while (!queue.isEmpty()) {
        Pair p = queue.remove();

        // Check if we've seen this level before
        if (p.order < maxList.size()) {
            // Level exists, update max if necessary
            int currMax = maxList.get(p.order);
            currMax = Math.max(currMax, p.node.val);
            maxList.set(p.order, currMax);
        } else {
            // New level, add first value as max
            maxList.add(p.node.val);
        }

        // Add children to queue with incremented order
        if (p.node.left != null)
            queue.add(new Pair(p.node.left, p.order + 1));

        if (p.node.right != null)
            queue.add(new Pair(p.node.right, p.order + 1));
    }

    return maxList;
}
```

### Alternative: Level-by-Level BFS
```java
public List<Integer> largestValues(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        int levelMax = Integer.MIN_VALUE;

        // Process all nodes at current level
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            levelMax = Math.max(levelMax, node.val);

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        result.add(levelMax);
    }

    return result;
}
```

### DFS Alternative (Recursive)
```java
public List<Integer> largestValues(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfs(root, 0, result);
    return result;
}

private void dfs(TreeNode node, int level, List<Integer> result) {
    if (node == null) return;

    // If this is first node at this level
    if (level == result.size()) {
        result.add(node.val);
    } else {
        // Update max for this level
        result.set(level, Math.max(result.get(level), node.val));
    }

    // Recurse to children
    dfs(node.left, level + 1, result);
    dfs(node.right, level + 1, result);
}
```

## Edge Cases
1. **Null tree**: Return empty list
2. **Single node**: Return list with one element
3. **Only left children**: Each level has one element
4. **Only right children**: Each level has one element
5. **Negative values**: Use Integer.MIN_VALUE for initialization
6. **All same values**: Each level returns that value

### Edge Case Examples
```
1. Null tree:
   Input: null
   Output: []

2. Single node:
   Input: [1]
   Output: [1]

3. Left-skewed:
   Input:   3
           /
          2
         /
        1
   Output: [3, 2, 1]

4. All negative:
   Input:    -1
           /    \
         -5     -2
   Output: [-1, -2]

5. Same values:
   Input:    5
           /   \
          5     5
   Output: [5, 5]
```

## Comparison of Approaches

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| BFS with Pair | O(n) | O(w) | Clear level tracking | Extra Pair object |
| BFS Level-by-Level | O(n) | O(w) | No extra class needed | More code |
| DFS Recursive | O(n) | O(h) | Concise | Less intuitive |

Where:
- n = number of nodes
- w = maximum width (nodes at any level)
- h = height of tree

## Related Problems
- [**Binary Tree Level Order Traversal **](../binarytreelevelordertraversal/notes.md): Similar level-order traversal
- [**Binary Tree Zigzag Level Order Traversal **](../binarytreelevelordertraversal/notes.md): Alternating direction
- [**Average of Levels in Binary Tree **](../averagelevelsinabinarytree/notes.md): Compute average instead of max
- [**Minimum Depth of Binary Tree **](../depthofabinarytree/notes.md): Level-based calculation
- [**Maximum Width of Binary Tree **](../depthofabinarytree/notes.md): Width calculation

## Tags
- Tree
- Binary Tree
- Breadth-First Search
- Level-Order Traversal
- Queue
- Medium
- Microsoft Interview
