package io.github.kuyer.jbase.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	
	public static void main(String[] args) throws Exception {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 7, 5l, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<Integer.MAX_VALUE; i++) {
					final int index = i;
					Runnable r = new Runnable() {
						@Override
						public void run() {
							try {
								if(index%2 == 0) {
									Thread.sleep(12000l);
								} else {
									Thread.sleep(4000l);
								}
								System.out.println(Thread.currentThread().getName()+" is run over.");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}};
					pool.execute(r);
					try {
						Thread.sleep(1000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}});
		t.start();
		for(;;) {
			System.out.println("CompletedTaskCount: "+pool.getCompletedTaskCount()+"; ActiveCount: "+pool.getActiveCount()+"; LargestPoolSize: "+pool.getLargestPoolSize()+"; QueueSize: "+pool.getQueue().size());
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
