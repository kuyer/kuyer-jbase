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
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
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
		IndexSearcher search = new IndexSearcher(reader);
		System.out.println("查询结果");
		//Term term = new Term("name", "rory");
		//TermQuery query = new TermQuery(term);//精确匹配
		//FuzzyQuery query = new FuzzyQuery(term);//模糊匹配
		MatchAllDocsQuery query = new MatchAllDocsQuery();//查询全部
		SortField sf = new SortField("value", SortField.Type.INT, true);
		Sort sort = new Sort(sf);
		TopDocs docs = search.search(query, 20, sort);
		for(ScoreDoc sd : docs.scoreDocs) {
			Document doc = search.doc(sd.doc);
			System.out.println(sd.doc+": "+doc.get("id")+", "+doc.get("name")+".");
		}
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
			Field value = new NumericDocValuesField("value", Integer.parseInt(map.get("id")));
			doc.add(value);//排序使用
			writer.addDocument(doc);
		}
		writer.deleteDocuments(new Term("id", "2"));//删除索引
		
		Document doc = new Document();
		Field id = new StringField("id", "4", Field.Store.YES);
		doc.add(id);
		Field name = new StringField("name", "rory41", Field.Store.YES);
		doc.add(name);
		Field value = new NumericDocValuesField("value", 41);
		doc.add(value);//排序使用
		writer.updateDocument(new Term("id", "4"), doc);//更新索引
		
		writer.forceMergeDeletes();//强制删除索引
		writer.forceMerge(2);//手动合并
		writer.close();
		directory.close();
	}
	
	private static List<Map<String, String>> getDatas() {
		List<Map<String, String>> list = new ArrayList<>();
		for(int i=1; i<11; i++) {
			Map<String, String> map = new HashMap<>();
			map.put("id", String.valueOf(i));
			map.put("name", "rory"+i);
			list.add(map);
		}
		return list;
	}

}
