# Symmetric Tree (LeetCode 101)

## Problem Statement
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

## Examples
```
Example 1 (Symmetric):
        1
       / \
      2   2
     / \ / \
    3  4 4  3

Output: true
Explanation: The tree is symmetric around its center.

Example 2 (Not Symmetric):
        1
       / \
      2   2
       \   \
        3   3

Output: false
Explanation: The right subtree mirrors the structure of the left
subtree but with different arrangement.

Example 3:
      2
     / \
    3   3

Output: true

Example 4:
        1
         \
          2

Output: false
```

## Key Insights
1. A tree is symmetric if the left subtree is a mirror of the right subtree
2. Mirror means: left.val == right.val, left.left mirrors right.right, left.right mirrors right.left
3. We compare nodes symmetrically: compare root.left with root.right
4. For left and right subtrees, compare: left.left with right.right, and left.right with right.left
5. Base cases: both null (symmetric), one null (not symmetric), values differ (not symmetric)

## Algorithm Steps

### Approach: Recursive Mirror Checking
1. Main function: Call helper with root.left and root.right
2. Helper function compares two nodes:
   - Base case 1: If both left and right are null, return true
   - Base case 2: If either is null (but not both), return false
   - Base case 3: If values are different, return false
   - Recursive case: Return AND of:
     - isSymmetric(left.left, right.right)  - Left's left matches right's right
     - isSymmetric(left.right, right.left)  - Left's right matches right's left
3. This naturally ensures mirror structure

## Complexity Analysis
- **Time Complexity:** O(n) - Visit each node once in worst case
- **Space Complexity:** O(h) - Recursion stack where h is height

## ASCII Visualization

```
        1
       / \
      2   2
     / \ / \
    3  4 4  3

Check isSymmetric(root):
  Call isSymmetric(2_left, 2_right)

isSymmetric(2_left, 2_right):
  2_left.val == 2_right.val? Yes (2 == 2)
  Need: isSymmetric(3, 3) AND isSymmetric(4, 4)

isSymmetric(3, 3):
  3.val == 3.val? Yes
  isSymmetric(null, null) AND isSymmetric(null, null)
  Both true -> true

isSymmetric(4, 4):
  4.val == 4.val? Yes
  isSymmetric(null, null) AND isSymmetric(null, null)
  Both true -> true

Result: true AND true = true

---

        1
       / \
      2   2
       \   \
        3   3

Check isSymmetric(root):
  Call isSymmetric(2_left, 2_right)

isSymmetric(2_left, 2_right):
  2_left.val == 2_right.val? Yes (2 == 2)
  Need: isSymmetric(null, 3) AND isSymmetric(3, null)

isSymmetric(null, 3):
  One is null, one is not -> false

Result: false
```

## Code Walkthrough

```java
public boolean isSymmetric(TreeNode root) {
    if (root == null)
        return true;

    // Check if left and right subtrees are mirrors of each other
    return isSymmetric(root.left, root.right);
}

public boolean isSymmetric(TreeNode left, TreeNode right) {
    // Both null: symmetric
    if (left == null && right == null)
        return true;

    // One null, one not: not symmetric
    if (left == null)
        return false;
    if (right == null)
        return false;

    // Values differ: not symmetric
    // Otherwise check mirror structure
    return left.val == right.val &&
           isSymmetric(left.left, right.right) &&
           isSymmetric(left.right, right.left);
}
```

## Iterative Alternative (Using Queue)

```java
public boolean isSymmetric(TreeNode root) {
    if (root == null)
        return true;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root.left);
    queue.offer(root.right);

    while (!queue.isEmpty()) {
        TreeNode left = queue.poll();
        TreeNode right = queue.poll();

        if (left == null && right == null)
            continue;
        if (left == null || right == null || left.val != right.val)
            return false;

        queue.offer(left.left);
        queue.offer(right.right);
        queue.offer(left.right);
        queue.offer(right.left);
    }

    return true;
}
```

## Edge Cases
1. Single node: TreeNode(1) → true
2. Two identical nodes:
   ```
      1
     / \
    2   2
   ```
   → true

3. Two different nodes:
   ```
      1
     / \
    2   3
   ```
   → false

4. Only left child:
   ```
      1
     /
    2
   ```
   → false

5. Only right child:
   ```
      1
       \
        2
   ```
   → false

6. Null tree: null → true
7. Deep skewed tree

## Related Problems
- LeetCode 100: Same Tree
- LeetCode 572: Subtree of Another Tree
- LeetCode 226: Invert Binary Tree
- LeetCode 250: Count Univalue Subtrees
- LeetCode 1740: Find Distance in a Binary Tree

## Tags
- Tree
- Recursion
- DFS
- Mirror/Symmetry
- Binary Tree
