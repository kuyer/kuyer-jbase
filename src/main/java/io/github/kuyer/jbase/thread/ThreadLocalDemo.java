package io.github.kuyer.jbase.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ThreadLocal把变量放到线程本地来实现线程的安全
 * @author Administrator
 *
 */
public class ThreadLocalDemo {
	
	private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}};

	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+": "+sdf.get().format(new Date()));
				}});
			thread.start();
		}
	}

}
