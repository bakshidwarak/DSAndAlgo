package graphs.assignment.rainfallchallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Rainfall Challenge (This is a popular Palantir problem)
 * 
 * Problem Statement
 * 
 * A group of farmers has some elevation data, and we're going to help them
 * understand how rainfall flows over their farmland.
 * 
 * We'll represent the land as a two-dimensional array of altitudes and use the
 * following model, based on the idea that water flows downhill:
 * 
 * If a cell’s four neighboring cells all have higher altitudes, we call this
 * cell a sink; water collects in sinks.
 * 
 * Otherwise, water will flow to the neighboring cell with the lowest altitude.
 * If a cell is not a sink, you may assume it has a unique lowest neighbor and
 * that this neighbor will be lower than the cell.
 * 
 * Cells that drain into the same sink – directly or indirectly – are said to be
 * part of the same basin.
 * 
 * Your challenge is to partition the map into basins. In particular, given a
 * map of elevations, your code should partition the map into basins and output
 * the sizes of the basins, in descending order.
 * 
 * Assume the elevation maps are square. Some farmers have small land plots such
 * as the examples below, while some have larger plots. However, in no case will
 * a farmer have a plot of land larger than S = 5000.
 * 
 * Your code should output a space-separated list of the basin sizes, in
 * descending order.
 * 
 * Suggested time: 55 minutes. This is difficult to understand, code and test in
 * under an hour. But that's the expectation.
 * 
 * Solution
 * 
 * The problem and the solution both, are from here:
 * http://codereview.stackexchange.com/questions/38500/rainfall-challenge Please
 * be sure to read author's solution and criticism of the answer. It's very
 * instructive.
 * 
 * A few examples are below:
 * 
 * <pre>
-----------------------------------------
Input:                 Output: 
 3                      7 2
 1 5 2 
 2 4 7 
 3 6 9 

The basins, labeled with A’s and B’s, are: 
 A A B 
 A A B 
 A A A 
-----------------------------------------
Input:                  Output: 
 1                       1
 10 

There is only one basin in this case. 
The basin, labeled with A’s is: 
 A
-----------------------------------------
Input:                  Output:            
 5                       11 7 7
 1 0 2 5 8 
 2 3 4 7 9 
 3 5 7 8 9 
 1 2 5 4 3 
 3 3 5 2 1 

The basins, labeled with A’s, B’s, and C’s, are: 
 A A A A A 
 A A A A A 
 B B A C C 
 B B B C C 
 B B C C C 
-----------------------------------------
Input:                  Output: 
 4                       7 5 4
 0 2 1 3                
 2 1 0 4 
 3 3 3 3 
 5 5 2 1 

The basins, labeled with A’s, B’s, and C’s, are: 
 A A B B 
 A B B B 
 A B B C 
 A C C C
-----------------------------------------
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class RainfallChallenge {

	static class Cell {
		int x;
		int y;

		public Cell(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input number of rows");
		int rows = scanner.nextInt();
		System.out.println("Input number of cols");
		int cols = scanner.nextInt();

		int[][] cells = new int[rows][cols];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				cells[i][j] = scanner.nextInt();

		findBasins(cells, rows, cols);

	}

	/**
	 * Simple DFS to get all the neighbours and exhaust them
	 * 
	 * @param cells
	 * @param rows
	 * @param cols
	 */
	private static void findBasins(int[][] cells, int rows, int cols) {
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				visited[i][j] = false;

		Map<Cell, Set<Cell>> basinMap = new HashMap<>();
		HashSet<Cell> sinks = new HashSet<>();

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				if (!visited[i][j]) {
					exhaust(cells, i, j, visited, basinMap, sinks);
				}
			}
		recurseAndPrintBasins(basinMap, cells, sinks);

	}

	/**
	 * Simple DFS exhaust where each of the Cell is marked as visited and all
	 * its neighbours visited.
	 * 
	 * @param cells
	 * @param i
	 * @param j
	 * @param visited
	 * @param map
	 * @param sinks
	 */
	private static void exhaust(int[][] cells, int i, int j, boolean[][] visited, Map<Cell, Set<Cell>> map,
			HashSet<Cell> sinks) {
		Cell current = new Cell(i, j);

		if (visited[i][j]) {

			return;
		}

		visited[i][j] = true;

		Cell c = getNeighbour(current, cells);
		/**
		 * If I am the minimum cell amoung neighbours I am the sink and hence
		 * current is a sink
		 */
		if (c.equals(current)) {
			sinks.add(current);
		} else {
			/**
			 * I am not a sink and I add myself to the set of other basins that
			 * flow into my neighbour
			 */
			if (map.containsKey(c)) {
				map.get(c).add(current);
			} else {
				HashSet<Cell> cell = new HashSet<>();
				cell.add(current);
				map.put(c, cell);
			}
		}

		exhaust(cells, c.x, c.y, visited, map, sinks);

	}

	/**
	 * Amongst all the neighbouring cell and current cell returns the minimum
	 * cell
	 * 
	 * @param current
	 * @param cells
	 * @return
	 */
	private static Cell getNeighbour(Cell current, int[][] cells) {

		int[] shifts = { 0, -1, 1 };
		Cell minCell = new Cell(current.x, current.y);
		int x = current.x;
		int y = current.y;
		int min = cells[current.x][current.y];

		for (int i = 0; i < shifts.length; i++) {
			for (int j = 0; j < shifts.length; j++) {
				if (Math.abs(shifts[i]) == Math.abs(shifts[j]))
					continue;
				if (x + shifts[i] >= 0 && x + shifts[i] < cells.length && y + shifts[j] >= 0
						&& y + shifts[j] < cells.length) {
					if (cells[x + shifts[i]][y + shifts[j]] < min) {
						min = cells[x + shifts[i]][y + shifts[j]];
						minCell = new Cell(x + shifts[i], y + shifts[j]);
					}
				}
			}

		}

		return minCell;
	}

	/**
	 * After we have exhausted, we have two objects with us
	 * 
	 * @param basins
	 *            -> The Map of all the cells to the set neighbouring basins
	 *            which flow into it
	 * 
	 * @param cells
	 * @param sinks
	 *            -> If there are no neighbours I flow into, I am a sink
	 */
	private static void recurseAndPrintBasins(Map<Cell, Set<Cell>> basins, int[][] cells, HashSet<Cell> sinks) {

		List<Set<Cell>> allBasins = new ArrayList<>();
		for (Cell sink : sinks) {
			HashSet<Cell> currentBasin = new HashSet<>();
			getBasins(sink, currentBasin, basins);
			allBasins.add(currentBasin);

		}
		allBasins.forEach(e -> System.out.println(e.stream().map(cell -> cells[cell.x][cell.y]).sorted()
				.map(String::valueOf).collect(Collectors.joining(","))));
		allBasins.forEach(e -> System.out.println(e.size()));

	}

	private static void getBasins(Cell currentCell, HashSet<Cell> basin, Map<Cell, Set<Cell>> basins) {
		basin.add(currentCell);
		Set<Cell> set = basins.get(currentCell);
		if (set != null) {
			for (Cell current : set) {
				getBasins(current, basin, basins);
			}

		}
	}

}
