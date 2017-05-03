package io.github.kuyer.jbase.design.stragety;

public class StragetyContext {
	
	private Stragety stragety;
	
	public StragetyContext(Stragety stragety) {
		this.stragety = stragety;
	}
	
	public String getHello(String name) {
		return this.stragety.hello(name);
	}

}
