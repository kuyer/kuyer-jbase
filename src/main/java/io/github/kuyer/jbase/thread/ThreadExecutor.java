package io.github.kuyer.jbase.thread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * http://www.cnblogs.com/wxwall/p/7050698.html
 * @author rory.zhang
 */
public class ThreadExecutor {
	
	private volatile boolean running = true;
	
	private BlockingQueue<Runnable> queue = null;
	
	private final Set<Worker> workers = new HashSet<>();
	
	private final List<Thread> threads = new ArrayList<>();
	
	private int poolSize = 0;
	
	private int coreSize = 0;
	
	boolean shutdown = false;
	
	public ThreadExecutor(int poolSize) {
		this.poolSize = poolSize;
		queue = new LinkedBlockingQueue<>();
	}
	
	public void execute(Runnable runnable) {
		if(null == runnable) {
			throw new NullPointerException();
		}
		if(coreSize<poolSize) {
			addThread(runnable);
		} else {
			try {
				queue.put(runnable);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void addThread(Runnable runnable) {
		coreSize ++;
		Worker worker = new Worker(runnable);
		workers.add(worker);
		Thread t = new Thread(worker);
		threads.add(t);
		t.start();
	}
	
	public void shutdown() {
		running = false;
		if(!workers.isEmpty()) {
			for(Worker worker : workers) {
				worker.interruptIfIdle();
			}
		}
		shutdown = true;
		Thread.currentThread().interrupt();
	}

	class Worker implements Runnable {

		public Worker(Runnable runnable) {
			queue.offer(runnable);
		}

		@Override
		public void run() {
			while(true && running) {
				if(shutdown) {
					Thread.interrupted();
				}
				Runnable task;
				try {
					task = getTask();
					task.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public Runnable getTask() throws InterruptedException {
			return queue.take();
		}
		
		public void interruptIfIdle() {
			for(Thread t : threads) {
				t.interrupt();
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadExecutor executor = new ThreadExecutor(3);
		for(int i=0; i<20; i++) {
			final int index = i+1;
			executor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" is running task "+index+".");
				}});
		}
		Thread.sleep(5000);
		executor.shutdown();
		Thread.sleep(3000);
	}

}
