# Sum Root to Leaf Numbers (LeetCode 129)

## Problem Statement
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

Find the total sum of all root-to-leaf numbers.

**Note:** A leaf is a node with no children.

## Examples
```
Example 1:
        1
       / \
      2   3

Root-to-leaf paths:
  Path 1: 1 -> 2 = 12
  Path 2: 1 -> 3 = 13
Total: 12 + 13 = 25

Example 2:
        4
       / \
      9   0
     / \
    5   1

Root-to-leaf paths:
  Path 1: 4 -> 9 -> 5 = 495
  Path 2: 4 -> 9 -> 1 = 491
  Path 3: 4 -> 0 = 40
Total: 495 + 491 + 40 = 1026

Example 3:
      1
     / \
    2   3

Total: 1 * 10 + 2 + 1 * 10 + 3 = 25
```

## Key Insights
1. We need to find all root-to-leaf paths
2. Each path represents a number formed by concatenating the digits
3. We can convert the sequence of digits to a number by multiplying previous sum by 10 and adding current digit
4. DFS is natural for this problem
5. We only count the sum when reaching a leaf node (no children)

## Algorithm Steps

### Approach: DFS with Path Tracking
1. Use a helper function that collects all root-to-leaf paths
2. At each node:
   - Add current node's value to the current path
   - Check if it's a leaf (no children)
   - If leaf, add path to result
   - If not leaf, recursively process left and right children
   - Backtrack by removing the current value from path
3. After collecting all paths, convert each path to a number and sum them up

## Complexity Analysis
- **Time Complexity:** O(n) - Visit each node once; O(h) for path length where h is height
- **Space Complexity:** O(h) - Recursion depth plus O(n) for storing all paths

## ASCII Visualization

```
        1
       / \
      2   3

DFS traversal:
current = [1], is_leaf? No
  -> left: current = [1,2], is_leaf? Yes, add [1,2]
           number = 1*10 + 2 = 12
  -> backtrack: current = [1]
  -> right: current = [1,3], is_leaf? Yes, add [1,3]
            number = 1*10 + 3 = 13
  -> backtrack: current = [1]

All paths: [[1,2], [1,3]]
Sum: 12 + 13 = 25

---

        4
       / \
      9   0
     / \
    5   1

DFS traversal:
current = [4]
  -> left: current = [4,9]
    -> left: current = [4,9,5], is_leaf? Yes, add [4,9,5]
             number = 4*100 + 9*10 + 5 = 495
    -> right: current = [4,9,1], is_leaf? Yes, add [4,9,1]
              number = 4*100 + 9*10 + 1 = 491
  -> right: current = [4,0], is_leaf? Yes, add [4,0]
            number = 4*10 + 0 = 40

All paths: [[4,9,5], [4,9,1], [4,0]]
Sum: 495 + 491 + 40 = 1026
```

## Code Walkthrough

```java
public int sumNumbers(TreeNode root) {
    if (root == null)
        return 0;

    List<Integer> current = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    // Collect all root-to-leaf paths
    helper(root, result, current);

    // Convert each path to a number and sum them
    int sum = 0;
    for (List<Integer> list : result) {
        int number = 0;
        for (int num : list) {
            number = number * 10 + num;
        }
        sum += number;
    }

    return sum;
}

public void helper(TreeNode root, List<List<Integer>> result, List<Integer> current) {
    // Check if this is a leaf node
    if (root.left == null && root.right == null) {
        current.add(root.val);
        result.add(new ArrayList<>(current));
        current.remove(current.size() - 1);
        return;
    }

    // Add current node to path
    current.add(root.val);

    // Recursively process children
    if (root.left != null)
        helper(root.left, result, current);
    if (root.right != null)
        helper(root.right, result, current);

    // Backtrack
    current.remove(current.size() - 1);
}
```

## Alternative: Direct Calculation (No Path Storage)

```java
public int sumNumbers(TreeNode root) {
    return helper(root, 0);
}

private int helper(TreeNode node, int currentSum) {
    if (node == null)
        return 0;

    currentSum = currentSum * 10 + node.val;

    // If it's a leaf, return the accumulated sum
    if (node.left == null && node.right == null) {
        return currentSum;
    }

    // Recursively sum left and right subtrees
    return helper(node.left, currentSum) + helper(node.right, currentSum);
}
```

## Edge Cases
1. Single node: TreeNode(5) → 5
2. Only left children (skewed tree):
   ```
     1
    /
   2
   /
  3
   ```
   Path: 1-2-3 = 123, Output: 123

3. Only right children:
   ```
     1
      \
       2
        \
         3
   ```
   Path: 1-2-3 = 123, Output: 123

4. All nodes are 0: → Sum is 0
5. Large numbers: May overflow if not using long

## Related Problems
- LeetCode 112: Path Sum
- LeetCode 113: Path Sum II
- LeetCode 404: Sum of Left Leaves
- LeetCode 257: Binary Tree Paths
- LeetCode 988: Smallest String Starting From Leaf

## Tags
- Tree
- DFS
- Recursion
- Path Tracking
- Number Formation
