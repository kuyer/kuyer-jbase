package io.github.kuyer.jbase.memcached;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemcachedClient {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private EventLoopGroup workGroup;
	private Channel channel;
	
	public void connect(String host, Integer port) throws Exception {
		workGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(workGroup).channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new MemcachedDecoder());
					ch.pipeline().addLast(new MemcachedEncoder());
					ch.pipeline().addLast(new MemcachedHandler());
				}});
		channel = bootstrap.connect(host, port).sync().channel();
		logger.info("open memcached connect {}:{} success.", host, port);
	}
	
	public void close() throws Exception {
		if(null != channel) {
			channel.closeFuture().sync();
		}
		workGroup.shutdownGracefully();
		workGroup = null;
		channel = null;
		logger.info("close memcached connect.");
	}
	
	public boolean set(String key, Object value) {
		MemcachedRequest request = new MemcachedRequest();
		request.setType(MemcachedRequest.TYPE_SET);
		request.setKey(key);
		request.setValue(value);
		channel.writeAndFlush(request);
		return false;
	}
	
	public Object get(String key) {
		MemcachedRequest request = new MemcachedRequest();
		request.setType(MemcachedRequest.TYPE_GET);
		request.setKey(key);
		channel.writeAndFlush(request);
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		MemcachedClient mclient = new MemcachedClient();
		mclient.connect("192.168.56.110", 11211);
		System.out.println("set: "+mclient.set("name", "rory"));
		System.out.println("get: "+mclient.get("name").toString());
		mclient.close();
	}

}
