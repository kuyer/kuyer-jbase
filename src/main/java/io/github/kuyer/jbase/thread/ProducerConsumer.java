package io.github.kuyer.jbase.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {
	
	/**
	 * 共享对象的监视器
	 */
	private static Object monitor = new Object();
	/**
	 * 生产的数量
	 */
	private static volatile AtomicInteger number = new AtomicInteger();
	/**
	 * 每批生产数量
	 */
	private static Integer size = 3;
	/**
	 * 总生产量
	 */
	private static volatile AtomicInteger count = new AtomicInteger();
	/**
	 * 计划总生产量
	 */
	private static Integer total = 20;
	
	static class Producer implements Runnable {
		@Override
		public void run() {
			while(count.get() < total) {
				synchronized (monitor) {
					if(number.get() == 0) {
						System.out.println(Thread.currentThread().getName()+" produce start.");
					}
					while(number.get() < size) {
						number.incrementAndGet();
					}
					monitor.notify();
					if(number.get() >= size) {
						System.out.println(Thread.currentThread().getName()+" produce stop.");
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println(Thread.currentThread().getName() + " produce finish. count: "+count);
		}
	}
	
	static class Consumer implements Runnable {
		@Override
		public void run() {
			while(count.get() < total) {
				synchronized (monitor) {
					while(number.get() < size) {
						System.out.println(Thread.currentThread().getName()+" consume wait. number: "+number);
						try {
							monitor.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					number.addAndGet(-10);
					count.addAndGet(10);
					System.out.println(Thread.currentThread().getName() + " consume.");
				}
			}
			System.out.println(Thread.currentThread().getName() + " consume finish. count: "+count);
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Producer(), "Producer");
		Thread t2 = new Thread(new Consumer(), "Consumer");
		t1.start();
		t2.start();
	}

}
