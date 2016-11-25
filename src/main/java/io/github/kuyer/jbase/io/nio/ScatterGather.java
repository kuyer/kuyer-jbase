package io.github.kuyer.jbase.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ScatterGather {
	
	/**
	 * 分散
	 * @param fname
	 */
	public static void scatter(String fname, int headLen, int bodyLen) throws Exception {
		String resource = BufferRead.class.getClass().getResource("/").getPath();
		RandomAccessFile raf = new RandomAccessFile(resource+fname, "rw");
		FileChannel fc = raf.getChannel();
		
		ByteBuffer head = ByteBuffer.allocate(headLen);
		ByteBuffer body = ByteBuffer.allocate(bodyLen);
		ByteBuffer[] bufferArray = {head, body};
		fc.read(bufferArray);
		
		System.out.println("Head:");
		head.flip();
		while(head.hasRemaining()) {
			byte[] bs = new byte[1];
			bs[0] = head.get();
			String str = new String(bs, Charset.forName("UTF-8"));
			System.out.print(str);
		}
		head.clear();
		System.out.println("\nBody:");
		body.flip();
		while(body.hasRemaining()) {
			byte[] bs = new byte[1];
			bs[0] = body.get();
			String str = new String(bs, Charset.forName("UTF-8"));
			System.out.print(str);
		}
		body.clear();
		fc.close();
		raf.close();
	}
	
	/**
	 * 聚集
	 * @param fname
	 */
	public static void gather(String fname, String headStr, String bodyStr) throws Exception {
		String resource = BufferRead.class.getClass().getResource("/").getPath();
		RandomAccessFile raf = new RandomAccessFile(resource+fname, "rw");
		FileChannel fc = raf.getChannel();
		
		ByteBuffer head = ByteBuffer.allocate(headStr.getBytes("UTF-8").length);
		head.put(headStr.getBytes("UTF-8"));
		head.flip();
		
		ByteBuffer body = ByteBuffer.allocate(bodyStr.getBytes("UTF-8").length);
		body.put(bodyStr.getBytes("UTF-8"));
		body.flip();
		
		ByteBuffer[] bufferArray = {head, body};
		fc.write(bufferArray);
		
		fc.close();
		raf.close();
	}
	
	public static void main(String[] args) throws Exception {
		String fname = "scatter-gather.txt";
		String headStr = "hello, my head!";
		String bodyStr = "hello, my body!";
		gather(fname, headStr, bodyStr);
		scatter(fname, headStr.getBytes().length, bodyStr.getBytes().length);
	}

}
