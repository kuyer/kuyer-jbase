package io.github.kuyer.jbase.search.analyzer;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class AnalyzerDemo {
	
	public static void tokenInfo(Analyzer analyzer, String content) throws Exception {
		System.out.println("----------------------------------------------------------");
		TokenStream stream = analyzer.tokenStream("content", new StringReader(content));
		stream.reset();
		System.out.println("Result: ");
		while(stream.incrementToken()) {
			PositionIncrementAttribute pia = stream.getAttribute(PositionIncrementAttribute.class);
			OffsetAttribute oa = stream.getAttribute(OffsetAttribute.class);
			CharTermAttribute cta = stream.getAttribute(CharTermAttribute.class);
			TypeAttribute ta = stream.getAttribute(TypeAttribute.class);
			System.out.println("位置增量："+pia.getPositionIncrement()+"；单词："+cta+"["+oa.startOffset()+","+oa.endOffset()+"]；类型："+ta.type());
		}
		stream.end();
		stream.close();
	}
	
	public static void tokenString(Analyzer analyzer, String content) throws Exception {
		TokenStream stream = analyzer.tokenStream("content", new StringReader(content));
		stream.reset();
		System.out.print("Result: ");
		while(stream.incrementToken()) {
			CharTermAttribute attr = stream.getAttribute(CharTermAttribute.class);
			System.out.print("["+attr.toString()+"]");
		}
		System.out.println();
		stream.end();
		stream.close();
	}

	public static void main(String[] args) throws Exception {
		Analyzer analyzer1 = new StandardAnalyzer();
		Analyzer analyzer2 = new StopAnalyzer();
		Analyzer analyzer3 = new SimpleAnalyzer();
		Analyzer analyzer4 = new WhitespaceAnalyzer();
		Analyzer analyzer5 = new SmartChineseAnalyzer();
		Analyzer analyzer6 = new MySmartAnalyzer(new String[] {"在", "是", "的"});
		Analyzer analyzer7 = new MyAnalyzer(new String[] {"I", "am", "from"});
		
		//String content = "this is my house, I am come from Zhengzhou, my house is closed.";
		String content = "你好，这是我的房间，我来自河南，现在在上海上班";
		
		tokenString(analyzer1, content);
		tokenString(analyzer2, content);
		tokenString(analyzer3, content);
		tokenString(analyzer4, content);
		tokenString(analyzer5, content);
		tokenString(analyzer6, content);
		tokenString(analyzer7, content);
		
		tokenInfo(analyzer1, content);
		tokenInfo(analyzer2, content);
		tokenInfo(analyzer3, content);
		tokenInfo(analyzer4, content);
		tokenInfo(analyzer5, content);
		tokenInfo(analyzer6, content);
		tokenInfo(analyzer7, content);
	}

}
