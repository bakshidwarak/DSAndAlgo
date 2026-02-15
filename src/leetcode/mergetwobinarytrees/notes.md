# LeetCode 617: Merge Two Binary Trees

## Problem Statement
Given two binary trees, merge them into a new binary tree. When two nodes overlap, sum their values. Otherwise, use the non-null node.

## Difficulty
Easy

## Examples

### Example 1
```
Tree 1:          Tree 2:
    1                2
   / \              / \
  3   2            1   3
 /                  \   \
5                    4   7

Output:
    3
   / \
  4   5
 / \   \
5   4   7
```

## Key Insights
1. **Recursive Overlap Handling**: When both nodes exist, sum them and recurse on children
2. **Base Cases**: If one tree is null, return the other
3. **New Tree Creation**: Create new tree rather than modifying existing ones
4. **Structural Preservation**: If one tree has child and other doesn't, use that child
5. **Traversal Pattern**: Can be DFS (recursive) or level-order

## Algorithm Steps
1. Base cases:
   - If t1 is null, return t2
   - If t2 is null, return t1
2. Create new node with sum of both nodes
3. Recursively merge left subtrees
4. Recursively merge right subtrees
5. Return new merged node

## Complexity Analysis
- **Time Complexity:** O(min(m, n)) where m, n are node counts (stops at smaller tree)
- **Space Complexity:** O(min(h1, h2)) - Recursion depth of smaller tree

## ASCII Visualization

```
Tree 1:      Tree 2:
    1            2
   / \          / \
  3   2        1   3
 /              \   \
5                4   7

Merge process:
1. root1=1, root2=2 → new val = 3
   Recurse left: (3, 1)
   Recurse right: (2, 3)

2. left merge (3, 1) → new val = 4
   Recurse left: (5, null) → 5
   Recurse right: (null, null) → null

3. right merge (2, 3) → new val = 5
   Recurse left: (null, null) → null
   Recurse right: (null, 7) → 7

Result:
    3
   / \
  4   5
 / \   \
5   4   7
```

## Edge Cases
1. **One tree is null:** Return the other
2. **Both trees are null:** Return null
3. **Single node trees:** Return node with sum
4. **Tree of different heights:** Properly handle null children
5. **Identical structures:** Values are summed correctly

## Related Problems
- **LeetCode 100:** Same Tree
- **LeetCode 101:** Symmetric Tree
- **LeetCode 572:** Subtree of Another Tree
- **LeetCode 897:** Increasing Order Search Tree

## Tags
`Tree` `Recursion` `Binary Tree` `DFS`
