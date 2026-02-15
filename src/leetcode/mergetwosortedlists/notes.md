# LeetCode 21: Merge Two Sorted Lists

## Problem Statement
Merge two sorted linked lists and return a new list. The new list should be created by splicing together the nodes of the two input lists.

## Difficulty
Easy

## Examples

### Example 1
```
Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

### Example 2
```
Input: l1 = [], l2 = [0]
Output: [0]
```

## Key Insights
1. **Two Pointer Approach**: Maintain pointers in both lists
2. **Comparison and Linking**: Compare current nodes and link smaller to result
3. **Remaining Elements**: After one list exhausts, append remaining from other
4. **Head Tracking**: Need to track both head and current pointer for result

## Algorithm Steps
1. Initialize three pointers: newHead (result), node1 (l1), node2 (l2)
2. While both lists have elements:
   - Compare node1.val and node2.val
   - Link smaller node to result
   - Advance that list's pointer
3. After loop, append remaining elements from non-empty list
4. Return head of merged list

## Complexity Analysis
- **Time Complexity:** O(m + n) where m, n are lengths of lists
- **Space Complexity:** O(1) - Only using pointers (excluding output)

## ASCII Visualization

```
l1: 1 → 2 → 4 → null
l2: 1 → 3 → 4 → null

Step 1: Compare 1 vs 1 → pick 1(l1)
Result: 1

Step 2: Compare 2 vs 1 → pick 1(l2)
Result: 1 → 1

Step 3: Compare 2 vs 3 → pick 2(l1)
Result: 1 → 1 → 2

Step 4: Compare 4 vs 3 → pick 3(l2)
Result: 1 → 1 → 2 → 3

Step 5: Compare 4 vs 4 → pick 4(l1)
Result: 1 → 1 → 2 → 3 → 4

Step 6: l2 has 4 left, append
Result: 1 → 1 → 2 → 3 → 4 → 4

Final: 1 → 1 → 2 → 3 → 4 → 4
```

## Edge Cases
1. **One list is empty:** Return the other list
2. **Both lists empty:** Return null
3. **Single node in each:** Merge correctly
4. **Duplicate values:** Handle correctly
5. **Very long lists:** Should work efficiently

## Related Problems
- **LeetCode 23:** Merge K Sorted Lists
- **LeetCode 88:** Merge Sorted Array
- **LeetCode 148:** Sort List
- **LeetCode 1305:** All Elements in Two Binary Search Trees

## Tags
`Linked List` `Two Pointers` `Sorting` `Merge`
