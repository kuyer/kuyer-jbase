package io.github.kuyer.jbase.netty.telnet;

import java.net.InetAddress;
import java.util.Date;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class NettyTelnetServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.write("Welcome to "+InetAddress.getLocalHost().getHostName()+"!\r\n");
		ctx.write("It is "+new Date()+" now.\r\n");
		ctx.flush();
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String response;
		boolean close = false;
		if(msg.isEmpty()) {
			response = "Please type something.\r\n";
		} else if("exit".equalsIgnoreCase(msg.toLowerCase())) {
			response = "Have a good day!\r\n";
			close = true;
		} else {
			response = "Did you say '"+msg+"'?\r\n";
		}
		ChannelFuture future = ctx.write(response);
		if(close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
