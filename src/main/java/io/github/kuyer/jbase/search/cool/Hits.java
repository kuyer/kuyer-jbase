package io.github.kuyer.jbase.search.cool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 命中结果
 * @author rory.zhang
 */
public class Hits {
	
	private int hitCount;
	private List<Doc> docs = new ArrayList<>();
	
	public Hits(int hitCount, List<Doc> docs) {
		this.hitCount = hitCount;
		this.docs = docs;
	}
	
	public int getHitCount() {
		return hitCount;
	}
	
	public List<Doc> getDocs() {
		return Collections.unmodifiableList(docs);
	}

}
