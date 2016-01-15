package programs.memoryAlloc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.util.VMSupport;
import static java.lang.System.out;

public class ClassWord {
	/*
	 * This is the example to have insight into object headers. In HotSpot,
	 * object header consists of two parts: mark word, and class word. We can
	 * clearly see the class word by analysing two syntactically equivalent
	 * instances of two distinct classes. See the difference in headers, that
	 * difference is the reference to class.
	 */

	public static void main(String[] args) throws Exception {
		out.println(VMSupport.vmDetails());
		out.println(ClassLayout.parseClass(A.class).toPrintable(new A()));
		out.println(ClassLayout.parseClass(B.class).toPrintable(new B()));
	}

	public static class A {
		// no fields
	}

	public static class B {
		// no fields
	}
}
