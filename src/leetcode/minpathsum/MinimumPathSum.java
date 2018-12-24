package leetcode.minpathsum;

/**
 * 64. Minimum Path Sum Medium
 * 
 * 993
 * 
 * 25
 * 
 * Favorite
 * 
 * Share Given a m x n grid filled with non-negative numbers, find a path from
 * top left to bottom right which minimizes the sum of all numbers along its
 * path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example:
 * 
 * <pre>
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
 * </pre>
 * 
 * Output: 7 Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * 
 * @author dwaraknathbs
 *
 */
public class MinimumPathSum {

	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int[][] result = new int[grid.length][grid[0].length];
		/*
		 * for(int i=0; i<result.length;i++){ for(int j=0;
		 * j<result[0].length;j++){ result[i][j]=-1; } }
		 */

		for (int x = grid.length - 1; x >= 0; x--) {
			for (int y = grid[0].length - 1; y >= 0; y--) {
				if (x == grid.length - 1 && y == grid[0].length - 1) {
					result[x][y] = grid[x][y];
				} else {
					int pathAlongX = Integer.MAX_VALUE;
					int pathAlongY = Integer.MAX_VALUE;

					if (x < grid.length - 1) {
						pathAlongX = result[x + 1][y];
					}
					if (y < grid[0].length - 1) {
						pathAlongY = result[x][y + 1];
					}

					result[x][y] = grid[x][y] + Math.min(pathAlongX, pathAlongY);
				}
			}
		}
		return result[0][0];
		// return helper(grid,0,0,result);

	}

	public int helper(int[][] grid, int x, int y, int[][] result) {
		if (result[x][y] == -1) {
			if (x == grid.length - 1 && y == grid[0].length - 1) {
				result[x][y] = grid[x][y];
			} else {
				int pathAlongX = Integer.MAX_VALUE;
				int pathAlongY = Integer.MAX_VALUE;

				if (x < grid.length - 1) {
					pathAlongX = helper(grid, x + 1, y, result);
				}
				if (y < grid[0].length - 1) {
					pathAlongY = helper(grid, x, y + 1, result);
				}

				result[x][y] = grid[x][y] + Math.min(pathAlongX, pathAlongY);
			}
		}

		return result[x][y];

	}

}
