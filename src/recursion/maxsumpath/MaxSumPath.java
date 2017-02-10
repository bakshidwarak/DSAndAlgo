package recursion.maxsumpath;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

/**
 * Given a 2D array of numbers , find the path with max sum
 * 
 * @author dwaraknathbs
 *
 */
public class MaxSumPath {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		int arr[][] = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = scanner.nextInt();
			}
		}

		maxSumPath(arr);
		scanner.close();

	}

	private static void maxSumPath(int[][] arr) {
		
		List<List<Integer>> paths= new ArrayList<>();
		List<Integer> currentPath= new ArrayList<>();

		System.out.println(maxPath(arr, 0, 0,paths,currentPath));
		
		paths.forEach(System.out::println);

	}

	private static int maxPath(int[][] arr, int i, int j, List<List<Integer>> paths, List<Integer> path) {
		if (i == arr.length - 1 && j == arr.length - 1) {

			path.add(arr[i][j]);
			paths.add(new ArrayList<>(path));
			
			return arr[i][j];
		}
		if(i>arr.length-1 || j>arr.length-1)
			return 0;

		path.add(arr[i][j]);
		int right = maxPath(arr, i, j + 1,paths,path);
		
//		
		//path.add(arr[i][j]);
		int down = maxPath(arr, i + 1, j,paths,path);
		path.remove((path.size()-1));
		if (right > down) {
			
			return right + arr[i][j];
		} else {
			
			

			return down + arr[i][j];
		}
		

	}

}
