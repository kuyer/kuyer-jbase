package io.github.kuyer.jbase.io;

import java.io.InputStream;
import java.io.OutputStream;

public class SystemIO {

	public static void main(String[] args) throws Exception {
		System.out.println("Please input: ");
		byte[] bytes = new byte[5];
		InputStream is = System.in;
		int len = is.read(bytes);
		System.out.println("input is: "+new String(bytes, 0, len));
		is.close();
		
		// 系统向屏幕输出流
		OutputStream os = System.out;
		// 屏幕输出
		os.write("hello, iostream!".getBytes());
		//关闭输出流
		os.close();
	}

}
