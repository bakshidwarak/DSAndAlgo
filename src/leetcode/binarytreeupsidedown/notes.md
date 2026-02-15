# 156. Binary Tree Upside Down

## Problem Statement
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

### Examples
```
Input:
     1
    / \
   2   3
  / \
 4   5

Output:
     4
    / \
   5   2
      / \
     3   1

Explanation:
The transformation flips the tree upside down.
Original root (1) becomes rightmost leaf.
Original leftmost leaf (4) becomes new root.
```

### Constraints
- The tree is left-leaning (all right nodes are leaves or empty)
- Each node has at most one right child which is a leaf

## Approach & Solution

### Key Insights
1. **No implementation**: The provided file is empty (just class declaration)
2. **Expected approach**: Recursively transform the tree
3. **Pattern**: Original left child becomes new root, right child becomes left child of original parent
4. **Rotation**: Each level rotates counterclockwise

### Algorithm Steps
(Based on typical solution approach)
1. Base case: if node is null or leaf, return node
2. Recursively flip the left subtree
3. The leftmost node becomes the new root
4. Current node's left child's left = current node's right child
5. Current node's left child's right = current node
6. Set current node's left and right to null (it becomes a leaf)
7. Return the new root

### Complexity Analysis
- **Time Complexity**: O(n)
  - Visit each node once
  - Where n is number of nodes
- **Space Complexity**: O(h)
  - Recursion stack depth
  - Where h is tree height
  - For left-leaning tree, h ≈ n, so O(n)

### Visualization
```
Original Tree:
     1
    / \
   2   3
  / \
 4   5

Step 1: Reach leftmost node (4)
        4 becomes new root

Step 2: Process node 2
        2's left (4) becomes root
        2's right (5) becomes left child of 2
        2 becomes right child of 4

     4
      \
       2
      /
     5

Step 3: Process node 1
        1's left is 2 (already processed)
        1's right (3) becomes left child of 1
        1 becomes right child of 2

Final:
     4
    / \
   5   2
      / \
     3   1

Transformation Pattern:
Original: parent -> left child, right child
Becomes:  left child -> right child, parent
```

## Code Walkthrough

Since the provided implementation is empty, here's the typical solution:

```java
public TreeNode upsideDownBinaryTree(TreeNode root) {
    // Base case: empty tree or leaf node
    if (root == null || (root.left == null && root.right == null)) {
        return root;
    }

    // Recursively flip the left subtree
    TreeNode newRoot = upsideDownBinaryTree(root.left);

    // Transform current node
    // root.left becomes the parent of root
    root.left.left = root.right;  // Original right becomes left child
    root.left.right = root;        // Original parent becomes right child

    // Current node becomes a leaf
    root.left = null;
    root.right = null;

    return newRoot;
}
```

**Iterative Approach:**
```java
public TreeNode upsideDownBinaryTree(TreeNode root) {
    TreeNode curr = root;
    TreeNode next = null;
    TreeNode temp = null;
    TreeNode prev = null;

    while (curr != null) {
        next = curr.left;

        // Swap
        curr.left = temp;
        temp = curr.right;
        curr.right = prev;

        prev = curr;
        curr = next;
    }

    return prev;
}
```

## Edge Cases
- **Empty tree**: null → null
- **Single node**: [1] → [1]
- **Two nodes**: [1,2] → [2,null,1]
- **Left-leaning only**: Works as expected
- **Has right leaves**: Transforms correctly

## Related Problems
- **206. Reverse Linked List**: Similar pointer manipulation
- **226. Invert Binary Tree**: Different tree transformation
- [**114. Flatten Binary Tree to Linked List**](../flattenbinarytreetolinkedlist/notes.md): Tree restructuring

## Tags
`tree` `recursion` `medium`

## Note
The implementation file is currently empty and needs to be completed.
