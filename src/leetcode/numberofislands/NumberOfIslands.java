package leetcode.numberofislands;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * 11110 11010 11000 00000 Answer: 1
 * 
 * Example 2:
 * 
 * 11000 11000 00100 00011 Answer: 3
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class NumberOfIslands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public int numIslands(char[][] grid) {
		if (grid.length == 0)
			return 0;
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				/**
				 * Common mistake is to explore every cell. It is important to
				 * explore only the cells with 1
				 */
				if (!visited[i][j] && grid[i][j] == '1') {
					exhaust(grid, i, j, visited);
					count++;
				}
			}
		}
		return count;
	}

	public void exhaust(char[][] grid, int x, int y, boolean[][] visited) {

		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
			return;
		}
		if (visited[x][y]) {
			return;
		}

		visited[x][y] = true;
		List<Pair> neigbours = getNeighbours(grid, x, y);
		for (Pair p : neigbours) {
			exhaust(grid, p.x, p.y, visited);
		}
	}

	public List<Pair> getNeighbours(char[][] grid, int x, int y) {
		List<Pair> result = new ArrayList<>();

		if (x + 1 < grid.length && y >= 0 && y < grid[0].length && grid[x + 1][y] == '1') {
			result.add(new Pair(x + 1, y));
		}
		if (y + 1 < grid[0].length && x >= 0 && x < grid.length && grid[x][y + 1] == '1') {
			result.add(new Pair(x, y + 1));
		}
		if (x - 1 >= 0 && x - 1 < grid.length && y >= 0 && y < grid[0].length && grid[x - 1][y] == '1') {
			result.add(new Pair(x - 1, y));
		}
		if (y - 1 >= 0 && y - 1 < grid[0].length && x >= 0 && x < grid.length && grid[x][y - 1] == '1') {
			result.add(new Pair(x, y - 1));
		}

		return result;
	}

}
