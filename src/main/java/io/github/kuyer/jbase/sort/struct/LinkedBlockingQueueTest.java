package io.github.kuyer.jbase.sort.struct;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue不能添加null元素
 * @author rory.zhang
 */
public class LinkedBlockingQueueTest {
	
	/**
	 * 当add的元素超过LinkedBlockingQueue的容量时，会抛出Queue full异常
	 * add方法实际执行的是offer方法
	 */
	public static void testAdd() {
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(2);
		System.out.println("add a: "+lbq.add("a"));
		System.out.println("add b: "+lbq.add("b"));
		System.out.println("add c: "+lbq.add("c"));
	}
	
	/**
	 * 如果队列为空，remove操作会出现NoSuchElementException异常
	 */
	public static void testRemove() {
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(2);
		lbq.add("a");
		System.out.println(lbq.remove());
		System.out.println(lbq.remove());
	}
	
	/**
	 * 当put的元素超过LinkedBlockingQueue的容量时，会发生阻塞队列的情况
	 * @throws Exception
	 */
	public static void testPut() throws Exception {
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(2);
		lbq.put("a");
		System.out.println("put a.");
		lbq.put("b");
		System.out.println("put b.");
		lbq.put("c");
		System.out.println("put c.");
	}
	
	/**
	 * 当队列为空时，take操作会发生阻塞，直到有新的元素被put
	 * @throws Exception
	 */
	public static void testTake() throws Exception {
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(2);
		lbq.put("a");
		System.out.println(lbq.take());
		System.out.println(lbq.take());
	}
	
	/**
	 * 当offer的元素超过LinkedBlockingQueue的容量时，会直接返回false，不将超出的元素添加到队列中，不会阻塞队列
	 */
	public static void testOffer() {
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(2);
		System.out.println("offer a: "+lbq.offer("a"));
		System.out.println("offer b: "+lbq.offer("b"));
		System.out.println("offer c: "+lbq.offer("c"));
		System.out.println("result: "+lbq.toString());
	}
	
	/**
	 * 如果队列为空，poll操作会获取null值，不会阻塞队列
	 * @throws Exception
	 */
	public static void testPoll() throws Exception {
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(2);
		lbq.offer("a");
		System.out.println(lbq.poll());
		System.out.println(lbq.poll());
	}
	
	public static void main(String[] args) throws Exception {
		testRemove();
	}

}
