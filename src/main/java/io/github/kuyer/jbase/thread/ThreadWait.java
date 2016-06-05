package io.github.kuyer.jbase.thread;

/**
 * wait notify notifyAll 在线程中起到了协调的作用
 * 调用某对象的wait会让当前的线程阻塞，并且当前线程拥有此对象的锁
 * 调用某对象的notify，会唤醒此对象正在阻塞的线程
 * @author rory.zhang
 */
public class ThreadWait {
	
	public static Object object = new Object();

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(object) {
					try {
						object.wait(2800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" get lock.");
				}
			}});
		t1.setName("thread-01");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(object) {
					object.notify();
					System.out.println(Thread.currentThread().getName()+" notify.");
				}
				System.out.println(Thread.currentThread().getName()+" release lock.");
			}});
		t2.setName("thread-02");
		
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}

}
