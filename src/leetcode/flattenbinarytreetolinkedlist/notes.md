# LeetCode 114: Flatten Binary Tree to Linked List

## Problem Statement

Given the `root` of a binary tree, flatten the tree into a "linked list":
- The "linked list" should use the same `TreeNode` class where the `right` child pointer points to the next node in the list and the `left` child pointer is always `null`
- The "linked list" should be in the same order as a **pre-order traversal** of the binary tree

### Examples

**Example 1:**
```
Input: root = [1,2,5,3,4,null,6]

Tree:
    1
   / \
  2   5
 / \   \
3   4   6

Output: [1,null,2,null,3,null,4,null,5,null,6]

Flattened:
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

**Example 2:**
```
Input: root = []
Output: []
```

**Example 3:**
```
Input: root = [0]
Output: [0]
```

**Constraints:**
- The number of nodes in the tree is in the range [0, 2000]
- -100 <= Node.val <= 100

**Follow up:** Can you flatten the tree in-place (with O(1) extra space)?

## Key Insights

1. **Pre-order Traversal**: The flattened list follows pre-order: root -> left -> right
2. **In-place Modification**: Must modify tree structure, not create new nodes
3. **Right Pointer Chain**: All nodes connected via right pointers
4. **Left Pointers Null**: All left pointers must be set to null
5. **Two Approaches**: Collect nodes first vs. flatten in-place

## Algorithm Steps

### Approach 1: Pre-order Traversal + Rebuild

1. **Collect Nodes**:
   - Perform pre-order traversal
   - Store all nodes in a list

2. **Rebuild Links**:
   - Iterate through node list
   - Set each node's right to next node
   - Set each node's left to null

3. **Return** modified root

### Approach 2: In-place (O(1) Space)

1. **For each node**:
   - If left subtree exists:
     - Find rightmost node in left subtree
     - Connect it to right subtree
     - Move left subtree to right
     - Set left to null
   - Move to next node (current.right)

## Complexity Analysis

### Approach 1: Pre-order + Rebuild
- **Time Complexity**: O(n)
  - Pre-order traversal: O(n)
  - Rebuild: O(n)
  - Overall: O(n)

- **Space Complexity**: O(n)
  - Store all nodes in list: O(n)
  - Recursion stack: O(h)
  - Overall: O(n)

### Approach 2: In-place
- **Time Complexity**: O(n)
  - Visit each node once
  - Finding rightmost takes O(h) per node
  - Amortized O(n) total

- **Space Complexity**: O(1)
  - Only pointer manipulation
  - No extra data structures

## Visual Explanation

### Example: Flattening Process

```
Original tree:
        1
       / \
      2   5
     / \   \
    3   4   6

Pre-order: 1, 2, 3, 4, 5, 6

Step 1: Collect nodes in pre-order
List: [1, 2, 3, 4, 5, 6]

Step 2: Link nodes
1 -> 2 -> 3 -> 4 -> 5 -> 6

Result:
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

### In-place Flattening Visualization

```
Step 1: Start at root (1)
        1
       / \
      2   5
     / \   \
    3   4   6

    Left subtree exists (2)
    Find rightmost of left subtree: node 4

Step 2: Connect rightmost (4) to right subtree (5)
        1
       /
      2
     / \
    3   4
         \
          5
           \
            6

Step 3: Move left subtree to right, clear left
        1
         \
          2
         / \
        3   4
             \
              5
               \
                6

Step 4: Process node 2 (has left child 3)
        1
         \
          2
           \
            3
             \
              4
               \
                5
                 \
                  6

Continue until all nodes processed...

Final:
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

### Detailed In-place Steps

```
Current = 1:
  Left exists (2)
  Find rightmost in left: 4
  Connect 4.right = 5
  Move 2 to 1.right
  Set 1.left = null

Current = 2:
  Left exists (3)
  Find rightmost in left: 3
  Connect 3.right = 4
  Move 3 to 2.right
  Set 2.left = null

Current = 3:
  No left child
  Move to 3.right (4)

Current = 4:
  No left child
  Move to 4.right (5)

Current = 5:
  No left child
  Move to 5.right (6)

Current = 6:
  No left child
  Done
```

## Code Walkthrough

### Current Implementation (Pre-order + Rebuild)

```java
public void flatten(TreeNode root) {
    // List to store nodes in pre-order
    List<TreeNode> nodes = new ArrayList<>();

    if (root == null)
        return;

    // Step 1: Collect all nodes in pre-order
    helper(root, nodes);

    // Step 2: Link nodes together
    TreeNode prev = null;

    for (TreeNode node : nodes) {
        if (prev != null) {
            // Connect previous node to current
            prev.right = node;
            prev.left = null;
        }
        prev = node;
    }
}

// Pre-order traversal helper
public void helper(TreeNode root, List<TreeNode> nodes) {
    if (root == null)
        return;

    // Pre-order: root -> left -> right
    nodes.add(root);
    helper(root.left, nodes);
    helper(root.right, nodes);
}
```

### In-place Solution (O(1) Space)

```java
public void flatten(TreeNode root) {
    TreeNode current = root;

    while (current != null) {
        // If left subtree exists
        if (current.left != null) {
            // Find rightmost node in left subtree
            TreeNode rightmost = current.left;
            while (rightmost.right != null) {
                rightmost = rightmost.right;
            }

            // Connect rightmost to right subtree
            rightmost.right = current.right;

            // Move left subtree to right
            current.right = current.left;
            current.left = null;
        }

        // Move to next node
        current = current.right;
    }
}
```

### Recursive In-place Solution

```java
private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null) return;

    // Reverse post-order: right -> left -> root
    flatten(root.right);
    flatten(root.left);

    // Set current node's right to previous
    root.right = prev;
    root.left = null;

    // Update prev to current node
    prev = root;
}
```

## Why Pre-order Matters

```
Tree:
    1
   / \
  2   3
 /
4

Pre-order: 1 -> 2 -> 4 -> 3
In-order: 4 -> 2 -> 1 -> 3
Post-order: 4 -> 2 -> 3 -> 1

Flattened list must follow pre-order:
1 -> 2 -> 4 -> 3
```

## Edge Cases

1. **Empty Tree**: root = null
   - Output: null

2. **Single Node**: root = [1]
   - Output: [1]
   - Already flattened

3. **Only Left Children**:
   ```
   1
    \
     2
      \
       3
   ```
   - Pre-order: 1, 2, 3
   - Already in correct form

4. **Only Right Children**:
   ```
   1
  /
 2
/
3
   ```
   - Pre-order: 1, 2, 3
   - Need to move to right

5. **Complete Binary Tree**:
   ```
       1
      / \
     2   3
    / \ / \
   4  5 6  7
   ```
   - Pre-order: 1,2,4,5,3,6,7

6. **Skewed Tree**: Already maximally skewed
   - No changes needed if right-skewed

## Follow-up: O(1) Space Solution

The in-place solution achieves O(1) extra space:
- No list to store nodes
- No recursion stack (iterative)
- Only pointer manipulation

Key technique:
1. Find rightmost of left subtree
2. Connect to right subtree
3. Move left to right
4. Clear left pointer

## Related Problems

1. **LeetCode 430**: Flatten a Multilevel Doubly Linked List
2. **LeetCode 426**: Convert Binary Search Tree to Sorted Doubly Linked List
3. **LeetCode 897**: Increasing Order Search Tree
4. **LeetCode 116**: Populating Next Right Pointers in Each Node
5. **LeetCode 117**: Populating Next Right Pointers in Each Node II

## Tags

- Tree
- Binary Tree
- Linked List
- Depth-First Search (DFS)
- Pre-order Traversal
- In-place Algorithm
- Tree Transformation
