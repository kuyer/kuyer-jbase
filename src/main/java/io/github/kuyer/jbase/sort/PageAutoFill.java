package io.github.kuyer.jbase.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页 后页不足，则自动填充前页数据
 * @author rory.zhang
 */
public class PageAutoFill {
	
	public static void main(String[] args) {
		String[] strs = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
		List<String> list = Arrays.asList(strs);
		for(int i=1; i<100; i++) {
			test(list, i);
		}
	}
	
	public static void test(List<String> list, int page) {
		long limit = 7;
		long start = (page-1)*limit;
		int size = list.size();
		if(size<=limit) {
			List<String> list1 = list.parallelStream().skip(0).limit(limit).collect(Collectors.toList());
			System.out.println(list1);
		} else {
			long start1 = start%size;
			List<String> list1 = list.parallelStream().skip(start1).limit(limit).collect(Collectors.toList());
			if(size-start1<limit) {
				List<String> list0 = list.parallelStream().skip(0).limit(limit-(size-start1)).collect(Collectors.toList());
				list1.addAll(list0);
			}
			System.out.println(list1);
		}
	}

}
