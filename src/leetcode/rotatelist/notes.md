# 61. Rotate List

## Problem Statement
Given a linked list, rotate the list to the right by k places, where k is non-negative.

The rotation means taking the last k elements and moving them to the front of the list.

## Examples

### Example 1
```
Input: head = 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
Rotate 1 step right: 5->1->2->3->4->NULL
Rotate 2 steps right: 4->5->1->2->3->NULL
```

### Example 2
```
Input: head = 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
Since list has 3 nodes, k=4 is equivalent to k=1
Rotate 1 step: 2->0->1->NULL
Rotate 2 steps: 1->2->0->NULL
Rotate 3 steps: 0->1->2->NULL
Rotate 4 steps: 2->0->1->NULL (same as k=1)
```

## Key Insights

1. **Modulo operation**: k % length gives effective rotation amount
2. **List becomes circular**: Connect last node to first, then break at new position
3. **Finding rotation point**: Need to find the node after which to break the list
4. **Length calculation**: Must traverse list once to get length

## Algorithm Steps

1. Handle edge case: if head is null, return head
2. Find the length of the linked list
3. Calculate effective rotation: k = k % length
4. If k == 0, return head unchanged
5. Find the node at position (length - k - 1) - this is where to break
6. Create new head from the next node
7. Connect the old last node to the old head
8. Break the connection at the rotation point

## Complexity Analysis

**Time Complexity:** O(n)
- First pass to find length: O(n)
- Second pass to find rotation point and rebuild: O(n)
- Overall: O(n)

**Space Complexity:** O(1)
- Only using pointer variables
- No additional data structures

## ASCII Visualization

```
Original list: 1 -> 2 -> 3 -> 4 -> 5 -> NULL
               |_______________________|
Length = 5, k = 2

Effective rotation = 2 % 5 = 2

We need to move last 2 nodes to front:
Last 2 nodes: 4 -> 5
First 3 nodes: 1 -> 2 -> 3

Step 1: Connect last node (5) to first node (1):
        1 -> 2 -> 3 -> 4 -> 5 -> 1 (circular)

Step 2: Find break point (before node 4):
        Position to break = length - k - 1 = 5 - 2 - 1 = 2
        Node at position 2 = 3

Step 3: Break after node 3:
        4 -> 5 -> 1 -> 2 -> 3 -> NULL

New head = node at position (5 - 2) = 4

Final: 4 -> 5 -> 1 -> 2 -> 3 -> NULL
```

## Code Walkthrough

```java
public ListNode rotateRight(ListNode head, int k) {
    // Edge case: null list or empty list
    if (head == null)
        return head;

    // Find the length of the list
    int length = findLength(head);

    // Calculate effective rotation (handle k > length)
    int shift = k % length;
    if (shift == 0)
        return head;  // No rotation needed

    ListNode curr = head;
    ListNode prev = null;
    ListNode head1 = head;  // Keep reference to original head

    // Move to position (shift + 1) from the beginning
    // This finds the node before where we want to break
    int c = 0;
    while (curr != null && c < shift + 1) {
        prev = curr;
        curr = curr.next;
        c++;
    }

    // Continue until we reach the end
    // This moves both pointers to the end
    while (curr != null) {
        prev = curr;
        curr = curr.next;
        head = head.next;  // Move original head pointer
    }

    // Now head points to the new head (start of rotation)
    // prev points to the last node
    // Connect last node to original head
    prev.next = head1;

    // The new head is at head.next (break the connection)
    ListNode newHead = head.next;
    head.next = null;

    return newHead;
}

// Helper method to find list length
public int findLength(ListNode head) {
    int count = 0;
    for (; head != null; head = head.next, count++)
        ;
    return count;
}
```

## Edge Cases

1. **Null head**: null -> null
2. **Single node**: 1 -> 1 (rotation doesn't change it)
3. **k = 0**: List unchanged
4. **k = length**: Full rotation, list unchanged
5. **k > length**: Use k % length
6. **Two nodes with k=1**: 1->2->NULL, k=1 -> 2->1->NULL

## Alternative Approach (Using Circular Linking)

```java
public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null) return head;

    // Find length and last node
    ListNode lastNode = head;
    int length = 1;
    while (lastNode.next != null) {
        lastNode = lastNode.next;
        length++;
    }

    // Calculate effective rotation
    k = k % length;
    if (k == 0) return head;

    // Make circular
    lastNode.next = head;

    // Find new head (at position length - k)
    ListNode newHead = head;
    for (int i = 0; i < length - k; i++) {
        newHead = newHead.next;
    }

    // Break the circle
    ListNode prevNode = newHead;
    for (int i = 0; i < length; i++) {
        prevNode = prevNode.next;
    }
    prevNode.next = null;

    return newHead;
}
```

## Related Problems

- 92: Reverse Linked List II
- 24: Swap Nodes in Pairs
- 25: Reverse Nodes in k-Group
- 143: Reorder List
## Tags

`medium` `linked-list` `two-pointer` `rotation` `modulo`
