# LeetCode 138: Copy List with Random Pointer

## Problem Statement

A linked list of length `n` is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a **deep copy** of the list. The deep copy should consist of exactly `n` brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the `next` and `random` pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. **None of the pointers in the new list should point to nodes in the original list.**

Return the head of the copied linked list.

### Examples

**Example 1:**
```
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Visual representation:
7 -> 13 -> 11 -> 10 -> 1
     |      |     |    |
     v      v     v    v
     7      1     1    7
```

**Example 2:**
```
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

1 -> 2
|    |
v    v
2    2
```

**Example 3:**
```
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
```

**Constraints:**
- 0 <= n <= 1000
- -10^4 <= Node.val <= 10^4
- Node.random is null or is pointing to some node in the linked list

## Key Insights

1. **Two-Pass Approach**: Need two passes - one to clone nodes and next pointers, another to set random pointers
2. **Mapping Required**: Must maintain a mapping from original nodes to cloned nodes to set random pointers correctly
3. **HashMap Solution**: Using HashMap provides O(1) lookup to find the cloned node corresponding to any original node
4. **Deep Copy Requirement**: All pointers in the new list must point only to new nodes, not original nodes

## Algorithm Steps

### Two-Pass Algorithm with HashMap

1. **First Pass - Clone Nodes and Next Pointers**:
   - Traverse original list
   - For each node, create a new node with same value
   - Link new nodes with next pointers
   - Store mapping: original node -> cloned node in HashMap
   - Keep track of newHead and previous node

2. **Second Pass - Set Random Pointers**:
   - Traverse original list again
   - For each node, get its random pointer
   - Look up the cloned version of the random node from HashMap
   - Set the cloned node's random pointer to the cloned random node

3. **Return** the new head

## Complexity Analysis

- **Time Complexity**: O(n)
  - First pass: O(n) to create nodes and build HashMap
  - Second pass: O(n) to set random pointers
  - HashMap operations: O(1) per operation
  - Overall: O(n)

- **Space Complexity**: O(n)
  - HashMap storing n mappings: O(n)
  - New list nodes: O(n)
  - Overall: O(n)

## Visual Explanation

### Example: [[7,null],[13,0],[11,4],[10,2],[1,0]]

```
Original List:
Node: [value, random_index]

7(null) -> 13(0) -> 11(4) -> 10(2) -> 1(0)
  ^         |        |        |       |
  |         v        v        v       v
  |        7(0)     1(4)    11(2)    7(0)
  |_______/         |       /        /
          |_________|______/        /
                    |______________/

Step 1: First Pass - Clone nodes and next pointers
Original:  7  ->  13  ->  11  ->  10  ->  1
            |      |       |       |       |
           map    map     map     map     map
            |      |       |       |       |
            v      v       v       v       v
Cloned:    7' -> 13' ->  11' ->  10' ->  1'

HashMap:
{7 -> 7', 13 -> 13', 11 -> 11', 10 -> 10', 1 -> 1'}

Step 2: Second Pass - Set random pointers
For node 7: random = null
  -> 7'.random = null

For node 13: random = 7
  -> Look up map[7] = 7'
  -> 13'.random = 7'

For node 11: random = 1
  -> Look up map[1] = 1'
  -> 11'.random = 1'

For node 10: random = 11
  -> Look up map[11] = 11'
  -> 10'.random = 11'

For node 1: random = 7
  -> Look up map[7] = 7'
  -> 1'.random = 7'

Result:
7'(null) -> 13'(0) -> 11'(4) -> 10'(2) -> 1'(0)
             |         |         |        |
             v         v         v        v
            7'        1'        11'       7'
```

## Code Walkthrough

```java
public RandomListNode copyRandomList(RandomListNode head) {
    // HashMap to store original -> cloned node mapping
    HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

    // Pointers for first pass
    RandomListNode curr = head;
    RandomListNode prev = null;
    RandomListNode newHead = null;

    // First Pass: Clone nodes and build next pointers
    while (curr != null) {
        // Create new node with same value
        RandomListNode newNode = new RandomListNode(curr.label);

        // Link previous node to current node
        if (prev != null) {
            prev.next = newNode;
        }

        // Set head of new list
        if (newHead == null) {
            newHead = newNode;
        }

        // Store mapping
        map.put(curr, newNode);

        // Move pointers forward
        prev = newNode;
        curr = curr.next;
    }

    // Second Pass: Set random pointers
    curr = head;
    while (curr != null) {
        // Get random pointer from original node
        RandomListNode random = curr.random;

        // Look up cloned versions
        RandomListNode randomsClone = map.get(random);
        RandomListNode currentClone = map.get(curr);

        // Set random pointer in cloned node
        currentClone.random = randomsClone;

        curr = curr.next;
    }

    return newHead;
}
```

## Alternative Approach: O(1) Space (Interweaving)

While the current solution uses O(n) space, there's an O(1) space solution:

1. **Interweave nodes**: Insert cloned nodes right after original nodes
   - Original: A -> B -> C
   - After: A -> A' -> B -> B' -> C -> C'

2. **Set random pointers**: For each original node, set its clone's random
   - A'.random = A.random.next (the clone of A.random)

3. **Separate lists**: Extract the cloned list

This is more complex but achieves O(1) extra space (not counting output).

## Edge Cases

1. **Empty List**: head = null
   - Should return null

2. **Single Node with Self-Random**:
   ```
   Input: [[1,0]]
   Node 1 points to itself
   ```

3. **No Random Pointers**: All random pointers are null
   ```
   Input: [[1,null],[2,null],[3,null]]
   Essentially a simple linked list copy
   ```

4. **All Nodes Point to Head**:
   ```
   1 -> 2 -> 3 -> 4
   |    |    |    |
   v    v    v    v
   1    1    1    1
   ```

5. **Circular Random Pointers**:
   ```
   1 -> 2 -> 3
   |    |    |
   v    v    v
   2    3    1
   ```

6. **Long Chain**: n = 1000
   - Should handle efficiently with HashMap

## Related Problems

1. **LeetCode 133**: Clone Graph (similar concept of deep copying with references)
2. **LeetCode 1490**: Clone N-ary Tree
3. **LeetCode 297**: Serialize and Deserialize Binary Tree
4. **LeetCode 426**: Convert Binary Search Tree to Sorted Doubly Linked List
5. **LeetCode 61**: Rotate List

## Tags

- Linked List
- Hash Table
- Deep Copy
- Two Pointers
- Cloning
- Graph Traversal
