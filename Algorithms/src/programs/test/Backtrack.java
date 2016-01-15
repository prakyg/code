package programs.test;

public class Backtrack {
	public Backtrack() {
		super();
	}

	public static void main(String[] args) throws java.lang.Exception {
		RecSubsets("", "ABC");
	}

	public static void RecSubsets(String soFar, String rest) {
		if (rest.length() == 0)
			System.out.println(soFar);
		else {
			RecSubsets(soFar + rest.charAt(0), rest.substring(1, rest.length())); // include
																					// first
																					// char
			RecSubsets(soFar, rest.substring(1, rest.length())); // exclude
																	// first
																	// char
		}
	}
}
