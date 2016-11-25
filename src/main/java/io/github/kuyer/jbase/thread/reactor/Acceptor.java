package io.github.kuyer.jbase.thread.reactor;

import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {
	
	private Reactor reactor;
	
	public Acceptor(Reactor reactor) {
		this.reactor = reactor;
	}

	@Override
	public void run() {
		try {
			SocketChannel schannel = reactor.sschannel.accept();
			if(null != schannel) {
				new AcceptorHandler(reactor.selector, schannel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
