package programs.trees;

//we will say a tree to be balanced if its left and right subtress have length difference upto 1
public class CheckBalance {
	public CheckBalance() {
		super();
	}

	public static void main(String[] args) {
		// CheckBalance checkBalance = new CheckBalance();
		BinaryTree.Node r = new BinaryTree.Node(2);

		BinaryTree tree = new BinaryTree(r);
		r.left = new BinaryTree.Node(7);
		r.left.left = new BinaryTree.Node(3);
		r.left.right = new BinaryTree.Node(6);
		r.left.right.left = new BinaryTree.Node(5);
		r.left.right.right = new BinaryTree.Node(11);
		r.right = new BinaryTree.Node(5);
		r.right.right = new BinaryTree.Node(9);
		// tree.printInOrder(r);
		// getHeight(tree.root);
		System.out.println("Getting LCA");
		System.out.println(tree.getLCAofElementsPresent(r, r.left, r.left.left));
		System.out.println(tree.getLCAofElementsPresent(r, r.left, r.left.right.right));
		System.out.println(tree.getLCAofElementsPresent(r, r.right, r.left.right.right));
		System.out.println(tree.getLCAofElementsUnknown(r, r, r.right));
	}

	// Simple idea would be to recursively calculate height of left and right at
	// each level via getHeight(node) method
	// But we will end up doing repeated work
	// so lets try to do the work in the getHeight method itself
	public static int getHeight(BinaryTree.Node r) {
		if (r == null)
			return 0; // parent doesn't have this left or right child
		// note: Returning 0 means that we are returning hieght +1
		int leftSubTreeHeight = getHeight(r.left);
		int rightSubTreeHeight = getHeight(r.right);
		if (Math.abs(leftSubTreeHeight - rightSubTreeHeight) > 1) {
			// tree is not balanced
			System.out.println("Tree is not balanced");
		}
		return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
	}

	public static int LCA(BinaryTree.Node root, BinaryTree.Node a, BinaryTree.Node b) {
		return 0;
	}

}
