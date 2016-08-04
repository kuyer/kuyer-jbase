package io.github.kuyer.jbase.search.store;

import java.io.File;
import java.nio.file.Path;

import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.store.Lock;

public class FsLockTest {

	public static void main(String[] args) throws Exception {
		File dir = new File("D:\\Work\\doc\\index");
		Path path = dir.toPath();
		FSDirectory fsdir = FSDirectory.open(path);
		Lock wlock = fsdir.obtainLock("fw1lock");
		IndexOutput io = fsdir.createOutput("fw1lock.txt", IOContext.DEFAULT);
		io.writeString("hello, rory");
		io.close();
		wlock.close();
	}

}
