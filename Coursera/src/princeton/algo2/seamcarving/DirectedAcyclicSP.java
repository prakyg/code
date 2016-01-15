package princeton.algo2.seamcarving;

import java.util.Stack;

public class DirectedAcyclicSP {
    double[][] energy;
    int width;
    int height;
    int[][] edgeTo;
    double[][] distTo;
    public DirectedAcyclicSP(double[][] energy, int width, int height) {
        this.energy = energy;
        this.width = width;
        this.height = height;
        edgeTo = new int[height][width];
        distTo = new double[height][width];
        for (int i=0; i< height; i++) {
            for (int j=0; j<width; j++){
                distTo[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }
    public int[] getVerticalSeam(){
        for (int j=0; j<width; j++) {
            distTo[0][j] = energy[0][j];
        }
//        for (int j=0; j<width; j++) {
//            //start at pixel energy[0][j]
//            recRelax(1,j,0,j);
//            if (j+1 < width)
//                recRelax(1,j+1,0,j);
//            if (j-1 >= 0)
//                recRelax(1,j-1,0,j);
//        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //relax the 3 edges
                if (i+1 < height) {
                    if (j-1 >= 0)
                        relax(i+1,j-1,i,j);
                    relax(i+1,j,i,j);
                    if (j+1 < width)
                        relax(i+1,j+1,i,j);
                }
            }
        }
        //At this point the complete distTo array is filled, i.e. no value should if infinity
        //lets compute the minimum distTo[height-1][j]
        int mini = height-1, minj=0;
        double min = distTo[height-1][0];
        for (int j=1; j<width; j++) {
            double tempDist = distTo[height-1][j];
            if (min > tempDist) {
                min = tempDist;
                minj = j;
            }
        }
        //we now have the min distance as well as final pixel of minimum seam
        int[] seam = new int[height];
        seam[height-1] = minj;
        for (int i = height-1; i > 0; i--)
            seam[i-1] = edgeTo[i][seam[i]];
        return seam;
    }
    private void relax(int i, int j, int m, int n) {
        double sourceDist = distTo[m][n];
        double currEnergy = energy[i][j];
        if (distTo[i][j] > sourceDist + currEnergy ){
            //we need to update
            distTo[i][j] = sourceDist + currEnergy;
            edgeTo[i][j] = n;  
        }
    }
    private void recRelax(int i, int j, int m, int n) {
        double sourceDist = distTo[m][n];
        double currEnergy = energy[i][j];
        if (distTo[i][j] > sourceDist + currEnergy ){
            //we need to update
            distTo[i][j] = sourceDist + currEnergy;
            edgeTo[i][j] = n;  
            //since we updated this edge we need to relax all edges from this pixel
            if (i+1 < height) {
                if (j-1 >= 0)
                    recRelax(i+1,j-1,i,j);
                recRelax(i+1,j,i,j);
                if (j+1 < width)
                    recRelax(i+1,j+1,i,j);
            }
        }
    }
}
