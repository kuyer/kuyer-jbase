package io.github.kuyer.jbase.lang;

public class ObjectChild extends ObjectParent {
	
	public String name;
	
	static {
		System.out.println("this is child static block.");
	}
	
	public ObjectChild() {
		System.out.println("this is child constractor.");
	}
	
	{
		System.out.println("this is child class.");
	}
	
	public ObjectChild(String name) {
		this.name = name;
		super.name = "rory";
		System.out.println("this is child constractor. name="+name);
	}

	@Override
	public String getName() {
		return this.name;
	}

}
