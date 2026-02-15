# LeetCode 105: Construct Binary Tree from Preorder and Inorder Traversal

## Problem Statement

Given two integer arrays `preorder` and `inorder` where:
- `preorder` is the preorder traversal of a binary tree
- `inorder` is the inorder traversal of the same tree

Construct and return the binary tree.

**Note:** You may assume that duplicates do not exist in the tree.

### Examples

**Example 1:**
```
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Tree structure:
      3
     / \
    9  20
      /  \
     15   7
```

**Example 2:**
```
Input: preorder = [-1], inorder = [-1]
Output: [-1]
```

**Constraints:**
- 1 <= preorder.length <= 3000
- inorder.length == preorder.length
- -3000 <= preorder[i], inorder[i] <= 3000
- preorder and inorder consist of unique values
- Each value of inorder also appears in preorder
- preorder is guaranteed to be the preorder traversal of the tree
- inorder is guaranteed to be the inorder traversal of the tree

## Key Insights

1. **Preorder Property**: The first element in preorder traversal is always the root
2. **Inorder Property**: Elements to the left of root in inorder are in left subtree, elements to the right are in right subtree
3. **Recursive Structure**: Once we identify the root and split left/right, we can recursively construct subtrees
4. **Index Mapping**: Using a HashMap for inorder indices provides O(1) lookup instead of O(n) search
5. **Index Calculation**: The tricky part is calculating the correct preorder index for the right subtree root

## Algorithm Steps

1. **Build Index Map**: Create a HashMap to store value -> index mapping for inorder array
2. **Recursive Construction**:
   - Base case: If preorderIndex is out of bounds or inorderStart > inorderEnd, return null
   - Create root node from preorder[preorderIndex]
   - Find root's position in inorder using the map
   - Calculate the preorder index for right subtree:
     - Left subtree size = inorderIndexRoot - inorderStart
     - Right subtree root index = preorderIndex + 1 + leftSubtreeSize
   - Recursively build left subtree (preorderIndex + 1, inorderStart to inorderIndexRoot - 1)
   - Recursively build right subtree (rightRootIndex, inorderIndexRoot + 1 to inorderEnd)
3. **Return** the constructed root

## Complexity Analysis

- **Time Complexity**: O(n)
  - Build HashMap: O(n)
  - Each node is visited once during recursion: O(n)
  - Lookup in HashMap: O(1) per node
  - Overall: O(n)

- **Space Complexity**: O(n)
  - HashMap storage: O(n)
  - Recursion call stack: O(h) where h is height, worst case O(n)
  - Overall: O(n)

## Visual Explanation

### Example: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]

```
Step 1: Root is 3 (first in preorder)
        Find 3 in inorder at index 1

        Inorder: [9, | 3 |, 15, 20, 7]
                  ^    ^    ^
                left  root  right

Step 2: Left subtree has elements [9]
        Right subtree has elements [15, 20, 7]

        preorder for left:  [9]
        preorder for right: [20, 15, 7]

Step 3: Build left subtree
        Root = 9 (no children)

             3
            /
           9

Step 4: Build right subtree recursively
        Root = 20
        inorder: [15, 20, 7]
        Left of 20: [15]
        Right of 20: [7]

             3
            / \
           9  20
             /  \
            15   7
```

### Index Calculation Example

```
For preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]

Current: Processing root = 3
- preorderIndex = 0
- inorderIndexRoot = 1 (position of 3 in inorder)
- inorderStart = 0
- inorderEnd = 4

Left subtree:
- preorderIndex for left = preorderIndex + 1 = 1
- inorder range: [0, 0] (just element 9)

Right subtree:
- leftSubtreeSize = inorderIndexRoot - inorderStart = 1 - 0 = 1
- preorderIndex for right = preorderIndex + 1 + leftSubtreeSize
                           = 0 + 1 + 1 = 2
- inorder range: [2, 4] (elements [15, 20, 7])

Formula breakdown:
rightRootIndex = inorderIndexRoot - 1 - inorderStart + 2 + preorderIndex
               = (inorderIndexRoot - inorderStart - 1) + preorderIndex + 2
               = leftSubtreeSize + preorderIndex + 1
```

## Code Walkthrough

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    // Step 1: Build HashMap for O(1) lookup of inorder indices
    HashMap<Integer, Integer> inorderMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inorderMap.put(inorder[i], i);
    }

    // Step 2: Start recursive construction
    return buildTreeHelper(preorder, inorder, 0, 0,
                          preorder.length - 1, inorderMap);
}

private TreeNode buildTreeHelper(int[] preorder, int[] inorder,
                                 int preorderIndex, int inorderStart,
                                 int inorderEnd, HashMap<Integer, Integer> inorderMap) {
    // Base case: invalid range
    if (preorderIndex >= preorder.length || inorderStart > inorderEnd)
        return null;

    // Step 3: Create root from preorder
    TreeNode root = new TreeNode(preorder[preorderIndex]);

    // Step 4: Find root position in inorder
    int inorderIndexRoot = inorderMap.get(preorder[preorderIndex]);

    // Step 5: Calculate right subtree root index
    // Formula: preorderIndex + 1 + (size of left subtree)
    int rightRootIndex = inorderIndexRoot - 1 - inorderStart + 2 + preorderIndex;

    // Step 6: Build left subtree
    root.left = buildTreeHelper(preorder, inorder, preorderIndex + 1,
                                inorderStart, inorderIndexRoot - 1, inorderMap);

    // Step 7: Build right subtree
    root.right = buildTreeHelper(preorder, inorder, rightRootIndex,
                                 inorderIndexRoot + 1, inorderEnd, inorderMap);

    return root;
}
```

## Edge Cases

1. **Single Node Tree**: preorder = [1], inorder = [1]
   - Should return a tree with just root node

2. **Only Left Subtree**: preorder = [3,2,1], inorder = [1,2,3]
   ```
       3
      /
     2
    /
   1
   ```

3. **Only Right Subtree**: preorder = [1,2,3], inorder = [1,2,3]
   ```
   1
    \
     2
      \
       3
   ```

4. **Balanced Tree**: preorder = [4,2,1,3,6,5,7], inorder = [1,2,3,4,5,6,7]
   ```
         4
        / \
       2   6
      / \ / \
     1  3 5  7
   ```

5. **Empty Tree**: Though not in constraints, handle null arrays

## Related Problems

1. **LeetCode 106**: Construct Binary Tree from Inorder and Postorder Traversal
2. **LeetCode 889**: Construct Binary Tree from Preorder and Postorder Traversal
3. **LeetCode 297**: Serialize and Deserialize Binary Tree
4. **LeetCode 449**: Serialize and Deserialize BST
5. **LeetCode 1008**: Construct Binary Search Tree from Preorder Traversal

## Tags

- Tree
- Binary Tree
- Array
- Hash Table
- Divide and Conquer
- Recursion
- Tree Construction
