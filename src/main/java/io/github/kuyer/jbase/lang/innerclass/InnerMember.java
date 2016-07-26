package io.github.kuyer.jbase.lang.innerclass;

/**
 * 成员内部类
 * @author rory.zhang
 */
public class InnerMember {
	
	private String name = "outer class";
	
	private static String desc = "outer class desc";
	
	private String sayHello(String name) {
		return "hello, "+name;
	}
	
	private void print() {
		System.out.println(getInner().getName());
	}
	
	// 外部类调用成员内部类需要先实例化内部类再调用
	private InnerMemberClass getInner() {
		return new InnerMemberClass();
	}
	
	// 成员内部类
	class InnerMemberClass {
		
		private String name = "inner class";
		
		public void say() {
			// 1. 成员内部类可以无条件的访问外部类所有成员和方法，包括private成员和静态成员
			System.out.println(sayHello(name));
			// 2. 当成员内部类拥有和外部类同样的属性和方法时，会发生隐藏外部类使用内部类的情况，调用外部类成员时用：外部类.this.成员
			System.out.println(sayHello(InnerMember.this.name));
		}
		
		private String getName() {
			return this.name;
		}
	}
	
	// 静态内部类: 它不能使用外部类的非static成员变量或者方法
	static class InnerStatic {
		public void hello() {
			System.out.println(desc);
		}
		
		public static void hi() {
			System.out.println(desc);
		}
	}

	public static void main(String[] args) {
		InnerMember im = new InnerMember();
		im.print();
		//成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象
		InnerMember.InnerMemberClass imc = im.new InnerMemberClass();
		imc.say();
	}

}
