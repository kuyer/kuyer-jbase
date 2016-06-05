package io.github.kuyer.jbase.io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastServer {

	public static void main(String[] args) throws Exception {
		String host = "239.0.0.255";
		int port = 9200;
		MulticastSocket ms = new MulticastSocket(port);
		InetAddress inetAddr = InetAddress.getByName(host);
		ms.joinGroup(inetAddr);
		System.out.println("multicast server started on port: "+port);
		new Thread(new MulticastRunnable(ms)).start();
	}
	
}

class MulticastRunnable implements Runnable {
	
	private MulticastSocket ms;

	public MulticastRunnable(MulticastSocket ms) {
		this.ms = ms;
	}

	@Override
	public void run() {
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, 1024);
		while(true) {
			try {
				ms.receive(dp);
				System.out.println("receive: "+new String(buf, 0, dp.getLength()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
