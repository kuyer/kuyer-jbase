package io.github.kuyer.jbase.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		Random random = new Random();
		for(int i=0; i<30; i++) {
			final int index = i+1;
			new Thread() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(index+": " +Thread.currentThread().getName()+" execute acquire.");
						Thread.sleep(random.nextInt(5) * 1000);
						semaphore.release();
						System.out.println(index+": " +Thread.currentThread().getName()+" execute release.");
					} catch (Exception e) {
						System.out.println(index+": " +Thread.currentThread().getName()+" execute error.");
					}
				}}.start();
		}
	}

}
