package programs.stackProg;

public class StackArray {
	private int[] elem;
	private int size;
	private int current;

	public StackArray() {
		this(10);
	}

	public StackArray(int n) {
		elem = new int[n];
		size = n; // size can be replaced with elem.length in the program
		current = 0; // we can also choose to initialize current with -1
	}

	public void push(int e) {
		if (current > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		elem[current++] = e;
	}

	public int pop() {
		if (current <= 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int temp = elem[current - 1];
		elem[current - 1] = 0;
		current--;
		return temp;
	}

	public int top() {
		if (current <= 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return elem[current - 1];
	}

	public boolean isEmpty() {
		if (current <= 0)
			return true;
		else
			return false;
	}

	public void print() {
		if (current <= 0) {
			System.out.println("empty");
		} else {
			for (int i = current - 1; i >= 0; i--) {
				System.out.println(elem[i]);
			}
		}
	}

	// Reversing an array using double recursion
	public void recReverse() {
		if (isEmpty()) {
			return;
		}
		int currentTop = pop();
		recReverse();

		/*
		 * Insert all the items (held in Function Call Stack) one by one from
		 * the bottom to top. Every item is inserted at the bottom
		 */
		putCurrentTopAtBottom(currentTop);
	}

	private void putCurrentTopAtBottom(int currentTop) {
		if (isEmpty()) {
			push(currentTop);
			return;
		}
		/*
		 * Hold all items in Function Call Stack until we reach end of the
		 * stack. When the stack becomes empty, the isEmpty(*top_ref) becomes
		 * true, the above if part is executed and the item is inserted at the
		 * bottom
		 */
		int temp = pop();
		putCurrentTopAtBottom(currentTop);
		/*
		 * Once the item is inserted at the bottom, push all the items held in
		 * Function Call Stack
		 */
		push(temp);
	}

	public static void main(String[] args) {
		StackArray s = new StackArray();
		s.push(5);
		s.push(7);// s.print();

		// s.pop();s.print();
		s.push(9);
		s.push(11);
		s.print();
		// s.pop();
		// s.pop();s.print();
		s.recReverse();
		s.print();
	}
}
