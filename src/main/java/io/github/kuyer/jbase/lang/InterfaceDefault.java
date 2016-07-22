package io.github.kuyer.jbase.lang;

public class InterfaceDefault implements InterfaceA, InterfaceB {

	public static void main(String[] args) {
		new InterfaceDefault().say("rory");
	}

	@Override
	public void say(String name) {
		sayHello(name);
		sayHi(name);
	}
	
	@Override
	public void sayHello(String name) {
		InterfaceA.super.sayHello(name);
	}

}

interface InterfaceA {
	
	public void say(String name);
	
	default public void sayHello(String name) {
		System.out.println("hello, "+name);
	}
	
}

interface InterfaceB {
	
	default public void sayHi(String name) {
		System.out.println("hi, "+name);
	}
	
	default public void sayHello(String name) {
		System.out.println("hello and hi, "+name);
	}
	
}
