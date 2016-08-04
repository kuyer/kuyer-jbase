package io.github.kuyer.jbase.search.store;

import java.io.File;
import java.nio.file.Path;

import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.store.Lock;

public class FsLockThread extends Thread {
	
	public static final String lockName = "fwtlock";
	
	private FSDirectory dir;
	
	public FsLockThread(FSDirectory dir) {
		this.dir = dir;
	}
	
	@Override
	public void run() {
		Lock wlock = null;
		boolean hasLock = false;
		try {
			wlock = dir.obtainLock(lockName);
			if(null != wlock) {
				hasLock = true;
			}
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName()+" obtain lock error.");
			e.printStackTrace();
		}
		if(!hasLock) {
			System.out.println(Thread.currentThread().getName()+" obtain lock fail.");
			return;
		}
		System.out.println(Thread.currentThread().getName()+" obtain lock success.");
		try {
			IndexOutput io = dir.createOutput(lockName+".txt", IOContext.DEFAULT);
			io.writeString(Thread.currentThread().getName()+" say hello.");
			Thread.sleep(1000);
			io.close();
			wlock.close();
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName()+" write error.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		File dir = new File("D:\\Work\\doc\\index");
		Path path = dir.toPath();
		FSDirectory fsd = FSDirectory.open(path);
		for(int i=0; i<10; i++) {
			new FsLockThread(fsd).start();
		}
	}

}
