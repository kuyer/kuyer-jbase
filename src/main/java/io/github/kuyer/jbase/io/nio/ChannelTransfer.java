package io.github.kuyer.jbase.io.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ChannelTransfer {
	
	public static void main(String[] args) throws Exception {
		String resource = BufferRead.class.getClass().getResource("/").getPath();
		RandomAccessFile rafFrom = new RandomAccessFile(resource+"elastic-stack.txt", "rw");
		RandomAccessFile rafTo = new RandomAccessFile(resource+"elastic-stack-copy.txt", "rw");
		
		FileChannel fcFrom = rafFrom.getChannel();
		FileChannel fcTo = rafTo.getChannel();
		
		long position = 0l;
		long count = fcFrom.size();
		//fcTo.transferFrom(fcFrom, position, count);
		fcFrom.transferTo(position, count, fcTo);
		
		fcFrom.close();
		fcTo.close();
		
		rafFrom.close();
		rafTo.close();
	}

}
