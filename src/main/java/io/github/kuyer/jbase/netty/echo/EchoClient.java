package io.github.kuyer.jbase.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class EchoClient {

	public static void main(String[] args) throws Exception {
		boolean ssl = System.getProperty("ssl") != null;
		String host = System.getProperty("host", "127.0.0.1");
		int port = Integer.parseInt(System.getProperty("port", ssl?"8992":"8993"));
		SslContext sslContext;
		if(ssl) {
			sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslContext = null;
		}
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();
						if(null != sslContext) {
							p.addLast(sslContext.newHandler(ch.alloc(), host, port));
						}
						//p.addLast(new LoggingHandler(LogLevel.INFO));
						p.addLast(new EchoClientHandler());
					}});
			ChannelFuture f = b.connect(host, port);
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

}
