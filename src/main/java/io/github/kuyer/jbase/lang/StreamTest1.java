package io.github.kuyer.jbase.lang;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest1 {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(4, 2, 7, 3, 1, 5, 9, 6);
		Stream<Integer> stream = list.stream();
		stream.filter(i->i!=7).skip(1).limit(3).forEach(System.out::print);
		System.out.println();
		System.out.println(Arrays.asList(3, 2, 5).stream().reduce((a, b)->a+b).get());
	}

}
