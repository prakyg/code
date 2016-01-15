package princeton.algo2.graphprograms;

public class Outcast {
    private WordNet w;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.w = wordnet;
    }
    // given an array of WordNet nouns, return an outcast
    //Assume that argument to outcast() contains only valid wordnet nouns (and that it contains at least two such nouns). 
    public String outcast(String[] nouns) {
        int [] dist = new int[nouns.length];
        int maxD = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < nouns.length; i++) {
            for (int j = 0; j < nouns.length; j++) {
                if (j != i)  //optimization
                    dist[i] += w.distance(nouns[i], nouns[j]);
            }
            if (dist[i] > maxD) {
                maxD = dist[i];
                maxIndex = i;
            }
        }
        return nouns[maxIndex];
    }
    // see test client below
    public static void main(String[] args) {

    }
}
