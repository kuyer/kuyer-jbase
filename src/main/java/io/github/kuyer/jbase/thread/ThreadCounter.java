package io.github.kuyer.jbase.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter {
	
	private static volatile int number = 0;
	private static AtomicInteger value = new AtomicInteger(0);
	
	private static CountDownLatch latch = new CountDownLatch(30);

	public static void main(String[] args) throws Exception {
		for(int i=0; i<30; i++) {
			new Thread() {
				@Override
				public void run() {
					for(int j=0; j<10000; j++) {
						number++;
						value.incrementAndGet();
					}
					latch.countDown();
				}}.start();
		}
		latch.await();
		System.out.println("number: "+number);
		System.out.println("value: "+value.get());
	}

}
