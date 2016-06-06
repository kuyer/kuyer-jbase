package io.github.kuyer.jbase.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * ForkJoin是一个用于并行计算的框架。
 * 它和MapReduce编程类似，将一个大任务分拆成多个worker计算，然后将计算的结果进行汇总，并将汇总的计算结果返回。
 * 不同的是，ForkJoin的worker都是在同一个JVM中，而不是在多个进程或机器中。
 * @author Rory.Zhang
 */
public class ThreadForkJoinDemo<V> extends ForkJoinTask<V> {

	private static final long serialVersionUID = 4920545328292473187L;
	private V value;
	private boolean success = false;
	
	public ThreadForkJoinDemo() {}
	
	public ThreadForkJoinDemo(V value) {
		this.value = value;
	}

	@Override
	protected boolean exec() {
		System.out.println("exec ...");
		return this.success;
	}

	@Override
	public V getRawResult() {
		return value;
	}

	@Override
	protected void setRawResult(V value) {
		this.value = value;
	}
	
	public static void testForkJoinInvoke() throws Exception {
		ForkJoinPool fjPool = new ForkJoinPool();
		ThreadForkJoinDemo<String> fjTask = new ThreadForkJoinDemo<String>("hello, fork & join!");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fjTask.complete("hello, rory!");
			}}).start();
		String result = fjPool.invoke(fjTask);
		System.out.println("result: "+result);
	}
	
	public static void testForkJoinSubmit() throws Exception {
		ForkJoinPool fjPool = new ForkJoinPool();
		ThreadForkJoinDemo<String> fjTask = new ThreadForkJoinDemo<String>("hello, fork & join!");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fjTask.complete("hello, rory!");
			}}).start();
		ForkJoinTask<String> task = fjPool.submit(fjTask);
		String result = task.get();
		System.out.println("result: "+result);
	}

	public static void main(String[] args) throws Exception {
		//testForkJoinInvoke();
		testForkJoinSubmit();
	}

}
