# LeetCode 235: Lowest Common Ancestor of a Binary Search Tree

## Problem Statement
Given a **Binary Search Tree (BST)**, find the **Lowest Common Ancestor (LCA)** of two given nodes. The LCA is the lowest node that has both nodes as descendants (a node can be its own descendant).

## Difficulty
Medium

## Examples

### Example 1
```
       _______6______
      /              \
    __2__          ___8__
   /     \        /      \
   0     _4      7        9
         / \
         3   5

LCA(2, 8) = 6
LCA(2, 4) = 2 (node can be ancestor of itself)
LCA(4, 5) = 4
```

## Key Insights
1. **BST Property Exploitation**: Use the BST ordering property to narrow down search space
2. **Three Cases**:
   - If both nodes are in left subtree, LCA is in left subtree
   - If both nodes are in right subtree, LCA is in right subtree
   - If nodes are on different sides of current node, current node is LCA
3. **No Need for Path Finding**: Unlike binary trees, we can find LCA without tracking full paths

## Algorithm Steps
1. Start at root node
2. Compare node values with both p and q values
3. If both p.val and q.val are less than root.val, recurse on left subtree
4. If both p.val and q.val are greater than root.val, recurse on right subtree
5. If one is on each side or matches root, return current root

## Complexity Analysis
- **Time Complexity:** O(h) where h = height of tree (O(log n) for balanced, O(n) for skewed)
- **Space Complexity:** O(h) - Recursion call stack

## ASCII Visualization

```
Find LCA(2, 4):
       6
      / \
     2   8
    / \
   0   4
      / \
     3   5

Step 1: root=6, p=2, q=4
        6 > 2 and 6 > 4 → go left

Step 2: root=2, p=2, q=4
        2 == 2 (found p) → return 2
        
Result: LCA(2,4) = 2
```

## Edge Cases
1. **One node is ancestor of other:** Return the ancestor
2. **Root is one of the nodes:** Return root
3. **Both nodes are root:** Return root
4. **Skewed tree (like linked list):** Still O(n) but algorithm is correct
5. **Nodes not in tree:** Algorithm assumes both nodes exist in tree

## Related Problems
- **LeetCode 236:** Lowest Common Ancestor of a Binary Tree
- **LeetCode 1257:** Smallest Common Region
- **LeetCode 1676:** Lowest Common Ancestor of a Binary Tree IV
- **LeetCode 1740:** Find Distance in a Binary Tree

## Tags
`Tree` `Binary Search Tree` `Recursion` `Lowest Common Ancestor`
