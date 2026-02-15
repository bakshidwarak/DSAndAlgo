# LeetCode 127: Word Ladder

## Problem Statement
Given two words, `beginWord` and `endWord`, and a dictionary's word list, find the **length** of the shortest transformation sequence from `beginWord` to `endWord`, such that:

1. Only one letter can be changed at a time
2. Each transformed word must exist in the word list
3. Return the length of the shortest path (number of words in sequence)

**Constraints:**
- All words have the same length
- All words contain only lowercase alphabetic characters
- No duplicates in word list
- `beginWord` and `endWord` are non-empty and different
- Return 0 if no such transformation sequence exists

## Examples

### Example 1
- **Input:** `beginWord = "hit"`, `endWord = "cog"`, `wordList = ["hot","dot","dog","lot","log","cog"]`
- **Output:** `5`
- **Explanation:** Path: "hit" → "hot" → "dot" → "dog" → "cog" (length = 5)

### Example 2
- **Input:** `beginWord = "hit"`, `endWord = "cog"`, `wordList = ["hot","dot","dog","lot","log"]`
- **Output:** `0`
- **Explanation:** Cannot reach "cog" from "hit"

## Key Insights

1. **Shortest Path Problem:** BFS finds shortest transformation
2. **Graph Construction:** Words are nodes, one-letter changes are edges
3. **Level-Based Search:** Track transformation steps as levels
4. **Visited Set:** Avoid revisiting words (remove from set after visiting)
5. **Character Substitution:** Generate neighbors by trying all 26 letters

## Algorithm Steps

### BFS Approach (Used in Code)

```
1. Create HashSet from word list for O(1) lookup
2. Create Queue and initialize with (beginWord, level=1)
3. While queue is not empty:
   a. Dequeue word and level
   b. If word equals endWord, return level
   c. Generate all neighbors by changing one character at a time:
      - For each position i in word:
        - For each letter 'a' to 'z':
          - Create new word with letter at position i
          - If new word in dictionary:
            - Remove from dictionary (mark as visited)
            - Enqueue (new word, level + 1)
   d. Restore character for next iteration
4. Return 0 if no path found (queue exhausted)
```

## Complexity Analysis

- **Time Complexity:** O(N × L × 26) = O(N × L)
  - N = number of words
  - L = length of each word
  - 26 letters to try for each position
- **Space Complexity:** O(N) - HashSet + Queue storage

## ASCII Visualization

```
Dictionary: {"hot", "dot", "dog", "lot", "log", "cog"}
BeginWord: "hit", EndWord: "cog"

BFS Tree:
                hit (level=1)
               /
             hot (level=2) ← only 1-letter change: 'i'→'o'
            / |
         dot  lot (level=3)
         / |     |
       dog log   (level=4)
         \  |
          cog  (level=5) ← FOUND!

Path: hit → hot → dot → dog → cog
Length: 5 ✓

---

Step-by-step BFS:

Initial: Queue = [(hit, 1)], Dictionary = {hot, dot, dog, lot, log, cog}

Step 1: Dequeue (hit, 1)
  Generate neighbors by changing each position:
  Position 0: ait, bit, cit, ..., zit
    ait in dict? NO
    bit in dict? NO
    ...
    (none found at position 0)

  Position 1: hat, hbt, hct, ..., hot (found!), hut
    hot in dict? YES → Add (hot, 2), Remove hot from dict

  Position 2: hia, hib, hic, ..., hin
    (none found)

  Queue = [(hot, 2)]

Step 2: Dequeue (hot, 2)
  Generate neighbors:
  Position 0: aot, bot, cot, ..., dot (found!), got, ..., lot (found!), ...
    dot in dict? YES → Add (dot, 3), Remove dot
    lot in dict? YES → Add (lot, 3), Remove lot

  Position 1: hat, hdt, het, ...
    (none found)

  Position 2: hoa, hob, hoc, hod, hoe, ...
    (none found)

  Queue = [(dot, 3), (lot, 3)]
  Dictionary = {dog, log, cog}

Step 3: Dequeue (dot, 3)
  Generate neighbors:
  Position 0: aot, bot, cot, ...
    (already removed hot, all others not in dict)

  Position 1: dat, dbt, dct, ddt, det, dft, ... dht, ...
    (none found)

  Position 2: doa, dob, doc, dod, doe, dof, dog (found!), ...
    dog in dict? YES → Add (dog, 4), Remove dog

  Queue = [(lot, 3), (dog, 4)]
  Dictionary = {log, cog}

Step 4: Dequeue (lot, 3)
  Generate neighbors:
  Position 0: aot, bot, cot, ..., dot (already visited)
    (none found)

  Position 1: lat, lbt, lct, ldt, let, lft, ..., llt, ...
    (none found)

  Position 2: loa, lob, loc, lod, loe, lof, log (found!), ...
    log in dict? YES → Add (log, 4), Remove log

  Queue = [(dog, 4), (log, 4)]
  Dictionary = {cog}

Step 5: Dequeue (dog, 4)
  Generate neighbors:
  Position 0: aog, bog, cog (found!), dog (already visited)
    cog in dict? YES → Add (cog, 5), Remove cog

  Position 1: dag, dbg, dcg, ...
    (none found)

  Position 2: doa, dob, doc, ...
    (none found)

  Queue = [(log, 4), (cog, 5)]
  Dictionary = {}

Step 6: Dequeue (log, 4)
  Generate neighbors:
  Position 0: aog, bog, cog (already visited)
  Position 1: lag, lbg, lcg, ...
  Position 2: loa, lob, loc, ...
  (none found in dictionary)

  Queue = [(cog, 5)]

Step 7: Dequeue (cog, 5)
  cog == endWord? YES
  Return 5 ✓
```

## Code Walkthrough

```java
static class Pair {
    String word;
    int level;

    public Pair(String s, int l) {
        this.word = s;
        this.level = l;
    }
}

public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> words = new HashSet<>();
    for (String s : wordList)
        words.add(s);

    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(beginWord, 1));

    while (!queue.isEmpty()) {
        Pair p = queue.remove();
        String curr = p.word;
        int level = p.level;

        // Found the end word
        if (curr.equals(endWord))
            return level;

        // Generate all neighbors
        char[] current = curr.toCharArray();
        for (int i = 0; i < current.length; i++) {
            char tmp = current[i];

            // Try all 26 letters at position i
            for (char c = 'a'; c <= 'z'; c++) {
                current[i] = c;
                String next = new String(current);

                if (words.contains(next)) {
                    queue.add(new Pair(next, level + 1));
                    words.remove(next);  // Mark as visited
                }
            }

            current[i] = tmp;  // Restore for next iteration
        }
    }

    return 0;  // No path found
}
```

**Execution Flow:**
1. Convert word list to HashSet
2. Initialize queue with begin word at level 1
3. BFS loop:
   - Dequeue current word and level
   - Check if reached end word
   - Generate all 1-letter variations
   - Add valid variations to queue
   - Remove visited words from dictionary
4. Return level when end word found, 0 if queue emptied

## Edge Cases

1. **No Path Exists:** Return 0
   - `beginWord = "hit"`, `endWord = "cog"`, `wordList = ["hot","dot"]` → 0

2. **Direct Connection:** End word differs by 1 letter
   - `beginWord = "hot"`, `endWord = "dot"`, `wordList = ["dot"]` → 2

3. **Long Path:** Multiple transformations needed
   - `"hit" → "hot" → "dot" → "dog" → "cog"` → 5

4. **End Word Not in List:** Should fail
   - `endWord = "xyz"` not in `wordList` → 0

5. **Single Word:** Only begin word
   - `beginWord = "hit"`, `endWord = "hit"` → 0 (different by constraint)

### Example Edge Cases:
```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0

Input: beginWord = "hit", endWord = "hot", wordList = ["hot"]
Output: 2
```

## Optimizations

1. **Bidirectional BFS:** Search from both ends simultaneously
   - Time: O(N × L × 26^(d/2)) where d is ladder length
2. **Word Pattern Generation:** Generate patterns instead of trying all letters
   - For "hot": h*t, *ot, ho*
3. **Early Termination:** Stop when paths meet in bidirectional search

## Related Problems

1. **LeetCode 126 - Word Ladder II:** Return all shortest paths (not just length)
2. **LeetCode 433 - Minimum Genetic Mutation:** Similar BFS problem
3. **LeetCode 752 - Open the Lock:** BFS with step counter
4. **LeetCode 1956 - Minimum Time For K Virus Variants:** BFS variation
5. **LeetCode 773 - Sliding Puzzle:** BFS on puzzle states
## Tags

`#Breadth-First-Search` `#Graph` `#String` `#Shortest-Path` `#Medium`

## Key Takeaways

- BFS finds shortest path in unweighted graph
- Word transformation is graph traversal problem
- Generate neighbors by changing each character position
- Mark visited nodes (remove from set) to avoid revisiting
- Time complexity O(N × L × 26) is manageable
- Level tracking in queue node simplifies distance calculation
