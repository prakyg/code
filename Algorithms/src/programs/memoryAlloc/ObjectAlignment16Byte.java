package programs.memoryAlloc;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.util.VMSupport;

public class ObjectAlignment16Byte {
	public ObjectAlignment16Byte() {
		super();
	}

	public static void main(String[] args) {
		// ObjectAlignment16Byte objectAlignment16Byte = new
		// ObjectAlignment16Byte();
		System.out.println(System.getProperty("java.version"));
		System.out.println(VMSupport.vmDetails());
		Character a = 'a';
		System.out.println(GraphLayout.parseInstance(a).toFootprint());

		char[] arr = new char[5];
		arr[0] = 'b';
		arr[1] = 'b';
		arr[2] = 'b';
		arr[3] = 'b';
		arr[4] = 'b';

		System.out.println(GraphLayout.parseInstance(arr).toFootprint());
	}
}
