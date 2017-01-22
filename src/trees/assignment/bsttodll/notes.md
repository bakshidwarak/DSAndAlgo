Convert a BST into a Circular Doubly Linked List
===========================================================================
Write a recursive function treeToList(Node root) that takes a BST and rearranges the internal pointers to make a circular doubly linked list out of the tree nodes. The "previous" pointers should be stored in the "Left" field and the "next" pointers should be stored in the "Right" field. The list should be arranged so that the nodes are in increasing order. Return the head pointer to the new list. The operation can be done in O(n) time.

Key Points
====================

1. Pointer manipulations- Draw it out with a base case
1. Two running pointers one for the head of the DLL and other for the previous element visited
1. Code similar to InOrder traversal
1. Implementing in Java- <b> The pointers(reference) cannot be reassigned inside the called method( Java is pass by value. The reference is passed as value) and hence we need to hack it around by either passing a wrapper object like an array</b>
1. <b> Ensure right node is copied over before changing it to point to the head </b>



Key Questions to Ask
====================
1. 

Approaches
====================

1. Similar to inorder, first traverse to the left most node and assign the head pointer
1. As and when we process a new node, treat it like the last node i.e the node-> right should point to head
	<pre>
	
	    if (prev == null) //Left most node
			head = root;
		else
			prev.right = root;
				
		head.left = root;

		Node right = root.right;

		root.right = head;
		prev = root;
		metaNodes[0]=head;
		metaNodes[1]=prev;
	</pre>

2. Time complexity O(n)
3. Space complexity O(logN) - Stack frames used in recursive calls
References
====================

 http://articles.leetcode.com/2010/11/convert-binary-search-tree-bst-to.html

