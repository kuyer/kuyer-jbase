package io.github.kuyer.jbase.thread.myreactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程版本的Handler
 * 思路是把耗时的操作（非IO操作）放到其它的线程里运行
 * 使得Handler只关注与Channel之间的IO操作
 * Handler快速地从Channel中读或写，可以使Channel及时地，更快地响应其它请求
 * 耗时的操作完成后，产生一个事件（改变state），再通知（由handler轮询这个状态是否有改变）
 * Handler执行Channel的读写操作
 * @author rory.zhang
 */
public class ReactorHandlerPool extends ReactorHandler {
	
	private ExecutorService pool = Executors.newFixedThreadPool(4);
	private int PROCESSING = 4;

	public ReactorHandlerPool(Selector selector, SocketChannel channel) throws Exception {
		super(selector, channel);
	}
	
	protected void read() throws Exception {
		int readCount = channel.read(input);
		if(readCount>0) {
			state = PROCESSING;
			pool.execute(new Processer(readCount));
		}
		skey.interestOps(SelectionKey.OP_WRITE);
	}
	
	protected void processAndHandOff(int readCount) {
		readProcess(readCount);
		state = SENDING;
	}
	
	class Processer implements Runnable {
		
		int readCount;
		
		public Processer(int readCount) {
			this.readCount = readCount;
		}

		@Override
		public void run() {
			processAndHandOff(readCount);
		}
	}

}
