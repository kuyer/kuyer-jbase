package io.github.kuyer.jbase.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

	public static void main(String[] args) throws Exception {
		String host = "127.0.0.1";
		int port = 9307;
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoClientHandler());
				}});
			ChannelFuture future = boot.connect().sync();
			System.out.println("echo server boot on "+future.channel().remoteAddress());
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
		
	}

}
