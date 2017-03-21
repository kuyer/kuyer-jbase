package io.github.kuyer.jbase.thread;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

import sun.misc.Unsafe;

public class UnsafeDemo {
	
	//private static final Unsafe unsafe = Unsafe.getUnsafe();
	private static final Unsafe unsafe = getUnsafe();
	private static final long valueOffset;
	
	public static Unsafe getUnsafe() {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			return (Unsafe)f.get(null);
		} catch (Exception e) {}
		return null;
	}
	
	static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (UnsafeDemo.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }
	
	private volatile int value;
	
	public UnsafeDemo(int val) {
		this.value = val;
	}
	
	public final int incrementAndGet() {
		return unsafe.getAndAddInt(this, valueOffset, 1);
	}
	
	public final int get() {
        return value;
    }
	
	private static UnsafeDemo a = new UnsafeDemo(0);
	private static volatile int b = 0;
	public static void main(String[] args) throws Exception {
		CountDownLatch loatch = new CountDownLatch(10);
		for(int i=0; i<10; i++) {
			new Thread() {
				@Override
				public void run() {
					for(int j=0; j<1000; j++) {
						b++;
						a.incrementAndGet();
					}
					loatch.countDown();
				}}.start();
		}
		loatch.await();
		System.out.println("result: "+a.get());
		System.out.println("b: "+b);
	}

}
