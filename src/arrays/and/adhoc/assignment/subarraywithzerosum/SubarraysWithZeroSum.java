package arrays.and.adhoc.assignment.subarraywithzerosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * SumZero
 * 
 * Given a set of integers, find a contiguous subset whose sum is zero. There
 * can be duplicate numbers in the input.
 * 
 * Input: Integer array e.g. 5,1,2,-3,7,-4
 * 
 * output: A subset that sums to zero.
 * 
 * e.g. 1,2,-3 OR -3,7,-4
 * 
 * If there are no such subsets, then print nothing
 * 
 * If there are multiple such subsets, then print any one
 * 
 * If a matching subset is a subset of a larger matching subset, then print
 * either one
 * 
 * If there is a number '0' in the array, then it counts as a valid answer
 * subarray.
 * 
 * What would be the complexity of the solution, if we were to print all subsets
 * that sum to zero (instead of just one)?
 * 
 * Solution:
 * http://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/ (This is
 * a variation of the Maximum Subarray problem)
 * 
 * @author dwaraknathbs
 *
 */
public class SubarraysWithZeroSum {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] inpString = input.split(",");
		scanner.close();
		int[] bars =new int[inpString.length];
		int i=0;
		for(String str:inpString){
			bars[i++]=Integer.parseInt(str);
		}
		String[] zeroSetes=sum2zero(bars);
		Arrays.stream(zeroSetes).forEach(System.out::println);

	}

	private static String[] sum2zero(int[] bars) {
		Hashtable<Integer, Integer> hashTable = new Hashtable<>();
		List<String> subArrrays = new ArrayList<>();
		int index = 1;
		int sum = 0;
		boolean isZeroAdded = false;
		/**
		 * Idea is to keep storing the continuous sum in a hashtable for every
		 * number read from the array. If the sum already exists in the table
		 * then mark the index of the sum and the current index in a list
		 */
		for (int i : bars) {
			/**
			 * If a number is zero it by itself forms the subarray with zero sum
			 */
			if (i == 0 && !isZeroAdded) {
				subArrrays.add("0");
				isZeroAdded = true;
			}
			sum += i;
			if (hashTable.containsKey(sum)) {
				Integer startIndex = hashTable.get(sum);
				String string = getArrayString(bars,startIndex, index );
				subArrrays.add(string);
				if(isZeroAdded){
					subArrrays.add("0,"+string);
				}
			} else {
				hashTable.put(sum, index);
			}
			
			index++;

		}
		if(sum==0) subArrrays.add(getArrayString(bars, 0, bars.length));
		String[] returnedArray=new String[subArrrays.size()];
		return subArrrays.toArray(returnedArray);
	}

	private static String getArrayString(int[] bars,int startIndex, int endindex ) {
		StringBuilder sb = new StringBuilder();
		/**
		 * Traverse the list and print them all ( it take O(n2) to print
		 * all the subarrays
		 */
		
		for (int k = startIndex; k <= endindex - 1; k++) {
			if (sb.length() != 0) {
				sb.append(",");
			}
			sb.append(bars[k]);

		}
		String string = sb.toString();
		return string;
	}

}
