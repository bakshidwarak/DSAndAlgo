# LeetCode 339: Nested List Weight Sum

## Problem Statement
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list whose elements may also be integers or other lists.

## Examples

**Example 1:**
```
Input: [[1,1],2,[1,1]]
Output: 10
Explanation:
- Four 1's at depth 2, each contributes 1*2 = 2, total 8
- One 2 at depth 1, contributes 2*1 = 2
- Total: 8 + 2 = 10
```

**Example 2:**
```
Input: [1,[4,[6]]]
Output: 27
Explanation:
- One 1 at depth 1: 1*1 = 1
- One 4 at depth 2: 4*2 = 8
- One 6 at depth 3: 6*3 = 18
- Total: 1 + 8 + 18 = 27
```

## Key Insights

1. **Depth Tracking**: Must track current depth while traversing
2. **Recursive DFS**: Natural fit for nested structure exploration
3. **Weighted Sum**: Multiply each integer by its depth
4. **Depth from Root**: Depth starts at 1 for top-level elements

## Algorithm Steps

1. Create recursive helper function that takes depth parameter
2. For each element in the list:
   - If it's an integer: add `value * depth` to sum
   - If it's a list: recursively call with `depth + 1`
3. Return accumulated sum

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) - Visit each element once |
| **Space Complexity** | O(d) - Recursion depth d (tree height) |
| **Best Case** | O(n) - Linear traversal |
| **Worst Case** | O(n) - Linear traversal |

- **n** = total number of integers
- **d** = maximum depth of nesting

## ASCII Visualization

```
Structure: [[1,1],2,[1,1]]

Tree with depths:
       Depth 1
         |
      /  |  \
    [0]  2  [2]   <- Elements at depth 1
   /  \      / \
  1    1    1   1  <- Elements at depth 2 (depth+1)

Calculation:
Depth 1: 2 × 1 = 2
Depth 2: 1 × 2 = 2
Depth 2: 1 × 2 = 2
Depth 2: 1 × 2 = 2
Depth 2: 1 × 2 = 2
Total = 2 + 2 + 2 + 2 + 2 = 10

Tree: [1,[4,[6]]]

       Depth 1
         |
      /     \
    1       [1]    <- Elements at depth 1
           /   \
         4     [1] <- Element at depth 2
              /
            6      <- Element at depth 3

Calculation:
Depth 1: 1 × 1 = 1
Depth 2: 4 × 2 = 8
Depth 3: 6 × 3 = 18
Total = 1 + 8 + 18 = 27
```

## Code Walkthrough

```java
public int depthSum(List<NestedInteger> nestedList) {
    return depthSumHelper(nestedList, 1);  // Start at depth 1
}

private int depthSumHelper(List<NestedInteger> nestedList, int depth) {
    int sum = 0;

    for (NestedInteger item : nestedList) {
        if (item.isInteger()) {
            // Base case: multiply integer by current depth
            sum += item.getInteger() * depth;
        } else {
            // Recursive case: process nested list with increased depth
            sum += depthSumHelper(item.getList(), depth + 1);
        }
    }

    return sum;
}
```

**Execution Flow for [1,[4,[6]]]:**
```
depthSumHelper([1,[4,[6]]], 1)
  item=1 (integer): sum = 0 + 1*1 = 1
  item=[4,[6]] (list): sum = 1 + depthSumHelper([4,[6]], 2)
    depthSumHelper([4,[6]], 2)
      item=4 (integer): sum = 0 + 4*2 = 8
      item=[6] (list): sum = 8 + depthSumHelper([6], 3)
        depthSumHelper([6], 3)
          item=6 (integer): sum = 0 + 6*3 = 18
          return 18
        return 8 + 18 = 26
      return 26
    return 1 + 26 = 27
```

## Edge Cases

1. **Single integer**: `[1]` → 1*1 = 1
2. **Single list**: `[[1]]` → 1*2 = 2
3. **Empty list**: `[]` → 0
4. **Empty nested lists**: `[[], []]` → 0
5. **Deeply nested**: `[[[1]]]` → 1*3 = 3
6. **Negative numbers**: `[[-1]]` → -1*2 = -2
7. **Mixed integers and lists**: `[1, [2], 3]` → 1 + 4 + 3 = 8

## Related Problems

1. **LeetCode 364** - Nested List Weight Sum II (Weight reversed by depth)
2. **LeetCode 341** - Flatten Nested List Iterator (Structure, no weights)
3. **LeetCode 385** - Mini Parser (Parse nested structure)
4. **LeetCode 690** - Employee Importance (Graph traversal with depth)
5. **LeetCode 559** - Maximum Depth of N-ary Tree (Find max depth)

## Tags

`DFS` `Recursion` `Nested List` `Easy` `Google` `Facebook` `Uber`

## Alternative Approaches

### Approach 2: BFS with Queue
```java
public int depthSumBFS(List<NestedInteger> nestedList) {
    Queue<Pair<NestedInteger, Integer>> queue = new LinkedList<>();
    int sum = 0;

    for (NestedInteger item : nestedList) {
        queue.offer(new Pair<>(item, 1));
    }

    while (!queue.isEmpty()) {
        Pair<NestedInteger, Integer> pair = queue.poll();
        NestedInteger item = pair.getKey();
        int depth = pair.getValue();

        if (item.isInteger()) {
            sum += item.getInteger() * depth;
        } else {
            for (NestedInteger ni : item.getList()) {
                queue.offer(new Pair<>(ni, depth + 1));
            }
        }
    }

    return sum;
}
```

**Time**: O(n), **Space**: O(n) for queue

## Notes

- DFS is more intuitive for this problem
- Depth parameter is passed down through recursion
- Start depth at 1 (not 0) as per problem definition
- This is a classic DFS on nested/tree structures
- Key difference from LeetCode 364 is depth direction
