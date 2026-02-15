# LeetCode 252: Meeting Rooms I

## Problem Statement
Given an array of meeting time intervals `[[s1,e1],[s2,e2],...]` where si < ei, determine if a person can **attend all meetings** without any time conflicts.

## Difficulty
Easy

## Examples

### Example 1
- **Input:** `[[0, 30], [5, 10], [15, 20]]`
- **Output:** `false`
- **Explanation:** Meetings [0,30] and [5,10] overlap

### Example 2
- **Input:** `[[7, 10], [2, 4]]`
- **Output:** `true`
- **Explanation:** No overlapping meetings

## Key Insights
1. **Sort by Start Time**: Order meetings by start time to check for overlaps
2. **Sequential Check**: After sorting, only need to check if previous meeting ends before next starts
3. **No Sorting Needed for Y/N**: Just checking possibility, not scheduling
4. **Overlap Detection**: If prev.end > curr.start, there's overlap

## Algorithm Steps
1. Handle empty case
2. Sort intervals by start time using custom comparator
3. Iterate through sorted intervals:
   - Compare end time of previous meeting with start time of current
   - If previous end > current start, return false (overlap)
4. If loop completes, return true (no overlaps)

## Complexity Analysis
- **Time Complexity:** O(n log n) - Dominated by sorting
- **Space Complexity:** O(1) - Only storing pointers

## ASCII Visualization

```
Unsorted intervals:
[0, 30], [5, 10], [15, 20]

After sorting by start:
[0, 30], [5, 10], [15, 20]

Check for overlap:
[0, 30] prev.end=30 > [5, 10] curr.start=5 → OVERLAP!
Result: false

Another example:
[1, 4], [4, 5], [7, 9]
After sorting: [1, 4], [4, 5], [7, 9]

[1, 4] prev.end=4 == [4, 5] curr.start=4 → NO overlap (meeting at exactly 4 OK)
[4, 5] prev.end=5 < [7, 9] curr.start=7 → NO overlap
Result: true
```

## Edge Cases
1. **Empty intervals:** Return true
2. **Single meeting:** Return true
3. **Adjacent meetings (same end/start):** Return true (no overlap)
4. **One meeting contains another:** Return false
5. **All non-overlapping:** Return true

## Related Problems
- **LeetCode 253:** Meeting Rooms II
- **LeetCode 1046:** Last Stone Weight
- **LeetCode 435:** Non-overlapping Intervals

## Tags
`Array` `Sorting` `Interval` `Greedy`
