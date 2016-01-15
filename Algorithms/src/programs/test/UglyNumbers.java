package programs.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UglyNumbers {
	public UglyNumbers() {
		super();
	}

	public static void main(String[] args) throws java.lang.Exception {
		UglyNumbers u = new UglyNumbers();
		u.ugly(1000);
	}

	// divisible by 2,3,5 (any or all) only
	void ugly(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		int i2 = 0, i3 = 0, i5 = 0;

		/*
		 * i2, i3 and i5 mark the minimum index in the array: ugly[] which are
		 * the candidates for the next ugly number after being multiplied by the
		 * corresponnding factor (i2 for 2, i3 for 3 and i5 for 5). Therefore,
		 * initially i2 = i3 = i5 = 0 because the next candidate for ugly number
		 * (i.e, ugly[1]) will be the min of (ugly[0]*2, ugly[0]*3, ugly[0]*5).
		 * 
		 * Now, once the min is found to be 2, algo increments i2 by 1 to
		 * suggest that the candidate for next ugly number which must be
		 * multiplied by 2 must be ugly[1] , because ugly[0] has already been
		 * considered for multiplication by 2. At this point i3 and i5 still
		 * point to zero coz ugly[0]*3 and ugly[0]*5 are still in race for the
		 * next ugly number.
		 */

		for (int i = 1; i != n; i++) {
			int next2 = ugly[i2] * 2, next3 = ugly[i3] * 3, next5 = ugly[i5] * 5;
			int min = Math.min(next2, Math.min(next3, next5));
			ugly[i] = min;
			if (min == next2)
				i2++;
			if (min == next3)
				i3++;
			if (min == next5)
				i5++;
		}

		System.out.println(Arrays.toString(ugly));
	}

}
