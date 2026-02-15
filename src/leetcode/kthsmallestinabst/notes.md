# Kth Smallest Element in a BST

## Problem Statement
**LeetCode Problem 230**: Kth Smallest Element in a BST (Medium)

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

**Note**: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

**Follow up**: What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

### Examples
**Example 1:**
```
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
```

**Example 2:**
```
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
```

## Key Insights
1. **BST Property**: In-order traversal visits nodes in sorted order
2. **Kth Smallest**: The kth element in in-order traversal
3. **In-Order Traversal**: Left -> Root -> Right
4. **Optimization**: Can stop traversal after finding kth element
5. **Follow-up**: Augment BST with subtree size for O(h) queries

## Algorithm Steps

### Approach 1: In-Order Traversal with List
```
1. Create empty list to store values
2. Perform in-order traversal:
   a. Recursively traverse left subtree
   b. Add current node's value to list
   c. Recursively traverse right subtree
3. Return list[k-1] (0-indexed)
```

### Approach 2: Optimized In-Order (Early Termination)
```
1. Use counter starting at k
2. Perform in-order traversal:
   a. Traverse left subtree
   b. Decrement counter
   c. If counter == 0, return current value
   d. Traverse right subtree
3. Stop as soon as kth element found
```

### Approach 3: Iterative with Stack
```
1. Use stack for iterative in-order traversal
2. Keep counter for number of nodes visited
3. When counter reaches k, return current node
```

## Complexity Analysis

### Approach 1 (List Collection):
- **Time Complexity**: O(n)
  - Visit all n nodes in worst case
  - Store all values in list
- **Space Complexity**: O(n)
  - List stores all node values
  - Recursion stack: O(h)

### Approach 2 (Early Termination):
- **Time Complexity**: O(h + k)
  - h = height of tree
  - k = kth position
  - Best: O(k) for balanced tree
  - Worst: O(n) for skewed tree
- **Space Complexity**: O(h)
  - Only recursion stack

### Approach 3 (Iterative):
- **Time Complexity**: O(h + k)
- **Space Complexity**: O(h)
  - Stack for traversal

## Visual Representation

### Example: k = 3
```
Tree:
       5
      / \
     3   6
    / \
   2   4
  /
 1

In-Order Traversal Process:
---------------------------
Visit order: 1 -> 2 -> 3 -> 4 -> 5 -> 6
Position:    1st  2nd  3rd  4th  5th  6th
                        ^
                     k=3, return 3

Traversal Steps:
1. Start at 5, go left
2. At 3, go left
3. At 2, go left
4. At 1, visit (1st element)
5. Back to 2, visit (2nd element)
6. Back to 3, visit (3rd element) <- k=3, FOUND!

Tree with visit order:
       5 (5th)
      / \
  (3rd) 3   6 (6th)
    / \
(2nd) 2   4 (4th)
  /
1 (1st)
```

### Example: k = 1
```
Tree:
   3
  / \
 1   4
  \
   2

In-Order Traversal:
1. Start at 3, go left to 1
2. At 1, no left child
3. Visit 1 (1st element) <- k=1, FOUND!

Visit order: 1 -> 2 -> 3 -> 4
Position:    ^
            k=1
```

## Code Walkthrough

### Current Implementation (List-Based)
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public int kthSmallest(TreeNode root, int k) {
    // Collect all values in sorted order
    List<Integer> nums = new ArrayList<>();
    kthSmallestHelper(root, nums);

    // Return kth element (k-1 for 0-indexed)
    return nums.get(k - 1);
}

public void kthSmallestHelper(TreeNode root, List<Integer> nums) {
    // In-order traversal: Left -> Root -> Right

    // Traverse left subtree first
    if (root.left != null) {
        kthSmallestHelper(root.left, nums);
    }

    // Visit current node (add to list)
    nums.add(root.val);

    // Traverse right subtree
    if (root.right != null)
        kthSmallestHelper(root.right, nums);
}
```

### Optimized Implementation (Early Termination)
```java
public int kthSmallest(TreeNode root, int k) {
    int[] result = new int[]{-1};  // Store result
    int[] count = new int[]{k};     // Counter
    kthSmallestOptimized(root, count, result);
    return result[0];
}

private void kthSmallestOptimized(TreeNode root, int[] count, int[] result) {
    if (root == null || count[0] <= 0) {
        return;  // Already found or null node
    }

    // Traverse left subtree
    kthSmallestOptimized(root.left, count, result);

    // Process current node
    count[0]--;
    if (count[0] == 0) {
        result[0] = root.val;
        return;  // Found kth smallest
    }

    // Traverse right subtree
    kthSmallestOptimized(root.right, count, result);
}
```

### Iterative Implementation (Stack-Based)
```java
public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    int count = 0;

    while (current != null || !stack.isEmpty()) {
        // Go to leftmost node
        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        // Process node
        current = stack.pop();
        count++;

        // Check if this is kth element
        if (count == k) {
            return current.val;
        }

        // Move to right subtree
        current = current.right;
    }

    return -1;  // Should never reach here if k is valid
}
```

## Edge Cases
1. **k = 1**: Return minimum element (leftmost node)
2. **k = n**: Return maximum element (rightmost node)
3. **Single node**: Return that node's value
4. **Balanced tree**: Efficient traversal
5. **Skewed tree**: Degrades to O(n)
6. **k invalid**: Problem states k is always valid

## Follow-up: Frequent Queries with Modifications

### Problem
If BST is frequently modified (insert/delete) and we need kth smallest often, how to optimize?

### Solution: Augmented BST
```java
class AugmentedTreeNode {
    int val;
    int leftSubtreeSize;  // Number of nodes in left subtree
    AugmentedTreeNode left;
    AugmentedTreeNode right;
}

public int kthSmallest(AugmentedTreeNode root, int k) {
    if (root == null) return -1;

    int leftSize = root.leftSubtreeSize;

    if (k <= leftSize) {
        // kth smallest is in left subtree
        return kthSmallest(root.left, k);
    } else if (k == leftSize + 1) {
        // Current node is kth smallest
        return root.val;
    } else {
        // kth smallest is in right subtree
        return kthSmallest(root.right, k - leftSize - 1);
    }
}
```

**Complexity with Augmentation**:
- Query: O(h) where h is height
- Insert/Delete: O(h) with subtree size updates
- Space: O(1) extra per node

## Related Problems
- **Kth Largest Element in BST**: Mirror problem
- **Binary Search Tree Iterator (LeetCode 173)**: Similar in-order traversal
- **Validate Binary Search Tree (LeetCode 98)**: BST property validation
- **Inorder Successor in BST (LeetCode 285)**: Next element in in-order
- **Closest Binary Search Tree Value (LeetCode 270)**: BST traversal

## Tags
- Tree
- Binary Search Tree
- In-Order Traversal
- Depth-First Search
- Stack
- Medium
- Amazon Interview
- Google Interview
