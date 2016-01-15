package programs.linkedListProg;

public class LinkedList {
	Node head;

	public LinkedList() {
		super();
	}

	void add(Node n) {
		if (head == null) {
			head = n;

		} else {
			Node currNode = head;
			while (currNode.next != null) {
				currNode = currNode.next;
			}
			currNode.next = n;
			// n.next = null; optional to prevent data manipulation
		}
	}

	void print() {
		if (head == null) {
			System.out.println("Empty");
		} else {
			Node currNode = head;
			while (currNode.next != null) {
				System.out.print(currNode.data + "-->");
				currNode = currNode.next;
			}
			System.out.println(currNode.data);
		}
	}

	Node recReverse(Node n) {
		if (n.next == null) {
			head = n;
			return n;
		}
		Node reversedSoFar = recReverse(n.next);
		reversedSoFar.next = n;
		n.next = null;
		return n;
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.print();
		l.add(new Node(5));
		l.print();
		l.add(new Node(10));
		l.add(new Node(3));
		l.add(new Node(7));
		l.add(new Node(8));
		l.print();
		l.recReverse(l.head);
		l.print();
	}
}
