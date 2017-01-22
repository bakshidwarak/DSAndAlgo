Determine if a Binary Tree is a Binary Search Tree (BST)
===========================================================================
Write a function isBST(BinaryTree *node) to verify if a given binary tree is a Binary Search Tree (BST) or not.

<div style="page-break-after: always;"></div>
Key Points
====================

1. Tree questions are mostly recursive
1. If my left sub tree and right sub tree are BSTs and I am gretaer than my left element and lesser than my right I am BST
1. Remeber the base case and the case when left or righ node could be null



Key Questions to Ask
====================
1. Possibility of overflows? Range of integers?

Approaches
====================

1. Recursively Check if the root value is > left subtree and < right subtree
	

2. Time complexity O(n)
3. Space complexity O(logN) - Stack frames used in recursive calls
References
====================

http://articles.leetcode.com/determine-if-binary-tree-is-binary
