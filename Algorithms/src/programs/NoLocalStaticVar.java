package programs;

public class NoLocalStaticVar {
	public NoLocalStaticVar() {
		super();
	}

	public void localStatic() {
		// static int x=0; //compiler error
	}

	public static void staticMethod() {
		// final static int x=0; //compiler error
	}

	public static void main(String[] args) {
		NoLocalStaticVar staticVarInLocalMethods = new NoLocalStaticVar();
	}
}
