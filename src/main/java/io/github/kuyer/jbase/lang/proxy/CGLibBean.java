package io.github.kuyer.jbase.lang.proxy;

public class CGLibBean {
	
	public String show(String name) {
		System.out.println("cglib "+name+" show ...");
		return "hello, "+name;
	}

}
