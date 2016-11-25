package io.github.kuyer.jbase.thread.reactor;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 参考：http://www.cnblogs.com/xuekyo/archive/2013/01/20/2868547.html
 * @author rory.zhang
 */
public class Reactor implements Runnable {
	
	public final Selector selector;
	public final ServerSocketChannel sschannel;
	
	public Reactor(int port) throws Exception {
		sschannel = ServerSocketChannel.open();
		InetSocketAddress inet = new InetSocketAddress(InetAddress.getLocalHost(), port);
		sschannel.socket().bind(inet);
		sschannel.configureBlocking(false);
		
		selector = Selector.open();
		SelectionKey skey = sschannel.register(selector, SelectionKey.OP_ACCEPT);
		skey.attach(new Acceptor(this));
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				selector.select();
				Set<SelectionKey> skeys = selector.selectedKeys();
				Iterator<SelectionKey> it = skeys.iterator();
				while(it.hasNext()) {
					SelectionKey skey = it.next();
					dispatch(skey);
					skeys.clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dispatch(SelectionKey skey) {
		Object attachment = skey.attachment();
		if(null != attachment) {
			Runnable r = (Runnable) attachment;
			r.run();
		}
	}

}
