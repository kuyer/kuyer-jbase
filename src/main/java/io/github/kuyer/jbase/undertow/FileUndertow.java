package io.github.kuyer.jbase.undertow;

import java.nio.file.Paths;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;

public class FileUndertow {

	public static void main(String[] args) {
		Undertow undertow = Undertow.builder().addHttpListener(8989, "localhost")
				.setHandler(Handlers.resource(new PathResourceManager(Paths.get(System.getProperty("user.home")), 100)).setDirectoryListingEnabled(true))
				.build();
		undertow.start();
	}

}
