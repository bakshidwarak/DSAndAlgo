# LeetCode 57: Insert Interval

## Problem Statement

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

### Examples

**Example 1:**
```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

**Example 2:**
```
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,9]
Output: [[1,2],[3,10],[12,16]]
Explanation: The new interval [4,9] overlaps with [3,5],[6,7],[8,10].
```

### Constraints
- Intervals are non-overlapping initially
- Intervals are sorted by start time
- New interval must be merged if it overlaps

## Key Insights

1. **Three Phases**:
   - Add all intervals that end before new interval starts
   - Merge all intervals that overlap with new interval
   - Add all intervals that start after new interval ends

2. **Merge Condition**: Intervals overlap if `interval.start <= newInterval.end`

3. **Merge Operation**: Take min of starts and max of ends

4. **Single Pass**: Can solve in one iteration through intervals

5. **No Sorting Needed**: Input is already sorted

## Algorithm Steps

### Phase 1: Add Before Intervals
1. While current interval ends before new interval starts:
   - Add current interval to result
   - Move to next interval

### Phase 2: Merge Overlapping
2. While current interval overlaps with new interval:
   - Expand new interval to include current interval
   - `newInterval.start = min(current.start, newInterval.start)`
   - `newInterval.end = max(current.end, newInterval.end)`
   - Move to next interval

3. Add merged interval to result

### Phase 3: Add After Intervals
4. While intervals remain:
   - Add remaining intervals to result

## Complexity Analysis

- **Time Complexity**: O(n)
  - Single pass through all intervals
  - Each interval processed once

- **Space Complexity**: O(n)
  - Result list stores up to n+1 intervals
  - No additional data structures

## ASCII Visualization

```
Example 1: intervals = [[1,3],[6,9]], newInterval = [2,5]

Original intervals:
  [1,3]    |===|
  [6,9]              |====|
           1  2  3  4  5  6  7  8  9

New interval: [2,5]
                |======|

Phase 1: Add before [2,5]
  No intervals end before 2

Phase 2: Merge with [2,5]
  [1,3]: 3 >= 2? YES → merge
  newInterval = [min(1,2), max(3,5)] = [1,5]

  [6,9]: 6 <= 5? NO → stop merging

Phase 3: Add merged [1,5]
  [1,5]    |========|

Phase 4: Add after [1,5]
  [6,9]              |====|

Result: [[1,5], [6,9]]

---

Example 2: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,9]

Timeline:
  [1,2]     |=|
  [3,5]        |==|
  [6,7]            |=|
  [8,10]              |==|
  [12,16]                   |====|
            1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16

New: [4,9]           |=====|

Phase 1: Add before [4,9]
  [1,2]: 2 < 4 → add [1,2]

Phase 2: Merge with [4,9]
  [3,5]: 3 <= 9 → merge
  newInterval = [min(3,4), max(5,9)] = [3,9]

  [6,7]: 6 <= 9 → merge
  newInterval = [min(3,6), max(9,7)] = [3,9]

  [8,10]: 8 <= 9 → merge
  newInterval = [min(3,8), max(9,10)] = [3,10]

  [12,16]: 12 <= 10? NO → stop

Phase 3: Add merged [3,10]

Phase 4: Add after [3,10]
  [12,16]

Result: [[1,2], [3,10], [12,16]]
```

## Code Walkthrough

```java
class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> mergedIntervals = new ArrayList<>();

    // Handle empty intervals
    if (intervals.isEmpty()) {
        mergedIntervals.add(newInterval);
        return mergedIntervals;
    }

    int i = 0;

    // Phase 1: Add all intervals before newInterval
    while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
        mergedIntervals.add(intervals.get(i));
        i++;
    }

    // Phase 2: Merge all overlapping intervals
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
        Interval mergingInterval = new Interval(
            Math.min(intervals.get(i).start, newInterval.start),
            Math.max(intervals.get(i).end, newInterval.end)
        );
        newInterval = mergingInterval;
        i++;
    }

    // Add merged interval
    mergedIntervals.add(newInterval);

    // Phase 3: Add all intervals after newInterval
    while (i < intervals.size()) {
        mergedIntervals.add(intervals.get(i));
        i++;
    }

    return mergedIntervals;
}
```

### Modern Implementation

```java
public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int n = intervals.length;

    // Add intervals before newInterval
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.add(intervals[i]);
        i++;
    }

    // Merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    result.add(newInterval);

    // Add remaining intervals
    while (i < n) {
        result.add(intervals[i]);
        i++;
    }

    return result.toArray(new int[result.size()][]);
}
```

## Edge Cases

1. **Empty intervals list**: Return [newInterval]
2. **New interval before all**: [[5,7]], [1,2] → [[1,2],[5,7]]
3. **New interval after all**: [[1,2]], [5,7] → [[1,2],[5,7]]
4. **New interval contains all**: [[2,3],[4,5]], [1,6] → [[1,6]]
5. **New interval contained**: [[1,10]], [2,5] → [[1,10]]
6. **Touching intervals**: [[1,5]], [5,7] → [[1,7]]
7. **Multiple merges**: Multiple intervals merge into one

## Common Mistakes

1. **Wrong merge condition**: Using `<` instead of `<=`
2. **Not using Math.max/min**: Incorrect merge calculation
3. **Forgetting to add merged interval**: Only adding originals
4. **Off-by-one in loop conditions**: Missing last interval
5. **Not handling contained intervals**: When new is inside existing

## Why Three Phases Work

```
Invariant: Original intervals are non-overlapping and sorted

Phase 1: All intervals with end < newStart
  These don't overlap with new interval
  Can add directly to result

Phase 2: All intervals with start <= newEnd
  These overlap with new interval
  Must merge into single interval

Phase 3: All remaining intervals
  These have start > newEnd (after merging)
  Don't overlap with merged interval
  Can add directly to result
```

## Related Problems

- **LeetCode 56**: Merge Intervals
- **LeetCode 252**: Meeting Rooms
- **LeetCode 253**: Meeting Rooms II
- **LeetCode 435**: Non-overlapping Intervals
- **LeetCode 986**: Interval List Intersections

## Tags

- Array
- Intervals
- Sorting
- Medium Difficulty
- Google
- Facebook
- LinkedIn
