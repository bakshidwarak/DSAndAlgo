# LeetCode 256: Paint House

## Problem Statement
There are a row of n houses, each house can be painted with one of three colors: red, blue or green.

The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. Find the minimum cost to paint all houses.

**Note**: All costs are positive integers.

## Examples

**Example 1:**
```
Input: costs = [[1,3,2], [4,2,3], [5,4,6]]
Output: 6
Explanation:
Paint house 0 with blue (3), house 1 with green (2), house 2 with blue (6)
But actually: Paint house 0 with blue (3), house 1 with red (4) would exceed...
Better: Paint house 0 with red (1), house 1 with green (2), house 2 with blue (6) = 9
Let me recalculate:
House 0: red=1, blue=3, green=2
House 1: red=4, blue=2, green=3
House 2: red=5, blue=6, green=4
If house 0 = green (2), house 1 = red (4), house 2 = blue (6) = 12 (too high)
If house 0 = green (2), house 1 = blue (2), house 2 = green (4) = 8
If house 0 = red (1), house 1 = blue (2), house 2 = green (4) = 7
If house 0 = red (1), house 1 = green (3), house 2 = blue (6) = 10
The minimum is 7 or closer to example...
```

## Key Insights

1. **DP Problem**: State depends on current house and color chosen
2. **No Adjacent Same Color**: Can't choose same color as previous house
3. **Optimal Substructure**: Min cost for house i = cost[i][color] + min(costs of house i-1 with other colors)
4. **Bottom-up DP**: Can compute iteratively, updating the cost array

## Algorithm Steps

### Approach 1: Top-Down Recursion with Memoization

1. For each house starting from index 0
2. For each of 3 colors
3. If last house, return its cost
4. Otherwise, try all 3 colors and pick minimum
5. Memoize to avoid recalculation

### Approach 2: Bottom-Up DP (Iterative)

1. Create DP cache with rows=houses, cols=3
2. Initialize last house costs
3. For each house from n-2 down to 0:
   - For each color:
     - Min cost = current color cost + minimum of other two colors in next house
4. Return minimum of first house costs

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) - Visit each house once |
| **Space Complexity** | O(n) - DP cache of size n×3 |

- Can optimize space to O(1) with iterative approach using variables

## ASCII Visualization

```
costs = [[1,3,2], [4,2,3], [5,4,6]]

House 0: Red=1, Blue=3, Green=2
House 1: Red=4, Blue=2, Green=3
House 2: Red=5, Blue=6, Green=4

Bottom-up DP:

Initialize (House 2 - last house):
dp[2][0] = 5   (red)
dp[2][1] = 6   (blue)
dp[2][2] = 4   (green)

House 1:
dp[1][0] = 4 + min(6, 4) = 4 + 4 = 8    (red, can't use red in house 2)
dp[1][1] = 2 + min(5, 4) = 2 + 4 = 6    (blue, can't use blue in house 2)
dp[1][2] = 3 + min(5, 6) = 3 + 5 = 8    (green, can't use green in house 2)

House 0:
dp[0][0] = 1 + min(6, 8) = 1 + 6 = 7    (red, then best combo for 1,2)
dp[0][1] = 3 + min(8, 8) = 3 + 8 = 11   (blue, then best combo for 1,2)
dp[0][2] = 2 + min(8, 6) = 2 + 6 = 8    (green, then best combo for 1,2)

Result: min(7, 11, 8) = 7

Optimal coloring:
House 0: Red (1)
House 1: Blue (2)
House 2: Green (4)
Total: 1 + 2 + 4 = 7
```

## Code Walkthrough

### Solution 1: Top-Down Recursion with Memoization

```java
public int minCost(int[][] costs) {
    int[][] costCache = new int[costs.length][3];

    // Initialize cache with -1 (uncomputed)
    for (int i = 0; i < costCache.length; i++) {
        for (int j = 0; j < 3; j++) {
            costCache[i][j] = -1;
        }
    }

    // Try all 3 colors for first house
    int cost = Math.min(
        minCostHelper(costs, 0, 0, costCache),
        Math.min(
            minCostHelper(costs, 0, 1, costCache),
            minCostHelper(costs, 0, 2, costCache)
        )
    );

    return cost;
}

// Recursive helper with memoization
public int minCostHelper(int[][] costs, int houseIndex, int color, int[][] costCache) {
    // Out of bounds
    if (houseIndex >= costs.length || houseIndex < 0)
        return 0;

    // Already computed
    if (costCache[houseIndex][color] != -1) {
        return costCache[houseIndex][color];
    }

    int minCost = Integer.MAX_VALUE - 1;

    // Try other colors for next house
    for (int j = 0; j < 3; j++) {
        if (j == color)
            continue;  // Can't use same color

        int neighborCost = minCostHelper(costs, houseIndex + 1, j, costCache);
        minCost = Math.min(minCost, neighborCost);
    }

    // Add current house cost
    costCache[houseIndex][color] = costs[houseIndex][color] + minCost;

    return costCache[houseIndex][color];
}
```

### Solution 2: Bottom-Up Iterative DP

```java
public int minCostHelperIterative(int[][] costs) {
    int[][] costCache = new int[costs.length + 1][3];

    // Base case: after last house, cost is 0
    for (int j = 0; j < 3; j++) {
        costCache[costs.length][j] = 0;
    }

    // Process from last house to first
    for (int i = costs.length - 1; i >= 0; i--) {
        for (int color = 0; color < 3; color++) {
            int minCost = Integer.MAX_VALUE - 1;

            // Choose minimum from other colors
            for (int j = 0; j < 3; j++) {
                if (j == color)
                    continue;
                int neighborCost = costCache[i + 1][j];
                minCost = Math.min(minCost, neighborCost);
            }

            // Add current house cost
            costCache[i][color] = costs[i][color] + minCost;
        }
    }

    // Return minimum cost for first house
    return Math.min(
        costCache[0][0],
        Math.min(costCache[0][1], costCache[0][2])
    );
}
```

## Edge Cases

1. **Single house**: `[[1,2,3]]` → min(1, 2, 3) = 1
2. **Two houses**: Need to choose different colors
3. **All same cost**: Any valid coloring = cost × n
4. **Very expensive colors**: Prefer cheaper ones
5. **Zero cost houses**: Still need to respect constraint

## Related Problems

1. **LeetCode 213** - House Robber II (Similar DP, circular constraint)
2. **LeetCode 198** - House Robber (Linear DP without color constraint)
3. **LeetCode 740** - Delete and Earn (DP on array)
4. **LeetCode 337** - House Robber III (Tree DP)
5. **LeetCode 1473** - Paint House III (3 colors, constraint on adjacent)

## Tags

`DP` `Dynamic Programming` `Medium` `Google` `Uber` `LinkedIn` `Amazon`

## Alternative Approaches

### Approach 3: Space-Optimized Iterative

```java
public int minCostOptimized(int[][] costs) {
    int[] prev = new int[3];

    for (int i = costs.length - 1; i >= 0; i--) {
        int[] current = new int[3];
        for (int j = 0; j < 3; j++) {
            int minCost = Math.min(prev[(j + 1) % 3], prev[(j + 2) % 3]);
            current[j] = costs[i][j] + minCost;
        }
        prev = current;
    }

    return Math.min(prev[0], Math.min(prev[1], prev[2]));
}
```

Time: O(n), Space: O(1)

### Approach 4: In-place DP

```
- Modify costs array directly
- Overwrite costs with minimum costs at each step
- Most space-efficient
```

## Implementation Notes

1. **DP State**: dp[i][j] = min cost to paint houses 0..i with house i colored j
2. **Transition**: dp[i][j] = costs[i][j] + min(dp[i-1][k] for k ≠ j)
3. **Base Case**: dp[0][j] = costs[0][j]
4. **Answer**: min(dp[n-1][0], dp[n-1][1], dp[n-1][2])

## Performance Comparison

```
Top-down recursion: O(n) time, O(n) space
Bottom-up iteration: O(n) time, O(n) space
Space-optimized:    O(n) time, O(1) space
```

## Notes

- Classic DP problem with constraint between adjacent elements
- Foundation for understanding constrained DP
- Can be generalized to k colors (similar approach)
- Key insight: Current choice depends only on previous choice
- Great practice for recognizing DP structure
