# LeetCode 364: Nested List Weight Sum II

## Problem Statement
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

**Key Difference from LeetCode 339**: The weight is defined from bottom-up. Leaf level integers have weight 1, and the root level integers have the largest weight.

## Examples

**Example 1:**
```
Input: [[1,1],2,[1,1]]
Output: 8
Explanation:
- Four 1's at max depth - 1 (leaf level): 1*1 = 1 each, total 4
- One 2 at max depth - 2: 2*2 = 4
- Total: 4 + 4 = 8
```

**Example 2:**
```
Input: [1,[4,[6]]]
Output: 17
Explanation:
- One 1 at depth 3 (max depth - 0): 1*3 = 3
- One 4 at depth 2 (max depth - 1): 4*2 = 8
- One 6 at depth 1 (max depth - 2, leaf): 6*1 = 6
- Total: 3 + 8 + 6 = 17
```

## Key Insights

1. **Reversed Depth Weighting**: Leaves have weight 1, root has max weight
2. **Two-Pass Approach**: First find max depth, then calculate weighted sum
3. **Map by Level**: Store elements at each level, multiply by (maxDepth - currentLevel + 1)
4. **DFS with Map**: Track depth and collect elements level-wise

## Algorithm Steps

### Approach 1: DFS with HashMap

1. First pass - DFS to find all elements and their depths
2. Store elements in HashMap with depth as key
3. Calculate max depth during traversal
4. Second pass - iterate through map levels
5. For each level, multiply sum by (maxDepth - level + 1)

### Approach 2: Two DFS

1. First DFS: Find max depth
2. Second DFS: Calculate weighted sum using max depth

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) - Two DFS passes |
| **Space Complexity** | O(n) - HashMap stores all elements |

- **n** = total number of integers
- First pass finds depths, second multiplies and sums

## ASCII Visualization

```
Structure: [[1,1],2,[1,1]]
Max Depth = 2

Level mapping:
Level 0: [2]         (depth 1)
Level 1: [1, 1, 1, 1] (depth 2)

Weight Calculation (Bottom-up):
Level 0 (depth from bottom = 2): 2 × 2 = 4
Level 1 (depth from bottom = 1): 1×1 + 1×1 + 1×1 + 1×1 = 4
Total = 4 + 4 = 8

Structure: [1,[4,[6]]]
Max Depth = 3

Level mapping:
Level 0: [1]    (depth 1)
Level 1: [4]    (depth 2)
Level 2: [6]    (depth 3)

Weight Calculation (Bottom-up):
Level 0 (weight = 3): 1 × 3 = 3
Level 1 (weight = 2): 4 × 2 = 8
Level 2 (weight = 1): 6 × 1 = 6
Total = 3 + 8 + 6 = 17

Tree visualization for [1,[4,[6]]]:
        Level 0
       /       \
      1        Level 1
              /     \
            4      Level 2
                   /
                  6

From bottom-up:
6 is at leaf (weight 1)
4 is one level up (weight 2)
1 is at root (weight 3)
```

## Code Walkthrough

```java
public int depthSumInverse(List<NestedInteger> nestedList) {
    int level = 0;
    Map<Integer, List<Integer>> result = new HashMap<>();

    // First pass: DFS to collect elements by level
    depthSumInverseHelper(nestedList, level, result);

    int sum = 0;
    int key = 0;

    // Second pass: Calculate weighted sum
    // result.size() = maxDepth
    while (result.containsKey(key)) {
        int minisum = 0;
        List<Integer> currentLevel = result.get(key);

        for (Integer num : currentLevel) {
            minisum += num;  // Sum all numbers at this level
        }

        // Weight = maxDepth - key
        sum += minisum * (result.size() - key);
        key++;
    }

    return sum;
}

private void depthSumInverseHelper(List<NestedInteger> nestedList,
                                    int level,
                                    Map<Integer, List<Integer>> result) {
    if (nestedList == null)
        return;

    // Ensure entry exists for current level
    if (!result.containsKey(level)) {
        result.put(level, new ArrayList<>());
    }

    for (NestedInteger element : nestedList) {
        if (element.isInteger()) {
            // Add integer to current level
            result.get(level).add(element.getInteger());
        } else {
            // Recursively process nested list at next level
            depthSumInverseHelper(element.getList(), level + 1, result);
        }
    }
}
```

**Execution Flow for [1,[4,[6]]]:**
```
depthSumInverseHelper([1,[4,[6]]], 0, map)
  result[0] = []
  item=1: result[0].add(1) → result[0] = [1]
  item=[4,[6]]: depthSumInverseHelper([4,[6]], 1, map)
    result[1] = []
    item=4: result[1].add(4) → result[1] = [4]
    item=[6]: depthSumInverseHelper([6], 2, map)
      result[2] = []
      item=6: result[2].add(6) → result[2] = [6]

Final map: {0: [1], 1: [4], 2: [6]}
maxDepth = 3

Calculate sum:
key=0: minisum=1, weight=3-0=3, sum += 1*3 = 3
key=1: minisum=4, weight=3-1=2, sum += 4*2 = 8
key=2: minisum=6, weight=3-2=1, sum += 6*1 = 6
Total = 3 + 8 + 6 = 17
```

## Edge Cases

1. **Single integer**: `[5]` → 5*1 = 5
2. **Single nested list**: `[[5]]` → 5*1 = 5
3. **Deeply nested**: `[[[[1]]]]` → 1*1 = 1
4. **Empty lists**: `[[], []]` → 0
5. **Mixed structure**: `[1, [2], [[3]]]` → 1*3 + 2*2 + 3*1 = 10
6. **Negative numbers**: `[[-1]]` → -1*1 = -1

## Related Problems

1. **LeetCode 339** - Nested List Weight Sum (Weight top-down)
2. **LeetCode 341** - Flatten Nested List Iterator
3. **LeetCode 385** - Mini Parser
4. **LeetCode 559** - Maximum Depth of N-ary Tree
5. **LeetCode 662** - Maximum Width of Binary Tree

## Tags

`DFS` `HashMap` `Nested List` `Medium` `Google` `Facebook` `LinkedIn`

## Alternative Approaches

### Approach 2: Two DFS passes
```java
public int depthSumInverse2(List<NestedInteger> nestedList) {
    // Find max depth first
    int maxDepth = findMaxDepth(nestedList);

    // Calculate weighted sum
    return depthSum(nestedList, maxDepth);
}

private int findMaxDepth(List<NestedInteger> list) {
    int max = 1;
    for (NestedInteger item : list) {
        if (!item.isInteger()) {
            max = Math.max(max, 1 + findMaxDepth(item.getList()));
        }
    }
    return max;
}

private int depthSum(List<NestedInteger> list, int depth) {
    int sum = 0;
    for (NestedInteger item : list) {
        if (item.isInteger()) {
            sum += item.getInteger() * depth;
        } else {
            sum += depthSum(item.getList(), depth - 1);
        }
    }
    return sum;
}
```

### Approach 3: Single DFS with Level-wise Sum
```
- Track depth while processing
- For each level, calculate contribution
- Sum = sum of (element * (maxDepth - level + 1))
```

## Implementation Notes

1. **Map Construction**: Map keys represent levels (0 to maxDepth-1)
2. **Weight Formula**: weight = map.size() - key
3. **Level-wise Processing**: Process all elements at one level together
4. **HashMap Size**: Represents the maximum depth

## Notes

- The key difference from LeetCode 339 is the weight calculation direction
- In LeetCode 339: weight = depth (top-down)
- Here: weight = maxDepth - level + 1 (bottom-up)
- Both approaches require understanding nested structures
- HashMap approach makes the level-wise calculation clear
- Two-DFS approach is also elegant but requires more recursion calls
