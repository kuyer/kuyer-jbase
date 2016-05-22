package io.github.kuyer.jbase.lang;

public class ObjectParent {
	
	public String name;
	
	static {
		System.out.println("this is parent static block.");
	}
	
	{
		System.out.println("this is parent class.");
	}
	
	public ObjectParent() {
		System.out.println("this is parent constructor.");
	}
	
	public ObjectParent(String name) {
		this.name = name;
		System.out.println("this is parent constructor. name="+name);
	}

	public String getName() {
		return name;
	}

}
