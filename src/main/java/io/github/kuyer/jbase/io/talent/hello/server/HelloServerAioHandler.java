package io.github.kuyer.jbase.io.talent.hello.server;

import com.talent.aio.common.Aio;
import com.talent.aio.common.ChannelContext;
import com.talent.aio.server.intf.ServerAioHandler;

import io.github.kuyer.jbase.io.talent.hello.common.HelloAioHandler;
import io.github.kuyer.jbase.io.talent.hello.common.HelloPacket;

public class HelloServerAioHandler extends HelloAioHandler implements ServerAioHandler<Object, HelloPacket, Object> {

	@Override
	public Object handler(HelloPacket packet, ChannelContext<Object, HelloPacket, Object> channelContext)
			throws Exception {
		byte[] body = packet.getBody();
		if(null != body) {
			String message = new String(body, HelloPacket.CHARSET);
			System.out.println("server received: "+message);
			HelloPacket response = new HelloPacket();
			response.setBody(("your message is "+message).getBytes(HelloPacket.CHARSET));
			Aio.send(channelContext, response);
		}
		return null;
	}

}
