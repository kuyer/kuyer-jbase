package io.github.kuyer.jbase.search.cool;

import java.util.List;

public class WordFrequencyScore implements Score {

	@Override
	public Float score(Doc doc, List<String> words) {
		return Float.valueOf(doc.getFrequency());
	}

}
