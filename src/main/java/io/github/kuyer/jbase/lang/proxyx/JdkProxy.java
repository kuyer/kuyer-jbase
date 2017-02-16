package io.github.kuyer.jbase.lang.proxyx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
	
	public static Object createProxy(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				before();
				Object obj = null;
				try {
					obj = method.invoke(target, args);
				} catch (Exception e) {
					exception(e);
				}
				after();
				return obj;
			}});
	}
	
	private static void before() {
		System.out.println("jdk proxy before.");
	}
	
	private static void exception(Exception e) {
		System.out.println("jdk proxy exception. "+e.getMessage());
	}
	
	private static void after() {
		System.out.println("jdk proxy after.");
	}
	
	public static void main(String[] args) {
		IProxy proxy1 = new ProxyImpl();
		IProxy proxy2 = (IProxy) createProxy(proxy1);
		System.out.println(proxy2.say("jdkproxy"));
	}

}
