Array Product excluding the ith element
===========================================================================
Given an array of numbers, nums, return an array of numbers products, where products[i] is the product of all nums[j], j != i.
 
 Input : [1, 2, 3, 4, 5] Output: [(2`*`3`*`4`*`5), (1`*`3`*`4`*`5), (1`*`2`*`4`*`5), (1`*`2`*`3`*`5),
 (1*2*3*4)] = [120, 60, 40, 30, 24] You must do this in O(N) without using
 division.

Key Points
====================

1. Dont overthink the approach.
2. Initially thought about using bitwise operator but held back as it is complex to multiply and divide using bit logic
3. Division not being allowed is the key. else the solve is easy to multiply all elements and divide the product by each item
4. Logarithms could be an option however might not work for negative values


Key Questions to Ask
====================
1. Can I use additional data structures

Approaches
====================

a divides a*b*c i.e a*b*c is a multiple of a, which means if we need
		  to compute (a`*`b`*`c)/a , it is equivalent to not including a at all in
		  the product
(a`*`b`*`c)/a = b`*`c [ Not multiply by a at all in the first place
		  
		  
Hence first <b>compute the cumulative products starting from 1 sans the
		  last item and again traverse back and compute the cumulative product
		  sans the first item</b>
		 

Sample Output
=====================
Case 1 Happy path
1 2 3 4 5
120 60 40 30 24
References
====================
http://stackoverflow.com/questions/2680548/given-an-array-of-numbers-return-array-of-products-of-all-other-numbers-no-div

