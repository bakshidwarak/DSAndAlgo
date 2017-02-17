package trees.assignment.rebuildatree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Rebuild the tree Given the in-order and pre-order traversing results of a
 * binary tree (as arrays), write a function to rebuild the tree. The function
 * should return the pointer to the root node of the tree. Then take that
 * pointer, and print your tree level by level (level order).
 * 
 * Trivia: Generally speaking, one needs to be given in-order traversal (with
 * either pre or post or level), as input, in order to re-construct a binary
 * tree. Without in-order traversal given, it's not possible to re-construct a
 * binary tree without ambiguity, even if all other 3 traversal orders are
 * given. The only exception, is if we know something more about the tree e.g.
 * if the binary tree is full and complete, then we can resolve the ambiguity
 * without having to know the in-order traversal. [Something to read:
 * http://www.geeksforgeeks.org/if-you-are-given-two-traversal-sequences-can-you-construct-the-binary-tree/]
 * 
 * Solutions:
 * http://articles.leetcode.com/2011/04/construct-binary-tree-from-inorder-and-preorder-postorder-traversal.html
 * http://edwardliwashu.blogspot.com/2013/01/construct-binary-tree-from-preorder-and.html
 * https://www.youtube.com/watch?v=PAYG5WEC1Gs
 * 
 * @author dwaraknathbs
 *
 */
public class RebuildBinaryTreeFromTraversals {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int _iInOrderArray_size = 0;
		_iInOrderArray_size = Integer.parseInt(in.nextLine().trim());
		int[] _iInOrderArray = new int[_iInOrderArray_size];
		int _iInOrderArray_item;
		for (int _iInOrderArray_i = 0; _iInOrderArray_i < _iInOrderArray_size; _iInOrderArray_i++) {
			_iInOrderArray_item = Integer.parseInt(in.nextLine().trim());
			_iInOrderArray[_iInOrderArray_i] = _iInOrderArray_item;
		}

		int _iPreOrderArray_size = 0;
		_iPreOrderArray_size = Integer.parseInt(in.nextLine().trim());
		int[] _iPreOrderArray = new int[_iPreOrderArray_size];
		int _iPreOrderArray_item;
		for (int _iPreOrderArray_i = 0; _iPreOrderArray_i < _iPreOrderArray_size; _iPreOrderArray_i++) {
			_iPreOrderArray_item = Integer.parseInt(in.nextLine().trim());
			_iPreOrderArray[_iPreOrderArray_i] = _iPreOrderArray_item;
		}

		constrctTree(_iInOrderArray, _iPreOrderArray);

	}

	static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;

		public TreeNode(int data) {
			super();
			this.data = data;
		}

	}

	private static void constrctTree(int[] inorderArray, int[] preOrderArray) {
		int inorderStart = 0;
		int inorderEnd = inorderArray.length;
		int preOrderStart = 0;
		int preOrderEnd = preOrderArray.length - 1;

		TreeNode tree = getTree(inorderArray, preOrderArray, inorderStart, inorderEnd, preOrderStart, preOrderEnd);
		printLevelOrder(tree);

	}

	/**
	 * 
	 * The element in preOrder array gives the root of the node. Find that
	 * element in the inorder array, all elements to the left of the index in
	 * inorder array forms the left subtree and the rest forms the right subtree
	 * 
	 * @param inorderArray
	 * @param preOrderArray
	 * @param inorderStart
	 * @param inorderEnd
	 * @param preOrderStart
	 * @param preOrderEnd
	 * @return
	 */
	private static TreeNode getTree(int[] inorderArray, int[] preOrderArray, int inorderStart, int inorderEnd,
			int preOrderStart, int preOrderEnd) {
		if (preOrderStart > preOrderEnd)
			return null;
		int data = preOrderArray[preOrderStart];
		TreeNode node = new TreeNode(data);
		for (int i = inorderStart; i < inorderEnd; i++) {
			if (data == inorderArray[i]) {
				/**
				 * Its key to calculate the length
				 */
				int len = i - inorderStart;
				node.left = getTree(inorderArray, preOrderArray, inorderStart, i + 1, preOrderStart + 1,
						preOrderStart + len);
				node.right = getTree(inorderArray, preOrderArray, i + 1, inorderEnd, preOrderStart + len + 1,
						preOrderEnd);
				break;
			}
		}
		return node;

	}

	private static void printLevelOrder(TreeNode tree) {
		Queue<Pair> queue = new LinkedList<Pair>();
		HashMap<Integer, List<TreeNode>> map = new HashMap<>();
		queue.add(new Pair(tree, 1));
		while (!queue.isEmpty()) {
			Pair nodePair = queue.remove();
			if (nodePair != null) {
				TreeNode currentNode = nodePair.node;
				int currentLevel = nodePair.distance;
				if (map.containsKey(currentLevel)) {
					map.get(currentLevel).add(currentNode);
				} else {
					List<TreeNode> treeList = new ArrayList<>();
					treeList.add(currentNode);
					map.put(currentLevel, treeList);
				}

				if (currentNode != null) {
					queue.add(new Pair(currentNode.left, currentLevel + 1));
					queue.add(new Pair(currentNode.right, currentLevel + 1));
				}
			}
		}
		for (int i = 1; i <= map.size(); i++) {
			List<TreeNode> nodesAtLevel = map.get(i);
			for (int k = 0; k < nodesAtLevel.size(); k++) {
				TreeNode treeNode = nodesAtLevel.get(k);
				if (treeNode != null)
					System.out.print(treeNode.data + " ");
			}

			System.out.println();
		}

	}

	static class Pair {
		TreeNode node;
		int distance;

		public Pair(TreeNode node, int level) {
			super();
			this.node = node;
			this.distance = level;
		}

	}
}
