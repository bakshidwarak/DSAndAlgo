# 270. Closest Binary Search Tree Value

## Problem Statement
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

### Examples
```
Example 1:
Input: root = [4,2,5,1,3], target = 3.714286
    4
   / \
  2   5
 / \
1   3

Output: 4
Explanation: Both 3 and 4 are close, but 4 is slightly closer

Example 2:
Input: root = [1], target = 4.428571
Output: 1
```

### Constraints
- The given tree node value and target are floating point
- You are guaranteed to have only one unique value in the BST that is closest to the target
- BST property: left subtree values < node value < right subtree values

## Approach & Solution

### Key Insights
1. **BST property**: Can use binary search to efficiently navigate
2. **Comparison**: Track current closest value while traversing
3. **Decision rule**: If target < node.val, go left; otherwise go right
4. **Update closest**: Compare absolute differences to find closer value

### Algorithm Steps
(The provided implementation file is empty - typical approach below)
1. Initialize closest value to root's value
2. Start from root and traverse:
   - Update closest if current node is closer to target
   - If target < node.val, go left (smaller values)
   - If target > node.val, go right (larger values)
   - If target == node.val, return node.val (exact match)
3. Return closest value found

### Complexity Analysis
- **Time Complexity**: O(h)
  - Where h is the height of the tree
  - Balanced BST: O(log n)
  - Skewed BST: O(n)
  - Only traverse one path from root to leaf
- **Space Complexity**: O(h)
  - Recursive approach: O(h) stack space
  - Iterative approach: O(1)

### Visualization
```
Tree:       4
           / \
          2   5
         / \
        1   3

Target: 3.714286

Traversal:
Start at 4: |4 - 3.714| = 0.286
            closest = 4
            3.714 < 4, go LEFT

At 2:      |2 - 3.714| = 1.714
            closest stays 4 (0.286 < 1.714)
            3.714 > 2, go RIGHT

At 3:      |3 - 3.714| = 0.714
            closest stays 4 (0.286 < 0.714)
            3.714 > 3, go RIGHT (null)

Result: 4

Another example:
Target: 0.5

Start at 4: diff = 3.5, closest = 4
            0.5 < 4, go LEFT

At 2:      diff = 1.5, closest = 2
            0.5 < 2, go LEFT

At 1:      diff = 0.5, closest = 1
            0.5 < 1, go LEFT (null)

Result: 1
```

## Code Walkthrough

Since the implementation is empty, here's the typical solution:

**Recursive Approach:**
```java
public int closestValue(TreeNode root, double target) {
    int closest = root.val;
    return closestValue(root, target, closest);
}

private int closestValue(TreeNode node, double target, int closest) {
    if (node == null) {
        return closest;
    }

    // Update closest if current node is closer
    if (Math.abs(node.val - target) < Math.abs(closest - target)) {
        closest = node.val;
    }

    // Traverse based on BST property
    if (target < node.val) {
        return closestValue(node.left, target, closest);
    } else {
        return closestValue(node.right, target, closest);
    }
}
```

**Iterative Approach (More efficient):**
```java
public int closestValue(TreeNode root, double target) {
    int closest = root.val;
    TreeNode curr = root;

    while (curr != null) {
        // Update closest if current is closer
        if (Math.abs(curr.val - target) < Math.abs(closest - target)) {
            closest = curr.val;
        }

        // Navigate based on BST property
        if (target < curr.val) {
            curr = curr.left;
        } else if (target > curr.val) {
            curr = curr.right;
        } else {
            // Exact match found
            return curr.val;
        }
    }

    return closest;
}
```

## Edge Cases
- **Single node**: Always return that node's value
- **Target equals node value**: Return immediately (exact match)
- **Target smaller than all values**: Return minimum value (leftmost)
- **Target larger than all values**: Return maximum value (rightmost)
- **Two equally close values**: Problem guarantees unique closest value
- **Negative values**: Works correctly with negative numbers
- **Very large/small targets**: Correctly handles extreme values

## Related Problems
- **272. Closest Binary Search Tree Value II**: Find k closest values
- **700. Search in a Binary Search Tree**: Similar BST traversal
- **701. Insert into a Binary Search Tree**: BST navigation
- **235. Lowest Common Ancestor of a BST**: Uses BST property

## Tags
`tree` `binary-search-tree` `binary-search` `easy`

## Note
The implementation file is currently empty and needs to be completed.
