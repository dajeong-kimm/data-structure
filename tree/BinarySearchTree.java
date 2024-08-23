/**
 * 이진탐색트리 : 정렬된 데이터를 빠르게 탐색하기 위한 자료구조
 * @author SSAFY
 *
 */
public class BinarySearchTree {
	public class TreeNode {
		int data;
		TreeNode left, right;
		
		public TreeNode(int item) {
			data = item;
			left = right = null;
		}
	}
	
	TreeNode root;
	
	BinarySearchTree() {
		root = null;
	}
	
	void insert(int data) {
		root = insertRec(root, data);
	}
	
	TreeNode insertRec(TreeNode root, int data) {
		if (root == null) {
			root = new TreeNode(data);
			return root;
		}
		
		if (data < root.data) {
			root.left = insertRec(root.left, data);
		} else if (data > root.data) {
			root.right = insertRec(root.right, data);
		}
		return root;
	}
	
	void inorder() {
		inorderRec(root);
	}
	
	void inorderRec(TreeNode root) {
		if (root != null) {
			inorderRec(root.left);
			System.out.print(root.data + " ");
			inorderRec(root.right);
		}
	}
	
	boolean search(int data) {
		return searchRec(root, data);
	}
	
	boolean searchRec(TreeNode root, int data) {
		if (root == null) {
			return false;
		}
		
		if (root.data == data) return true;
		
		return data < root.data ? searchRec(root.left, data) : searchRec(root.right, data);
	}
}
