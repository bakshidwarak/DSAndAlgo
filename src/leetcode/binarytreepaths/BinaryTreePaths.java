

package leetcode.binarytreepaths;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * 257. Binary Tree Paths Given a binary tree, return all root-to-leaf paths.
 * For example, given the following binary tree:
 * 
 * <pre>
   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
 * </pre>
 * 
 * @author dwaraknathbs
 */
public class BinaryTreePaths {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        Stack<TreeNode> currentPath = new Stack<>();
        binaryTreePaths(root, paths, currentPath);
        return paths;
    }

    public void binaryTreePaths(TreeNode node, List<String> paths, Stack<TreeNode> currentPath) {
        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            currentPath.push(node);
            ArrayList<TreeNode> path = new ArrayList<>(currentPath);
            StringBuilder sb = new StringBuilder();
            for (TreeNode str : path) {
                if (sb.length() != 0)
                    sb.append("->");
                sb.append(str.val);
            }
            paths.add(sb.toString());
            currentPath.pop();
            return;
        }
        currentPath.push(node);
        binaryTreePaths(node.left, paths, currentPath);
        binaryTreePaths(node.right, paths, currentPath);
        currentPath.pop();
        return;
    }

}
