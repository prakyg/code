package programs.test;

public class SquareRoot {
	public SquareRoot() {
		super();
	}

	public static double squareRoot(double S) {
		double x = S;
		System.out.println("initial approx: " + x);
		double e = 0.0000000000001d;
		while (x - (S / x) > e) {
			x = 0.5 * (x + (S / x));
			System.out.println("New Approximation :" + x);
		}
		return x;
	}

	public static void main(String[] args) {
		double temp = squareRoot(3);
		System.out.println("Trying root* root: " + (temp * temp));
	}

}
