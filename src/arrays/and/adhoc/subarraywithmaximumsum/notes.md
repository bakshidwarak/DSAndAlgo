Subarray of maximum Sum
===========================================================================
Given an array of integers, identify the subarray with maximum sum

Example: 10 -6 -1 1 21 -25 15

{1,21} has a sum of 22 which is the max


Key Points
====================

1. <b> Subsets- Set of unique elements</b>
  1. Total number of subsets=<b> 2<sup>N</sup></b>
1. <b> Subsequence</b> Some of the elements <b> not contagious</b> but <b>maintains order</b>
  1. Total number of Subsequence=<b> 2<sup>N</sup></b>
1. <b> Subarray</b> A part of the array <b>contiguous</b>
  1. Total number of subarray=<b> N<sup>2</sup></b>



Key Questions to Ask
====================
1. Possibility of overflows? Range of integers?

Approaches
====================

1. Brute Force
	a. Generate all subarrays of the given array. 
	b. Get the maximum sum and return it
	c. O(N<sup>2</sup>)
	

2. <b>Optimal</b><b>Kadane's algorithm </b>
  2. Go from left to right and <b>Keep track of cumulative sum</b>
  2. When the cumulative sum becomes <0, reset the cumulative sum to 0
  2. Idea is if after addition the the sum becomes lesser than zero the array traversed so far cannot have the max sum


References
====================

