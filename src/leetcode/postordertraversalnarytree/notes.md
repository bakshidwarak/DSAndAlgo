# N-ary Tree Postorder Traversal - LeetCode Problem 590

## Problem Statement
Given an n-ary tree, return the postorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.

## Examples

**Example 1:**
```
      1
    / | \
   3  2  4
  / \
 5   6
```
- Output: `[5,6,3,2,4,1]`
- Explanation: Postorder means: visit all children first, then visit the node itself

**Example 2:**
```
        1
       /||\\\
      2 3 4 5
```
- Output: `[2,3,4,5,1]`

## Key Insights
1. **Postorder Definition**: Left-Right-Root or in n-ary trees, Children-Parent
2. **Recursive vs Iterative**: Problem suggests finding iterative solution instead of trivial recursive
3. **Stack Usage**: Can use stack to simulate recursion (iterative approach)
4. **Reversal Trick**: In iterative approach, use preorder (Parent-Children) then reverse to get postorder

## Algorithm Steps

### Approach 1: Recursive (Trivial)
1. Process all children recursively
2. Add current node's value to result

### Approach 2: Iterative (Stack-based)
1. Use a stack and push root node
2. While stack is not empty:
   - Pop node
   - Add value to result
   - Push all children to stack
3. Reverse the result list

**Pseudocode (Iterative):**
```
function postorder(root):
    if root is null: return empty list

    stack = new Stack()
    result = new List()

    stack.push(root)
    while stack is not empty:
        node = stack.pop()
        result.add(node.val)
        for each child in node.children:
            if child is not null:
                stack.push(child)

    Collections.reverse(result)
    return result
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n is the number of nodes |
| Space Complexity | O(h) where h is height (recursion stack) or O(n) for iterative stack |

**Time Analysis:**
- Must visit each node exactly once: O(n)
- Reversal at end: O(n)
- Total: O(n)

## ASCII Visualization

```
Postorder Traversal Order:

Tree:
      1
    / | \
   3  2  4
  / \
 5   6

Recursive approach:
visit(1)
  → visit(3)
     → visit(5) → output 5
     → visit(6) → output 6
     → output 3
  → visit(2) → output 2
  → visit(4) → output 4
  → output 1

Result: [5, 6, 3, 2, 4, 1]

Iterative approach (reverse preorder):
Stack: [1]
Pop 1, add to result: [1], push children: [3,2,4]
Pop 4, add to result: [1,4], push children: []
Pop 2, add to result: [1,4,2], push children: []
Pop 3, add to result: [1,4,2,3], push children: [5,6]
Pop 6, add to result: [1,4,2,3,6], push children: []
Pop 5, add to result: [1,4,2,3,6,5], push children: []

Reverse: [5,6,3,2,4,1]
```

## Code Walkthrough

```java
// Iterative approach using stack
public List<Integer> postorderIterative(Node root) {
    Stack<Node> stack = new Stack<>();
    List<Integer> result = new ArrayList<>();

    if (root != null)
        stack.push(root);

    // Process preorder (Parent-Children)
    while (!stack.isEmpty()) {
        Node node = stack.pop();
        result.add(node.val);  // Add parent first

        // Push children (order matters for final reversal)
        for (Node child : node.children) {
            if (child != null)
                stack.push(child);
        }
    }

    // Reverse to convert preorder to postorder
    Collections.reverse(result);
    return result;
}

// Recursive approach (trivial)
public void postOrderHelper(Node root, List<Integer> result) {
    if (root == null)
        return;

    // Process children first
    for (Node child : root.children)
        postOrderHelper(child, result);

    // Then add current node
    result.add(root.val);
}
```

## Edge Cases

1. **Single Node**: `Node(1)` -> `[1]`
2. **Empty Tree**: `null` -> `[]`
3. **Deep Tree**: Long chain of single children
4. **Wide Tree**: One node with many children
5. **Complex Structure**: Mix of deep and wide branches
6. **Large Tree**: 1000+ nodes

## Related Problems

1. **LeetCode 589**: N-ary Tree Preorder Traversal - Parent-Children-Siblings order
2. **LeetCode 144**: Binary Tree Preorder Traversal - Similar concept for binary trees
3. **LeetCode 145**: Binary Tree Postorder Traversal - Similar for binary trees
4. **LeetCode 102**: Binary Tree Level Order Traversal - BFS approach
5. **LeetCode 107**: Binary Tree Level Order Traversal II - Level order reversed
## Tags

- Tree
- DFS/Recursion
- Stack
- Depth-First Search
- N-ary Tree
- Difficulty: Easy
- Acceptance: ~70%
