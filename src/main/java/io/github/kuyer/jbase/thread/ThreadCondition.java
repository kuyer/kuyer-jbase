package io.github.kuyer.jbase.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition 在线程中启动了线程之间的协调作用
 * @author rory.zhang
 */
public class ThreadCondition {
	
	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					System.out.println(Thread.currentThread().getName()+" await.");
					condition.await();
					System.out.println(Thread.currentThread().getName()+" get lock.");
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}});
		t1.setName("thread-01");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					condition.signal();
					System.out.println(Thread.currentThread().getName()+" signal.");
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
				System.out.println(Thread.currentThread().getName()+" release lock.");
			}});
		t2.setName("thread=02");
		
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}

}
