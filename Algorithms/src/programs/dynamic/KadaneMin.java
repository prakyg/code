package programs.dynamic;

import java.util.Arrays;

public class KadaneMin {
	int[] a;
	int n;

	public KadaneMin(int[] a) {
		this.a = a;
		n = a.length;
	}

	public static void main(String[] args) {
		KadaneMin k = new KadaneMin(new int[] { 1, 2, 3, 5, -5, 7, -100, 4 });
		System.out.println(k.getMinSum());
		KadaneMin k2 = new KadaneMin(new int[] { -16, -21, -3, -5, -5, -100 });
		System.out.println(k2.getMinSum());
	}

	int getMinSum() {
		int globalMin = a[0];

		for (int i = 1; i < n; i++) {
			if (a[i - 1] > 0) {
				a[i] = a[i - 1];
			} else {
				a[i] = a[i - 1] + a[i];
			}
			if (globalMin > a[i]) {
				globalMin = a[i];
			}
		}
		System.out.println(Arrays.toString(a));
		return globalMin;

	}
}