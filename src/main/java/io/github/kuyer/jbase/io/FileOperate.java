package io.github.kuyer.jbase.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.Writer;

public class FileOperate {

	public static void main(String[] args) throws Exception {
		File f1 = new File("D:"+File.separator+"file-byte.txt");
		OutputStream os = new FileOutputStream(f1);
		byte[] bytes = "hello, bytes!".getBytes();
		os.write(bytes);
		os.close();
		
		File f2 = new File("D:"+File.separator+"file-char.txt");
		Writer writer = new FileWriter(f2);
		writer.write("hello, char!");
		//writer.flush();
		writer.close();
	}

}
