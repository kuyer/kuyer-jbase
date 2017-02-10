package io.github.kuyer.jbase.io;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FilesOperate {

	public static void main(String[] args) throws Exception {
		String filePath = "d:\\var\\test";
		String fileName = "aa.log";
		Path path = Paths.get(filePath);
		if(Files.notExists(path)) {
			Files.createDirectories(path);
		}
		Path file = Paths.get(filePath, fileName);
		if(Files.exists(file)) {
			// 存在则读
			byte[] bytes = Files.readAllBytes(file);
			System.out.println(new String(bytes));
		} else {
			// 不存在则写
			BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            writer.write("Java\nGo\nPython\nNodeJS\nObject C\n中国南京");
            writer.flush();
            writer.close();
            System.out.println("write success.");
		}
	}

}
