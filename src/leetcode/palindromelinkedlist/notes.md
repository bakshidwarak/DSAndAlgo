# LeetCode 234: Palindrome Linked List

## Problem Statement
Given a singly linked list, determine if it is a palindrome.

**Follow-up**: Could you do it in O(n) time and O(1) space?

## Examples

**Example 1:**
```
Input: 1->2->2->1
Output: true
```

**Example 2:**
```
Input: 1->2
Output: false
```

**Example 3:**
```
Input: 1->2->3->2->1
Output: true
```

## Key Insights

1. **Two-Pointer Technique**: Find middle of linked list
2. **Reverse Second Half**: Reverse the second half of the list
3. **Compare Halves**: Compare first half with reversed second half
4. **O(1) Space**: No extra space except for reversal in-place

## Algorithm Steps

1. **Find Middle**: Use slow and fast pointers to find center
   - Slow moves 1 step, fast moves 2 steps
   - When fast reaches end, slow is at middle

2. **Reverse Second Half**: Reverse the linked list starting from middle

3. **Compare**: Compare first half with reversed second half
   - If all values match, it's a palindrome
   - Stop at middle (don't compare middle twice for odd length)

## Complexity Analysis

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) - Three passes: find middle, reverse, compare |
| **Space Complexity** | O(1) - Only a few pointers, no extra structures |
| **Best Case** | O(n) - Must traverse entire list |
| **Worst Case** | O(n) - Full traversal required |

Perfect for the O(n) time, O(1) space follow-up!

## ASCII Visualization

```
List: 1->2->3->2->1

Step 1: Find Middle
Initial: slow=1, fast=1, current=1
  slow=1, fast=1  (after pointers move)
  slow=2, fast=3  (fast jumped 2)
  slow=3, fast=1  (fast wrapped? No, fast moves 2: 3->None, stops)

Actually, let's trace properly:
slow pointer moves: 1 → 2 → 3
fast pointer moves: 1 → 3 → None

When fast reaches end (or its next is None), slow is at middle
For 1->2->3->2->1: middle is node with value 3

Step 2: Reverse Second Half
Original second half: 2->1
Reverse: 1->2

Before reverse:
1->2->3->2->1
     ^center
       ^start of second

After reverse:
1->2->3<-2<-1
(first half forward)
(second half backward)

Step 3: Compare
First half (forward): 1, 2
Second half (backward): 1, 2
All match → Palindrome!

For even-length list: 1->2->2->1
Find middle: slow stops at second 2
Reverse second half: 1->2
Compare: 1,2 vs 1,2 → Palindrome!
```

## Code Walkthrough

```java
public boolean isPalindrome(ListNode head) {
    if (head == null)
        return true;

    // Step 1: Find the center node
    ListNode center = findCenterNode(head);
    System.out.println("Center=" + center.val);

    // Step 2: Reverse the second half
    ListNode second = reverse(center);

    // Step 3: Compare first and second halves
    ListNode left = head;
    ListNode right = second;

    while (left != center && right != null) {
        if (left.val == right.val) {
            left = left.next;
            right = right.next;
        } else {
            return false;
        }
    }
    return true;
}

// Find center using slow and fast pointers
public ListNode findCenterNode(ListNode head) {
    ListNode left = head;
    ListNode right = head.next;  // Start fast pointer one step ahead

    while (right != null) {
        left = left.next;  // Move slow by 1

        if (right.next != null) {
            right = right.next.next;  // Move fast by 2
        } else {
            break;
        }
    }

    return left;  // Slow pointer is at middle
}

// Reverse linked list starting from node
public ListNode reverse(ListNode node) {
    if (node == null || node.next == null)
        return node;

    ListNode root = node;  // New tail
    ListNode curr = node.next;
    ListNode prev = node;

    while (curr != null) {
        ListNode next = curr.next;  // Save next
        prev.next = next;  // Point prev to skip curr
        curr.next = root;  // Point curr to root
        root = curr;  // Update root

        curr = next;  // Move to next
    }

    return root;  // New head
}

public void printList(ListNode node) {
    while (node != null) {
        System.out.print(node.val + " ");
        node = node.next;
    }
    System.out.println();
}
```

**Reverse Operation Breakdown:**
```
Original: 1->2->3
root=1, prev=1, curr=2

Iteration 1:
  next=3
  prev.next=3 (1->3)
  curr.next=root (2->1)
  root=2 (new head)
  List: 2->1  3

Iteration 2:
  curr=3, next=None
  prev.next=None (3->None)
  curr.next=root (3->2)
  root=3 (new head)
  List: 3->2->1

Final: 3->2->1 (reversed)
```

## Edge Cases

1. **Single node**: `1` → true
2. **Two nodes same**: `1->1` → true
3. **Two nodes different**: `1->2` → false
4. **Odd length palindrome**: `1->2->3->2->1` → true
5. **Even length palindrome**: `1->2->2->1` → true
6. **Not palindrome**: `1->2->3` → false
7. **Empty list**: `None` → true

## Related Problems

1. **LeetCode 141** - Linked List Cycle (Two pointers to detect cycle)
2. **LeetCode 143** - Reorder List (Reverse and rearrange)
3. **LeetCode 206** - Reverse Linked List (Reversal technique)
4. **LeetCode 876** - Middle of the Linked List (Find middle)
5. **LeetCode 125** - Valid Palindrome (String palindrome variant)

## Tags

`Linked List` `Two Pointers` `Medium` `Google` `Amazon` `Facebook` `Apple`

## Alternative Approaches

### Approach 2: Stack-Based (O(n) time, O(n) space)
```java
public boolean isPalindromeStack(ListNode head) {
    Stack<Integer> stack = new Stack<>();
    ListNode curr = head;

    // Push all values to stack
    while (curr != null) {
        stack.push(curr.val);
        curr = curr.next;
    }

    // Compare while popping
    curr = head;
    while (curr != null) {
        if (curr.val != stack.pop())
            return false;
        curr = curr.next;
    }
    return true;
}
```

### Approach 3: Recursion-Based (O(n) time, O(n) space)
```
- Use recursion to reach end
- Compare while unwinding
- Space: O(n) for call stack
```

## Implementation Notes

1. **Fast Pointer Start**: Start at head.next, not head
2. **Odd vs Even**: Code handles both naturally
3. **Center Position**: For odd length, middle node is skipped in comparison
4. **Reversal**: In-place reversal is safe (no external references needed)
5. **Comparison**: Stop before comparing middle node twice

## Common Pitfalls

1. **Fast Pointer Check**: Must check fast.next before accessing fast.next.next
2. **Center Node**: Include middle node in first half for odd-length lists
3. **Comparison Loop**: Condition `left != center` handles different parities
4. **Reversal Logic**: Careful pointer manipulation to avoid breaking links

## Performance Comparison

```
Approach      Time    Space   Notes
Stack-based   O(n)    O(n)    Simpler but uses extra space
Reverse half  O(n)    O(1)    Optimal, meets follow-up
Recursive     O(n)    O(n)    More space due to call stack
```

## Notes

- This is a classic interview problem for linked lists
- Demonstrates importance of two-pointer technique
- Tests understanding of linked list reversal
- Great practice for in-place modifications
- The O(1) space solution is elegant and efficient
