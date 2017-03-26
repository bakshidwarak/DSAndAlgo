package recursion.assignment.treediameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Diameter of a tree Calculate the diameter of a tree (not necessarily a binary
 * tree). Diameter is the longest path between two leaves of a tree. A path is
 * the sum total of all distances (weights) attached to all edges between two
 * nodes.
 * 
 * In the examples below, a tree is represented in a specific notation.
 * 
 * e.g. "{0,1,{5,0}}" means: it starts with root (0), which has one (1) child,
 * which will follow in braces. Inside the braces, it says that the distance
 * (weight) of reaching that first child is 5 and that there are no more
 * children after that (0)
 * 
 * You can represent the tree however you like. This is just one convenient way.
 * Other example test-cases:
 * 
 * // One node - no diameter new TestCase("{0,0}", 0),
 * 
 * // One leaf new TestCase("{0,1,{5,0}}", 5),
 * 
 * // Still one leaf new TestCase
 * 
 * ("{0,1,{5,1,{4,1,{7,0}}}}", 16),
 * 
 * // The diameter of the first son is the diameter of the tree
 * TestCase("{0,1,{5,2,{8,0},{7,0}}}", 15),
 * 
 * // The diameter of the last son is the diameter of the tree new
 * TestCase("{0,3,{1,2,{5,0},{7,0}},{1,2,{6,0},{5,0}},{1,2,{10,0},{9,0}}}", 19),
 * 
 * 
 * // Now the diameter is between a leaf in the first son and a leaf in the
 * third son new
 * TestCase("{0,3,{5,2,{8,0},{7,0}},{5,2,{9,0},{8,0}},{5,2,{10,0},{9,0}}}", 29)
 * 
 * 
 * Test-cases: Please hard-code inputs and present output to STDOUT. Ignore the
 * failing dummy test-case.
 * 
 * Solution: http://techieme.in/tree-diameter/ OR
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * 
 * (Suggested interview time, 45 minutes)
 * 
 * 
 * @author dwaraknathbs
 *
 */
public class TreeDiameterNAryTree {

	static class Node {
		int weight;
		List<Node> children = new ArrayList<>();

	}

	static class TreeDiameterAndPath {
		int diameter;
		int path;

		public TreeDiameterAndPath(int height, int path) {
			super();
			this.diameter = height;
			this.path = path;
		}

	}

	public static void main(String[] args) {

		Node test0 = buildTreeFromString("{0,0}");
		// One leaf
		Node test1 = buildTreeFromString("{0,1,{5,0}}");// 5
		// Still one leaf
		Node test2 = buildTreeFromString("{0,1,{5,1,{4,1,{7,0}}}}");// , 16
		// The diameter of the first son is the diameter of the tree
		Node test3 = buildTreeFromString("{0,1,{5,2,{8,0},{7,0}}}");// , 15
		// The diameter of the last son is the diameter of the tree
		Node test4 = buildTreeFromString("{0,3,{1,2,{5,0},{7,0}},{1,2,{6,0},{5,0}},{1,2,{10,0},{9,0}}}");// ,
																											// 19
		// Now the diameter is between a leaf in the first son and a leaf in the
		// third son
		Node test5 = buildTreeFromString("{0,3,{5,2,{8,0},{7,0}},{5,2,{9,0},{8,0}},{5,2,{10,0},{9,0}}}");// ,
																											// 29

		System.out.println(treeDiameter(test0));
		System.out.println(treeDiameter(test1));
		System.out.println(treeDiameter(test2));
		System.out.println(treeDiameter(test3));
		System.out.println(treeDiameter(test4));
		System.out.println(treeDiameter(test5));

	}

	static int start = 0;

	private static Node buildTreeFromString(String treeString) {
		start = 0;
		return buildTree(treeString.toCharArray());
	}

	private static Node buildTree(char[] charArray) {
		start++;// Handling opening curly braces
		Node n = new Node();
		int weight = 0;
		while (Character.isDigit(charArray[start])) {
			weight *= 10;
			weight += Character.getNumericValue(charArray[start]);
			;
			start++;
		}
		start++;// Handling comma
		n.weight = weight;
		int numberOfChildren = 0;
		while (Character.isDigit(charArray[start])) {
			numberOfChildren *= 10;
			numberOfChildren += Character.getNumericValue(charArray[start]);
			;
			start++;
		}
		start++;// Handling comma
		while (numberOfChildren > 0) {
			n.children.add(buildTree(charArray));
			numberOfChildren--;
			start++;
			;
		}

		return n;

	}

	static int treeDiameter(Node root) {

		TreeDiameterAndPath diameterAndPath = getTreeDiameterAndPath(root);
		return diameterAndPath.diameter;
	}

	/**
	 * The contract of the recursion is, every node returns the diameter and the
	 * max value of path from leaf node to itself
	 * 
	 * @param root
	 * @return
	 */
	private static TreeDiameterAndPath getTreeDiameterAndPath(Node root) {
		if (root == null)
			return new TreeDiameterAndPath(0, 0);

		System.out.println("Processing " + root.weight);

		int diameterToReturn = 0;
		int path = 0;

		int distanceFromTheLongestNode = 0, distanceFromTheSecondLongestNode = 0;
		int maxDiameter = 0;
		if (!root.children.isEmpty()) {
			for (Node n : root.children) {
				TreeDiameterAndPath treeDiameterAndPath = getTreeDiameterAndPath(n);
				int currentDia = treeDiameterAndPath.diameter;
				int currentPath = treeDiameterAndPath.path;

				if (currentPath > distanceFromTheLongestNode) {
					distanceFromTheSecondLongestNode = distanceFromTheLongestNode;
					distanceFromTheLongestNode = currentPath;
				} else if (currentPath > distanceFromTheSecondLongestNode) {
					distanceFromTheSecondLongestNode = currentPath;
				}

				if (currentDia > maxDiameter) {
					maxDiameter = currentDia;

				}
			}

			path = distanceFromTheLongestNode + distanceFromTheSecondLongestNode;

			diameterToReturn = Math.max(maxDiameter, path);
		}

		int pathToNode = distanceFromTheLongestNode + root.weight;
		System.out.println("Returning " + diameterToReturn + "," + pathToNode);
		return new TreeDiameterAndPath(diameterToReturn, pathToNode);
	}

}
