package io.github.kuyer.jbase.io;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 广播客户端
 * @author rory.zhang
 */
public class BroadcastClient {

	public static void main(String[] args) throws Exception {
		// 广播地址
		String host = "255.255.255.255";
		int port = 9200;
		String message = "broadcast for test ...";
		
		InetAddress inetAddr = InetAddress.getByName(host);
		DatagramSocket ds = new DatagramSocket();
		DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), inetAddr, port);
		ds.send(dp);
		ds.close();
	}

}
