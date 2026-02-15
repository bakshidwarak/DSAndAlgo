# Remove Nth Node From End of List - LeetCode Problem 19

## Problem Statement
Given a linked list, remove the n-th node from the end of list and return its head.

Constraints:
- Given n will always be valid
- Follow-up: Could you do this in one pass?

## Examples

**Example 1:**
- Input: head = 1→2→3→4→5, n = 2
- Output: 1→2→3→5

**Example 2:**
- Input: head = 1, n = 1
- Output: empty

**Example 3:**
- Input: head = 1→2, n = 2
- Output: 2

**Example 4:**
- Input: head = 1→2, n = 1
- Output: 1→2

## Key Insights
1. **Two Pointers**: Fast pointer advances n steps, then both advance together
2. **One Pass**: Achieve with fast-slow pointer technique
3. **Distance**: When fast reaches end, slow is n positions behind
4. **Head Removal**: Special case when removing head node
5. **Dummy Node**: Alternative approach to handle edge cases

## Algorithm Steps

### Approach: Two Pointers (One Pass)

**Step 1: Setup**
- Create two pointers: slow, fast both at head
- Create prev = head to track previous node
- k = 0

**Step 2: Advance Fast**
- Move fast pointer n steps ahead
- If fast becomes null, we're removing head

**Step 3: Advance Both**
- Move both slow and fast until fast reaches null
- Track prev

**Step 4: Remove**
- Set prev.next = slow.next

**Step 5: Return**
- Return head (or head.next if removing head)

**Pseudocode:**
```
function removeNthFromEnd(head, n):
    if head == null:
        return head

    slow = head
    fast = head
    prev = head
    k = 0

    // Advance fast pointer n steps
    while fast != null and k < n:
        fast = fast.next
        k++

    // If fast == null, we're removing the head
    if fast == null:
        return head.next

    // Advance both pointers
    while fast != null:
        prev = slow
        slow = slow.next
        fast = fast.next

    // Remove the node
    if prev != null:
        prev.next = slow.next

    return head
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = list length |
| Space Complexity | O(1) constant space |

**Time Analysis:**
- Single pass through list: O(n)

**Space Analysis:**
- Only pointers used: O(1)

## ASCII Visualization

```
Example: 1→2→3→4→5, n=2

Initial Setup:
head → |1| → |2| → |3| → |4| → |5| → null
       ↑slow ↑prev
       ↑fast

Step 1: Advance fast n=2 steps
head → |1| → |2| → |3| → |4| → |5| → null
       ↑slow ↑prev
              ↑fast

Step 2: Advance both while fast != null
head → |1| → |2| → |3| → |4| → |5| → null
              ↑slow ↑prev
                     ↑fast

Second iteration:
head → |1| → |2| → |3| → |4| → |5| → null
                     ↑slow ↑prev
                            ↑fast

Step 3: fast == null, stop
slow points to node 4, prev points to node 3
Remove: prev.next = slow.next
        node3.next = node5

Result:
head → |1| → |2| → |3| → |5| → null

Example where head is removed: 1→2, n=2

Initial:
head → |1| → |2| → null
       ↑slow ↑prev
       ↑fast

Step 1: Advance fast 2 steps
       |1| → |2| → null → null
       ↑slow ↑prev
              ↑fast
              (after step 1)
                     (after step 2)

fast == null, so return head.next
Result: 2→null

Pointer Movement Visualization:
n=2, list: 1→2→3→4→5

Timeline:
       slow fast
Start: H    H
After fast advances 2: H  3
Move both:
       1    4
       2    5
       3    null(stop)

Now slow=4, prev=3
prev.next = slow.next (5)
Result: 1→2→3→5
```

## Code Walkthrough

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null)
        return head;

    ListNode slow = head;
    ListNode fast = head;
    ListNode prev = head;
    int k = 0;

    // Advance fast pointer n steps
    while (fast != null && k < n) {
        fast = fast.next;
        k++;
    }

    // If fast == null, we need to remove the head
    if (fast == null) {
        head = head.next;
        return head;
    }

    // Move both pointers until fast reaches the end
    while (fast != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next;
    }

    // Remove the nth node
    if (prev != null)
        prev.next = slow.next;

    return head;
}

// Alternative: Using dummy node (cleaner)
public ListNode removeNthFromEndDummy(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode slow = dummy;
    ListNode fast = dummy;

    // Advance fast pointer n+1 steps
    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }

    // Advance both until fast reaches end
    while (fast != null) {
        slow = slow.next;
        fast = fast.next;
    }

    // Remove the node
    slow.next = slow.next.next;

    return dummy.next;
}

// Alternative: Two pass approach (for comparison)
public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
    // First pass: count nodes
    int count = 0;
    ListNode curr = head;
    while (curr != null) {
        count++;
        curr = curr.next;
    }

    // If removing head
    if (count == n) {
        return head.next;
    }

    // Second pass: remove at position (count - n)
    curr = head;
    for (int i = 0; i < count - n - 1; i++) {
        curr = curr.next;
    }
    curr.next = curr.next.next;

    return head;
}
```

## Edge Cases

1. **Single Node**: 1, n=1 -> null
2. **Remove Head**: 1→2, n=2 -> 2
3. **Remove Tail**: 1→2→3, n=1 -> 1→2
4. **Remove Middle**: 1→2→3→4, n=2 -> 1→2→4
5. **Two Nodes**: 1→2, n=1 -> 1 or n=2 -> 2
6. **Large List**: Handle for n up to 5000
7. **n = length**: Remove first node

## Related Problems

1. **LeetCode 206**: Reverse Linked List
2. **LeetCode 21**: Merge Two Sorted Lists
3. **LeetCode 23**: Merge k Sorted Lists
4. **LeetCode 25**: Reverse Nodes in K-Group
5. **LeetCode 328**: Odd Even Linked List
## Tags

- Linked List
- Two Pointers
- One Pass
- Fast-Slow Pointer
- Medium Difficulty
- Acceptance: ~37%
