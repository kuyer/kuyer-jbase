package io.github.kuyer.jbase.search.cool;

/**
 * 搜索接口
 * @author rory.zhang
 */
public interface Search {
	
	Hits search(String word);
	
	Hits search(String word, Mode mode);
	
	Hits search(String word, int page);
	
	Hits search(String word, Mode mode, int page);

}
