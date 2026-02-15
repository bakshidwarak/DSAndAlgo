# 110. Balanced Binary Tree

## Problem Statement
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as: a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

### Examples
```
Example 1:
Input: [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
Output: true

Example 2:
Input: [1,2,2,3,3,null,null,4,4]
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Output: false
Explanation: The left subtree has depth 3, right has depth 0, difference > 1
```

### Constraints
- The number of nodes in the tree is in the range [0, 5000]
- -10^4 <= Node.val <= 10^4

## Approach & Solution

### Key Insights
1. **Recursive definition**: A tree is balanced if both subtrees are balanced AND the height difference is at most 1
2. **Depth calculation**: Need helper function to compute tree depth/height
3. **Bottom-up check**: Check balance condition at every node
4. **Height difference**: |depth(left) - depth(right)| <= 1

### Algorithm Steps
1. For each node, check if it's balanced:
   - Calculate depth of left subtree
   - Calculate depth of right subtree
   - Check if |leftDepth - rightDepth| <= 1
2. Recursively check if left subtree is balanced
3. Recursively check if right subtree is balanced
4. A node is balanced if: difference <= 1 AND left is balanced AND right is balanced
5. Helper function `depth()` computes height: 1 + max(depth(left), depth(right))

### Complexity Analysis
- **Time Complexity**: O(n log n) to O(n²)
  - For each node (n nodes), we calculate depth
  - Depth calculation visits all nodes in subtree
  - Balanced tree: O(n log n) - each level has half the nodes
  - Skewed tree: O(n²) - depth calculation O(n) at each of n nodes
- **Space Complexity**: O(h)
  - Where h is the height of the tree
  - Recursion stack depth equals tree height
  - Best case (balanced): O(log n)
  - Worst case (skewed): O(n)

### Visualization
```
Example 1: Balanced Tree
       3
      / \
     9  20
       /  \
      15   7

Check node 3:
  depth(9) = 1
  depth(20) = 2
  |1 - 2| = 1 ✓ (≤ 1)
  isBalanced(9) = true
  isBalanced(20) = true
  Result: true

Example 2: Unbalanced Tree
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4

Check node 1:
  depth(left) = 3 (path 1→2→3→4)
  depth(right) = 1 (path 1→2)
  |3 - 1| = 2 ✗ (> 1)
  Result: false

Depth Calculation Example:
       3
      / \
     9  20
       /  \
      15   7

depth(3) = 1 + max(depth(9), depth(20))
         = 1 + max(1, 2)
         = 3

depth(9) = 1 + max(null, null)
         = 1 + max(0, 0)
         = 1

depth(20) = 1 + max(depth(15), depth(7))
          = 1 + max(1, 1)
          = 2
```

## Code Walkthrough

```java
public boolean isBalanced(TreeNode root) {
    // Empty tree is balanced
    if (root == null)
        return true;

    // Check three conditions:
    // 1. Height difference <= 1
    // 2. Left subtree is balanced
    // 3. Right subtree is balanced
    if (Math.abs(depth(root.left) - depth(root.right)) <= 1) {
        return isBalanced(root.left) && isBalanced(root.right);
    }

    return false;
}

public int depth(TreeNode root) {
    // Base case: empty tree has depth 0
    if (root == null)
        return 0;

    // Depth = 1 (current node) + max depth of subtrees
    return 1 + Math.max(depth(root.left), depth(root.right));
}
```

**Optimization Note:**
The current implementation recalculates depth multiple times. A more efficient O(n) approach would compute depth and check balance in a single pass:

```java
// Optimized: Return -1 for unbalanced, otherwise return height
public int checkBalance(TreeNode root) {
    if (root == null) return 0;

    int left = checkBalance(root.left);
    if (left == -1) return -1;  // Left subtree unbalanced

    int right = checkBalance(root.right);
    if (right == -1) return -1;  // Right subtree unbalanced

    if (Math.abs(left - right) > 1) return -1;  // Current node unbalanced

    return 1 + Math.max(left, right);  // Return height
}
```

## Edge Cases
- **Empty tree**: null → true (empty tree is balanced)
- **Single node**: [1] → true (height 1, no subtrees)
- **Complete binary tree**: Always balanced
- **Left-skewed tree**: [1,2,null,3] → false (unbalanced)
- **Right-skewed tree**: [1,null,2,null,3] → false (unbalanced)
- **Perfectly balanced**: All levels filled → true
- **Almost balanced**: Difference exactly 1 → true

## Related Problems
- **104. Maximum Depth of Binary Tree**: Uses same depth calculation
- **111. Minimum Depth of Binary Tree**: Similar recursive structure
- **543. Diameter of Binary Tree**: Also computes depths at each node
- **124. Binary Tree Maximum Path Sum**: Similar bottom-up approach

## Tags
`tree` `dfs` `binary-tree` `recursion` `easy`
