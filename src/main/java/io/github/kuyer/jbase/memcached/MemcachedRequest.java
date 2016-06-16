package io.github.kuyer.jbase.memcached;

public class MemcachedRequest {
	
	public static final String TYPE_SET = "set";
	public static final String TYPE_GET = "get";
	
	private String type;
	private String key;
	private Object value;
	private Long timeout;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Long getTimeout() {
		return timeout;
	}
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

}
