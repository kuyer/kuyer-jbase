package io.github.kuyer.jbase.sort.hash;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/** 一致性Hash算法 **/
public class ConsistentHash<T> {
	
	/** hash函数 **/
	private HashFunction hash;
	/** 每个节点虚拟节点个数 **/
	private int replicas;
	/** 环形虚拟节点 **/
	private SortedMap<Long, T> circle = new TreeMap<Long, T>();

	public ConsistentHash(HashFunction hash, int replicas, Collection<T> nodes) {
		this.hash = hash;
		this.replicas = replicas;
		for(T node : nodes) {
			add(node);
		}
	}
	
	/** 增加节点 **/
	public void add(T node) {
		if(null == node) {
			return;
		}
		for(int i=0; i<this.replicas; i++) {
			circle.put(this.hash.hash(node.toString()+i), node);
		}
	}
	
	/** 删除节点 **/
	public void remove(T node) {
		if(null == node) {
			return;
		}
		for(int i=0; i<this.replicas; i++) {
			circle.remove(this.hash.hash(node.toString()+i));
		}
	}
	
	/** 获取Node节点 **/
	public T get(String key) {
		if(this.circle.isEmpty()) {
			return null;
		}
		Long hash = this.hash.hash(key);
		if(null == hash) {
			return null;
		}
		if(!circle.containsKey(hash)) {
			// 沿环的顺时针找到一个虚拟节点
			SortedMap<Long, T> tailMap = this.circle.tailMap(hash);
			hash = tailMap.isEmpty()?this.circle.firstKey():tailMap.firstKey();
		}
		return circle.get(hash);
	}
	
	public void print() {
		Set<Map.Entry<Long, T>> set = this.circle.entrySet();
		for(Map.Entry<Long, T> entry : set) {
			System.out.println(entry.getValue().toString() + ": "+entry.getKey());
		}
	}
	
}
