package io.github.kuyer.jbase.sort.string;

/**
 * BF算法
 * 从主串S的第一个字符开始和模式T的第一个字符进行比较，若相等，则继续比较两者的后续字符；
 * 若不相等，则从主串S的第二个字符开始和模式T的第一个字符进行比较，重复上述过程，若T中的字符全部比较完毕，则说明本趟匹配成功；
 * 若S中的字符全部比较完毕，则匹配失败。这个算法称为朴素的模式匹配算法，简称BF算法
 * @author rory.zhang
 */
public class BruteForce {

	public static void main(String[] args) {
		String s = "yorhellomynameisrrhowareyouwhatisyornameyou";
		String t = "your";
		
		boolean flag = false;
		int index = 0;
		int len = s.length();
		for(int i=0; i<len; i++) {
			char c = s.charAt(i);
			if(c == t.charAt(index)) {
				index++;
				if(index == t.length()) {
					flag = true;
					break;
				}
			} else {
				index = 0;
			}
		}
		System.out.println("result: "+flag);
	}

}
