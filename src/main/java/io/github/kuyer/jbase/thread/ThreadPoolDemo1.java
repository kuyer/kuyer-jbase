package io.github.kuyer.jbase.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {

	public static void main(String[] args) {
		Random r = new Random();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=0; i<30; i++) {
			int index = i;
			executor.execute(new Runnable() {
				@Override
				public void run() {
					int s = r.nextInt(5);
					System.out.println(Thread.currentThread().getName()+" executor task "+index+". sleep "+s+"s.");
					try {
						Thread.sleep(s*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}});
		}
		executor.shutdown();
	}

}
