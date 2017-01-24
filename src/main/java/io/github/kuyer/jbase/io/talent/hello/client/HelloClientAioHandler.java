package io.github.kuyer.jbase.io.talent.hello.client;

import com.talent.aio.client.intf.ClientAioHandler;
import com.talent.aio.common.ChannelContext;

import io.github.kuyer.jbase.io.talent.hello.common.HelloAioHandler;
import io.github.kuyer.jbase.io.talent.hello.common.HelloPacket;

public class HelloClientAioHandler extends HelloAioHandler implements ClientAioHandler<Object, HelloPacket, Object> {

	private static HelloPacket heartbeatPacket = new HelloPacket();
	
	@Override
	public Object handler(HelloPacket packet, ChannelContext<Object, HelloPacket, Object> channelContext) throws Exception {
		// TODO Auto-generated method stub
		byte[] body = packet.getBody();
		if(null != body) {
			String message = new String(body, HelloPacket.CHARSET);
			System.out.println("client received: "+message);
		}
		return null;
	}

	@Override
	public HelloPacket heartbeatPacket() {
		return heartbeatPacket;
	}

}
