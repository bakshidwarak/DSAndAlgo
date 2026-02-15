# LeetCode 453: Minimum Moves to Equal Array Elements

## Problem Statement
Given a non-empty integer array, find the **minimum number of moves** to make all elements equal, where a **move** increments n-1 elements by 1.

## Difficulty
Easy

## Examples

### Example 1
- **Input:** `[1, 2, 3]`
- **Output:** `3`
- **Explanation:** `[1,2,3]` → `[2,3,3]` → `[3,4,3]` → `[4,4,4]` (3 moves)

### Example 2
- **Input:** `[1, 0, 0, 8, 6]`
- **Output:** `14`

## Key Insights
1. **Inverse Perspective**: Incrementing n-1 elements is same as decrementing 1 element
2. **Mathematical Insight**: Target value = minimum element + moves
3. **Derivation**: If sum + m*(n-1) = target*n, and target = min+m, then m = sum - n*min
4. **Simple Formula**: Total moves = sum(array) - n * min(array)
5. **No Need to Find Target**: The formula works without explicitly finding target

## Algorithm Steps
1. Find minimum element in array
2. Calculate sum of all elements
3. Apply formula: moves = sum - n * min
4. Return result

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass for sum and min
- **Space Complexity:** O(1) - Only using variables

## Mathematical Derivation

```
Let m = number of moves
Original sum = S
After m moves, sum = S + m*(n-1)
After m moves, all elements = x (target)
So: S + m*(n-1) = n*x

Also: x = min_element + m (each element increases by m when we decrement min by m)

From S + m*(n-1) = n*x:
S + mn - m = n*x
S + mn - m = n*(min+m)
S + mn - m = n*min + nm
S - m = n*min
m = S - n*min

Therefore: moves = sum(array) - n * min(array)
```

## ASCII Visualization

```
Array: [1, 2, 3]
n = 3, sum = 6, min = 1

Using formula:
moves = 6 - 3*1 = 3

Verification:
Move 1: Increment [2, 3] → [2, 3, 3], sum = 8
Move 2: Increment [2, 3] → [3, 4, 3], sum = 10
Move 3: Increment [3, 3] → [4, 4, 4], sum = 12

After 3 moves: sum = 6 + 3*2 = 12 ✓
All elements = 1 + 3 = 4 ✓
```

## Edge Cases
1. **All elements equal:** Return 0
2. **Two elements:** Handle correctly
3. **Large array:** Formula works efficiently
4. **Large numbers:** Might need long/BigInteger
5. **Single element:** Return 0

## Related Problems
- **LeetCode 462:** Minimum Moves to Equal Array Elements II
- **LeetCode 598:** Range Addition II
- **LeetCode 1450:** Number of Students Doing Homework at a Given Time

## Tags
`Array` `Math` `Greedy`
