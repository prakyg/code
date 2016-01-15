package programs;

public class SetBitCounter {
	public SetBitCounter() {
		super();
	}

	public static void main(String[] args) {
		SetBitCounter s = new SetBitCounter();
		int x = 5;
		System.out.println(s.getSetBitsFromOneToN(x));
		System.out.println(s.getMySetBitsFromOneToN(x));
	}

	int getSetBitsFromOneToN(int N) {
		int two = 2, ans = 0;
		int n = N;
		while (n != 0) {
			ans += (N / two) * (two >> 1);
			if ((N & (two - 1)) > (two >> 1) - 1)
				ans += (N & (two - 1)) - (two >> 1) + 1;
			two <<= 1;
			n >>= 1;
		}
		return ans;
	}

	int getMySetBitsFromOneToN(int N) {
		// int n = N;
		// int half = (N+1)/2;
		// int count = 0;
		// while (n!=0){
		// count +=half;
		// n = n>>1;
		// if (n == 0){
		// //this is the last set bit
		// //revert earlier changes
		// //count -=half;
		// //Add only relevant set bits, so in case of 4 add only 1 bit while in
		// case of 5,
		// }
		// }
		// return count;
		int two = 2, ans = 0;
		int n = N;
		while (n != 0) {
			ans += (N / two) * (two >> 1);
			// if((N&(two-1)) > (two>>1)-1) ans += (N&(two-1)) - (two>>1)+1;
			two <<= 1;
			n >>= 1;
		}
		return ans;
	}
}
