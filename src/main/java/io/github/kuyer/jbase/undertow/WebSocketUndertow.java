package io.github.kuyer.jbase.undertow;

import java.io.IOException;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;

public class WebSocketUndertow {

	public static void main(String[] args) {
		Undertow.builder().addHttpListener(8989, "localhost").setHandler(
				Handlers.path().addPrefixPath("/chat", Handlers.websocket(new WebSocketConnectionCallback() {
					@Override
					public void onConnect(WebSocketHttpExchange exchange, WebSocketChannel channel) {
						channel.getReceiveSetter().set(new AbstractReceiveListener() {
							@Override
							protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message)
									throws IOException {
								String text = message.getData();
								for(WebSocketChannel session : channel.getPeerConnections()) {
									WebSockets.sendText(text, session, null);
								}
							}
						});
						channel.resumeReceives();
					}
				})).addPrefixPath("/", Handlers.resource(new ClassPathResourceManager(
						WebSocketUndertow.class.getClassLoader(), WebSocketUndertow.class.getPackage()))
						.addWelcomeFiles("undertow_chat_index.html"))
			).build().start();
	}

}
