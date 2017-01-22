package io.github.kuyer.jbase.io.talent.hello.common;

import com.talent.aio.common.intf.Packet;

public class HelloPacket extends Packet {
	
	public static final int PORT = 9017;
	public static final int HEADER_LENGTH = 4;//消息头的长度
	public static final String CHARSET = "utf-8";
	
	private byte[] body;
	
	public byte[] getBody() {
		return body;
	}
	
	public void setBody(byte[] body) {
		this.body = body;
	}

}
