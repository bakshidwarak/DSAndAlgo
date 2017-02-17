package trees.assignment.bstiterator;

import java.util.Stack;

/**
 * Tree Iterator! Implement an iterator over a binary search tree (BST). Your
 * iterator will be initialized with the root node of a BST.
 * 
 * 1. Calling next() will return the next smallest number in the BST. 2. Calling
 * hasNext() should return whether the next element exists.
 * 
 * Both functions should run in average O(1) time and uses O(h) memory, where h
 * is the height of the tree.
 * 
 * [Iterator is a concept in higher level languages like C++ or Java. You
 * probably can tell what it is. If you want to know more, please feel free to
 * Google for the concept.]
 * 
 * Solutions: 1. With parent pointer:
 * http://stackoverflow.com/questions/12850889/in-order-iterator-for-binary-tree
 * 2. Without parent pointer, but with stack:
 * https://leetcode.com/discuss/20001/my-solutions-in-3-languages-with-stack
 * 
 * Choice of the solution will depend on what the interviewer asks you to do. #2
 * is generally preferred i.e. without assuming there is a parent pointer.
 * 
 * @author dwaraknathbs
 *
 */
public class BSTIterator {
	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1);
		TreeNode tree2 = new TreeNode(2);

		TreeNode tree3 = new TreeNode(3);
		tree2.left = tree1;
		tree2.right = tree3;
		TreeNode tree4 = new TreeNode(4);
		tree3.right = tree4;
		TreeNode tree5 = new TreeNode(5);
		tree5.left = tree2;
		TreeNode tree6 = new TreeNode(6);
		TreeNode tree7 = new TreeNode(7);
		TreeNode tree8 = new TreeNode(8);
		tree5.right = tree8;
		tree8.left = tree6;
		tree6.right = tree7;
		TreeNode tree9 = new TreeNode(9);
		tree8.right = tree9;

		BSTIterator iterator = new BSTIterator(tree5);
		while (iterator.hasNext()) {
			System.out.println("Next min is " + iterator.next());
		}

	}

	Stack<TreeNode> stack = new Stack<>();

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	/**
	 * We could just do an inorder traversal and push the entire tree to the
	 * stack. This is where the constraint of O(h) comes into picture. Inorder
	 * to use only O(h) we traverse the left of the tree and populate the stack.
	 * As and when we get the next we pop out and add the right node to the
	 * stack
	 * 
	 * @param root
	 */
	public BSTIterator(TreeNode root) {
		push(root);
	}

	private void push(TreeNode root) {
		if (root == null)
			return;

		/**
		 * Initially traverse to the left of the tree and populate the stack
		 */
		stack.push(root);
		push(root.left);

	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		System.out.println("Stack size before popping" + stack.size());
		/**
		 * Top of the stack gives me the current min element
		 */
		TreeNode node = stack.pop();
		/**
		 * Once popping, push the right node into the stack
		 */
		push(node.right);
		System.out.println("Stack size" + stack.size());
		return node.val;
	}
}

/**
 * Your BSTIterator will be called like this: BSTIterator i = new
 * BSTIterator(root); while (i.hasNext()) v[f()] = i.next();
 */
