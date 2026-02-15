# LeetCode 366: Find Leaves of Binary Tree

## Problem Statement

Given the `root` of a binary tree, collect a tree's nodes as if you were doing this: Collect all the leaf nodes, remove them, and repeat until the tree is empty.

### Examples

**Example 1:**
```
Input: root = [1,2,3,4,5]

Tree:
          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]
Explanation:
Removing leaves [4,5,3] results in tree: [1,2]
Removing leaves [2] results in tree: [1]
Removing leaves [1] results in empty tree: []
```

**Example 2:**
```
Input: root = [1]
Output: [[1]]
```

**Constraints:**
- The number of nodes in the tree is in the range [1, 100]
- -100 <= Node.val <= 100

## Key Insights

1. **Layer-by-Layer Removal**: Remove leaves level by level from outside to inside
2. **Height-Based Grouping**: Nodes at same "height from bottom" are removed together
3. **DFS Solution**: Use DFS to identify and remove leaves
4. **Modification Approach**: Actually remove nodes from tree during traversal
5. **Parent Tracking**: Need parent pointer to disconnect leaf nodes

## Algorithm Steps

### Iterative Removal Approach

1. **While tree is not empty**:
   - Initialize empty list for current leaves
   - Check if root itself is a leaf
   - If yes, add to list and set root to null
   - Otherwise, perform DFS to find and remove all leaves

2. **DFS Process**:
   - If node is null, return
   - If node is a leaf (no children):
     - Add to current leaves list
     - Disconnect from parent
   - Otherwise, recursively process left and right children

3. **Repeat** until tree is empty

4. **Return** all collected leaf layers

## Complexity Analysis

- **Time Complexity**: O(n^2)
  - Each iteration visits all remaining nodes: O(n)
  - Number of iterations equals tree height: O(h)
  - Worst case (skewed tree): O(n) iterations
  - Overall: O(n * h), worst case O(n^2)

- **Space Complexity**: O(h)
  - DFS recursion stack: O(h)
  - Result storage: O(n) but not counted
  - Overall: O(h)

## Better Approach: Height-Based Solution

**Key Insight**: Instead of actually removing nodes, calculate each node's height and group by height.

### Height-Based Algorithm

1. **Calculate Heights**: Use DFS to compute each node's height from bottom
   - Leaf nodes: height = 0
   - Internal nodes: height = 1 + max(left_height, right_height)

2. **Group by Height**: Add nodes to result[height]

3. **Return** grouped results

**Complexity**: O(n) time, O(h) space - much better!

## Visual Explanation

### Example: Original Tree

```
          1
         / \
        2   3
       / \
      4   5

Iteration 1: Find leaves [4, 5, 3]
          1
         /
        2

Iteration 2: Find leaves [2]
          1

Iteration 3: Find leaves [1]
        (empty)

Result: [[4,5,3], [2], [1]]
```

### DFS Trace - Iteration 1

```
dfs(1, null, leaves=[])
  1 is not leaf (has children)

  dfs(2, parent=1, leaves=[])
    2 is not leaf (has children)

    dfs(4, parent=2, leaves=[])
      4 is leaf (no children)
      Add 4 to leaves: [4]
      Disconnect: parent.left = null

    dfs(5, parent=2, leaves=[])
      5 is leaf (no children)
      Add 5 to leaves: [4,5]
      Disconnect: parent.right = null

  dfs(3, parent=1, leaves=[])
    3 is leaf (no children)
    Add 3 to leaves: [4,5,3]
    Disconnect: parent.right = null

After iteration 1:
  Tree: 1 -> 2 (no children)
  Leaves collected: [4,5,3]
```

### Height-Based Approach Visualization

```
Original tree with heights:

          1 (h=2)
         / \
        2   3 (h=0)
       / \
      4   5 (h=0)
     (h=0)

Height calculation:
  - Node 4: max(-1, -1) + 1 = 0
  - Node 5: max(-1, -1) + 1 = 0
  - Node 3: max(-1, -1) + 1 = 0
  - Node 2: max(0, 0) + 1 = 1
  - Node 1: max(1, 0) + 1 = 2

Grouping:
  Height 0: [4, 5, 3]  -> leaves
  Height 1: [2]        -> next layer
  Height 2: [1]        -> root

Result: [[4,5,3], [2], [1]]
```

## Code Walkthrough

### Current Implementation (Iterative Removal)

```java
public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> output = new ArrayList<>();
    TreeNode temp = root;

    // Repeat until tree is empty
    while (temp != null) {
        List<Integer> currentLeaf = new ArrayList<>();

        // Check if root is a leaf
        if (temp.left == null && temp.right == null) {
            currentLeaf.add(temp.val);
            temp = null;  // Tree is now empty
        } else {
            // Find and remove all leaves
            dfs(temp, null, currentLeaf);
        }

        output.add(currentLeaf);
    }

    return output;
}

public void dfs(TreeNode node, TreeNode parent, List<Integer> currentLeaf) {
    if (node == null)
        return;

    // Check if current node is a leaf
    if (node.left == null && node.right == null) {
        currentLeaf.add(node.val);

        // Disconnect from parent
        if (parent != null) {
            if (parent.left == node)
                parent.left = null;
            if (parent.right == node)
                parent.right = null;
        }
        return;
    }

    // Recursively process children
    dfs(node.left, node, currentLeaf);
    dfs(node.right, node, currentLeaf);
}
```

### Better Implementation (Height-Based)

```java
public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    height(root, result);
    return result;
}

private int height(TreeNode node, List<List<Integer>> result) {
    if (node == null)
        return -1;

    // Calculate height from bottom
    int leftHeight = height(node.left, result);
    int rightHeight = height(node.right, result);
    int level = Math.max(leftHeight, rightHeight) + 1;

    // Ensure result list has enough sublists
    if (result.size() == level) {
        result.add(new ArrayList<>());
    }

    // Add node to appropriate level
    result.get(level).add(node.val);

    return level;
}
```

## Comparison of Approaches

### Iterative Removal
**Pros:**
- Intuitive, follows problem description literally
- Demonstrates tree modification

**Cons:**
- O(n^2) time complexity
- Actually modifies the tree
- More complex with parent tracking

### Height-Based
**Pros:**
- O(n) time complexity
- Elegant and concise
- Doesn't modify tree
- Single pass solution

**Cons:**
- Less obvious connection to problem statement
- Requires insight about height grouping

## Edge Cases

1. **Single Node**: root = [1]
   - Output: [[1]]

2. **Only Left Children**:
   ```
   1
    \
     2
      \
       3
   ```
   - Output: [[3], [2], [1]]

3. **Only Right Children**: Mirror of above

4. **Perfect Binary Tree**:
   ```
       1
      / \
     2   3
    / \ / \
   4  5 6  7
   ```
   - Output: [[4,5,6,7], [2,3], [1]]

5. **Skewed Tree**: Maximum height
   - Output: One node per layer

6. **Balanced Tree**: Optimal structure
   - Logarithmic number of layers

## Related Problems

1. **LeetCode 104**: Maximum Depth of Binary Tree
2. **LeetCode 111**: Minimum Depth of Binary Tree
3. **LeetCode 110**: Balanced Binary Tree
4. **LeetCode 102**: Binary Tree Level Order Traversal
5. **LeetCode 107**: Binary Tree Level Order Traversal II
6. **LeetCode 637**: Average of Levels in Binary Tree

## Tags

- Tree
- Binary Tree
- Depth-First Search (DFS)
- Tree Traversal
- Height Calculation
- Post-Order Traversal
