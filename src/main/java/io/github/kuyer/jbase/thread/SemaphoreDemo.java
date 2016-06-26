package io.github.kuyer.jbase.thread;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * 信号量 被用于控制特定资源在同一时间内被访问个数，类似线程池，保证资源被合理的利用
 * @author Administrator
 *
 */
public class SemaphoreDemo {
	
	private static Semaphore semaphore = new Semaphore(3);

	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName()+" execute at "+new Date());
						Thread.sleep(2000);
						semaphore.release();
					} catch (Exception e) {
						System.out.println(Thread.currentThread().getName()+" interrupted.");
					}
				}}).start();
		}
	}

}
