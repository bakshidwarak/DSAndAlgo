# 100. Same Tree

## Problem Statement
Given two binary trees, write a function to check if they are equal/same or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same values.

## Examples

### Example 1
```
Tree 1:  1         Tree 2:  1
        / \                / \
       2   3              2   3

Output: true
Explanation: Both trees have same structure and values
```

### Example 2
```
Tree 1:  1         Tree 2:     1
        /                        \
       2                          2

Output: false
Explanation: Different structure
```

### Example 3
```
Tree 1:  1         Tree 2:  1
        / \                / \
       2   1              1   2

Output: false
Explanation: Same structure but different node values
```

## Key Insights

1. **Recursive base cases**: Handle null nodes carefully
2. **Early equality check**: If both nodes are same object, return true
3. **Structural comparison**: Check left and right subtrees recursively
4. **Value comparison**: Ensure node values match
5. **Order matters**: Left subtree must match left, right must match right

## Algorithm Steps

1. If both nodes are null, return true (both empty subtrees are same)
2. If one is null and other is not, return false
3. If values don't match, return false
4. Recursively check left subtrees
5. Recursively check right subtrees
6. Return true only if all checks pass

## Complexity Analysis

**Time Complexity:** O(min(m, n))
- m = nodes in tree 1, n = nodes in tree 2
- In worst case (identical trees), visit all O(m) nodes
- Early termination if trees differ

**Space Complexity:** O(min(h1, h2))
- h1, h2 = heights of both trees
- Recursion depth = minimum height for early termination
- In worst case (skewed tree): O(h)

## ASCII Visualization

```
Comparing two trees:

Tree 1:     1              Tree 2:     1
           / \                        / \
          2   3                      2   3

Comparison process:
isSameTree(1, 1):
  Both values = 1, continue
  isSameTree(2, 2):
    Both values = 2, continue
    isSameTree(null, null): return true
    isSameTree(null, null): return true
    return true
  isSameTree(3, 3):
    Both values = 3, continue
    isSameTree(null, null): return true
    isSameTree(null, null): return true
    return true
  return true && true = true

Result: Same tree!
```

## Code Walkthrough

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    // Base case 1: Both nodes are the same object (same reference)
    if (p == q)
        return true;

    // Base case 2: One is null, other is not
    if (p == null || q == null)
        return false;

    // Check if values match, AND both left subtrees match, AND both right subtrees match
    return p.val == q.val &&
           isSameTree(p.left, q.left) &&
           isSameTree(p.right, q.right);
}
```

### Step-by-step Execution for Example 1

```
p = [1,2,3], q = [1,2,3]

Call 1: isSameTree(node1, node1')
  p == q? false (different objects)
  p == null || q == null? false
  p.val == q.val? 1 == 1? YES
  Recursively check left: isSameTree(node2, node2')
    p.val == q.val? 2 == 2? YES
    Check its left: isSameTree(null, null)
      p == q? YES (both null)
      return true
    Check its right: isSameTree(null, null)
      p == q? YES
      return true
    return true && true = true
  Recursively check right: isSameTree(node3, node3')
    p.val == q.val? 3 == 3? YES
    Check its left: isSameTree(null, null) -> true
    Check its right: isSameTree(null, null) -> true
    return true && true = true
  return true && true && true = true
```

## Edge Cases

1. **Both empty**: null, null -> true
2. **One empty**: null, node -> false
3. **Both single node with same value**: [1], [1] -> true
4. **Both single node different value**: [1], [2] -> false
5. **Same structure different values**: [1,2,3], [1,2,4] -> false
6. **Different structures**: [1,2], [1,null,2] -> false

## Iterative Approach using Queue

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    Queue<TreeNode[]> queue = new LinkedList<>();
    queue.add(new TreeNode[]{p, q});

    while (!queue.isEmpty()) {
        TreeNode[] nodes = queue.poll();
        TreeNode node1 = nodes[0];
        TreeNode node2 = nodes[1];

        // Check if both null or values differ
        if (node1 == null && node2 == null) {
            continue;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }

        // Add children to queue
        queue.add(new TreeNode[]{node1.left, node2.left});
        queue.add(new TreeNode[]{node1.right, node2.right});
    }
    return true;
}
```

## Related Problems

- 101: Symmetric Tree (check if tree is mirror of itself)
- 572: Subtree of Another Tree (check if one tree is subtree of another)
- 1367: Linked List in Binary Tree
- 99: Recover Binary Search Tree

## Tags

`easy` `tree` `recursion` `depth-first-search` `breadth-first-search`
