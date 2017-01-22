PostOrder Traversal without recursion
===========================================================================
Write a function to traverse a Binary tree PostOrder, without using recursion. As you traverse, please print contents of the nodes.
(Bonus: Spend 1 minute more and also do it with recursion)

 

Key Points
====================

1. Can be done using two stacks or one stack
1. More checks for one stack


Key Questions to Ask
====================
1. 

Approaches
====================

1. Two stack approach
  1. Have two stacks one for the traversal and one for the output
  1. While the stack is not empty, pop the current element and push it to the output stack and add its left and right to the traversal stack
1. One  stack approach
  1. While the stack is not empty pop the current node and push it after pushing the children
  1. Both approach is similar in memory and time, two stack approach more elegant

	

2. Time complexity O(n)
3. Space complexity O(n) 
References
====================

https://www.youtube.com/watch?v=hv-mJUs5mvU
