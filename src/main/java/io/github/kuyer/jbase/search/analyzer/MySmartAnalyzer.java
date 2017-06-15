package io.github.kuyer.jbase.search.analyzer;

import java.nio.charset.StandardCharsets;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.cn.smart.HMMChineseTokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.util.IOUtils;

import io.github.kuyer.jbase.search.analyzer.filter.MySameTokenFilter;

public class MySmartAnalyzer extends Analyzer {
	
	private CharArraySet stopWords;
	
	private static final String DEFAULT_STOPWORD_FILE = "mystopwords.txt";
	private static final String STOPWORD_FILE_COMMENT = "//";
	
	private CharArraySet getDefautStopWords() {
		try {
			return CharArraySet.unmodifiableSet(WordlistLoader.getWordSet(IOUtils
			          .getDecodingReader(MySmartAnalyzer.class, DEFAULT_STOPWORD_FILE,
			              StandardCharsets.UTF_8), STOPWORD_FILE_COMMENT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MySmartAnalyzer() {
		this(new String[] {});
	}
	
	public MySmartAnalyzer(String[] words) {
		CharArraySet sw = StopFilter.makeStopSet(words, true);
		if(null != sw) {
			stopWords = sw;
		}
		CharArraySet cas = getDefautStopWords();
		if(null != cas) {
			stopWords.addAll(cas);
		}
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer tokenizer = new HMMChineseTokenizer();
		TokenStream result = new PorterStemFilter(tokenizer);
		if(null!=stopWords && !stopWords.isEmpty()) {
			result = new StopFilter(result, stopWords);
		}
		return new TokenStreamComponents(tokenizer, new MySameTokenFilter(result));
	}

}
