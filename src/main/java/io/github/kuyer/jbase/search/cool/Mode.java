package io.github.kuyer.jbase.search.cool;

import java.util.Set;

public enum Mode {
	
	INTERSECTION, UNION;
	
	/**
	 * 求existentDocs和increasedDocs的交集，合并结果存储于existentDocs中
	 * @param existentDocs
	 * @param increasedDocs
	 */
	public static void intersection(Set<Doc> existentDocs, Set<Doc> increasedDocs) {
		existentDocs.parallelStream().forEach(existentDoc -> {
			if(!increasedDocs.contains(existentDoc)) {
				existentDocs.remove(existentDoc);
				return;
			}
			for(Doc increasedDoc : increasedDocs) {
				if(existentDoc.getId() == increasedDoc.getId()) {
					existentDoc.merge(increasedDoc);
					break;
				}
			}
		});
	}
	
	/**
	 * 求existentDocs和increasedDocs的并集，合并结果存储于existentDocs中
	 * @param existentDocs
	 * @param increasedDocs
	 */
	public static void union(Set<Doc> existentDocs, Set<Doc> increasedDocs) {
		increasedDocs.parallelStream().forEach(increasedDoc -> {
			if(!existentDocs.contains(increasedDoc)) {
				existentDocs.add(increasedDoc);
			}
		});
	}

}
