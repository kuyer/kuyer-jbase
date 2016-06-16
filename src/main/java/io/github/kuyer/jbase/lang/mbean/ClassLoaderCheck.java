package io.github.kuyer.jbase.lang.mbean;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

public class ClassLoaderCheck {

	public static void main(String[] args) {
		ClassLoadingMXBean mxbean = ManagementFactory.getClassLoadingMXBean();
		System.out.println("object name: "+mxbean.getObjectName());
		System.out.println("total class count: "+mxbean.getTotalLoadedClassCount());
		System.out.println("loaded class count: "+mxbean.getLoadedClassCount());
		System.out.println("unload class count: "+mxbean.getUnloadedClassCount());
	}

}
