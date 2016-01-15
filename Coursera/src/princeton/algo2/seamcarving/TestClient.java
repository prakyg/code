package princeton.algo2.seamcarving;

public class TestClient {
    public TestClient() {
        super();
    }

    public static void main(String[] args) {
        int [] a = new int[10];
        for (int i = 0; i< 10 ; i++) {
            //System.out.println();
            a[i] = i;
        }
        int p = 5;
        System.arraycopy(a, p + 1, a, p, a.length - p - 1);
        for (int i = 0; i< 10 ; i++) {
            System.out.print(a[i]+" ");
        }
    }
}
