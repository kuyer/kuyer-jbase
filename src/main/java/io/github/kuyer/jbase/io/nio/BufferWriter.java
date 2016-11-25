package io.github.kuyer.jbase.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferWriter {
	
	public static void main(String[] args) throws Exception {
		String resource = BufferRead.class.getClass().getResource("/").getPath();
		RandomAccessFile raf = new RandomAccessFile(resource+"buffer-read.txt", "rw");
		FileChannel fc = raf.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.put("hello, cpp!".getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
			fc.write(buf);
		}
		fc.close();
		raf.close();
		
	}

}
