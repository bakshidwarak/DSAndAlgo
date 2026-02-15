# 671. Second Minimum Node In a Binary Tree

## Problem Statement
Given a non-empty special binary tree where each node has exactly two or zero sub-nodes and if a node has two sub-nodes, then the node's value is the smaller value among its two sub-nodes.

Find the second minimum value in the tree. If no second minimum value exists, return -1.

## Examples

### Example 1
```
Tree:     2
         / \
        2   5
           / \
          5   7

Output: 5
Explanation: The smallest value is 2, the second smallest is 5
```

### Example 2
```
Tree:     2
         / \
        2   2

Output: -1
Explanation: The smallest value is 2, but there's no second smallest
```

## Key Insights

1. **Root is minimum**: Due to the tree property, root always contains the minimum value
2. **Second minimum must be in subtree**: Since parent < children, second min is in subtrees
3. **Two approaches**:
   - Find first value > root in subtrees
   - Collect all distinct values and return second smallest
4. **Recursive exploration**: Need to explore entire tree to find second minimum

## Algorithm Steps

1. Find the minimum value (at root)
2. In left and right subtrees, find the minimum value that is greater than the root
3. Return the smaller of these two values
4. If no such value exists, return -1

## Complexity Analysis

**Time Complexity:** O(n)
- May need to visit all n nodes in worst case
- When tree has only one distinct value

**Space Complexity:** O(h)
- h = height of tree
- Recursion call stack depth

## ASCII Visualization

```
Tree:         2
             / \
            2   5
               / \
              5   7

Step 1: Find root (minimum) = 2

Step 2: Explore subtrees looking for value > 2
  Left subtree (rooted at 2):
    Value = 2, not > 2, continue to children
    No children with value > 2
    Return Integer.MAX_VALUE (not found)

  Right subtree (rooted at 5):
    Value = 5, which is > 2
    Return 5 (found second minimum)

Step 3: Compare left result and right result
  min(Integer.MAX_VALUE, 5) = 5

Answer: 5
```

## Code Walkthrough

```java
static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public int findSecondMinimumValue(TreeNode root) {
    // Edge case: no tree or single node
    if (root == null)
        return -1;

    // Edge case: no children
    if (root.left == null)
        return -1;

    // Check immediate children
    // If either child equals root, it can't be second min
    // If child > root, it could be second min
    if (root.left.val > root.val && root.left.val <= root.right.val) {
        return root.left.val;
    } else if (root.right.val > root.val && root.right.val < root.left.val) {
        return root.right.val;
    } else {
        // Neither immediate child is second min
        // Must be deeper in the tree
        int secondMin = Math.min(
            getMinimum(root.left, root.val),
            getMinimum(root.right, root.val)
        );
        if (secondMin == Integer.MAX_VALUE)
            return -1;
        else
            return secondMin;
    }
}

public int getMinimum(TreeNode root, int val) {
    // If current value > val, we found a candidate for second min
    if (root.val > val)
        return root.val;

    // If no children and value <= val, can't find second min here
    if (root.left == null)
        return Integer.MAX_VALUE;

    // Recursively search both subtrees
    return Math.min(
        getMinimum(root.left, root.val),
        getMinimum(root.right, root.val)
    );
}
```

## Step-by-step Example

```
Tree:     2
         / \
        2   5
           / \
          5   7

findSecondMinimumValue(2):
  root.val = 2
  root.left = 2, root.right = 5

  Check immediate children:
  root.left.val > root.val? 2 > 2? NO
  root.right.val > root.val? 5 > 2? YES
  root.right.val <= root.left.val? 5 <= 2? NO

  Neither condition matches, so continue

  secondMin = min(
    getMinimum(node(2), 2),
    getMinimum(node(5), 2)
  )

  getMinimum(node(2), 2):
    2 > 2? NO
    node.left = null? YES
    return Integer.MAX_VALUE

  getMinimum(node(5), 2):
    5 > 2? YES
    return 5

  secondMin = min(Integer.MAX_VALUE, 5) = 5
  return 5
```

## Edge Cases

1. **No second minimum**: [2,2,2] -> -1
2. **Immediate second minimum**: [2,2,5] -> 5
3. **Deep second minimum**: [2,2,2,2,2,5,5] -> 5
4. **Single node**: [5] -> -1
5. **Two nodes same value**: [2,2] -> -1
6. **Two nodes different value**: [2,5] -> 5

## Alternative Approach (Collect All Distinct Values)

```java
public int findSecondMinimumValueAlternative(TreeNode root) {
    Set<Integer> values = new TreeSet<>();
    dfs(root, values);

    if (values.size() < 2)
        return -1;

    Iterator<Integer> it = values.iterator();
    it.next();  // Skip the first (minimum)
    return it.next();  // Return the second
}

private void dfs(TreeNode node, Set<Integer> values) {
    if (node == null)
        return;
    values.add(node.val);
    dfs(node.left, values);
    dfs(node.right, values);
}
```

## Special Tree Property

The special property of this tree (parent < children) simplifies the problem:
- Root is guaranteed to be the minimum
- Second minimum must be > root
- This eliminates many candidates

## Related Problems

- 100: Same Tree
- 101: Symmetric Tree
- 530: Minimum Absolute Difference in BST
- 235: Lowest Common Ancestor of a Binary Search Tree

## Tags

`easy` `tree` `binary-tree` `recursion` `depth-first-search`
