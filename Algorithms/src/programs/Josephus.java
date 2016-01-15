package programs;

public class Josephus {
	public Josephus() {
		super();
	}

	public static void main(String[] args) {
		Josephus j = new Josephus();
		System.out.println("Final value:" + j.josephusRec(16, 2));
	}

	int josephusRec(int n, int k) {
		if (n == 1)
			return 1;
		else {
			/*
			 * The position returned by josephus(n - 1, k) is adjusted because
			 * the recursive call josephus(n - 1, k) considers the original
			 * position k%n + 1 as position 1
			 */
			int temp = (josephusRec(n - 1, k) + k - 1) % n + 1;
			System.out.println("value: " + temp);
			return temp;
		}
	}
}
