# Trim a Binary Search Tree (LeetCode 669)

## Problem Statement
Given the root of a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lie in [L, R].

You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

## Examples
```
Example 1:
Input: root = [1,0,2], L = 1, R = 2
Output: [1,null,2]

        1              1
       / \    --->      \
      0   2              2

Example 2:
Input: root = [3,0,4,null,2,null,null,1], L = 1, R = 3
Output: [3,2,null,1]

Original:
        3
       / \
      0   4
       \
        2
       /
      1

After trim (keep [1,3]):
      3
     /
    2
   /
  1

Example 3:
Input: root = [1,null,3], L = 1, R = 3
Output: [1,null,3]

No trimming needed, all elements already in [1,3]
```

## Key Insights
1. Use BST property: left subtree has smaller values, right subtree has larger values
2. If node value < L: entire left subtree is out of range, return trimmed right subtree
3. If node value > R: entire right subtree is out of range, return trimmed left subtree
4. If node value in [L, R]: trim both subtrees recursively
5. The root can change if original root is outside the range

## Algorithm Steps

### Approach: Recursive Trimming Using BST Property
1. Base case: If node is null, return null
2. If node.val > R: Node and entire right subtree are out of range
   - Return trimBST(node.left, L, R) to get from left subtree
3. If node.val < L: Node and entire left subtree are out of range
   - Return trimBST(node.right, L, R) to get from right subtree
4. If node.val in [L, R]: Keep this node, trim both subtrees
   - node.left = trimBST(node.left, L, R)
   - node.right = trimBST(node.right, L, R)
   - Return node
5. This ensures result contains only nodes with values in [L, R]

## Complexity Analysis
- **Time Complexity:** O(n) - Visit each node at most once
- **Space Complexity:** O(h) - Recursion depth where h is height

## ASCII Visualization

```
Original tree:
        3
       / \
      0   4
       \
        2
       /
      1

L = 1, R = 3

trimBST(3, 1, 3):
  3 is in [1,3]
  node.left = trimBST(0, 1, 3)
  node.right = trimBST(4, 1, 3)

trimBST(0, 1, 3):
  0 < 1 (too small)
  Return trimBST(0.right=null, 1, 3) = null

trimBST(4, 1, 3):
  4 > 3 (too large)
  Return trimBST(4.left=null, 1, 3) = null

So after:
node.left = null
node.right = null
Return 3

Wait, that's wrong. The node 0 has a right child 2.

Let me redo:

trimBST(3, 1, 3):
  3 is in [1,3]
  node.left = trimBST(0, 1, 3)
    0 < 1
    Return trimBST(right=2, 1, 3)
      trimBST(2, 1, 3):
        2 is in [1,3]
        node.left = trimBST(1, 1, 3)
          1 is in [1,3]
          node.left = trimBST(null, 1, 3) = null
          node.right = trimBST(null, 1, 3) = null
          Return 1
        node.right = trimBST(null, 1, 3) = null
        Return 2
    Return 2
  node.left = 2

  node.right = trimBST(4, 1, 3)
    4 > 3
    Return trimBST(left=null, 1, 3) = null
  node.right = null

Result:
      3
     /
    2
   /
  1
```

## Code Walkthrough

```java
public TreeNode trimBST(TreeNode root, int L, int R) {
    if (root == null)
        return root;

    // Node is in range [L, R]
    if (root.val >= L && root.val <= R) {
        // Trim both subtrees
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
    }
    // Node is less than L, entire left subtree is too small
    else if (root.val < L) {
        // Only consider right subtree
        return trimBST(root.right, L, R);
    }
    // Node is greater than R, entire right subtree is too large
    else if (root.val > R) {
        // Only consider left subtree
        return trimBST(root.left, L, R);
    }

    return root;
}
```

## Edge Cases
1. Entire tree in range: No trimming needed
2. Entire tree out of range: Return null
3. Root is out of range: Root changes to child or descendant
4. All nodes on one side: Trim entire left or right subtree
5. Single node in range: Return that node
6. Single node out of range: Return null

Examples:
```
1. Tree [1,2,3], L=1, R=3
   All in range, return [1,2,3]

2. Tree [1,2,3], L=2, R=3
   1 < 2, return trimBST(2, 2, 3)
   Result: [2,null,3]

3. Tree [1,2,3], L=4, R=5
   1 < 4, return trimBST(2, 4, 5)
   2 < 4, return trimBST(3, 4, 5)
   3 < 4, return trimBST(null, 4, 5)
   Result: null

4. Tree [3,2,4,1], L=3, R=4
   3 in [3,4], trim children
   Left: trimBST(2, 3, 4) -> 2 < 3, return trimBST(1, 3, 4) -> null
   Right: trimBST(4, 3, 4) -> 4 in range, return 4
   Result: [3,null,4]
```

## Visual Trimming Process

```
Original:
      3
     / \
    2   4
   /
  1

L=3, R=4:

Step 1: trimBST(3, 3, 4)
  3 in [3,4], keep it
  left = trimBST(2, 3, 4)

Step 2: trimBST(2, 3, 4)
  2 < 3, return trimBST(1, 3, 4)

Step 3: trimBST(1, 3, 4)
  1 < 3, return trimBST(null, 3, 4) = null

Back to Step 2: return null
Back to Step 1:
  node.left = null
  node.right = trimBST(4, 3, 4)

Step 4: trimBST(4, 3, 4)
  4 in [3,4], keep it
  return 4

Back to Step 1:
  node.right = 4
  return 3

Result:
      3
       \
        4
```

## Related Problems
- LeetCode 98: Validate Binary Search Tree
- LeetCode 235: Lowest Common Ancestor of a BST
- LeetCode 450: Delete Node in a BST
- LeetCode 108: Convert Sorted Array to BST

## Tags
- Tree
- Binary Search Tree
- Recursion
- DFS
- BST Property
