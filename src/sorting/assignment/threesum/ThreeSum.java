package sorting.assignment.threesum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ThreeSum {
	 public static void main(String[] args) throws IOException{
	        Scanner in = new Scanner(System.in);
	           String[] res;
	        
	        int _intArr_size = 0;
	        _intArr_size = Integer.parseInt(in.nextLine().trim());
	        int[] _intArr = new int[_intArr_size];
	        int _intArr_item;
	        for(int _intArr_i = 0; _intArr_i < _intArr_size; _intArr_i++) {
	            _intArr_item = Integer.parseInt(in.nextLine().trim());
	            _intArr[_intArr_i] = _intArr_item;
	        }
	        
	        res = printTriplets(_intArr);
	        for(int res_i=0; res_i < res.length; res_i++) {
	           System.out.println(String.valueOf(res[res_i]));
	          
	        }
	        
	      
	    }

	private static String[] printTriplets(int[] _intArr) {
		Arrays.sort(_intArr);
		
		ArrayList<String> triplets= new ArrayList<>();
		
		int i=0;
		for(i=0; i<_intArr.length;i++ ){
			int k=_intArr.length-1;
			int j=i+1;
			while(j<k){
				if(_intArr[i]+_intArr[j]+_intArr[k]<0){
					j++;
				}
				else if(_intArr[i]+_intArr[j]+_intArr[k]>0){
					k--;
				}
				else
				{
					triplets.add(_intArr[j]+","+_intArr[k]+","+_intArr[i]);
					
					j++;
					k--;
					while(j<k && _intArr[j]==_intArr[j+1]) j++;
					while(j<k && _intArr[k]==_intArr[k-1]) k--;
					
				}
			}
		}
		String[] result= new String[triplets.size()];
		return triplets.toArray(result);
	}
}
