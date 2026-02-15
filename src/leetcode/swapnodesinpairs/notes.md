# Swap Nodes in Pairs (LeetCode 24)

## Problem Statement
Given a linked list, swap every two adjacent nodes and return its head.

You must solve the problem without modifying the values in the list's nodes. Only nodes themselves may be changed.

## Examples
```
Example 1:
Input: head = [1,2,3,4]
Output: [2,1,4,3]

Visualization:
1 -> 2 -> 3 -> 4  becomes  2 -> 1 -> 4 -> 3

Example 2:
Input: head = []
Output: []

Example 3:
Input: head = [1]
Output: [1]

Example 4:
Input: head = [1,2]
Output: [2,1]
```

## Key Insights
1. We need to swap adjacent pairs, not modify values
2. This requires changing node pointers
3. Recursive approach: swap first two, then recursively swap the rest
4. Key steps: Save the next of next, swap the first two, recursively handle the rest
5. Return the new head after swapping the first pair

## Algorithm Steps

### Approach: Recursive Swapping
1. Base case: If node is null or node.next is null, return node (nothing to swap)
2. For each call:
   - Save reference to node (will become second in pair)
   - Save reference to node.next.next (will be processed in next recursive call)
   - Make current node point to the recursively swapped rest
   - Make next node point to current node
   - Return next node (new head of this swapped pair)
3. This naturally handles the recursion for all pairs

## Complexity Analysis
- **Time Complexity:** O(n) - Visit each node once
- **Space Complexity:** O(n) - Recursion stack depth in worst case

## ASCII Visualization

```
Original: 1 -> 2 -> 3 -> 4 -> NULL

Step 1: swapInPairs(1)
  1 is not null, 1.next = 2 is not null
  node = 1
  next = 2
  next2next = 3
  Recursively: node.next = swapInPairs(3)
    This will return 4 -> 3 -> NULL
  next.next = node -> next.next = 1
  Return next (return 2)

Step 2: swapInPairs(3)
  3 is not null, 3.next = 4 is not null
  node = 3
  next = 4
  next2next = NULL
  Recursively: node.next = swapInPairs(NULL)
    Return NULL
  next.next = node -> next.next = 3
  Return next (return 4)

Step 3: swapInPairs(NULL)
  Return NULL

Building result from bottom up:
swapInPairs(NULL) = NULL
swapInPairs(3) = 4 -> 3 -> NULL
swapInPairs(1) = 2 -> 1 -> (4 -> 3 -> NULL) = 2 -> 1 -> 4 -> 3 -> NULL

Final: 2 -> 1 -> 4 -> 3 -> NULL

---

Example with odd nodes:
Original: 1 -> 2 -> 3 -> NULL

Step 1: swapInPairs(1)
  node = 1, next = 2, next2next = 3
  node.next = swapInPairs(3)

Step 2: swapInPairs(3)
  node = 3, next = NULL (3.next is NULL)
  Return 3

Building result:
swapInPairs(3) = 3
swapInPairs(1) = 2 -> 1 -> 3

Final: 2 -> 1 -> 3 -> NULL (only complete pair swapped)
```

## Code Walkthrough

```java
public ListNode swapPairs(ListNode head) {
    if (head == null)
        return head;

    return swapInPairs(head);
}

public ListNode swapInPairs(ListNode node) {
    // Base case: no pair to swap (null or single node)
    if (node == null || node.next == null)
        return node;

    // Save references
    ListNode next = node.next;           // Second node of pair
    ListNode next2next = next.next;      // First node of next pair

    // Perform swap
    next.next = node;                    // Second points to first
    node.next = swapInPairs(next2next);  // First points to recursively swapped rest

    return next;  // Return new head of this pair
}
```

## Iterative Alternative

```java
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;

    while (prev.next != null && prev.next.next != null) {
        ListNode first = prev.next;
        ListNode second = prev.next.next;

        // Swap
        prev.next = second;
        first.next = second.next;
        second.next = first;

        prev = first;
    }

    return dummy.next;
}
```

## Edge Cases
1. Empty list: null → null
2. Single node: 1 -> null → 1 -> null
3. Two nodes: 1 -> 2 -> null → 2 -> 1 -> null
4. Three nodes: 1 -> 2 -> 3 -> null → 2 -> 1 -> 3 -> null
5. Four nodes: 1 -> 2 -> 3 -> 4 -> null → 2 -> 1 -> 4 -> 3 -> null

## Related Problems
- LeetCode 25: Reverse Nodes in k-Group
- LeetCode 206: Reverse Linked List
- LeetCode 92: Reverse Linked List II
- LeetCode 141: Linked List Cycle

## Tags
- Linked List
- Recursion
- Two Pointers
- Pointer Manipulation
