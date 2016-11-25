package io.github.kuyer.jbase.thread.reactor;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class AcceptorHandler implements Runnable {
	
	private SocketChannel schannel;

	public AcceptorHandler(Selector selector, SocketChannel schannel) throws Exception {
		this.schannel = schannel;
		schannel.configureBlocking(false);
		SelectionKey skey = schannel.register(selector, 0);
		skey.attach(this);//将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法。参看dispatch(SelectionKey key)
		skey.interestOps(SelectionKey.OP_READ);// 同时将SelectionKey标记为可读，以便读取。
		selector.wakeup();
	}

	@Override
	public void run() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.clear();
		try {
			this.schannel.read(buf);
			buf.flip();
			//TODO 激活线程池 处理这些request
			// requestHandler(new Request(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
