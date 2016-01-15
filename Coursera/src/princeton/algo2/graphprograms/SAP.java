package princeton.algo2.graphprograms;

import edu.princeton.cs.algs4.Digraph;

public class SAP {
    private Digraph G;
    private FindAncestor fa;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new NullPointerException();
        this.G = new Digraph(G);
        this.fa = new FindAncestor(G);
    }

    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     */
    public int length(int v, int w) {
        if (v < 0 || v >= G.V() 
            || w < 0 || w >= G.V())
            throw new java.lang.IndexOutOfBoundsException();
        return fa.getAncestorLength(v, w);
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     */
    public int ancestor(int v, int w) {
        if (v < 0 || v >= G.V() 
            || w < 0 || w >= G.V())
            throw new java.lang.IndexOutOfBoundsException();
        return fa.getAncestor(v, w);
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
        return fa.getAncestorLength(v, w);
        
    }

    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
        return fa.getAncestor(v, w);
    }

       // do unit testing of this class
    public static void main(String[] args) {
        
    }
}
