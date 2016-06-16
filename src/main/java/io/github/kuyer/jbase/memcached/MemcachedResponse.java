package io.github.kuyer.jbase.memcached;

public class MemcachedResponse {
	
	private boolean flag;
	private Object result;
	private Throwable throwable;
	
	public MemcachedResponse(boolean flag) {
		this.flag = flag;
	}
	
	public MemcachedResponse(Object result) {
		this.flag = true;
		this.result = result;
	}
	
	public MemcachedResponse(Throwable throwable) {
		this.flag = false;
		this.throwable = throwable;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
