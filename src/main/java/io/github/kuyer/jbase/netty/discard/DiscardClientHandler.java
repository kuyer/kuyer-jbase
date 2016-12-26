package io.github.kuyer.jbase.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

	private ByteBuf bdata;
	private ChannelHandlerContext ctx;
	
	private void generateTraffic() {
		this.ctx.writeAndFlush(bdata.retainedDuplicate()).addListener(trafficGenerator);
	}
	
	private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			if(future.isSuccess()) {
				generateTraffic();
			} else {
				future.cause().printStackTrace();
				future.channel().close();
			}
		}};
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
		bdata = ctx.alloc().directBuffer(512).writeZero(512);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		bdata.release();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		this.ctx.close();
	}

}
