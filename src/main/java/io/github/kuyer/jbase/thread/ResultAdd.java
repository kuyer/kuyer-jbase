package io.github.kuyer.jbase.thread;

public class ResultAdd implements Runnable {
	
	public volatile int value = 0;

	@Override
	public void run() {
		value ++;
	}
	
	public static void main(String[] args) throws Exception {
		ResultAdd ra = new ResultAdd();
		Thread[] ts = new Thread[2];
		for(int i=0; i<2; i++) {
			ts[i] = new Thread(ra);
		}
		for(int i=0; i<2; i++) {
			ts[i].start();
		}
		for(int i=0; i<2; i++) {
			//ts[i].join();
		}
		System.out.println(ra.value);
	}

}
