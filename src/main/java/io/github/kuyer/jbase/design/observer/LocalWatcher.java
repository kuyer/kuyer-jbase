package io.github.kuyer.jbase.design.observer;

import java.util.Observable;
import java.util.Observer;

public class LocalWatcher implements Observer {
	
	public LocalWatcher(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("watcher receive: "+((LocalNews)o).getMessage());
	}

}
