# Invert Binary Tree

## Problem Statement
**LeetCode Problem 226**: Invert Binary Tree (Easy)

Invert a binary tree (flip it horizontally so that left and right children are swapped at every level).

### Examples
**Example 1:**
```
Input:
     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

**Example 2:**
```
Input:
     2
   /   \
  1     3

Output:
     2
   /   \
  3     1
```

## Key Insights
1. **Mirror Image**: The operation creates a mirror image of the tree
2. **Recursive Nature**: Each subtree needs to be inverted
3. **Post-Order Logic**: Invert children first, then swap
4. **Simple Swap**: At each node, swap left and right children
5. **Multiple Approaches**: Can be solved recursively, iteratively, or with BFS/DFS

## Algorithm Steps

### Recursive Approach
```
1. Base case: if node is null, return null
2. Recursively invert left subtree
3. Recursively invert right subtree
4. Swap left and right children
5. Return current node
```

### Iterative Approach (BFS with Queue)
```
1. Create queue and add root
2. While queue is not empty:
   a. Dequeue node
   b. Swap its left and right children
   c. Add left child to queue (if not null)
   d. Add right child to queue (if not null)
3. Return root
```

## Complexity Analysis
- **Time Complexity**: O(n)
  - Visit each node exactly once
  - n = number of nodes in tree
- **Space Complexity**:
  - Recursive: O(h) for call stack (h = height)
  - Iterative: O(w) for queue (w = max width)
  - Worst case: O(n) for both

## Visual Representation

### Example: Detailed Inversion Process
```
Original Tree:
        4
      /   \
     2     7
    / \   / \
   1   3 6   9

Step 1: Process node 4
  - Recursively invert left subtree (2)
  - Recursively invert right subtree (7)
  - Swap left and right

Step 2: Process node 2 (left subtree of 4)
  - Recursively invert left subtree (1)
  - Recursively invert right subtree (3)
  - Swap left and right
  Result:
     2
    / \
   3   1

Step 3: Process node 7 (right subtree of 4)
  - Recursively invert left subtree (6)
  - Recursively invert right subtree (9)
  - Swap left and right
  Result:
     7
    / \
   9   6

Step 4: Swap children of 4
  Original:          After swap:
      4                  4
    /   \              /   \
   2     7            7     2
  / \   / \          / \   / \
 3   1 9   6        9   6 3   1

Final Result:
        4
      /   \
     7     2
    / \   / \
   9   6 3   1
```

### Level-by-Level View
```
Level 0:    4           ->      4
           / \                 / \

Level 1:  2   7         ->    7   2
         / \ / \             / \ / \

Level 2: 1 3 6 9        ->  9 6 3 1

The tree is flipped horizontally at each level
```

## Code Walkthrough

### Recursive Solution
```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public TreeNode invertTree(TreeNode root) {
    // Base case: empty tree or leaf node
    if (root == null) {
        return null;
    }

    // Recursively invert left and right subtrees
    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);

    // Swap the left and right children
    root.left = right;
    root.right = left;

    return root;
}
```

### Iterative Solution (BFS)
```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;

    // Use queue for level-order traversal
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        TreeNode current = queue.poll();

        // Swap left and right children
        TreeNode temp = current.left;
        current.left = current.right;
        current.right = temp;

        // Add children to queue for processing
        if (current.left != null) {
            queue.offer(current.left);
        }
        if (current.right != null) {
            queue.offer(current.right);
        }
    }

    return root;
}
```

### Iterative Solution (DFS with Stack)
```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();

        // Swap children
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        // Push children to stack
        if (node.left != null) stack.push(node.left);
        if (node.right != null) stack.push(node.right);
    }

    return root;
}
```

## Edge Cases
1. **Null tree**: Return null
2. **Single node**: Return same node (no children to swap)
3. **Only left children**: Becomes only right children
4. **Only right children**: Becomes only left children
5. **Complete binary tree**: All levels fully swapped
6. **Skewed tree**: Becomes opposite skew

### Edge Case Examples
```
1. Empty tree:
   Input: null
   Output: null

2. Single node:
   Input: [1]
   Output: [1]

3. Left-skewed:
   Input:    3        Output:    3
            /                     \
           2                       2
          /                         \
         1                           1

4. Complete tree:
   Input:    1        Output:    1
           /   \               /   \
          2     3             3     2
```

## Famous Story
This problem became famous when Max Howell (creator of Homebrew) tweeted:
> "Google: 90% of our engineers use the software you wrote (Homebrew), but you can't invert a binary tree on a whiteboard so f*** off."

This sparked debate about interview practices in tech companies.

## Related Problems
- **Symmetric Tree (LeetCode 101)**: Check if tree is mirror of itself
- **Maximum Depth of Binary Tree (LeetCode 104)**: Tree traversal practice
- **Same Tree (LeetCode 100)**: Compare two trees
- **Binary Tree Level Order Traversal (LeetCode 102)**: Similar BFS approach
- **Mirror Reflection (LeetCode 858)**: Different mirror concept

## Tags
- Tree
- Binary Tree
- Depth-First Search
- Breadth-First Search
- Recursion
- Easy
- Interview Classic
- Google
