# LeetCode 346: Moving Average from Data Stream

## Problem Statement
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Design a class to compute the moving average of a stream of integers.

## Examples

**Example:**
```
MovingAverage m = new MovingAverage(3);
m.next(1) = 1.0             // average of [1]
m.next(10) = 5.5            // average of [1, 10]
m.next(3) = (1 + 10 + 3)/3 = 4.666...
m.next(5) = (10 + 3 + 5)/3 = 6.0      // drop 1, add 5
m.next(15) = (3 + 5 + 15)/3 = 7.666...
```

## Key Insights

1. **Sliding Window**: Maintain a window of fixed size
2. **Deque Efficiency**: Use deque (double-ended queue) for O(1) add/remove
3. **Running Sum**: Track sum to avoid recalculating for every element
4. **Stream Processing**: Data arrives one at a time, need online algorithm

## Algorithm Steps

1. Initialize deque and window size
2. For each new element:
   - If window is full, remove oldest element from back (update sum)
   - Add new element to front (update sum)
   - Calculate average = sum / current window size
   - Return average

## Complexity Analysis

| Operation | Time | Space |
|-----------|------|-------|
| Constructor | O(1) | O(1) |
| next() | O(1) | O(size) |
| **Overall** | O(1) per call | O(size) |

- **Time**: Each next() call does constant work (one add, possibly one remove)
- **Space**: Deque stores at most `size` elements

## ASCII Visualization

```
Window Size = 3

Initial: queue = [], sum = 0, currentSize = 0

next(1):
  queue = [1]
  sum = 1
  currentSize = 1
  avg = 1.0

next(10):
  queue = [10, 1]
  sum = 11
  currentSize = 2
  avg = 5.5

next(3):
  queue = [3, 10, 1]
  sum = 14
  currentSize = 3
  avg = 4.666...

next(5):
  Window full, remove last (1)
  queue = [5, 3, 10]
  sum = 18
  currentSize = 3
  avg = 6.0

next(15):
  Window full, remove last (10)
  queue = [15, 5, 3]
  sum = 23
  currentSize = 3
  avg = 7.666...
```

## Code Walkthrough

```java
class MovingAverage {
    private int size;                    // Max window size
    private int currentSize = 0;         // Current elements in window
    private int runningSum = 0;          // Sum of elements in window
    private Deque<Integer> queue;        // FIFO for window

    public MovingAverage(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    public double next(int val) {
        // Remove oldest if window is full
        if (currentSize == size) {
            int toBeRemoved = queue.removeLast();  // Remove from back
            runningSum -= toBeRemoved;
            currentSize--;
        }

        // Add new element
        currentSize++;
        runningSum += val;
        queue.addFirst(val);  // Add to front

        // Calculate and return average
        return (double) runningSum / currentSize;
    }
}
```

**Key Points:**
- `addFirst()`: Add new element to front (newest)
- `removeLast()`: Remove oldest element from back
- Only remove when window is full to avoid negative currentSize

## Edge Cases

1. **Window size 1**: Moving average equals each element
2. **Empty window**: Not applicable (next() always receives values)
3. **Negative numbers**: Works correctly
4. **Large window**: Works but uses more space
5. **Single element**: Returns that element as average

## Related Problems

1. **LeetCode 239** - Sliding Window Maximum
2. **LeetCode 3** - Longest Substring Without Repeating Characters
3. **LeetCode 567** - Permutation in String
4. **LeetCode 1004** - Max Consecutive Ones III
5. **LeetCode 1658** - Minimum Operations to Reduce X to Zero

## Tags

`Design` `Queue` `Data Stream` `Easy` `Amazon` `Apple` `Twitter`

## Alternative Approaches

### Approach 2: Fixed Size Array
- Use circular buffer instead of deque
- Slightly more space efficient but harder to implement

### Approach 3: Simple Queue
- Use regular queue (less efficient than deque)
- Time: O(1) amortized, but removals vary

## Notes

- Deque is ideal for this problem due to O(1) operations on both ends
- Running sum optimization prevents O(size) calculation for each element
- This is a classic streaming/online algorithm problem
- Real-world usage: Stock price moving averages, monitoring metrics
