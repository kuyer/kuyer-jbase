package io.github.kuyer.jbase.netty.telnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class NettyTelnetClient {
	
	static final boolean SSL = System.getProperty("ssl") != null;
	static final String HOST = System.getProperty("host", "127.0.0.1");
	static final int PORT = Integer.parseInt(System.getProperty("port", SSL?"8992":"8993"));

	public static void main(String[] args) throws Exception {
		final SslContext sslContext;
		if(SSL) {
			sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslContext = null;
		}
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new NettyTelnetClientInitializer(sslContext));
			Channel c = b.connect(HOST, PORT).sync().channel();
			ChannelFuture f = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			for(;;) {
				String line = reader.readLine();
				if(line == null) {
					break;
				}
				f = c.writeAndFlush(line+"\r\n");
				if("exit".equals(line.toLowerCase())) {
					c.closeFuture().sync();
					break;
				}
			}
			if(null != f) {
				f.sync();
			}
		} finally {
			group.shutdownGracefully();
		}
	}

}
