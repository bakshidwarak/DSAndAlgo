

package hackerrank.connectedcellinagrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Check out the resources on the page's right side to learn more about
 * depth-first search. The video tutorial is by Gayle Laakmann McDowell, author
 * of the best-selling interview book Cracking the Coding Interview. Consider a
 * matrix with rows and columns, where each cell contains either a or a and any
 * cell containing a is called a filled cell. Two cells are said to be connected
 * if they are adjacent to each other horizontally, vertically, or diagonally;
 * in other words, cell is connected to cells , , , , , , , and , provided that
 * the location exists in the matrix for that . If one or more filled cells are
 * also connected, they form a region. Note that each cell in a region is
 * connected to at least one other cell in the region but is not necessarily
 * directly connected to all the other cells in the region. Task Given an
 * matrix, find and print the number of cells in the largest region in the
 * matrix. Note that there may be more than one region in the matrix. Input
 * Format The first line contains an integer, , denoting the number of rows in
 * the matrix. The second line contains an integer, , denoting the number of
 * columns in the matrix. Each line of the subsequent lines contains
 * space-separated integers describing the respective values filling each row in
 * the matrix. Constraints
 * 
 * <pre>

Output Format

Print the number of cells in the largest region in the given matrix.

Sample Input

4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0
Sample Output

5
Explanation

The diagram below depicts two regions of the matrix; for each region, the component cells forming the region are marked with an X:

X X 0 0     1 1 0 0
0 X X 0     0 1 1 0
0 0 X 0     0 0 1 0
1 0 0 0     X 0 0 0
 * </pre>
 * 
 * The first region has five cells and the second region has one cell. Because
 * we want to print the number of cells in the largest region of the matrix, we
 * print .
 * 
 * @author dwaraknathbs
 */
public class ConnectedCellInAGrid {

    static class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Counter {
        int count;

        public Counter() {
            count = 0;
        }

        public void increment() {
            count++;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for (int grid_i = 0; grid_i < n; grid_i++) {
            for (int grid_j = 0; grid_j < m; grid_j++) {
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        boolean visited[][] = new boolean[n][m];
        int maxCount = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    Counter count = new Counter();

                    exhaust(grid, visited, i, j, count);
                    maxCount = Math.max(count.count, maxCount);
                }

            }

        System.out.println(maxCount);
    }

    public static void exhaust(int[][] grid, boolean[][] visited, int row, int col, Counter count) {
        if (visited[row][col])
            return;
        if (grid[row][col] == 1)
            count.increment();
        visited[row][col] = true;
        for (Pair p : getNeighbours(grid, row, col)) {
            exhaust(grid, visited, p.i, p.j, count);
        }
    }

    public static List<Pair> getNeighbours(int[][] grid, int row, int col) {

        List<Pair> pairs = new ArrayList<>();
        int[] indices = { 1, 0, -1 };
        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < indices.length; j++) {
                int newX = indices[i] + row;
                int newY = indices[j] + col;
                if (newX >= grid.length || newX < 0 || newY >= grid[0].length || newY < 0)
                    break;
                if (grid[newX][newY] == 1) {
                    pairs.add(new Pair(newX, newY));
                }
            }
        }
        return pairs;
    }

}
