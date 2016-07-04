package io.github.kuyer.jbase.design.factory;

/**
 * 工厂方法模式
 * @author rory.zhang
 */
public class FactoryMethod {
	
	/**
	 * 普通工厂方法模式：普通工厂模式，通过工厂类的一个方法，根据不同类型的参数，获取不同的实例
	 * 工厂方法模式的场景：大量的对象实例需要创建，并且这些对象具有共同的接口，可以通过工厂方法模式进行创建；
	 * 普通工厂方法模式，缺点是参数有误，不能创建实例
	 * 多个工厂方法模式，缺点是需要实例化工厂类
	 * @param type
	 * @return
	 */
	public SendApi produce(String type) {
		if(null == type) {
			System.out.println("type is null.");
			return null;
		} else if("mail".equals(type.trim())) {
			return new SendByMail();
		} else if("sms".equals(type.trim())) {
			return new SendBySms();
		} else {
			System.out.println("type is error.");
			return null;
		}
	}
	
	/**
	 * 多个工厂方法模式，通过工厂类，提供多个方法，每个方法提供对应的实例
	 * 相比普通工厂方法模式有了改进，普通工厂方法模式，如果方法参数传递不正确，则不能正确创建对象
	 * @return
	 */
	public SendApi productMail() {
		return new SendByMail();
	}
	public SendApi productSms() {
		return new SendBySms();
	}
	
	/**
	 * 静态工厂方法模式，通过工厂类，提供多个静态的方法，每个方法提供对应的实例
	 * 优点是：不需要创建实例，直接调用即可
	 * @return
	 */
	public static SendApi produceMail() {
		return new SendByMail();
	}
	public static SendApi produceSms() {
		return new SendBySms();
	}
	
	public static void main(String[] args) {
		// 普通工厂模式测试
		FactoryMethod fm1 = new FactoryMethod();
		SendApi sendApi1 = fm1.produce("mail");
		sendApi1.send();
		
		//抽象工厂模式
		FactoryAbstract fa2 = new FactoryAbstractSms();
		SendApi sendApi2 = fa2.produce();
		sendApi2.send();
	}

}
