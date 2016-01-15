package programs.dynamic;

public class LIS {
	int[] elem;
	int[] A;

	public LIS(int[] elem) {
		if (elem.length == 0) {
			throw new IllegalArgumentException("Array can not be empty");
		}
		this.elem = elem;
		A = new int[elem.length];
	}

	public int findLIS() {
		int n = elem.length;

		// Initialization
		A[0] = 1;

		// recurrence
		for (int i = 1; i < n; i++) {
			A[i] = getMaximumSubsequenceLessThanElemi(i);
		}

		// Return max length by sequential search over A[i]
		int max = A[0];
		for (int i = 1; i < n; i++) {
			if (A[i] > max) {
				max = A[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		// Null check
		// LIS obj1 = new LIS(null);
		// System.out.println(obj1.findLIS());

		// empty array
		// LIS obj1 = new LIS(new int[]{});
		// System.out.println(obj1.findLIS());

		LIS obj = new LIS(new int[] { 7, 8, 1, 2, 6, 9 });
		System.out.println(obj.findLIS());
	}

	private int getMaximumSubsequenceLessThanElemi(int x) {
		// This method will iterate from i=0 to i=x-1
		// It will find the maximum number stored in A[] (sequence length) which
		// is less than elem[x]
		int max = 0;
		for (int i = 0; i < x; i++) {
			if (elem[i] < elem[x] && A[i] > max) {
				max = A[i];
			}
		}
		return max + 1;
	}
}
