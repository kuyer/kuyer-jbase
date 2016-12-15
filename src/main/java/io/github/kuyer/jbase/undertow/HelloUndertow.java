package io.github.kuyer.jbase.undertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class HelloUndertow {

	public static void main(String[] args) {
		Undertow undertow = Undertow.builder().addHttpListener(8989, "localhost").setHandler(new HttpHandler() {
			@Override
			public void handleRequest(HttpServerExchange exchange) throws Exception {
				exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
				exchange.getResponseSender().send("hello, undertow");
			}}).build();
		undertow.start();
	}

}
