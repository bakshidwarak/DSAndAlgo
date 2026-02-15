# 103. Binary Tree Zigzag Level Order Traversal

## Problem Statement
Given a binary tree, return the zigzag level order traversal of its nodes' values (i.e., from left to right, then right to left for the next level and alternate between).

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
  [20,9],
  [15,7]
]

Explanation:
Level 0: [3] (left to right)
Level 1: [20,9] (right to left - reversed)
Level 2: [15,7] (left to right)
```

### Constraints
- The number of nodes in the tree is in the range [0, 2000]
- -100 <= Node.val <= 100

## Approach & Solution

### Key Insights
1. **BFS with level tracking**: Use queue to traverse tree level by level
2. **Zigzag pattern**: Reverse odd-numbered levels (using 1-based indexing in code)
3. **HashMap for grouping**: Collect nodes by level number
4. **Post-processing**: Reverse even-numbered levels after traversal

### Algorithm Steps
1. Create a queue for BFS and HashMap to store nodes by level
2. Add root to queue with level 1
3. While queue is not empty:
   - Dequeue a (node, level) pair
   - Add node value to the list for that level in HashMap
   - Enqueue left and right children with level+1
4. After traversal, iterate through levels:
   - For even levels (2, 4, 6...), reverse the list
   - Add each level's list to result
5. Return result

### Complexity Analysis
- **Time Complexity**: O(n)
  - BFS traversal: O(n) where n is number of nodes
  - Reversing levels: O(n) total across all levels
  - Overall: O(n)
- **Space Complexity**: O(n)
  - Queue holds up to n/2 nodes at last level
  - HashMap stores all n node values
  - Result list stores all n values
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
Level 1: Queue = [(3,1)]
         levelElements[1] = [3]

Level 2: Queue = [(9,2), (20,2)]
         levelElements[2] = [9, 20]

Level 3: Queue = [(15,3), (7,3)]
         levelElements[3] = [15, 7]

Post-processing (Zigzag):
Level 1 (odd):  [3] → [3] (no change)
Level 2 (even): [9, 20] → [20, 9] (reversed)
Level 3 (odd):  [15, 7] → [15, 7] (no change)

Output: [[3], [20, 9], [15, 7]]

Visual Zigzag Pattern:
    3          →  Level 1: left to right
   / \
  9  20        ←  Level 2: right to left (zigzag)
    /  \
   15   7      →  Level 3: left to right
```

## Code Walkthrough

```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> levels = new ArrayList<>();

    // BFS queue storing (node, level) pairs
    Queue<NodeOrder> queue = new LinkedList<>();

    // HashMap to group nodes by level
    HashMap<Integer, List<Integer>> levelElements = new HashMap<>();

    queue.add(new NodeOrder(root, 1));

    // BFS traversal
    while (!queue.isEmpty()) {
        NodeOrder current = queue.remove();
        int order = current.level;

        // Get or create list for this level
        List<Integer> currentElements = levelElements.get(order);
        if (currentElements == null) {
            currentElements = new ArrayList<>();
        }
        currentElements.add(current.node.val);
        levelElements.put(order, currentElements);

        // Add children to queue
        addNodeIfExistsToQueue(queue, current.node.left, order + 1);
        addNodeIfExistsToQueue(queue, current.node.right, order + 1);
    }

    // Build result with zigzag pattern
    for (int entry : levelElements.keySet()) {
        List<Integer> elements = levelElements.get(entry);

        // Reverse even-numbered levels (using 1-based indexing)
        if (entry % 2 == 0) {
            Collections.reverse(elements);
        }

        levels.add(elements);
    }

    return levels;
}

private void addNodeIfExistsToQueue(Queue<NodeOrder> queue,
                                    TreeNode node, int order) {
    if (node == null)
        return;
    queue.add(new NodeOrder(node, order));
}
```

**Helper Class:**
```java
static class NodeOrder {
    TreeNode node;
    int level;

    public NodeOrder(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }
}
```

**Alternative Approach (Using Deque):**
```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean leftToRight = true;

    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            if (leftToRight) {
                level.add(node.val);
            } else {
                level.add(0, node.val);  // Add at beginning
            }

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        result.add(level);
        leftToRight = !leftToRight;
    }

    return result;
}
```

## Edge Cases
- **Empty tree**: null → []
- **Single node**: [1] → [[1]]
- **Two levels**: [1,2,3] → [[1], [3,2]]
- **Left-skewed tree**: Each level has one node, zigzag has no effect
- **Right-skewed tree**: Similar to left-skewed
- **Complete binary tree**: All levels except last are full
- **Many levels**: Alternates correctly between directions

## Related Problems
- **102. Binary Tree Level Order Traversal**: Regular level order (no zigzag)
- **107. Binary Tree Level Order Traversal II**: Bottom-up level order
- **637. Average of Levels in Binary Tree**: Similar BFS approach
- **199. Binary Tree Right Side View**: Related level-based traversal

## Tags
`tree` `bfs` `binary-tree` `queue` `medium`
