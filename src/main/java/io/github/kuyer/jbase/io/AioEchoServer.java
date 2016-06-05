package io.github.kuyer.jbase.io;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

/**
 * 用法：telnet localhost 9200
 * @author rory.zhang
 */
public class AioEchoServer {
	
	private AsynchronousServerSocketChannel aserver;
	
	private void init(String host, int port) throws Exception {
		AsynchronousChannelGroup agroup = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10);
		aserver = AsynchronousServerSocketChannel.open(agroup);
		aserver.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		aserver.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
		aserver.bind(new InetSocketAddress(host, port));
		System.out.println("aio echo server started on port: "+port);
		System.out.println("aio echo channel provider: "+aserver.provider());
		aserver.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
			final ByteBuffer buffer = ByteBuffer.allocate(1024);
			@Override
			public void completed(AsynchronousSocketChannel result, Object attachment) {
				System.out.println("waiting ...");
				buffer.clear();
				try {
					result.read(buffer).get();
					buffer.flip();
					System.out.println("echo: "+new String(buffer.array()).trim()+" to "+result);
					result.write(buffer);
					buffer.flip();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						result.close();
					} catch (Exception ex) {}
					aserver.accept(null, this);
				}
				
			}
			@Override
			public void failed(Throwable exc, Object attachment) {
				System.out.println("aio echo server has error. "+exc.getCause());
			}});
		System.in.read();
	}

	public static void main(String[] args) throws Exception {
		AioEchoServer aes = new AioEchoServer();
		aes.init("localhost", 9200);
	}

}
