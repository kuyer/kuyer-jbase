package io.github.kuyer.jbase.netty4.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

@Sharable
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
	
	private int index = 0;
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			String type = "other idle";
			if(event.state() == IdleState.READER_IDLE) {
				type = "read idle";
			} else if(event.state() == IdleState.WRITER_IDLE) {
				type = "write idle";
			} else if(event.state() == IdleState.ALL_IDLE) {
				type = "all idle";
			}
			ByteBuf messageByteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HeartBeat-"+(index++)+".\n", CharsetUtil.UTF_8));
			ctx.writeAndFlush(messageByteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
			System.out.println(ctx.channel().remoteAddress()+" timeout. type: "+type);
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}
	
}
