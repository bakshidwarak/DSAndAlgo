package leetcode.islandperimeter;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a map in form of a two-dimensional integer grid where 1
 * represents land and 0 represents water. Grid cells are connected
 * horizontally/vertically (not diagonally). The grid is completely surrounded
 * by water, and there is exactly one island (i.e., one or more connected land
 * cells). The island doesn't have "lakes" (water inside that isn't connected to
 * the water around the island). One cell is a square with side length 1. The
 * grid is rectangular, width and height don't exceed 100. Determine the
 * perimeter of the island.
 * 
 * Example:
 * 
 * [[0,1,0,0], [1,1,1,0], [0,1,0,0], [1,1,0,0]]
 * 
 * Answer: 16 Explanation: The perimeter is the 16 yellow stripes in the image
 * below:
 * 
 * @author dwaraknathbs
 *
 */
public class IslandPerimeter {

	public int islandPerimeter(int[][] grid) {

		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		boolean[][] visited = new boolean[grid.length][grid[0].length];

		int[] count = new int[1];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!visited[i][j] && grid[i][j] == 1) {
					exhaust(grid, i, j, count, visited);
				}
			}
		}

		return count[0];

	}

	public void exhaust(int[][] grid, int i, int j, int[] count, boolean[][] visited) {
		if (visited[i][j])
			return;

		visited[i][j] = true;

		List<int[]> neighbours = getNeighbours(grid, i, j);

		count[0] += (4 - neighbours.size());

		for (int[] neighbour : neighbours) {
			exhaust(grid, neighbour[0], neighbour[1], count, visited);
		}
	}

	public List<int[]> getNeighbours(int[][] grid, int i, int j) {
		List<int[]> neighbours = new ArrayList<>();

		if (i + 1 < grid.length && grid[i + 1][j] == 1) {
			neighbours.add(new int[] { i + 1, j });
		}
		if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
			neighbours.add(new int[] { i, j + 1 });
		}
		if (i - 1 >= 0 && grid[i - 1][j] == 1) {
			neighbours.add(new int[] { i - 1, j });
		}
		if (j - 1 >= 0 && grid[i][j - 1] == 1) {
			neighbours.add(new int[] { i, j - 1 });
		}

		return neighbours;
	}

}
