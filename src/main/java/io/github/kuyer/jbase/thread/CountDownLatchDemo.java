package io.github.kuyer.jbase.thread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws Exception {
		CountDownLatch cdl = new CountDownLatch(5);
		for(int i=0; i<5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+": "+new Date());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					cdl.countDown();
				}}).start();
		}
		cdl.await();
		System.out.println(Thread.currentThread().getName()+" is over.");
	}

}
