# LeetCode 56: Merge Intervals

## Problem Statement
Given a collection of intervals, **merge all overlapping intervals** and return a list of non-overlapping intervals.

## Difficulty
Medium

## Examples

### Example 1
- **Input:** `[[1,3], [2,6], [8,10], [15,18]]`
- **Output:** `[[1,6], [8,10], [15,18]]`
- **Explanation:** Intervals [1,3] and [2,6] overlap, merge to [1,6]

### Example 2
- **Input:** `[[1,4], [4,5]]`
- **Output:** `[[1,5]]`
- **Explanation:** Intervals with same boundary [4] are considered overlapping

## Key Insights
1. **Sort by Start Time**: Order intervals by start position first
2. **Sequential Merging**: After sorting, merge adjacent overlapping intervals
3. **Overlap Condition**: current.start <= first.end means overlap exists
4. **Keep Maximum End**: Use max of both ends when merging

## Algorithm Steps
1. Handle empty case
2. Sort intervals by start time
3. Initialize first interval from sorted list
4. Iterate through remaining intervals:
   - If current start <= first end: merge by updating first's end to max(first.end, current.end)
   - Else: add first to result and make current the new first
5. Don't forget to add the last interval to result

## Complexity Analysis
- **Time Complexity:** O(n log n) - Dominated by sorting
- **Space Complexity:** O(1) - Not counting output array

## ASCII Visualization

```
Input: [[1,3], [2,6], [8,10], [15,18]]

After sorting: [[1,3], [2,6], [8,10], [15,18]]

Step 1: first = [1,3]
Step 2: current = [2,6]
        2 <= 3 → merge
        first = [1, max(3,6)] = [1,6]

Step 3: current = [8,10]
        8 > 6 → no merge
        Add [1,6] to result
        first = [8,10]

Step 4: current = [15,18]
        15 > 10 → no merge
        Add [8,10] to result
        first = [15,18]

Step 5: End of loop
        Add [15,18] to result

Output: [[1,6], [8,10], [15,18]]
```

## Edge Cases
1. **Empty list:** Return empty list
2. **Single interval:** Return that interval
3. **All overlapping:** Return single merged interval
4. **No overlapping:** Return all intervals
5. **One interval contains another:** Return the larger one

## Related Problems
- **LeetCode 435:** Non-overlapping Intervals
- **LeetCode 252:** Meeting Rooms
- **LeetCode 986:** Interval List Intersections
- **LeetCode 1288:** Remove Covered Intervals

## Tags
`Array` `Sorting` `Greedy` `Intervals`
