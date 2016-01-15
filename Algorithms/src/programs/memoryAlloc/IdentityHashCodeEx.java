package programs.memoryAlloc;

import static java.lang.System.out;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.util.VMSupport;

public class IdentityHashCodeEx {
	/*
	 * The example for identity hash code. The identity hash code, once
	 * computed, should stay the same. HotSpot opts to store the hash code in
	 * the mark word as well. You can clearly see the hash code bytes in the
	 * header once it was computed.
	 */

	public static void main(String[] args) throws Exception {
		out.println(VMSupport.vmDetails());
		final A a = new A();

		ClassLayout layout = ClassLayout.parseClass(A.class);
		out.println("**** Fresh object");
		out.println(layout.toPrintable(a));
		out.println("hashCode: " + Integer.toHexString(a.hashCode()));
		out.println();
		out.println("**** After identityHashCode()");
		out.println(layout.toPrintable(a));
	}

	public static class A {
		// no fields
	}
}
