package programs.dynamic;
//Number of ways to reach sum N with coins of various denominations, order doesn't matter

public class CoinsSum {
	int[] coins;
	int sum;

	public CoinsSum(int[] c, int s) {
		coins = c;
		sum = s;
	}

	public static void main(String[] args) {
		CoinsSum coinsSum = new CoinsSum(new int[] { 1, 2 }, 1);
		coinsSum.showCombinations();
	}

	void showCombinations() {
		int[][] sumCombi = new int[coins.length][sum + 1];
		// initialize
		for (int s = 0; s <= sum; s++) {
			sumCombi[0][s] = (s % coins[0] == 0) ? 1 : 0; // 1st coin can make
															// any sum if it is
															// a factor of it
		}
		for (int c = 0; c < coins.length; c++) {
			sumCombi[c][0] = 1; // for sum 0 no combination
		}

		for (int c = 1; c < coins.length; c++) { // calculate for each sum
			for (int s = 1; s <= sum; s++) { // calculate for each coin upto c
				sumCombi[c][s] = 0;
				for (int j = 0; s - j * coins[c] >= 0; j++)
					sumCombi[c][s] = sumCombi[c][s] + sumCombi[c - 1][s - j * coins[c]];
			}
		}
		for (int i = 0; i < coins.length; i++) {
			System.out.println("coins included upto " + (i + 1));
			for (int j = 1; j <= sum; j++) {
				System.out.print(sumCombi[i][j] + " "); // for sum 0 no
														// combination
			}
			System.out.println();
		}
	}

}
