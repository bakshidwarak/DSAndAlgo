package leetcode.mergintervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 * 
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
 * [1,5],[6,9].
 * 
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in
 * as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * 
 * @author dwaraknathbs
 *
 */
public class MergingIntervals {

	public static void main(String[] args) {

		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 3));
		intervals.add(new Interval(6, 9));

		Interval i = new Interval(2, 5);

		insert(intervals, i).forEach(interval -> System.out.println("(" + interval.start + "," + interval.end + ")"));

	}

	static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	/**
	 * The idea is split into three stages
	 * 
	 * 1)Look for all intervals before the given interval and add them as is
	 * 
	 * 2)Merge the new interval till the the end of the new interval is greater
	 * than the subsequent intervals
	 * 
	 * 3)Add the rest of the intervals
	 * 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> mergedIntervals = new ArrayList<Interval>();
		if (intervals.isEmpty()) {
			mergedIntervals.add(newInterval);
			return mergedIntervals;
		}

		int i = 0;
		while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
			mergedIntervals.add(intervals.get(i));
			i++;
		}
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {

			Interval mergingInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start),
					Math.max(intervals.get(i).end, newInterval.end));
			newInterval = mergingInterval;
			i++;
		}

		mergedIntervals.add(newInterval);

		while (i < intervals.size()) {
			mergedIntervals.add(intervals.get(i));
			i++;
		}

		return mergedIntervals;
	}

}
