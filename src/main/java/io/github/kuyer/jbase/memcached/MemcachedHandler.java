package io.github.kuyer.jbase.memcached;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemcachedHandler extends SimpleChannelInboundHandler<MemcachedCommand<MemcachedResponse>> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MemcachedCommand<MemcachedResponse> response)
			throws Exception {
		logger.info("memcached client receive message.");
		response.setResponse();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.info("memcached client has exception. {}", cause.getCause());
		ctx.close();
	}

}
