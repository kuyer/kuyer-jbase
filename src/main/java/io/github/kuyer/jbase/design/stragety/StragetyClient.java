package io.github.kuyer.jbase.design.stragety;

public class StragetyClient {

	public static void main(String[] args) {
		System.out.println(new StragetyContext(new BlueStragety()).getHello("stragety"));
	}

}
