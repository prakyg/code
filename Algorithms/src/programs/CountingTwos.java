package programs;

public class CountingTwos {
	public CountingTwos() {
		super();
	}

	public static void main(String[] args) {
		System.out.println(bruteForce(3) + " " + getEfficiently(3));
		System.out.println(bruteForce(13) + " " + getEfficiently(13));
		System.out.println(bruteForce(33) + " " + getEfficiently(33));
		System.out.println(bruteForce(1000) + " " + getEfficiently(1000));
		System.out.println(bruteForce(925) + " " + getEfficiently(925));
		System.out.println(bruteForce(125) + " " + getEfficiently(125));
		System.out.println(bruteForce(1225) + " " + getEfficiently(1225));
		System.out.println(bruteForce(319238915) + " " + getEfficiently(319238915));
	}

	public static int bruteForce(int x) {
		int count = 0;
		for (int i = 0; i <= x; i++) {
			count += getTwosIn_i(i);
		}
		return count;
	}

	private static int getTwosIn_i(int i) {
		int count = 0;
		while (i > 0) {
			int digit = i % 10;
			i = i / 10;
			if (digit == 2)
				count++;
		}
		return count;
	}

	public static int getEfficiently(int number) {
		int x = number;
		int count = 0;
		int numDigits = getNumberOfDigits(x);
		int rightToDigit = 0;
		for (int i = 0; i < numDigits; i++) {
			// init
			int digit = x % 10;
			int leftToDigit = x / 10;

			// System.out.println("Examining digit:"+digit);
			if (digit > 2)
				count += (leftToDigit + 1) * (Math.pow(10, i));
			else if (digit == 2)
				count += (leftToDigit) * (Math.pow(10, i)) + rightToDigit + 1;
			else {
				count += (leftToDigit) * (Math.pow(10, i));
			}

			x = x / 10;
			rightToDigit = rightToDigit + (int) (Math.pow(10, i) * digit);
		}

		return count;
	}

	private static int getNumberOfDigits(int x) {
		if (x == 0)
			return 1;
		int count = 0;
		while (x > 0) {
			x = x / 10;
			count++;
		}
		// System.out.println("Number of digits being returned = "+count);
		return count;
	}
}
