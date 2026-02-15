# LeetCode 85: Maximal Rectangle

## Problem Statement
Given a 2D binary matrix filled with 0s and 1s, find the **largest rectangle** containing only 1s and return its **area**.

## Difficulty
Hard

## Examples

### Example 1
```
Input:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 6 (Rectangle of width 3 and height 2)
```

## Key Insights
1. **Convert to Histogram Problem**: Treat each row as the base of a histogram where heights represent consecutive 1s
2. **Largest Rectangle in Histogram**: For each row, calculate heights and find largest rectangle in that histogram
3. **Height Updates**: When encountering 1, increment height; when 0, reset height to 0
4. **Stack-based Histogram Solution**: Use monotonic stack to efficiently find largest rectangle in histogram

## Algorithm Steps
1. Create a heights array where heights[i] = consecutive 1s above including current row
2. For each row:
   - Update heights array (increment if 1, reset if 0)
   - Find largest rectangle in histogram using stack
3. Return maximum area found

## Complexity Analysis
- **Time Complexity:** O(m*n) where m=rows, n=columns
- **Space Complexity:** O(n) for heights array and stack

## ASCII Visualization

```
Matrix:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Heights after each row:
Row 0: [1, 0, 1, 0, 0]
Row 1: [2, 0, 2, 1, 1]
Row 2: [3, 1, 3, 2, 2] → Largest rectangle area = 6
Row 3: [4, 0, 0, 3, 0]

At row 2, heights [3, 1, 3, 2, 2]:
Can form 3x2 rectangle = area 6
```

## Edge Cases
1. **No 1s in matrix:** Return 0
2. **All 1s in matrix:** Return m*n
3. **Single row:** Find longest consecutive 1s
4. **Single column:** Count total 1s
5. **Only one 1:** Return 1

## Related Problems
- **LeetCode 84:** Largest Rectangle in Histogram
- **LeetCode 221:** Maximal Square
- **LeetCode 1504:** Count Submatrices With All Ones

## Tags
`Dynamic Programming` `Stack` `Histogram` `Matrix`
