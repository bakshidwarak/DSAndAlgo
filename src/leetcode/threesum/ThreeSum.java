package leetcode.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {
	
	 static class Pair{
	        
	        int num1;
	        int num2;
	        int num3;
	        
	        public Pair(int num1, int num2, int num3){
	            this.num1=num1;
	            this.num2=num2;
	            this.num3=num3;
	        }
	          @Override
	        public int hashCode() {
	            final int prime = 31;
	            int result = 1;
	            result = prime * result + num1;
	            result = prime * result + num2;
	            result = prime * result + num3;
	            return result;
	        }

	        @Override
	        public boolean equals(Object obj) {
	            if (this == obj)
	                return true;
	            if (obj == null)
	                return false;
	            if (getClass() != obj.getClass())
	                return false;
	            Pair other = (Pair) obj;
	            if (num1 != other.num1)
	                return false;
	            if (num2 != other.num2)
	                return false;
	            if (num3 != other.num3)
	                return false;
	            return true;
	        }
	    }
	    public List<List<Integer>> threeSum(int[] nums) {
	        List<List<Integer>> result= new ArrayList<>();
	        
	        HashSet<Pair> resultPairs= new HashSet<>();
	        if(nums.length<3) return result;
	        Arrays.sort(nums);
	        for(int i=0; i<nums.length; i++){
	            int j=i+1;
	            int l=nums.length-1;
	            while(j<l){
	                
	                if(nums[j]+nums[l]==-1*nums[i]){
	                    resultPairs.add(new Pair(nums[i],nums[j],nums[l]));
	                    j++;
	                    l--;
	                    continue;
	                }
	                else if(nums[j]+nums[l]<-1*nums[i]){
	                    j++;
	                }
	                else
	                {
	                    l--;
	                }
	            }
	        }
	        
	        for(Pair p : resultPairs){
	            result.add(getList(p.num1,p.num2,p.num3));
	        }
	        return result;
	    }
	    
	    public List<Integer> getList(int num1, int num2, int num3){
	        List<Integer> result= new ArrayList<>();
	        result.add(num1);
	        result.add(num2);
	        result.add(num3);
	        return result;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

   