package programs;

import java.util.concurrent.Semaphore;

public class OddEvenSemaphore {
	public OddEvenSemaphore() {
		super();
	}

	public static void main(String[] args) {
		// OddEvenSemaphore oddEvenSemaphore = new OddEvenSemaphore();
		Semaphore s = new Semaphore(1);
		Thread odd = new Thread(new Odd(s));
		Thread even = new Thread(new Even(s));
		odd.start();
		even.start();
	}

	public static class Odd implements Runnable {
		Semaphore s;

		Odd(Semaphore s) {
			this.s = s;
		}

		int count = 1;

		public void run() {

			while (count <= 9) {
				try {
					s.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("count" + count);
				count = count + 2;
				s.release();
			}
		}
	}

	public static class Even implements Runnable {
		Even(Semaphore s) {
			this.s = s;
		}

		Semaphore s;
		int count = 2;

		public void run() {
			while (count <= 10) {
				try {
					s.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("count" + count);
				count = count + 2;
				s.release();
			}
		}
	}
}
