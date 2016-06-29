package io.github.kuyer.jbase;

public class Test {
	
	public String sayHello(String name) {
		System.out.println("hi, "+name);
		return "hello, "+name;
	}

	/**
	public static void main(String[] args) {
		Object obj = new Object();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					System.out.println(Thread.currentThread().getName()+" invoked.");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}}, "block-thread-1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					System.out.println(Thread.currentThread().getName()+" invoked.");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}}, "block-thread-2");
		t1.start();
		t2.start();
	}
	**/

}
