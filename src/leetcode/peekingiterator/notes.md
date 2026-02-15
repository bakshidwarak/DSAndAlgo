# LeetCode 284: Peeking Iterator

## Problem Statement
Given an Iterator class interface with methods: `next()` and `hasNext()`, design and implement a PeekingIterator that support the `peek()` operation.

The `peek()` operation returns the next element in the iteration without advancing the iterator.

## Examples

**Example:**
```
Iterator iterator = new Iterator([1,2,3]);
PeekingIterator peekerIterator = new PeekingIterator(iterator);

peekerIterator.next();    // return 1
peekerIterator.peek();    // return 2
peekerIterator.next();    // return 2
peekerIterator.next();    // return 3
peekerIterator.hasNext(); // return false
```

## Key Insights

1. **Decorator Pattern**: Wrap existing iterator with additional functionality
2. **Lookahead**: Store next element without consuming it
3. **Linked List Storage**: Use nodes to track all elements in iteration order
4. **Position Tracking**: Index tracks current position in processed elements

## Algorithm Steps

### Approach 1: Pre-process All Elements (Current Implementation)

1. In constructor, iterate through given iterator and store all elements
2. Build linked list structure from these elements
3. `next()`: Return element at current index and increment
4. `peek()`: Return element at current index without incrementing
5. `hasNext()`: Check if current index < list size

### Approach 2: Single Element Lookahead

1. Keep track of next element separately
2. When `next()` called, fetch next if not already cached
3. More memory efficient but requires careful management

## Complexity Analysis

| Operation | Time | Space |
|-----------|------|-------|
| Constructor | O(n) | O(n) |
| peek() | O(1) | - |
| next() | O(1) | - |
| hasNext() | O(1) | - |
| **Overall** | O(n) for all ops | O(n) |

- n = total elements in original iterator
- Preprocessing converts to O(1) peek operations

## ASCII Visualization

```
Original Iterator: [1, 2, 3, 4, 5]

Preprocessing (Constructor):
Iterate through iterator:
  Iterator yields: 1 → Create Node(1)
  Iterator yields: 2 → Create Node(2)
  Iterator yields: 3 → Create Node(3)
  Iterator yields: 4 → Create Node(4)
  Iterator yields: 5 → Create Node(5)

Build linked list:
first → [1] → [2] → [3] → [4] → [5]

State after constructor:
currentIndex = 0
flattenedList = [1, 2, 3, 4, 5]
first = Node pointing to 1

Iteration:
next():
  return flattenedList[0]=1, currentIndex=1
  flattenedList = [1, 2, 3, 4, 5]
                   ^current position

peek():
  return flattenedList[1]=2
  flattenedList = [1, 2, 3, 4, 5]
                   ^peek (no advance)

next():
  return flattenedList[1]=2, currentIndex=2
  flattenedList = [1, 2, 3, 4, 5]
                      ^current position

next():
  return flattenedList[2]=3, currentIndex=3

hasNext():
  return 3 < 5 = true

Continue until currentIndex >= 5
hasNext() returns false
```

## Code Walkthrough

```java
class PeekingIterator implements Iterator<Integer> {

    static class Node {
        Integer x;
        Node next;

        private Node(Integer x) {
            this.x = x;
        }

        public static Node of(Integer val) {
            return new Node(val);
        }
    }

    private Node first = null;  // Head of linked list

    public PeekingIterator(Iterator<Integer> iterator) {
        Node temp = null;
        Node prev = null;

        // Convert iterator elements to linked list
        while (iterator.hasNext()) {
            temp = Node.of(iterator.next());

            // Link previous node to current
            if (prev != null) {
                prev.next = temp;
            }

            // Track first node
            if (first == null) {
                first = temp;
            }

            // Move prev to current for next iteration
            prev = temp;
        }
    }

    // Return next element without advancing
    public Integer peek() {
        if (first != null)
            return first.x;
        return null;
    }

    // Return next element and advance
    @Override
    public Integer next() {
        Integer toReturn = null;

        if (first != null) {
            toReturn = first.x;
            first = first.next;  // Advance pointer
        }

        return toReturn;
    }

    // Check if more elements
    @Override
    public boolean hasNext() {
        return first != null;
    }
}
```

**Execution Trace for Example:**
```
Input iterator: [1, 2, 3, 4, 5]

Constructor:
  Iterator.next() → 1
    temp = Node(1)
    first = Node(1)
    prev = Node(1)

  Iterator.next() → 2
    temp = Node(2)
    prev.next = Node(2)
    prev = Node(2)

  ... continue for 3, 4, 5

  Result: first → [1] → [2] → [3] → [4] → [5]

next() call 1:
  toReturn = first.x = 1
  first = first.next → Node(2)
  return 1

peek() call 1:
  return first.x = 2
  first unchanged → Node(2)

next() call 2:
  toReturn = first.x = 2
  first = first.next → Node(3)
  return 2

next() call 3:
  toReturn = first.x = 3
  first = first.next → Node(4)
  return 3

hasNext():
  return first != null = true (first = Node(4))

next() call 4:
  toReturn = first.x = 4
  first = first.next → Node(5)
  return 4

next() call 5:
  toReturn = first.x = 5
  first = first.next → null
  return 5

hasNext():
  return first != null = false (first = null)
```

## Edge Cases

1. **Empty iterator**: No elements → peek() returns null, hasNext() = false
2. **Single element**: `[1]` → peek() = 1, next() = 1, hasNext() = false
3. **Multiple peek calls**: peek() several times returns same value
4. **No peek before next**: Can call next() directly

## Related Problems

1. **LeetCode 251** - Flatten 2D Vector (Iterator with preprocessing)
2. **LeetCode 341** - Nested List Iterator (Nested structure iteration)
3. **LeetCode 173** - Binary Search Tree Iterator (Custom iterator)
4. **LeetCode 281** - Zigzag Iterator (Multiple iterator handling)
5. **LeetCode 1586** - Binary Search Tree Iterator II (Previous/next)

## Tags

`Design` `Iterator` `Easy` `Google` `Microsoft` `Amazon` `Apple`

## Alternative Approaches

### Approach 2: Single Element Lookahead (More Efficient)
```java
class PeekingIteratorLazy implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer nextVal;
    private boolean hasNext;

    public PeekingIteratorLazy(Iterator<Integer> iterator) {
        this.iterator = iterator;
        advance();
    }

    private void advance() {
        hasNext = iterator.hasNext();
        if (hasNext) {
            nextVal = iterator.next();
        }
    }

    public Integer peek() {
        return nextVal;
    }

    @Override
    public Integer next() {
        Integer val = nextVal;
        advance();
        return val;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }
}
```
- Space: O(1) (only stores one element)
- Time: O(1) per operation

### Approach 3: Queue-Based
```
- Store elements in queue
- No preprocessing needed
- Similar efficiency to linked list
```

## Implementation Notes

1. **Node Class**: Static inner class for linked list nodes
2. **Factory Method**: `Node.of()` for clean creation
3. **Pointer Management**: Careful tracking of first pointer
4. **Builder Pattern**: Link nodes as we iterate

## Common Pitfalls

1. **Null Handling**: Return null when no next element
2. **Pointer Advancement**: Only advance on next(), not peek()
3. **Iterator Exhaustion**: Handle when original iterator is empty
4. **Multiple Iterations**: Each PeekingIterator is independent

## Performance Trade-offs

```
Approach              Space     peek()   next()  hasNext()
Preprocess all       O(n)      O(1)     O(1)    O(1)
Single lookahead     O(1)      O(1)     O(1)    O(1)
Queue                O(n)      O(1)     O(1)    O(1)
```

All have similar time complexity, trade-off is space.

## Design Pattern

This demonstrates the **Decorator Pattern**:
- Wraps existing Iterator with new functionality
- Adds `peek()` without modifying original
- Compatible with any Iterator implementation

## Notes

- Classic design pattern implementation
- Tests understanding of iterator concept
- Shows how to extend existing interfaces
- Preprocessing vs lazy evaluation trade-off
- Good practice for decorator/wrapper patterns
