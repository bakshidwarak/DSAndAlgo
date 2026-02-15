# LeetCode 975: Odd Even Jump

## Problem Statement
You are given an integer array A. From some starting index, you can make a series of jumps.

- **Odd numbered jumps** (1st, 3rd, 5th...): Jump to the smallest value that is >= current value
- **Even numbered jumps** (2nd, 4th, 6th...): Jump to the largest value that is <= current value

If there are multiple valid targets with the same value, jump to the smallest index.

A starting index is "good" if you can reach the end of the array (index A.length - 1) by jumping. Return the number of good starting indexes.

## Examples

**Example 1:**
```
Input: [10,13,12,14,15]
Output: 2
Explanation:
- From index 0 (10): Jump odd → 12, Can't jump even → Not good
- From index 1 (13): Jump odd → 14, Can't jump even → Not good
- From index 2 (12): Jump odd → 13, Jump even → Can't → Not good
- From index 3 (14): Jump odd → 15, Reached end → Good
- From index 4 (15): Already at end → Good
Answer: 2 (indices 3, 4)
```

**Example 2:**
```
Input: [2,3,1,1,4]
Output: 3
Explanation:
- From index 0 (2): Jump odd → 3, Jump even → 1, Jump odd → 1, Jump even → Can't → Not good
- From index 1 (3): Jump odd → 4, Reached end → Good
- From index 2 (1): Jump odd → 1, Jump even → 1, Jump odd → 1 (infinite loop) → Not good
- From index 3 (1): Jump odd → 1, Jump even → 1 (infinite loop) → Not good
- From index 4 (4): Already at end → Good
Answer: 3
```

## Key Insights

1. **Dynamic Programming**: Track reachability from each position
2. **Memoization**: Cache results to avoid recalculation
3. **Jump State**: State depends on both position and jump parity (odd/even)
4. **Greedy Jump**: Always jump to the best next position based on parity

## Algorithm Steps

### Approach 1: DFS with Memoization

1. Create 2D DP array: `result[index][isOdd]` where isOdd ∈ {0, 1}
2. For each starting position:
   - Check if we can reach the end
   - Use recursive DFS to explore jumps
3. DFS logic:
   - If index == n-1: return true (reached end)
   - If isOdd jump: find smallest value >= current that's after current index
   - If even jump: find largest value <= current that's after current index
   - Recursively check if next position leads to end

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n^2) - Each position checked with both parities |
| **Space Complexity** | O(n) - Memoization table for 2n states |

- With proper sorting approach: O(n log n)
- This naive approach: O(n^2) worst case

## ASCII Visualization

```
Array: [10,13,12,14,15]
Index:  [0, 1, 2, 3, 4]

From index 0, value 10, ODD jump:
  Need smallest value >= 10 after index 0
  Candidates: 13(1), 12(2), 14(3), 15(4)
  Smallest: 12 at index 2
  Jump to index 2

From index 2, value 12, EVEN jump:
  Need largest value <= 12 after index 2
  Candidates: 14(3)>12, 15(4)>12
  No valid target, NOT GOOD

From index 1, value 13, ODD jump:
  Need smallest value >= 13
  Candidates: 14(3), 15(4)
  Smallest: 14 at index 3
  Jump to index 3

From index 3, value 14, EVEN jump:
  Need largest value <= 14 after index 3
  Candidates: 15(4)>14
  No valid target

Wait, Example says index 3 is good...
Let me reconsider: From 3 to 4 (odd jump with value 14→15)
From 4 (EVEN jump): Already at end → GOOD

State tracking:
result[4][0] = 1 (even jump, at end)
result[4][1] = 1 (odd jump, at end)
result[3][1] = check(4, 0) = 1 → GOOD
result[3][0] = check(no_target) = 0
result[0][1] = check(2, 0) = 0 → NOT GOOD
```

## Code Walkthrough

```java
public int oddEvenJumps(int[] A) {
    int count = 0;
    int odd = 1;  // Start with odd jump
    int[][] result = new int[A.length][2];

    // Initialize all states as unvisited (-1)
    Arrays.stream(result).forEach(a -> Arrays.fill(a, -1));

    // Check each starting position
    for (int i = 0; i < A.length; i++) {
        if (isGood(i, odd, A, result)) {
            count++;
        }
    }

    return count;
}

// Check if position can reach end with given jump parity
public boolean isGood(int index, int isOdd, int[] A, int[][] result) {
    if (result[index][isOdd] == -1) {
        if (index == A.length - 1) {
            result[index][isOdd] = 1;  // At end, always good
        } else if (isOdd == 1) {
            // ODD jump: find smallest value >= current
            int minIndex = -1;
            int minValue = Integer.MAX_VALUE;

            for (int j = index + 1; j < A.length; j++) {
                if (A[index] <= A[j]) {
                    if (A[j] < minValue) {
                        minValue = A[j];
                        minIndex = j;
                    }
                }
            }

            if (minIndex != -1) {
                // Check if this jump leads to end
                result[index][isOdd] = isGood(minIndex, 0, A, result) ? 1 : 0;
            } else {
                result[index][isOdd] = 0;  // No valid jump
            }
        } else {
            // EVEN jump: find largest value <= current
            int maxIndex = -1;
            int maxValue = Integer.MIN_VALUE;

            for (int j = index + 1; j < A.length; j++) {
                if (A[index] >= A[j]) {
                    if (A[j] > maxValue) {
                        maxValue = A[j];
                        maxIndex = j;
                    }
                }
            }

            if (maxIndex != -1) {
                result[index][isOdd] = isGood(maxIndex, 1, A, result) ? 1 : 0;
            } else {
                result[index][isOdd] = 0;
            }
        }
    }

    return result[index][isOdd] == 1;
}
```

## Edge Cases

1. **Single element**: `[1]` → 1 (already at end)
2. **All increasing**: `[1,2,3,4]` → All odd jumps lead to end
3. **All decreasing**: `[4,3,2,1]` → Only last is good
4. **Duplicates**: `[1,1,1,1]` → Need to find smallest index among same values
5. **Two elements**: `[1,2]` → Check both positions
6. **No valid jumps**: Circular or dead-end patterns

## Related Problems

1. **LeetCode 45** - Jump Game II (Simple jump)
2. **LeetCode 55** - Jump Game (Reachability)
3. **LeetCode 1306** - Jump Game III (Graph traversal)
4. **LeetCode 1345** - Jump Game IV (BFS/DFS)
5. **LeetCode 1696** - Jump Game VI (DP with k jumps)

## Tags

`DP` `DFS` `Memoization` `Hard` `Google` `Facebook` `Bloomberg`

## Alternative Approaches

### Approach 2: Sorting-Based DP (More Efficient)
```
- Sort indices by values (for odd jumps)
- Sort indices by values descending (for even jumps)
- Use DP to track which positions are good
- Time: O(n log n) instead of O(n^2)
- Space: O(n)
```

### Approach 3: BFS Backwards
```
- Start from end (which is always good)
- Work backwards to find all good starting positions
- More efficient than checking each position independently
```

## Optimization Opportunities

1. **Sorting**: Avoid O(n) search for next jump target
2. **TreeMap**: Find ceiling/floor in O(log n)
3. **Backward Search**: Only from positions reachable from end

## Implementation Notes

1. **Parity Tracking**: 0 = even jump, 1 = odd jump
2. **Memoization Array**: 2D for position and jump type
3. **Linear Search**: Naive O(n) per jump in this code
4. **Multiple Valid Targets**: Choose smallest index when values equal

## Common Mistakes

1. **Jump Logic**: Odd finds smallest >=, Even finds largest <=
2. **Index Selection**: When multiple valid, choose smallest index
3. **Boundary Check**: Must stay within array bounds
4. **Initialization**: Start with odd jump (jump 1 is odd)
5. **Termination**: Stop when reaching last index

## Notes

- Complex DP problem combining multiple concepts
- Requires careful tracking of jump parity
- Natural solution is O(n^2), but optimizable to O(n log n)
- Great practice for understanding DP memoization
- Shows importance of algorithm optimization
