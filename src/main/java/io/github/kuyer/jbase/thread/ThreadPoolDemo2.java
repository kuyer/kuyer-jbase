package io.github.kuyer.jbase.thread;

public class ThreadPoolDemo2 {
	
	public static void main(String[] args) {
		int COUNT_BITS = Integer.SIZE-3;
		System.out.println(COUNT_BITS);
		int CAPACITY = (1<<COUNT_BITS)-1;
		System.out.println(CAPACITY);
		System.out.println(-1<<COUNT_BITS);
		System.out.println(0<<COUNT_BITS);
		System.out.println(1<<COUNT_BITS);
		System.out.println(2<<COUNT_BITS);
		System.out.println(3<<COUNT_BITS);
	}

}
