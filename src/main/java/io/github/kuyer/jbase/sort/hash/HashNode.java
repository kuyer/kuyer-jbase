package io.github.kuyer.jbase.sort.hash;

/** 节点 **/
public class HashNode {
	
	/** IP **/
	private String ip;
	/** 名称 **/
	private String name;
	
	public HashNode() {}
	
	public HashNode(String ip, String name) {
		this.ip = ip;
		this.name = name;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.ip;
	}

}
