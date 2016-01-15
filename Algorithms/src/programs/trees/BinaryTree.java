package programs.trees;

public class BinaryTree {
	Node root;

	static enum Child

	{
        LEFT,RIGHT;
    }

	private static class BoolWrap {
		boolean val = false;
	}

	static class Node {
		int num;
		Node parent;
		Node left;
		Node right;

		Node(int n) {
			num = n;
		}

		@Override
		public boolean equals(Object b) {
			if (!(b instanceof Node))
				return false;
			Node b1 = (Node) b;
			if (this.num == b1.num)
				return true;
			else
				return false;
		}

		@Override
		public String toString() {
			return new Integer(num).toString();
		}
	}

	public BinaryTree(Node root) {
		this.root = root;
	}

	public static void main(String[] args) {
		// BinaryTree binaryTree = new BinaryTree();
	}

	// add corresponding node n to parent Node
	void add(Node parent, Node n, Child c) {
		if (parent == null || n == null)
			throw new NullPointerException("Nodes can't be null");
		n.parent = parent;
		if (c == Child.LEFT) {
			parent.left = n;
		} else {
			parent.right = n;
		}

	}

	// root in order, left->root->right
	void printInOrder(Node r) {
		if (r == null) {
			return;
		}
		printInOrder(r.left);
		System.out.println(r.num);
		printInOrder(r.right);
	}

	// PreOrder = Root first, root->left->right
	void printPreOrder(Node r) {
		if (r == null) {
			return;
		}
		System.out.println(r.num);
		printPreOrder(r.left);
		printPreOrder(r.right);
	}

	// postOrder = Root last, left->right->root
	void printPostOrder(Node r) {
		if (r == null) {
			return;
		}
		System.out.println(r.num);
		printPostOrder(r.left);
		printPostOrder(r.right);
	}

	// Duplicate elemeents don't make any sense for LCA problem since in that
	// case
	// multiple ancestors will exist
	// This method returns the LCA or null if there is no LCA at each recursive
	// call.
	// However if both left and right are not null, then root is returned, so
	// kind of a hack
	public Node getLCAofElementsPresent(Node root, Node a, Node b) {
		if (root == null) {
			return null;
		}
		if (root.equals(a) || root.equals(b)) {
			return root; // let us assume both nodes exist in tree
		}
		Node left = getLCAofElementsPresent(root.left, a, b);
		Node right = getLCAofElementsPresent(root.right, a, b);

		// if both left and right are not null, that means one element exists in
		// left tree and other in right
		// BINGO!! root must be the LCA
		if (left != null && right != null)
			return root;

		// The case when both nodes are null is not applicable since we have
		// assumed that both nodes exist in the tree
		/*
		 * //Both searches return null, means we don't have the elements in our
		 * tree if (left == null && right == null) return null;
		 */

		// Code path comes here if either left or right is not null
		if (left != null) {
			// both nodes are in the left subtree & by recursion we have ensured
			// that algorithm has returned the LCA in the left subtree
			// we are now just propagating this value upwards.
			return left;
		} else {
			// both nodes are in the right subtree
			return right;
		}
	}

	public Node getLCAofElementsUnknown(Node root, Node a, Node b) {
		BoolWrap elemAexists = new BoolWrap();
		BoolWrap elemBexists = new BoolWrap();
		Node lca = getLCAofElementsUnknown(root, a, b, elemAexists, elemBexists);

		if (elemAexists.val == true && elemBexists.val == true) {
			return lca;
		} else {
			return null;
		}
	}

	public Node getLCAofElementsUnknown(Node root, Node a, Node b, BoolWrap x, BoolWrap y) {
		if (root == null) {
			return null; // tree is empty
		}
		// BASE CASE
		if (root.equals(a)) {
			x.val = true;
			return root;
		} else if (root.equals(b)) {
			y.val = true;
			return root;
		}
		Node left = getLCAofElementsUnknown(root.left, a, b, x, y);
		Node right = getLCAofElementsUnknown(root.right, a, b, x, y);

		if (left == null && right == null) // Both elements not present in the
											// sub-trees
			return null;
		if (left != null && right != null) {
			return root;
		}
		// Code path comes here if either left or right is not null
		if (left != null) {
			// both nodes are in the left subtree & by recursion we have ensured
			// that algorithm has returned the LCA in the left subtree
			// we are now just propagating this value upwards.
			return left;
		} else {
			// both nodes are in the right subtree
			return right;
		}
	}
}
