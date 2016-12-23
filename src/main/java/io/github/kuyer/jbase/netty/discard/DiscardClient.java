package io.github.kuyer.jbase.netty.discard;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class DiscardClient {
	
	public static void main(String[] args) throws Exception {
		boolean ssl = System.getProperty("ssl") != null;
		String host = System.getProperty("host", "127.0.0.1");
		int port = Integer.parseInt(System.getProperty("port", ssl?"8992":"8993"));
		int size = Integer.parseInt(System.getProperty("size", "256"));
		final SslContext sslContext;
		if(ssl) {
			sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslContext = null;
		}
	}

}
