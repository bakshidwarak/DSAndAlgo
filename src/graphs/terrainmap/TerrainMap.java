package graphs.terrainmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a terrain map of data. Water flows from higer terrain to lower terrain.
 * Calculate the number of basins and the number of basins
 * 
 * @author dwaraknathbs
 *
 */
public class TerrainMap {

	public static void main(String[] args) {
		int[][] terrainMap = new int[3][3];
		int[] values = { 8, 10, 0, 13, 15, 14, 0, 15, 20 };
		int k = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				terrainMap[i][j] = values[k++];

		int totalBasins = getTotalBasins(terrainMap);
		System.out.println(totalBasins);

	}

	private static int getTotalBasins(int[][] terrainMap) {
		int[][] visited = new int[terrainMap.length][terrainMap.length];
		List<Pair> basins = new ArrayList<>();
		for (int i = 0; i < terrainMap.length; i++)
			for (int j = 0; j < terrainMap.length; j++)
				getBasins(terrainMap, visited, i, j, basins);

		basins.stream().forEach(p -> System.out.println("(" + p.x + "," + p.y + ")"));
		return basins.size();
	}

	private static void getBasins(int[][] terrainMap, int[][] visited, int i, int j, List<Pair> basins) {
		if (visited[i][j] == 1)
			return;

		visited[i][j] = 1;
		Pair p = getLowestNeighbour(terrainMap, i, j);
		if (p.x == i && p.y == j) {
			basins.add(p);
		}
		getBasins(terrainMap, visited, p.x, p.y, basins);

	}

	private static Pair getLowestNeighbour(int[][] terrainMap, int i, int j) {
		int[] range = { 0, 1, -1 };
		Pair minij = new Pair(i, j);
		for (int k = 0; k < range.length; k++)
			for (int l = 0; l < range.length; l++) {
				int newX = i + range[k];
				int newY = j + range[l];
				if (newX < 0 || newX >= terrainMap.length || newY < 0 || newY >= terrainMap.length)
					continue;

				if (terrainMap[newX][newY] < terrainMap[minij.x][minij.y]) {

					minij = new Pair(newX, newY);

				}
			}

		return minij;
	}
}

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
