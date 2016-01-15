package programs.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class RunnCallExample {
	public RunnCallExample() {
		super();
	}

	public static void main(String[] args) throws InterruptedException {
		RunnCallExample runnCallExample = new RunnCallExample();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("I am running, wooHoooo!! ");
			}
		};
		Thread t = new Thread(r); // calling thread's constructor;
		t.start();

		Callable<Integer> c = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("I am getting called, heeHaw!!! ");
				return 1;
			}
		};
		// Thread t1 = new Thread(c); won't work
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future f = es.submit(c);
		// alternative
		FutureTask<Integer> f1 = new FutureTask<Integer>(c);
		// VALID but can't be started with a thread !!!
		// Future<Integer> f2 = new FutureTask<Integer> (c);
		Thread t1 = new Thread(f1);
		t1.start();
		// System.out.println("value returned is "+f1.get());
	}
}
