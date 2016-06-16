package io.github.kuyer.jbase.lang.mbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ServerApp {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName oname = new ObjectName("io.github.kuyer.jbase.server:type=ServerMBean");
		Server mbean = new Server();
		mbs.registerMBean(mbean, oname);
		ServerListener listener = new ServerListener();
		mbs.addNotificationListener(oname, listener, null, null);
		System.out.println("server start success.");
		Thread.sleep(Long.MAX_VALUE);
	}

}
