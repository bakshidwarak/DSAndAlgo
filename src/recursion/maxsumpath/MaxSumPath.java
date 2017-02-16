package recursion.maxsumpath;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
		Set<Integer> currentPath= new HashSet<>();

		System.out.println(maxPath(arr, 0, 0,paths,currentPath));
		
		currentPath.forEach(System.out::print);

	}

	private static int maxPath(int[][] arr, int i, int j, List<List<Integer>> paths, Set<Integer> path) {
		if (i == arr.length - 1 && j == arr.length - 1) {

			
					return arr[i][j];
		}
		if(i>arr.length-1 || j>arr.length-1)
			return 0;

		
		int right = maxPath(arr, i, j + 1,paths,path);
		
//		
		//path.add(arr[i][j]);
		int down = maxPath(arr, i + 1, j,paths,path);
	
		if (right > down) {
			path.add(arr[i][j+1]);
			return right + arr[i][j];
		} else {
			
			path.add(arr[i+1][j]);

			return down + arr[i][j];
		}
		

	}

}
