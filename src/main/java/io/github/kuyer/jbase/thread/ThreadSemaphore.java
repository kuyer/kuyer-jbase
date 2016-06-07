package io.github.kuyer.jbase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// http://www.cnblogs.com/leesf456/default.html?page=2
// http://www.cnblogs.com/gaoxing/p/4348719.html
public class ThreadSemaphore {

	public static void main(String[] args) {
		
		int maxAvailable = 5;
		Semaphore available = new Semaphore(maxAvailable, true);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=0; i<8; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						available.acquire();
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName()+" acquire count: 1");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						available.release();
						System.out.println(Thread.currentThread().getName()+" release count: 1");
					}
				}});
		}
		executor.shutdown();
		//System.in.read();
	}

}
