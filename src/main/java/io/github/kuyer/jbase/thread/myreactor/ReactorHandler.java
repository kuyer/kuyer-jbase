package io.github.kuyer.jbase.thread.myreactor;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReactorHandler implements Runnable {
	
	protected SocketChannel channel;
	protected SelectionKey skey;
	protected ByteBuffer input = ByteBuffer.allocate(1024);
	protected static final int READING=0, SENDING=1;
	
	int state = READING;
	String name = "";

	public ReactorHandler(Selector selector, SocketChannel channel) throws Exception {
		this.channel = channel;
		channel.configureBlocking(false);
		skey = this.channel.register(selector, 0);
		skey.attach(this);
		skey.interestOps(SelectionKey.OP_READ);
		selector.wakeup();
	}

	@Override
	public void run() {
		try {
			if(this.state == READING) {
				read();
			} else if(this.state == SENDING) {
				send();
			}
		} catch (Exception e) {
			close();
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			this.channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void read() throws Exception {
		int readCount = channel.read(input);
		if(readCount>0) {
			readProcess(readCount);
		}
		state = SENDING;
		skey.interestOps(SelectionKey.OP_WRITE);
	}
	
	protected synchronized void readProcess(int readCount) {
		StringBuilder sb = new StringBuilder();
		input.flip();
		byte[] subBytes = new byte[readCount];
		byte[] array = input.array();
		System.arraycopy(array, 0, subBytes, 0, readCount);
		sb.append(new String(subBytes));
		input.clear();
		name = sb.toString().trim();
	}

	private void send() throws Exception {
		ByteBuffer output = ByteBuffer.wrap(("hello, "+name+"!\n").getBytes());
		channel.write(output);
		skey.interestOps(SelectionKey.OP_READ);
		state = READING;
	}

}
