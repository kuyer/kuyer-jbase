package io.github.kuyer.jbase.undertow;

import java.net.URI;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.ResponseCodeHandler;
import io.undertow.server.handlers.proxy.LoadBalancingProxyClient;
import io.undertow.server.handlers.proxy.ProxyHandler;
import io.undertow.util.Headers;

public class ReverseProxyUndertow {

	public static void main(String[] args) throws Exception {
		Undertow.builder().addHttpListener(8981, "localhost")
			.setHandler(new HttpHandler() {
				@Override
				public void handleRequest(HttpServerExchange exchange) throws Exception {
					exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
					exchange.getResponseSender().send("hello, undertow! this is server1");
				}})
			.build().start();
		Undertow.builder().addHttpListener(8982, "localhost")
			.setHandler(new HttpHandler() {
				@Override
				public void handleRequest(HttpServerExchange exchange) throws Exception {
					exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
					exchange.getResponseSender().send("hello, undertow! this is server2");
				}})
			.build().start();
		Undertow.builder().addHttpListener(8983, "localhost")
			.setHandler(new HttpHandler() {
				@Override
				public void handleRequest(HttpServerExchange exchange) throws Exception {
					exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
					exchange.getResponseSender().send("hello, undertow! this is server3");
				}})
			.build().start();
		LoadBalancingProxyClient loadBalancer = new LoadBalancingProxyClient()
				.addHost(new URI("http://localhost:8981"))
				.addHost(new URI("http://localhost:8982"))
				.addHost(new URI("http://localhost:8983"))
				.setConnectionsPerThread(20);
		Undertow undertow = Undertow.builder().addHttpListener(8989, "localhost")
				.setIoThreads(4)
				.setHandler(new ProxyHandler(loadBalancer, 30000, ResponseCodeHandler.HANDLE_404))
				.build();
		undertow.start();
	}

}
