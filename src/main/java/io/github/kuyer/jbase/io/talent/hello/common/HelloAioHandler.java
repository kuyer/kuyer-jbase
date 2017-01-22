package io.github.kuyer.jbase.io.talent.hello.common;

import java.nio.ByteBuffer;

import com.talent.aio.common.ChannelContext;
import com.talent.aio.common.exception.AioDecodeException;
import com.talent.aio.common.intf.AioHandler;

public abstract class HelloAioHandler implements AioHandler<Object, HelloPacket, Object> {

	@Override
	public ByteBuffer encode(HelloPacket packet, ChannelContext<Object, HelloPacket, Object> channelContext) {
		byte[] body = packet.getBody();
		int bodyLen = 0;
		if(null != body) {
			bodyLen = body.length;
		}
		int allLen = HelloPacket.HEADER_LENGTH + bodyLen;
		ByteBuffer buffer = ByteBuffer.allocate(allLen);
		buffer.order(channelContext.getGroupContext().getByteOrder());
		if(null != body) {
			buffer.put(body);
		}
		return buffer;
	}

	@Override
	public HelloPacket decode(ByteBuffer buffer, ChannelContext<Object, HelloPacket, Object> channelContext)
			throws AioDecodeException {
		// TODO Auto-generated method stub
		int readLen = buffer.limit() - buffer.position();
		if(readLen < HelloPacket.HEADER_LENGTH) {
			return null;
		}
		int bodyLen = buffer.getInt();
		if(bodyLen < 0) {
			throw new AioDecodeException("bodyLen ["+bodyLen+"] is not right, remote: "+channelContext.getClientNode());
		}
		int needLen = HelloPacket.HEADER_LENGTH + bodyLen;
		int restLen = readLen - needLen;
		if(restLen < 0) {
			return null;
		}
		HelloPacket packet = new HelloPacket();
		if(bodyLen > 0) {
			byte[] dist = new byte[bodyLen];
			buffer.get(dist);
			packet.setBody(dist);
		}
		return packet;
	}

}
