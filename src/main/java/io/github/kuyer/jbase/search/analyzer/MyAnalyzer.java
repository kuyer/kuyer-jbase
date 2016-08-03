package io.github.kuyer.jbase.search.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.LowerCaseTokenizer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.util.CharArraySet;

public class MyAnalyzer extends Analyzer {
	
	private CharArraySet stops;//用于存放分词信息
	
	public MyAnalyzer() {
		stops = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}
	
	public MyAnalyzer(String[] words) {
		// true 表示忽略大小写
		stops = StopFilter.makeStopSet(words, true);
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer source = new LowerCaseTokenizer();
		return new TokenStreamComponents(source, new StopFilter(new LowerCaseFilter(source), stops));
	}

}
