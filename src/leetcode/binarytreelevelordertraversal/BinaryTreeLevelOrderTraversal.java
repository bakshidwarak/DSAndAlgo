

package leetcode.binarytreelevelordertraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal Given a binary tree, return the level
 * order traversal of its nodes' values. (ie, from left to right, level by
 * level).
 * 
 * <pre>
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 * </pre>
 * 
 * @author dwaraknathbs
 */
public class BinaryTreeLevelOrderTraversal {
    
      static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
     

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    static class Pair{
        TreeNode node;
        int level;
        public Pair(TreeNode node,int level){
            this.node=node;
            this.level=level;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> order= new ArrayList<>();
        if(root==null) return order;
        Queue<Pair> queue= new LinkedList<Pair>();
        queue.add(new Pair(root,0));
        while(!queue.isEmpty()){
            Pair p=queue.remove();
            int level=p.level;
            if(order.size()<=level){
                List<Integer> elements= new ArrayList<>();
                elements.add(p.node.val);
                order.add(elements);
            }else {
                List<Integer> elements= order.get(p.level);
                 elements.add(p.node.val);
                 order.set(p.level,elements);
            }
            if(p.node.left!=null){
                queue.add(new Pair(p.node.left,level+1));
            }
            if(p.node.right!=null){
                queue.add(new Pair(p.node.right,level+1));
            }
            
        }
    return order;
    }

}
