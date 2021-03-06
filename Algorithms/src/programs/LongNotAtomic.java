package programs;

/*
 * One thread constantly tries to assign 0 to test while the other tries to assign -1. Eventually you'll end up with a number that's either 0b1111111111111111111111111111111100000000000000000000000000000000
or
0b0000000000000000000000000000000011111111111111111111111111111111.
(Assuming you aren't on a 64 bit JVM. Most, if not all, 64 bit JVMs will actually do atomic assignment for longs and doubles.)
 */
public class LongNotAtomic implements Runnable {
	private static long test = 0;

	private final long val;

	public LongNotAtomic(long val) {
		this.val = val;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			test = val;
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new LongNotAtomic(-1));
		Thread t2 = new Thread(new LongNotAtomic(0));

		System.out.println(Long.toBinaryString(-1));
		System.out.println(pad(Long.toBinaryString(0), 64));

		t1.start();
		t2.start();

		long val;
		while ((val = test) == -1 || val == 0) {
		}

		System.out.println(pad(Long.toBinaryString(val), 64));
		System.out.println(val);

		t1.interrupt();
		t2.interrupt();
	}

	// prepend 0s to the string to make it the target length
	private static String pad(String s, int targetLength) {
		int n = targetLength - s.length();
		for (int x = 0; x < n; x++) {
			s = "0" + s;
		}
		return s;
	}
}