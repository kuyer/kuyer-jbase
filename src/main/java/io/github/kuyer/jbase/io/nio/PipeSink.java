package io.github.kuyer.jbase.io.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeSink {

	public static void main(String[] args) throws Exception {
		Pipe pipe = Pipe.open();
		Pipe.SinkChannel sink = pipe.sink();
		ByteBuffer sinkBuf = ByteBuffer.allocate(48);
		sinkBuf.clear();
		sinkBuf.put("hello, rory!".getBytes());
		sinkBuf.flip();
		while(sinkBuf.hasRemaining()) {
			sink.write(sinkBuf);
		}
		sinkBuf.clear();
		sink.close();
		
		Pipe.SourceChannel source = pipe.source();
		ByteBuffer srcBuf = ByteBuffer.allocate(48);
		srcBuf.clear();
		int bytesRead = source.read(srcBuf);
		srcBuf.flip();
		if(bytesRead != -1) {
			while(srcBuf.hasRemaining()) {
				System.out.print((char)srcBuf.get());
			}
		}
		srcBuf.clear();
		source.close();
	}

}
