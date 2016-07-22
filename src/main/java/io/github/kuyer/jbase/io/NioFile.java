package io.github.kuyer.jbase.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class NioFile {

	public static void main(String[] args) throws Exception {
		getFiles("D:/", "glob:**/*.zip");
		System.out.println("============================================================");
		
		String content = new String(Files.readAllBytes(Paths.get("D:\\user.htm")));
		System.out.println("content: "+content);
		
		List<String> lines = Files.readAllLines(Paths.get("D:\\user.htm"), StandardCharsets.UTF_8);
		for(int i=0; i<lines.size(); i++) {
			System.out.println(i+": "+lines.get(i));
		}
		
		Files.lines(Paths.get("D:\\user.htm"), StandardCharsets.UTF_8).forEach(System.out::println);
	}

	private static void getFiles(String path, String match) throws Exception {
		PathMatcher pmatcher = FileSystems.getDefault().getPathMatcher(match);
		Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				if(pmatcher.matches(file)) {
					System.out.println(file);
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				return FileVisitResult.CONTINUE;
			}});
	}

}
