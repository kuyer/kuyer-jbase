package io.github.kuyer.jbase.sort.string;

/**
 * BF算法
 * 从主串S的第一个字符开始和模式T的第一个字符进行比较，若相等，则继续比较两者的后续字符；
 * 若不相等，则从主串S的第二个字符开始和模式T的第一个字符进行比较，重复上述过程，若T中的字符全部比较完毕，则说明本趟匹配成功；
 * 若S中的字符全部比较完毕，则匹配失败。这个算法称为朴素的模式匹配算法，简称BF算法
 * @author rory.zhang
 */
public class BruteForce {
	
	public static int isMatch(String s, String t) {
		int result = -1;
		int index = 0;
		int len = s.length();
		System.out.println("length1: "+len);
		int times = 0;
		for(int i=0; i<len; i++) {
			times ++;
			char c = s.charAt(i);
			if(c == t.charAt(index)) {
				if(result == -1) {
					result = i;
				}
				index++;
				if(index == t.length()) {
					break;
				}
			} else {
				index = 0;
				result = -1;
			}
		}
		System.out.println("times1: "+times);
		return result;
	}
	
	public static int isMatchx(String source, String target) {
		int slen = source.length();
		int tlen = target.length();
		if(slen<tlen) {
			return -1;
		}
		char[] sarr = source.toCharArray();
		char[] tarr = target.toCharArray();
		int times = 0;
		System.out.println("length2: "+slen);
		for(int i=tlen-1; i<slen; i++) {
			times ++;
			if(tarr[tlen-1] == sarr[i]) {
				int k = tlen-2;
				for(int j=i-1; j>i-tlen; j--) {
					times ++;
					if(tarr[k] == sarr[j]) {
						if(k == 0) {
							System.out.println("times2: "+times);
							return j;
						}
						k --;
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println("result1: "+isMatch("yorhellomynameisrrhowareyoxwhatisyornameyousfdsf", "you"));
		System.out.println("result2: "+isMatchx("yorhellomynameisrrhowareyoxwhatisyornameyousfdsf", "you"));
	}

}
