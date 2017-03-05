package io.github.kuyer.jbase.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockPractice {
	
	private static int a = 0;
	private static Lock lock = new ReentrantLock();
	
	private static void increateBySynchronized() {
		a = 0;
		for(int i=0; i<1000; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized(LockPractice.class) {
						a ++;
					}
				}});
			t.start();
		}
		while(Thread.activeCount()>1) {
			Thread.yield();
		}
		System.out.println("synchronized: "+a);
	}
	
	private static void increateByLock() {
		a = 0;
		for(int i=0; i<1000; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					try {
						a ++;
					} finally {
						lock.unlock();
					}
				}});
			t.start();
		}
		while(Thread.activeCount()>1) {
			Thread.yield();
		}
		System.out.println("lock: "+a);
	}

	public static void main(String[] args) {
		increateBySynchronized();
		increateByLock();
	}

}
