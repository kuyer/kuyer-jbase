package io.github.kuyer.jbase.io;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AioFile implements CompletionHandler<Integer, AsynchronousFileChannel> {
	
	int pos = 0;
	AsynchronousFileChannel afchannel = null;
	ByteBuffer buffer = null;
	
	@Override
	public void completed(Integer result, AsynchronousFileChannel attachment) {
		if(result != -1) {
			pos += result;
			System.out.println("read:"+new String(buffer.array(), 0, result));
			buffer.flip();
			attachment.read(buffer, pos, attachment, this);
		} else {
			return;
		}
	}

	@Override
	public void failed(Throwable exc, AsynchronousFileChannel attachment) {
		System.err.println("error!");
		exc.printStackTrace();
	}
	
	public void read(String filePath) throws Exception {
		Path fpath = Paths.get(filePath);
		afchannel = AsynchronousFileChannel.open(fpath, StandardOpenOption.READ);
		buffer = ByteBuffer.allocate(50);
		afchannel.read(buffer, pos, afchannel, this);
	}

	public static void main(String[] args) throws Exception {
		String filePath = "D:\\temp\\url.txt";
		
		/**
		AioFile af = new AioFile();
		af.read(filePath);
		System.in.read();
		**/
		
		ByteBuffer buffer = ByteBuffer.allocate(50);
		Path fpath = Paths.get(filePath);
		AsynchronousFileChannel afchannel = AsynchronousFileChannel.open(fpath, StandardOpenOption.READ);
		Future<Integer> future = afchannel.read(buffer, 0);
		while(!future.isDone()) {
			System.out.println("waiting ...");
			Thread.sleep(1l);
		}
		System.out.println("done: "+future.isDone());
		System.out.println("read: "+future.get());
		buffer.flip();
		System.out.println(Charset.forName(System.getProperty("file.encoding")).decode(buffer));
		buffer.clear();
		afchannel.close();
		
	}

}
