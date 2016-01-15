package princeton.algo2.graphprograms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestClient {
    public TestClient() {
        super();
    }

    public static void main(String[] args) {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length   = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
        System.out.println(sap.length(11, 12));
        Map<String, List<Integer>> m = new HashMap<String, List<Integer>>();
        m.put("A",new ArrayList<Integer>(Arrays.asList(1,2)));        
        List<Integer> l1 = m.get("A");
        l1.add(3);
        l1 = m.get("A");
        System.out.println(Arrays.toString(l1.toArray()));
    }
}
