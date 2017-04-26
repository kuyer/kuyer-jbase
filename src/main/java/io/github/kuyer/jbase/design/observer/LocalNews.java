package io.github.kuyer.jbase.design.observer;

import java.util.Observable;

public class LocalNews extends Observable {
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void sendMessage(String message) {
		this.setChanged();
		this.message = message;
		System.out.println("news send: "+message);
		this.notifyObservers();
	}

}
