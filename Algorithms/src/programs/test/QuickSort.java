package programs.test;

public class QuickSort {
	public QuickSort() {
		A = new int[] { 4, 1, 3, 2, 5, 7, 10, 9 };
	}

	int[] A;

	public static void main(String[] args) throws java.lang.Exception {
		QuickSort obj = new QuickSort();
		obj.showArrayList();
		obj.sort(0, 7);
		obj.showArrayList();
	}

	private void showArrayList() {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + "  ");

		}
		System.out.println();
	}

	private void sort(int l, int r) {
		System.out.println("l= " + l + " r= " + r);
		// Return condition
		if (l >= r) {
			return;
		}
		// pick the pivot as the first element of the array.
		int p = l;
		int i = l;
		// int j= l+1;
		/*
		 * ________________________________ |p| <p | >p | unsorted | p i k
		 */

		for (int k = i + 1; k <= r; k++) {
			if (A[k] < A[p]) {
				// swap first element of >p part with this element and move i, k
				// forward
				swap(i + 1, k);
				i++;
			}
			// else K moves forward, i remains the same
		}
		// NOTE: at the end, pivot should point to the correct position of the
		// pivot element
		swap(p, i);
		p = i;
		// Rearrange the array in terms of elements less than pivot (<p) and
		// more than pivot (>p)

		// Recursively invoke sort on left and right parts w.r.t. pivot
		sort(l, p - 1);
		sort(p + 1, r);
	}

	private void swap(int x, int y) {
		int temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}
}
