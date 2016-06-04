package io.github.kuyer.jbase.io;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Random;

public class AioClient {

	public static void main(String[] args) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		ByteBuffer helloBuffer = ByteBuffer.wrap("hello, aio!".getBytes());
		ByteBuffer randomBuffer;
		CharBuffer charBuffer;
		Charset charset = Charset.defaultCharset();
		CharsetDecoder decoder = charset.newDecoder();
		
		AsynchronousSocketChannel asocket = AsynchronousSocketChannel.open();
		if(asocket.isOpen()) {
			asocket.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
			asocket.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
			asocket.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
			Void connect = asocket.connect(new InetSocketAddress("127.0.0.1", 9200)).get();
			if(null == connect) {
				System.out.println("local address: "+asocket.getLocalAddress());
				asocket.write(helloBuffer).get();
				while(asocket.read(buffer).get() != -1) {
					buffer.flip();
					charBuffer = decoder.decode(buffer);
					System.out.println("receive: "+charBuffer.toString());
					if(buffer.hasRemaining()) {
						buffer.compact();
					} else {
						buffer.clear();
					}
					int r = new Random().nextInt(100);
					if(r == 50) {
						System.out.println("50 was generated. close client.");
						break;
					} else {
						randomBuffer = ByteBuffer.wrap("random: ".concat(String.valueOf(r)).getBytes());
						asocket.write(randomBuffer).get();
					}
				}
			} else {
				System.out.println("connect fail.");
			}
		} else {
			System.out.println("aio client is not opened.");
		}
	}

}
