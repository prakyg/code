package programs;

public class MultithreadingBasics implements Runnable {
	String s;
	int count = 1;

	public MultithreadingBasics(String s) {
		this.s = s;
	}

	synchronized static void test(String s) {

		System.out.println(s);
	}

	synchronized static void testStatic(String s) {

		System.out.println(s);
	}

	synchronized void testCount() {

		System.out.println("count is " + count);
	}

	public void run() {
		// synchronized (this){
		// count++;
		while (true)
			testCount();
		// test(s);
		// testStatic(s)
		// }
	}

	// Note that: since lock is acquired on the same object, if both read and
	// write are invoked on the same object for 2 different threads
	// only 1 will execute at a time
	synchronized static void read() {
		System.out.println("Reading");
	}

	synchronized static void write() {
		System.out.println("wrting");
	}

	public static void main(String[] args) {

		MultithreadingBasics m1 = new MultithreadingBasics("Thread 1 ");
		MultithreadingBasics m2 = new MultithreadingBasics("Thread 2 ");
		// This example shows that synchrozid blocks access to 2 threads only if
		// they refer to the same object
		// This is because the lock is obtained on the same object
		Thread t = new Thread(m1);
		Thread t2 = new Thread(m2);
		t.start();
		t2.start();
		// In case of static method, nothing changes. Since the calling objects
		// are still the same

		Thread t3 = new Thread(m1);
	}
}
