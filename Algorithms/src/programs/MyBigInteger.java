package programs;

import java.math.BigInteger;

import java.util.Arrays;

/* Notes on endianness
 * When looking at the value in a human readable format, such as 0x008237b1, the leftmost byte (0x00 here) is called the �most significant byte�. Numbers here contribute the most to the magnitude of the value. The rightmost byte (0xb1 here), is called the �least significant� byte
 * Big endian (MSB at smallest memory address) 00 82 37 b1
 * little endian (MSB at largest memory address) b1 37 82 00
 */
public class MyBigInteger {
	// We need to specify a base. Base should be as big as possible.
	// Since we need to multiply numbers as well, base should be half of max
	// possible
	// Max = long , half = int
	// Max range of unsigned int = 32 bits will be 4.x billion
	// Let's take 1 billion to stay in decimal system
	// Note that : 1 billion = 10^9 and can represented in 30 bits
	final static int base = 1000000000; // 10^9

	// Note that in base 10, a single digit can be 0-9, base 2 (0,1), base 16 =
	// 0-F
	// => Base 1 billion can have max value = 1 billion-1
	final static int base_digits = 9; // number of digits in max possible value
										// of int (i.e. 1 Billion -1)
	// So to represent each digit, we will need an int
	// to represent the full number , int[]
	private int[] digits; // size of this array: stay tuned. Max size is limited
							// to an int in Java
	private final int size; // Will be calculated in the constructor depending
							// on number

	// Constructor will need to accept a String, else how will you pass a number
	// greater than Long.MAX_VALUE
	public MyBigInteger(String number) {
		// How to construct the number?
		// 12345 (base 10)= 1x10^4 + 2x10^3 + 3x10^2 + 4x10^1 + 5x10^0
		// Similary in base 1 billion, take the first 9 digits => convert them
		// into int and store at digits[0]
		// Then take the next 9 , and put them at digits[1]
		int numberOfDigitsDecimal = number.length();
		int numberOfDigits1BillionBase = ((numberOfDigitsDecimal - 1) / base_digits) + 1;
		size = numberOfDigits1BillionBase;
		digits = new int[size];
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < numberOfDigits1BillionBase; i++) {
			// get the digits starting from 0 to index 8
			// in general - 9*i to 9*(i+1)-1
			// substring method of string doesn't include the end index
			endIndex = numberOfDigitsDecimal - 9 * i;// startIndex = 9*i;
			startIndex = numberOfDigitsDecimal - 9 * (i + 1);// endIndex =
																// 9*(i+1);
			if (startIndex < 0)
				startIndex = 0;
			String intPart = number.substring(startIndex, endIndex);
			// Parse this String into an int
			digits[i] = Integer.parseInt(intPart, 10);
		}
	}

	private MyBigInteger(int[] digits, int size) {
		this.digits = Arrays.copyOf(digits, size);
		this.size = size;
	}

	public MyBigInteger add(MyBigInteger x) {
		// adding 2 2^30 bit integers can't cause overflow. But it can produce a
		// carry at 2^31th bit.
		int bigSize, smallsize;
		MyBigInteger bigRef;
		if (this.size > x.size) {
			bigSize = this.size;
			smallsize = x.size;
			bigRef = this;
		} else {
			bigSize = x.size;
			smallsize = this.size;
			bigRef = x;
		}

		int sumDigits[] = new int[bigSize + 1]; // Max + 1 because 9+9 = 18
												// possible
		int carry = 0;
		int i;
		for (i = 0; i < smallsize; i++) {
			sumDigits[i] = this.digits[i] + x.digits[i] + carry;
			carry = sumDigits[i] / base;
			// clear the carry
			sumDigits[i] = sumDigits[i] % (base);
		}
		while (i < bigSize) {
			sumDigits[i] = bigRef.digits[i] + carry;
			carry = sumDigits[i] / base;// (newDigits[i]>base-1)?1:0;
			sumDigits[i] = sumDigits[i] % (base);
			i++;
		}
		if (carry == 1) { // final digit carry
			bigSize++;
			sumDigits[bigSize - 1] = 1;
		}
		return new MyBigInteger(sumDigits, bigSize);
	}

	// lets override the toString() to make the number printable
	@Override
	public String toString() {
		StringBuilder number = new StringBuilder("");

		for (int i = size - 1; i >= 0; i--) {
			number = number.append(digits[i]); // StringBuilder's append method
												// accepts int , how very
												// convenient for us
		}
		return number.toString();
	}

	public static void main(String[] args) {
		// MyBigInteger myBigInteger = new MyBigInteger();

		// StringBuilder x = new StringBuilder("");

		// System.out.println(x);
		// MyBigInteger b1 = new
		// MyBigInteger("9123456789123456789123456789123456789123456789");
		// MyBigInteger b2 = new MyBigInteger("1");
		// MyBigInteger b3 = b1.add(b2);
		MyBigInteger b1 = new MyBigInteger("99999999999999999");
		MyBigInteger b2 = new MyBigInteger("2");
		MyBigInteger b3 = b1.add(b2);
		// MyBigInteger b1 = new MyBigInteger("987654321");
		// MyBigInteger b2 = new MyBigInteger("987654321");
		// MyBigInteger b3 = b1.add(b2);
		System.out.println(b3);

	}

}
