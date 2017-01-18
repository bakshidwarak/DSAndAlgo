package trees.assignment.mergebst;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class MergeBinarySearchTrees {




    static class Node {
        int val;
        Node left;
        Node right;
        public Node(int value) {
            this.val = value;
        }
    }
    static class LinkedList{
    	int val;
    	LinkedList next;
		public LinkedList(int val) {
			super();
			this.val = val;
		}
    	
    }

    static Node createTree(String data) {
        if (data == null || data.length() == 0) return null;
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
    	if(root == null) return;
    	printInorder(root.left);
    	System.out.print(root.val+" ");
    	printInorder(root.right);
    }
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        
        int _size1;
        _size1 = Integer.parseInt(in.nextLine().trim());
        
        String _str1;
        try {
            _str1 = in.nextLine();
        } catch (Exception e) {
            _str1 = null;
        }
        Node n1 = createTree(_str1);
        
        int _size2;
        _size2 = Integer.parseInt(in.nextLine().trim());
        
        String _str2;
        try {
            _str2 = in.nextLine();
        } catch (Exception e) {
            _str2 = null;
        }
        Node n2 = createTree(_str2);
        Node res = mergeTrees(n1, n2);
        printInorder(res);

        }
    
    private static void inorderArray(Node n,ArrayList<Integer> list){
    	
    	if(n==null) return ;
    	
    	inorderArray(n.left, list);
    		
    		list.add(n.val);
    		inorderArray(n.right, list);
    	
    }

	private static Node mergeTrees(Node n1, Node n2) {
		ArrayList<Integer> l1=new ArrayList<>();
		inorderArray(n1,l1);
		ArrayList<Integer> l2=new ArrayList<>();
		inorderArray(n2,l2);
		int[] sortedArray = getSortedArray(l1, l2);
		
		Node mergedTree=reconstruct(sortedArray,0,sortedArray.length-1);
		return mergedTree;
	}

	private static Node reconstruct(int[] sortedArray, int low, int high) {
		if(low>high) return null;
		int mid=(high+low)/2;
		int value=sortedArray[mid];
		Node node= new Node(value);
		node.left=reconstruct(sortedArray, low, mid-1);
		node.right=reconstruct(sortedArray, mid+1, high);
		return node;
	}

	private static int[] getSortedArray(ArrayList<Integer> l1, ArrayList<Integer> l2) {
		int k=0;
		int countN1 = l1.size();
		int countN2=l2.size();;
		int[] sortedArray=new int[countN1+countN2];
		int i=0;
		int j=0;
		while(i<countN1 && j<countN2){
			if(l1.get(i)<=l2.get(j)){
				sortedArray[k++]=l1.get(i++);
				
				
			}
			else if(l2.get(j)<l1.get(i)){
				sortedArray[k++]=l2.get(j++);
				
			}
		}
		while(i<countN1){
			sortedArray[k++]=l1.get(i++);;
			
		}
		while(j<countN2){
			sortedArray[k++]=l2.get(j++);
			
		}
		return sortedArray;
	}

	private static int countElements(LinkedList l1) {
		LinkedList curr=l1;
		int countN1=0;
		while(curr!=null){
			countN1+=1;
			curr=curr.next;
		}
		return countN1;
	}
}
