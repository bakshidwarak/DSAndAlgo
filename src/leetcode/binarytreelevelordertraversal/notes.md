# 102. Binary Tree Level Order Traversal

## Problem Statement
Given a binary tree, return the level order traversal of its nodes' values (i.e., from left to right, level by level).

### Examples
```
Input: [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7

Output:
[
  [3],
  [9,20],
  [15,7]
]
```

### Constraints
- The number of nodes in the tree is in the range [0, 2000]
- -1000 <= Node.val <= 1000

## Approach & Solution

### Key Insights
1. **BFS traversal**: Use queue to process nodes level by level
2. **Track level number**: Store (node, level) pairs in queue
3. **Dynamic list sizing**: Grow result lists as needed for each level
4. **Level-based grouping**: Nodes at same level go in same sublist

### Algorithm Steps
1. Create result list and BFS queue
2. Add root to queue with level 0
3. While queue is not empty:
   - Dequeue a (node, level) pair
   - If result list size <= level, create new sublist for this level
   - Add node's value to the appropriate level's sublist
   - Enqueue left child (if exists) with level+1
   - Enqueue right child (if exists) with level+1
4. Return result list

### Complexity Analysis
- **Time Complexity**: O(n)
  - Where n is the number of nodes
  - Each node is visited exactly once
  - List operations (add, get, set) are O(1) amortized
- **Space Complexity**: O(n)
  - Queue can hold up to n/2 nodes at the last level
  - Result list holds all n node values
  - Total: O(n)

### Visualization
```
Input Tree:
        3
       / \
      9  20
        /  \
       15   7

BFS Process:
Step 0: Queue = [(3, level=0)]
        Result = []

Step 1: Dequeue (3, 0)
        Result size (0) <= level (0), create new list
        Result = [[3]]
        Enqueue: (9, 1), (20, 1)
        Queue = [(9, 1), (20, 1)]

Step 2: Dequeue (9, 1)
        Result size (1) <= level (1), create new list
        Result = [[3], [9]]
        No children
        Queue = [(20, 1)]

Step 3: Dequeue (20, 1)
        Result size (2) > level (1), use existing list
        Result = [[3], [9, 20]]
        Enqueue: (15, 2), (7, 2)
        Queue = [(15, 2), (7, 2)]

Step 4: Dequeue (15, 2)
        Result size (2) <= level (2), create new list
        Result = [[3], [9, 20], [15]]
        Queue = [(7, 2)]

Step 5: Dequeue (7, 2)
        Result size (3) > level (2), use existing list
        Result = [[3], [9, 20], [15, 7]]
        Queue = []

Output: [[3], [9, 20], [15, 7]]
```

## Code Walkthrough

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> order = new ArrayList<>();

    // Handle empty tree
    if (root == null) return order;

    // BFS queue storing (node, level) pairs
    Queue<Pair> queue = new LinkedList<Pair>();
    queue.add(new Pair(root, 0));

    while (!queue.isEmpty()) {
        Pair p = queue.remove();
        int level = p.level;

        // Create new sublist if this is a new level
        if (order.size() <= level) {
            List<Integer> elements = new ArrayList<>();
            elements.add(p.node.val);
            order.add(elements);
        } else {
            // Add to existing level's list
            List<Integer> elements = order.get(p.level);
            elements.add(p.node.val);
            order.set(p.level, elements);
        }

        // Add children to queue with incremented level
        if (p.node.left != null) {
            queue.add(new Pair(p.node.left, level + 1));
        }
        if (p.node.right != null) {
            queue.add(new Pair(p.node.right, level + 1));
        }
    }

    return order;
}
```

**Helper Class:**
```java
static class Pair {
    TreeNode node;
    int level;

    public Pair(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }
}
```

**Alternative Approach (Without Pair Class):**
Use queue size to determine level boundaries:
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.remove();
            currentLevel.add(node.val);

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        result.add(currentLevel);
    }

    return result;
}
```

## Edge Cases
- **Empty tree**: null → []
- **Single node**: [1] → [[1]]
- **Left-skewed tree**: [[1], [2], [3], ...] (each level has one node)
- **Right-skewed tree**: Similar to left-skewed
- **Complete binary tree**: Last level may have many nodes
- **Perfect binary tree**: Each level has 2^level nodes
- **Only left children**: Produces levels with single elements on left side

## Related Problems
- **107. Binary Tree Level Order Traversal II**: Bottom-up level order
- [**103. Binary Tree Zigzag Level Order Traversal**](../binarytreezigzagtraversal/notes.md): Alternating left-right direction
- **637. Average of Levels in Binary Tree**: Similar BFS, compute averages
- **199. Binary Tree Right Side View**: Get rightmost node of each level
- **515. Find Largest Value in Each Tree Row**: Get maximum of each level

## Tags
`tree` `bfs` `binary-tree` `queue` `medium`
