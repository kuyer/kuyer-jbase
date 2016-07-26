package io.github.kuyer.jbase.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileCopy {

	public static void main(String[] args) throws Exception {
		copy("D:\\Work\\hosts\\mirrors\\CentOS-6.7-i386-minimal.iso", "D:\\Work\\hosts\\mirrors\\CentOS6.iso");
	}
	
	// NIO 复制文件
	public static void copy(String inPath, String outPath) throws Exception {
		FileInputStream fis = new FileInputStream(inPath);
		FileOutputStream fos = new FileOutputStream(outPath);
		
		FileChannel fcin = fis.getChannel();
		FileChannel fcout = fos.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int byteRead = fcin.read(buffer);
		while(byteRead != -1) {
			buffer.flip();
			fcout.write(buffer);
			buffer.clear();
			byteRead = fcin.read(buffer);
		}
		
		fcout.close();
		fcin.close();
		
		fos.close();
		fis.close();
	}

}
