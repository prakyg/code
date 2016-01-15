package princeton.algo2.graphprograms;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.HashSet;
import java.util.Set;

class FindAncestor {
    private Digraph G;
    //private int ancestorLength;
    private int ancestor = -1;
    //private boolean[] marked, markedByW ;
    
    public FindAncestor(Digraph G) {
        this.G = new Digraph(G);
        
    }
    public int getAncestor(int v1, int w1){
        ancestor = -1;
        getAncestorLength(v1, w1);
        return ancestor;
    }
    public int getAncestorLength(int v1, int w1){
        int[] distTo = new int[G.V()];
        int[] edgeTo = new int[G.V()];
        int[] distToByW = new int[G.V()];
        
        int s = v1;
        Queue<Integer> q = new Queue<Integer>();
        Set<Integer> markedSet = new HashSet<Integer>();
        markedSet.add(s); //marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!markedSet.contains(w)) {                             //!marked[w]
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    markedSet.add(w); //marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
        
        s = w1;
        q.enqueue(w1);
        int currDist = Integer.MAX_VALUE;
        
        Set<Integer> markedByWSet = new HashSet<Integer>();
        markedByWSet.add(w1);             //markedByW[w1] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            if (markedSet.contains(v) && distTo[v]+distToByW[v] < currDist){  //node was visited in BFS-1    //marked[v]
                currDist = distTo[v]+distToByW[v];
                ancestor = v;
            }
            //Optimization
                if (currDist != Integer.MAX_VALUE && distToByW[v] >= currDist)
                    return currDist;
            
            for (int w : G.adj(v)) {
                if (!markedByWSet.contains(w)) {                        //!markedByW[w]
                    //edgeTo[w] = v;
                    distToByW[w] = distToByW[v] + 1;
                    markedByWSet.add(w);                            //markedByW[w] = true;
                    q.enqueue(w);
                }
            }
        }
        if (currDist == Integer.MAX_VALUE)
            return -1;
        return currDist;
    }
    public int getAncestor(Iterable<Integer> v1,Iterable<Integer> w1) {
        ancestor = -1;
        getAncestorLength(v1, w1);
        return ancestor;
    }
    
    public int getAncestorLength(Iterable<Integer> v1,Iterable<Integer> w1) {
            int[] distTo = new int[G.V()];
            int[] edgeTo = new int[G.V()];
            int[] distToByW = new int[G.V()];
            
            Iterable<Integer> sources = v1;
            Set<Integer> markedSet = new HashSet<Integer>();
            Queue<Integer> q = new Queue<Integer>();
            for (int s : sources) {
                markedSet.add(s); //marked[s] = true;
                distTo[s] = 0;
                q.enqueue(s);
            }
            while (!q.isEmpty()) {
                int v = q.dequeue();
                for (int w : G.adj(v)) {
                    if (!markedSet.contains(w)) {            //!marked[w]
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        markedSet.add(w); //marked[w] = true;
                        q.enqueue(w);
                    }
                }
            }
            Set<Integer> markedByWSet = new HashSet<Integer>();
            sources = w1;
            int currDist = Integer.MAX_VALUE;
            for (int s : sources) {
                markedByWSet.add(s); //markedByW[s] = true;
                distToByW[s] = 0;
                q.enqueue(s);
            }
            while (!q.isEmpty()) {
                int v = q.dequeue();
                
                if (markedSet.contains(v) && (distTo[v]+distToByW[v]) < currDist){  //node was visited in BFS-1      //marked[v]
                    currDist = distTo[v]+distToByW[v];
                    ancestor = v;
                }
                //Optimization
                if (currDist != Integer.MAX_VALUE && distToByW[v] >= currDist)
                    return currDist;
                
                for (int w : G.adj(v)) {
                    if (!markedByWSet.contains(w)) {                   //!markedByW[w]
                        //edgeTo[w] = v;
                        distToByW[w] = distToByW[v] + 1;
                        markedByWSet.add(w);//markedByW[w] = true;
                        q.enqueue(w);
                    }
                }
            }
            if (currDist == Integer.MAX_VALUE)
                return -1;
            return currDist;
        }
}
