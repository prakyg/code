package programs;

public class OddEvenSingleMethod implements Runnable {
	boolean isEven;
	static Object obj1 = new Object();
	static int count = 1;

	public OddEvenSingleMethod(boolean isEven) {
		this.isEven = isEven;
	}

	public void run() {
		while (count <= 10)
			print();
	}

	public static void main(String[] args) {
		OddEvenSingleMethod o = new OddEvenSingleMethod(true);
		OddEvenSingleMethod o2 = new OddEvenSingleMethod(false);
		Thread t = new Thread(o);
		Thread t2 = new Thread(o2);
		t.start();
		t2.start();

	}

	void print() {
		if (!isEven) {
			synchronized (obj1) {
				if (count % 2 == 0) {
					try {
						obj1.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Odd" + count);
				count++;
				obj1.notifyAll();
			}
		} else {
			synchronized (obj1) {
				if (count % 2 != 0) {
					try {
						obj1.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Even" + count);
				count++;
				obj1.notifyAll();
			}
		}
	}

}
