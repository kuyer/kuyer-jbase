package io.github.kuyer.jbase.design.factory;

/**
 * 抽象工厂模式（Abstract Factory）
 * 工厂方法模式的问题是，实例的创建依赖于工厂类，如果要拓展程序，需要对工厂类进行修改，这违背了闭包原则
 * 抽象工厂模式的拓展很好，只需要实现工厂类的一个抽象接口方法，就可以了
 * @author Rory.Zhang
 */
public interface FactoryAbstract {

	public SendApi produce();

}
