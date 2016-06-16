package io.github.kuyer.jbase.lang.mbean;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

/**
 * JMX 即 Java Management Extensions Java管理扩展
 * MBean 即 Managed Beans 被管理的Beans
 * 通过jconsole jmc jvisualvm可查看和执行mbean
 * @author Rory.Zhang
 */
public class EchoApp {

	public static void main(String[] args) throws MalformedObjectNameException, 
			InstanceAlreadyExistsException, 
			NotCompliantMBeanException, 
			InstanceNotFoundException, 
			ReflectionException, 
			MBeanException, 
			InterruptedException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName oname = new ObjectName("io.github.kuyer.jbase.jmx:type=EchoMXBean");
		EchoMBean mbean = new Echo();
		mbs.registerMBean(mbean, oname);
		Object result = mbs.invoke(oname, "print", new Object[] {"kuyer"}, new String[] {"java.lang.String"});
		System.out.println(result.toString());
		Thread.sleep(Long.MAX_VALUE);
	}

}
