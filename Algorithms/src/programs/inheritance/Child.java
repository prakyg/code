package programs.inheritance;

public class Child extends Parent {
	public Child() {
		super();
	}

	// @Override if we had used override annotation we would have immediately
	// noticed that
	// this method doesn't do any overriding at all
	public void noPrivateOverride() {
		System.out.println("Child NoOverride");
	}

	/*
	 * final public void noFinalOverride(){ System.out.println(
	 * "Parent noFinalOverride"); }
	 */

	// @Override
	public static void noStaticOverride() {
		System.out.println("Child noStaticOverride");
	}
}
