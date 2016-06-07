package io.github.kuyer.jbase.lang;

public class ReferenceCountGC {
	
	private static final int _1MB = 1024 * 1024;
	
	/** 查看GC是否回收 **/
	public byte[] size = new byte[2 * _1MB];
	public Object instance = null;
	
	public static void testGC() {
		// 定义两个对象
		ReferenceCountGC rc1 = new ReferenceCountGC();
		ReferenceCountGC rc2 = new ReferenceCountGC();
		
		// 给对象成员赋值，存在相互引用的情况
		rc1.instance = rc2;
		rc2.instance = rc1;
		
		// 将引用对象设置为空，即堆对象没有了引用
		rc1 = null;
		rc2 = null;
		
		// 垃圾回收
		System.gc();
	}

	public static void main(String[] args) {
		// 运行参数设置：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
		testGC();
	}

}
