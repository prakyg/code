package programs.dp;

import java.util.Arrays;

public class SequenceAlignment {
	public SequenceAlignment() {
		super();
	}

	public static float seqAlign(String x, String y) {
		int m = x.length();
		int n = y.length();

		// Let P be the array, where P[i][j] contains penalty for strings x of
		// length i and string y of length j
		float[][] P = new float[m + 1][n + 1];

		float pGap = 0.5f;
		float pMatch = 0;
		float pMismatch = 1.0f;

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					P[i][j] = pGap * i + pGap * j;
					continue;
				}
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					P[i][j] = P[i - 1][j - 1] + pMatch;
				} else {
					P[i][j] = Math.min(P[i - 1][j] + pGap, P[i][j - 1] + pGap);
				}
			}
		}

		return P[m][n];
	}

	public static float LCS(String x, String y) {
		int m = x.length();
		int n = y.length();

		// Let P be the array, where P[i][j] contains penalty for strings x of
		// length i and string y of length j
		int[][] L = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					L[i][j] = 0;
					continue;
				}

				// debug
				System.out.println("Trying to match " + x.charAt(i - 1) + " with " + y.charAt(j - 1));
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					L[i][j] = L[i - 1][j - 1] + 1;
				} else {
					L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
				}
			}
		}
		// System.out.println(Arrays.toString(L));

		// Reconstruction Algorithm
		int index = L[m][n];
		char[] lcsChar = new char[index];
		for (int i = m, j = n; i >= 1 && j >= 1;) {
			if (x.charAt(i - 1) == y.charAt(j - 1)) { // means we came here from
														// i-1, j-1
				lcsChar[index - 1] = x.charAt(i - 1);
				i--;
				j--;
				index--;
			} else {
				if (L[i - 1][j] > L[i][j - 1]) {
					i--;
				} else {
					j--;
				}
			}
		}
		String lcsString = new String(lcsChar);
		System.out.println("Longest common subsequence is: " + lcsString);
		return L[m][n];
	}

	public static void main(String[] args) {
		SequenceAlignment sequenceAlignment = new SequenceAlignment();
		System.out.println("Penalty: " + seqAlign("1Heallo", "Hello1"));
		System.out.println("LCS: " + LCS("xHeallo", "Hellox"));
	}
}
