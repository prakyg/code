package programs;

public class OddEvenSynchClassLock {
	static volatile int count = 1;

	// boolean isEven;
	public OddEvenSynchClassLock() {
		super();
	}

	public static void main(String[] args) {
		OddEvenSynchClassLock o = new OddEvenSynchClassLock();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while (true)
					o.printEven();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				while (true)
					o.printOdd();
			}
		});
		t1.start();
		t2.start();
	}

	synchronized void printEven() {

		while (count % 2 != 0) {
			try {
				// the thread will release the lock if it goes to waiting()
				// state
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Even" + count);

		count++;

		notifyAll();

	}

	synchronized void printOdd() {

		while (count % 2 == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Odd" + count);
		count++;
		notifyAll();
	}
}
