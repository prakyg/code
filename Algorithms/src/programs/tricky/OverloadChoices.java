package programs.tricky;

//According to java specs, in case of overloading, compiler picks the most specific function at the time of compilation.
public class OverloadChoices {
	public OverloadChoices() {
		super();
	}

	public static void main(String[] args) {
		OverloadChoices o = new OverloadChoices();
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Calling method with a Sting literal");
		o.method("x");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Calling method with a integer literal");
		o.method(2);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Calling method with an object");
		o.method(new Object());
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Calling method with a null");
		o.method(null);
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Calling method with an object reference that points to string literal");
		Object o2 = new String("h");
		o.method(o2);
	}

	public void method(Object o) {
		System.out.println("Object impl");
	}

	public void method(String s) {
		System.out.println("String impl");
	}
	// Compilation error
	/*
	 * public void method(String... s) { System.out.println("String impl"); }
	 */
}
