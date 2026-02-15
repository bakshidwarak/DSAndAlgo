# 637. Average of Levels in Binary Tree

## Problem Statement
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

### Examples
```
Input:
    3
   / \
  9  20
    /  \
   15   7

Output: [3, 14.5, 11]
Explanation:
Level 0: average = 3/1 = 3
Level 1: average = (9+20)/2 = 14.5
Level 2: average = (15+7)/2 = 11
```

### Constraints
- The range of node's value is in the range of 32-bit signed integer
- Tree is non-empty (has at least one node)

## Approach & Solution

### Key Insights
1. **Level-order traversal**: Use BFS (queue) to traverse tree level by level
2. **Track level information**: Store each node with its level number
3. **Aggregate by level**: Use HashMap to accumulate sum and count for each level
4. **Calculate averages**: Divide sum by count for each level to get average

### Algorithm Steps
1. Create a queue for BFS and a HashMap to store sum/count per level
2. Add root to queue with level 1
3. While queue is not empty:
   - Dequeue a node with its level
   - Update the sum and count for that level in HashMap
   - Enqueue left and right children (if exist) with level+1
4. Iterate through levels 1 to maxLevel and calculate average for each
5. Return list of averages

### Complexity Analysis
- **Time Complexity**: O(n)
  - Where n is the number of nodes in the tree
  - Each node is visited exactly once during BFS
  - HashMap operations (get/put) are O(1) average
  - Final iteration through levels is O(h) where h is height
- **Space Complexity**: O(n)
  - Queue can contain up to n/2 nodes at last level (complete tree)
  - HashMap stores up to h entries (one per level)
  - Result list stores h values
  - Total: O(n) dominated by queue size

### Visualization
```
Input Tree:
        3
       / \
      9  20
        /  \
       15   7

BFS Queue Processing:
Initial: Queue = [(3, level=1)]

Step 1: Dequeue (3, 1)
        levels[1] = {sum: 3, count: 1}
        Enqueue: (9, 2), (20, 2)
        Queue = [(9, 2), (20, 2)]

Step 2: Dequeue (9, 2)
        levels[2] = {sum: 9, count: 1}
        No children
        Queue = [(20, 2)]

Step 3: Dequeue (20, 2)
        levels[2] = {sum: 9+20=29, count: 2}
        Enqueue: (15, 3), (7, 3)
        Queue = [(15, 3), (7, 3)]

Step 4: Dequeue (15, 3)
        levels[3] = {sum: 15, count: 1}
        Queue = [(7, 3)]

Step 5: Dequeue (7, 3)
        levels[3] = {sum: 15+7=22, count: 2}
        Queue = []

Final HashMap:
Level 1: sum=3, count=1    → average = 3.0
Level 2: sum=29, count=2   → average = 14.5
Level 3: sum=22, count=2   → average = 11.0

Output: [3.0, 14.5, 11.0]
```

## Code Walkthrough

```java
public List<Double> averageOfLevels(TreeNode root) {
    // Map to store sum and count for each level
    Map<Integer, CountSum> levels = new HashMap<>();

    // Queue for BFS traversal
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(1, root));

    int maxLevel = 1;

    // BFS traversal
    while (!queue.isEmpty()) {
        Pair current = queue.remove();

        if (current.node != null) {
            // Update or create sum/count for this level
            if (levels.containsKey(current.level)) {
                CountSum countSum = levels.get(current.level);
                CountSum revised = new CountSum(
                    countSum.sum + current.node.val,
                    countSum.count + 1
                );
                levels.put(current.level, revised);
            } else {
                CountSum countSum = new CountSum(current.node.val, 1);
                levels.put(current.level, countSum);
                maxLevel++;
            }

            // Add children to queue with incremented level
            queue.add(new Pair(current.level + 1, current.node.right));
            queue.add(new Pair(current.level + 1, current.node.left));
        }
    }

    // Calculate averages for each level
    List<Double> result = new ArrayList<>();
    for (int i = 1; i <= maxLevel; i++) {
        CountSum current = levels.get(i);
        if (current != null) {
            double average = (double) current.sum / (double) current.count;
            result.add(average);
        }
    }

    return result;
}
```

**Helper Classes:**
```java
static class Pair {
    int level;
    TreeNode node;

    public Pair(int level, TreeNode node) {
        this.level = level;
        this.node = node;
    }
}

static class CountSum {
    long sum;    // Use long to handle potential overflow
    int count;

    public CountSum(long sum, int count) {
        this.sum = sum;
        this.count = count;
    }
}
```

## Edge Cases
- **Single node tree**: [1] → [1.0]
- **Left-skewed tree**: Each level has one node, averages are node values
- **Right-skewed tree**: Similar to left-skewed
- **Complete binary tree**: Last level may have many nodes
- **Large values**: Uses `long` for sum to prevent overflow
- **Negative values**: Works correctly with negative numbers
- **Level with one node**: Average equals that node's value

## Related Problems
- [**102. Binary Tree Level Order Traversal**](../binarytreelevelordertraversal/notes.md): Returns nodes grouped by level
- [**107. Binary Tree Level Order Traversal II**](../binarytreelevelordertraversal/notes.md): Bottom-up level order
- **199. Binary Tree Right Side View**: Similar BFS approach
- **515. Find Largest Value in Each Tree Row**: Similar level-wise aggregation
- [**104. Maximum Depth of Binary Tree**](../depthofabinarytree/notes.md): Level-related tree problem

## Tags
`tree` `bfs` `binary-tree` `queue` `easy`
