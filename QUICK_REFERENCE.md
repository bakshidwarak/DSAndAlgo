# LeetCode Problems 161-173: Quick Reference Guide

## Problems Overview

| # | Problem | LeetCode # | Difficulty | Key Technique | Location |
|---|---------|-----------|-----------|---------------|----------|
| 1 | Two Sum II | 167 | Easy | Two-Pointers | twosum2/notes.md |
| 2 | Two Sum IV (BST) | 653 | Easy | BST + Two-Pointers | twosumbinarysearchtree/notes.md |
| 3 | Unique Paths II | 63 | Medium | Dynamic Programming | uniquepathsII/notes.md |
| 4 | Valid Anagram | 242 | Easy | Character Counting | validanagrams/notes.md |
| 5 | Valid Number | 65 | Medium | State Machine | validnumber/notes.md |
| 6 | Valid Palindrome | 125 | Easy | Two-Pointers | validpalindrome/notes.md |
| 7 | Valid Palindrome II | 680 | Easy | Greedy + Two-Pointers | validpalindrome2/notes.md |
| 8 | Valid Triangle Number | 611 | Medium | Sorting + Two-Pointers | validtrianglenumber/notes.md |
| 9 | Word Break | 139 | Medium | Dynamic Programming | wordbreak/notes.md |
| 10 | Word Break II | 140 | Hard | DP + Backtracking | wordbreak/notes_wordbreakII.md |
| 11 | Word Dictionary | 211 | Medium | Trie + DFS | worddictionary/notes.md |
| 12 | Word Ladder | 127 | Medium | BFS | wordladder/notes.md |
| 13 | Word Search | 79 | Medium | Backtracking | wordsearch/notes.md |
| 14 | ZigZag Conversion | 6 | Easy | String Simulation | zigzagconversion/notes.md |

## Algorithm Technique Index

### Two-Pointers (4 problems)
- Two Sum II (167)
- Two Sum IV (653)
- Valid Palindrome (125)
- Valid Palindrome II (680)
- Valid Triangle Number (611)

### Dynamic Programming (3 problems)
- Unique Paths II (63)
- Word Break (139)
- Word Break II (140)

### Graph/Tree Algorithms (4 problems)
- Two Sum IV (653) - BST
- Word Dictionary (211) - Trie
- Word Ladder (127) - BFS
- Word Search (79) - Backtracking

### String Problems (9 problems)
- Valid Anagram (242)
- Valid Number (65)
- Valid Palindrome (125)
- Valid Palindrome II (680)
- Word Break (139)
- Word Break II (140)
- Word Dictionary (211)
- Word Ladder (127)
- Word Search (79)
- ZigZag Conversion (6)

## Time Complexity Summary

| Problem | Approach | Time | Space |
|---------|----------|------|-------|
| Two Sum II | Two-Pointers | O(n) | O(1) |
| Two Sum IV | Inorder + Two-Pointers | O(n) | O(n) |
| Unique Paths II | DP | O(m×n) | O(m×n) |
| Valid Anagram | Char Count | O(n) | O(1) |
| Valid Number | State Machine | O(n) | O(1) |
| Valid Palindrome | Two-Pointers | O(n) | O(1) |
| Valid Palindrome II | Two-Pointers | O(n) | O(1) |
| Valid Triangle | Sort + Two-Pointers | O(n²) | O(1) |
| Word Break | DP | O(n²) | O(n) |
| Word Break II | DP + DFS | O(n × 2^n) | O(n) |
| Word Dictionary | Trie + DFS | O(m) add, O(26^n) search | O(m×n) |
| Word Ladder | BFS | O(n×l×26) | O(n) |
| Word Search | Backtracking | O(m×n×4^l) | O(l) |
| ZigZag | String Manipulation | O(n) | O(n) |

## Pattern Recognition

### Easy Problems (4)
These are fundamentals for interview prep:
1. Two Sum II - Master two-pointer technique
2. Two Sum IV - Apply technique to BST
3. Valid Anagram - Character frequency counting
4. Valid Palindrome - String validation
5. ZigZag - String pattern recognition

### Medium Problems (9)
Core problem-solving skills:
1. Unique Paths II - 2D DP with obstacles
2. Valid Number - Careful state validation
3. Valid Palindrome II - Greedy with recovery
4. Valid Triangle - Mathematical insight
5. Word Break - DP feasibility
6. Word Dictionary - Trie data structure
7. Word Ladder - BFS shortest path
8. Word Search - Backtracking exploration

### Hard Problems (1)
Advanced techniques:
1. Word Break II - DP + Backtracking combo

## Key Insights by Problem

1. **Two Sum II**: Sorted array unlocks O(n) solution
2. **Two Sum IV**: BST's inorder = sorted array
3. **Unique Paths II**: Obstacles force dp[i][j] = 0
4. **Valid Anagram**: Frequency matching is key
5. **Valid Number**: State tracking for format validation
6. **Valid Palindrome**: Filter non-alphanumeric first
7. **Valid Palindrome II**: Try both skip options
8. **Valid Triangle**: Sort then count combinations
9. **Word Break**: Bottom-up DP is efficient
10. **Word Break II**: Memoize feasibility, then DFS
11. **Word Dictionary**: Trie with wildcard DFS
12. **Word Ladder**: BFS finds shortest path
13. **Word Search**: DFS with backtracking
14. **ZigZag**: Cycle pattern = 2×(rows-1)

## Common Pitfalls

- **Two-Pointer**: Forgetting to handle unsorted input
- **DP**: Off-by-one errors in indexing
- **State Machine**: Missing edge cases for boundaries
- **Trie**: Not handling wildcards properly
- **BFS**: Forgetting to mark visited nodes
- **Backtracking**: Not restoring state during backtrack

## Study Recommendations

### Day 1-2: Fundamentals
- Two Sum II
- Valid Anagram
- Valid Palindrome

### Day 3-4: String Problems
- Valid Number
- Valid Palindrome II
- ZigZag Conversion

### Day 5-6: Trees & Arrays
- Two Sum IV
- Unique Paths II
- Valid Triangle

### Day 7-8: Advanced
- Word Break
- Word Dictionary
- Word Search

### Day 9: Graph Algorithms
- Word Ladder

### Day 10: Integration
- Word Break II

## File Locations

All notes are located in:
```
/tmp/DSAndAlgo/src/leetcode/[problem-name]/notes.md
```

Each notes file contains:
- Problem Statement
- Examples
- Key Insights
- Algorithm Steps
- ASCII Visualizations
- Code Walkthrough
- Edge Cases
- Related Problems
- Tags
- Key Takeaways

## How to Study

1. Read problem statement and examples
2. Study key insights
3. Review algorithm steps
4. Trace through ASCII visualizations
5. Study code walkthrough
6. Test with edge cases
7. Compare with related problems

---

**Total Coverage**: 14 comprehensive problems with ~105KB of documentation
**Estimated Study Time**: 7-10 hours for complete mastery
**Difficulty Progression**: Easy → Medium → Hard
