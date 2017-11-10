package leetcode.serializedeserializebinarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following tree
 * 
 * <pre>

    1
   / \
  2   3
     / \
    4   5
 * </pre>
 * 
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a
 * binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself. Note: Do not use
 * class member/global/static variables to store states. Your serialize and
 * deserialize algorithms should be stateless.
 * 
 * @author dwaraknathbs
 *
 */
public class SerializeDeserializeBTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null)
			return "null";

		ArrayList<String> serializedTree = new ArrayList<>();
		buildString(root, serializedTree);
		String serial = serializedTree.stream().collect(Collectors.joining(","));
		System.out.println(serial);
		return serializedTree.stream().collect(Collectors.joining(","));

	}

	public void buildString(TreeNode root, ArrayList<String> serializedTree) {
		if (root == null) {
			serializedTree.add("null");
			return;
		}
		serializedTree.add(Integer.toString(root.val));
		buildString(root.left, serializedTree);
		buildString(root.right, serializedTree);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {

		if (data == null || data.trim().equals("")) {
			return null;
		}

		String[] nodes = data.split(",");
		Queue<String> nodeQueue = new LinkedList<>();
		Arrays.stream(nodes).forEach(s -> nodeQueue.add(s));

		TreeNode head = constructTree(nodeQueue);
		return head;

	}

	public TreeNode constructTree(Queue<String> nodes) {
		if (nodes.isEmpty()) {
			return null;
		}
		String current = nodes.poll();
		if (current.equals("null"))
			return null;
		TreeNode head = new TreeNode(Integer.valueOf(current));
		head.left = constructTree(nodes);
		head.right = constructTree(nodes);

		return head;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
