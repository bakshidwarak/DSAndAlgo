package trees.assignment.printallpaths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PrintAllPathsFromRootToLeaf {
	static class Node {
		int val;
		Node left;
		Node right;

		public Node(int value) {
			this.val = value;
		}
	}

	static Node createTree(String data) {
		if (data == null || data.length() == 0)
			return null;
		StringTokenizer st = new StringTokenizer(data, " ");
		return deserialize(st);
	}

	static Node deserialize(StringTokenizer st) {
		if (!st.hasMoreTokens())
			return null;
		String s = st.nextToken();
		if (s.equals("#"))
			return null;
		Node root = new Node(Integer.valueOf(s));
		root.left = deserialize(st);
		root.right = deserialize(st);

		return root;
	}

	static void printInorder(Node root) {
		if (root == null)
			return;
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}

	static void printAllPaths(Node root) {

		List<Integer> path = new ArrayList<>();

		printPaths(root, path, 0);
		// paths.stream().forEach(System.out::println);
		// for(String str:paths){
		// System.out.println(str);
		// }

	}

	private static void printPaths(Node root, List<Integer> pathList, int pathLen) {
		if (root == null) {

			return;
		}
		pathList.add(pathLen, root.val);
		pathLen++;
		if (root.left == null && root.right == null) {
			printPath(pathList, pathLen);
			return;
		}

		printPaths(root.left, pathList, pathLen);
		printPaths(root.right, pathList, pathLen);
	}

	private static void printPath(List<Integer> pathList, int pathLen) {
		for (int i = 0; i < pathLen; i++) {
			System.out.print(pathList.get(i) + " ");
		}
		System.out.println();

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int _size;
		_size = Integer.parseInt(in.nextLine().trim());

		String _str;
		try {
			_str = in.nextLine();
		} catch (Exception e) {
			_str = null;
		}
		Node n = createTree(_str);
		printAllPaths(n);

	}
}
