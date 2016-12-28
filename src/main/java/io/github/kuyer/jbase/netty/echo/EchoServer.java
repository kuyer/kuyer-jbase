package io.github.kuyer.jbase.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class EchoServer {

	public static void main(String[] args) throws Exception {
		boolean ssl = System.getProperty("ssl") != null;
		int port = Integer.parseInt(System.getProperty("port", ssl?"8992":"8993"));
		SslContext sslContext;
		if(ssl) {
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} else {
			sslContext = null;
		}
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 512).handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();
						if(null != sslContext) {
							p.addLast(sslContext.newHandler(ch.alloc()));
						}
						p.addLast(new EchoServerHandler());
					}});
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}
