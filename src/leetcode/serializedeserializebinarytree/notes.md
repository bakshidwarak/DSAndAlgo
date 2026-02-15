# 297. Serialize and Deserialize Binary Tree

## Problem Statement
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how the serialization/deserialization algorithm should work.

**Key requirement:** A binary tree can be serialized to a string and deserialized back to the original tree structure.

## Examples

### Example
```
Tree:     1
         / \
        2   3
           / \
          4   5

Serialized: "1,2,3,null,null,4,5"

After deserialization, reconstruct the exact same tree structure
```

## Key Insights

1. **Preorder traversal**: Process node before children (natural for serialization)
2. **Null markers**: Use "null" to mark empty subtrees
3. **Comma separator**: Use commas to separate values
4. **Queue for deserialization**: Track which nodes to process
5. **Stateless algorithms**: Don't use class members to store state

## Algorithm Steps

### Serialization (Preorder DFS)
1. Perform preorder traversal
2. Add node value to result list
3. When encountering null, add "null"
4. Join all values with commas

### Deserialization
1. Split serialized string by commas
2. Add all parts to a queue
3. Recursively construct tree:
   - Dequeue first element
   - If "null", return null
   - Create node with value
   - Recursively construct left subtree
   - Recursively construct right subtree

## Complexity Analysis

**Time Complexity:**
- Serialization: O(n) - visit each node once
- Deserialization: O(n) - process each value once

**Space Complexity:**
- Serialization: O(n) - result list
- Deserialization: O(n) - queue and recursion stack

## ASCII Visualization

```
Original Tree:
    1
   / \
  2   3
     / \
    4   5

Serialization (Preorder):
Step 1: Visit 1 -> add "1"
Step 2: Visit 2 -> add "2"
Step 3: 2 has no left -> add "null"
Step 4: 2 has no right -> add "null"
Step 5: Visit 3 -> add "3"
Step 6: Visit 4 -> add "4"
Step 7: 4 has no children -> add "null", "null"
Step 8: Visit 5 -> add "5"
Step 9: 5 has no children -> add "null", "null"

Result: "1,2,null,null,3,4,null,null,5,null,null"

Deserialization:
Queue: [1,2,null,null,3,4,null,null,5,null,null]

construct():
  Dequeue: "1" -> Create node(1)
  construct left of 1:
    Dequeue: "2" -> Create node(2)
    construct left of 2:
      Dequeue: "null" -> return null
    construct right of 2:
      Dequeue: "null" -> return null
    return node(2)
  construct right of 1:
    Dequeue: "3" -> Create node(3)
    construct left of 3:
      Dequeue: "4" -> Create node(4)
      construct left of 4:
        Dequeue: "null" -> return null
      construct right of 4:
        Dequeue: "null" -> return null
      return node(4)
    construct right of 3:
      Dequeue: "5" -> Create node(5)
      construct left of 5:
        Dequeue: "null" -> return null
      construct right of 5:
        Dequeue: "null" -> return null
      return node(5)
    return node(3)
  return node(1)

Result: Reconstructed tree identical to original
```

## Code Walkthrough

```java
public class Codec {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string using preorder traversal
    public String serialize(TreeNode root) {
        if (root == null)
            return "null";

        ArrayList<String> serializedTree = new ArrayList<>();
        buildString(root, serializedTree);

        // Join list elements with commas
        return serializedTree.stream()
            .collect(Collectors.joining(","));
    }

    // Helper: Preorder DFS to build serialization
    public void buildString(TreeNode root, ArrayList<String> list) {
        if (root == null) {
            list.add("null");
            return;
        }

        // Preorder: process root first
        list.add(Integer.toString(root.val));
        // Then left subtree
        buildString(root.left, list);
        // Then right subtree
        buildString(root.right, list);
    }

    // Decodes your encoded data to tree
    public TreeNode deserialize(String data) {
        if (data == null || data.trim().equals("")) {
            return null;
        }

        // Split by comma
        String[] nodes = data.split(",");
        // Add to queue for easy dequeuing
        Queue<String> nodeQueue = new LinkedList<>();
        Arrays.stream(nodes).forEach(s -> nodeQueue.add(s));

        // Recursively construct tree
        return constructTree(nodeQueue);
    }

    // Helper: Recursively construct tree from queue
    public TreeNode constructTree(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        String current = queue.poll();

        // If null, return null
        if (current.equals("null"))
            return null;

        // Create node and recursively build left and right subtrees
        TreeNode head = new TreeNode(Integer.valueOf(current));
        head.left = constructTree(queue);
        head.right = constructTree(queue);

        return head;
    }
}
```

## Serialization Formats

### Preorder (Used Above)
```
    1
   / \
  2   3

Result: 1,2,null,null,3,null,null
```

### Level-order (Alternative)
```
Result: 1,2,3,null,null,null,null
```

### Inorder (Less common for this problem)
```
Result: null,2,null,1,null,3,null
```

## Edge Cases

1. **Null tree**: null -> "null"
2. **Single node**: 1 -> "1,null,null"
3. **Skewed tree**: 1->2->3->null -> "1,2,3,null,null,null,null"
4. **Complete tree**: All levels filled
5. **Empty tree after serialization**: null -> null

## Alternative: Level-Order Serialization

```java
public String serializeLevel(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> queue = new LinkedList<>();
    if (root != null) queue.offer(root);

    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node == null) {
            sb.append("null,");
        } else {
            sb.append(node.val).append(",");
            queue.offer(node.left);
            queue.offer(node.right);
        }
    }

    if (sb.length() > 0)
        sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
}
```

## Why Stateless?

The problem specifies "stateless" algorithms to prevent:
- Using class member variables to store state
- Carrying state between serialize and deserialize calls
- Making the solution tied to a specific instance

Our solution uses parameters and local variables, making it truly stateless.

## Related Problems

- 428: Serialize and Deserialize N-ary Tree
- 449: Serialize and Deserialize BST
- 331: Verify Preorder Serialization of a Binary Tree
- 606: Construct String from Binary Tree

## Tags

`hard` `tree` `serialization` `depth-first-search` `breadth-first-search` `design`
