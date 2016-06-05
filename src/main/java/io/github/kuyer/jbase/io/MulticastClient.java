package io.github.kuyer.jbase.io;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {

	public static void main(String[] args) throws Exception {
		String host = "239.0.0.255";
		int port = 9200;
		
		MulticastSocket ms = new MulticastSocket();
		ms.setTimeToLive(32);
		
		String message = "multicast for test ...";
		byte[] bytes = message.getBytes();
		InetAddress inetAddr = InetAddress.getByName(host);
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, inetAddr, port);
		ms.send(dp);
		ms.close();
	}

}
