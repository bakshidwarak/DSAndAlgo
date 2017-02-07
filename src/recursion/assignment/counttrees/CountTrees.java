package recursion.assignment.counttrees;

import java.io.IOException;
import java.util.Scanner;

public class CountTrees {
	public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
       
        int res;
        int _iNodeCount;
        _iNodeCount = Integer.parseInt(in.nextLine().trim());
        
        res = countTrees(_iNodeCount);
       System.out.println(String.valueOf(res));
       
    }

	private static int countTrees(int _iNodeCount) {
		if(_iNodeCount==0){
			return 1;
		}

		if(  _iNodeCount==1)
		{
			return 1;
		}
		int sum=0;int left=0; int right=0;
		for(int k=1; k<=_iNodeCount; k++){
			left=countTrees(_iNodeCount-k);
			right=countTrees(k-1);
			sum=sum+(left*right);
		}
		return sum;
	}
}
