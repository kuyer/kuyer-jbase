package io.github.kuyer.jbase.lang.proxyx;

public class ProxyImpl implements IProxy {

	@Override
	public String say(String name) {
		System.out.println("invoke proxy.");
		return "hello, I will proxy "+name+"!";
	}

}
