# LeetCode 433: Minimum Genetic Mutation

## Problem Statement
A gene string can be represented by an 8-character string with choices from "A", "C", "G", "T". A mutation changes ONE single character. Given start gene, end gene, and a bank of valid mutations, find the **minimum number of mutations** needed to go from start to end. If impossible, return -1.

## Difficulty
Medium

## Examples

### Example 1
- **Input:** `start = "AACCGGTT"`, `end = "AACCGGTA"`, `bank = ["AACCGGTA"]`
- **Output:** `1`
- **Explanation:** Only 1 mutation needed (last char T→A)

### Example 2
- **Input:** `start = "AACCGGTT"`, `end = "AAACGGTA"`, `bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]`
- **Output:** `2`
- **Explanation:** Multiple mutations needed

### Example 3
- **Input:** `start = "AAAAACCC"`, `end = "AACCCCCC"`, `bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]`
- **Output:** `3`
- **Explanation:** Chain of 3 mutations

## Key Insights
1. **Graph Search Problem**: Each valid gene is a node, mutations are edges
2. **BFS Approach**: Find shortest path from start to end
3. **Valid Transitions**: Only use genes from the bank (one mutation away)
4. **Visited Set**: Track visited mutations to avoid cycles
5. **Level-by-Level**: BFS guarantees shortest path

## Algorithm Steps
1. Create HashSet from bank for quick lookup
2. Use queue for BFS with (mutation_string, level) pairs
3. Mark start as visited
4. While queue not empty:
   - Dequeue current mutation and level
   - If matches end, return level
   - For each position (0-7):
     - Try all 4 possible characters (A,C,G,T)
     - If different from current and in bank and not visited:
       - Mark as visited
       - Enqueue with level+1
5. If loop ends, return -1 (no path found)

## Complexity Analysis
- **Time Complexity:** O(N * 8 * 4) = O(N) where N = size of bank
- **Space Complexity:** O(N) - Queue and visited set

## ASCII Visualization

```
start = "AACCGGTT"
end = "AACCGGTA"
bank = ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

BFS Tree:
                AACCGGTT (level 0)
                    |
                AACCGGTA (level 1) ← Found end!
                Return 1

Longer example with path:
start = "AACCGGTT"
end = "AAACGGTA"
bank = ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

Level 0: AACCGGTT
         |
Level 1: AACCGGTA, AACCGCTA, ...
         |          |
Level 2: ... AAACGGTA ← Found end!
         Return 2
```

## Edge Cases
1. **Start equals end:** Return 0
2. **End not in reachable graph:** Return -1
3. **Empty bank:** Return -1 (unless start=end)
4. **Bank with only start:** Can't progress
5. **Multiple paths of same length:** Return minimum (first found by BFS)

## Related Problems
- **LeetCode 127:** Word Ladder
- **LeetCode 90:** Subsets II
- **LeetCode 1258:** Synonymous Sentences
- **LeetCode 1345:** Jump Game IV

## Tags
`BFS` `Graph` `String` `Shortest Path`
