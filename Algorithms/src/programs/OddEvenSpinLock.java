package programs;

import java.util.concurrent.Semaphore;

public class OddEvenSpinLock implements Runnable {
	static int count = 1;
	static volatile int tcount = 0; // Notice the volatile? If we don't have it,
									// visibilty is not guaranteed by JMM and a
									// deadlock can occur.
	boolean isEven;

	public OddEvenSpinLock(boolean isEven) {
		this.isEven = isEven;
	}

	public void run() {
		if (this.isEven == true) {
			while (count <= 10) {
				printEven();
			}
		} else {
			while (count <= 9)
				printOdd();
		}
	}

	public static void main(String[] args) {
		// OddEvenThreads oddEvenThreads = new OddEvenThreads();
		Thread t1 = new Thread(new OddEvenSpinLock(true));
		Thread t2 = new Thread(new OddEvenSpinLock(false));
		t1.start();
		t2.start();
	}

	// we can use 2 functions , but lets try with 1 first
	void printEven() {
		System.out.println("Thread even, tcount =" + (++tcount));
		while (count % 2 != 0)
			;
		System.out.println("Even" + count);
		count++;
	}

	void printOdd() {
		System.out.println("Thread odd, tcount =" + (++tcount));
		while (count % 2 == 0)
			;
		System.out.println("Odd" + count);
		count++;

	}
}
