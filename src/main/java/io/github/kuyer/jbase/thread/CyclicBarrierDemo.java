package io.github.kuyer.jbase.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		Random random = new Random();
		CyclicBarrier barrier = new CyclicBarrier(5);
		for(int i=0; i<5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int secs = random.nextInt(5);
					System.out.println(Thread.currentThread().getName()+" start at: "+new Date()+"; will sleep "+secs+"s.");
					try {
						Thread.sleep(secs*1000);
						barrier.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" end at: "+new Date()+"; sleeped "+secs+"s.");
				}}).start();
		}
	}

}
