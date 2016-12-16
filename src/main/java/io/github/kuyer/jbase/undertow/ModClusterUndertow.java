package io.github.kuyer.jbase.undertow;

import java.net.InetAddress;

import org.xnio.OptionMap;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.proxy.mod_cluster.MCMPConfig;
import io.undertow.server.handlers.proxy.mod_cluster.ModCluster;

public class ModClusterUndertow {
	
	/* the host & port to receive the mcmp elements */
	static String mcmpHost = System.getProperty("io.github.kuyer.jbase.undertow.MCMP_HOST", "localhost");
	static int mcmpPort = Integer.parseInt(System.getProperty("io.github.kuyer.jbase.undertow.MCMP_PORT", "8981"));

	static String proxyHost = System.getProperty("io.github.kuyer.jbase.undertow.PROXY_HOST", "localhost");
	static int proxyPort = Integer.parseInt(System.getProperty("io.github.kuyer.jbase.undertow.PROXY_PORT", "8982"));
	
	
	public static void main(String[] args) throws Exception {
		final XnioWorker worker = Xnio.getInstance().createWorker(OptionMap.EMPTY);
		ModCluster modCluster = ModCluster.builder(worker).build();
		if(null == mcmpHost) {
			mcmpHost = InetAddress.getLocalHost().getHostName();
			System.out.println("Using Host: " + mcmpHost);
		}
		modCluster.start();
		
		HttpHandler proxy = modCluster.createProxyHandler();
		MCMPConfig config = MCMPConfig.webBuilder().setManagementHost(mcmpHost)
				.setManagementPort(mcmpPort).enableAdvertise().getParent().build();
		HttpHandler mcmp = config.create(modCluster, proxy);
		Undertow undertow = Undertow.builder().addHttpListener(mcmpPort, mcmpHost).addHttpListener(proxyPort, proxyHost).setHandler(mcmp).build();
		undertow.start();
		
		modCluster.advertise(config);
	}

}
