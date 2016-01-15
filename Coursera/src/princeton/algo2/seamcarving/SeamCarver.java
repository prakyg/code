package princeton.algo2.seamcarving;

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private int width;
    private int height;
    private int [][] colorArr;
    private double[][] energy;
    private boolean transposed = false;
    public SeamCarver(Picture picture) {               // create a seam carver object based on the given picture
        if (picture == null)
            throw new NullPointerException("picture object can't be null");
        this.width = picture.width();
        this.height = picture.height();
        int max = Math.max(width, height);
        this.colorArr = new int[max][max];
        this.energy = new double[max][max];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                
                Color temp = picture.get(j,i);  //since picture is getting referenced in column, row type, 
                                                    //we want to store it in the array as it is.
                int c = temp.getRed();
                c = (c << 8) | temp.getGreen();
                c = (c << 8) | temp.getBlue();
                colorArr[i][j] = c;
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                energy[i][j] = this.energy(j,i);    //the energy method takes input in column, row way
            }
        }
    }
    public Picture picture() {                         // current picture
        if (transposed)
            transpose();
        Picture picture = new Picture(width,height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                picture.set(i,j,new Color(colorArr[j][i]));
            }
        }
        return picture;
    }
    public int width() {                           // width of current picture
        if (transposed)
            return height;
        return width;
    }
    public int height() {                          // height of current picture
        if (transposed)
            return width;
        return height;
    }
    public double energy(int j, int i) {              // energy of pixel at column j and row i
        if (transposed) {
            int temp = i;
            i = j;
            j = temp;
        }         
        return energyInternal(i,j);
    }
    private void checker(int i, int j){
        //System.out.println("checking index i,j= "+i+","+j);
        if (i < 0 || i >= height || j < 0 || j >= width) {
            //System.out.println("violation for "+i+","+j);
            throw new IndexOutOfBoundsException();
        }
    }
    private int xGrad(int i, int j){
        int rightPix = colorArr[i][j+1];
        int leftPix = colorArr[i][j-1];
        return energyCalc(leftPix,rightPix);
    }
    private int yGrad(int i, int j){
        int upPix = colorArr[i-1][j];
        int downPix = colorArr[i+1][j];
        return energyCalc(upPix, downPix);
    }
    private int energyCalc(int c1, int c2){
        return (int)(Math.pow(getRed(c2) - getRed(c1), 2) +
                    Math.pow(getBlue(c2) - getBlue(c1), 2) +
                        Math.pow(getGreen(c2) - getGreen(c1), 2));
    }
    public int[] findHorizontalSeam() {              // sequence of indices for horizontal seam
        if (!transposed)
            transpose();
        return findVerticalSeamInternal();
    }
    
    public int[] findVerticalSeam() {                // sequence of indices for vertical seam
        if (transposed)
            transpose(); //removing transpose
        return findVerticalSeamInternal();
    }
    
    public void removeHorizontalSeam(int[] seam) {  // remove horizontal seam from current picture
        //checkSeam(seam,height);
        if (!transposed)
            transpose();
        removeVerticalSeamInternal(seam);
    }
    public void removeVerticalSeam(int[] seam) {    // remove vertical seam from current picture
        if (transposed)
            transpose(); //remove transpose
        removeVerticalSeamInternal(seam);
    }
    private void removeVerticalSeamInternal(int[] seam) {
        if (seam == null)
            throw new NullPointerException();
        if (width <= 1)
            throw new IllegalArgumentException();
        checkSeam(seam);
        // removing a vertical seam means we are removing a vertical line in the actual array
        // since we need to copy elements after removing a line, traversing array horizontally is faster
        for (int i = 0; i < height; i++) {
            //remove colorArr[i][seam[i]] entry from the array, shift array after removing the seam
            //seam[i] is the pixel to be removed, so we need to copy seam[i+1] to seam[i]
            System.arraycopy(colorArr[i], seam[i] + 1, colorArr[i], seam[i], width - seam[i] - 1);
            //need to update energies from the energy array
            System.arraycopy(energy[i], seam[i] + 1, energy[i], seam[i], width - seam[i] - 1);
        }
        width--;
        //System.out.println("Width/height of the image = "+width+" "+height);
        
        for (int i = 0; i < height; i++) {
            //1. Update energy of colorArr[i][seam[i]-1] and colorArr[i][seam[i]+1] which has become
            //colorArr[i][seam[i]] after shift
            if (seam[i] - 1 >= 0) {//we might end up checking for a pixel outside array, ignore them
                //System.out.println("Calculating energy for pixel1 "+i+","+(seam[i]-1));
                energy[i][seam[i] - 1] = energyInternal(i, seam[i] - 1);
            }
            if (seam[i] < width) {
                //System.out.println("Calculating energy for pixel2 "+i+","+(seam[i]));
                energy[i][seam[i]] = energyInternal(i, seam[i]);
            }
        }
    }
    private int[] findVerticalSeamInternal() {
        DirectedAcyclicSP seamFinder = new DirectedAcyclicSP(energy,width,height);
        return seamFinder.getVerticalSeam();
    }
    private double energyInternal(int i, int j) {
        //return energy(j,i);
        //System.out.println("trying to calculate energy at pixel O=("+i+","+j+")");
        checker(i, j);
        if (i == 0 || i == height-1 
                || j == 0 || j == width-1) {
            return 1000;
        }
        return Math.sqrt(xGrad(i, j) + yGrad(i, j));
    }
    private void checkSeam(int[] seam){
        if (seam.length != height) {
            throw new IllegalArgumentException();
        }
        int len = width;
        int prev = seam[0];
        if (prev < 0 || prev >= len)
            throw new IllegalArgumentException();
        
        for (int i = 1; i < height; i++) {
            //System.out.println("checking seam val : "+i+","+seam[i]+" prev="+prev);
            if (seam[i] < 0 || seam[i] >= len
                    || seam[i] < prev-1 || seam[i] > prev+1)
                throw new IllegalArgumentException();
            prev = seam[i];
        }
    }
    private void transpose() {
        //System.out.println("Width/height of the image = "+width+" "+height);
        int max = Math.max(width, height);
        for (int i = 0; i < max; i++) {
            for (int j = i; j < max; j++) {
                //swap
                int temp = colorArr[i][j];
                colorArr[i][j] = colorArr[j][i];
                colorArr[j][i] = temp;
            }
        }
//        System.out.println("Energy array before transpose: ");
//        for (int i=0; i<height; i++){
//            for (int j=0; j<width; j++){
//                //swap
//                System.out.print(energy[i][j]+" ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < max; i++) {
            for (int j = i; j < max; j++) {
                //swap
                //System.out.println("swapping at loc "+i+","+j+" "+energy[i][j] +" with "+ energy[j][i]);
                double temp = energy[i][j];
                energy[i][j] = energy[j][i];
                energy[j][i] = temp;
            }
        }
        //swap width & height
        int temp = width;
        width = height;
        height = temp;
        //toggle the value of transposed
        transposed = !transposed;
        
//        System.out.println("Energy array after transpose: ");
//        for (int i=0; i<height; i++){
//            for (int j=0; j<width; j++){
//                //swap
//                System.out.print(energy[i][j]+" ");
//            }
//            System.out.println();
//        }
        //System.out.println("Width/height of the image transposed = "+width+" "+height);
    }
    public static void main(String[] args) {
        //SeamCarver seamCarver = new SeamCarver();
    }

    private int getRed(int c2) {
        return (c2>>16) & 0xFF;
    }
    
    private int getGreen(int c2) {
        return (c2>>8) & 0xFF;
    }

    private int getBlue(int c2) {
        return c2 & 0xFF;
    }

    
}
