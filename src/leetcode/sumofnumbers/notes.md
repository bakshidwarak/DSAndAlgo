# Add Two Numbers (LeetCode 2)

## Problem Statement
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each node contains a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

## Examples
```
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807
Visualization:
      2 -> 4 -> 3
  +   5 -> 6 -> 4
  _______________
      7 -> 0 -> 8

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
Explanation: 9999999 + 9999 = 10009998
Visualization:
      9 -> 9 -> 9 -> 9 -> 9 -> 9 -> 9
  +                   9 -> 9 -> 9 -> 9
  _________________________________
      8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
```

## Key Insights
1. Numbers are stored in reverse order, so we process from the least significant digit
2. We need to handle carry from addition at each position
3. One list might be longer than the other
4. After processing both lists, if there's still a carry, we need an extra node
5. We can traverse both lists simultaneously, handling null pointers for shorter lists

## Algorithm Steps

### Approach: Single Pass with Carry
1. Initialize carry = 0, head = null, result = null
2. While either l1 or l2 is not null:
   - Get values from current nodes (0 if node is null)
   - Calculate sum = val1 + val2 + carry
   - Extract digit = sum % 10, carry = sum / 10
   - Create new node with digit
   - Link it to result
   - Move to next nodes in l1 and l2
3. After loop, if carry exists, create one more node
4. Return head

## Complexity Analysis
- **Time Complexity:** O(max(m, n)) - Where m and n are lengths of the two lists
- **Space Complexity:** O(max(m, n)) - For the result list (not counting input)

## ASCII Visualization

```
l1: 2 -> 4 -> 3        (represents 342)
l2: 5 -> 6 -> 4        (represents 465)

Step 1: Add 2 + 5 = 7, carry = 0
Result: 7 -> null

Step 2: Add 4 + 6 + 0 = 10, digit = 0, carry = 1
Result: 7 -> 0 -> null

Step 3: Add 3 + 4 + 1 = 8, digit = 8, carry = 0
Result: 7 -> 0 -> 8 -> null

Both lists exhausted, carry = 0
Final: 7 -> 0 -> 8 (represents 807)

---

l1: 9 -> 9 -> 9        (represents 999)
l2: 9 -> 9 -> 9 -> 9   (represents 9999)

Step 1: 9 + 9 = 18, digit = 8, carry = 1
Result: 8

Step 2: 9 + 9 + 1 = 19, digit = 9, carry = 1
Result: 8 -> 9

Step 3: 9 + 9 + 1 = 19, digit = 9, carry = 1
Result: 8 -> 9 -> 9

Step 4: 0 + 9 + 1 = 10, digit = 0, carry = 1
Result: 8 -> 9 -> 9 -> 0

Step 5: l1 exhausted, carry = 1, create node with 1
Result: 8 -> 9 -> 9 -> 0 -> 1

Final: 8 -> 9 -> 9 -> 0 -> 1 (represents 10998, which is 999 + 9999)
```

## Code Walkthrough

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null;      // Head of result list
    ListNode result = null;    // Tail of result list (for linking)
    int carry = 0;             // Carry from addition

    while (l1 != null || l2 != null) {
        // Get values from both lists (0 if null)
        int num = 0;
        if (l1 != null)
            num += l1.val;
        if (l2 != null)
            num += l2.val;

        // Add carry
        num = num + carry;

        // Extract digit and update carry
        int val = num % 10;
        carry = num / 10;

        // Create new node
        ListNode current = new ListNode(val);

        // Link to result
        if (result == null) {
            head = current;  // First node
        } else {
            result.next = current;  // Link to previous
        }
        result = current;

        // Move to next nodes
        if (l1 != null)
            l1 = l1.next;
        if (l2 != null)
            l2 = l2.next;
    }

    // Add carry node if exists
    if (carry != 0) {
        ListNode current = new ListNode(carry);
        if (result != null)
            result.next = current;
    }

    return head;
}
```

## Edge Cases
1. Different lengths:
   - [9,9] + [1] = [0,0,1]
2. One number is zero: [0] + [0] = [0]
3. Carry at the end:
   - [9,9,9] + [1] = [0,0,0,1]
4. Multiple carries:
   - [9,9,9,9,9] + [9,9,9,9,9] = [8,9,9,9,9,1]

## Related Problems
- LeetCode 445: Add Two Numbers II (Forward order)
- LeetCode 67: Add Binary
- LeetCode 415: Add Strings
- LeetCode 989: Add to Array-Form of Integer

## Tags
- Linked List
- Math
- Simulation
- Carry Handling
