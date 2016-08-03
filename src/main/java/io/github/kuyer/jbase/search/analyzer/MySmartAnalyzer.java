package io.github.kuyer.jbase.search.analyzer;

import java.nio.charset.StandardCharsets;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.cn.smart.HMMChineseTokenizer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.WordlistLoader;
import org.apache.lucene.util.IOUtils;

public class MySmartAnalyzer extends Analyzer {
	
	private CharArraySet stopWords;
	
	private static final String DEFAULT_STOPWORD_FILE = "stopwords.txt";
	private static final String STOPWORD_FILE_COMMENT = "//";
	
	private CharArraySet getDefautStopWords() {
		try {
			return CharArraySet.unmodifiableSet(WordlistLoader.getWordSet(IOUtils
			          .getDecodingReader(SmartChineseAnalyzer.class, DEFAULT_STOPWORD_FILE,
			              StandardCharsets.UTF_8), STOPWORD_FILE_COMMENT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MySmartAnalyzer() {
		stopWords = getDefautStopWords();
	}
	
	public MySmartAnalyzer(String[] words) {
		stopWords = StopFilter.makeStopSet(words, true);
		CharArraySet cas = getDefautStopWords();
		if(null != cas) {
			stopWords.add(cas);
		}
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer tokenizer = new HMMChineseTokenizer();
		TokenStream result = new PorterStemFilter(tokenizer);
		if(null!=stopWords && !stopWords.isEmpty()) {
			result = new StopFilter(result, stopWords);
		}
		return new TokenStreamComponents(tokenizer, result);
	}

}
