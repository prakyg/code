package programs.tricky;

public class RecurseLikeHell {
	public RecurseLikeHell() {
		super();
	}

	public static void main(String[] args) {
		System.out.println(rec(3, 2));
	}

	public static int rec(int m, int n) {
		if (m == 0)
			return n + 1;
		else if (n == 0)
			return rec(m - 1, 1);
		else
			return rec(m - 1, rec(m, n - 1));
	}
}
