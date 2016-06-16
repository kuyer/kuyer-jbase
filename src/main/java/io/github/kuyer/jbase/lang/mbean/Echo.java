package io.github.kuyer.jbase.lang.mbean;

public class Echo implements EchoMBean {

	@Override
	public String print(String name) {
		System.out.println("name: "+name);
		return "hi, "+name;
	}

}
