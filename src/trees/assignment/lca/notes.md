Lowest Common Ancestor in a Binary Tree 
===========================================================================
Given a binary tree (not a binary search tree) and two values say n1 and n2, write a program to find the least common ancestor.

Following is definition of LCA from Wikipedia:
Let T be a rooted tree. The lowest common ancestor between two nodes n1 and n2 is defined as the lowest node in T that has both n1 and n2 as descendants (where we allow a node to be a descendant of itself).

The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that is located farthest from the root. Computation of lowest common ancestors may be useful, for instance, as part of a procedure for determining the distance between pairs of nodes in a tree: the distance from n1 to n2 can be computed as the distance from the root to n1, plus the distance from the root to n2, minus twice the distance from the root to their lowest common ancestor. (Source Wiki)
![lpa.png](lpa.png)


Key Points
====================

1. Tree questions are mostly recursive
1. Example of DFS based solution
1. We could store path of n1 and n2 from root and compare the paths from the beginning and return the node that is at the index when the paths start to differ
  1. Path based solution is O(n+n) time complexity. Two traversals and O(n+n) space complexity( for storing paths)
  1. Tried a single traversal to look for both n1 and n2 and is not possible
1. Simple recursive way - O(n) single traversal



Key Questions to Ask
====================
1. Is it a Binary tree or BST( here it is given but if it is not its a good question to ask)

Approaches
====================

1. Two Traversals
  1. Store paths of n1 and n2 from the root (DFS)
  1. Compare the paths from the front till they differ and return the node at the index 
1. One traversal approach.
  1. if left has n1 or n2 and right has n1 or n2, the root is the LCA
  1. if LEFT is not null ( n1 or n2 was present in the left) and RIGHT is null(n1 or n2 is not present=> both n1 qnd n2 were on the left), the LCA is on the left side of the root. 
  1. if right is not null ( n1 or n2 was present in the left) and left is null (n1 or n2 is not present=> both n1 qnd n2 were on the right), the LCA is on the right of the root
  1. Single traversal , no extra memory required for path arrays. 
	

2. Time complexity O(n)

References
====================


 http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/