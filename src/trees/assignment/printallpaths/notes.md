Print all paths in a tree
===========================================================================
Given a binary tree, print out all of its root-to-leaf paths one per line.
 
e.g. for a tree that's 
     1
  2     3
4 5  6 7
 
Print:
1 2 4
1 2 5
1 3 6
1 3 7

 

Key Points
====================

1. Have a running Array/ Array List that stores the levels and nodes at that level
1. Add to the list the root val and increment the level and pass to the subsequent recurisve calls


Key Questions to Ask
====================
1. 

Approaches
====================

1. Have a running Array/ Array List that stores the levels and nodes at that level
1. Add to the list the root val and increment the level and pass to the subsequent recurisve calls

	

2. Time complexity O(n)
3. Space complexity O(n) 
References
====================

http://www.geeksforgeeks.org/given-a-binary-tree-print-out-all-of-its-root-to-leaf-paths-one-per-line/
