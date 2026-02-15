# LeetCode 295: Find Median from Data Stream

## Problem Statement
Design a data structure that supports:
1. **addNum(int num)** - Add a number from data stream
2. **findMedian()** - Return median of all numbers added so far

For even-sized list, median is average of two middle numbers. For odd-sized, it's the middle number.

## Difficulty
Hard

## Examples

### Example
```
addNum(1) → [1]
addNum(2) → [1, 2] → median = 1.5
addNum(3) → [1, 2, 3] → median = 2
```

## Key Insights
1. **Two Heaps Strategy**: Use max heap for lower half and min heap for upper half
2. **Balanced Heaps**: Maintain heaps to be equal size or differ by 1
3. **Size Constraint**: Max heap size >= min heap size always
4. **Efficient Rebalancing**: After each addition, check if rebalancing needed

## Algorithm Steps
1. Create max heap (for lower half) and min heap (for upper half)
2. For addNum():
   - If max heap empty or num <= max heap peek, add to max heap
   - Otherwise add to min heap
   - Rebalance heaps if size difference >= 2
3. For findMedian():
   - If heaps are equal size: return (max heap peek + min heap peek) / 2
   - Otherwise: return peek of larger heap

## Complexity Analysis
- **Time Complexity:** O(log n) per addNum(), O(1) for findMedian()
- **Space Complexity:** O(n) for storing all numbers

## ASCII Visualization

```
Adding: 1, 2, 3, 4, 5

After 1:
Max Heap: [1]         Min Heap: []
Median = 1

After 2:
Max Heap: [1]         Min Heap: [2]
Median = (1+2)/2 = 1.5

After 3:
Max Heap: [2, 1]      Min Heap: [3]
Median = 2

After 4:
Max Heap: [2, 1]      Min Heap: [3, 4]
Median = (2+3)/2 = 2.5

After 5:
Max Heap: [3, 2, 1]   Min Heap: [4, 5]
Median = 3
```

## Edge Cases
1. **Single element:** Return that element
2. **Two elements:** Return their average
3. **Large numbers:** Handle integer overflow
4. **Negative numbers:** Heaps work the same
5. **Duplicates:** Both heaps handle duplicates

## Related Problems
- **LeetCode 480:** Sliding Window Median
- **LeetCode 239:** Sliding Window Maximum
- **LeetCode 703:** Kth Largest Element in a Stream

## Tags
`Heap` `Priority Queue` `Data Stream` `Median`
