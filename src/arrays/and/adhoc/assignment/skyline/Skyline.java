package arrays.and.adhoc.assignment.skyline;

/**
 * Skyline! This is a very popular problem. Make sure you nail it. Here is the
 * problem definition:
 * 
 * You are given a set of n rectangles in no particular order. They have varying
 * widths and heights, but their bottom edges are collinear, so that they look
 * like buildings on a skyline. For each rectangle, you’re given the x position
 * of the left edge, the x position of the right edge, and the height. Your task
 * is to draw an outline around the set of rectangles so that you can see what
 * the skyline would look like when silhouetted at night.
 * 
 * Each building is represented by triplet (left, ht, right) ‘left': is x
 * coordinated of left side (or wall). ‘right': is x coordinate of right side
 * ‘ht': is height of building.
 * 
 * 
 * 
 * 
 * For example, the building above, is represented as (1, 11, 5).
 * 
 * A skyline is a collection of rectangular strips. A rectangular strip is
 * represented as a pair (left, ht) where left is x coordinate of left side of
 * strip and ht is height of strip.
 * 
 * Examples: Input: Array of buildings { (1,11,5), (2,6,7), (3,13,9), (12,7,16),
 * (14,3,25), (19,18,22), (23,13,29), (24,4,28) }
 * 
 * Output: Skyline (an array of rectangular strips) A strip has x coordinate of
 * left side and height (1, 11), (3, 13), (9, 0), (12, 7), (16, 3), (19, 18),
 * (22, 3), (25, 0)
 * 
 * The below figure demonstrates input and output.
 * 
 * The left side shows buildings and right side shows skyline.
 * 
 * 
 * 
 * 
 * Consider following as another example when there is only one building Input:
 * {(1, 11, 5)} Output: (1, 11), (5, 0)
 * 
 * Solution: Good explanation with visuals:
 * https://briangordon.github.io/2014/08/the-skyline-problem.html Succinct
 * explanation:
 * http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
 * More granular problem definition:
 * https://leetcode.com/problems/the-skyline-problem/
 * 
 * Interview time: 40 minutes
 * 
 * @author dwaraknathbs
 *
 */
public class Skyline {

	static class Strip {
		int left;
		int height;

		public Strip(int left, int height) {
			super();
			this.left = left;
			this.height = height;
		}
	}

	static class SkylineView {
		Strip[] strips;
		int capacity;
		/**
		 * Maintaining totalStrips is very important and not capacity while
		 * merging. Capacity could be more but if the height of the strip is
		 * equal to the previous strip, the strip can be ignored and hence we
		 * need to track the total number of strips
		 */
		int totalStrips = 0;

		public SkylineView(int capacity) {
			super();
			this.capacity = capacity;
			strips = new Strip[capacity];
		}

	}

	static class Building {
		int left;
		int height;
		int right;

		public Building(int left, int height, int right) {
			super();
			this.left = left;
			this.height = height;
			this.right = right;
		}

	}

	public static void main(String[] args) {

		Building b1 = new Building(1, 11, 5);
		Building b2 = new Building(2, 6, 7);
		Building b3 = new Building(3, 13, 9);
		Building b4 = new Building(12, 7, 16);
		Building b5 = new Building(14, 3, 25);
		Building b6 = new Building(19, 18, 22);
		Building b7 = new Building(23, 13, 29);
		Building b8 = new Building(24, 4, 28);

		Building[] buildings = new Building[8];
		buildings[0] = b1;
		buildings[1] = b2;
		buildings[2] = b3;
		buildings[3] = b4;
		buildings[4] = b5;
		buildings[5] = b6;
		buildings[6] = b7;
		buildings[7] = b8;

		SkylineView view = getSkyline(buildings);
		printSkyline(view);
	}

	private static void printSkyline(SkylineView view) {
		for (int i = 0; i < view.totalStrips; i++) {
			System.out.println("(" + view.strips[i].left + "," + view.strips[i].height + ")");
		}

	}

	static SkylineView getSkyline(Building[] buildings) {
		return getSkylineByDivideAndConquer(buildings, 0, buildings.length - 1);
	}

	/**
	 * Very similar to merge sort. We can divide and conquer the strips to
	 * achieve the skyline in NLogN time
	 * 
	 * @param buildings
	 * @param low
	 * @param high
	 * @return
	 */
	private static SkylineView getSkylineByDivideAndConquer(Building[] buildings, int low, int high) {
		if (low == high) {
			SkylineView skylineView = new SkylineView(2);
			Strip[] strips = new Strip[2];
			strips[0] = new Strip(buildings[low].left, buildings[low].height);
			strips[1] = new Strip(buildings[low].right, 0);
			skylineView.strips = strips;
			skylineView.totalStrips = 2;
			return skylineView;
		}
		int mid = (low + high) / 2;

		SkylineView leftSideView = getSkylineByDivideAndConquer(buildings, low, mid);

		SkylineView rightSideView = getSkylineByDivideAndConquer(buildings, mid + 1, high);

		SkylineView completeView = mergeSkylines(leftSideView, rightSideView);
		return completeView;

	}

	/**
	 * Given two Skyline views, merges the strips in them
	 * 
	 * @param leftSideView
	 * @param rightSideView
	 * @return
	 */
	private static SkylineView mergeSkylines(SkylineView leftSideView, SkylineView rightSideView) {
		int i = 0;
		int j = 0;
		int k = 0;
		int currentLeftHeight = 0;
		int currentRightHeight = 0;
		SkylineView view = new SkylineView(leftSideView.totalStrips + rightSideView.totalStrips);

		while (isPresent(leftSideView) && i < leftSideView.totalStrips && isPresent(rightSideView)
				&& j < rightSideView.totalStrips) {

			if (leftSideView.strips[i].left < rightSideView.strips[j].left) {

				int x1 = leftSideView.strips[i].left;
				currentLeftHeight = leftSideView.strips[i].height;
				int height = Math.max(currentRightHeight, currentLeftHeight);
				Strip strip = new Strip(x1, height);
				if (k > 0 && view.strips[k - 1].height == height) {
					i++;
					continue;
				}

				if (k > 0 && view.strips[k - 1].left == x1) {
					view.strips[k - 1].height = Math.max(height, view.strips[k - 1].height);
					continue;
				}

				view.strips[k] = strip;
				view.totalStrips++;
				i++;
				k++;

			} else {
				int x1 = rightSideView.strips[j].left;
				currentRightHeight = rightSideView.strips[j].height;
				int height = Math.max(currentRightHeight, currentLeftHeight);
				if (k > 0 && view.strips[k - 1].height == height) {
					j++;
					continue;
				}

				if (k > 0 && view.strips[k - 1].left == x1) {
					view.strips[k - 1].height = Math.max(height, view.strips[k - 1].height);
					continue;
				}

				Strip strip = new Strip(x1, height);
				view.strips[k] = strip;
				view.totalStrips++;
				j++;
				k++;

			}
		}
		while (i >= leftSideView.totalStrips && isPresent(rightSideView) && j < rightSideView.totalStrips) {
			int x1 = rightSideView.strips[j].left;
			currentRightHeight = rightSideView.strips[j].height;
			int height = Math.max(currentRightHeight, currentLeftHeight);
			if (k > 0 && view.strips[k - 1].height == height) {
				j++;
				continue;
			}

			if (k > 0 && view.strips[k - 1].left == x1) {
				view.strips[k - 1].height = Math.max(height, view.strips[k - 1].height);
				continue;
			}

			Strip strip = new Strip(x1, height);
			view.strips[k] = strip;
			view.totalStrips++;
			j++;
			k++;
		}

		while (j >= rightSideView.totalStrips && isPresent(leftSideView) && i < leftSideView.totalStrips) {
			int x1 = leftSideView.strips[i].left;
			currentLeftHeight = leftSideView.strips[i].height;
			int height = Math.max(currentRightHeight, currentLeftHeight);
			if (k > 0 && view.strips[k - 1].height == height) {
				i++;
				continue;
			}

			if (k > 0 && view.strips[k - 1].left == x1) {
				view.strips[k - 1].height = Math.max(height, view.strips[k - 1].height);
				i++;
				continue;
			}

			Strip strip = new Strip(x1, height);
			view.strips[k] = strip;
			view.totalStrips++;
			i++;
			k++;
		}
		return view;
	}

	private static boolean isPresent(SkylineView leftSideView) {
		return leftSideView != null && leftSideView.strips != null;
	}

}
