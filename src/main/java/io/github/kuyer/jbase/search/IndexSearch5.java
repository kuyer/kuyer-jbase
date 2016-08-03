package io.github.kuyer.jbase.search;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

public class IndexSearch5 {

	public static void main(String[] args) throws Exception {
		String indexPath = "D:\\Work\\temp";
		String fieldName = "text";
		String word = "city";
		String[] texts = new String[] {
				"kaifeng is an old city, do you know?",
				"what is your name?",
				"how old are you, you",
				"beijing is a beautiful city, but the name is? no city name",
				"china has many cities",
				"usa has many cities, and model city",
				"you name is rory? in beijig city, or shanghai city, or kaifeng city?"};
		
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory = new SimpleFSDirectory(new File(indexPath).toPath());
		//Directory directory = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(directory, config);
		writer.deleteAll();
		//writer.forceMergeDeletes();//强制清除回收站
		for(String text : texts) {
			Document doc = new Document();
			doc.add(new Field(fieldName, text, TextField.TYPE_STORED));
			if(text.equals("usa has many cities, and model city")) {
			}
			writer.addDocument(doc);
		}
		writer.close();
		
		DirectoryReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		QueryParser parser = new QueryParser(fieldName, analyzer);
		Query query = parser.parse(word);
		ScoreDoc[] hits = searcher.search(query, 100).scoreDocs;
		System.out.println("search results:");
		for(int i=0; i<hits.length; i++) {
			Document doc = searcher.doc(hits[i].doc);
			System.out.println(i+". "+doc.get(fieldName));
		}
		reader.close();
		directory.close();
	}

}
