package io.github.kuyer.jbase.search.cool;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档
 * @author rory.zhang
 */
public class Doc implements Comparable<Doc> {
	
	/** 文档ID **/
	private int id;
	/** 累计词频 **/
	private int frequency;
	/** 文档文本 **/
	private String text;
	/** 词和词位 **/
	private Map<String, List<Integer>> wordPosition = new HashMap<>();
	/** 文档评分 **/
	private float score;
	
	/** 合并通过不同关键词搜索到的同一个文档 **/
	public void merge(Doc doc) {
		if(id == doc.getId()) {
			this.frequency += doc.getFrequency();
			this.wordPosition.putAll(doc.getWordPosition());
		}
	}
	
	public Map<String, List<Integer>> getWordPosition() {
		return Collections.unmodifiableMap(wordPosition);
	}
	
	public void putWordPosition(String word, List<Integer> postions) {
		this.wordPosition.put(word, postions);
	}
	
	public void removeWordPosition(String word) {
		this.wordPosition.remove(word);
	}
	
	public void clearWordPosition() {
		this.wordPosition.clear();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof Doc)) {
			return false;
		}
		Doc doc = (Doc) o;
		return id == doc.getId();
	}
	
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public int compareTo(Doc o) {
		return new Integer(this.id).compareTo(o.getId());
	}

}
