package princeton.algo2.boggle;

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver
{
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    //private BoggleBoard board;

    private final int[] xPos = {-1, -1, 0, 1, 1, 1, 0, -1}; //start at 12'o clock clockwise
    private final int[] yPos = {0, 1, 1, 1, 0, -1, -1, -1};
    private final int[] score = {1,1,2,3,5};
    private boolean[][] marked;
    private Set<String> validWords;
    private AlphaTrie dict;
    
    public BoggleSolver(String[] dictionary) {
        dict = new AlphaTrie();
        for (int i = 0; i < dictionary.length; i++) {
            dict.put(dictionary[i]);
        }
//        System.out.println(dict.contains("ALLOWED"));
//        System.out.println(dict.isPrefix("ALLOW"));
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        validWords = new HashSet<String>();
        int w = board.cols();
        int h = board.rows();
        marked = new boolean[h][w];
        //Start iteration over the board to check if strings getting formed are in dictionary
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                marked[i][j] = true;
                String str;
                char c = board.getLetter(i, j);
                if (c == 'Q') {
                    str = "QU";
                } else {
                    str = (Character.valueOf(c)).toString();
                }
                
                recBackTrackFind(i, j, board, str);
                marked[i][j] = false;
            }
        }
        return validWords;
    }
    
    
    private void recBackTrackFind(int i, int j, BoggleBoard board, String str) {
        //System.out.println("Trying combinations with str = "+str);
        //Function will enter with at least 1 letter
        //base case ??
        //Go through all letters possible from the current board character tile
        for (int k = 0; k < 8; k++) {
            int nx = i + xPos[k];
            int ny = j + yPos[k];
            //System.out.println("Trying with coordinates:" + nx+","+ny);
            if (insideBoard(nx, ny, board) && !marked[nx][ny]) {
                //System.out.println("Trying with coordinates:" + nx+","+ny);
                marked[nx][ny] = true;
                char c = board.getLetter(nx, ny);
                String candidate;
                if (c == 'Q')
                    candidate = str.concat("QU");
                else    
                    candidate = str.concat(Character.valueOf(c).toString());
                //System.out.println("Trying string: " + candidate + ":" + dict.contains(candidate));
                
                
                if (candidate.length() > 2 && dict.contains(candidate)) {
                    //check if word exists in dictionary
                    validWords.add(candidate); //set implementation will ignore duplicates (we can defer this to trie!!)
                }
                
                if (dict.isPrefix(candidate)) {//valid prefix

                        //the tree is worth traversing as this is a valid prefix to some words
                        //possible optimization return char array so that we can check against that particular character??    
                        //???? What if i return the corresponding node of the trie itself, only need to look down in that case
                    recBackTrackFind(nx, ny, board, candidate);
                }
                marked[nx][ny] = false;
            }
        } // end for loop
    }
    


    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        int l = word.length();
        if (l <= 2)
            return 0;
        //Search in dictionary else return 0
        if (!dict.contains(word))
            return 0;
        //Word exists in the dictionary
        
        if (l >= 8)
            return 11;
        else 
            return score[l-3];
    }

    private boolean insideBoard(int x, int y, BoggleBoard board) {
        if (x < 0 || x >= board.rows()
                || y < 0 || y >= board.cols())
            return false;
        //System.out.println("coordinate "+x+","+y+" is safe");
        return true;
    }
//    public static void main(String[] args) {
//        List<String> l = new ArrayList<String>();
//        In in = new In("dictionary-algs4.txt");
//        String line = in.readLine();
//        while (line != null) {
//            l.add(line.trim());
//            line = in.readLine();
//        }
//        System.out.println("Dictionary entries " + l.size());
//        String[] str = new String[l.size()];
//        for (int i = 0; i < l.size(); i++)
//            str[i] = l.get(i);
//        BoggleSolver b = new BoggleSolver(str);
//        //BoggleSolver b2 = new BoggleSolver(str);
//        System.out.println("Constructed dictionary size " + b.dict.size());
//        
//        for (String s: str) {
//            if (!b.dict.contains(s))
//                System.out.println(s + " not found ");
//        }
//
////        for (String s: str) {
////                System.out.println(s + " "+ b.dict.isPrefix(s));
////        }
//        
//        BoggleBoard bb = new BoggleBoard("board-q.txt");
//        System.out.println(bb.toString());
//        Set<String> v =(Set<String>) b.getAllValidWords(bb);
//        System.out.println("set size "+v.size());
//        for (String s: v)
//            System.out.println(s);
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
////         Set<String> v1 =(Set<String>) b2.getAllValidWords2(bb);
////        System.out.println("set size "+v1.size());
////        for (String s: v1)
////            System.out.println(s);
//    }
}
