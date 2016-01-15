package princeton.algo2.burrowsWheeler;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
//        String s = BinaryStdIn.readString();
//        char[] input = s.toCharArray();
        
        
        char[] intToChar = new char[256];
        int[] charIndex = new int[256];
        
        for (int i = 0; i < 256; i++) {
            intToChar[i] = (char)i;
            charIndex[(char)i] = i;
        }
        
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            //find c's index 
            int index = charIndex[c];
            BinaryStdOut.write((char)index);
            
            //update mapping
            charIndex[c] = 0;
        }
            
    }

        // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        
    }

        // if args[0] is '-', apply move-to-front encoding
        // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        
    }
}
