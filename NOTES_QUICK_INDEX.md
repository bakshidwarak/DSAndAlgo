# Quick Index - LeetCode Problems 141-160 Notes

## Direct File Links

| # | Problem | LeetCode | File Path | Key Algorithm |
|---|---------|----------|-----------|---|
| 1 | Sort Colors | 75 | `sortcolors/notes.md` | Three-Pointer/Dutch Flag |
| 2 | Convert Sorted Array to BST | 108 | `sortedarraytobst/notes.md` | Divide & Conquer |
| 3 | Sort Transformed Array | 360 | `sorttransformedarray/notes.md` | Two Pointers + Parabola |
| 4 | Strobogrammatic Number | 246 | `sstrobogrammaticnumber/notes.md` | Two Pointers |
| 5 | Strobogrammatic Number II | 247 | `sstrobogrammaticnumberII/notes.md` | Recursion |
| 6 | Subsets | 78 | `subsets/notes.md` | Backtracking |
| 7 | Subsets II | 90 | `subsetsII/notes.md` | Backtracking + Duplicate Skip |
| 8 | Sum of Left Leaves | 404 | `sumofleafnodes/notes.md` | DFS + Tree |
| 9 | Add Two Numbers | 2 | `sumofnumbers/notes.md` | Linked List + Carry |
| 10 | Sum of Square Numbers | 633 | `sumofsquarenumbers/notes.md` | Two Pointers |
| 11 | Sum Root to Leaf Numbers | 129 | `sumroottoleafnumbers/notes.md` | DFS + Path Tracking |
| 12 | Swap Nodes in Pairs | 24 | `swapnodesinpairs/notes.md` | Recursion + Linked List |
| 13 | Symmetric Tree | 101 | `symmetrictree/notes.md` | DFS + Mirror Check |
| 14 | 3Sum | 15 | `threesum/notes.md` | Two Pointers + Sorting |
| 15 | Top K Frequent Words | 692 | `topkfrequentwords/notes.md` | Min Heap + HashMap |
| 16 | Total Hamming Distance | 477 | `totalhammingdistance/notes.md` | Bit Manipulation |
| 17 | Transpose of a Matrix | 867 | `transposeofamatrix/notes.md` | Array Manipulation |
| 18 | Tree Diameter | 543 | `treediameter/notes.md` | DFS + Tree |
| 19 | Trim a Binary Search Tree | 669 | `trimbst/notes.md` | BST + Recursion |
| 20 | Two Sum III - Data Structure | 170 | `twosum/notes.md` | HashMap + Design |

## By Algorithm Type

### Two Pointers (5)
- Sort Colors
- Strobogrammatic Number
- 3Sum
- Sum of Square Numbers
- Sort Transformed Array

### Tree/DFS (5)
- Sum of Left Leaves
- Sum Root to Leaf Numbers
- Symmetric Tree
- Tree Diameter
- Trim a Binary Search Tree

### Recursion/Backtracking (6)
- Subsets
- Subsets II
- Strobogrammatic Number II
- Swap Nodes in Pairs
- Convert Sorted Array to BST
- Trim a Binary Search Tree

### Hash Table/Heap (2)
- Top K Frequent Words
- Two Sum III

### Linked List (2)
- Add Two Numbers
- Swap Nodes in Pairs

### Other (4)
- Sort Transformed Array (Parabola)
- Transpose of a Matrix (Array)
- Total Hamming Distance (Bit Manipulation)
- 3Sum (Sorting)

## Complexity Quick Reference

| Problem | Time | Space | Notes |
|---------|------|-------|-------|
| Sort Colors | O(n) | O(1) | One-pass |
| Convert Sorted Array to BST | O(n) | O(log n) | Recursion depth |
| Sort Transformed Array | O(n) | O(1) | Two pointers |
| Strobogrammatic Number | O(n) | O(1) | Two pointers |
| Strobogrammatic Number II | O(5^(n/2) * n) | O(n) | Generation |
| Subsets | O(2^n * n) | O(n) | Copy time |
| Subsets II | O(2^n * n) | O(n) | Copy time |
| Sum of Left Leaves | O(n) | O(h) | DFS depth |
| Add Two Numbers | O(max(m,n)) | O(max(m,n)) | Result length |
| Sum of Square Numbers | O(sqrt(c)) | O(sqrt(c)) | Recursion |
| Sum Root to Leaf Numbers | O(n) | O(h) | DFS depth |
| Swap Nodes in Pairs | O(n) | O(n) | Recursion |
| Symmetric Tree | O(n) | O(h) | DFS depth |
| 3Sum | O(n²) | O(1) | Sorting only |
| Top K Frequent Words | O(n log k) | O(n) | Heap |
| Total Hamming Distance | O(32n) = O(n) | O(32) | Bit counting |
| Transpose of a Matrix | O(m*n) | O(m*n) | Result size |
| Tree Diameter | O(n²) | O(h) | Suboptimal |
| Trim a Binary Search Tree | O(n) | O(h) | DFS depth |
| Two Sum III | add: O(1), find: O(n) | O(n) | Trade-off |

## Difficulty by Complexity

### Easy
- Transpose of a Matrix
- Strobogrammatic Number
- Sum of Left Leaves
- Symmetric Tree

### Medium
- Sort Colors
- Convert Sorted Array to BST
- Sort Transformed Array
- Subsets
- Subsets II
- Sum of Square Numbers
- Sum Root to Leaf Numbers
- Swap Nodes in Pairs
- 3Sum
- Top K Frequent Words
- Total Hamming Distance
- Tree Diameter
- Trim a Binary Search Tree

### Hard
- Strobogrammatic Number II
- Two Sum III
- Add Two Numbers

## Data Structures Used

### Arrays
- Sort Colors
- Sort Transformed Array
- Transpose of a Matrix
- Total Hamming Distance

### Trees
- Convert Sorted Array to BST
- Sum of Left Leaves
- Symmetric Tree
- Tree Diameter
- Trim a Binary Search Tree
- Sum Root to Leaf Numbers

### Linked Lists
- Add Two Numbers
- Swap Nodes in Pairs

### Hash Table
- 3Sum (HashSet for duplicates)
- Top K Frequent Words (HashMap)
- Two Sum III (HashMap)

### Heap
- Top K Frequent Words (Min Heap)

### Other
- Subsets (Recursion tree)
- Subsets II (Recursion tree)
- Strobogrammatic Number II (Recursion tree)

---

**Total Documentation**: 2,500+ lines
**Last Updated**: 2026-02-15
**Version**: 1.0
