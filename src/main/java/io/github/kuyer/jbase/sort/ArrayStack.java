package io.github.kuyer.jbase.sort;

import java.lang.reflect.Array;

/**
 * 栈
 * @author Rory.Zhang
 */
public class ArrayStack<T> {
	
	private static final int SIZE = 20;
	private T[] arr;
	private int count;
	
	public ArrayStack(Class<T> type) {
		this(type, SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(Class<T> type, int size) {
		arr = (T[]) Array.newInstance(type, size);
		this.count = 0;
	}
	
	/** 入栈 **/
	public void push(T val) {
		arr[this.count++] = val;
	}
	
	/** 返回栈顶元素 **/
	public T peek() {
		return arr[this.count-1];
	}
	
	/** 返回栈顶元素并出栈 **/
	public T pop() {
		T val = arr[this.count-1];
		count --;
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
		ArrayStack<String> stack = new ArrayStack<String>(String.class);
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
