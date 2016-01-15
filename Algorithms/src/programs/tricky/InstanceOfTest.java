package programs.tricky;

import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

public class InstanceOfTest {
	public InstanceOfTest() {
		super();
	}

	public static void main(String[] args) throws IOException {
		HashSet h = null;
		Set s = new HashSet();
		if (h instanceof HashSet)
			System.out.println("null is instance of HashSet");
		h = new HashSet();
		if (h instanceof HashSet)
			System.out.println("h is instance of HashSet");
		if (h instanceof Set)
			System.out.println("h is instance of Set");
		if (s instanceof HashSet)
			System.out.println("s is instance of HashSet");
		if (s instanceof Set)
			System.out.println("s is instance of Set");

		SuperX x = new SuperX();
		if (x instanceof SuperX)
			System.out.println("x is instance of SuperX");
		if (x instanceof ChildX)
			System.out.println("x is instance of ChildX");
	}
}
