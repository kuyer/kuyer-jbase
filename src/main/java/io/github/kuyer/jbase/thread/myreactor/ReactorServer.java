package io.github.kuyer.jbase.thread.myreactor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 单线程实现
 * Server端用一个Selector利用一个线程来响应所有的请求
 * 1. 当ACCEPT事件就绪，Acceptor被选中，执行它的run方法：创建一个Handler，并将Handler的interestOps初始为READ
 * 2. 当READ事件就绪，handler被选中，执行它的run方法
 * @author rory.zhang
 */
public class ReactorServer implements Runnable {
	
	private Selector selector;
	private ServerSocketChannel server;
	private boolean widthThreadPool = false;
	
	public ReactorServer(int port, boolean widthThreadPool) throws Exception {
		this.widthThreadPool = widthThreadPool;
		selector = Selector.open();
		server = ServerSocketChannel.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		SelectionKey skey = server.register(selector, SelectionKey.OP_ACCEPT);
		skey.attach(new ReactorAcceptor());
	}

	@Override
	public void run() {
		System.out.println("server listen on port: "+server.socket().getLocalPort());
		try {
			while(!Thread.interrupted()) {
				int readySelectionKeyCount = selector.select();
				if(readySelectionKeyCount<=0) {
					continue;
				}
				Set<SelectionKey> skeys = selector.selectedKeys();
				Iterator<SelectionKey> skiter = skeys.iterator();
				while(skiter.hasNext()) {
					dispatch(skiter.next());
				}
				skeys.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dispatch(SelectionKey skey) {
		Runnable r = (Runnable) skey.attachment();
		if(null != r) {
			r.run();
		}
	}

	class ReactorAcceptor implements Runnable {
		@Override
		public void run() {
			try {
				SocketChannel channel = server.accept();
				if(null != channel) {
					if(widthThreadPool) {
						new ReactorHandlerPool(selector, channel);
					} else {
						new ReactorHandler(selector, channel);
					}
					System.out.println("connect accepted by client: port"+channel.socket().getPort());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}}

	public static void main(String[] args) throws Exception {
		ReactorServer server = new ReactorServer(9528, true);
		new Thread(server).start();
	}

}
