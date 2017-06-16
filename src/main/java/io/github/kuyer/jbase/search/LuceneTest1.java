package io.github.kuyer.jbase.search;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneTest1 {
	
	private static String INDEX_PATH = "D:\\temp\\index";
	
	public static void main(String[] args) throws Exception {
		index();
		count();
	}
	
	private static void count() throws Exception {
		Directory directory = FSDirectory.open(new File(INDEX_PATH).toPath());
		IndexReader reader = StandardDirectoryReader.open(directory);
		System.out.println("总索引数："+reader.maxDoc());
		System.out.println("可用索引："+reader.numDocs());
		System.out.println("删除索引："+reader.numDeletedDocs());
		reader.close();
		directory.close();
	}

	private static void index() throws Exception {
		Directory directory = FSDirectory.open(new File(INDEX_PATH).toPath());
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(directory, config);
		writer.deleteAll();
		List<Map<String, String>> list = getDatas();
		for(Map<String, String> map : list) {
			Document doc = new Document();
			Field id = new StringField("id", map.get("id"), Field.Store.YES);
			doc.add(id);
			Field name = new StringField("name", map.get("name"), Field.Store.YES);
			doc.add(name);
			writer.addDocument(doc);
		}
		writer.deleteDocuments(new Term("id", "2"));
		writer.close();
		directory.close();
	}
	
	private static List<Map<String, String>> getDatas() {
		List<Map<String, String>> list = new ArrayList<>();
		for(int i=1; i<11; i++) {
			Map<String, String> map = new HashMap<>();
			map.put("id", String.valueOf(i));
			map.put("name", "rory-"+i);
			list.add(map);
		}
		return list;
	}

}
