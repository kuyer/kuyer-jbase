package io.github.kuyer.jbase.sort.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTest {

	public static void main(String[] args) {
		String ipPrefix = "192.168.0.";
		String nodePrefix = "node-";
		String dataPrefix = "data-";
		
		Map<String, Integer> datas = new HashMap<String, Integer>();
		
		List<HashNode> nodes = new ArrayList<HashNode>();
		for(int i=0; i<10; i++) {
			String ip = ipPrefix+i;
			nodes.add(new HashNode(ip, nodePrefix+i));
			datas.put(ip, 0);
		}
		ConsistentHash<HashNode> ch = new ConsistentHash<HashNode>(
				new HashFunction(), 3, nodes);
		
		for(int i=0; i<500; i++) {
			String data = dataPrefix + i;
			HashNode node = ch.get(data);
			datas.put(node.getIp(), datas.get(node.getIp())+1);
		}
		
		for(int i=0; i<10; i++) {
			String ip = ipPrefix+i;
			System.out.println("ip: "+ip+"; datas: "+datas.get(ip));
		}
		
		System.out.println("==============================================");
		
		String dataKey = dataPrefix+"214";
		HashNode node = ch.get(dataKey);
		System.out.println(dataKey+": "+node.getIp()+"("+node.getName()+")");
		
		dataKey = dataPrefix+"215";
		node = ch.get(dataKey);
		System.out.println(dataKey+": "+node.getIp()+"("+node.getName()+")");
		
		dataKey = dataPrefix+"315";
		node = ch.get(dataKey);
		System.out.println(dataKey+": "+node.getIp()+"("+node.getName()+")");
		
		dataKey = dataPrefix+"312";
		node = ch.get(dataKey);
		System.out.println(dataKey+": "+node.getIp()+"("+node.getName()+")");
		
		dataKey = dataPrefix+"314";
		node = ch.get(dataKey);
		System.out.println(dataKey+": "+node.getIp()+"("+node.getName()+")");
		
		System.out.println("==============================================");
		
		ch.print();
	}

}
