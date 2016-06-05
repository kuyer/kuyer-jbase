package io.github.kuyer.jbase.io;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 广播服务
 * @author rory.zhang
 */
public class BroadcastServer {

	public static void main(String[] args) throws Exception {
		int port = 9200;
		byte[] buf = new byte[1024];//存储发来的消息
		DatagramSocket ds = new DatagramSocket(port);
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		System.out.println("broadcast server start on port: "+port);
		ds.receive(dp);
		ds.close();
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<1024; i++) {
			if(buf[i] == 0) {
				break;
			}
			sb.append((char) buf[i]);
		}
		System.out.println("receive: "+sb.toString());
	}

}
