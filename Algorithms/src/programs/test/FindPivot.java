package programs.test;

//We are using binary search to find the pivot so log(n) time
public class FindPivot {
	int[] a;

	public FindPivot(int[] a) {
		this.a = a;
	}

	public static void main(String[] args) throws java.lang.Exception {
		FindPivot f = new FindPivot(new int[] { 5, 6, 7, 8, 9, 0, 1, 2, 3, 4 });
		System.out.println(f.getPivotElement());
	}

	int getPivotElement() {
		return getPivotElement(0, a.length - 1);
	}

	private int getPivotElement(int l, int r) {
		if (r - l <= 1) {
			if (r - l == 0) {
				return l;
			} else if (a[l] > a[r])
				return r;
			else if (a[l] > a[r])
				return l;
		}
		int mid = (l + r) / 2;
		if (a[mid] > a[r]) {
			return getPivotElement(mid, r);
		} else if (a[mid] < a[l]) {
			return getPivotElement(l, mid);
		}
		return -1; // in case both the if conditions fail , then array is not
					// rotated.
	}
}
