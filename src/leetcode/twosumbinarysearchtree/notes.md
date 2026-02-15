# LeetCode 653: Two Sum IV - Input is a BST

## Problem Statement
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

**Constraints:**
- Binary Search Tree structure
- Must find exactly two different elements
- Elements cannot be the same node (if value appears twice, must use both instances)

## Examples

### Example 1
```
      5
     / \
    3   6
   / \   \
  2   4   7

Target = 9
Output: True
Explanation: 2 + 7 = 9
```

### Example 2
```
      5
     / \
    3   6
   / \   \
  2   4   7

Target = 28
Output: False
```

## Key Insights

1. **Sorted Property:** Inorder traversal of a BST gives sorted array
2. **Reduction to Two Sum II:** Convert BST traversal to sorted array problem
3. **Two-Pointer Technique:** Use sorted array with two pointers (left and right)
4. **No Extra Hash Table:** BST structure provides ordering for free
5. **Time vs Space Trade-off:** O(n) time with O(n) space for the array

## Algorithm Steps

### Approach: Inorder Traversal + Two Pointers

```
1. Perform inorder traversal of BST
   - Store all values in a list (will be sorted)
2. Use two-pointer technique on the sorted list:
   a. Initialize left pointer at start (i = 0)
   b. Initialize right pointer at end (j = list.size() - 1)
   c. While i < j:
      - Calculate sum = list[i] + list[j]
      - If sum equals target, return true
      - If sum > target, decrement j
      - If sum < target, increment i
3. Return false if no pair found
```

## Complexity Analysis

- **Time Complexity:** O(n) - Inorder traversal is O(n) + Two-pointer pass is O(n)
- **Space Complexity:** O(n) - Storage for inorder traversal array + O(h) recursion stack (h = height)

## ASCII Visualization

```
BST Structure:
        5
       / \
      3   6
     / \   \
    2   4   7

Inorder Traversal: [2, 3, 4, 5, 6, 7]

Two-Pointer on Target = 9:
  i=0      j=5
  [2, 3, 4, 5, 6, 7]
   ^            ^
  sum = 2 + 7 = 9 ✓ FOUND!

Two-Pointer on Target = 28:
  i=0      j=5
  [2, 3, 4, 5, 6, 7]
   ^            ^
  sum = 2 + 7 = 9 < 28, move i right

  i=1      j=5
  sum = 3 + 7 = 10 < 28, move i right

  i=2      j=5
  sum = 4 + 7 = 11 < 28, move i right

  ... eventually i >= j, return false
```

## Code Walkthrough

```java
public boolean findTarget(TreeNode root, int k) {
    List<Integer> inorder = new ArrayList<>();
    inorderTraversal(root, inorder);        // Step 1: Get sorted values

    int i = 0;
    int j = inorder.size() - 1;

    // Step 2: Two-pointer approach
    for (; i < j;) {
        int sum = inorder.get(i) + inorder.get(j);
        if (sum == k) {
            return true;
        }
        if (sum < k) {
            i++;                            // Need larger sum
        } else {
            j--;                            // Need smaller sum
        }
    }
    return false;
}

// Helper: Inorder traversal (Left, Root, Right)
public void inorderTraversal(TreeNode root, List<Integer> inorder) {
    if (root == null)
        return;
    inorderTraversal(root.left, inorder);
    inorder.add(root.val);                  // Add in sorted order
    inorderTraversal(root.right, inorder);
}
```

**Execution Flow:**
1. Recursively traverse left subtree
2. Add current node value to list
3. Recursively traverse right subtree
4. Result is sorted due to BST property
5. Use two-pointer technique on sorted list

## Edge Cases

1. **Single Node:** Single node BST cannot have two elements (but problem requires two)
2. **Two Nodes Only:** Works correctly
3. **Balanced vs Skewed:** Algorithm works regardless of BST shape
4. **Negative Values:** Works with negative values in BST
5. **Duplicate Values in Result:** If two equal numbers sum to target, works if both exist
6. **Target = Sum of Node with Itself:** Requires two different nodes, so doesn't count self

### Example Edge Cases:
- `root = [5,3,6,2,4,null,7]`, `target = 9` → true (2 + 7)
- `root = [2,1,3]`, `target = 6` → false (only 1+2=3 and 1+3=4 and 2+3=5 available)

## Related Problems

1. **LeetCode 167 - Two Sum II:** Same logic but input is sorted array
2. **LeetCode 1 - Two Sum:** Two Sum with unsorted array
3. **LeetCode 3Sum:** Find three numbers with target sum
4. **LeetCode 94 - Binary Tree Inorder Traversal:** Tree traversal technique
5. **LeetCode 700 - Search in BST:** BST search operations
## Alternative Approach

**Using HashSet with Single Pass (Space-Time Trade-off):**
```
1. Do inorder traversal
2. For each value, check if (target - value) exists in HashSet
3. Add current value to HashSet
4. Time: O(n), Space: O(n)
```

## Tags

`#Binary-Search-Tree` `#Two-Pointers` `#Tree` `#DFS` `#Inorder-Traversal` `#Easy`

## Key Takeaways

- Inorder traversal of BST produces sorted array
- This converts problem to sorted Two Sum II
- Two-pointer technique is optimal for sorted data
- BST's sorted property eliminates need for explicit sorting
- Inorder traversal order is crucial (not pre/post order)
