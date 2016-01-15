package programs.dynamic;

public class MinCoinsToMakeSum {

	public static void main(String[] args) throws java.lang.Exception {
		coins(new int[] { 1, 2 }, 10);

	}

	public static void coins(int[] coins, int n) {
		int m = coins.length;
		int[][] sum = new int[m][n + 1];

		// init
		for (int i = 0; i < m; i++)
			sum[i][0] = 0;
		for (int s = 1; s < n + 1; s++) {
			sum[0][s] = (s % coins[0] == 0 ? s / coins[0] : 0);
		}
		//
		for (int i = 1; i < m; i++) {
			for (int s = 1; s < n + 1; s++) {
				if (s - coins[i] >= 0)
					sum[i][s] = min(sum[i][s - coins[i]] + 1, sum[i - 1][s]);
				else
					sum[i][s] = sum[i - 1][s];
			}
		}
		for (int i = 0; i < m; i++) {
			for (int s = 1; s < n + 1; s++) {
				System.out.print(sum[i][s] + " ");
			}
			System.out.println();
		}
	}

	public static int min(int a, int b) {
		return (a < b ? a : b);
	}
}
