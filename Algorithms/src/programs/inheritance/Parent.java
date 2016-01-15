package programs.inheritance;

public class Parent {
	public Parent() {
		super();
	}

	private void noPrivateOverride() {
		System.out.println("Parent NoOverride");
	}

	final public void noFinalOverride() {
		System.out.println("Parent noFinalOverride");
	}

	public static void noStaticOverride() {
		System.out.println("Parent noStaticOverride");
	}
}
