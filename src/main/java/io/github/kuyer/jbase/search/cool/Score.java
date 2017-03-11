package io.github.kuyer.jbase.search.cool;

import java.util.List;

@FunctionalInterface
public interface Score {
	
	/**
	 * 文档评分
	 * @param doc
	 * @param words
	 * @return
	 */
	public Float score(Doc doc, List<String> words);

}
