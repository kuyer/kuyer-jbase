package io.github.kuyer.jbase.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * telnet localhost 9025
 * 快捷键：“ctrl+]”
 * 发送信息：send hello, for test!
 * @author rory.zhang
 */
public class TelnetServer {
	
	public TelnetServer() throws Exception {
		this(9025);
	}
	
	public TelnetServer(int port) throws Exception {
		init(port);
	}

	private void init(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();//用于服务器端接受客户端的连接
		EventLoopGroup workGroup = new NioEventLoopGroup();//用于网络事件的处理
		try {
			ServerBootstrap sboot = new ServerBootstrap();
			sboot.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TelnetServerHandler());
				}}).option(ChannelOption.SO_BACKLOG, 1024);//指定此套接口排队的最大连接个数
			ChannelFuture future = sboot.bind(port).sync();
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new TelnetServer();
	}

}
