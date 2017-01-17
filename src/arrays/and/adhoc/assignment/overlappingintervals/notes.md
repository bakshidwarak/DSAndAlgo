Overlapping Intervals
===========================================================================
Given a set of time intervals in any order, merge all overlapping intervals
into one and output the result which should have only mutually exclusive
intervals. Let the intervals be represented as pairs of integers for
simplicity.

For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }.
The intervals {1,3} and {2,4} overlap with each other, so they should be
merged and become {1, 4}. Similarly {5, 7} and {6, 8} should be merged and
become {5, 8}


<div style="page-break-after: always;"></div>

Key Points
====================
1. Dont overthink the approach.
2. Sort the intervals by start time and a linear scan for each interval will solve in O(NLogN)
2. <b> Make sure you handle first interval</b>
3.Other solutions that came to my mind but didnt seem very elegant
	1. IntervalTrees - Takes NLogN to build and seems to be efficient for querying and my knowhow of Interval Trees is limited
	2. Maintaining an array with indices ranging from 0 till the end time. For every interval add+1 to the array( O(N<sup>2</sup>)
	3. Compare every interval with every other interval

4. <b> Remember to get the max of the currentEnd time and interval's end time to handle sub intervals </b>


Key Questions to Ask
====================
1. Can I use additional data structures( like stacks or interval trees)
2. Am I allowed to sort the input set

Approaches
====================

1. Sort the input by start time( if creating a Interval class make sure it overrides compareTo)
2. Track start and end time
3.Iterate the input set
	..1 Check for first Node
	..2 if interval.start < end && interval.end >=start( <b> We need to check both start and end because the interval could also be a sub interval eg (1,4) and (2,3)</b>
	<b> Remember to get the max of the currentEnd time and interval's end time to handle sub intervals </b>
		..1 Reset end to the current interval's end
	..3 else
	    ..1 Create an interval with  start and Max(current intervals end ,endInterval)  and add to the overlap set
	    
4. <b>Handle last interval</b>Finally add the current start and end to the overlapping interval

``` java

     for (Interval interval : originalIntervals) {
			if (firstItem) {
				lastStart = interval.getStart();
				lastEnd = interval.getEnd();
				firstItem = false;
			}
			if (interval.getStart() <= lastEnd && interval.getEnd() >= lastStart) {
				lastEnd = Math.max(interval.getEnd(),lastEnd);
			} else {
				mergedIntervals.add(Interval.of(lastStart, lastEnd));
				lastStart = interval.getStart();
				lastEnd = interval.getEnd();
			}
	    }
		mergedIntervals.add(Interval.of(lastStart, lastEnd));
```

<div style="page-break-after: always;"></div>

Sample Output
=====================
Case 1 Happy path
1,3
2,4
3,5
6,7
7,8
{ 1 , 3 }
{ 2 , 4 }
{ 3 , 5 }
{ 6 , 7 }
{ 7 , 8 }
Merged Intervals
{ 1 , 5 }
{ 6 , 8 }


Case 2 No overlap intervals
1,2
3,4
6,7
8,9
{ 1 , 2 }
{ 3 , 4 }
{ 6 , 7 }
{ 8 , 9 }
Merged Intervals
{ 1 , 2 }
{ 3 , 4 }
{ 6 , 7 }
{ 8 , 9 }

Case 3 Overlapping sub intervals

1,4
2,3
5,6
{ 1 , 4 }
{ 2 , 3 }
{ 5 , 6 }
Merged Intervals
{ 1 , 4 }
{ 5 , 6 }

References
====================
http://www.geeksforgeeks.org/merging-intervals/

