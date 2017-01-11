package arrays.and.adhoc.assignment.overlappingintervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Given a set of time intervals in any order, merge all overlapping intervals
 * into one and output the result which should have only mutually exclusive
 * intervals. Let the intervals be represented as pairs of integers for
 * simplicity.
 * 
 * For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }.
 * The intervals {1,3} and {2,4} overlap with each other, so they should be
 * merged and become {1, 4}. Similarly {5, 7} and {6, 8} should be merged and
 * become {5, 8}
 * 
 * @author dwaraknathbs
 *
 */
public class OverlappingIntervals {

	public static void main(String[] args) {
		List<Interval> originalIntervals = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] timeSplit = line.split(",");
			originalIntervals.add(Interval.of(Integer.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1])));
		}
		scanner.close();
		printIntervals(originalIntervals);
		Collections.sort(originalIntervals);
		List<Interval> mergedIntervals = new ArrayList<>();
		int lastStart = 0;
		int lastEnd = 0;
		boolean firstItem = true;

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
		System.out.println("Merged Intervals");
		printIntervals(mergedIntervals);

	}

	private static void printIntervals(List<Interval> intervalSet) {
		intervalSet.stream().forEach(System.out::println);

	}

}

class Interval implements Comparable<Interval> {
	private Integer start;
	private Integer end;

	private Interval(Integer start, Integer end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	@Override
	public int compareTo(Interval o) {
		return this.getStart().compareTo(o.getStart());
	}

	public static Interval of(int start, int end) {
		return new Interval(start, end);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("{ %d , %d }", start, end);
	}

}
