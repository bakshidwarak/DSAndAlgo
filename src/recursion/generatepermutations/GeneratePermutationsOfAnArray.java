package recursion.generatepermutations;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given an array print all its permutations
 * @author dwaraknathbs
 *
 */
public class GeneratePermutationsOfAnArray {

	public static void main(String[] args) {
		int arr[] ={1,2,3,4,5,6};
		printPermutations(arr,0);

	}

	private static void printPermutations(int[] arr, int s) {
		if(s==arr.length){
			System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
		}
		
		for(int k=s; k<arr.length; k++){
			swap(arr,k,s);
			printPermutations(arr,s+1);
			swap(arr,s,k);
		}
		
	}

	private static void swap(int[] arr, int s, int k) {
		int temp=arr[s];
		arr[s]=arr[k];
		arr[k]=temp;
		
	}

}
