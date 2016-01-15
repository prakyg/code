package programs;

import java.util.concurrent.Semaphore;

public class OddEvenSynchronized implements Runnable {
	static Object obj1 = new Object();
	static volatile int count = 1;
	boolean isEven;

	public OddEvenSynchronized(boolean isEven) {
		this.isEven = isEven;
	}

	public void run() {
		while (count <= 10) {
			if (this.isEven == true) {

				printEven();
			} else {
				printOdd();
			}
		}
	}

	public static void main(String[] args) {
		// OddEvenThreads oddEvenThreads = new OddEvenThreads();
		Thread t1 = new Thread(new OddEvenSynchronized(true));
		Thread t2 = new Thread(new OddEvenSynchronized(false));
		t1.start();
		t2.start();
	}

	// we can use 2 functions , but lets try with 1 first
	void printEven() {
		synchronized (obj1) {
			while (count % 2 != 0) {
				try {
					obj1.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Even" + count);

		count++;
		synchronized (obj1) {
			obj1.notifyAll();
		}
	}

	void printOdd() {
		synchronized (obj1) {
			while (count % 2 == 0) {
				try {
					obj1.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Odd" + count);
		count++;
		synchronized (obj1) {
			obj1.notifyAll();
		}

	}
}
