package princeton.algo2.graphprograms;

import edu.princeton.cs.algs4.Digraph;

import java.util.HashSet;
import java.util.Set;

public class CountDigraphRoots {
    private Digraph G;
    private int count;
    private Set<Integer> roots;
    public CountDigraphRoots(Digraph G) {
        this.G = new Digraph(G);
        roots = new HashSet<Integer>();
        findRootsCount();
    }
    private void findRootsCount(){
        for (int i=0; i<G.V(); i++) {
            if (G.outdegree(i)== 0){
                roots.add(i);
            }
        }
    }
    
    public int getRootsCount(){
        return roots.size();
    }
    

    public static void main(String[] args) {
        
    }
}
