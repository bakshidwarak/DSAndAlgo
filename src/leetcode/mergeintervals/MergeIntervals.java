package leetcode.mergeintervals;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 * 
 * @author dwaraknathbs
 *
 */
public class MergeIntervals {

	public class Interval {
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

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new ArrayList<>();
		if (intervals.size() == 0)
			return result;
		intervals.sort((interval1, interval2) -> interval1.start - interval2.start);
		Interval first = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			if (current.start <= first.end) {
				/**
				 * Note it is important to take max
				 */
				first = new Interval(first.start, Math.max(first.end, current.end));

			} else {
				result.add(first);
				first = current;
			}
		}
		result.add(first);
		return result;
	}

}
