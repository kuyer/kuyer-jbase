package io.github.kuyer.jbase.io;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

public class AioServer {

	public static void main(String[] args) throws Exception {
		AsynchronousServerSocketChannel aserver = AsynchronousServerSocketChannel.open();
		if(aserver.isOpen()) {
			aserver.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
			aserver.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			aserver.bind(new InetSocketAddress("127.0.0.1", 9200));
			System.out.println("waiting for connections ...");
			while(true) {
				Future<AsynchronousSocketChannel> future = aserver.accept();
				AsynchronousSocketChannel asocket = future.get();
				System.out.println(asocket.getRemoteAddress()+" connection success.");
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
				try {
					while(asocket.read(buffer).get() != -1) {
						buffer.flip();
						asocket.write(buffer).get();
						if(buffer.hasRemaining()) {
							buffer.compact();
						} else {
							buffer.clear();
						}
					}
					System.out.print(asocket.getRemoteAddress()+" was handler.");
				} catch (Exception e) {}
			}
		} else {
			System.out.println("aio server is not opened.");
		}
	}

}
