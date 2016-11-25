package io.github.kuyer.jbase.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferRead {

	public static void main(String[] args) throws Exception {
		String resource = BufferRead.class.getClass().getResource("/").getPath();
		RandomAccessFile raf = new RandomAccessFile(resource+"elastic-stack.txt", "rw");
		FileChannel fc = raf.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = fc.read(buf);
		while(-1 != bytesRead) {
			System.out.println("Read: "+bytesRead);
			buf.flip();
			while(buf.hasRemaining()) {
				byte[] bs = new byte[1];
				bs[0] = buf.get();
				String content = new String(bs, Charset.forName("UTF-8"));
				System.out.print(content);
			}
			System.out.println();
			buf.clear();
			bytesRead = fc.read(buf);
		}
		fc.close();
		raf.close();
	}

}
