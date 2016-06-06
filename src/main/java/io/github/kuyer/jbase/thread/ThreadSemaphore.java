package io.github.kuyer.jbase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// http://www.cnblogs.com/leesf456/default.html?page=2
// http://www.cnblogs.com/gaoxing/p/4348719.html
public class ThreadSemaphore {

	public static void main(String[] args) {
		
		int maxAvailable = 10;
		Semaphore available = new Semaphore(maxAvailable, true);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i=0; i<30; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						available.acquire();
						System.out.println("save date");
						available.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}});
			executor.shutdown();
		}
	}

}
