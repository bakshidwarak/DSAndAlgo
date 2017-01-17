Sum2 Zero
===========================================================================
SumZero

Given a set of integers, find a contiguous subset whose sum is zero. There can be duplicate numbers in the input.

Input: Integer array e.g. 5,1,2,-3,7,-4

output: A subset that sums to zero. 

e.g. 1,2,-3 OR -3,7,-4

* If there are no such subsets, then print nothing

* If there are multiple such subsets, then print any one

* If a matching subset is a subset of a larger matching subset, then print either one

* If there is a number '0' in the array, then it counts as a valid answer subarray.

What would be the complexity of the solution, if we were to print all subsets that sum to zero (instead of just one)? 
<div style="page-break-after: always;"></div>
Key Points
====================

1. Needs an additional data structure to keep storing the sum in map
2. Presence of a zero in the array indicates atleast one subarray exists
3. O(n) to determine if a subarray exists

What would be the complexity of the solution, if we were to print all subsets that sum to zero (instead of just one)? 
It will be O(n<sup>2</<sup>)
Key Questions to Ask
====================
1. Is the additional data structure allowed.
2. If not we are left only with O(n<sup>3</<sup>) solution

Approaches
====================
1. Idea is to keep storing sum and the corresponding index for sum till every element in a map. Lookup the sum from map each time , if the sum is already present then the total will be zero. The elements from the index of the sum till the current index is the subset
3. Had to add one hack for handling zeroes
4. If a number is zero it by itself forms the subarray with zero sum

``` java
     
     if (hashTable.containsKey(sum)) {
				Integer startIndex = hashTable.get(sum);
				String string = getArrayString(bars,startIndex, index );
				subArrrays.add(string);
				if(isZeroAdded){
					subArrrays.add("0,"+string);
				}
			} else {
				hashTable.put(sum, index);
			}
```
 
<div style="page-break-after: always;"></div>
Sample Output
=====================
<pre>
Case 1 Happy path
1,2,3,-6
1,2,3,-6

2)
5,1,2,-3,7,-4
1,2,-3
-3,7,-4
</pre>
References
====================
http://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/ (This is a variation of the Maximum Subarray problem)

