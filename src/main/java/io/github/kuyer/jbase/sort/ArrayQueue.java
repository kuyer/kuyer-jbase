package io.github.kuyer.jbase.sort;

import java.lang.reflect.Array;

/**
 * 队列
 * @author Rory.Zhang
 */
public class ArrayQueue<T> {
	
	private static final int SIZE = 20;
	private T[] arr;
	private int count;
	
	public ArrayQueue(Class<T> type) {
		this(type, SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(Class<T> type, int size) {
		arr = (T[]) Array.newInstance(type, size);
		this.count = 0;
	}
	
	/** 添加队列 **/
	public void push(T val) {
		arr[this.count++] = val;
	}
	
	/** 返回队列开头元素 **/
	public T peek() {
		return arr[0];
	}
	
	/** 返回队列开头元素并删除 **/
	public T pop() {
		T val = arr[0];
		count --;
		for(int i=1; i<=count; i++) {
			arr[i-1] = arr[i];
		}
		return val;
	}
	
	/** 栈大小 **/
	public int size() {
		return this.count;
	}
	
	/** 判断栈是否为空 **/
	public boolean isEmpty() {
		return size() <= 0;
	}
	
	/** 打印栈 **/
	public void print() {
		if(isEmpty()) {
			System.out.println("stack is empty!");
			return;
		}
		System.out.printf("stack size: %d\n", size());
		int i = size()-1;
		while(i>=0) {
			System.out.print(arr[i]);
			i--;
		}
		System.out.println();
		System.out.println("print finish.");
	}

	public static void main(String[] args) {
		ArrayQueue<String> stack = new ArrayQueue<String>(String.class);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println("stack peek. "+stack.peek());
		stack.print();
		stack.push("d");
		stack.push("e");
		System.out.println("stack pop. "+stack.pop());
		stack.print();
		stack.push("f");
		stack.print();
	}

}
