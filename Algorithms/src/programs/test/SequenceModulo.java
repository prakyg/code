package programs.test;

public class SequenceModulo {
	public SequenceModulo() {
		super();
	}

	public static void main(String[] args) throws java.lang.Exception {
		int[] v = { 5, 6, 4, 4 };
		int W = 10;
		boolean[] belongs = new boolean[v.length];
		for (int i = 0; i < v.length; i++) {
			belongs[i] = false;
		}

		int a[][] = new int[v.length + 1][W + 1];
		// initialize a[0][w] to 0
		for (int j = 1; j <= W; j++)
			a[0][j] = 0;

		for (int i = 1; i <= v.length; i++) {
			for (int w = 1; w <= W; w++) { // modulating with 0 doesn't make
											// sense,so start w with 1
				if (i - 2 >= 0 && !belongs[i - 2]) { // check belongs of ith-1
														// element
					a[i][w] = v[i - 1] % w; // start the subsequence afresh from
											// this element and modulus w
					belongs[i - 1] = true;
				} else {
					if (w - v[i - 1] > 0) {
						int op1 = (a[i - 1][w]) % w;
						int op2 = (a[i - 1][w - v[i - 1]] + v[i - 1]) % w;
						a[i][w] = max(op1, op2); // don't be confused by v[i-1]
													// here, it is ith element
													// only
						if (op1 >= op2) {

						}
					} else
						// case when w - v[i-1] <0
						a[i][w] = (a[i - 1][w]) % w;

				}
			}
		}
		System.out.println(a[v.length][W]);
	}

	public static int max(int a, int b) {
		return ((a > b) ? a : b);
	}
}
