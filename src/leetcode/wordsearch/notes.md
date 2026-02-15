# LeetCode 79: Word Search

## Problem Statement
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where "adjacent" means horizontally or vertically neighboring (not diagonally).

**Constraints:**
- Same letter cell may not be used more than once in one word
- Board dimensions: m x n (both positive)
- Word is non-empty
- Case-sensitive matching

## Examples

### Example 1
```
Board:
[['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']]

word = "ABCCED"  → true  (Path: A→B→C→C→E→D)
word = "SEE"     → true  (Path: S→E→E)
word = "ABCB"    → false (Cannot reuse cells)
```

## Key Insights

1. **Backtracking/DFS:** Explore all possible paths from each starting cell
2. **Cell Revisiting Prevention:** Mark visited cells during path exploration
3. **Restore State:** Undo marking after recursive call (backtrack)
4. **Early Termination:** Stop when word found or path invalid
5. **Character Mismatch:** Quick rejection if current cell doesn't match

## Algorithm Steps

### DFS Backtracking Approach

```
1. For each cell (i, j) in board:
   - If cell character matches word[0]:
     - Try finding rest of word from this cell
     - If found, return true
2. Return false if no starting cell leads to word

findWord(board, row, col, word, wordIndex):
3. Base case: If wordIndex == word.length:
   - Return true (matched entire word)
4. Boundary check: If out of bounds:
   - Return false
5. Character match check: If board[row][col] != word[wordIndex]:
   - Return false
6. Mark current cell as visited: board[row][col] = '#' or '1'
7. Try all 4 directions (up, down, left, right):
   - If any direction returns true:
     - Return true
8. Restore cell value (backtrack): board[row][col] = original character
9. Return false if no direction finds word
```

## Complexity Analysis

- **Time Complexity:** O(m × n × 4^L) worst case
  - m × n cells to start search
  - 4^L possible paths (4 directions per step, L = word length)
  - Pruning due to dictionary/boundaries
- **Space Complexity:** O(L) - Recursion stack depth = word length

## ASCII Visualization

```
Board:
  0   1   2   3
0 A   B   C   E
1 S   F   C   S
2 A   D   E   E

Word: "ABCCED"

Search from (0,0) = 'A':
Path exploration (DFS tree):
         (0,0)A
          |
       (0,1)B
          |
       (0,2)C ← Found first 'C'
        /    \
  (1,2)C    (0,3)E
      |      |
  (1,1)F  (dead end)

Backtrack, try (1,2)C from (0,2)C:
Path: A→B→C(0,2)→C(1,2)→E(2,2)→D(2,1)
      0→1→2→3→4→5 matches "ABCCED" ✓

---

Detailed Traversal:

Start: findWord(board, 0, 0, "ABCCED", 0)
  wordIndex=0, char='A'
  board[0][0]='A' == 'A'? YES
  Mark: board[0][0]='1'

  Try UP (row=-1): Out of bounds, return false
  Try DOWN (row=1): findWord(board, 1, 0, "ABCCED", 1)
    wordIndex=1, char='B'
    board[1][0]='S' != 'B'? return false
  Try LEFT (col=-1): Out of bounds, return false
  Try RIGHT (col=1): findWord(board, 0, 1, "ABCCED", 1)
    wordIndex=1, char='B'
    board[0][1]='B' == 'B'? YES
    Mark: board[0][1]='1'

    Try UP: Out of bounds
    Try DOWN: findWord(board, 1, 1, "ABCCED", 2)
      wordIndex=2, char='C'
      board[1][1]='F' != 'C'? return false
    Try LEFT: Already visited (='1')
    Try RIGHT: findWord(board, 0, 2, "ABCCED", 2)
      wordIndex=2, char='C'
      board[0][2]='C' == 'C'? YES
      Mark: board[0][2]='1'

      Try UP: Out of bounds
      Try DOWN: findWord(board, 1, 2, "ABCCED", 3)
        wordIndex=3, char='C'
        board[1][2]='C' == 'C'? YES
        Mark: board[1][2]='1'

        Try UP: board[0][2]='1' (visited)
        Try DOWN: findWord(board, 2, 2, "ABCCED", 4)
          wordIndex=4, char='E'
          board[2][2]='E' == 'E'? YES
          Mark: board[2][2]='1'

          Try UP: Already visited
          Try DOWN: Out of bounds
          Try LEFT: findWord(board, 2, 1, "ABCCED", 5)
            wordIndex=5, char='D'
            board[2][1]='D' == 'D'? YES
            Mark: board[2][1]='1'

            Try UP: Already visited (or 'F')
            Try DOWN: Out of bounds
            Try LEFT: findWord(board, 2, 0, "ABCCED", 6)
              wordIndex=6 == length? return TRUE ✓

          (Rest of exploration continues, but first path found)

        Restore: board[2][2]='E'

      (Continue other directions...)
      Restore: board[1][2]='C'

    Restore: board[0][2]='C'

  Restore: board[0][1]='B'

Result: Word found! Return TRUE
```

## Code Walkthrough

```java
public boolean exist(char[][] board, String word) {
    // Empty board or word check
    if (board.length == 0)
        return false;

    // Try starting from each cell
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (findWord(board, i, j, word, 0)) {
                return true;
            }
        }
    }

    return false;
}

public boolean findWord(char[][] board, int row, int col, String word, int wordIndex) {
    // Base case: matched entire word
    if (wordIndex >= word.length()) {
        return true;
    }

    // Boundary check
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0) {
        return false;
    }

    // Character mismatch
    if (board[row][col] != word.charAt(wordIndex)) {
        return false;
    }

    // Mark as visited to prevent revisiting
    char ch = board[row][col];
    board[row][col] = '1';

    // Try all 4 directions
    boolean isPresent = findWord(board, row + 1, col, word, wordIndex + 1)  // DOWN
                     || findWord(board, row, col + 1, word, wordIndex + 1)  // RIGHT
                     || findWord(board, row - 1, col, word, wordIndex + 1)  // UP
                     || findWord(board, row, col - 1, word, wordIndex + 1); // LEFT

    // Backtrack: restore cell value
    board[row][col] = ch;

    return isPresent;
}
```

**Execution Flow:**
1. Check if board is empty
2. Iterate through all cells as potential starting points
3. For each cell, call DFS to find word
4. DFS checks boundaries and character match
5. Mark cell as visited (using sentinel value)
6. Recursively explore 4 adjacent cells
7. Backtrack by restoring original cell value
8. Return true if any path finds entire word

## Edge Cases

1. **Single Cell Board:** 1x1 grid
   - `board = [['A']]`, `word = "A"` → true
   - `board = [['A']]`, `word = "B"` → false

2. **Single Row/Column:** Linear path
   - `board = [['A','B','C']]`, `word = "ABC"` → true
   - `board = [['A','B','C']]`, `word = "CBA"` → false

3. **Word = Single Character:** Match any cell
   - `board = [['A']]`, `word = "A"` → true

4. **Reuse Prevention:** Cannot use same cell twice
   - `board = [['A']]`, `word = "AA"` → false

5. **Backtracking Required:** Path exploration with dead ends
   - `board = [['A','B'],['C','D']]`, `word = "ABCD"` → true if adjacent
   - `board = [['A','B'],['C','D']]`, `word = "ACBD"` → depends on adjacency

6. **Case Sensitivity:** Exact character matching
   - `board = [['a']]`, `word = "A"` → false

### Example Edge Cases:
```
Input: board = [["A"]], word = "A", Output: true
Input: board = [["A"]], word = "B", Output: false
Input: board = [["A","B","C"],["D","E","F"]], word = "ABCDEF", Output: false
Input: board = [["A","B"],["C","D"]], word = "ABCD", Output: false
Input: board = [["A","B"],["C","D"]], word = "ACDB", Output: true
```

## Optimization Tips

1. **Check Word in Dictionary First:** Skip if no letters match
2. **Pruning:** Count character frequencies to validate possibility
3. **Start with Unique Characters:** Begin search from less common letters
4. **Early Termination:** Use return immediately when found

## Related Problems

1. **LeetCode 212 - Word Search II:** Search multiple words
2. **LeetCode 211 - Add and Search Word:** Trie-based search
3. **LeetCode 130 - Surrounded Regions:** Similar board DFS
4. **LeetCode 200 - Number of Islands:** Connected components
5. **LeetCode 542 - 01 Matrix:** BFS on board
## Tags

`#Backtracking` `#DFS` `#Array` `#Matrix` `#Medium`

## Key Takeaways

- Backtracking explores all possible paths systematically
- Mark visited cells to prevent revisiting in same path
- Restore cell value during backtrack for other paths
- 4-directional movement (up, down, left, right)
- Early termination when word fully matched
- Boundary and character checks prevent invalid exploration
- Time complexity manageable with pruning
