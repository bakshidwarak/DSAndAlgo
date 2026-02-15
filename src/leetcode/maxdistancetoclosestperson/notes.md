# LeetCode 849: Maximize Distance to Closest Person

## Problem Statement
In a row of seats, 1 represents a person and 0 represents an empty seat. Alex wants to sit in an empty seat such that the distance to the **closest person is maximized**. Return that maximum distance.

## Difficulty
Easy

## Examples

### Example 1
- **Input:** `[1, 0, 0, 0, 1, 0, 1]`
- **Output:** `2`
- **Explanation:** If Alex sits at index 2, closest person is at distance 2

### Example 2
- **Input:** `[1, 0, 0, 0]`
- **Output:** `3`
- **Explanation:** If Alex sits at last index 3, closest person is at distance 3

## Key Insights
1. **Three Scenarios**:
   - Empty seats at the beginning (distance = number of seats)
   - Empty seats at the end (distance = number of seats)
   - Empty seats between two people (distance = (gap_size) / 2)
2. **Gap Analysis**: For seats between people, the optimal seat is in the middle
3. **Edge Cases**: Seats before first person and after last person have different calculation

## Algorithm Steps
1. Initialize pointer i to track positions of people
2. Iterate through array looking for consecutive 1s
3. For each group of zeros:
   - If at start and before any 1: distance = gap size
   - If between two 1s: distance = gap size / 2
   - If at end and after last 1: distance = gap size
4. Track and return maximum distance

## Complexity Analysis
- **Time Complexity:** O(n) - Single pass through array
- **Space Complexity:** O(1) - Only using a few variables

## ASCII Visualization

```
Array: [1, 0, 0, 0, 1, 0, 1]
Index:  0  1  2  3  4  5  6

i=0: seats[0]=1 (person)
i=1: j=2,3,4 (0s) then seats[4]=1
     Gap between two people: distance = (4-0)/2 = 2

i=4: seats[4]=1 (person)
i=5: j=6 (0) then seats[6]=1
     Gap between two people: distance = (6-4)/2 = 1

i=6: seats[6]=1 (last person)
j=7 (out of bounds)
If there were more, distance = 7-6 = 1

Maximum = 2
```

## Edge Cases
1. **All empty except edges:** `[1, 0, 0, 0, 0, 0, 1]` → max = 3
2. **Person at start:** `[1, 0, 0, 1]` → max = 1
3. **Person at end:** `[1, 0, 0, 0]` → max = 3
4. **Only two people:** `[1, 0, 1]` → max = 1
5. **Multiple gaps:** Find max among all gaps

## Related Problems
- **LeetCode 1437:** Check If All 1s Are at Least Length K Places Away
- **LeetCode 2087:** Minimum Cost Homecoming of a Robot in a Grid
- **LeetCode 1833:** Maximum Ice Cream Bars

## Tags
`Array` `Greedy` `Distance Calculation`
