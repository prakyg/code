package princeton.algo2.graphprograms;

import edu.princeton.cs.algs4.Digraph;

public class DiGraphCycleDetector {
    private boolean marked[];
        private boolean onStack[];
        private Digraph G;
        private boolean hasCycle;

        public DiGraphCycleDetector(Digraph G) {
            this.G = new Digraph(G);
            marked = new boolean[G.V()];
            onStack = new boolean[G.V()];
            findCycle(0);
        }

        public boolean hasCycle() {
            return hasCycle;
        }

        private void findCycle(int v) {
            marked[v] = true;
            onStack[v] = true;

            for (int w : G.adj(v)) {
                if(!marked[w]) {
                    findCycle(w);
                } else if (onStack[w]) {
                    hasCycle = true;
                    return;
                }
            }

            onStack[v] = false;
        }
    
    public static void main(String[] args) {
        
    }
}
