package princeton.algo2.graphprograms;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {
    private Digraph G;
    private List<String> synSetList = new ArrayList<String>();
    private Map<String, List<Integer>> m = new HashMap<String, List<Integer>>();
    private SAP s;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new NullPointerException();
        In in = new In(synsets);
        int nodeId = 0;
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] arr = line.split(",");
            String[] nouns = arr[1].split(" ");
            synSetList.add(arr[1]);
              
            for (int i= 0; i< nouns.length; i++) {
                String n = nouns[i];
                List<Integer> l = m.get(n);
                if (l == null)
                    l = new ArrayList<Integer>();
                l.add(nodeId);
                m.put(n, l);
            }
            nodeId++;
        }
        G = new Digraph(synSetList.size());
        in = new In(hypernyms);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] arr = line.split(",");
            int synsetId = Integer.parseInt(arr[0]);
            for (int i = 1; i < arr.length; i++){
                //each directed edge v ->w represents that w is a hypernym of v
                G.addEdge(synsetId, Integer.parseInt(arr[i]));
            }
        }
        //Validate that graph has no cycles
        DiGraphCycleDetector cycleCheck = new DiGraphCycleDetector(G);
        if (cycleCheck.hasCycle()) {
            throw new IllegalArgumentException();
        }
        //TODO: Throw IllegalArgumentException if input is not a rooted 
        CountDigraphRoots rootCounter = new CountDigraphRoots(G);
        //System.out.println("Num roots = "+rootCounter.getRootsCount() );
        if (rootCounter.getRootsCount() != 1) {
            throw new IllegalArgumentException();
        }
        
    }

    /**
     * returns all WordNet nouns
     */
    public Iterable<String> nouns() {
        return m.keySet();
    }

    /**
     * is the word a WordNet noun?
     */
    public boolean isNoun(String word) {
        if (word == null)
            throw new NullPointerException();
        return m.containsKey(word);
    }

    /**
     * returns MINIMUM distance between nounA and nounB (defined below)
     * NounA and NounB can relate to multiple nodes
     */
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new NullPointerException();
        if (!m.containsKey(nounA) || !m.containsKey(nounB))
            throw new IllegalArgumentException();
        if (s == null)
            s = new SAP(G); 
        return s.length(m.get(nounA), m.get(nounB));
    }

    /**
     *  a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
        in a shortest ancestral path (defined below)
    */
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new NullPointerException();
        if (!m.containsKey(nounA) || !m.containsKey(nounB))
            throw new IllegalArgumentException();
        if (s == null)
            s = new SAP(G); 
        
        int ancestorNode = s.ancestor(m.get(nounA), m.get(nounB));
        return synSetList.get(ancestorNode);
    }

       // do unit testing of this class
    public static void main(String[] args) {
//        WordNet w = new WordNet("synsets.txt","hypernyms.txt");
//        System.out.println(w.sap("worm", "bird"));
//        System.out.println(w.distance("worm", "bird"));
    }
//    private static class SynsetNode{
//        private final List<String> synonyms;
//        private final String gloss;
//        
//        SynsetNode(List<String> synonyms,String gloss){
//            this.synonyms = synonyms;
//            this.gloss = gloss;
//        }
//    }
}
