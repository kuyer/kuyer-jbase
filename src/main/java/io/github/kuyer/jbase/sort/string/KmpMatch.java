package io.github.kuyer.jbase.sort.string;

public class KmpMatch {
	
	public static int[] getNext(char[] cs) {
		int len = cs.length;
		int[] next = new int[len];
		if(len >= 1) {
			int i = -1;
			int j = 0;
			next[0] = i;
			while(j < len-1) {
				if(i==-1 || cs[j] == cs[i]) {
					i++;
					j++;
					next[j] = i;
				} else {
					i = next[i];
				}
			}
		}
		return next;
	}
	
	public static int indexOf(String source, String pattern) {
		char[] src = source.toCharArray();
		char[] ptn = pattern.toCharArray();
		int[] next = getNext(ptn);
		int i=0, j=0;
		int slen = src.length;
		int plen = ptn.length;
		int times = 0;
		while(i<slen && j<plen) {
			times ++;
			if(j==-1 || src[i] == ptn[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		System.out.println("times: "+times);
		if(j == plen) {
			return i-j;
		}
		return -1;
	}

	public static void main(String[] args) {
		/*char[] cs = "hehe".toCharArray();
		System.out.println(cs);
		int[] next = getNext(cs);
		for(int i : next) {
			System.out.print(i+",");
		}
		System.out.println();*/
		System.out.println(indexOf("yorhellomynameisrrhowareyoxwhatisyornameyou", "you"));
	}

}
