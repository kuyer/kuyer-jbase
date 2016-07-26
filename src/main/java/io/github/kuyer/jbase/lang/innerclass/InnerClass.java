package io.github.kuyer.jbase.lang.innerclass;

public class InnerClass {
	
	//局部内部类
	public InnerMan getWoman() {
		// 局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。
		class InnerWoman extends InnerMan {
			//注意，局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。
		}
		return new InnerWoman();
	}

	public static void main(String[] args) {
		//匿名内部类写法虽然能达到一样的效果，但是既冗长又难以维护，所以一般使用匿名内部类的方法来编写事件监听代码。同样的，匿名内部类也是不能有访问修饰符和static修饰符的。
		//匿名内部类是唯一一种没有构造器的类。正因为其没有构造器，所以匿名内部类的使用范围非常有限，大部分匿名内部类用于接口回调。匿名内部类在编译的时候由系统自动起名为Outter$1.class。
		//一般来说，匿名内部类用于继承其他类或是实现接口，并不需要增加额外的方法，只是对继承方法的实现或是重写。
		new Thread(new Runnable() {
			//匿名内部类
			@Override
			public void run() {
				System.out.println("to run");
			}}).start();
	}

}

class InnerMan {
	
}
