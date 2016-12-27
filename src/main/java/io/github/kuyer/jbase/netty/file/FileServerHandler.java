package io.github.kuyer.jbase.netty.file;

import java.io.RandomAccessFile;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;

public class FileServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		RandomAccessFile raf = null;
		long length = -1;
		try {
			raf = new RandomAccessFile(msg, "r");
			length = raf.length();
		} catch (Exception e) {
			ctx.writeAndFlush("err: "+e.getClass().getSimpleName()+":"+e.getMessage()+"\n");
			return;
		} finally {
			if(length<0 && raf != null) {
				raf.close();
			}
		}
		ctx.write("ok: "+raf.length()+"\n");
		if(ctx.pipeline().get(SslHandler.class) == null) {
			ctx.write(new DefaultFileRegion(raf.getChannel(), 0, length));
		} else {
			ctx.write(new ChunkedFile(raf));
		}
		ctx.writeAndFlush("\n");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("hello, type the path of the file.\n");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if(ctx.channel().isActive()) {
			ctx.writeAndFlush("err: "+cause.getClass().getSimpleName()+":"+cause.getMessage()+"\n")
				.addListener(ChannelFutureListener.CLOSE);
		}
	}

}
