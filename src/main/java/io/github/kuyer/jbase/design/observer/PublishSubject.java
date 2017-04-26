package io.github.kuyer.jbase.design.observer;

public class PublishSubject extends MySubject {
	
	public void change(String message) {
		System.out.println("publish send: "+message);
		notifyObservers(message);
	}

}
