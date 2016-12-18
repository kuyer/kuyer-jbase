package io.github.kuyer.jbase.netty.telnet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

public class NettyTelnetClientInitializer extends ChannelInitializer<SocketChannel> {
	
	private static final StringDecoder DECODER = new StringDecoder();
	private static final StringEncoder ENCODER = new StringEncoder();
	
	private static final NettyTelnetClientHandler CLIENT_HANDLER = new NettyTelnetClientHandler();
	
	private final SslContext sslContext;

	public NettyTelnetClientInitializer(SslContext sslContext) {
		this.sslContext = sslContext;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if(null != sslContext) {
			pipeline.addLast(sslContext.newHandler(ch.alloc(), NettyTelnetClient.HOST, NettyTelnetClient.PORT));
		}
		pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast(DECODER);
		pipeline.addLast(ENCODER);
		pipeline.addLast(CLIENT_HANDLER);
	}

}
