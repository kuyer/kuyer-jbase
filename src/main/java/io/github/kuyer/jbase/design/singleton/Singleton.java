package io.github.kuyer.jbase.design.singleton;

/**
 * 单例模式，在Java中，单例对象能保证在jvm中，该对象只有一个实例存在；这种模式的好处是：
 * 1、某些类创建比较频繁，对于一些大型的对象，是一笔很大的开销
 * 2、省去了new操作，降低了系统内存的使用频率，减轻了GC压力
 * @author Rory.Zhang
 */
public class Singleton {
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		return SingletonFactory.instance;
	}
	
	private static class SingletonFactory {
		private static Singleton instance = new Singleton();
	}

}
