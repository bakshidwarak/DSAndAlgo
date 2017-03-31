package leetcode.largestvalueintreelevel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example: Input:
 * 
 * <pre>

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
 * </pre>
 * 
 * @author dwaraknathbs
 *
 */
public class FindLargestValueInEachTreeRow {
	
	
	  static class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
	
	 static class Pair{
	        TreeNode node;
	        int order;
	        public Pair(TreeNode node,int order){
	            this.node=node;
	            this.order=order;
	        }
	    }
	    public static List<Integer> largestValues(TreeNode root) {
	        
	        List<Integer> maxList= new ArrayList<>();
	        
	        Queue<Pair> queue= new LinkedList<Pair>();
	        queue.add(new Pair(root,0));
	        
	        while(!queue.isEmpty()){
	            Pair p=queue.remove();
	            
	            if(p.order<maxList.size()){
	                int currMax=maxList.get(p.order);
	                currMax=Math.max(currMax,p.node.val);
	                maxList.set(p.order,currMax);
	            }
	            else
	            {
	                maxList.add(p.node.val);
	            }
	            if(p.node.left!=null)
	            queue.add(new Pair(p.node.left,p.order+1));
	            
	            if(p.node.right!=null)
	            queue.add(new Pair(p.node.right,p.order+1));
	        }
	        
	        return maxList;
	        
	    }
	public static void main(String[] args) {
		TreeNode root= new TreeNode(1);
		TreeNode left= new TreeNode(3);
		TreeNode right= new TreeNode(2);
		
		TreeNode root1= new TreeNode(5);
		TreeNode left1= new TreeNode(3);
		TreeNode right1= new TreeNode(9);
		
		left.left=root1;
		left.right=left1;
		right.right =right1;
		root.left=left;
		root.right=right;
		
		largestValues(root).forEach(System.out::println);
		

	}

}
