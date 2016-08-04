package io.github.kuyer.jbase.search.analyzer.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

public class MySameTokenFilter extends TokenFilter {
	
	private CharTermAttribute cta = null;
	private PositionIncrementAttribute pia = null;
	private AttributeSource.State current;
	private Stack<String> sames = null;

	public MySameTokenFilter(TokenStream input) {
		super(input);
		cta = this.addAttribute(CharTermAttribute.class);
		pia = this.addAttribute(PositionIncrementAttribute.class);
		sames = new Stack<String>();
	}

	@Override
	public boolean incrementToken() throws IOException {
		while(sames.size() >= 1) {
			String str = sames.pop();
			restoreState(current);
			cta.setEmpty();
			cta.append(str);
			pia.setPositionIncrement(0);
			return true;
		}
		if(!input.incrementToken()) {
			return false;
		}
		if(getSameWords(cta.toString())) {
			current = captureState();
		}
		return true;
	}

	private boolean getSameWords(String str) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("房间", new String[] {"狗窝", "洞房"});
		map.put("我", new String[] {"俺", "吾", "咱"});
		String[] words = map.get(str);
		if(null != words) {
			for(String word : words) {
				sames.push(word);
			}
			return true;
		}
		return false;
	}

}
