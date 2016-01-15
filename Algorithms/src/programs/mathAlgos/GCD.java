package programs.mathAlgos;

//https://en.wikipedia.org/wiki/Euclidean_algorithm
public class GCD {
	public GCD() {
		super();
	}

	public static void main(String[] args) {
		System.out.println("GCD of 10 , 15 " + getGCD(10, 15));
		System.out.println("GCD of 100, 15 " + getGCD(100, 15));
		System.out.println("GCD of 2 , 15 " + getGCD(2, 15));
		System.out.println("GCD of 63, 18 " + getGCD(63, 18));
	}

	public static int getGCD(int a, int b) {
		if (a < b) {
			int temp = b;
			b = a;
			a = temp;
		}
		int r = 0;
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
