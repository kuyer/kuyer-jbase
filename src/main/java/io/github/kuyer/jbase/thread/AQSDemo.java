package io.github.kuyer.jbase.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AQSDemo extends AbstractQueuedSynchronizer {

	private static final long serialVersionUID = 3053567912379805303L;

	@Override
	protected boolean tryAcquire(int arg) {
		if(this.compareAndSetState(0, 1)) {
			this.setExclusiveOwnerThread(Thread.currentThread());
			System.out.println(Thread.currentThread().getName() + " try acquire success.");
			return true;
		}
		System.out.println(Thread.currentThread().getName() + " try acquire failed.");
		return false;
	}

	@Override
	protected boolean tryRelease(int arg) {
		System.out.println(Thread.currentThread().getName() + " try release.");
		this.setState(0);
		this.setExclusiveOwnerThread(null);
		return true;
	}

	protected void lock() {
		this.acquire(1);
	}
	
	protected void unlock() {
		this.release(1);
	}

	public static void main(String[] args) {
		AQSDemo aqs = new AQSDemo();
		for(int i=0; i<5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					aqs.lock();
					System.out.println(Thread.currentThread().getName() + " will start.");
					System.out.println(Thread.currentThread().getName() + " will sleep 5s.");
					try {
						Thread.sleep(5000);
						System.out.println(Thread.currentThread().getName() + " contiue.");
					} catch(Exception e) {
						System.err.println(Thread.currentThread().getName() + " interrupted.");
						Thread.currentThread().interrupt();
					} finally {
						aqs.unlock();
					}
				}}).start();
		}
	}

}
