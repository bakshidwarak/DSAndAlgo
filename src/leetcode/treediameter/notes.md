# Tree Diameter (LeetCode 543)

## Problem Statement
Given a binary tree, you need to compute the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

**Note:** The length of path between two nodes is represented by the number of edges between them.

## Examples
```
Example 1:
        1
       / \
      2   3
     / \
    4   5

Diameter: [4,2,1,3] or [5,2,1,3]
Length: 3
(Number of edges: 4-2, 2-1, 1-3 = 3 edges)

Example 2:
      1
     /
    2

Diameter: [2,1]
Length: 1

Example 3:
        1
       / \
      2   2
     / \ / \
    3  4 4  3

Diameter: [3,2,1,2,3]
Length: 4
```

## Key Insights
1. Diameter could pass through root or entirely in left/right subtree
2. The diameter is either:
   - The longest path passing through current node's root
   - The diameter of left subtree
   - The diameter of right subtree
3. Path through root = depth of left subtree + depth of right subtree
4. We need to compute depth and diameter for each node
5. The answer is the maximum of all these values

## Algorithm Steps

### Approach: DFS with Depth Tracking
1. For each node, compute:
   - Diameter of left subtree
   - Diameter of right subtree
   - Path through this node (left depth + right depth)
2. Return maximum of these three values
3. Use helper function to get the depth of a node

**Note:** The provided code has O(n²) complexity. A better approach tracks diameter in a single DFS.

## Complexity Analysis
- **Time Complexity:** O(n²) - For each node, we recalculate depth of subtrees
- **Space Complexity:** O(h) - Recursion stack where h is height

## ASCII Visualization

```
        1
       / \
      2   3
     / \

Computing diameter at node 1:
  diameter(1) = max(
    diameter(2),
    diameter(3),
    depth(2) + depth(3)
  )

  diameter(2) = max(
    diameter(4),
    diameter(5),
    depth(4) + depth(5)
  )

  diameter(3) = max(
    diameter(null),
    diameter(null),
    depth(null) + depth(null)
  ) = 0

Let's calculate depths:
  depth(4) = 1 (leaf)
  depth(5) = 1 (leaf)
  depth(null) = 0
  depth(3) = 1 (leaf)
  depth(2) = 2 (1 + max(depth(4), depth(5)) = 1 + 1)
  depth(1) = 3 (1 + max(depth(2), depth(3)) = 1 + 2)

Now compute diameters:
  diameter(4) = 0 (no children)
  diameter(5) = 0 (no children)
  diameter(3) = 0 (no children)

  diameter(2) = max(
    0,  // diameter(4)
    0,  // diameter(5)
    1 + 1  // depth(4) + depth(5)
  ) = 2

  diameter(1) = max(
    2,  // diameter(2)
    0,  // diameter(3)
    2 + 1  // depth(2) + depth(3)
  ) = 3

Answer: 3
```

## Code Walkthrough

```java
public int diameterOfBinaryTree(TreeNode root) {
    // Get depth at this node
    int mydepth = getDepth(root);

    // Get diameter of left subtree
    int ldia = diameterOfBinaryTree(root.left);

    // Get diameter of right subtree
    int rdia = diameterOfBinaryTree(root.right);

    // Diameter is max of: left diameter, right diameter, or path through root
    return Math.max(mydepth, ldia + rdia);
}

public int getDepth(TreeNode root) {
    if (root == null)
        return 0;

    // Depth is 1 + max depth of children
    int maxDepth = Math.max(getDepth(root.left), getDepth(root.right));
    return maxDepth + 1;
}
```

## Optimal Approach (O(n) Single Pass)

```java
private int maxDiameter = 0;

public int diameterOfBinaryTree(TreeNode root) {
    maxDiameter = 0;
    depth(root);
    return maxDiameter;
}

private int depth(TreeNode node) {
    if (node == null)
        return 0;

    int leftDepth = depth(node.left);
    int rightDepth = depth(node.right);

    // Update maximum diameter
    maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

    // Return depth of current subtree
    return Math.max(leftDepth, rightDepth) + 1;
}
```

## Edge Cases
1. Single node: root = [1] → 0 (no edges)
2. Two nodes: root = [1,2] → 1 (one edge)
3. Skewed tree (all left):
   ```
     1
    /
   2
   /
  3
   ```
   Diameter: 2

4. Skewed tree (all right): Same as above
5. Balanced tree with diameter not through root
6. Very deep tree: May cause stack overflow with recursive approach

## Key Points
- Diameter doesn't have to pass through root
- It's the longest path between ANY two nodes
- Count edges, not nodes
- Path can be in left subtree only, right subtree only, or pass through root

## Related Problems
- LeetCode 104: Maximum Depth of Binary Tree
- LeetCode 110: Balanced Binary Tree
- LeetCode 100: Same Tree
- LeetCode 101: Symmetric Tree
- LeetCode 1522: Diameter of N-ary Tree

## Tags
- Tree
- DFS
- Recursion
- Depth-First Search
