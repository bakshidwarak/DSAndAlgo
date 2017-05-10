package leetcode.meetingroom1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all
 * meetings.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 * 
 * Hide Company Tags
 * 
 * @author dwaraknathbs
 *
 */
public class MeetingRoom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

	Comparator<Interval> intervalComparator = new Comparator<Interval>() {
		public int compare(Interval i1, Interval i2) {
			return Integer.compare(i1.start, i2.start);
		}
	};

	/**
	 * Basic idea is to sort the intervals by start time and compare one by one.
	 * If a meetings end time is > than next meetings start there is an overlap
	 * 
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
		if (intervals.length == 0)
			return true;
		Arrays.sort(intervals, intervalComparator);
		Interval prev = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			Interval current = intervals[i];
			if (prev.end > current.start)
				return false;

			prev = current;
		}
		return true;
	}

}
