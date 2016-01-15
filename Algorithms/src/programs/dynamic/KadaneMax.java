package programs.dynamic;

import java.util.Arrays;

public class KadaneMax {
	int[] a;
	int n;

	public KadaneMax(int[] a) {
		this.a = a;
		n = a.length;
	}

	public static void main(String[] args) {
		KadaneMax k = new KadaneMax(new int[] { 1, 2, 3, 5, -5, 7, -100, 4 });
		System.out.println(k.getMaxSum());
		KadaneMax k2 = new KadaneMax(new int[] { -16, -21, -3, -5, -5, -100 });
		System.out.println(k2.getMaxSum());
		KadaneMax k3 = new KadaneMax(new int[] { -16, -21, -3, -5, -5, -100 });
		System.out.println(k3.getMaxNegativeSum());
	}

	int getMaxSum() {
		int globalMax = a[0];
		a[0] = a[0] < 0 ? 0 : a[0];
		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] + a[i];
			if (a[i] < 0)
				a[i] = 0;
			if (globalMax < a[i]) {
				globalMax = a[i];
			}
		}
		System.out.println(Arrays.toString(a));
		return globalMax;

	}

	int getMaxNegativeSum() {
		int globalMax = a[0];

		for (int i = 1; i < n; i++) {
			if (a[i - 1] < 0) {
				// a[i] = a[i];
			} else {
				a[i] = a[i - 1] + a[i];
			}
			if (globalMax < a[i]) {
				globalMax = a[i];
			}
		}
		System.out.println(Arrays.toString(a));
		return globalMax;

	}
}
