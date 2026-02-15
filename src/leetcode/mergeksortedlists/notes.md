# LeetCode 23: Merge K Sorted Linked Lists

## Problem Statement
Merge **k sorted linked lists** and return it as one sorted linked list.

## Difficulty
Hard

## Examples

### Example 1
```
Input: [[1,4,5], [1,3,4], [2,6]]
Output: [1,1,2,1,3,4,4,5,6]
```

## Key Insights
1. **Priority Queue Strategy**: Use min heap to efficiently get smallest element
2. **K Lists Processing**: Process one element at a time from the smallest list
3. **Next Pointer Addition**: After taking an element, add its next node to heap
4. **Heap Size**: At most k elements in heap at any time
5. **Efficient Selection**: O(log k) to get/add elements vs O(k) for linear search

## Algorithm Steps
1. Create min heap comparator based on node values
2. Add first node from each list to heap (if not null)
3. Build result:
   - Poll smallest element from heap
   - Create new node with that value
   - Add the polled node's next to heap (if exists)
   - Link to result list
4. Return head of merged list

## Complexity Analysis
- **Time Complexity:** O(N log k) where N = total nodes, k = number of lists
- **Space Complexity:** O(k) - At most k nodes in heap at any time

## ASCII Visualization

```
Lists: [1→4→5], [1→3→4], [2→6]

Initial heap: [1(list1), 1(list2), 2(list3)]

Step 1: Poll 1(list1)
        Add 4(list1) to heap
        Heap: [1(list2), 2(list3), 4(list1)]
        Result: 1

Step 2: Poll 1(list2)
        Add 3(list2) to heap
        Heap: [2(list3), 4(list1), 3(list2)]
        Result: 1→1

Step 3: Poll 2(list3)
        Add 6(list3) to heap
        Heap: [3(list2), 4(list1), 6(list3)]
        Result: 1→1→2

Continue until heap empty...

Final: 1→1→2→1→3→4→4→5→6
```

## Edge Cases
1. **Empty list of lists:** Return null
2. **Some empty lists:** Skip them (null check)
3. **Single list:** Return that list
4. **All single element lists:** Simple merge
5. **All empty lists:** Return null

## Related Problems
- **LeetCode 21:** Merge Two Sorted Lists
- **LeetCode 88:** Merge Sorted Array
- **LeetCode 1305:** All Elements in Two Binary Search Trees
- **LeetCode 264:** Ugly Number II

## Tags
`Linked List` `Heap` `Priority Queue` `Divide and Conquer`
