

package leetcode.painthouses;

/**
 * There are a row of n houses, each house can be painted with one of the three
 * colors: red, blue or green. The cost of painting each house with a certain
 * color is different. You have to paint all the houses such that no two
 * adjacent houses have the same color. The cost of painting each house with a
 * certain color is represented by a n x 3 cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color red; costs[1][2] is the cost of
 * painting house 1 with color green, and so on... Find the minimum cost to
 * paint all houses. Note: All costs are positive integers.
 * 
 * @author dwaraknathbs
 */
public class PaintHouse {

    public static void main(String[] args) {
        int[][] costs = { { 2, 3, 4 }, { 3, 4, 5 }, { 1, 1, 1 } };
        System.out.println(minCost(costs));
        System.out.println(minCostHelperIterative(costs));

    }

    public static int minCost(int[][] costs) {
        int[][] costCache = new int[costs.length][3];
        for (int i = 0; i < costCache.length; i++) {
            for (int j = 0; j < 3; j++) {
                costCache[i][j] = -1;
            }
        }
        int cost = Math.min(minCostHelper(costs, 0, 0, costCache), Math.min(minCostHelper(costs, 0, 1, costCache), minCostHelper(costs, 0, 2, costCache)));
        for (int i = 0; i < costCache.length; i++) {
            System.out.println();

            for (int j = 0; j < 3; j++) {
                System.out.print(costCache[i][j] + " ");
            }
        }
        return cost;
    }

    public static int minCostHelperIterative(int[][] costs) {
        int[][] costCache = new int[costs.length + 1][3];
        for (int j = 0; j < 3; j++) {
            costCache[costs.length][j] = 0;
        }

        for (int i = costs.length - 1; i >= 0; i--)
            for (int color = 0; color < 3; color++) {
                int minCost = Integer.MAX_VALUE - 1;
                for (int j = 0; j < 3; j++) {
                    if (j == color)
                        continue;
                    int neighbourAfter = costCache[i + 1][j];
                    minCost = Math.min(minCost, neighbourAfter);
                }

                costCache[i][color] = costs[i][color] + minCost;
            }
        for (int i = 0; i < costCache.length; i++) {
            System.out.println();

            for (int j = 0; j < 3; j++) {
                System.out.print(costCache[i][j] + " ");
            }
        }

        return Math.min(costCache[0][0], Math.min(costCache[0][1], costCache[0][2]));
    }

    public static int minCostHelper(int[][] costs, int startIndex, int color, int[][] costCache) {
        if (startIndex >= costs.length || startIndex < 0)
            return 0;
        int minCost = Integer.MAX_VALUE - 1;
        if (costCache[startIndex][color] == -1) {
            for (int j = 0; j < 3; j++) {
                if (j == color)
                    continue;
                int neighbourAfter = minCostHelper(costs, startIndex + 1, j, costCache);
                minCost = Math.min(minCost, neighbourAfter);
            }

            costCache[startIndex][color] = costs[startIndex][color] + minCost;
        }
        return costCache[startIndex][color];
    }

    public static int minCostHelperRecursive(int[][] costs, int startIndex, int color) {
        if (startIndex >= costs.length || startIndex < 0)
            return 0;
        int minCost = Integer.MAX_VALUE - 1;

        for (int j = 0; j < 3; j++) {
            if (j == color)
                continue;
            int neighbourAfter = minCostHelperRecursive(costs, startIndex + 1, j);
            minCost = Math.min(minCost, neighbourAfter);
        }

        int cost = costs[startIndex][color] + minCost;

        return cost;
    }

}
