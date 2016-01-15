package programs.tricky;

public class MultiOverflow {
	public MultiOverflow() {
		super();
	}

	public static void main(String[] args) {
		MultiOverflow multiOverflow = new MultiOverflow();
		// expression are evaluated from left to right in java.
		// an expression is always converted to int if not specified
		// if specified promotion to that type will take place
		long longWithoutL = 1000 * 60 * 60 * 24 * 365;
		System.out.println("Long multi without L: " + longWithoutL);
		long longWithL = 1000 * 60 * 60 * 24 * 365L;
		System.out.println("Long multi without L: " + longWithL);
		// but even the 2nd expression is incorrect

		long longWithIncorrectL = 1000 * 60 * 60 * 24 * 365 * 1000L;
		System.out.println("Long multi with end L: " + longWithoutL);
		long longWithCorrectL = 1000 * 60 * 60 * 24 * 365 * 1000L;
		System.out.println("Long multi with start L: " + longWithL);
	}
}
