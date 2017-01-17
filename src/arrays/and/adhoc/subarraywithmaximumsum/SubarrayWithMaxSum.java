package arrays.and.adhoc.subarraywithmaximumsum;

import java.util.Scanner;

public class SubarrayWithMaxSum {

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
		
		System.out.println(getMaxSum(bars));

	}

	private static int getMaxSum(int[] array) {
		int cumulativeSum=0;
		int maxSum=0;
		for(int i=0; i<array.length; i++){
			cumulativeSum+=array[i];
			maxSum=Math.max(maxSum, cumulativeSum);
			if(cumulativeSum<0){
				
				
				cumulativeSum=0;
			}
			
		}
		return maxSum;
	}

}
