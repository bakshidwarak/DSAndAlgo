package trees.assignment.connectsibling;

public class ConnectSibling {
	
	static class TreeNode {
		int data;
		TreeNode right;
		TreeNode left;
		TreeNode nextSibling;
		public TreeNode(int data) {
			super();
			this.data = data;
		}
		
		
	}

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
		
		connectSiblings(tree5);
		
		printTreeBySibling(tree5);
	}

	private static void printTreeBySibling(TreeNode tree5) {
		if(tree5==null) return;
		TreeNode temp=tree5;
		
		while(temp!=null){
			System.out.print(temp.data+" ");
			temp=temp.nextSibling;
		}
		System.out.println();
		printTreeBySibling(tree5.left);
	}

	private static void connectSiblings(TreeNode root) {
		if(root==null) return;
		
		if(root.left==null || root.right==null) return;
		
		TreeNode prevRight = null;
		TreeNode curr=root;
		while(curr!=null){
			if(prevRight!=null){
				prevRight.nextSibling=curr.left;
			}
			curr.left.nextSibling=curr.right;
			prevRight=curr.right;
			curr=curr.nextSibling;
		}
		connectSiblings(root.left);
	}

}
