package programs.tricky;

//A finally block shouldn't contain a return statement.
//This class shows you the effects of not paying heed to the above warning.
public class FinallyBlock {
	private static int staticCount = 0;

	public FinallyBlock() {
		super();
	}

	public static void main(String[] args) {
		System.out.println("When Finally does a return: " + finallyReturns());
		System.out.println("----------------------------------------------------------------");
		System.out.println("When Finally reurns with a expression: " + finallyReturnsWithExp());
		System.out.println("----------------------------------------------------------------");
		System.out.println("When Finally changes the return var: " + finallyChangesReturnVar());
		System.out.println("----------------------------------------------------------------");
		System.out.println("When Finally returns with a expression: " + finallyReturnsWithExpDetailed());
		System.out.println("----------------------------------------------------------------");

		try {
			int ret = finallyReturnSwallowsException();
			System.out.println("If this stmt executes that means exception got swallowed");
			System.out.println("Value returned is " + ret);
		} catch (IllegalArgumentException ile) {
			System.out.println("Exception caught");
		}
		System.out.println("----------------------------------------------------------------");

	}

	// This method tells us that in case both try and finally have return stmt
	// finally's stmt will be executed (although try's return(expr) will be
	// calculated
	public static int finallyReturns() {
		int count = 0;
		try {
			return 25;
		} finally {
			return 50;
		}
	}

	public static int finallyReturnsWithExp() {
		int count = 0;
		try {
			return (count = count + 10); // value of count at this point =10
		} finally {
			return (count = count + 20); // value of count at this point =30
		}
	}

	// This method tells us a very important thing
	// i.e. in return (expr) the expr is calculated before the flow passes on to
	// finally block
	// and this calculated value is returned back (no matter what happens in
	// finally block)
	public static int finallyChangesReturnVar() {

		int count = 100;
		try {
			return (count);
		} finally {
			System.out.println("in finally block");
			count = -100;
			System.out.println("Value of count at this moment:" + count);
		}
	}

	// This method shows nothing new (just gives you proof)
	public static int finallyReturnsWithExpDetailed() {
		int count = 0;
		try {
			System.out.println("in try block");
			return tryRet(); // value of count at this point =10
		} finally {
			return tryFin(); // value of count at this point =30
		}
	}

	private static int tryRet() {
		System.out.println("calculating try's return expression");
		staticCount = 1;
		System.out.println("Static count value is set to " + staticCount);
		return staticCount;
	}

	private static int tryFin() {
		System.out.println("calculating fianlly's return expression");
		System.out.println("Static count value received is " + staticCount);
		staticCount++;
		System.out.println("Static count value incremented by 1 and is " + staticCount);
		return staticCount;
	}

	/*
	 * This method shows that even if you throw an exception in try/catch block,
	 * if your finally has a return it will result in swallowing of exceptions
	 */
	public static int finallyReturnSwallowsException() throws IllegalArgumentException {
		int count = 0;
		try {
			System.out.println("in try block");
			throw new IllegalArgumentException();
		} finally {
			System.out.println("In finally block after exception is thrown");
			return 10; // value of count at this point =30
		}
	}
}
