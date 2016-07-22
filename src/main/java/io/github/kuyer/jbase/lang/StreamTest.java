package io.github.kuyer.jbase.lang;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// http://blog.csdn.net/chszs/article/details/47038607
public class StreamTest {

	public static void main(String[] args) {
		// 1. 遍历并打印容器中的元素
		IntStream.range(0, 10).forEach(value -> System.out.print(value+" "));
		System.out.println();
		System.out.println("========================= split =========================");
		
		// 2. 统计容器中元素的个数
		List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
		System.out.println(list.stream().count());
		System.out.println("========================= split =========================");
		
		// 3. 计算容器中元素的平均数
		Double average = list.stream().collect(Collectors.averagingInt(item -> item));
		System.out.println(average);
		System.out.println("========================= split =========================");
		
	}

}
