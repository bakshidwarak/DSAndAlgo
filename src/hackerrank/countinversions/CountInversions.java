package hackerrank.countinversions;

import java.util.Scanner;
/**
 * In an array, , the elements at indices  and  (where ) form an inversion if . In other words, inverted elements  and  are considered to be "out of order". To correct an inversion, we can swap adjacent elements.

For example, consider . It has two inversions:  and . To sort the array, we must perform the following two swaps to correct the inversions:

Given  datasets, print the number of inversions that must be swapped to sort each dataset on a new line.

Input Format

The first line contains an integer, , denoting the number of datasets. 
The  subsequent lines describe each respective dataset over two lines:

The first line contains an integer, , denoting the number of elements in the dataset.
The second line contains  space-separated integers describing the respective values of .
Constraints

Output Format

For each of the  datasets, print the number of inversions that must be swapped to sort the dataset on a new line.

Sample Input

2  
5  
1 1 1 2 2  
5  
2 1 3 1 2
Sample Output

0  
4   
Explanation

We sort the following  datasets:

 is already sorted, so there are no inversions for us to correct. Thus, we print  on a new line.
 
As we performed a total of  swaps to correct inversions, we print  on a new line.
 * @author dwaraknathbs
 *
 */
public class CountInversions {

    public static long countInversions(int[] arr){
    
        
       long[] inversions= new long[1];
       int[] sorted= countInversionHelper(arr,0,arr.length-1,inversions);
       
        return inversions[0];
        
    }
    
    public static int[] countInversionHelper(int[] arr, int start, int end,long[] inversions){
        
        if(start==end){
            int[] result = new int[1];
            result[0] = arr[start];
            return result;
        }
       
        int mid=(start+end)/2;
        int[] left=countInversionHelper(arr,start,mid,inversions);
        int[] right=countInversionHelper(arr,mid+1,end,inversions);
        int[] sorted=sweepInversions(left,right,inversions);
        return sorted;
    }
    
    public static int[] sweepInversions(int[] left, int[] right,long[] inversions){
       int[] sorted= new int[left.length+right.length];
        
        int i=0;
        int j=0;
        int k=0;
        for(; k<sorted.length;){
           while(i<left.length && j<right.length){
               if(left[i]<=right[j]){
                  
                   sorted[k++]=left[i];
                   i++;
               }
               else {
                    /**
                     * If left was greater than right, then as left and right
                     * are sorted arrays all the elements in the left will have
                     * to be swapped. Hence total number of inversions is the
                     * total length of left - the current elements index
                     */
                    inversions[0]+=left.length-i;
                   sorted[k++]=right[j];
                   j++;
               }
           }
            
            while(i<left.length){
                sorted[k++]=left[i++];
            }
            while(j<right.length){
                sorted[k++]=right[j++];
            }
        }
        return sorted;
        
        
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int arr_i=0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            System.out.println(countInversions(arr));
        }
    }
    
    
}
