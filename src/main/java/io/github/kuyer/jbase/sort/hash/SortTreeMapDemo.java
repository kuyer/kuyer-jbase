package io.github.kuyer.jbase.sort.hash;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortTreeMapDemo {
	
	public static void main(String[] args) {
		SortedMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(12, "1201");
		map.put(8, "801");
		map.put(35, "1501");
		map.put(3, "301");
		map.put(42, "1101");
		map.put(21, "1101");
		Set<Map.Entry<Integer, String>> set = map.entrySet();
		for(Map.Entry<Integer, String> entry : set) {
			System.out.println(entry.getKey());
		}
		System.out.println("================================================");
		
		SortedMap<Integer, String> mm = map.tailMap(18);
		for(Map.Entry<Integer, String> entry : mm.entrySet()) {
			System.out.println(entry.getKey());
		}
		System.out.println("================================================");
		
		System.out.println(mm.isEmpty()?map.firstKey():mm.firstKey());
	}

}
