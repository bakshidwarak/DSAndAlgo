# LeetCode 237: Delete Node in a Linked List

## Problem Statement

Write a function to **delete a node** (except the tail) in a singly linked list, given only access to that node.

### Examples

**Example 1:**
```
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list
should become 4 -> 1 -> 9 after calling your function.
```

**Example 2:**
```
Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list
should become 4 -> 5 -> 9 after calling your function.
```

**Example 3:**
```
Input: head = [1,2,3,4], node = 3
Output: [1,2,4]
```

**Constraints:**
- The number of the nodes in the given list is in the range [2, 1000]
- -1000 <= Node.val <= 1000
- The value of each node in the list is unique
- The node to be deleted is in the list and is not a tail node

## Key Insights

1. **No Previous Pointer**: We don't have access to the previous node
2. **Cannot Actually Delete**: Without prev pointer, we can't change the previous node's next pointer
3. **Copy Next Node**: The trick is to copy the next node's data to current node
4. **Skip Next Node**: Then make current node point to next.next
5. **Guaranteed Not Tail**: Problem guarantees the node is not the tail, so node.next exists

## Algorithm Steps

1. **Check Validity**:
   - If node is null or node.next is null, return (safety check)

2. **Copy Next Node's Value**:
   - Copy node.next.val to node.val

3. **Skip Next Node**:
   - Set node.next = node.next.next

This effectively "deletes" the current node by making it look like the next node and removing the next node from the list.

## Complexity Analysis

- **Time Complexity**: O(1)
  - Only constant operations: copy value and update pointer
  - No traversal needed

- **Space Complexity**: O(1)
  - No extra space used
  - Only pointer manipulation

## Visual Explanation

### Example: Delete node with value 5

```
Initial state:
head -> 4 -> 5 -> 1 -> 9
             ^
           node

We want to delete node (value 5), but we only have access to it.

Step 1: Copy next node's value (1) to current node
head -> 4 -> 1 -> 1 -> 9
             ^
           node
         (value changed from 5 to 1)

Step 2: Skip the next node
head -> 4 -> 1 -----> 9
             ^    X
           node   (removed)

Result:
head -> 4 -> 1 -> 9

The node with value 5 is effectively "deleted" even though we
actually deleted the node that originally contained 1.
```

### Detailed Step-by-Step

```
Before:
  4  ->  5  ->  1  ->  9
        node  next

Step 1: node.val = node.next.val
  4  ->  1  ->  1  ->  9
        node  next
    (changed)

Step 2: node.next = node.next.next
  4  ->  1  -------->  9
        node
                 (1 skipped)

After:
  4  ->  1  ->  9
        node
```

## Code Walkthrough

```java
public void deleteNode(ListNode node) {
    // Safety check: ensure node and next node exist
    if (node == null || node.next == null)
        return;

    /*
     * Since we cannot actually remove the node (no access to previous node),
     * we copy the next node's data into the current node and then remove
     * the next node by skipping it.
     *
     * This is equivalent to "deleting" the current node from the list.
     */

    // Step 1: Copy the next node's value to current node
    node.val = node.next.val;

    // Step 2: Skip the next node (effectively deleting it)
    node.next = node.next.next;

    // The original next node is now unreachable and will be garbage collected
}
```

## Why This Works

1. **From External Perspective**: The list appears to have the target node removed
2. **Actual Implementation**: We physically remove a different node (the next one)
3. **Value Swap**: By copying the next node's value, the current node "becomes" the next node
4. **Memory**: The actual node object being deleted is node.next, not node
5. **Constraints**: This only works because:
   - We're guaranteed node is not the tail
   - We don't care about the actual object identity
   - Linked list values can be duplicated

## Edge Cases

1. **Node at Position 2 in List**: Delete second node
   ```
   Input: 1 -> 2 -> 3, node = 2
   Output: 1 -> 3
   ```

2. **Node Near Tail**: Delete second-to-last node
   ```
   Input: 1 -> 2 -> 3 -> 4, node = 3
   Output: 1 -> 2 -> 4
   ```

3. **Only Two Nodes**: Minimum list size
   ```
   Input: 1 -> 2, node = 1
   Output: 2
   ```

4. **Large List**: n = 1000
   - Still O(1) operation

5. **Negative Values**: node.val can be negative
   ```
   Input: -1 -> -2 -> -3, node = -2
   Output: -1 -> -3
   ```

## Important Notes

1. **Limitation**: This technique doesn't work for tail node
   - Tail has no next node to copy from
   - Problem explicitly excludes this case

2. **Object Identity**: The actual node object isn't deleted, its next is
   - This matters if there are external references to nodes

3. **Not Traditional Delete**: This is a clever workaround, not standard deletion
   - In normal deletion, we'd need access to previous node

4. **No Head Pointer Needed**: We don't need or modify the head pointer

## Comparison with Normal Deletion

### Normal Deletion (with previous pointer):
```java
void deleteNode(ListNode prev, ListNode node) {
    prev.next = node.next;  // Skip the node to delete
}
```

### This Problem (without previous pointer):
```java
void deleteNode(ListNode node) {
    node.val = node.next.val;      // Copy next value
    node.next = node.next.next;    // Skip next node
}
```

## Related Problems

1. **LeetCode 203**: Remove Linked List Elements
2. **LeetCode 19**: Remove Nth Node From End of List
3. **LeetCode 83**: Remove Duplicates from Sorted List
4. **LeetCode 82**: Remove Duplicates from Sorted List II
5. **LeetCode 1474**: Delete N Nodes After M Nodes of a Linked List

## Tags

- Linked List
- Pointer Manipulation
- Clever Trick
- Constant Space
- Constant Time
