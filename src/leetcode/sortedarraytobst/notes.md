# Convert Sorted Array to Binary Search Tree (LeetCode 108)

## Problem Statement
Given an array where elements are sorted in ascending order, convert it to a height-balanced Binary Search Tree.

**Height-balanced:** A binary tree is height-balanced if the left and right subtrees of every node differ in height by at most 1.

## Examples
```
Input: [-10,-3,0,5,9]
Output:       0
             / \
           -3   9
           /   /
         -10  5

Input: [0,1,2,3,4,5]
Output:      2
            / \
           1   4
          /   / \
         0   3   5

Alternative answers:
         3
        / \
       1   4
        \   \
         2   5

         1
        / \
       0   3
            \
             2
              \
               4
                \
                 5
```

## Key Insights
1. The middle element of the sorted array becomes the root
2. Elements to the left of the middle form the left subtree
3. Elements to the right form the right subtree
4. Recursively apply the same logic to create a balanced tree
5. This guarantees height balance because we always pick the middle element

## Algorithm Steps

### Approach: Recursive Divide and Conquer
1. Check if the array is empty (base case)
2. Use a helper function with indices: start and end
3. Find the middle index: mid = (start + end) / 2
4. Create a root node with the middle element
5. Recursively build left subtree with elements [start, mid-1]
6. Recursively build right subtree with elements [mid+1, end]
7. Connect and return the root

## Complexity Analysis
- **Time Complexity:** O(n) - Visit each node once
- **Space Complexity:** O(log n) - Recursion stack depth (height of balanced tree) plus O(n) for the tree itself

## ASCII Visualization

```
Sorted Array: [-10, -3, 0, 5, 9]
Indices:       0    1   2  3  4

Step 1: mid = (0+4)/2 = 2, root = 0
        Left: [-10, -3] (indices 0-1)
        Right: [5, 9] (indices 3-4)
                 0
               /   \
              ?     ?

Step 2: Process left [-10, -3]
        mid = (0+1)/2 = 0, root = -10
        Left: [] (empty)
        Right: [-3] (index 1)
              -10
                \
                -3

Step 3: Process right [5, 9]
        mid = (3+4)/2 = 3, root = 5
        Left: [] (empty)
        Right: [9] (index 4)
              5
               \
                9

Final Tree:
           0
         /   \
       -10    5
         \     \
         -3     9
```

## Code Walkthrough

```java
public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0)
        return null;

    return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
}

public TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
    // Base case: invalid range
    if (start > end)
        return null;

    // Single element case
    if (start == end) {
        return new TreeNode(nums[start]);
    }

    // Find middle element
    int mid = (start + end) / 2;

    // Create root and recursively build subtrees
    TreeNode n = new TreeNode(nums[mid]);
    n.left = sortedArrayToBSTHelper(nums, start, mid - 1);
    n.right = sortedArrayToBSTHelper(nums, mid + 1, end);

    return n;
}
```

## Edge Cases
1. Empty array: [] → null
2. Single element: [0] → TreeNode(0)
3. Two elements: [1, 2] → 1 with right child 2
4. All negative numbers: [-5, -3, -1] → balanced tree
5. Large sorted array: [1..100] → perfectly balanced BST

## Related Problems
- LeetCode 98: Validate Binary Search Tree
- LeetCode 110: Balanced Binary Tree
- LeetCode 109: Convert Sorted List to BST
- LeetCode 1008: Construct BST from Preorder Traversal

## Tags
- Tree
- Binary Search Tree
- Divide and Conquer
- Recursion
- Array
- Height-Balanced
