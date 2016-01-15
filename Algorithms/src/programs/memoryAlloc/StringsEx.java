package programs.memoryAlloc;

import java.io.PrintWriter;
import static java.lang.System.out;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.util.VMSupport;

public class StringsEx {
	public static void main(String[] args) throws Exception {
		out.println(VMSupport.vmDetails());

		String s = "a";
		String s2 = "1234";
		System.out.println(s2.hashCode());

		System.out.println(ClassLayout.parseClass(String.class).toPrintable());
		PrintWriter pw = new PrintWriter(out);
		// Notice that the size of String Object Header = 24
		// The object on the heap is as follows
		// Char array = 16 bytes (64-bit compressed)
		// data = 2 bytes (1 character = 2 bytes)
		// Alignment = 6 bytes lost
		pw.println(GraphLayout.parseInstance(s).toFootprint());
		pw.println(GraphLayout.parseInstance(s2).toFootprint());

		int[] a = new int[0];
		pw.println(GraphLayout.parseInstance(a).toFootprint());

		pw.close();

		;
	}
}
