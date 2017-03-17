package leetcode.painthouses;

public class PaintHouse {

	public static void main(String[] args) {
		int[][] costs = { { 17, 2, 17 }, { 16, 16, 5 }, { 14, 3, 19 } };
		System.out.println(minCost(costs));

	}

	public static int minCost(int[][] costs) {

		int minCost = Integer.MAX_VALUE - 1;
		for (int j = 0; j < 3; j++)
			minCost = Math.min(minCost, minCostHelper(costs, 0, j));
		return minCost;

	}

	public static int minCostHelper(int[][] costs, int startIndex, int colorToExclude) {
		for (int i = 0; i <= startIndex; i++)
			System.out.print("     ");
		System.out.println("(" + startIndex + " , " + colorToExclude + ")");
		int minCost = Integer.MAX_VALUE - 1;
		if (startIndex == costs.length - 1) {
			for (int j = 0; j < 3; j++) {
				if (j == colorToExclude)
					continue;
				minCost = Math.min(minCost, costs[startIndex][j]);
			}

		} else {
			int currentCost = 0;
			for (int i = startIndex; i < costs.length; i++) {
				for (int j = 0; j < 3; j++) {
					if (j == colorToExclude)
						continue;
					if (i + 1 < costs.length) {
						currentCost = costs[i][j] + minCostHelper(costs, i + 1, j);
						minCost = Math.min(minCost, currentCost);
					}
				}
			}
		}
		for (int i = 0; i <= startIndex; i++)
			System.out.print("     ");
		System.out.println(
				"current house" + startIndex + " with colors to exclude " + colorToExclude + " min " + minCost);
		return minCost == Integer.MAX_VALUE - 1 ? 0 : minCost;
	}

}
