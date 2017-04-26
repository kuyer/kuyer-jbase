package io.github.kuyer.jbase.design.observer;

public class LocalClient {

	public static void main(String[] args) {
		LocalNews ln = new LocalNews();
		new LocalWatcher(ln);
		ln.sendMessage("test");
	}

}
