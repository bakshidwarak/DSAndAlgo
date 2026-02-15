# 257. Binary Tree Paths

## Problem Statement
Given a binary tree, return all root-to-leaf paths.

A leaf is a node with no children.

### Examples
```
Input:
   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]
Explanation: All root-to-leaf paths are: 1->2->5, 1->3
```

### Constraints
- The number of nodes in the tree is in the range [1, 100]
- -100 <= Node.val <= 100

## Approach & Solution

### Key Insights
1. **DFS traversal**: Use depth-first search to explore all paths
2. **Backtracking**: Maintain current path using stack, backtrack after exploring each branch
3. **Leaf detection**: When node has no left and right children, it's a leaf
4. **Path building**: Build path string by joining node values with "->"

### Algorithm Steps
1. Use a stack to maintain current path from root to current node
2. Recursively traverse the tree:
   - If node is null, return
   - If node is a leaf (no children):
     - Add node to current path
     - Build path string and add to results
     - Remove node from path (backtrack)
     - Return
   - If node is not a leaf:
     - Add node to current path
     - Recursively process left child
     - Recursively process right child
     - Remove node from path (backtrack)
3. Return all collected paths

### Complexity Analysis
- **Time Complexity**: O(n)
  - Where n is the number of nodes
  - Each node is visited once
  - Building path strings takes O(h) per path where h is height
  - Total: O(n * h) in worst case, but typically O(n)
- **Space Complexity**: O(h)
  - Recursion stack depth: O(h) where h is tree height
  - Current path stack: O(h)
  - Result list: O(n) for storing paths
  - Total: O(n + h) ≈ O(n)

### Visualization
```
Input Tree:
     1
   /   \
  2     3
   \
    5

DFS Traversal with Backtracking:

Start at 1:
  currentPath = [1]

Go left to 2:
  currentPath = [1, 2]

Go left (null):
  return

Go right to 5:
  currentPath = [1, 2, 5]
  Leaf found! Add "1->2->5" to result
  Backtrack: currentPath = [1, 2]

Backtrack from 2: currentPath = [1]

Go right to 3:
  currentPath = [1, 3]
  Leaf found! Add "1->3" to result
  Backtrack: currentPath = [1]

Done.

Result: ["1->2->5", "1->3"]

Visual representation of paths:
    1
   / \
  2   3     ← leaf
   \
    5       ← leaf

Path 1: 1 → 2 → 5
Path 2: 1 → 3
```

## Code Walkthrough

```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<>();
    Stack<TreeNode> currentPath = new Stack<>();

    // Start DFS from root
    binaryTreePaths(root, paths, currentPath);

    return paths;
}

public void binaryTreePaths(TreeNode node, List<String> paths,
                           Stack<TreeNode> currentPath) {
    // Base case: null node
    if (node == null)
        return;

    // Leaf node: record path
    if (node.left == null && node.right == null) {
        currentPath.push(node);

        // Build path string
        ArrayList<TreeNode> path = new ArrayList<>(currentPath);
        StringBuilder sb = new StringBuilder();
        for (TreeNode n : path) {
            if (sb.length() != 0)
                sb.append("->");
            sb.append(n.val);
        }
        paths.add(sb.toString());

        // Backtrack
        currentPath.pop();
        return;
    }

    // Internal node: continue DFS
    currentPath.push(node);

    // Explore left subtree
    binaryTreePaths(node.left, paths, currentPath);

    // Explore right subtree
    binaryTreePaths(node.right, paths, currentPath);

    // Backtrack
    currentPath.pop();
    return;
}
```

**Alternative Approach (String-based):**
```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root != null) {
        dfs(root, "", result);
    }
    return result;
}

private void dfs(TreeNode node, String path, List<String> result) {
    if (node.left == null && node.right == null) {
        // Leaf node
        result.add(path + node.val);
        return;
    }

    // Internal node
    path = path + node.val + "->";

    if (node.left != null) {
        dfs(node.left, path, result);
    }
    if (node.right != null) {
        dfs(node.right, path, result);
    }
}
```

## Edge Cases
- **Single node**: [1] → ["1"]
- **Left-skewed tree**: [1,2,null,3] → ["1->2->3"]
- **Right-skewed tree**: [1,null,2,null,3] → ["1->2->3"]
- **Two leaves at different depths**: Correctly handles varying path lengths
- **Complete binary tree**: Multiple paths of same length
- **Negative values**: [-1,-2,3] → ["-1->-2", "-1->3"]

## Related Problems
- **112. Path Sum**: Check if root-to-leaf path with specific sum exists
- **113. Path Sum II**: Return all root-to-leaf paths with specific sum
- **124. Binary Tree Maximum Path Sum**: Find path with maximum sum
- **129. Sum Root to Leaf Numbers**: Sum all paths interpreted as numbers
- **437. Path Sum III**: Count paths (not necessarily root-to-leaf) with sum

## Tags
`tree` `dfs` `string` `backtracking` `binary-tree` `easy`
