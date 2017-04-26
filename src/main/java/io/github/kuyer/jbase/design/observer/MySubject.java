package io.github.kuyer.jbase.design.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class MySubject {
	
	private List<MyObserver> observers = new ArrayList<>();
	
	public void attach(MyObserver observer) {
		System.out.println("attach observer.");
		observers.add(observer);
	}
	
	public void detach(MyObserver observer) {
		System.out.println("detach observer.");
		observers.remove(observer);
	}
	
	public void notifyObservers(String message) {
		for(MyObserver observer : observers) {
			observer.update(message);
		}
	}

}
