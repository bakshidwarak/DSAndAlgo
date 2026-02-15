# Reverse Linked List - LeetCode Problem 206

## Problem Statement
Given the head of a singly linked list, reverse the list, and return the reversed list.

Follow-up: A linked list can be reversed either iteratively or recursively. Could you implement both?

## Examples

**Example 1:**
- Input: head = 1→2→3→4→5
- Output: 5→4→3→2→1

**Example 2:**
- Input: head = 1→2
- Output: 2→1

**Example 3:**
- Input: head = null
- Output: null

## Key Insights
1. **Three Pointers**: prev, current, next for iterative approach
2. **Link Direction**: Reverse each node's next pointer
3. **Iterative vs Recursive**: Both valid with different tradeoffs
4. **Head Tracking**: New head will be last node of original list
5. **No Value Change**: Only rearrange pointers

## Algorithm Steps

### Approach 1: Iterative (Stack-based conceptually)

**Step 1: Initialize**
- prev = null (new tail)
- curr = head (current node)

**Step 2: Iterate**
- While curr is not null:
  - Save next: next = curr.next
  - Reverse: curr.next = prev
  - Move prev: prev = curr
  - Move curr: curr = next

**Step 3: Return**
- Return prev (new head)

### Approach 2: Recursive

**Step 1: Base Case**
- If head is null or head.next is null: return head

**Step 2: Recursive Case**
- Recursively reverse: head.next.next = head
- Set head.next = null (break old link)
- Return new head

**Pseudocode (Iterative):**
```
function reverseList(head):
    prev = null
    curr = head

    while curr != null:
        next = curr.next      // Save next node
        curr.next = prev      // Reverse the link
        prev = curr           // Move prev forward
        curr = next           // Move curr forward

    return prev               // New head
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = number of nodes |
| Space Complexity | O(1) iterative, O(n) recursive |

**Time Analysis:**
- Single pass through list: O(n)

**Space Analysis:**
- Iterative: O(1) pointers only
- Recursive: O(n) for call stack

## ASCII Visualization

```
Iterative Reversal: 1→2→3→4→5

Initial:
prev=null, curr=1
null ← 1 2→3→4→5

Iteration 1:
next = 2
1.next = null
prev = 1, curr = 2
null ← 1 2→3→4→5

Iteration 2:
next = 3
2.next = 1
prev = 2, curr = 3
null ← 1 ← 2 3→4→5

Iteration 3:
next = 4
3.next = 2
prev = 3, curr = 4
null ← 1 ← 2 ← 3 4→5

Iteration 4:
next = 5
4.next = 3
prev = 4, curr = 5
null ← 1 ← 2 ← 3 ← 4 5→null

Iteration 5:
next = null
5.next = 4
prev = 5, curr = null
null ← 1 ← 2 ← 3 ← 4 ← 5

Return prev = 5 (new head)
Result: 5→4→3→2→1

Recursive Approach: 1→2→3→4→5

Call Stack:
reverse(1→2→3→4→5)
  reverse(2→3→4→5)
    reverse(3→4→5)
      reverse(4→5)
        reverse(5)
          return 5 (base case)

Unwinding:
4→5 becomes 5→4
  4.next.next = 4 (5.next = 4)
  4.next = null

3→4→5 becomes 5→4→3
  3.next.next = 3
  3.next = null

2→3→4→5 becomes 5→4→3→2
  2.next.next = 2
  2.next = null

1→2→3→4→5 becomes 5→4→3→2→1
  1.next.next = 1
  1.next = null

Pointer Movement (Iterative):
Original: 1→2→3→4→5

After step 1: null←1  2→3→4→5
After step 2: null←1←2  3→4→5
After step 3: null←1←2←3  4→5
After step 4: null←1←2←3←4  5
After step 5: null←1←2←3←4←5

Visual Transformation:
1→2→3→4→5
↓
1←2→3→4→5
↓
1←2←3→4→5
↓
1←2←3←4→5
↓
1←2←3←4←5
Result: 5→4→3→2→1
```

## Code Walkthrough

```java
// Approach 1: Iterative
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        // Save the next node
        ListNode next = curr.next;

        // Reverse the current node's link
        curr.next = prev;

        // Move prev and curr one step forward
        prev = curr;
        curr = next;
    }

    return prev;  // New head
}

// Approach 2: Recursive
public ListNode reverseListRecursive(ListNode head) {
    // Base case: empty list or single node
    if (head == null || head.next == null)
        return head;

    // Recursively reverse the rest of the list
    ListNode newHead = reverseListRecursive(head.next);

    // Reverse the link between current and next
    head.next.next = head;
    head.next = null;

    return newHead;
}

// Alternative: Recursive with helper
public ListNode reverseListRecursiveHelper(ListNode head) {
    reverseHelper(head, null);
    return null;  // Need to track new head differently
}

private ListNode reverseHelper(ListNode curr, ListNode prev) {
    if (curr == null)
        return prev;

    ListNode next = curr.next;
    curr.next = prev;
    return reverseHelper(next, curr);
}

// Alternative: Two-pointer iterative (more explicit)
public ListNode reverseListTwoPointer(ListNode head) {
    if (head == null || head.next == null)
        return head;

    ListNode prev = null;
    ListNode current = head;

    while (current != null) {
        ListNode temp = current.next;  // Store next
        current.next = prev;            // Point to previous
        prev = current;                 // Move prev forward
        current = temp;                 // Move current forward
    }

    return prev;
}
```

## Edge Cases

1. **Null List**: null -> null
2. **Single Node**: 1 -> 1
3. **Two Nodes**: 1→2 -> 2→1
4. **Already Reversed**: 5→4→3→2→1 -> 1→2→3→4→5
5. **Large List**: 1000 nodes handled efficiently
6. **Negative Values**: -1→-2→-3 -> -3→-2→-1
7. **Duplicate Values**: 1→1→1 -> 1→1→1

## Related Problems

1. **LeetCode 92**: Reverse Linked List II - Reverse between positions
2. **LeetCode 25**: Reverse Nodes in K-Group - Reverse k nodes
3. **LeetCode 234**: Palindrome Linked List - Uses reversal
4. **LeetCode 143**: Reorder List - Uses reversal
5. **LeetCode 24**: Swap Nodes in Pairs - Similar pointer manipulation

## Tags

- Linked List
- Recursion
- Iteration
- Two Pointers
- Classic Problem
- Easy Difficulty
- Acceptance: ~70%
