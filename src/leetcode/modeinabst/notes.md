# LeetCode 501: Find Mode in Binary Search Tree

## Problem Statement
Given a **Binary Search Tree (BST)** with duplicates, find all **modes** (most frequently occurring elements) in the BST.

## Difficulty
Easy

## Examples

### Example 1
```
BST: 1
      \
       2
      /
     2

Output: [2]
Explanation: 2 appears twice, 1 appears once. Mode is 2.
```

### Example 2
```
BST:   1
      / \
     1   2

Output: [1]
or [1, 2]
Explanation: 1 and 2 both appear twice. Both are modes.
```

## Key Insights
1. **In-Order Traversal**: BST in-order traversal produces sorted sequence
2. **Frequency Counting**: Count consecutive equal elements
3. **Hash Map Storage**: Store elements grouped by their frequency
4. **Find Maximum Frequency**: Find the maximum count
5. **Return All with Max Frequency**: Return all elements with highest frequency

## Algorithm Steps
1. Perform in-order traversal to get sorted array
2. Count frequency of each element (consecutive elements)
3. Store elements and their frequencies in HashMap
4. Find maximum frequency
5. Retrieve all elements with maximum frequency
6. Return as array

## Complexity Analysis
- **Time Complexity:** O(n) - In-order traversal is O(n), counting is O(n)
- **Space Complexity:** O(n) - HashMap and result array

## ASCII Visualization

```
BST:      1
         / \
        1   2
       /
      1
       \
        3

In-order traversal: [1, 1, 1, 2, 3]

Frequency count:
1: count = 3
2: count = 1
3: count = 1

Maximum frequency = 3
Mode = [1]

Another example:
BST:    5
       / \
      3   6
     /     \
    2       6

In-order: [2, 3, 5, 6, 6]

Frequency:
2: 1
3: 1
5: 1
6: 2

Maximum = 2
Mode = [6]
```

## Edge Cases
1. **Single element tree:** Return that element
2. **All elements same:** Return that element
3. **All elements different:** Return all elements
4. **Two elements with same frequency:** Return both
5. **Large tree:** Works efficiently with O(n) traversal

## Related Problems
- **LeetCode 230:** Kth Smallest Element in a BST
- **LeetCode 235:** Lowest Common Ancestor of a BST
- **LeetCode 270:** Closest Binary Search Tree Value
- **LeetCode 99:** Recover Binary Search Tree

## Tags
`Tree` `Binary Search Tree` `In-order Traversal` `Hash Map`
