# LeetCode 104: Maximum Depth of Binary Tree

## Problem Statement

Given the `root` of a binary tree, return its **maximum depth**.

A binary tree's **maximum depth** is the number of nodes along the longest path from the root node down to the farthest leaf node.

### Examples

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: 3

Tree structure:
      3
     / \
    9  20
      /  \
     15   7
```

**Example 2:**
```
Input: root = [1,null,2]
Output: 2

Tree structure:
1
 \
  2
```

**Example 3:**
```
Input: root = []
Output: 0
```

**Example 4:**
```
Input: root = [0]
Output: 1
```

**Constraints:**
- The number of nodes in the tree is in the range [0, 10^4]
- -100 <= Node.val <= 100

## Key Insights

1. **Recursive Nature**: Tree depth is naturally solved using recursion
2. **Subproblem**: Depth of tree = 1 + max(depth of left subtree, depth of right subtree)
3. **Base Case**: Null node has depth 0
4. **Post-Order Traversal**: Need to compute children's depths before parent's depth
5. **Simple Problem**: One of the most straightforward tree problems

## Algorithm Steps

1. **Base Case**: If root is null, return 0
2. **Recursive Calls**:
   - Calculate depth of left subtree: maxDepth(root.left)
   - Calculate depth of right subtree: maxDepth(root.right)
3. **Combine Results**:
   - Take maximum of left and right depths
   - Add 1 for current node
4. **Return** the total depth

## Complexity Analysis

- **Time Complexity**: O(n)
  - Must visit every node once
  - Each node processed in constant time
  - n = number of nodes
  - Overall: O(n)

- **Space Complexity**: O(h)
  - Recursion call stack depth
  - h = height of tree
  - Best case (balanced): O(log n)
  - Worst case (skewed): O(n)
  - Overall: O(h)

## Visual Explanation

### Example 1: Balanced Tree

```
Tree:
        3
       / \
      9  20
        /  \
       15   7

Step-by-step calculation:

Level 4: Leaf nodes return base case
  15 -> maxDepth(null) = 0 for both children
        return 1 + max(0, 0) = 1

  7  -> maxDepth(null) = 0 for both children
        return 1 + max(0, 0) = 1

Level 3: Node 9 (leaf)
  9  -> maxDepth(null) = 0 for both children
        return 1 + max(0, 0) = 1

Level 3: Node 20
  20 -> left: maxDepth(15) = 1
        right: maxDepth(7) = 1
        return 1 + max(1, 1) = 2

Level 2: Root 3
  3  -> left: maxDepth(9) = 1
        right: maxDepth(20) = 2
        return 1 + max(1, 2) = 3

Final answer: 3
```

### Example 2: Skewed Tree

```
Tree:
1
 \
  2
   \
    3
     \
      4

Calculation:
maxDepth(1):
  left = 0
  right = maxDepth(2):
    left = 0
    right = maxDepth(3):
      left = 0
      right = maxDepth(4):
        left = 0
        right = 0
        return 1 + max(0, 0) = 1
      return 1 + max(0, 1) = 2
    return 1 + max(0, 2) = 3
  return 1 + max(0, 3) = 4

Answer: 4
```

### Example 3: Small Tree

```
Tree:
    1
   / \
  2   3

maxDepth(1):
  left = maxDepth(2):
    left = 0
    right = 0
    return 1 + max(0, 0) = 1

  right = maxDepth(3):
    left = 0
    right = 0
    return 1 + max(0, 0) = 1

  return 1 + max(1, 1) = 2

Answer: 2
```

## Code Walkthrough

```java
public static int maxDepth(TreeNode root) {
    // Base case: null node has depth 0
    if (root == null)
        return 0;

    // Recursive case:
    // 1. Calculate depth of left subtree
    int leftDepth = maxDepth(root.left);

    // 2. Calculate depth of right subtree
    int rightDepth = maxDepth(root.right);

    // 3. Current depth = 1 (current node) + max of children depths
    return 1 + Math.max(leftDepth, rightDepth);
}

// Concise version (same logic):
public static int maxDepth(TreeNode root) {
    if (root == null)
        return 0;

    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

## Alternative Approaches

### 1. Iterative BFS (Level-Order Traversal)

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int depth = 0;

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        depth++;

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    return depth;
}
```

### 2. Iterative DFS with Stack

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
    stack.push(new Pair<>(root, 1));
    int maxDepth = 0;

    while (!stack.isEmpty()) {
        Pair<TreeNode, Integer> current = stack.pop();
        TreeNode node = current.getKey();
        int depth = current.getValue();

        maxDepth = Math.max(maxDepth, depth);

        if (node.right != null) stack.push(new Pair<>(node.right, depth + 1));
        if (node.left != null) stack.push(new Pair<>(node.left, depth + 1));
    }

    return maxDepth;
}
```

## Recursion Call Stack Visualization

```
For tree:    3
            / \
           9  20
             /  \
            15   7

Call Stack:
┌─────────────────────┐
│ maxDepth(3)         │ <- Initial call
│  ├─ maxDepth(9)     │ <- Recurse left
│  │   ├─ maxDepth(null) = 0
│  │   └─ maxDepth(null) = 0
│  │   return 1
│  └─ maxDepth(20)    │ <- Recurse right
│      ├─ maxDepth(15)│
│      │   ├─ maxDepth(null) = 0
│      │   └─ maxDepth(null) = 0
│      │   return 1
│      └─ maxDepth(7) │
│          ├─ maxDepth(null) = 0
│          └─ maxDepth(null) = 0
│          return 1
│      return 2
│  return 3
└─────────────────────┘
```

## Edge Cases

1. **Empty Tree**: root = null
   - Output: 0

2. **Single Node**: root = [1]
   - Output: 1

3. **Only Left Children**:
   ```
   1
    \
     2
      \
       3
   ```
   - Output: 3

4. **Only Right Children**: Mirror of above
   - Output: 3

5. **Perfect Binary Tree**: All levels completely filled
   ```
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
   ```
   - Output: 3

6. **One Deep Path**:
   ```
       1
      / \
     2   3
    /
   4
  /
 5
   ```
   - Output: 4 (follows left path)

## Related Problems

1. **LeetCode 111**: Minimum Depth of Binary Tree
2. **LeetCode 110**: Balanced Binary Tree (uses depth calculation)
3. **LeetCode 559**: Maximum Depth of N-ary Tree
4. **LeetCode 1376**: Time Needed to Inform All Employees
5. **LeetCode 366**: Find Leaves of Binary Tree
6. **LeetCode 543**: Diameter of Binary Tree

## Tags

- Tree
- Binary Tree
- Depth-First Search (DFS)
- Recursion
- Tree Traversal
- Easy
