Merge two BSTs
===========================================================================
Merge two BSTs in O(N1 + N2) time, where N1 and N2 are number of nodes in the two trees respectively. The merged tree should contain all the elements of both trees and also be a balanced BST. Finally, print the new tree level by level. 
 
e.g.
Tree-1:  2->1,3
Tree-2:  7->6,8
Output:
 
6
2 7
1 3 8
 
The output above is a tree that's printed level by level.
 
(This is a very good question. It's not hard at all, but will need you to write several functions: one to parse, one to sort, one to merge, one to reconstruct and one to print. Each of these can be separate short interview questions)
 

Key Points
====================

1. <b>Inorder traversal of a BST always results in a sorted array</b>
1. In-order traverse the tree and get the sorted array
1. Middle element of the sorted array becomes the head of the merged BST
1. Recursively construct subtrees from subarrays ( as in merge sort)


Key Questions to Ask
====================
1. 

Approaches
====================

1. In-order traverse the tree and get the sorted array
1. Middle element of the sorted array becomes the head of the merged BST
1. Recursively construct subtrees from subarrays ( as in merge sort)
	

2. Time complexity O(n+m)
3. Space complexity O(n+m) 
References
====================

http://stackoverflow.com/questions/7540546/merging-2-binary-search-trees
