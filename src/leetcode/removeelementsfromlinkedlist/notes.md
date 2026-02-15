# Remove Elements from Linked List - LeetCode Problem 203

## Problem Statement
Remove all elements from a linked list of integers that have value val.

Constraints:
- Return the head of the modified linked list
- Handle case where head itself needs to be removed

## Examples

**Example 1:**
- Input: head = 1→2→6→3→4→5→6, val = 6
- Output: 1→2→3→4→5

**Example 2:**
- Input: head = 7→7→7→7, val = 7
- Output: empty

**Example 3:**
- Input: head = 1→1→1, val = 1
- Output: empty

## Key Insights
1. **Head Removal**: Must handle case where head node needs removal
2. **Prev Pointer**: Track previous node to update links
3. **Single Pass**: One iteration through the list
4. **Link Manipulation**: Update next pointers to skip target nodes
5. **Edge Cases**: All nodes same value, empty list, etc.

## Algorithm Steps

### Approach: Traversal with Previous Pointer

**Step 1: Handle Null**
- If head is null, return null

**Step 2: Initialize Pointers**
- current = head
- prev = null

**Step 3: Traverse**
- While current is not null:
  - If current.val == val:
    - If prev is null (head removal): head = head.next
    - Else: prev.next = current.next
  - Else: prev = current
  - Move current forward

**Step 4: Return**
- Return updated head

**Pseudocode:**
```
function removeElements(head, val):
    if head == null:
        return null

    current = head
    prev = null

    while current != null:
        if current.val == val:
            if prev == null:
                head = head.next
            else:
                prev.next = current.next
        else:
            prev = current

        current = current.next

    return head
```

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) where n = number of nodes |
| Space Complexity | O(1) constant space |

**Time Analysis:**
- Single traversal of list: O(n)
- Each operation: O(1)

## ASCII Visualization

```
Example: 1→2→6→3→4→5→6, val=6

Initial State:
head → |1| → |2| → |6| → |3| → |4| → |5| → |6| → null
current=1, prev=null

Step 1: current=1, 1!=6, move prev
head → |1| → |2| → |6| → |3| → |4| → |5| → |6| → null
       ↑curr  ↑prev

Step 2: current=2, 2!=6, move prev
head → |1| → |2| → |6| → |3| → |4| → |5| → |6| → null
              ↑curr ↑prev

Step 3: current=6, 6==6, remove (prev.next = current.next)
       Before: |2| → |6| → |3|
       After:  |2| → |3|
head → |1| → |2| → |3| → |4| → |5| → |6| → null
       ↑prev  ↑curr (move without prev)

Continue...
Step 4: current=3, 3!=6, move prev
head → |1| → |2| → |3| → |4| → |5| → |6| → null
                    ↑curr ↑prev

Step 5: current=4, 4!=6, move prev
head → |1| → |2| → |3| → |4| → |5| → |6| → null
                           ↑curr ↑prev

Step 6: current=5, 5!=6, move prev
head → |1| → |2| → |3| → |4| → |5| → |6| → null
                                  ↑curr ↑prev

Step 7: current=6, 6==6, remove
       Before: |5| → |6| → null
       After:  |5| → null
head → |1| → |2| → |3| → |4| → |5| → null
       ↑prev  ↑curr (null)

Final Result: 1→2→3→4→5

Example where head is removed: 6→7→8, val=6

Step 1: current=6, 6==6
       prev=null, so head = head.next
head: 6→7→8  becomes  head: 7→8
       ↓
6→7→8  becomes  7→8

Result: 7→8
```

## Code Walkthrough

```java
public ListNode removeElements(ListNode head, int val) {
    if (head == null)
        return null;

    ListNode current = head;
    ListNode prev = null;

    while (current != null) {
        // Check if current node value matches target
        if (current.val == val) {
            // Handle head removal
            if (prev == null) {
                head = head.next;
            } else {
                // Bypass current node
                prev.next = current.next;
            }
        } else {
            // Keep node, move prev forward
            prev = current;
        }

        // Always move to next node
        current = current.next;
    }

    return head;
}

// Alternative: Recursive approach
public ListNode removeElementsRecursive(ListNode head, int val) {
    if (head == null)
        return null;

    // Process rest of list first
    head.next = removeElementsRecursive(head.next, val);

    // Check current node
    if (head.val == val)
        return head.next;  // Skip current
    else
        return head;       // Keep current
}

// Alternative: With dummy node (cleaner)
public ListNode removeElementsDummy(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode current = head;

    while (current != null) {
        if (current.val == val) {
            prev.next = current.next;
        } else {
            prev = current;
        }
        current = current.next;
    }

    return dummy.next;
}
```

## Edge Cases

1. **Empty List**: null -> null
2. **Single Node Keep**: 1 (val=2) -> 1
3. **Single Node Remove**: 1 (val=1) -> null
4. **All Remove**: 1→1→1 (val=1) -> null
5. **No Remove**: 1→2→3 (val=4) -> 1→2→3
6. **Head Remove**: 6→2→3 (val=6) -> 2→3
7. **Tail Remove**: 1→2→6 (val=6) -> 1→2
8. **Multiple Remove**: 1→6→2→6→3 (val=6) -> 1→2→3

## Related Problems

1. **LeetCode 27**: Remove Element - Similar for array
2. **LeetCode 19**: Remove Nth Node From End of List
3. **LeetCode 237**: Delete Node in a Linked List
4. **LeetCode 83**: Remove Duplicates from Sorted List
5. **LeetCode 1474**: Delete N Nodes After M Nodes
## Tags

- Linked List
- Two Pointers
- In-Place Modification
- Edge Cases
- Easy Difficulty
- Acceptance: ~45%
