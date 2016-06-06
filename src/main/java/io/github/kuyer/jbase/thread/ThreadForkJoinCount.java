package io.github.kuyer.jbase.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 并行计算 1+2+3+...+100=?
 * @author Rory.Zhang
 */
public class ThreadForkJoinCount extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 677289261373636521L;
	
	/** fork/join次数 **/
	private static int index = 0;
	
	/** 开始数字 **/
	private int start;
	/** 结束数字 **/
	private int end;
	/** 阀值 **/
	private int threshold;
	
	public ThreadForkJoinCount(int start, int end, int threshold) {
		this.start = start;
		this.end = end;
		this.threshold = threshold;
	}

	@Override
	protected Integer compute() {
		System.out.println("forkjoin ..."+(index++));
		int sum = 0;
		boolean needFork = (end-start) > threshold;
		if(needFork) {
			int middle = (start + end) / 2;
			
			ThreadForkJoinCount leftTask = new ThreadForkJoinCount(start, middle, threshold);
			ThreadForkJoinCount rightTask = new ThreadForkJoinCount(middle+1, end, threshold);
			leftTask.fork();
			rightTask.fork();
			
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();
			
			sum = leftResult + rightResult;
		} else {
			for(int i=start; i<=end; i++) {
				sum += i;
			}
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		ForkJoinPool fjpool = new ForkJoinPool();
		ThreadForkJoinCount task = new ThreadForkJoinCount(1, 100, 5);
		Future<Integer> result = fjpool.submit(task);
		System.out.println("result: "+result.get());
	}

}
