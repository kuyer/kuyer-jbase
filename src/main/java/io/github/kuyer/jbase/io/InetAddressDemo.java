package io.github.kuyer.jbase.io;

import java.net.InetAddress;

public class InetAddressDemo {
	
	private static void print(InetAddress inet) throws Exception {
		System.out.println("inet: "+inet);
		System.out.println("原始IP地址："+inet.getAddress());
		System.out.println("IP地址："+inet.getHostAddress());
		System.out.println("主机名："+inet.getHostName());
	}

	public static void main(String[] args) throws Exception {
		System.out.println("local inet address");
		InetAddress inet1 = InetAddress.getLocalHost();
		print(inet1);
		
		System.out.println("remote inet address");
		InetAddress inet2 = InetAddress.getByName("192.168.0.6");
		print(inet2);
		
		InetAddress[] inet3 = InetAddress.getAllByName("www.baidu.com");
		for(InetAddress inet : inet3) {
			System.out.println("www.baidu.com inet address");
			print(inet);
		}
	}

}
