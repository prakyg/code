package programs.test;

/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class CountInversions {
	ArrayList<Integer> a = new ArrayList<Integer>();

	public static void main(String[] args) throws java.lang.Exception {
		CountInversions obj = new CountInversions();
		obj.showArrayList();
		System.out.println("No of inversions = " + obj.countInvAndMergeSort(0, 9));
		// Note: max inversions can be n-1 + n-2 + .... 1 = (n-1)(n-1+1)/2 =
		// n(n-1)/2

		// obj.showArrayList();
	}

	CountInversions() {
		a.add(8);
		a.add(7);
		a.add(6);
		a.add(5);
		a.add(15);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		a.add(0);

	}

	private void showArrayList() {
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i) + "  ");
		}
	}

	private int countInvAndMergeSort(int l, int r) {
		System.out.println("left value " + l);
		System.out.println("right value " + r);
		if (r <= l) {
			return 0;
		}
		int m = (l + r) / 2;
		System.out.println("Mid is calculated as " + m);
		int x = countInvAndMergeSort(l, m);
		int y = countInvAndMergeSort(m + 1, r);
		int z = countSplitAndMerge(l, m, r);

		return x + y + z;
	}

	private int countSplitAndMerge(int l, int m, int r) {
		int i = l, j = m + 1, splitCount = 0;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		while (true) {
			if (a.get(i) < a.get(j)) {
				temp.add(a.get(i));
				i++;
				if (i > m) {
					while (j <= r) {
						temp.add(a.get(j));
						j++;
					}
					break;
				}
			} else {
				// COUNT inversions
				splitCount += m - i + 1;
				temp.add(a.get(j));
				j++;
				if (j > r) {
					while (i <= m) {
						temp.add(a.get(i));
						i++;
					}
					break;
				}
			} // end else

		} // end while
		System.out.println("Showing array temp: ");
		for (int i3 = 0; i3 < temp.size(); i3++) {
			System.out.print(temp.get(i3) + "  ");

		}
		System.out.println();
		System.out.println("l =" + l + "  r=" + r + " m=" + m);
		// System.arraycopy(a, l, temp, 0, r-l);
		for (int i1 = l, i2 = 0; i2 < r - l + 1; i1++, i2++) {
			a.set(i1, temp.get(i2));
		}
		this.showArrayList();
		return splitCount;
	}
}
