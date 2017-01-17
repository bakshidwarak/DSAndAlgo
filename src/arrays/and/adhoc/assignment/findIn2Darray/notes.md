Array Product excluding the ith element
===========================================================================
Find a number in a sorted 2D array.

You're given a 2d array (N x M) where all the numbers (integers) in the array are in increasing order from left to right and top to bottom. You're also given a target number, to be searched inside the array. What is the best way to search and determine if a target number is in the array?

Solution: take inspiration from http://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/

(Notice that this can be a very deceptive problem in a good way. Solution seems difficult, but it's actually quite simple.)
<div style="page-break-after: always;"></div>
Key Points
====================

1. Very deceptive question. Normal tendency was to think that the numbders are first sorted from left to right and then top to bottom. In which case we tend to incline towards an enhanced binary search. 
<b> Need to clarify the question correctly by giving different examples </b>
2. <b>Start from top right and traverse down or left</b>


Key Questions to Ask
====================
1. Clarify the question. Ask if numbers can repeat

Approaches
====================

1. First approach was to find the max of row and column and do a binary search in the col/row arrays as we traverse. The complexity for this approach is O(nlogn)(i.e if equal col and rows)
2. <b>Optimal Approach</b> 
	..1 Start from top right
	....1 if the number is < the number to be searched
	     ......1 Go left columnwise
	....1 else go down next row
	repeat till you find the element
	<b>Complexity O(m+n)</b>
		 
``` java
		
		for (int row = 0, col = cols - 1; row < rows && col >= 0;) {
			// Start from top right
			
			int currentElement = array[row][col];
			if (currentElement == element) {
				System.out.println(String.format("Element %d found at position %d,%d", element, row, col));
				return;
		   }
			if (currentElement > element) {
				col--;// Go left in the column

			} else {
				row++;// element is greater so go down to next row
			}

		}
        System.out.println("Element Not found");
```

<div style="page-break-after: always;"></div>
Sample Output
=====================
Case 1 Happy path
Enter the number of rows
4
Enter the number of columns
4
Enter the elements in array
10 20 30 40
15 25 35 45
27 29 37 48
32 33 39 50
Enter the value to search
29
Element 29 found at position 2,1

References
====================
http://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/

