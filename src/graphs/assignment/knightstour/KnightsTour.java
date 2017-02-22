package graphs.assignment.knightstour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * (This is an interview twist on a classical CS problem)
 * 
 * Assume you're given a normal chessboard and a knight that moves like in
 * normal chess. You are then given two inputs: starting location and ending
 * location in the form of x,y co-ordinates. The goal is to calculate the
 * shortest number of moves that the knight can take to get to the target
 * location.
 * 
 * Input: (All the coordinates start with 0 and end with (rows-1) or (cols -1)).
 * For a 8x8 board the first cell will be (0,0) and the corresponding opposite
 * corner cell will be (7,7)
 * 
 * @author dwaraknathbs
 *
 */
public class KnightsTour {
	static int MAX;
	static int MIN;

	static class Cell {

		int x;
		int y;

		public Cell(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		List<Cell> neighbors = new ArrayList<>();

		/**
		 * Key method that determines given a position of the knight all other
		 * cells it can move to
		 * 
		 * @return
		 */
		List<Cell> getNeighbours() {
			int[] xRange = { -1, -2, 1, 2 };

			for (int i = 0; i < xRange.length; i++) {
				for (int j = 0; j < xRange.length; j++) {
					if (Math.abs(xRange[i]) == Math.abs(xRange[j]))
						continue;
					if (x + xRange[i] < 0 || x + xRange[i] >= MAX || y + xRange[j] < 0 || y + xRange[j] >= MAX)
						continue;
					neighbors.add(new Cell(x + xRange[i], y + xRange[j]));
				}
			}
			return neighbors;
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

	static class Pair {
		Cell c;
		Cell backRef;

		public Pair(Cell c, Cell backRef) {
			super();
			this.c = c;
			this.backRef = backRef;
		}
	}

	public static void main(String[] args) {
		System.out.println(minimumMoves(8, 8, 0, 0, 7, 7));

	}

	static int minimumMoves(int rows, int cols, int startx, int starty, int endx, int endy) {
		MAX = rows;
		MIN = cols;
		Cell start = new KnightsTour.Cell(startx, starty);
		Cell end = new Cell(endx, endy);
		int minimumMoves = bfsPath(start, end);

		return minimumMoves;

	}

	/**
	 * As it is unweighted graph, a simple BFS will give the shortest path
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private static int bfsPath(Cell start, Cell end) {
		Queue<Pair> queue = new LinkedList<>();
		HashSet<Cell> visited = new HashSet<>();
		HashMap<Cell, Cell> backRefs = new HashMap<>();
		queue.add(new KnightsTour.Pair(start, null));
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			Cell current = p.c;
			if (visited.contains(current))
				continue;
			backRefs.put(current, p.backRef);
			visited.add(current);
			if (current.equals(end)) {
				break;
			}
			for (Cell neighbour : current.getNeighbours()) {
				queue.add(new Pair(neighbour, current));
			}
		}

		Cell curr = end;
		int pathCount = 0;
		while (curr != start) {
			pathCount++;
			curr = backRefs.get(curr);
		}

		return pathCount;
	}

}
