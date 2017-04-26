package io.github.kuyer.jbase.design.observer;

public class MyObserverClient {

	public static void main(String[] args) {
		MyObserver observer = new SubscribeObserver();
		PublishSubject subject = new PublishSubject();
		subject.attach(observer);
		subject.change("test");
	}

}
