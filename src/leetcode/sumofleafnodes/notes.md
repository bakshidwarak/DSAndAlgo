# Sum of Leaf Nodes / Sum of Left Leaves (LeetCode 404)

## Problem Statement
Find the sum of all left leaves in a given binary tree.

A left leaf is a leaf node that is the left child of its parent.

**Note:** A leaf node has no children.

## Examples
```
Example 1:
        3
       / \
      9  20
        /  \
       15   7

Left leaves: 9, 15
Output: 24

Example 2:
      1
     / \
    2   3
   / \
  4   5

Left leaves: 4
Output: 4

Example 3:
        1
       /
      2
     /
    3

Left leaves: 3
Output: 3
```

## Key Insights
1. A left leaf is identified by two conditions:
   - It must be a leaf (no children)
   - It must be the left child of its parent
2. We need to track whether a node is a left child or right child
3. Only when both conditions are met do we add it to the sum
4. We can use a helper function with a boolean flag indicating if the node is a left child

## Algorithm Steps

### Approach: DFS with Left Flag
1. Create a helper function that takes:
   - Current node
   - A boolean flag indicating if the node is a left child
2. Base cases:
   - If node is null, return 0
   - If node is a leaf (no children) AND isLeft is true, return the node's value
3. Recursive case:
   - Sum = helper(left child, true) + helper(right child, false)
   - Return the sum
4. Call helper on root with an unused boolean (we check left and right separately)

## Complexity Analysis
- **Time Complexity:** O(n) - Visit each node once
- **Space Complexity:** O(h) - Recursion stack where h is height of tree

## ASCII Visualization

```
        3 (not a leaf, isLeft=N/A)
       / \
      9  20 (not a leaf, isLeft=false)
    (leaf,   /  \
   isLeft=true) 15  7 (both leaf nodes)
    Return 9   (isLeft=true) (isLeft=false)
              Return 15      Return 0

Tree traversal:
sumOfLeftLeaves(3, N/A)
  -> sumOfLeftLeaves(9, true) + sumOfLeftLeaves(20, false)
  -> sumOfLeftLeaves(9, true):
     - 9 is a leaf and isLeft=true
     - Return 9
  -> sumOfLeftLeaves(20, false):
     - 20 is not a leaf
     - Return sumOfLeftLeaves(15, true) + sumOfLeftLeaves(7, false)
     -> sumOfLeftLeaves(15, true):
        - 15 is a leaf and isLeft=true
        - Return 15
     -> sumOfLeftLeaves(7, false):
        - 7 is a leaf but isLeft=false
        - Return 0
     - Return 15 + 0 = 15
  -> Return 9 + 15 = 24
```

## Code Walkthrough

```java
public int sumOfLeftLeaves(TreeNode root) {
    if (root == null)
        return 0;

    // Process left subtree (its children are left/right)
    // Process right subtree (its children are left/right)
    return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
}

// Helper function with isLeft flag
public int sumOfLeftLeaves(TreeNode root, boolean isLeft) {
    if (root == null)
        return 0;

    // Check if this node is a left leaf
    if (root.left == null && root.right == null && isLeft) {
        return root.val;
    }

    // Recursively process children
    return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
}
```

## Edge Cases
1. Null tree: null → 0
2. Single node: TreeNode(5) → 0 (root is not a left child of anyone)
3. Only left children:
   ```
     1
    /
   2
   ```
   Left leaves: 2, Output: 2

4. Only right children:
   ```
     1
      \
       2
   ```
   Left leaves: none, Output: 0

5. Balanced tree with all leaves:
   ```
       1
      / \
     2   3
   ```
   Left leaves: 2, Output: 2

## Related Problems
- LeetCode 112: Path Sum
- LeetCode 113: Path Sum II
- LeetCode 129: Sum Root to Leaf Numbers
- LeetCode 100: Same Tree
- LeetCode 101: Symmetric Tree

## Tags
- Tree
- DFS
- Recursion
- Traversal
- Binary Tree
