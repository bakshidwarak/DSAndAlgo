# LeetCode 112: Path Sum

## Problem Statement
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

## Examples

**Example 1:**
```
Tree:
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1

Target: 22
Output: true

Path: 5 → 4 → 11 → 2 = 22
```

**Example 2:**
```
Tree:
    1
   / \
  2   3

Target: 5
Output: false

Paths: 1→2=3, 1→3=4 (neither equals 5)
```

## Key Insights

1. **Root-to-Leaf**: Path must start at root and end at leaf node
2. **Leaf Identification**: Node with no left AND no right child
3. **Recursive Decomposition**: Can break into subproblems
4. **Remaining Sum**: For each node, reduce target by current value

## Algorithm Steps

1. **Base Cases**:
   - If root is null: return false (no path)
   - If leaf node found and value == sum: return true
   - If leaf node found and value != sum: return false

2. **Recursive Case**:
   - Check left subtree with remaining sum (sum - root.val)
   - Check right subtree with remaining sum (sum - root.val)
   - Return true if either subtree has valid path

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) - Visit each node once |
| **Space Complexity** | O(h) - Recursion depth, h = height |

- n = number of nodes
- h = height (O(log n) for balanced, O(n) for skewed)
- Best case: O(log n) if tree is balanced
- Worst case: O(n) if tree is skewed (linked list)

## ASCII Visualization

```
Tree:
        5
       / \
      4   8
     /   / \
    11  13  4
   / \      \
  7   2      1

Target: 22

Path checking:
Root (5): 5, target remaining: 22-5=17
├─ Left (4): 5+4=9, target remaining: 17-4=13
│  ├─ Left (11): 9+11=20, target remaining: 13-11=2
│  │  ├─ Left (7): 20+7=27 > 22, not valid path
│  │  └─ Right (2): 20+2=22 ✓ FOUND!
│  └─ Right (null)
└─ Right (8): 5+8=13, target remaining: 17-8=9
   ├─ Left (13): 13+13=26 > 22, not valid
   └─ Right (4): 13+4=17, target remaining: 9-4=5
      └─ Right (1): 17+1=18 != 22, not valid

Result: true (found path 5→4→11→2)

Recursion tree:
hasPathSum(5, 22)
├─ hasPathSum(4, 17)  [5+4=9, sum-5=17]
│  ├─ hasPathSum(11, 13)  [9+11=20, sum-9=13]
│  │  ├─ hasPathSum(7, 2)  [20+7=27, sum-20=2]
│  │  │  └─ false (7 is leaf, 27≠22)
│  │  └─ hasPathSum(2, 2)  [20+2=22, sum-20=2]
│  │     └─ true (2 is leaf, 22==22) ✓
│  └─ false
├─ OR
└─ hasPathSum(8, 9)  [5+8=13, sum-5=9]
   ├─ hasPathSum(13, 9) → false
   └─ hasPathSum(4, 5)
      └─ hasPathSum(1, 5) → false
```

## Code Walkthrough

```java
public boolean hasPathSum(TreeNode root, int sum) {
    // Base case: no tree
    if (root == null)
        return false;

    // Check if leaf node
    if (root.left == null && root.right == null) {
        // Leaf: check if value matches target
        return root.val == sum;
    }

    // Recursive case: check both subtrees with remaining sum
    return hasPathSum(root.left, sum - root.val) ||
           hasPathSum(root.right, sum - root.val);
}
```

**Execution Flow for Example 1:**

```
hasPathSum(Node(5), 22):
  root != null ✓
  Not a leaf (left=4, right=8) ✓
  Call hasPathSum(Node(4), 22-5=17) OR hasPathSum(Node(8), 17)

  hasPathSum(Node(4), 17):
    root != null ✓
    Not a leaf (left=11, right=null) ✓
    Call hasPathSum(Node(11), 17-4=13) OR hasPathSum(null, 13)

    hasPathSum(Node(11), 13):
      root != null ✓
      Not a leaf (left=7, right=2) ✓
      Call hasPathSum(Node(7), 13-11=2) OR hasPathSum(Node(2), 2)

      hasPathSum(Node(7), 2):
        root != null ✓
        Leaf node (no children) ✓
        7 == 2? No
        Return false

      hasPathSum(Node(2), 2):
        root != null ✓
        Leaf node (no children) ✓
        2 == 2? Yes ✓
        Return true ✓

    Return true OR false = true ✓

  Return true OR ... = true ✓
```

## Edge Cases

1. **Empty tree**: `null` → false
2. **Single node**:
   - `[1], sum=1` → true
   - `[1], sum=2` → false
3. **Negative values**: `-1 + 3 + (-2) = 0` → works
4. **All left path**: `1->2->3` (no right children)
5. **All right path**: `1->2->3` (no left children)
6. **Negative sum**: Works for any integer sum

## Related Problems

1. **LeetCode 113** - Path Sum II (Return all paths, not just boolean)
2. **LeetCode 437** - Path Sum III (Any node to any node, not root-to-leaf)
3. **LeetCode 124** - Binary Tree Maximum Path Sum (Max path value)
4. **LeetCode 129** - Sum Root to Leaf Numbers (Treat path as number)
5. **LeetCode 257** - Binary Tree Paths (Return all paths as strings)

## Tags

`Tree` `DFS` `Recursion` `Easy` `Google` `Amazon` `Apple` `Microsoft` `Facebook`

## Alternative Approaches

### Approach 2: Iterative DFS with Stack
```java
public boolean hasPathSumIterative(TreeNode root, int sum) {
    if (root == null)
        return false;

    Stack<TreeNode> nodeStack = new Stack<>();
    Stack<Integer> sumStack = new Stack<>();

    nodeStack.push(root);
    sumStack.push(sum - root.val);

    while (!nodeStack.isEmpty()) {
        TreeNode node = nodeStack.pop();
        int currSum = sumStack.pop();

        if (node.left == null && node.right == null && currSum == 0) {
            return true;
        }

        if (node.left != null) {
            nodeStack.push(node.left);
            sumStack.push(currSum - node.left.val);
        }

        if (node.right != null) {
            nodeStack.push(node.right);
            sumStack.push(currSum - node.right.val);
        }
    }

    return false;
}
```
- Time: O(n), Space: O(h) for stack

### Approach 3: BFS with Queue
```
- Similar to iterative DFS but uses queue
- Level-order traversal instead of depth-first
- Same complexity
```

## Implementation Notes

1. **Leaf Check**: `left == null && right == null`
2. **Remaining Sum**: Always subtract current node's value
3. **Short-Circuit**: OR operator stops at first true
4. **Base Cases**: Handle null and leaf separately

## Common Mistakes

1. **Leaf Definition**: Must have NO children (not just one)
2. **Null Handling**: Must check null before accessing children
3. **Remaining Sum**: Must pass `sum - root.val` to children
4. **Exact Match**: Path sum must EQUAL target, not just part of it

## Optimization Notes

1. **Early Termination**: Negative sums can't reach positive targets (with all positive values)
2. **Pruning**: Skip branches with impossible sums
3. **Memoization**: Can cache (node, sum) pairs but may not help much

## Performance Comparison

```
Approach      Time    Space   Notes
Recursive     O(n)    O(h)    Clean, intuitive
Iterative DFS O(n)    O(h)    Avoids recursion stack
BFS           O(n)    O(w)    w = max width
```

## Notes

- Classic tree traversal problem
- Foundation for understanding DFS on trees
- Simple recursive structure is elegant
- Tests understanding of leaf nodes and recursion
- Great interview problem for tree traversal
