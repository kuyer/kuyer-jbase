package io.github.kuyer.jbase.io.talent.hello.server;

import java.io.IOException;

import com.talent.aio.server.AioServer;
import com.talent.aio.server.ServerGroupContext;
import com.talent.aio.server.intf.ServerAioHandler;
import com.talent.aio.server.intf.ServerAioListener;

import io.github.kuyer.jbase.io.talent.hello.common.HelloPacket;

public class HelloServer {

	public static void main(String[] args) throws IOException {
		ServerAioHandler<Object, HelloPacket, Object> aioHandler = new HelloServerAioHandler();
		ServerAioListener<Object, HelloPacket, Object> aioListener = null;
		ServerGroupContext<Object, HelloPacket, Object> serverGroupContext = new ServerGroupContext<>("", HelloPacket.PORT, aioHandler, aioListener);
		AioServer<Object, HelloPacket, Object> aioServer = new AioServer<>(serverGroupContext);
		aioServer.start();
	}

}
