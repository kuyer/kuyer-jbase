package io.github.kuyer.jbase.io;

import java.nio.ByteBuffer;

public class ByteBufferDemo {

	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.put((byte)'A');
		buf.put((byte)'B');
		buf.put((byte)'C');
		buf.put((byte)'D');
		buf.flip();
		System.out.println((char)buf.get());
		System.out.println((char)buf.get());
		System.out.println((char)buf.get());
		System.out.println((char)buf.get());
	}

}
