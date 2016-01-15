package programs;

public class StackOverFlowCheck {
	public StackOverFlowCheck() {
		super();
	}

	public static void main(String[] args) {
		StackOverFlowCheck stackOverFlowCheck = new StackOverFlowCheck();
		recurse(0);
	}

	static void recurse(int i) {
		long l = 0; // populate stack;
		long l2 = 0, l3 = 0, l4 = 0;
		Object o = null;
		if (i % 100 == 0)
			System.out.println("Value of i " + i);
		recurse(++i);
	}
}
