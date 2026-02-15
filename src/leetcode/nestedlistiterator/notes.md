# LeetCode 341: Nested List Iterator

## Problem Statement
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list whose elements may also be integers or other lists. You need to support `hasNext()` and `next()` methods.

## Examples

**Example 1:**
```
Input: [[1,1],2,[1,1]]
Output: [1, 1, 2, 1, 1]
Explanation: By calling next() repeatedly, we get each element
```

**Example 2:**
```
Input: [1,[4,[6]]]
Output: [1, 4, 6]
Explanation: Deeply nested structure flattened
```

## Key Insights

1. **Recursion**: Use DFS to flatten nested structure upfront
2. **Iterator Pattern**: Implement Iterator interface for flexibility
3. **Preprocessing**: Flatten entire structure in constructor
4. **Simple Access**: Once flattened, access is straightforward

## Algorithm Steps

1. In constructor, recursively traverse the nested list
2. Whenever an integer is found, add it to result list
3. Whenever a nested list is found, recursively process it
4. `hasNext()` checks if currentIndex < flattened list size
5. `next()` returns element at currentIndex and increments

## Complexity Analysis

| Operation | Time | Space |
|-----------|------|-------|
| Constructor | O(n) | O(n) |
| hasNext() | O(1) | - |
| next() | O(1) | - |
| **Overall** | O(n) for all operations | O(n) |

- **n** = total number of integers in nested structure
- Space: Store all integers in flattened list

## ASCII Visualization

```
Nested Structure: [[1,1],2,[1,1]]

Tree representation:
         root
        /  |  \
      [0] 2  [2]
     /  \      / \
    1    1    1   1

DFS Traversal:
- Process [0]: is list, recurse
  - Process 1: is integer, add to result
  - Process 1: is integer, add to result
- Process 2: is integer, add to result
- Process [2]: is list, recurse
  - Process 1: is integer, add to result
  - Process 1: is integer, add to result

Result: [1, 1, 2, 1, 1]
         ^
      currentIndex=0
```

## Code Walkthrough

```java
public class NestedListIterator implements Iterator<Integer> {

    private List<Integer> flattenedList = new ArrayList<>();
    private int currentIndex = 0;

    // Constructor: Flatten entire nested structure using DFS
    public NestedListIterator(List<NestedInteger> nestedList) {
        addToList(nestedList, 0);
    }

    // Recursive DFS to flatten
    public void addToList(List<NestedInteger> nestedList, int index) {
        for (NestedInteger number : nestedList) {
            if (number.isInteger()) {
                // Base case: integer found
                flattenedList.add(number.getInteger());
                index++;
            } else {
                // Recursive case: nested list found
                addToList(number.getList(), index);
            }
        }
    }

    @Override
    public Integer next() {
        return flattenedList.get(currentIndex++);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < flattenedList.size();
    }
}
```

**Execution Flow:**
1. Constructor calls `addToList()` with initial nested list
2. `addToList()` iterates through each element
3. If integer, add to `flattenedList`
4. If list, recursively call `addToList()` with that list
5. After construction, iterator is ready to traverse

## Edge Cases

1. **Empty list**: `[]` → No elements, hasNext() returns false
2. **Single integer**: `[1]` → One element
3. **Single list**: `[[1]]` → Must recurse once
4. **Deeply nested**: `[[[[[1]]]]]` → Works with any depth
5. **Mixed nesting**: `[1, [2, [3, 4]]]` → Handles multiple levels
6. **Empty sublists**: `[[], [1], []]` → Skip empty lists

## Related Problems

1. **LeetCode 339** - Nested List Weight Sum (DFS, same structure)
2. **LeetCode 364** - Nested List Weight Sum II (DFS variant)
3. **LeetCode 385** - Mini Parser (Parse nested list from string)
4. **LeetCode 251** - Flatten 2D Vector (Similar iterator design)
5. **LeetCode 173** - Binary Search Tree Iterator (Iterator pattern)

## Tags

`Stack` `Design` `DFS` `Medium` `Google` `Facebook` `Apple`

## Alternative Approaches

### Approach 2: Lazy Evaluation with Stack
```
- Use stack during iteration (process on demand)
- More memory efficient for large structures
- More complex to implement
- Time: O(1) per next() call in practice
- Space: O(depth) instead of O(n)
```

### Approach 3: Queue-Based
```
- Use queue to manage elements to process
- Similar to lazy evaluation
- BFS style traversal
```

## Implementation Considerations

1. **NestedInteger Interface**: Provided by LeetCode
   - `isInteger()`: Check if element is integer
   - `getInteger()`: Get integer value
   - `getList()`: Get nested list

2. **Index Parameter**: Not really used in this implementation
   - Could be removed for cleaner code
   - Currently unused in the recursive calls

## Notes

- Preprocessing approach is simpler and more straightforward
- Trade-off: Fast queries but slow initialization
- Opposite approach (lazy evaluation) would be: Slow queries, fast initialization
- Good example of implementing Iterator interface
- Demonstrates recursive DFS on nested structures
