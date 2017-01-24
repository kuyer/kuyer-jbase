package io.github.kuyer.jbase.io.talent.hello.client;

import com.talent.aio.client.AioClient;
import com.talent.aio.client.ClientChannelContext;
import com.talent.aio.client.ClientGroupContext;
import com.talent.aio.client.intf.ClientAioHandler;
import com.talent.aio.client.intf.ClientAioListener;
import com.talent.aio.common.Aio;
import com.talent.aio.common.ReconnConf;

import io.github.kuyer.jbase.io.talent.hello.common.HelloPacket;

public class HelloClient {

	public static void main(String[] args) throws Exception {
		ClientAioHandler<Object, HelloPacket, Object> aioHandler = new HelloClientAioHandler();
		ClientAioListener<Object, HelloPacket, Object> aioListener = null;
		ReconnConf<Object, HelloPacket, Object> reconnConf = new ReconnConf<>();//自动连接
		ClientGroupContext<Object, HelloPacket, Object> clientGroupContext = new ClientGroupContext<>("127.0.0.1", HelloPacket.PORT, aioHandler, aioListener, reconnConf);
		AioClient<Object, HelloPacket, Object> aioClient = new AioClient<>(clientGroupContext);
		String bindIp = null;
		int bindPort = 0;
		ClientChannelContext<Object, HelloPacket, Object> clientChannelContext = aioClient.connect(bindIp, bindPort);
		HelloPacket packet = new HelloPacket();
		packet.setBody("hello, aio!".getBytes(HelloPacket.CHARSET));
		Aio.send(clientChannelContext, packet);
	}

}
