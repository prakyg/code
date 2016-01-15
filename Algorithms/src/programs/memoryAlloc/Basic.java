package programs.memoryAlloc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.util.VMSupport;

/*
     * This sample showcases the basic field layout.
     * You can see a few notable things here:
     *   a) how much the object header consumes;
     *   b) how fields are laid out;
     *   c) how the external alignment beefs up the object size
     */
//For more examples: http://hg.openjdk.java.net/code-tools/jol/file/tip/jol-samples/src/main/java/org/openjdk/jol/samples/

public class Basic {
	boolean a;

	public Basic() {
		super();
	}

	public static void main(String[] args) {
		// MemoryTest memoryTest = new MemoryTest();
		System.out.println(System.getProperty("java.version"));
		System.out.println(VMSupport.vmDetails());

		// Shallow space will be printed: This is only the space consumed by a
		// single instance of that class;
		// it does not include any other objects referenced by that class.
		// It does include VM overhead for the object header, field alignment
		// and padding.
		System.out.println(ClassLayout.parseClass(Basic.class).toPrintable());

		System.out.println(ClassLayout.parseClass(A.class).toPrintable());
		A obj = new A();
		// Deep space
		// Of course, some objects in the footprint might be shared (also
		// referenced from other objects),
		// so it is an overapproximation of the space that could be reclaimed
		// when that object is garbage collected.
		System.out.println(GraphLayout.parseInstance(obj).toFootprint());

		// If you instead use GraphLayout.parseInstance(obj).toPrintable(),
		// jol will tell you the address, size, type, value and path of field
		// dereferences to each referenced object,
		// though that's usually too much detail to be useful
		System.out.println(GraphLayout.parseInstance(obj).toPrintable());
	}

	public static class A {
		Integer a;
	}

}
