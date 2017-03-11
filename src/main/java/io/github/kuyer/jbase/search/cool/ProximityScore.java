package io.github.kuyer.jbase.search.cool;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ProximityScore implements Score {
	
	private int slop = 0;

	public int getSlop() {
		return slop;
	}

	public void setSlop(int slop) {
		this.slop = slop;
	}

	@Override
	public Float score(Doc doc, List<String> words) {
		if(words.size()<=1) {
			return 0f;
		}
		Map<String, List<Integer>> wordPosition = doc.getWordPosition();
		if(words.size() != wordPosition.size()) {
			return 0f;
		}
		AtomicInteger score = new AtomicInteger();
		String lastWord = words.get(words.size()-1);
		wordPosition.get(lastWord).stream().forEach(endPostion -> {
			int prePostion = endPostion;
			int permitPostion = prePostion - slop -1;
			int times = 0;
			for(int i=words.size()-2; i>-1; i--) {
				boolean find = false;
			}
		});
		return null;
	}

}
