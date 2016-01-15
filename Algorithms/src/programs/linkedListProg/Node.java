package programs.linkedListProg;

public class Node {
	int data;
	Node next;

	public Node(int data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object n) {
		if (n instanceof Node) {
			Node n1 = (Node) n;
			if (data == n1.data)
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return data;
	}
}
