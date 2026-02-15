# Leaf-Similar Trees

## Problem Statement
**LeetCode Problem 872**: Leaf-Similar Trees (Easy)

Consider all the leaves of a binary tree. From left to right order, the values of those leaves form a leaf value sequence.

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

### Examples
**Example 1:**
```
Input:
Tree 1:        Tree 2:
    3              3
   / \            / \
  5   1          5   1
 / \ / \        / \ / \
6  2 9  8      6  7 4  2
  / \                 / \
 7   4               9   8

Leaf sequence 1: [6, 7, 4, 9, 8]
Leaf sequence 2: [6, 7, 4, 9, 8]
Output: true
```

**Example 2:**
```
Input:
Tree 1: [1,2,3]
Tree 2: [1,3,2]

Leaf sequence 1: [2, 3]
Leaf sequence 2: [3, 2]
Output: false
```

**Note**: Both of the given trees will have between 1 and 100 nodes.

## Key Insights
1. **Leaf Node Definition**: Node with no left and no right children
2. **Order Matters**: Sequence must match exactly (not just set of leaves)
3. **DFS Traversal**: Use DFS to collect leaves in left-to-right order
4. **Pre-order Traversal**: Natural order for collecting leaves
5. **Two-Phase Approach**: Collect both sequences, then compare

## Algorithm Steps
```
1. Create empty list for tree1 leaves
2. Perform DFS on tree1 to collect leaf sequence
3. Create empty list for tree2 leaves
4. Perform DFS on tree2 to collect leaf sequence
5. Compare sequences:
   a. If lengths differ, return false
   b. If any element differs, return false
   c. Otherwise, return true
```

## Complexity Analysis
- **Time Complexity**: O(n + m)
  - n = nodes in tree1
  - m = nodes in tree2
  - Visit each node once
- **Space Complexity**: O(h1 + h2 + L)
  - h1, h2 = heights of trees (recursion stack)
  - L = total number of leaves
  - Lists store leaf values

## Visual Representation

### Example: Leaf Collection Process
```
Tree:
        3
       / \
      5   1
     / \ / \
    6  2 9  8
      / \
     7   4

DFS Traversal Order:
Start at 3
  -> Go left to 5
     -> Go left to 6 (LEAF! Add to sequence: [6])
     -> Go right to 2
        -> Go left to 7 (LEAF! Add to sequence: [6, 7])
        -> Go right to 4 (LEAF! Add to sequence: [6, 7, 4])
  -> Go right to 1
     -> Go left to 9 (LEAF! Add to sequence: [6, 7, 4, 9])
     -> Go right to 8 (LEAF! Add to sequence: [6, 7, 4, 9, 8])

Final leaf sequence: [6, 7, 4, 9, 8]
```

### Comparison Example
```
Tree 1:          Tree 2:
   1                1
  / \              / \
 2   3            3   2

Tree 1 leaves: [2, 3]
Tree 2 leaves: [3, 2]

Comparison:
Position 0: 2 != 3 -> DIFFERENT!
Result: false
```

## Code Walkthrough

### Current Implementation
```java
static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    // Collect leaf sequences for both trees
    List<Integer> seq1 = new ArrayList<>();
    List<Integer> seq2 = new ArrayList<>();

    dfs(root1, seq1);
    dfs(root2, seq2);

    // Check if sequences have same length
    if (seq1.size() != seq2.size())
        return false;

    // Compare element by element
    for (int i = 0; i < seq1.size(); i++) {
        if (seq1.get(i) != seq2.get(i))
            return false;
    }

    return true;
}

public void dfs(TreeNode root, List<Integer> seq) {
    if (root == null)
        return;

    // Check if this is a leaf node
    if (root.left == null && root.right == null) {
        seq.add(root.val);
        return;  // Don't recurse further
    }

    // Recurse to children (left first for correct order)
    dfs(root.left, seq);
    dfs(root.right, seq);
}
```

### Alternative: Using equals()
```java
public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> seq1 = new ArrayList<>();
    List<Integer> seq2 = new ArrayList<>();

    dfs(root1, seq1);
    dfs(root2, seq2);

    return seq1.equals(seq2);  // List.equals() compares size and elements
}
```

### Space-Optimized: Iterator Pattern
```java
public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    Iterator<Integer> iter1 = getLeafIterator(root1);
    Iterator<Integer> iter2 = getLeafIterator(root2);

    while (iter1.hasNext() && iter2.hasNext()) {
        if (!iter1.next().equals(iter2.next())) {
            return false;
        }
    }

    return !iter1.hasNext() && !iter2.hasNext();
}

private Iterator<Integer> getLeafIterator(TreeNode root) {
    List<Integer> leaves = new ArrayList<>();
    dfs(root, leaves);
    return leaves.iterator();
}
```

### DFS Helper Walkthrough
```java
public void dfs(TreeNode root, List<Integer> seq) {
    // Base case: null node
    if (root == null)
        return;

    // Check if leaf: no left AND no right children
    if (root.left == null && root.right == null) {
        seq.add(root.val);
        return;  // Important: stop recursion at leaf
    }

    // Not a leaf: recurse to children
    // Left before right ensures left-to-right order
    dfs(root.left, seq);
    dfs(root.right, seq);
}
```

## Edge Cases
1. **Both trees null**: Return true (empty sequences match)
2. **One tree null**: Return false (one empty, one not)
3. **Single node trees**: Both are leaves, compare values
4. **Different number of leaves**: Return false immediately
5. **Same leaves, different structure**: Return true (structure doesn't matter)
6. **Mirror trees**: May or may not be leaf-similar

### Edge Case Examples
```
1. Both null:
   Input: null, null
   Output: true

2. Single nodes:
   Tree1: [5], Tree2: [5]
   Output: true

   Tree1: [5], Tree2: [6]
   Output: false

3. Different structures, same leaves:
   Tree1:  1        Tree2:    1
          /                    \
         2                      2
   Leaves: [2], [2]
   Output: true

4. Different leaf counts:
   Tree1:  1        Tree2:  1
          / \              /
         2   3            2
   Leaves: [2,3], [2]
   Output: false

5. Same leaves, different order:
   Tree1:  1        Tree2:  1
          / \              / \
         2   3            3   2
   Leaves: [2,3], [3,2]
   Output: false
```

## Common Mistakes
1. **Forgetting to check both children are null**:
   - Must check `left == null AND right == null`
   - Checking just one can miss nodes
2. **Wrong traversal order**:
   - Must visit left before right
   - In-order or post-order won't give correct leaf sequence
3. **Continuing recursion at leaf**:
   - Should return after adding leaf
4. **Not handling null trees**:
   - Need null checks

## Related Problems
- **Same Tree (LeetCode 100)**: Compare entire tree structure
- **Symmetric Tree (LeetCode 101)**: Check mirror symmetry
- **Sum of Left Leaves (LeetCode 404)**: Sum specific leaves
- **Find Leaves of Binary Tree (LeetCode 366)**: Group leaves by level
- **Binary Tree Paths (LeetCode 257)**: Find all root-to-leaf paths

## Tags
- Tree
- Binary Tree
- Depth-First Search
- Tree Traversal
- Leaf Nodes
- Easy
- Amazon Interview
