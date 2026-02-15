# Reverse K Nodes in Linked List - LeetCode Problem 25

## Problem Statement
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

Constraints:
- k is a positive integer and is less than or equal to the length of the linked list
- If the number of nodes is not a multiple of k, left-out nodes in the end should remain as is
- You may not alter the values in the nodes, only nodes itself may be changed
- Only constant memory is allowed

## Examples

**Example 1:**
- Input: head = 1→2→3→4→5, k = 2
- Output: 2→1→4→3→5

**Example 2:**
- Input: head = 1→2→3→4→5, k = 3
- Output: 3→2→1→4→5

**Example 3:**
- Input: head = 1→2, k = 3
- Output: 1→2

## Key Insights
1. **Group Processing**: Reverse k nodes at a time, recursively process remaining
2. **Boundary Check**: Verify at least k nodes exist before reversing
3. **Recursion**: Recursive call on next k-group, link result back
4. **Link Management**: Critical to maintain correct pointers
5. **In-Place**: Only rearrange node pointers, not values

## Algorithm Steps

### Approach: Recursive Reversal

**Step 1: Check if k nodes exist**
- Traverse k steps to verify enough nodes
- If not enough, return current head (no reversal)

**Step 2: Reverse k nodes**
- Use standard reversal technique
- Keep track of new head and previous node

**Step 3: Link next group**
- Recursively call on remaining list
- Link current group's tail to next group's head

**Step 4: Return**
- Return new head of this group

**Pseudocode:**
```
function reverseKGroup(head, k):
    if head == null or head.next == null or not ifKNodes(head, k):
        return head

    curr = head.next
    prev = head
    next = null
    i = 0

    // Reverse k nodes
    while curr != null and i < k-1:
        next = curr.next
        curr.next = head
        prev.next = next
        head = curr
        curr = next
        i++

    // Link to next group
    if next != null:
        prev.next = reverseKGroup(next, k)

    return head

function ifKNodes(node, k):
    count = 0
    while node != null:
        count++
        node = node.next
        if count == k:
            return true
    return false
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = number of nodes |
| Space Complexity | O(n/k) for recursion depth |

**Time Analysis:**
- Each node processed once: O(n)
- Reversal: O(k) per group, n/k groups

**Space Analysis:**
- Recursion depth: O(n/k)
- Each level: O(1) space for pointers

## ASCII Visualization

```
Example: 1→2→3→4→5, k=2

Initial Group [1,2]:
head=1, curr=2
1 ← 2   3→4→5
Reversed to: 2→1

Process:
curr=2, i=0 (i < k-1 = 1)
  next = 3
  curr.next = head (2.next = 1)
  prev.next = next (1.next = 3)
  head = curr (head = 2)
  curr = next (curr = 3)
  i = 1

Result of first group:
2→1→[next group]

Recursive call on 3→4→5, k=2:
Group [3,4]:
head=3, curr=4
4→3→5
Reversed to: 4→3

Link back:
1.next = 4 (result of recursive call)
Final: 2→1→4→3→5

Visual Step-by-Step Reversal (k=2):

Original: 1→2→3→4→5

Group 1: Reverse [1,2]
Step 1:
head   curr
|1|   |2|→3→4→5
prev

Iteration 1 (i=0):
next = 3
2.next = 1
1.next = 3
head = 2
curr = 3

head=2, prev=1, curr=3
2→1→3→4→5

Group 2: Reverse [3,4] (recursive)
Similar process...
4→3→5

Final Link:
Group1→Group2
2→1→4→3→5

Example with non-multiple: 1→2→3→4→5→6, k=3

Group 1: [1,2,3] → 3→2→1
Group 2: [4,5,6] → 6→5→4
Result: 3→2→1→6→5→4

Example with remainder: 1→2→3→4→5, k=3

Group 1: [1,2,3] → 3→2→1
Remaining: [4,5] (only 2 nodes, k=3, don't reverse)
Result: 3→2→1→4→5
```

## Code Walkthrough

```java
public ListNode reverseKGroup(ListNode head, int k) {
    // Check if we have at least k nodes
    if (head == null || head.next == null || !ifknodes(head, k))
        return head;

    ListNode curr = head.next;
    ListNode prev = head;
    ListNode next = null;
    int i = 0;

    // Reverse k nodes
    while (curr != null && i < k - 1) {
        next = curr.next;      // Save next
        curr.next = head;      // Reverse link
        prev.next = next;      // Move tail forward
        head = curr;           // Update head
        curr = next;           // Move current
        i++;
    }

    // Link to next group (recursively)
    if (next != null)
        prev.next = reverseKGroup(next, k);

    return head;
}

// Helper: Check if at least k nodes exist
public boolean ifknodes(ListNode node, int k) {
    int count = 0;

    while (node != null) {
        count++;
        node = node.next;
        if (count == k)
            return true;
    }

    return false;
}

// Alternative: Iterative approach with helper function
public ListNode reverseKGroupIterative(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;

    while (prev != null) {
        // Check if k nodes exist
        ListNode temp = prev;
        for (int i = 0; i < k; i++) {
            temp = temp.next;
            if (temp == null)
                return dummy.next;
        }

        // Reverse k nodes
        ListNode prev_group = prev;
        ListNode curr = prev.next;

        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev_group;
            prev_group = curr;
            curr = next;
        }

        // Connect groups
        ListNode temp2 = prev.next;
        prev.next = prev_group;
        prev = temp2;
    }

    return dummy.next;
}
```

## Edge Cases

1. **Exact Multiple**: 1→2→3→4, k=2 -> 2→1→4→3
2. **k=1**: No reversal, return as is
3. **k >= length**: No reversal if k > length
4. **Remainder**: 1→2→3→4→5, k=2 -> 2→1→4→3→5 (5 stays)
5. **Single Node**: 1, k=1 -> 1
6. **Two Nodes, k=2**: 1→2, k=2 -> 2→1
7. **Large k**: k=10 for 5-node list -> return unchanged
8. **k=0**: Invalid, but handle gracefully

## Related Problems

1. **LeetCode 206**: Reverse Linked List - Reverse entire list
2. **LeetCode 92**: Reverse Linked List II - Reverse between positions
3. **LeetCode 141**: Linked List Cycle - Detect cycles
4. **LeetCode 143**: Reorder List - Reorder with reversal
5. **LeetCode 24**: Swap Nodes in Pairs - k=2 special case

## Tags

- Linked List
- Recursion
- Reversal
- Group Processing
- Hard Difficulty
- Acceptance: ~42%
