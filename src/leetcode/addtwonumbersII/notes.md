# 445. Add Two Numbers II

## Problem Statement
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

### Examples
```
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
       Represents: 7243 + 564

Output: 7 -> 8 -> 0 -> 7
        Represents: 7807

Input: (5) + (5)
Output: 1 -> 0
        Represents: 10
```

### Constraints
- Both linked lists are non-empty
- No leading zeros except for the number 0 itself
- Most significant digit comes first (unlike problem 2. Add Two Numbers)
- Follow-up: Cannot modify input lists or reverse them

## Approach & Solution

### Key Insights
1. **Stack-based reversal**: Since digits are in reverse order for addition (need to add from least significant), use stacks to reverse without modifying original lists
2. **Carry handling**: Process carry digit-by-digit as in traditional addition
3. **Three-phase processing**: Handle overlapping digits, then remaining digits from each list, then final carry
4. **Result construction**: Build result from most significant to least significant by popping from result stack

### Algorithm Steps
1. Push all nodes from both linked lists onto separate stacks
2. Pop elements from both stacks simultaneously and add with carry
3. Store results in another stack
4. After one stack is empty, continue with remaining stack elements
5. If carry exists after all digits processed, add it
6. Build final linked list by popping from result stack

### Complexity Analysis
- **Time Complexity**: O(max(m, n))
  - Where m and n are lengths of the two linked lists
  - First pass: O(m) + O(n) to build stacks
  - Second pass: O(max(m, n)) to process addition
  - Third pass: O(max(m, n)) to build result list
- **Space Complexity**: O(m + n)
  - Two stacks for input lists: O(m) + O(n)
  - One stack for result: O(max(m, n) + 1)
  - Total: O(m + n)

### Visualization
```
Input Lists:
    7 -> 2 -> 4 -> 3  (7243)
    5 -> 6 -> 4       (564)

Step 1: Push to stacks
Stack1: [7, 2, 4, 3]  (top at right)
Stack2: [5, 6, 4]

Step 2: Pop and add from right (least significant)
Pop: 3, 4  →  3+4=7, carry=0  →  Push 7 to result
Pop: 4, 6  →  4+6=10, carry=1  →  Push 0 to result
Pop: 2, 5  →  2+5+1=8, carry=0  →  Push 8 to result
Pop: 7     →  7+0=7, carry=0  →  Push 7 to result

Result Stack: [7, 8, 0, 7]

Step 3: Build list by popping
7 -> 8 -> 0 -> 7
```

## Code Walkthrough

The implementation uses three stacks for a clean solution:

```java
// Step 1: Convert both lists to stacks
Stack<ListNode> first = getNumber(l1);
Stack<ListNode> second = getNumber(l2);
Stack<ListNode> result = new Stack<>();

int carry = 0;

// Step 2: Process digits while both stacks have elements
while (!first.isEmpty() && !second.isEmpty()) {
    ListNode num1 = first.pop();
    ListNode num2 = second.pop();
    int sum = carry + num1.val + num2.val;
    ListNode curr = new ListNode(sum % 10);
    carry = sum / 10;
    result.push(curr);
}

// Step 3: Process remaining digits from first list
while (!first.isEmpty()) {
    ListNode num1 = first.pop();
    int sum = carry + num1.val;
    ListNode curr = new ListNode(sum % 10);
    carry = sum / 10;
    result.push(curr);
}

// Step 4: Process remaining digits from second list
while (!second.isEmpty()) {
    ListNode num2 = second.pop();
    int sum = carry + num2.val;
    ListNode curr = new ListNode(sum % 10);
    carry = sum / 10;
    result.push(curr);
}

// Step 5: Handle final carry
if (carry != 0) {
    result.push(new ListNode(carry));
}

// Step 6: Build result list from stack
ListNode head = null;
ListNode prev = null;
while (!result.isEmpty()) {
    ListNode curr = result.pop();
    if (head == null) head = curr;
    if (prev != null) prev.next = curr;
    prev = curr;
}
```

**Helper Function:**
```java
public Stack<ListNode> getNumber(ListNode l) {
    Stack<ListNode> stack = new Stack<>();
    ListNode temp = l;
    while (temp != null) {
        stack.push(temp);
        temp = temp.next;
    }
    return stack;
}
```

## Edge Cases
- **Different lengths**: [9,9,9] + [1] should give [1,0,0,0]
- **All nines with carry**: [9,9] + [1] = [1,0,0]
- **Single digit lists**: [5] + [5] = [1,0]
- **One list is null**: Handled by null checks at start
- **Zero values**: [0] + [0] = [0]
- **Carry propagation**: [9,9,9,9] + [1] = [1,0,0,0,0]

## Related Problems
- **2. Add Two Numbers**: Similar but digits in reverse order (least significant first)
- **415. Add Strings**: String-based addition
- **989. Add to Array-Form of Integer**: Adding integer to array representation
- **67. Add Binary**: Binary string addition

## Tags
`linked-list` `stack` `math` `medium`
