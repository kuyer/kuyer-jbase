package io.github.kuyer.jbase.search;

import java.io.File;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

//https://www.tutorialspoint.com/lucene/lucene_indexing_process.htm
public class ElasticTest1 {

	public static void main(String[] args) throws Exception {
		Directory dir = FSDirectory.open(new File("d:\\aa.txt").toPath());
		dir.obtainLock(IndexWriter.WRITE_LOCK_NAME);
		dir.close();
	}

}
