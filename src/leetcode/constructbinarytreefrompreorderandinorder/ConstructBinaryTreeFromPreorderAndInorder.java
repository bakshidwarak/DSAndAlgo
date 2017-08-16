package leetcode.constructbinarytreefrompreorderandinorder;

import java.util.HashMap;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * @author dwaraknathbs
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorder {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {

		HashMap<Integer, Integer> inorderMap = new HashMap<>();

		for (int i = 0; i < inorder.length; i++) {
			inorderMap.put(inorder[i], i);
		}

		return buildTreeHelper(preorder, inorder, 0, 0, preorder.length - 1, inorderMap);

	}

	/**
	 * Basic idea: Preorder can be used to identify the root node
	 * 
	 * Get the corresponding index from inorder node, the left will be from the
	 * start of inorder to the index-1, the right will be from inorderIndex+1
	 * till the end
	 * 
	 * Getting the corresponding prorderIndex for the right node is tricky. It
	 * can be determined by calculating the length of the left split in the
	 * inorder array and add the same length from the preorder index to get the
	 * root of the right child
	 * 
	 * 
	 * @param preorder
	 * @param inorder
	 * @param preorderIndex
	 * @param inorderStart
	 * @param inorderEnd
	 * @param inorderMap
	 * @return
	 */
	public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preorderIndex, int inorderStart, int inorderEnd,
			HashMap<Integer, Integer> inorderMap) {

		if (preorderIndex >= preorder.length || inorderStart > inorderEnd)
			return null;

		TreeNode root = new TreeNode(preorder[preorderIndex]);
		int inorderIndexRoot = inorderMap.get(preorder[preorderIndex]);
		int rightRootIndex = inorderIndexRoot - 1 - inorderStart + 2 + preorderIndex;
		root.left = buildTreeHelper(preorder, inorder, preorderIndex + 1, inorderStart, inorderIndexRoot - 1,
				inorderMap);

		root.right = buildTreeHelper(preorder, inorder, rightRootIndex, inorderIndexRoot + 1, inorderEnd, inorderMap);

		return root;
	}

}
