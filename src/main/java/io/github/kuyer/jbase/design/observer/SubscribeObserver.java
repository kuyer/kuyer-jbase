package io.github.kuyer.jbase.design.observer;

public class SubscribeObserver implements MyObserver {

	@Override
	public void update(String message) {
		System.out.println("subscribe receive: "+message);
	}

}
