package io.github.kuyer.jbase.design.factory;

public class SendBySms implements SendApi {

	@Override
	public void send() {
		System.out.println("send by sms.");
	}

}
