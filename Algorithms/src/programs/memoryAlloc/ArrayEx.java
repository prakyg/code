package programs.memoryAlloc;

import java.io.PrintWriter;

import static java.lang.System.out;

import org.openjdk.jol.datamodel.X86_32_DataModel;
import org.openjdk.jol.datamodel.X86_64_COOPS_DataModel;
import org.openjdk.jol.datamodel.X86_64_DataModel;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.layouters.CurrentLayouter;
import org.openjdk.jol.layouters.HotSpotLayouter;
import org.openjdk.jol.layouters.Layouter;
import org.openjdk.jol.util.VMSupport;

import programs.memoryAlloc.DataModels.A;

public class ArrayEx {
	public static void main(String[] args) throws Exception {
		out.println(VMSupport.vmDetails());
		int[] a = new int[0];
		out.println(GraphLayout.parseInstance(a).toFootprint());
	}
}
