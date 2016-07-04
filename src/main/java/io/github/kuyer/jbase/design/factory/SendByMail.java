package io.github.kuyer.jbase.design.factory;

public class SendByMail implements SendApi {

	@Override
	public void send() {
		System.out.println("send by email.");
	}

}
